import model.Event;
import model.EventTypeEnum;
import service.Database;
import service.DatabaseValidator;
import service.LineProcessor;
import service.LineReaderIterator;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static service.ErrorLogger.setupLogger;

public class Main {

    public static void main(String[] args) throws IOException {
        // read csvFile
        LineReaderIterator reader = new LineReaderIterator();
        LineProcessor processor = new LineProcessor();
        Database database = new Database();

        Logger logger = setupLogger();
        // Step 1 : read the line
        String line = reader.getNextLine();
        while (line != null) {
            //System.out.println("line: " + line);

            // Step 2 : parse the line
            Event event = null;
            try {
                event = processor.parseLine(line);
                    // Step 3 : update database
                if (EventTypeEnum.isCreateType(event.getEventType())) {
                    DatabaseValidator.validateUserData(event.getUser());
                    database.addUser(event.getUser());
                }
                else if (EventTypeEnum.isUpdateType(event.getEventType())) {
                    database.updateUser(event);
                }
                else
                    throw new IllegalArgumentException("Unknown event type: '" + event.getEventType() + "'.");
            } catch(IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
                logger.log(Level.SEVERE, "An issue occurs with line: '" + line +"'. Error: " + e.getMessage());
            }

            line = reader.getNextLine();
        }
        // Ensure all log records are flushed to the file before exiting
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
        reader.close();
    }
}

import model.Event;
import model.EventTypeEnum;
import rest.ApiServer;
import service.*;

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
        int lineCounter = 0;

        // Step 1 : read the line
        String line = reader.getNextLine();
        lineCounter++;
        while (line != null) {
            //System.out.println("line: " + line);

            if (!line.isEmpty()) {
                // Step 2 : parse the line
                Event event = null;
                try {
                    event = processor.parseLine(line);

                    // Step 3 : update database
                    if (EventTypeEnum.isCreateType(event.getEventType())) {
                        database.addUser(event.getUser());
                    } else if (EventTypeEnum.isUpdateType(event.getEventType())) {
                        database.updateUser(event);
                    } else
                        throw new IllegalArgumentException("Unknown event type: '" + event.getEventType() + "'.");
                } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
                    logger.log(Level.SEVERE, "An issue occurs in line " + lineCounter + " : '" + line + "'. Error: " + e.getMessage());
                }
            }
            line = reader.getNextLine();
            lineCounter++;
        }

        // Run API server
        ApiServer apiServer = new ApiServer(database);
        apiServer.setUp();

        // Ensure all log records are flushed to the log file before exiting
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
        reader.close();
    }
}

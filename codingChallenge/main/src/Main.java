import model.Event;
import model.EventTypeEnum;
import service.Database;
import service.LineProcessor;
import service.LineReaderIterator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // read csvFile
        LineReaderIterator reader = new LineReaderIterator();
        LineProcessor processor = new LineProcessor();
        Database database = new Database();

        // Step 1 : read the line
        String line = reader.getNextLine();
        while (line != null) {
            //System.out.println("line: " + line);

            // Step 2 : parse the line
            Event event = processor.parseLine(line);

            /*
            // Step 3 : update database
            if (EventTypeEnum.isCreateType(event.getEventType()))
                database.addUser(event.getUser());
            else if (EventTypeEnum.isUpdateType(event.getEventType()))
                database.updateUser(event);
            else
                throw new IllegalArgumentException("Unknown event type : " + event.getEventType());

             */
            line = reader.getNextLine();
        }
        reader.close();
    }
}

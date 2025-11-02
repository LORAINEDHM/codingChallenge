package service;

import java.io.IOException;
import java.util.logging.*;

public class ErrorLogger {

    private static final String LOG_FILE = System.getenv("LOG_PATH") != null ? System.getenv("LOG_PATH") : "application.log";

    /**
     * Initializes the logger to write output to a file.
     * This method should be called once at application startup.
     */
    public static Logger setupLogger() {
        Logger logger = Logger.getLogger("logger");

        try {

            logger.setUseParentHandlers(false);
            // 1. Remove default handlers (like ConsoleHandler) to only use the file.
            // (Optional: If you want console output too, skip this step)

            for (Handler handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }

            // to append logs to the file instead of overwriting it each time
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            //FileHandler fileHandler = new FileHandler(LOG_FILE, 0, 1, true);

            fileHandler.setLevel(Level.ALL);

            //Set the formatter to make the output readable (non-XML format)
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
            logger.setLevel(Level.WARNING);
            logger.info("Logger initialized successfully. Logs are being written to " + LOG_FILE);

        } catch (IOException e) {
            // If the file cannot be created or written to, print the error to console
            System.err.println("Fatal: Could not set up file logging handler.");
            e.printStackTrace();
        }
        return logger;
    }

}

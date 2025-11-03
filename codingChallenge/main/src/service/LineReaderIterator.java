package service;

import java.io.*;

public class LineReaderIterator {

    private static final String CSV_FILE_NAME = "user_events.csv";
    private final BufferedReader br;

    public LineReaderIterator() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(CSV_FILE_NAME);

        if (is == null) {
            throw new IOException("Error: The CSV file '" + CSV_FILE_NAME + "' was not found in the classpath (e.g., in main/resources).");
        }

        this.br = new BufferedReader(new InputStreamReader(is));
    };

    public String getNextLine() throws IOException {
        return this.br.readLine();
    }

    public void close() throws IOException {
        if (this.br != null) {
            this.br.close();
        }
    }
}

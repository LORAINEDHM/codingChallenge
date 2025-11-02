package service;

import java.io.*;

public class LineReaderIterator {

    private static final String CSV_FILE_NAME = "user_events.csv";
    private final BufferedReader br;

    public LineReaderIterator() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(CSV_FILE_NAME);
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

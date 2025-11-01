import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // read csvFile
        LineReaderIterator reader = new LineReaderIterator();
        String line = reader.getNextLine();
        while (line != null) {
            //System.out.println("line: " + line);
            line = reader.getNextLine();
        }
        reader.close();
    }
}

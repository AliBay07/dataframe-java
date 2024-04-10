import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSVReader {
    private String filename;
    private Scanner sc;
    private String delimiter = ";";

    /**
     * Constructs a CSVReader object to read from the specified file with the default delimiter.
     *
     * @param filename The name of the CSV file to read from.
     */
    public CSVReader(String filename) {
        this.filename = filename;
        try {
            this.sc = new Scanner(new File(filename));
            this.sc.useDelimiter("\\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a CSVReader object to read from the specified file with a custom delimiter.
     *
     * @param filename  The name of the CSV file to read from.
     * @param delimiter The delimiter used to separate fields in the CSV file.
     */
    public CSVReader(String filename, String delimiter) {
        this(filename);
        this.delimiter = delimiter;
    }

    /**
     * Reads the next line from the CSV file and splits it into fields.
     *
     * @return An array of Objects representing the fields on the next line.
     */
    public Object[] nextLine() {
        if (!this.sc.hasNext())
            return new Object[]{};
        String[] str = sc.next().split(this.delimiter);
        Object[] res = new Object[str.length];

        // Iterate over the split array
        for (int i = 0; i < str.length; i++) {
            // Check if the value is an integer and convert if so
            try {
                Integer intValue = Integer.parseInt((str[i]));
                res[i] = intValue;
            } catch (NumberFormatException e1) {
                // Check if the value is a double and convert if so
                try {
                    Double doubleValue = Double.parseDouble((String) str[i]);
                    res[i] = doubleValue;
                } catch (NumberFormatException e2) {
                    res[i] = str[i];
                }
            }
            // In case it is a string, nothing will be done, as it is already read as strings from the file
        }
        return res;
    }

    /**
     * Reads all lines from the CSV file and returns them as a list of arrays of Objects.
     *
     * @return A 2D array containing arrays of Objects representing each line in the CSV file.
     */
    public Object[][] allLines() {
        ArrayList<Object[]> list = new ArrayList<>();
        while (this.sc.hasNext()) {
            list.add(this.nextLine());
        }

        Object[][] res = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * Checks if the end of the file (EOF) has been reached.
     *
     * @return true if the end of the file has been reached, otherwise false.
     */
    public boolean EOF() {
        return !sc.hasNext();
    }
}

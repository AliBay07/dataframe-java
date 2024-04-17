import org.junit.Test;
import static org.junit.Assert.*;

public class CSVReaderTest {
    CSVReader csvReader;
    Object[][] data = {
            {"city", "country", "temperature"},
            {"rio de janeiro", "brazil", 10},
            {"grenoble", "france", 15},
            {"tokyo", "japan", 1.6},
            {"new york", "usa", 10.7},
            {"london", "uk", 34.4},
            {"yerevan","", ""},
            {"","armenia", ""},
            {"","", 3}
    };

    @Test
    public void testNextLine_default_delimiter(){
        this.csvReader = new CSVReader("src/test/sample/default_delimiter_with_spaces.csv");
        for(int i = 0; !csvReader.EOF(); i++) {
            assertFalse(csvReader.EOF());
            Object[] line = csvReader.nextLine();
            for (int j = 0; j < line.length; j++)
                assertEquals(data[i][j], line[j]);
        }
        assertTrue(csvReader.EOF());
        assertEquals(new Object[]{}, csvReader.nextLine());
    }

    @Test
    public void testNextLine_delimiter1(){
        this.csvReader = new CSVReader("src/test/sample/delimiter1_with_spaces.csv", ";");
        for(int i = 0; !csvReader.EOF(); i++){
            assertFalse(csvReader.EOF());
            Object[] line = csvReader.nextLine();
            for(int j = 0; j < line.length; j++)
                assertEquals(data[i][j], line[j]);
        }
        assertTrue(csvReader.EOF());
        assertEquals(new Object[]{}, csvReader.nextLine());
    }

    @Test
    public void testAllLines(){
        this.csvReader = new CSVReader("src/test/sample/default_delimiter_with_spaces.csv");
        Object[][] read = this.csvReader.allLines();
        for(int i = 0; i < read.length; i++)
            for(int j = 0; j < read[i].length; j++)
                assertEquals(data[i][j], read[i][j]);
        assertTrue(csvReader.EOF());
    }
}
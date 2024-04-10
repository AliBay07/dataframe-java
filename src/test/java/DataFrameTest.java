import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataFrameTest {

    @Test
    public void testGetRowValues() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        DataFrame df = new DataFrame(columnsLabels, rowsValues);

        ArrayList<Object> expectedRowValues = new ArrayList<>();
        expectedRowValues.add("Serge");
        expectedRowValues.add(24);
        expectedRowValues.add("Armenia");

        assertEquals(expectedRowValues, df.getRowValues(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetRowValuesWithInvalidIndex() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        DataFrame df = new DataFrame(columnsLabels, rowsValues);

        df.getRowValues(10);
    }

    @Test
    public void testGetColumnLabels() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        DataFrame df = new DataFrame(columnsLabels, rowsValues);

        ArrayList<String> expectedColumnLabels = new ArrayList<>();
        expectedColumnLabels.add("Name");
        expectedColumnLabels.add("Age");
        expectedColumnLabels.add("Country");

        assertEquals(expectedColumnLabels, df.getColumnLabels());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyData() {
        Object[][] data = {};
        DataFrame df = new DataFrame(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRow() {
        Object[][] data = {{}};
        DataFrame df = new DataFrame(data);
    }

    @Test
    public void testConstructorWithValidData() {
        Object[][] data = {
                {"Name", "Age", "Country"},
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };

        DataFrame df = new DataFrame(data);

        assertEquals("Name", df.getColumnLabels().get(0));
        assertEquals("Age", df.getColumnLabels().get(1));
        assertEquals("Country", df.getColumnLabels().get(2));

        assertEquals("Ali", df.getColumnValues("Name").get(0));
        assertEquals(21, df.getColumnValues("Age").get(0));
        assertEquals("Lebanon", df.getColumnValues("Country").get(0));

        assertEquals("Serge", df.getColumnValues("Name").get(1));
        assertEquals(24, df.getColumnValues("Age").get(1));
        assertEquals("Armenia", df.getColumnValues("Country").get(1));

        assertEquals("Jorane", df.getColumnValues("Name").get(2));
        assertEquals(23, df.getColumnValues("Age").get(2));
        assertEquals("France", df.getColumnValues("Country").get(2));

        assertEquals("Noemie", df.getColumnValues("Name").get(3));
        assertEquals(23, df.getColumnValues("Age").get(3));
        assertEquals("France", df.getColumnValues("Country").get(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyColumnLabels() {
        String[] columnsLabels = {};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        DataFrame df = new DataFrame(columnsLabels, rowsValues);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRowsValues() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {};
        DataFrame df = new DataFrame(columnsLabels, rowsValues);
    }

    @Test
    public void testConstructor2ParametersWithValidData() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        DataFrame df = new DataFrame(columnsLabels, rowsValues);

        assertEquals("Name", df.getColumnLabels().get(0));
        assertEquals("Age", df.getColumnLabels().get(1));
        assertEquals("Country", df.getColumnLabels().get(2));

        assertEquals("Ali", df.getColumnValues("Name").get(0));
        assertEquals(21, df.getColumnValues("Age").get(0));
        assertEquals("Lebanon", df.getColumnValues("Country").get(0));

        assertEquals("Serge", df.getColumnValues("Name").get(1));
        assertEquals(24, df.getColumnValues("Age").get(1));
        assertEquals("Armenia", df.getColumnValues("Country").get(1));

        assertEquals("Jorane", df.getColumnValues("Name").get(2));
        assertEquals(23, df.getColumnValues("Age").get(2));
        assertEquals("France", df.getColumnValues("Country").get(2));

        assertEquals("Noemie", df.getColumnValues("Name").get(3));
        assertEquals(23, df.getColumnValues("Age").get(3));
        assertEquals("France", df.getColumnValues("Country").get(3));
    }

    @Test
    public void testCSVConstructorWithValidFile() {
        String pathtoFile = "src/test/sample/test.csv";
        String delimiter = ",";
        DataFrame df = new DataFrame(pathtoFile, delimiter);

        assertEquals("Name", df.getColumnLabels().get(0));
        assertEquals("Age", df.getColumnLabels().get(1));
        assertEquals("Country", df.getColumnLabels().get(2));

        assertEquals("Ali", df.getColumnValues("Name").get(0));
        assertEquals(21, df.getColumnValues("Age").get(0));
        assertEquals("Lebanon", df.getColumnValues("Country").get(0));

        assertEquals("Serge", df.getColumnValues("Name").get(1));
        assertEquals(24, df.getColumnValues("Age").get(1));
        assertEquals("Armenia", df.getColumnValues("Country").get(1));

        assertEquals("Jorane", df.getColumnValues("Name").get(2));
        assertEquals(23, df.getColumnValues("Age").get(2));
        assertEquals("France", df.getColumnValues("Country").get(2));

        assertEquals("Noemie", df.getColumnValues("Name").get(3));
        assertEquals(23, df.getColumnValues("Age").get(3));
        assertEquals("France", df.getColumnValues("Country").get(3));
    }

}
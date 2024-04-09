import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DataFrameTest {

    private DataFrame df;

    @Before
    public void setUp() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        df = new DataFrame(columnsLabels, rowsValues);
    }

    @Test
    public void testGetRowValues() {
        ArrayList<Object> expectedRowValues = new ArrayList<>();
        expectedRowValues.add("Serge");
        expectedRowValues.add(24);
        expectedRowValues.add("Armenia");

        assertEquals(expectedRowValues, df.getRowValues(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetRowValuesWithInvalidIndex() {
        df.getRowValues(10);
    }

    @Test
    public void testGetColumnLabels() {
        ArrayList<String> expectedColumnLabels = new ArrayList<>();
        expectedColumnLabels.add("Name");
        expectedColumnLabels.add("Age");
        expectedColumnLabels.add("Country");

        assertEquals(expectedColumnLabels, df.getColumnLabels());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyData() {
        Object[][] data = {};
        DataFrame df1 = new DataFrame(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRow() {
        Object[][] data = {{}};
        DataFrame df1 = new DataFrame(data);
    }

    @Test
    public void testConstructorWithValidData() {
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
        DataFrame df1 = new DataFrame(columnsLabels, rowsValues);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRowsValues() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {};
        DataFrame df = new DataFrame(columnsLabels, rowsValues);
    }

    @Test
    public void testConstructor2ParametersWithValidData() {
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
    public void testGetLabel(){
        assertEquals("Name",df.getLabel(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLabelWithInvalidIndex(){
        int[] i={0};
        df.iloc(i);

        String er = df.getLabel(12);
    }


    public void testEquals() {
        String[] columnsLabels2 = {"Name", "Age", "Country"};
        Object[][] rowsValues2 = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        DataFrame df2 = new DataFrame(columnsLabels2, rowsValues2);

        String[] columnsLabels3 = {"Name", "Age", "Country"};
        Object[][] rowsValues3 = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "Germany"} // Changing one value to make it different
        };
        DataFrame df3 = new DataFrame(columnsLabels3, rowsValues3);

        // Testing equality
        assertTrue(df.equals(df2));
        assertTrue(df2.equals(df));

        // Testing inequality
        assertFalse(df.equals(df3));
        assertFalse(df2.equals(df3));
        assertFalse(df3.equals(df));
        assertFalse(df3.equals(df2));
    }

    @Test
    public void testIlocWithIndexValide(){
        String[] columnsLabels2 = {"Name", "Age", "Country"};
        Object[][] rowsValues2 = {
                {"Ali", 21, "Lebanon"},
        };
        DataFrame df2 = new DataFrame(columnsLabels2, rowsValues2);
        int[] i={0};
        DataFrame df3 = df.iloc(i);
        assertTrue(df3.equals(df2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIlocWithIndexInvalide(){
        String[] columnsLabels2 = {"Name", "Age", "Country"};
        Object[][] rowsValues2 = {
                {"Ali", 21, "Lebanon"},
        };
        DataFrame df2 = new DataFrame(columnsLabels2, rowsValues2);
        int[] i={4};
        DataFrame df3 = df.iloc(i);
    }

    @Test
    public void testLocWithIndexValide(){
        String[] columnsLabels2 = {"Name","Age"};
        Object[][] rowsValues2 = {
                {"Ali",21},
                {"Serge",24},
                {"Jorane",23},
                {"Noemie",23}
        };
        DataFrame df2 = new DataFrame(columnsLabels2, rowsValues2);
        String[] i={"Name","Age"};
        assertTrue(df2.equals(df.loc(i)));
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testLocWithIndexInvalide(){
        String[] columnsLabels2 = {"Sexe"};
        df.loc(columnsLabels2);
    }
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testLocWithIndexInvalide(){
        String[] columnsLabels2 = {};
        df.loc(columnsLabels);
    }

}
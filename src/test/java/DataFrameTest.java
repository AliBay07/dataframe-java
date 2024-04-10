import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataFrameTest {

    private DataFrame dfOneParam;
    private DataFrame dfTwoParams;
    private DataFrame dfCSVOneParam;
    private DataFrame dfCSVTwoParams;

    @Before
    public void setUp() {
        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        dfTwoParams = new DataFrame(columnsLabels, rowsValues);
        Object[][] data = {
                {"Name", "Age", "Country"},
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };
        dfOneParam = new DataFrame(data);

        String pathToFile = "src/test/sample/test.csv";
        String delimiter = ",";
        dfCSVOneParam = new DataFrame(pathToFile);
        dfCSVTwoParams = new DataFrame(pathToFile, delimiter);
    }

    @Test
    public void testGetRowValues() {
        ArrayList<Object> expectedRowValues = new ArrayList<>();
        expectedRowValues.add("Serge");
        expectedRowValues.add(24);
        expectedRowValues.add("Armenia");

        assertEquals(expectedRowValues, dfTwoParams.getRowValues(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetRowValuesWithInvalidIndex() {
        dfTwoParams.getRowValues(10);
    }

    @Test
    public void testGetColumnLabels() {
        ArrayList<String> expectedColumnLabels = new ArrayList<>();
        expectedColumnLabels.add("Name");
        expectedColumnLabels.add("Age");
        expectedColumnLabels.add("Country");

        assertEquals(expectedColumnLabels, dfTwoParams.getColumnLabels());
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
        assertEquals("Name", dfOneParam.getColumnLabels().get(0));
        assertEquals("Age", dfOneParam.getColumnLabels().get(1));
        assertEquals("Country", dfOneParam.getColumnLabels().get(2));

        assertEquals("Ali", dfOneParam.getColumnValues("Name").get(0));
        assertEquals(21, dfOneParam.getColumnValues("Age").get(0));
        assertEquals("Lebanon", dfOneParam.getColumnValues("Country").get(0));

        assertEquals("Serge", dfOneParam.getColumnValues("Name").get(1));
        assertEquals(24, dfOneParam.getColumnValues("Age").get(1));
        assertEquals("Armenia", dfOneParam.getColumnValues("Country").get(1));

        assertEquals("Jorane", dfOneParam.getColumnValues("Name").get(2));
        assertEquals(23, dfOneParam.getColumnValues("Age").get(2));
        assertEquals("France", dfOneParam.getColumnValues("Country").get(2));

        assertEquals("Noemie", dfOneParam.getColumnValues("Name").get(3));
        assertEquals(23, dfOneParam.getColumnValues("Age").get(3));
        assertEquals("France", dfOneParam.getColumnValues("Country").get(3));
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
        assertEquals("Name", dfTwoParams.getColumnLabels().get(0));
        assertEquals("Age", dfTwoParams.getColumnLabels().get(1));
        assertEquals("Country", dfTwoParams.getColumnLabels().get(2));

        assertEquals("Ali", dfTwoParams.getColumnValues("Name").get(0));
        assertEquals(21, dfTwoParams.getColumnValues("Age").get(0));
        assertEquals("Lebanon", dfTwoParams.getColumnValues("Country").get(0));

        assertEquals("Serge", dfTwoParams.getColumnValues("Name").get(1));
        assertEquals(24, dfTwoParams.getColumnValues("Age").get(1));
        assertEquals("Armenia", dfTwoParams.getColumnValues("Country").get(1));

        assertEquals("Jorane", dfTwoParams.getColumnValues("Name").get(2));
        assertEquals(23, dfTwoParams.getColumnValues("Age").get(2));
        assertEquals("France", dfTwoParams.getColumnValues("Country").get(2));

        assertEquals("Noemie", dfTwoParams.getColumnValues("Name").get(3));
        assertEquals(23, dfTwoParams.getColumnValues("Age").get(3));
        assertEquals("France", dfTwoParams.getColumnValues("Country").get(3));
    }

    @Test
    public void testCSVConstructorWithDelimiterWithValidFile() {
        String pathtoFile = "src/test/sample/test.csv";
        String delimiter = ",";
        DataFrame df = new DataFrame(pathtoFile, delimiter);

        assertEquals("Name", dfCSVTwoParams.getColumnLabels().get(0));
        assertEquals("Age", dfCSVTwoParams.getColumnLabels().get(1));
        assertEquals("Country", dfCSVTwoParams.getColumnLabels().get(2));

        assertEquals("Ali", dfCSVTwoParams.getColumnValues("Name").get(0));
        assertEquals(21, dfCSVTwoParams.getColumnValues("Age").get(0));
        assertEquals("Lebanon", dfCSVTwoParams.getColumnValues("Country").get(0));

        assertEquals("Serge", dfCSVTwoParams.getColumnValues("Name").get(1));
        assertEquals(24, dfCSVTwoParams.getColumnValues("Age").get(1));
        assertEquals("Armenia", dfCSVTwoParams.getColumnValues("Country").get(1));

        assertEquals("Jorane", dfCSVTwoParams.getColumnValues("Name").get(2));
        assertEquals(23, dfCSVTwoParams.getColumnValues("Age").get(2));
        assertEquals("France", dfCSVTwoParams.getColumnValues("Country").get(2));

        assertEquals("Noemie", dfCSVTwoParams.getColumnValues("Name").get(3));
        assertEquals(23, dfCSVTwoParams.getColumnValues("Age").get(3));
        assertEquals("France", dfCSVTwoParams.getColumnValues("Country").get(3));
    }

    @Test
    public void testCSVConstructorWithoutDelimiterWithValidFile() {
        String pathtoFile = "src/test/sample/test.csv";
        DataFrame df = new DataFrame(pathtoFile);

        assertEquals("Name", dfCSVOneParam.getColumnLabels().get(0));
        assertEquals("Age", dfCSVOneParam.getColumnLabels().get(1));
        assertEquals("Country", dfCSVOneParam.getColumnLabels().get(2));

        assertEquals("Ali", dfCSVOneParam.getColumnValues("Name").get(0));
        assertEquals(21, dfCSVOneParam.getColumnValues("Age").get(0));
        assertEquals("Lebanon", dfCSVOneParam.getColumnValues("Country").get(0));

        assertEquals("Serge", dfCSVOneParam.getColumnValues("Name").get(1));
        assertEquals(24, dfCSVOneParam.getColumnValues("Age").get(1));
        assertEquals("Armenia", dfCSVOneParam.getColumnValues("Country").get(1));

        assertEquals("Jorane", dfCSVOneParam.getColumnValues("Name").get(2));
        assertEquals(23, dfCSVOneParam.getColumnValues("Age").get(2));
        assertEquals("France", dfCSVOneParam.getColumnValues("Country").get(2));

        assertEquals("Noemie", dfCSVOneParam.getColumnValues("Name").get(3));
        assertEquals(23, dfCSVOneParam.getColumnValues("Age").get(3));
        assertEquals("France", dfCSVOneParam.getColumnValues("Country").get(3));
    }

    @Test(expected = RuntimeException.class)
    public void testCSVConstructorWithDelimiterWithInvalidFile() {
        String pathtoFile = "src/test/sample/unknown.csv";
        String delimiter = ",";
        DataFrame df = new DataFrame(pathtoFile, delimiter);
    }

    @Test(expected = RuntimeException.class)
    public void testCSVConstructorWithoutDelimiterWithInvalidFile() {
        String pathtoFile = "src/test/sample/unknown.csv";
        DataFrame df = new DataFrame(pathtoFile);
    }

    @Test
    public void testGetLabel(){
        assertEquals("Name",dfOneParam.getLabel(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLabelWithInvalidIndex(){
        int[] i={0};
        dfOneParam.iloc(i);

        String er = dfOneParam.getLabel(12);
    }

    @Test
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
        assertEquals(dfOneParam, df2);
        assertEquals(df2, dfOneParam);

        // Testing inequality
        assertNotEquals(dfOneParam, df3);
        assertNotEquals(df2, df3);
        assertNotEquals(df3, dfOneParam);
        assertNotEquals(df3, df2);
    }

    @Test
    public void testIlocWithIndexValide(){
        String[] columnsLabels2 = {"Name", "Age", "Country"};
        Object[][] rowsValues2 = {
                {"Ali", 21, "Lebanon"},
        };
        DataFrame df2 = new DataFrame(columnsLabels2, rowsValues2);
        int[] i={0};
        DataFrame df3 = dfOneParam.iloc(i);
        assertEquals(df3, df2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIlocWithIndexInvalide(){
        String[] columnsLabels2 = {"Name", "Age", "Country"};
        Object[][] rowsValues2 = {
                {"Ali", 21, "Lebanon"},
        };
        DataFrame df2 = new DataFrame(columnsLabels2, rowsValues2);
        int[] i={4};
        DataFrame df3 = dfOneParam.iloc(i);
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
        assertEquals(df2, dfOneParam.loc(i));
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testLocWithIndexInvalide(){
        String[] columnsLabels2 = {"Sexe"};
        dfOneParam.loc(columnsLabels2);
    }
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testLocWithIndexInvalide2(){
        String[] columnsLabels2 = {};
        dfOneParam.loc(columnsLabels2);
    }

    @Test
    public void testMean(){
        Object[] meanExcepted = {null, 22.75, null};
        assertArrayEquals(meanExcepted, dfOneParam.moyenne());
    }
    @Test
    public void testMeanWithNullValue(){
        String[] lab = {"Name","Age"};
        Object[][] data = {
                {"Ali", 21},
                {"Noa", 22},
                {"Val", null},
        };
        DataFrame df2 = new DataFrame(lab, data);
        Object[] meanExcepted = {null, 21.5};
        assertArrayEquals(meanExcepted, df2.moyenne());
    }

    @Test
    public void testCount(){
        Object[] countExcepted = {4,4,4};
        assertArrayEquals(countExcepted, dfOneParam.count());
    }

    @Test
    public void testCountWithNullValue(){
        String[] lab = {"Name","Sexe"};
        Object[][] data = {
                {"Ali", "Homme"},
                {"Noa", "Homme"},
                {"Val", null},
        };
        DataFrame df2 = new DataFrame(lab, data);
        Object[] countExcepted = {3, 2};
        assertArrayEquals(countExcepted, df2.count());
    }

    @Test
    public void testMinEtMax(){
        Object[][] minMaxExcepted = {
                {null, 21.0, null},
                {null, 24.0, null}
        };
        assertArrayEquals(minMaxExcepted, dfOneParam.minEtMax());
    }

    @Test
    public void testStudentDeviation(){
        String[] lab = {"Age", "Nombre Enfant"};
        Object[][] data = {
                {20,0},
                {26,3},
                {30,1}
        };
        DataFrame df = new DataFrame(lab,data);
        Object[] sdExcepted = {4.109609335312651, 1.247219128924647};
        assertArrayEquals(sdExcepted,df.sd(df.moyenne()));
    }

    @Test
    public void testFilterReturningOneValue() {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Name");
        columnNames.add("Age");

        ArrayList<Object> filterValues = new ArrayList<>();
        filterValues.add("Ali");
        filterValues.add(21);

        DataFrame filteredDataFrame = dfTwoParams.filter(columnNames, filterValues);

        assertEquals(filteredDataFrame.getRows().size(), 1);
        assertEquals("Ali", filteredDataFrame.getRowValues(0).get(0));
        assertEquals(21, filteredDataFrame.getRowValues(0).get(1));
        assertEquals("Lebanon", filteredDataFrame.getRowValues(0).get(2));
    }

    @Test
    public void testFilterReturningMultipleValues() {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Age");

        ArrayList<Object> filterValues = new ArrayList<>();
        filterValues.add(23);

        DataFrame filteredDataFrame = dfTwoParams.filter(columnNames, filterValues);

        assertEquals(filteredDataFrame.getRows().size(), 2);
        assertEquals("Jorane", filteredDataFrame.getRowValues(0).get(0));
        assertEquals(23, filteredDataFrame.getRowValues(0).get(1));
        assertEquals("France", filteredDataFrame.getRowValues(0).get(2));
        assertEquals("Noemie", filteredDataFrame.getRowValues(1).get(0));
        assertEquals(23, filteredDataFrame.getRowValues(1).get(1));
        assertEquals("France", filteredDataFrame.getRowValues(1).get(2));
    }

    @Test
    public void testFilterReturningNoValue() {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Age");

        ArrayList<Object> filterValues = new ArrayList<>();
        filterValues.add(60);

        DataFrame filteredDataFrame = dfTwoParams.filter(columnNames, filterValues);

        assertNull(filteredDataFrame.getRowValues(0).get(0));
        assertNull(filteredDataFrame.getRowValues(0).get(1));
        assertNull(filteredDataFrame.getRowValues(0).get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilterWithInvalidColumnName() {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("unknown-column");

        ArrayList<Object> filterValues = new ArrayList<>();
        filterValues.add(9999);

        DataFrame filteredDataFrame = dfTwoParams.filter(columnNames, filterValues);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFilterWithInvalidColumnAndRows() {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("unknown-column");

        DataFrame filteredDataFrame = dfTwoParams.filter(columnNames, new ArrayList<>());
    }

}
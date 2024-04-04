import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Represents a DataFrame, a two-dimensional labeled data structure with columns of potentially different types.
 * Provides methods to manipulate and analyze tabular data.
 */
public class DataFrame {

    private final Hashtable<String, Object[][]> columns;
    private final ArrayList<Object[]> rows;
    private final ArrayList<String> labels;

    /**
     * Constructs a DataFrame from a 2D array of Objects.
     *
     * @param data The 2D array representing the DataFrame. The first row should contain column labels.
     * @throws IllegalArgumentException if the input data has fewer than one row or one column.
     */
    public DataFrame(Object[][] data) {
        if (data.length < 1 || data[0].length < 1) {
            throw new IllegalArgumentException("Incorrect input data format.");
        }

        columns = new Hashtable<>();
        rows = new ArrayList<>();
        labels = new ArrayList<>();

        String[] columnLabels = new String[data[0].length];
        for (int i = 0; i < data[0].length; i++) {
            columnLabels[i] = data[0][i].toString();
            labels.add(columnLabels[i]);
        }

        for (int i = 0; i < columnLabels.length; i++) {
            String columnLabel = columnLabels[i];
            Object[][] columnData = new Object[data.length - 1][1];
            for (int j = 1; j < data.length; j++) {
                columnData[j - 1][0] = data[j][i];
            }
            columns.put(columnLabel, columnData);
        }

        rows.addAll(Arrays.asList(data).subList(1, data.length));
    }

    /**
     * Constructs a DataFrame from column labels and corresponding row values.
     *
     * @param columns_labels The labels of the columns.
     * @param rows_values    The values of the rows, organized as a 2D array.
     * @throws IllegalArgumentException if the length of column labels is not equal to the number of columns in rows_values.
     */
    public DataFrame(String[] columns_labels, Object[][] rows_values) {

        if (rows_values.length == 0 || columns_labels.length == 0 ||
                columns_labels.length != rows_values[0].length) {
            throw new IllegalArgumentException("Incorrect input data format.");
        }

        columns = new Hashtable<>();
        rows = new ArrayList<>();
        labels = new ArrayList<>();

        rows.addAll(Arrays.asList(rows_values));

        for (int i = 0; i < columns_labels.length; i++) {
            String columnLabel = columns_labels[i];
            labels.add(columnLabel);
            Object[][] columnData = new Object[rows_values.length][1];
            for (int j = 0; j < rows_values.length; j++) {
                columnData[j][0] = rows_values[j][i];
            }
            columns.put(columnLabel, columnData);
        }
    }

    public DataFrame(String filename){
//        try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {

        }

    /**
     * Returns a list of column labels in the DataFrame.
     *
     * @return An ArrayList containing the column labels.
     */
    public ArrayList<String> getColumnLabels() {
        return this.labels;
    }

    /**
     * Returns a list of values of the column with the given label.
     *
     * @param columnName The label of the column.
     * @return A list containing the values of the specified column.
     * @throws IllegalArgumentException if the column with the given label does not exist.
     */
    public ArrayList<Object> getColumnValues(String columnName) {
        if (!columns.containsKey(columnName)) {
            throw new IllegalArgumentException("Column with label " + columnName + " does not exist.");
        }

        Object[][] columnData = columns.get(columnName);
        ArrayList<Object> columnValues = new ArrayList<>();

        for (Object[] rowData : columnData) {
            columnValues.add(rowData[0]);
        }

        return columnValues;
    }

    /**
     * Returns an ArrayList containing the values of the row at the specified index.
     *
     * @param index The index of the row.
     * @return An ArrayList containing the values of the row.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public ArrayList<Object> getRowValues(int index) {
        if (index < 0 || index >= rows.size()) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }

        Object[] rowData = rows.get(index);
        ArrayList<Object> rowValues = new ArrayList<>();

        Collections.addAll(rowValues, rowData);

        return rowValues;
    }

    /**
     * Prints the DataFrame to the console.
     * The DataFrame is printed in a tabular format with column labels and corresponding row values.
     */
    public void printDataFrame() {

        System.out.print(" |");
        for (String column : columns.keySet()) {
            System.out.print(" " + column + " |");
        }
        System.out.println();

        for (int i = 0; i <= columns.size(); i++) {
            System.out.print("------");
        }
        System.out.println();

        for (Object[] row : rows) {
            System.out.print(" |");
            for (Object value : row) {
                System.out.print(" " + value + " |");
            }
            System.out.println();
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class DataFrame {

    private final Hashtable<String, Object[]> columns;
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
            Object[] columnData = new Object[data.length - 1];
            for (int j = 1; j < data.length; j++) {
                columnData[j - 1] = data[j][i];
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
            Object[] columnData = new Object[rows_values.length];
            for (int j = 0; j < rows_values.length; j++) {
                columnData[j] = rows_values[j][i];
            }
            columns.put(columnLabel, columnData);
        }
    }

    /**
     * Constructs a DataFrame from a csv file.
     *
     * @param filepath The path to the csv file.
     */
    public DataFrame(String filepath) {
        this(new CSVReader(filepath).allLines());
    }

    /**
     * Constructs a DataFrame from a csv file and a delimiter.
     *
     * @param filepath The path to the csv file.
     * @param delimiter The Delimiter to split the csv columns.
     */
    public DataFrame(String filepath, String delimiter) {
        this(new CSVReader(filepath, delimiter).allLines());
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

        Object[] columnData = columns.get(columnName);
        ArrayList<Object> columnValues = new ArrayList<>();

        Collections.addAll(columnValues, columnData);

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
     * Returns a label of the row at the specified index.
     *
     * @param index The index of the row.
     * @return A String
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public String getLabel(int index){
        if (index < 0 || index >= rows.size()) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        return labels.get(index);
    }

    /**
     * Returns all the labels in the dataframe.
     *
     * @return A list of strings
     */
    public Object[] getLabels(){
        String[] lab = new String[labels.size()];
        int i = 0;
        for(String l : labels){
            lab[i] = l;
            i++;
        }
        return lab;
    }

    /**
     * Prints the DataFrame to the console.
     * The DataFrame is printed in a tabular format with column labels and corresponding row values.
     */
    public void printDataFrame() {

        System.out.print(" |");
        for (String column : labels) {
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

    /**
     * Prints the first few rows of a tabular data structure, including column labels.
     * The number of rows to display is specified by the parameter numberOfRows.
     *
     * @param numberOfRows The number of rows to display from the beginning of the data structure.
     */
    public void head(int numberOfRows) {
        System.out.print(" |");
        for (String column : labels) {
            System.out.print(" " + column + " |");
        }
        System.out.println();

        for (int i = 0; i <= columns.size(); i++) {
            System.out.print("------");
        }
        System.out.println();

        numberOfRows = Math.min(numberOfRows, rows.size());

        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(" |");
            for (Object value : rows.get(i)) {
                System.out.print(" " + value + " |");
            }
            System.out.println();
        }
    }

    /**
     * Prints the last few rows of a tabular data structure, including column labels.
     * The number of rows to display is specified by the parameter numberOfRows.
     *
     * @param numberOfRows The number of rows to display from the end of the data structure.
     */
    public void tail(int numberOfRows) {
        System.out.print(" |");
        for (String column : labels) {
            System.out.print(" " + column + " |");
        }
        System.out.println();

        for (int i = 0; i <= columns.size(); i++) {
            System.out.print("------");
        }
        System.out.println();

        int startIndex = Math.max(rows.size() - numberOfRows, 0);

        for (int i = startIndex; i < rows.size(); i++) {
            System.out.print(" |");
            for (Object value : rows.get(i)) {
                System.out.print(" " + value + " |");
            }
            System.out.println();
        }
    }

    /**
     * Returns a new DataFrame containing rows specified by the given array of indices.
     *
     * @param indices An array of indices indicating which rows to select.
     * @return A new DataFrame containing selected rows.
     * @throws IllegalArgumentException if indices is empty.
     */
    public DataFrame iloc(int[] indices){
        if(indices.length == 0){
            throw new IllegalArgumentException();
        }
        Object[][] lines = new Object[indices.length][] ;
        int i = 0;
        for(int idx : indices){
            lines[i] = rows.get(idx);
            i++;
        }
        return new DataFrame((String[]) getLabels(), lines);
    }


    /**
     * Returns a new DataFrame containing columns specified by the given array of labels.
     *
     * @param lab An array of labels indicating which columns to select.
     * @return A new DataFrame containing selected columns.
     * @throws IllegalArgumentException if lab is empty, or label is not present in DataFrame.
     */
    public DataFrame loc(String[] lab){
        if(lab.length == 0){
            throw new IllegalArgumentException("Input data is empty");
        }
        Object[][] res = new Object[rows.size()][lab.length];
        int i = 0;
        int j = 0;
        for(String l : lab){
            if(!labels.contains(l)){
                throw new IllegalArgumentException("Invalid input data : column "+l+" is not in DataFrame");
            }
            Object[] col = columns.get(l);
            j=0;
            for(Object o : col){
                res[j][i]= o;
                j++;
            }
            i++;
        }
        DataFrame df = new DataFrame(lab, res);
        return df;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two DataFrames are considered equal if they have the same number of rows and columns,
     * and if the elements in corresponding positions in their rows are equal.
     *
     * @param obj The reference object with which to compare.
     * @return {@code true} if this DataFrame is equal to the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DataFrame other = (DataFrame) obj;
        if (rows.size() != other.rows.size() || columns.size() != other.columns.size()) {
            return false;
        }
        for (int i = 0; i < rows.size(); i++) {
            Object[] thisRow = rows.get(i);
            Object[] otherRow = other.rows.get(i);
            if (!Arrays.deepEquals(thisRow, otherRow)) {
                return false;
            }
        }
        return true;
    }


}
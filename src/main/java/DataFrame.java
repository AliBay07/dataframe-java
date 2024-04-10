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
        return new DataFrame(lab, res);
    }

    /**
     * Filters the DataFrame based on given column names and filter values.
     *
     * @param columnNames The list of column names to filter on.
     * @param filterValues The list of filter values corresponding to each column.
     * @return A new DataFrame containing only the rows that match the filters.
     * @throws IllegalArgumentException if the number of column names and filter values don't match.
     */
    public DataFrame filter(ArrayList<String> columnNames, ArrayList<Object> filterValues) {
        if (columnNames.size() != filterValues.size()) {
            throw new IllegalArgumentException("Number of column names and filter values must be the same.");
        }

        ArrayList<Object[]> filteredRows = new ArrayList<>();

        for (Object[] row : rows) {
            boolean match = true;

            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);
                Object filterValue = filterValues.get(i);
                if (!columns.containsKey(columnName)) {
                    throw new IllegalArgumentException("Column " + columnName + " does not exist.");
                }

                Object cellValue = row[labels.indexOf(columnName)];

                if (!filterValue.equals(cellValue)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                filteredRows.add(row);
            }
        }

        if (filteredRows.isEmpty()) {
            Object[][] nullRow = new Object[1][labels.size()];
            Arrays.fill(nullRow[0], null);
            return new DataFrame((String[]) getLabels(), nullRow);
        } else {
            Object[][] filteredData = new Object[filteredRows.size()][];
            filteredData = filteredRows.toArray(filteredData);
            return new DataFrame((String[]) getLabels(), filteredData);
        }
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

     /**
     * Calculates the mean (average) of numerical values in each column of the DataFrame.
     * If a column contains non-numerical values or is empty, the mean value for that column is considered null.
     * @return An array containing the mean values for each column in the DataFrame.
     **/
    public Object[] moyenne(){
        int i = 0;
        Object[] tab = new Object[labels.size()];
        for (String s : this.labels) {
            Object[] row = this.columns.get(s);
            if (row.length > 0 && row[0] instanceof Number) {
                double sum = 0.0;
                int compteur = 0;
                for(Object n : row) {
                    if(n != null) {
                        sum = sum + ((Number) n).doubleValue();
                    }
                    else
                        compteur ++;
                }
                tab[i] = (double) sum / (row.length - compteur);
            } else {
                tab[i] = null;
            }
            i++;
        }
        return tab;
    }

    /**
     * Counts the non-null elements in each column of the DataFrame.
     * @return An array containing the count of non-null elements for each column in the DataFrame.
     **/
    public Integer[] count(){
        Integer[] tab = new Integer[labels.size()];
        int i = 0 ;
        for(String l : labels){
            int compteur = columns.get(l).length;
            for(Object c : columns.get(l)){
                if(c == null){
                    compteur--;
                }
            }
            tab[i]=compteur;
            i++;
        }
        return tab;
    }

    /**
     * Finds the minimum and maximum values in each numerical column of the DataFrame.
     * If a column contains non-numerical values, the minimum and maximum values for that column are considered null.$
     * @return A 2D array where the first row contains the minimum values and the second row contains the maximum values
     **/
    public Object[][] minEtMax(){
        Object[] min = new Object[labels.size()];
        Object[] max = new Object[labels.size()];
        int i = 0 ;
        for(String l : labels){
            if(columns.get(l)[0] instanceof Number) {
                double maxVal = Integer.MIN_VALUE;
                double minVal = Integer.MAX_VALUE;
                for(int j = 0; j < columns.get(l).length; j++){
                    if (((Number) columns.get(l)[j]).doubleValue() > maxVal)
                        maxVal = ((Number) columns.get(l)[j]).doubleValue();
                    if (((Number) columns.get(l)[j]).doubleValue() < minVal)
                        minVal = ((Number) columns.get(l)[j]).doubleValue();

                }
                min[i]=minVal;
                max[i]=maxVal;
            }
            else{
                min[i]=null;
                max[i]=null;
            }
            i++;
        }
        return new Object[][]{
                min,
                max
        };
    }

    /**
     * Calculates the standard deviation of numerical values in each column of the DataFrame based on the provided means.
     * If a column contains non-numerical values, the standard deviation for that column is considered null.
     * @param mean An array containing the mean values for each column in the DataFrame.
     * @return An array containing the standard deviation values for each column in the DataFrame.
     */
    public Object[] sd(Object[] mean){
        Object[] sd = new Object[labels.size()];
        int i = 0;
        for(String l : labels){
            double res=0;
            if(columns.get(l)[0] instanceof Number) {
                for(int j = 0; j < columns.get(l).length; j++) {
                    double cal = (((Number) columns.get(l)[j]).doubleValue() - ((Number) mean[i]).doubleValue());
                    res = res + cal * cal;
                }
                sd[i] = (double) Math.sqrt((double) 1 / columns.get(l).length * res);
            }
            else{
                sd[i] = null;
            }
            i++;
        }
        return sd;
    }

    /**
     * Generates a summary of the DataFrame, including count, mean, minimum, maximum, and standard deviation for each column.
     **/
    public void describe(){
        Object[] moy = moyenne();
        Integer[] count = count();
        Object[][] minmax = minEtMax();
        Object[] sd = sd(moy);

        System.out.printf("%-12s", "");
        for (String label : labels) {
            System.out.printf("%-10s", label);
            System.out.printf("%-2s", "");
        }
        System.out.println();
        System.out.printf("%-10s", "Count");
        System.out.printf("| ");
        for(int i : count){
            System.out.printf("%-10s", i);
            System.out.printf("%-2s", "");
        }
        System.out.println();
        System.out.printf("%-10s", "Mean");
        System.out.printf("| ");
        for(Object m : moy){
            System.out.printf("%-10s", m);
            System.out.printf("%-2s", "");
        }
        System.out.println();
        System.out.printf("%-10s", "Min");
        System.out.printf("| ");
        for(Object min : minmax[0]){
            System.out.printf("%-10s", min);
            System.out.printf("%-2s", "");
        }
        System.out.println();
        System.out.printf("%-10s", "Max");
        System.out.printf("| ");
        for(Object max : minmax[1]){
            System.out.printf("%-10s", max);
            System.out.printf("%-2s", "");
        }
        System.out.println();
        System.out.printf("%-10s", "Ecart-Type");
        System.out.printf("| ");
        for(Object e : sd){
            System.out.printf("%-10s", e);
            System.out.printf("%-2s", "");
        }

    }

}
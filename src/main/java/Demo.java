import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        String[] columnsLabels = {"Name", "Age", "Country"};
        Object[][] rowsValues = {
                {"Ali", 21, "Lebanon"},
                {"Serge", 24, "Armenia"},
                {"Jorane", 23, "France"},
                {"Noemie", 23, "France"}
        };

        System.out.println("Creating dataframe with the following data : ");
        System.out.println("\nColumns: " + Arrays.toString(columnsLabels));
        System.out.println("\nRows: " + Arrays.deepToString(rowsValues));

        DataFrame df = new DataFrame(columnsLabels, rowsValues);

        System.out.println("\nPrinting the dataframe using the method .printDataFrame : ");
        df.printDataFrame();

        System.out.println("\nPrinting the first two rows in the dataframe using the method .head : ");
        df.head(2);

        System.out.println("\nPrinting the last two rows in the dataframe using the method .tail : ");
        df.tail(2);

        System.out.println("\n Displaying the summary of a dataframe using the method .describe: ");
        df.describe();

        System.out.println("\nAccessing specific columns using the method .loc:");
        String[] selectedColumns = {"Name", "Age", "Country"};
        for (String col : selectedColumns) {
            System.out.println("\nColumn '" + col + "':");
            df.loc(new String[]{col}).printDataFrame();
        }

        System.out.println("\nAccessing multiple columns (Name and Country) using the method .iloc:");
        System.out.println("\nColumns 'Name' and 'Country': ");
        df.loc(new String[]{"Name", "Country"}).printDataFrame();

        System.out.println("\nAccessing specific rows using the method .iloc:");
        int[] selectedRows = {0, 1, 2};
        for (int row : selectedRows) {
            System.out.println("\nRow at index " + row + ":");
            df.iloc(new int[]{row}).printDataFrame();
        }

        System.out.println("\nAccessing multiple rows (0, 1 and 3) using the method .iloc:");

        System.out.println("\nRow at indices 0, 1 and 3: ");
        df.iloc(new int[]{0, 1, 3}).printDataFrame();

        System.out.println("\nFiltering the dataframe based on 'Country' column with value 'France' using the method .filter:");
        df.filter(new ArrayList<>(List.of("Country")), new ArrayList<>(List.of("France"))).printDataFrame();

        System.out.println("\nGrouping the dataframe by 'Country' and calculating mean using the method .groupby: ");
        String[] labels = {"Country", "Age", "Salary"};
        Object[][] data = {
                {"France", 23, 1000},
                {"Italy", 23, 1200},
                {"Italy", 24, 1350},
                {"France", 23, 1300},
                {"Spain", 25, 2000},
                {"Spain", 23, 1370},
                {"France", 24, 1100},
                {"Brazil", 23, 950},
                {"France", 25, 1650},
                {"Brazil", 25, 1750},
        };
        DataFrame df_groupby = new DataFrame(labels, data);
        System.out.println("\nBefore the group by: \n");
        df_groupby.printDataFrame();
        System.out.println("\nAfter the group by: \n");
        df_groupby.groupby("Country", "mean").printDataFrame();

        System.out.println("\nGrouping the dataframe by 'Country' and calculating sum using the method .groupby: ");
        labels = new String[]{"Country", "Salary"};
        data = new Object[][]{
                {"France", 1000},
                {"Italy", 1200},
                {"Italy", 1350},
                {"France", 1300},
                {"Spain", 2000},
                {"Spain", 1370},
                {"France", 1100},
                {"Brazil", 950},
                {"France", 1650},
                {"Brazil", 1750},
        };
        df_groupby = new DataFrame(labels, data);
        System.out.println("\nBefore the group by: \n");
        df_groupby.printDataFrame();
        System.out.println("\nAfter the group by: \n");
        df_groupby.groupby("Country", "sum").printDataFrame();
    }

}

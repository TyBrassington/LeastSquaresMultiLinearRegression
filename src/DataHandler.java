import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataHandler {

    public static double[][] readCSV(String fileName, int startColumn, int endColumn, boolean includeOnes) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/" + fileName))) {
            String line = br.readLine();
            int numRows = 0;
            int numCols = line.split(",").length;
            while (br.readLine() != null) {
                numRows++;
            }

            if (startColumn < 1 || startColumn > numCols || endColumn < startColumn || endColumn > numCols) {
                throw new IllegalArgumentException("Invalid column range");
            }

            BufferedReader br1 = new BufferedReader(new FileReader("src/" + fileName));
            br1.readLine();

            double[][] matrix = new double[numRows][endColumn - startColumn + (includeOnes ? 2 : 1)];

            int row = 0;
            while ((line = br1.readLine()) != null) {
                String[] values = line.split(",");
                int outputCol = 0;
                if (includeOnes) {
                    matrix[row][outputCol++] = 1;
                }
                for (int col = startColumn - 1; col <= endColumn - 1; col++) {
                    matrix[row][outputCol++] = Double.parseDouble(values[col]);
                }
                row++;
            }

            return matrix;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return new double[0][];
        }
    }
}

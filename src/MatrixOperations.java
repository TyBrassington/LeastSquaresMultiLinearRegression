public class MatrixOperations {
    public static int[][] combineVectors(int[]... arrs) {
        int numRows = arrs[0].length;
        int numCols = arrs.length;
        int[][] matrix = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = arrs[j][i];
            }
        }
        return matrix;
    }

    public static double[][] transposeMatrix(double[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        double[][] transposedArray = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedArray[j][i] = arr[i][j];
            }
        }
        return transposedArray;
    }


    public static double[][] matrixMultiplication(double[][] arr1, double[][] arr2){
        int rows1 = arr1.length;
        int cols1 = arr1[0].length;
        int rows2 = arr2.length;
        int cols2 = arr2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException("Invalid Matrix Multiplications");
        }
        double[][] resultantMatrix = new double[rows1][cols2];

        for (int i = 0; i < rows1; i++){
            for (int j = 0; j < cols2; j++){
                for (int k = 0; k < cols1; k++){
                    resultantMatrix[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return resultantMatrix;
    }

    public static double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("Matrix is not square");
        }

        // Augmenting the matrix with the identity matrix of the same size
        double[][] augmentedMatrix = new double[n][n * 2];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][i + n] = 1;
        }

        // Applying Gauss-Jordan elimination
        for (int i = 0; i < n; i++) {
            double pivot = augmentedMatrix[i][i];
            if (pivot == 0) {
                throw new ArithmeticException("Matrix is singular, cannot find inverse");
            }

            // Dividing row i by pivot
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            // Subtracting multiples of row i from other rows to make the rest of column i zero
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmentedMatrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                    }
                }
            }
        }

        // Extracting the inverse matrix from the augmented matrix
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, inverse[i], 0, n);
        }

        return inverse;
    }

    public static double dotProduct(double[][] matrix1, double[][] matrix2){
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (rows1 != rows2 || cols1 != cols2) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for dot product");
        }

        double result = 0;

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                result += matrix1[i][j] * matrix2[i][j];
            }
        }

        return result;
    }


    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {

        double[][] X = DataHandler.readCSV("data.csv", 1, 5, true);
        double[][] Y = DataHandler.readCSV("data.csv",6,6,false);

        double[][] betaHat = calculateBetaHat(X, Y);

        System.out.println("Beta Hat: ");
        MatrixOperations.printMatrix(betaHat);

        double[][] inputMatrix = {
                {1.0}, //Do not touch this one

                {7.0}, // Hours studied
                {99.0}, //Previous Scores
                {1.0}, //Extracurricular Activities (Yes = 1, No = 0)
                {8.0}, //Sleep Hours
                {8.0} //Sample Question Papers Practiced
        };

        double prediction = predictYValue(betaHat, inputMatrix);
        System.out.println("\nLeast Squares Approximation: " + prediction);
    }

    private static double[][] calculateBetaHat(double[][] X, double[][] Y) {
        double[][] Xt = MatrixOperations.transposeMatrix(X);
        double[][] XtX = MatrixOperations.matrixMultiplication(Xt, X);
        double[][] AtY = MatrixOperations.matrixMultiplication(Xt, Y);
        double[][] inverseXtX = MatrixOperations.inverseMatrix(XtX);

        if (inverseXtX == null) {
            return null;
        }

        return MatrixOperations.matrixMultiplication(inverseXtX, AtY);
    }

    private static double predictYValue(double[][] betaHat, double[][] inputMatrix) {
        return MatrixOperations.dotProduct(betaHat, inputMatrix);
    }
}

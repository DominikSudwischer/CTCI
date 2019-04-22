package util;

public class MatrixUtil {
	
	public static void transpose(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < i; j++) {
				swap(matrix, i, j);
			}
		}
	}

	public static void swap(int[][] matrix, int i, int j) {
		int tmp = matrix[i][j];
		matrix[i][j] = matrix[j][i];
		matrix[j][i] = tmp;
	}

	public static void rotateColumn180(int[][] matrix, int j) {
		for (int i = 0; i < matrix.length / 2; i++) {
			int tmp = matrix[i][j];
			matrix[i][j] = matrix[matrix.length - 1 - i][j];
			matrix[matrix.length - 1 - i][j] = tmp;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j]);
				System.out.print(" ");
				if (j == matrix.length - 1) {
					System.out.print("\n");
				}
			}
		}
	}
}

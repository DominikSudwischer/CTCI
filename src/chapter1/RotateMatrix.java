package chapter1;

import util.MatrixUtil;

/**
 * Given an NxN matrix that represents an image, find an algorithm that rotates the
 * matrix by 90 degrees. In this case, we rotate to the left. This operation should
 * be performed in place.
 * 
 * @author Dominik Sudwischer
 *
 */
public class RotateMatrix {

	/*
	 * Idea: First transpose the matrix and then turn each column upside down. Time
	 * complexity: O(n^2) where n is the dimension of the square matrix.
	 */
	public static void rotateMatrix(int[][] matrix) {
		util.MatrixUtil.transpose(matrix);
		for (int j = 0; j < matrix.length; j++) {
			util.MatrixUtil.rotateColumn180(matrix, j);
		}
	}

	public static void main(String[] args) {
		int n = 10;
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n * n; i++) {
			matrix[i / n][i % n] = i + 1;
		}
		System.out.println("Input");
		util.MatrixUtil.printMatrix(matrix);
		rotateMatrix(matrix);
		System.out.println("Output");
		util.MatrixUtil.printMatrix(matrix);
	}
}

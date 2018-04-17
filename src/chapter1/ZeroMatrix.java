package chapter1;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an MxN matrix of integers, write an algorithm that sets any row and column
 * with at least one 0 entry to 0.
 * 
 * @author Dominik Sudwischer
 *
 */
public class ZeroMatrix {

	/*
	 * Idea: Row by row, store the columns that have to be set to zero by looking at
	 * each element. After scanning the whole row, if at least one cell with 0 in it
	 * has been found,, set the whole row to 0. After scanning each row, set all
	 * stored columns to 0. Time complexity: O(m*n) where m and n are the dimensions
	 * of the matrix.
	 */
	public static void setZero(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		Set<Integer> columnsToBetSetToZero = new HashSet<Integer>();
		for (int i = 0; i < m; i++) {
			if (rowContainsZero(matrix, i, columnsToBetSetToZero)) {
				setRowToZero(matrix, i);
			}
		}
		for (int j : columnsToBetSetToZero) {
			setColumnToZero(matrix, j);
		}
	}

	private static void setRowToZero(int[][] matrix, int i) {
		int n = matrix[i].length;
		for (int j = 0; j < n; j++) {
			matrix[i][j] = 0;
		}
	}

	private static void setColumnToZero(int[][] matrix, int j) {
		int m = matrix.length;
		for (int i = 0; i < m; i++) {
			matrix[i][j] = 0;
		}
	}

	private static boolean rowContainsZero(int[][] matrix, int i,
	        Set<Integer> columnsToBeSetToZero) {
		int n = matrix[i].length;
		boolean containsZero = false;
		for (int j = 0; j < n; j++) {
			if (matrix[i][j] == 0) {
				columnsToBeSetToZero.add(j);
				containsZero = true;
			}
		}
		return containsZero;
	}

	/*
	 * A slightly different approach can be used to set the space needed from O(n) to
	 * O(1). To do so, we use the first row with a 0 entry to store information about
	 * all columns we have to erase. When we scan a row after that specific row and
	 * find a 0 cell, we swap that 0 into the specific row and then erase the row
	 * afterwards.
	 */
	public static void setZeroConstantSpace(int[][] matrix) {
		int firstRowWithZeroCell = -1;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] == 0) {
					firstRowWithZeroCell = i;
					break;
				}
			}
			if(firstRowWithZeroCell >= 0) {
				break;
			}
		}
		for(int i = firstRowWithZeroCell + 1; i < matrix.length; i++) {
			if(swapZeroes(matrix, i, firstRowWithZeroCell)) {
				setRowToZero(matrix, i);
			}
		}
		for(int j = 0; j < matrix[firstRowWithZeroCell].length; j++) {
			if(matrix[firstRowWithZeroCell][j] == 0) {
				setColumnToZero(matrix, j);
			}
		}
		setRowToZero(matrix, firstRowWithZeroCell);
	}
	
	private static boolean swapZeroes(int[][] matrix, int currentRow, int firstRowWithZeroCell) {
		boolean hasSwapped = false;
		for(int j = 0; j < matrix[currentRow].length; j++) {
			if(matrix[currentRow][j] == 0) {
				hasSwapped = true;
				swapRowEntries(matrix, currentRow, firstRowWithZeroCell, j);
			}
		}
		return hasSwapped;
	}
	
	private static void swapRowEntries(int[][] matrix, int row1, int row2, int column) {
		int tmp = matrix[row1][column];
		matrix[row1][column] = matrix[row2][column];
		matrix[row2][column] = tmp;
	}
	
	public static void main(String[] args) {
		int n = 10;
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n * n; i++) {
			matrix[i / n][i % n] = i + 1;
		}
		matrix[1][2] = 0;
		matrix[8][0] = 0;
		System.out.println("Input:");
		util.MatrixUtil.printMatrix(matrix);
		System.out.println("Output:");
		setZero(matrix);
		util.MatrixUtil.printMatrix(matrix);
	}
}

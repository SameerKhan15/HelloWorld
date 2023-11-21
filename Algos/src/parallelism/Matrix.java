package parallelism;

public class Matrix {
	
	private int dim, rowDisplace, colDisplace;
	private double[][] matrix;
	
	public Matrix(double[][] matrix, int x, int y, int d) {
		this.matrix = matrix;
		rowDisplace = x;
		colDisplace = y;
		dim = d;
	}
	
	public Matrix(int d) {
		this(new double[d][d], 0, 0, d);
	}
	
	public void setValue(int row, int col, double val) throws Exception {
		if (row > (dim - 1) || col > (dim - 1) || row < 0 || col < 0) {
			throw new Exception("Illegal input. row and/or col val is out of bound");
		}
		matrix[rowDisplace + row][colDisplace + col] = val;
	}
	
	public double getValue(int row, int col) {
		return matrix[rowDisplace + row][colDisplace + col];
	}
	
	public Matrix split(int i, int j) {
		int newDim = dim / 2;
		return new Matrix(matrix, rowDisplace + (i * newDim), colDisplace + (j * newDim), newDim);
	}
	
	public int getDim() {
		return dim;
	}
	
	public static void add(Matrix lhs, Matrix rhs, Matrix sum) throws Exception {
		for (int a = 0 ; a < lhs.getDim() ; a++) {
			for (int b = 0 ; b < lhs.getDim() ; b++) {
				sum.setValue(a, b, lhs.getValue(a, b) + rhs.getValue(a, b));
			}
		}
	}
	
	public static void printMatrix(Matrix matrix) {
		for (int a = 0 ; a < matrix.getDim() ; a++) {
			for (int b = 0 ; b < matrix.getDim() ; b++) {
				System.out.print(matrix.getValue(a, b) + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int dim = 4;
		double[][] lhs = new double[dim][dim];
		double[][] rhs = new double[dim][dim];
		double[][] sum = new double[dim][dim];
		
		for (int a = 0 ; a < dim ; a++) {
			for (int b = 0 ; b < dim ; b++) {
				int val = (a + b);
				lhs[a][b] = val;
				rhs[a][b] = lhs[a][b];
			}
		}
		
		Matrix lhsMatrix = new Matrix(lhs, 0, 0, dim);
		Matrix rhsMatrix = new Matrix(rhs, 0, 0, dim);
		Matrix sumMatrix = new Matrix(sum, 0, 0, dim);
		
		try {
			Matrix.add(lhsMatrix, rhsMatrix, sumMatrix);
			
			System.out.println("LHS");
			Matrix.printMatrix(lhsMatrix);
			
			System.out.println("RHS");
			Matrix.printMatrix(rhsMatrix);
			
			System.out.println("SUM");
			Matrix.printMatrix(sumMatrix);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package parallelism;

public class MMThread {
	
	private class Worker extends Thread {
		private int row, col;
		
		private Worker(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public void run() {
			for (int i = 0;  i < n ; i++) {
				prod[row][col] = lhs[row][i] * rhs[i][col];
			}
		}
	}
	
	private double[][] lhs, rhs, prod;
	private int n;
	
	public MMThread(double[][] lhs, double[][] rhs) throws Exception {
		if (lhs == null 
				|| rhs == null 
				|| (lhs.length == 0) 
				|| (lhs.length != rhs.length) 
				|| (lhs[0].length != rhs[0].length)
				|| (lhs[0].length != lhs.length)) {
			throw new Exception("Invalid input");
		}
		
		this.lhs = lhs;
		this.rhs = rhs;
		//total number of rows
		this.n = lhs.length;
		this.prod = new double[n][n];
	}
	
	public int getLength() {
		return n;
	}
	
	public void computeDotProduct() {
		Worker[][] worker = new Worker[n][n];
		for (int row = 0 ; row < n ; row++) {
			for (int col = 0 ; col < n ; col++) {
				worker[row][col] = new Worker(row, col);
				worker[row][col].start();
			}
		}
		
		for (int row = 0 ; row < n ; row++) {
			for (int col = 0 ; col < n ; col++) {
				try {
					worker[row][col].join();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public double[][] getProductMatrix() {
		return this.prod;
	}
	
	public void printMatrix(double[][] matrix) {
		for (int row = 0 ; row < matrix.length ; row++) {
			for (int col = 0 ; col < matrix[0].length ; col++) {
				System.out.print(matrix[row][col]+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		int matrixDimension = 4;
		
		double[][] lhs = new double[matrixDimension][matrixDimension];
		double[][] rhs = new double[matrixDimension][matrixDimension];
		
		int a = 0;
		for (int row = 0 ; row < matrixDimension ; row++) {
			for (int col = 0 ; col < matrixDimension ; col++) {
				lhs[row][col] = a;
				rhs[row][col] = a;
				a++;
			}
		}
		
		try {
			MMThread mThread = new MMThread(lhs, rhs);
			mThread.computeDotProduct();
			mThread.printMatrix(mThread.getProductMatrix());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
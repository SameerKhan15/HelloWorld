package sliding.window;

public class MaxSumWithContigiousElements {
	
	public int getMaxSum(int[] array) throws Exception {
		if (array == null || array.length == 0) {
			throw new Exception("input array is null or empty");
		}
		
		int maxSum=array[0];
		int currSum = maxSum;
		int lPtr=0, rPtr=0;
		
		while (true) {
			if ((rPtr + 1) > (array.length - 1)) {
				break;
			}
			
			rPtr++;
			
			if ((currSum + array[rPtr]) >= currSum) {
				currSum = currSum + array[rPtr];
				
				if ((currSum - array[lPtr]) > currSum) {
					currSum = currSum - array[lPtr];
					lPtr++;
				}
				
				if (currSum > maxSum) {
					maxSum = currSum;
				}
			} else {
				lPtr = rPtr;
				currSum = array[lPtr];
			}
		}
		
		return maxSum;
	}
	
	public static void main(String args[]) {
		MaxSumWithContigiousElements algo = new MaxSumWithContigiousElements();
		
		try {
			/* testcase1 */
			int[] a1 = {0, 1, -4, 5, 6, -2, 2};
			System.out.println(algo.getMaxSum(a1));
			
			/* testcase2 */
			int[] a2 = {0, 10, -4, 5, 6, -2, 2};
			System.out.println(algo.getMaxSum(a2));
			
			/* testcase3 */
			int[] a3 = {-1, 2, 3, 6, 9, -9, 10, 11, -2};
			System.out.println(algo.getMaxSum(a3));
			
			/* testcase4 */
			int[] a4 = {-2};
			System.out.println(algo.getMaxSum(a4));
			
			/* testcase5*/
			int[] a5 = {};
			System.out.println(algo.getMaxSum(a5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
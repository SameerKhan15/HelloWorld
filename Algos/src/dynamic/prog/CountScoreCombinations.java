package dynamic.prog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CountScoreCombinations {
	
	private void generateCombinations(int score, int pointsIndex, List<Integer> points, List<List<Integer>> combinations, 
			List<Integer> pointsOnStack) {
		//base case
		if (score == 0) {
			combinations.add(new ArrayList<>(pointsOnStack));
			return;
		}
		
		if (score < 0) {
			return;
		}
		
		for (int a = pointsIndex ; a < points.size() ; a++) {
			int point = points.get(a);
			pointsOnStack.add(point);
			generateCombinations(score - point, a, points, combinations, pointsOnStack);
			pointsOnStack.remove(pointsOnStack.size() - 1);
		}
	}
	
	public void printScoreCombinations(int score, Set<Integer> points) {
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> pointsOnStack = new ArrayList<>();
		
		List<Integer> pointsAsList = new ArrayList<>();
		Iterator<Integer> pointsIter = points.iterator();
		while (pointsIter.hasNext()) {
			pointsAsList.add(pointsIter.next());
		}
		generateCombinations(score, 0, pointsAsList, combinations, pointsOnStack);
		System.out.println(combinations);
	}

	public static void main(String[] args) {
		CountScoreCombinations cntCombinations = new CountScoreCombinations();
		Set<Integer> points = new HashSet<>();
		points.add(2);
		points.add(4);
		points.add(6);
		points.add(8);
		
		cntCombinations.printScoreCombinations(8, points); 
	}
}
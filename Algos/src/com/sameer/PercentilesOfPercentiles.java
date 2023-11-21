package com.sameer;

import java.util.Arrays;
import java.util.Random;

public class PercentilesOfPercentiles {
	
	private Random rand;
	
	public enum CorrelationScheme {
		fullyCorrelated,
		noCorrelation;
	}
	
	public PercentilesOfPercentiles() {
		rand = new Random();
	}
	
	public void getPercentileAdd(int numberOfServices, int numDatapoints, int numOps, double perc, CorrelationScheme correlationScheme) {
		//Generate datasets
		int[][] dataset = new int[numberOfServices][numDatapoints];
		for (int a = 0 ; a < numberOfServices ; a++) {
			for (int b = 0 ; b < numDatapoints ; b++) {
				dataset[a][b] = rand.nextInt(100) + 1;
			}
		}
		
		//Build parent request latency distribution
		int[] reqs = new int[numOps];
		
		for (int a = 0 ; a < numOps ; a++) {
			for (int b = 0 ; b < numberOfServices ; b++) {
				//pick randomly
				reqs[a] += dataset[b][rand.nextInt(numDatapoints)];
			}
		}
		
		//Sort the latency array smallest -> largest
		for (int a = 0 ; a < numberOfServices ; a++) {
			Arrays.sort(dataset[a]);
			System.out.println(String.format("Dataset Perc:%s", perc)+" "+dataset[a][(int) (perc * dataset[a].length) - 1]);
		}
		
		Arrays.sort(reqs);
		System.out.println(String.format("Reqs Perc:%s", perc)+" "+reqs[(int) (perc * reqs.length) - 1]);
	}
	
	public static void main(String[] args) {
		PercentilesOfPercentiles perc = new PercentilesOfPercentiles();
		
		perc.getPercentileAdd(5, 1000, 1000000, .25, null);
		perc.getPercentileAdd(5, 1000, 1000000, .50, null);
		perc.getPercentileAdd(5, 1000, 1000000, .95, null);
		perc.getPercentileAdd(5, 1000, 1000000, .99, null);
		perc.getPercentileAdd(5, 1000, 1000000, 1, null);
	}
}
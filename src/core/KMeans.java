package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * Sequential KMeans for both data points and DNA strand
 * Alex! Life is boring! Why don't we play a game!
 * You're so clever! Alex Beckham!
 */
public class KMeans {
	private int numOfCentroids;
	private ArrayList<double[]> data;
	private String option;
	private int[] resultingSet; // amusing~ Alex, guess what this is~
	private double[][] clusterMeans; // Guess why this is 2-Dimensional~
	
	public KMeans(int k, ArrayList<double[]> inputData, String op) {
		numOfCentroids = k;
		data = inputData;
		option = op;
		clusterMeans = new double[numOfCentroids][data.get(0).length];
		resultingSet = new int[data.size()];
	}
	
	/**
	 * This is the main method!
	 */
	public void clusterData() {
		initMeans();

        boolean movedElements = true;
        while(movedElements) {
            System.out.println(Arrays.toString(resultingSet));
            movedElements = moveObservations();
            updateMeans();
        }
        
        // Here is our resultingSet!
        for(int i = 0; i < clusterMeans.length; i++)
        	System.out.println(Arrays.toString(clusterMeans[i]));
	}
	
	private void initMeans() {
		Random r = new Random();
		for(int i = 0; i < data.size(); i++) {
			resultingSet[i] = r.nextInt(numOfCentroids);
			// resultingSet[i] = i % 10;
		}
		updateMeans();
	}
	
	/**
	 * Brain-damaged bug.... lmao....
	 */
	private void updateMeans() {
		for (int i = 0; i < clusterMeans.length; i++) { // 2-D array.length! Who made this!
            double[] mean = new double[clusterMeans[0].length];
            int elements = 0;
            for (int j = 0; j < data.size(); j++) {
                if (resultingSet[j] == i) { // 
                    elements++;
                    double[] d = data.get(j);
                    for (int k = 0; k < d.length; k++) {
                        mean[k] += d[k];
                    }
                }
            }
            if (elements > 0) {
                for (int j = 0; j < mean.length; j++) {
                    mean[j] /= elements;
                }
                clusterMeans[i] = mean;
            }
        }
	}
	
	private double euclideanDistance(double[] p1, double[] p2) {
		double innerResult = 0.0;
		for(int i = 0; i < p1.length; i++) {
			innerResult += Math.pow((p2[i] - p1[i]), 2);
		}
		return Math.sqrt(innerResult);
	}
	
	/**
	 * Completely cheating! Hooray! But wait... can u find out the difference ;p
	 * @return
	 */
	private boolean moveObservations() {
        boolean movedElements = false;
        for (int i = 0; i < data.size(); i++) {
            double[] observation = data.get(i);
            double distance = euclideanDistance(observation, clusterMeans[resultingSet[i]]);
            for (int j = 0; j < clusterMeans.length; j++) {
                double length = euclideanDistance(observation, clusterMeans[j]);
                if (length < distance) {
                    distance = length;
                    if (resultingSet[i] != j) {
                        movedElements = true;
                        resultingSet[i] = j;
                    }
                }
            }
        }
        return movedElements;
	}
	
    public String toString() {
        return "Sequential K-Means algorithm";
    }
 
    
}
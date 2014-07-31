package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DNACluster {
	private int numInterations = 100;
	private String[] centroids;
	private int numOfCentroids;
	private ArrayList<String> data;
	
	private String[] oneCentroid = new String[1];
	// this used to regroup the dna strands with each cluster(centroid)
	// it is a group of [group of strings]
	// This one is really interesting!
	private ArrayList<String>[] clusters; 								
	private ArrayList<String>[] oneCluster = new ArrayList[1];
	
	private int[] resultingSet; // same use in KMeans class, only stores index
	
	private int DNAlength;
	private int numOfDNAStrands;
	private final char[] nucleobases = {'A', 'C', 'G', 'T'};
	private final int numNucleobases = 4;
	
	@SuppressWarnings("unchecked")
	public DNACluster(int numOfClusters, ArrayList<String> dnaStrands) {
		data = dnaStrands;
		DNAlength = dnaStrands.get(0).length();
		centroids = new String[numOfClusters];
		numOfCentroids = numOfClusters;
		numOfDNAStrands = dnaStrands.size();
		resultingSet = new int[numOfDNAStrands];
		// initializing clusters is different
		clusters = new ArrayList[numOfClusters];
		for (int i = 0; i < numOfClusters; i++)
			clusters[i] = new ArrayList<String>();
	}
	
	/**
	 * This is the main method.
	 */
	public void clusterData() {
		init();
		
		for(int i = 0; i < numInterations; i++) {
			/** get nearest centroid for all dna strands **/
			for(int j = 0; j < numOfDNAStrands; j++) {
				resultingSet[j] = getNearestCentroid(data.get(j)); 
			}
			
			/** After that, recalculate centroids for each cluster **/
			// regroup
			for (int j = 0; j < numOfDNAStrands; j++) {
				clusters[resultingSet[j]].add(data.get(j));
			}
			// recalculate the each centroid
			for (int j = 0; j < numOfCentroids; j++) {
				centroids[j] = recalculateCentroid(clusters[j]);
			}
		}
		
		// only need to check resulting set for final result
		System.out.println(Arrays.toString(resultingSet));
	}
	
	private void init() {
		Random random = new Random();
		for (int i = 0; i < numOfCentroids; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < DNAlength; j++) {
				sb.append(this.nucleobases[random.nextInt(this.numNucleobases)]);
			}
			centroids[i] = new String(sb);
		}
	}
	
	private int getNearestCentroid(String DNA) {
		int index = -1;
		int len = this.centroids.length;
		int minDistance = Integer.MAX_VALUE;
		int distance;

		for (int i = 0; i < len; i++) {
			distance = 0;
			for (int j = 0; j < this.DNAlength; j++) {
				if (this.centroids[i].charAt(j) != DNA.charAt(j))
					distance++;
			}
			if (distance < minDistance) {
				minDistance = distance;
				index = i;
			}
		}
		return index;
	}
	
	private String recalculateCentroid(ArrayList<String> DNAStrands) {
		StringBuilder newCentroid = new StringBuilder();
		for (int i = 0; i < this.DNAlength; i++) {

			// get the frequency of each kind of nucleobase at "i" position
			// for all strands in the current cluster
			int[] freqNucleobases = new int[this.numNucleobases];
			for (String DNAStrand : DNAStrands) {
				char nucleobase = DNAStrand.charAt(i);
				switch (nucleobase) {
					case 'A' :
						freqNucleobases[0]++;
						break;
					case 'C' :
						freqNucleobases[1]++;
						break;
					case 'G' :
						freqNucleobases[2]++;
						break;
					case 'T' :
						freqNucleobases[3]++;
						break;
				}
			}

			// get the most populous nucleobase
			int maxFreq = Integer.MIN_VALUE;
			int popIndex = -1;
			for (int j = 0; j < this.numNucleobases; j++) {
				if (freqNucleobases[j] > maxFreq) {
					maxFreq = freqNucleobases[j];
					popIndex = j;
				}
			}
			// attach the most populous nucleobase to the new centroid
			newCentroid.append(this.nucleobases[popIndex]);
		}
		return new String(newCentroid);
	}
}

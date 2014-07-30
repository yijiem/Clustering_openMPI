package test;

import io.ReadCSV;
import java.util.ArrayList;
import config.ConfigFile;
import core.KMeans;

/*
 * Test is interesting!
 */
public class TestForSequential {
	public static void main(String[] args) {
		ArrayList<double[]> inputData = new ArrayList<double[]>();
		inputData = ReadCSV.read(ConfigFile.inputFile, "nothing");
		KMeans seqKM = new KMeans(ConfigFile.numOfCentroids, inputData, "nothing");
		seqKM.clusterData();
	}
}

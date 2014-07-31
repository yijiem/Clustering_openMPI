package test;

import io.ReadCSV;

import java.util.ArrayList;

import config.ConfigFile;
import core.DNACluster;
import core.KMeans;

/*
 * Test is interesting!
 */
public class TestForSequential {
	public static void main(String[] args) {
		ArrayList<double[]> inputData = new ArrayList<double[]>();
		inputData = ReadCSV.read(ConfigFile.inputDataPointFile, ConfigFile.dataPointOption);
		KMeans seqKM = new KMeans(ConfigFile.numOfCentroids, inputData, ConfigFile.dataPointOption);
		seqKM.clusterData();
		
		System.out.println("----------------Interval Line-----------------");
		
		ArrayList<String> inputData2 = new ArrayList<String>();
		inputData2 = ReadCSV.read(ConfigFile.inputDNAFile, ConfigFile.dnaOption);
		DNACluster seqDNA = new DNACluster(ConfigFile.numOfCentroids, inputData2);
		seqDNA.clusterData();
	}
}

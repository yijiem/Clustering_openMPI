package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * What is a fucking CSV file! Fucking CMU!
 */
public class ReadCSV {
	public static ArrayList<double[]> read(String filename, String option) {
		ArrayList<double[]> returnList = new ArrayList<double[]>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			String line = "";
			while((line = bf.readLine()) != null) {
				String[] coordinate = line.split(",");
				double[] data = new double[coordinate.length];
				for(int i = 0; i < data.length; i++) {
					data[i] = Double.parseDouble(coordinate[i]);
				}
				returnList.add(data);
			}
			// print data set for test
			// for(int i = 0; i < returnList.size(); i++) {
			// 	System.out.println(Arrays.toString(returnList.get(i)));
			// }
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnList;
	}
}

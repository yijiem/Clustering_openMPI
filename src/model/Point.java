package model;

/*
 * This class is discarded! I could be no more stupid. 
 * I thought I have to use ArrayList<Point> and ArrayList<double>
 * to represent 2-d data point and DNA strand respectively.
 * Yet, this can be achieved by just using ArrayList<double[]>.
 * Shit!
 */
public class Point {
	private double x_coordinate;
	private double y_coordinate;
	
	public Point(double x, double y) {
		x_coordinate = x;
		y_coordinate = y;
	}
}

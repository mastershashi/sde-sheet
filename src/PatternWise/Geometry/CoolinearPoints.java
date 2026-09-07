package PatternWise.Geometry;

import java.util.HashMap;
import java.util.Map;

public class CoolinearPoints {
    /**
     * 
     * Given three points are coolinear if they have same slope
     * A(x1,y1)
     * B(x2,y2)
     * c(x3,y3)
     * slope (AB) = (x2-x1)/(y2-y1)
     * clope (BC) = (x3-x2)/(y3-y2);
     * 
     */
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // points are given here
    boolean isCoolinear(Point[] points) {
        Point currentPoint = points[0];
        double[] slopArray = new double[2];
        int k = 0;
        for (int i = 1; i < points.length; i++) {
            slopArray[k++] = Math.abs(points[i].x - currentPoint.x) / (points[i].y - currentPoint.y);
            currentPoint = points[i];
        }
        if (slopArray[0] == slopArray[1]) {
            return true;
        }
        return false;

    }

    int maximumNumberOfCoolinearPoints(Point[] points) {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        Point currentPoint = points[0];
        for (int i = 1; i < points.length; i++) {
            Double slope = Math.abs(points[i].x - currentPoint.x) / (points[i].y - currentPoint.y);
            frequencyMap.put(slope, frequencyMap.getOrDefault(slope, 0) + 1);
            currentPoint = points[i];
        }
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Double, Integer> slopeFrequency : frequencyMap.entrySet()) {
            max = Math.max(max, slopeFrequency.getValue());
        }
        return max;
    }

    public static void main(String[] args) {
        Point pointA = new Point(1, 1);
        Point pointB = new Point(2, 2);
        Point pointC = new Point(3, 3);
        Point pointD = new Point(4, 5);
        Point pointE = new Point(5, 6);
        Point[] pointArray = new Point[5];
        pointArray[0] = pointA;
        pointArray[1] = pointB;
        pointArray[2] = pointC;
        pointArray[3] = pointD;
        pointArray[4] = pointE;
        CoolinearPoints obj = new CoolinearPoints();
        System.out.println(obj.maximumNumberOfCoolinearPoints(pointArray));
    }

}

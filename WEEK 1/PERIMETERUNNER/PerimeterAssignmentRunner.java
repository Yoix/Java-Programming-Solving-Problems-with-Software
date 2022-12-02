package PERIMETERUNNER;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int totalPoints = 0;
        for(Point p : s.getPoints()){
            totalPoints = totalPoints + 1;
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        double numSides = (double) getNumPoints(s);
        double avgLength = length / numSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point lastPoint = s.getLastPoint();
        double largestSide = 0.0;
        for (Point currentPoint : s.getPoints()){
            double distance = lastPoint.distance(currentPoint);
            if(distance > largestSide){
                largestSide = distance;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point lastPoint = s.getLastPoint();
        double largestX = 0.0;
        for (Point currentPoint : s.getPoints()){
            int lgX = currentPoint.getX();
            if(lgX > largestX){
            largestX = lgX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeterMF = 0.0;
        File largestfile = null;
        
        for(File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        Shape s = new Shape(fr);
        double perim = getPerimeter(s);
        if(perim > largestPerimeterMF){
        largestPerimeterMF = perim;
        }
       }
        return largestPerimeterMF;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        File largestFile = null;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if(perim > largestPerimeter){
                largestPerimeter = perim;
                largestFile = f;
            }
        }
        return largestFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        int totalPoints = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        
        System.out.println("Perimeter = " + length);
        System.out.println("Total Points = " + totalPoints);
        System.out.println("Total Average length = " + avgLength);
        System.out.println("Largest Side = " + largestSide);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double perimeterMultipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println("Perimeter in Multiple Files =" + perimeterMultipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("File with the largest perimeter = " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}

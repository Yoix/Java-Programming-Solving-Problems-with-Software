
/**
 * Write a description of class BabyBirths here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths{
    public static void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord recorder : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(recorder.get(2));
            if(numBorn <= 100){
            System.out.println("Name " + recorder.get(0) + " Gender " + recorder.get(1) +
                               " Num Born " + recorder.get(2));
            }
        }
    }
    public static void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        for (CSVRecord recorder : fr.getCSVParser(false)){
            if(recorder.get(1).equals("M")){
                totalBoys+=1;
            } else {
                totalGirls+=1;
            }
            totalBirths +=1;
        }
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total Boys: " + totalBoys);
        System.out.println("Total Girls: " + totalGirls);
    }
    //This static method returns the rank of the name in the file
    //for the given gender, where rank 1 is the name with the largest
    //number of births.
    public static int getRank(int year, String name, String gender){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord recorder : parser){
            if (recorder.get(1).equals(gender)) {
                rank += 1;
                if (recorder.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    public static String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int num = 0;
        for (CSVRecord recorder : parser){
            num +=1;
            if (num == rank){
                String name = recorder.get(0);
                return name;
            }
        }
        return "No name";
    }
    public static void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        if (rank != -1) {
            String newname = getName(newYear, rank, gender);
            System.out.println(name+" born in " + year + " would be "+ newname+" if (s)he was born in " + newYear);
        }
        else {
            System.out.println("NO NAME");
        
        }
    }
    public static int getRank2(int year, String name, String gender, FileResource fr){
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for(CSVRecord recorder : parser){
            if(recorder.get(1).equals(gender)){
                rank +=1;
                if(recorder.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    public static int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource(); 
        int highestRank = 0;
        String yob = null;
        int year = 0;
        int highestYear = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            yob = fName.substring(3,7);
            year = Integer.parseInt(yob);
            int currentRank = getRank2(year, name, gender, fr);
            if (highestRank == 0){
                highestRank = currentRank;
            }
            if (currentRank != -1){
                if(currentRank < highestRank) {
                    highestRank = currentRank;
                    highestYear = year;
                }
            } else {
                return -1;
            }
        }
        return highestYear;
    }
    public static double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0;
        String yob = null;
        int year = 0;
        int count = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            yob = fName.substring(3,7);
            year = Integer.parseInt(yob);
            int currentRank = getRank2(year, name,gender,fr);
            if(currentRank != 1){
                totalRank += currentRank;
                count++;
            }
            System.out.println(totalRank);
        }
        if(totalRank == 0){
            return -1.0;
        }
        double averageRank = totalRank/count;
        return averageRank;
    }
    public static int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int rank = getRank(year, name, gender);
        for(CSVRecord recorder: fr.getCSVParser(false)){
            if (recorder.get(1).equals(gender)){
                rank--;
                if(rank > 0){
                    totalBirths += Integer.parseInt(recorder.get(2));
                }
            }
        }
        return totalBirths;
    }
    
    //TESTINGS
    public static void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public static void testGetRank(){
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    public static void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    public static void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public static void testYearOfHighestRank(){
        int year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }
    public static void testAverageRank(){
        double averageRank = getAverageRank("Robert", "M");
        System.out.println(averageRank);
    }
    public static void testGetTotalBirthsRankedHigher(){
        int totalBirth = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(totalBirth);
    }
}

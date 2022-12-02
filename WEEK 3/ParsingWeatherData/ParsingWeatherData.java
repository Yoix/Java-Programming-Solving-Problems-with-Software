
/**
 * Write a description of class ParsingWeatherData here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class ParsingWeatherData{
    //Testing in the main void
    public static void main(String[] args){
        //testColdestHourInFile();
        testFileWithColdestTemperature();
        //testLowestHumidityInFile();
        //testLowestHumidityInManyFiles();
        //testAverageTemperatureInFile();
        //testAverageTemperatureWithHighHumidityInFile();
    }
    //This Function get a coldest hour in a one File.
    public static CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow: parser){
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    //This function get a smallest of two rows, represtanting in CSV
    public static CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if (smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp != -9999) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    //Function that works with files. So, u can see what is the day or an hour with the most coldest temperature
    public static String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        String filename="";
    
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parse = fr.getCSVParser();
            CSVRecord currentRecord = coldestHourInFile(parse);
            coldestSoFar = getSmallestOfTwo(currentRecord, coldestSoFar);
            filename = f.getPath();
        }
        return filename;
    }
    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        //Start with lowestSoFar as nothing
        CSVRecord lowestSoFar = null;
        ///For each row in the CSV File
        for (CSVRecord currentRow : parser){
            lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
        }
        //The Lowest So Far is the answer
        return lowestSoFar;
    }
    
    //Function help the lowestHumidityInFile for get the smallest humidity
    public static CSVRecord getSmallestHumidityOfTwo(CSVRecord currentRow,CSVRecord smallestSoFar){
        //If smallestSoFar is nothing
        if (smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else {
            if(currentRow.get("Humidity").length() != 3){
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                //Check if currentRow’s temperature < smallestSoFar’s
            if (currentTemp < smallestTemp && currentTemp != -9999) {
                    //If so update smallestSoFar to currentRow
                    smallestSoFar = currentRow;       
                }
            }
        }
        return smallestSoFar;
    }
    //Test coldest humidity in many files
    public static CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        //Iterating in the fles
        for (File f : dr.selectedFiles()){
            //USE METHOD TO GET LARGEST IN FILE
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    //Temperature average in a only file. Every temperature dividide for the counting of the temperatures
    public static double averageTemperatureInFile(CSVParser parser){
        //Initializing values
        double sum = 0;
        double average = 0;
        int count = 1;
        //Iterating in the files
        for (CSVRecord currentRow : parser){
            //Actually temperature
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            //Doing the calculates
            sum += currentTemp;
            average = sum / count;
            count++;
        }
        return average;
    }
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        //Initializing values
        double sum = 0;
        double average = 0;
        int count = 1;
        //Iterating in the files
        for (CSVRecord currentRow : parser){
            //Compare two methods
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentHumidity >= value){
            sum += currentTemp;
            average = sum/count;
            count++;
            }        
        }
        return average;
    }
    //TESTINGS
    public static void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEDT"));
    }
    public static void testFileWithColdestTemperature() {
    String filename = fileWithColdestTemperature();
    FileResource fr = new FileResource(filename);
    CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
    System.out.println("Coldest day was in file "+ filename);
    System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
    System.out.println("All the Temperature on the coldest day were:");
                
    for (CSVRecord currentRow : fr.getCSVParser()) {
            // use method to compare two records
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF") );;
        }
    }
    public static void testLowestHumidityInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());   
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }
    public static void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public static void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    public static void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (average == 0){
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when high Humidity is " + average);
        }
    }
}

/**
 * Write a description of class WhichCountriesExport here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport{
    public static void numberOfExporters(CSVParser parser, String exportOfInterest){
        int count = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)){
                String country = record.get("Country");   
                System.out.println(country);
                count++;
            }
        }
        System.out.println("How many countries export " + exportOfInterest + " : " + count);
    }
    public static void countryInfo(CSVParser parser, String country){
        boolean found = false;
        for (CSVRecord record : parser){
            String getCountry = record.get("Country");
            if (getCountry.contains(country)){
                String export = record.get("Exports");
                System.out.println(getCountry + " : " + export);
                found = true;
            }
        }
        if (found != true){
            System.out.println("NOT FOUND");
        }
    }
    public static void listExporterTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println("The " + country + " exports " + exportItem1 + " and " + exportItem2);
            }
        }
    }
    public static void bigExports(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (value.length() > ("$"+amount).length()){
                String country = record.get("Country");
                System.out.println(country + " : " + value);
            }
        }
    }
    public static void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();
        numberOfExporters(parser, "cocoa");
        parser = fr.getCSVParser();
        listExporterTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        bigExports(parser, "$999,999,999,999");
        parser = fr.getCSVParser();
    }
    public static void main(String[] args){
        tester();
    }
}

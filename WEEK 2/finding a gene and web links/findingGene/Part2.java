
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class Part2 {
    public static String findSimpleGene(String dna, String starCodon, String stopCodon){
    String resultTemp = "";
    int start = dna.indexOf("ATG");
    int stop = dna.indexOf("TAA");
    if (start == -1){
        return "";
    }
    if((stop - start) % 3 == 0){
        resultTemp = dna.substring(start, stop + 3);
        String result = resultTemp.toLowerCase();
        return result;
    }
    else {
        return "";
    }
    }
    
    public static void testSimpleGene(){
    String gene = "";
    String dna1 = "AAAATACCAGTACCACTAAGGA";
    String startCodon = "ATG";
    String stopCodon = "TAA";
    
    System.out.println("First DNA Strand is: " + dna1);
    gene = findSimpleGene(dna1, startCodon, stopCodon);
    System.out.println("The gene is: " + gene);
    
    String dna2= "ATCATGAACAACGGA";
    System.out.println("Second DNA Strand is: " + dna2);
    gene = findSimpleGene(dna2, startCodon, stopCodon);
    System.out.println("The gene is: " + gene);
    
    String dna3= "ATCGAATCCAAT";
    System.out.println("Third DNA Strand is: " + dna3);
    gene = findSimpleGene(dna3, startCodon, stopCodon);
    System.out.println("The gene is: " + dna3);
    
    String dna4= "ATCATCATGGTGGTTTAAGAC";
    System.out.println("Fourth DNA Strand is: " + dna4);
    gene = findSimpleGene(dna4, startCodon, stopCodon);
    System.out.println("The gene is: " + dna4);
    
    String dna5= "ATGCGCCGTAA";
    System.out.println("Fifth DNA Strand is: " + dna5);
    gene = findSimpleGene(dna5, startCodon, stopCodon);
    System.out.println("The gene is: " + gene);
    }
    
}

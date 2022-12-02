
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part1 {
    public static String findSimpleGene(String dna){
    int start = dna.indexOf("ATG");
        if (start == -1){
        return "";
    }
    int stop = dna.indexOf("TAA");
    if((stop - start) % 3 == 0){
        return dna.substring(start, stop + 3);
    }
    else {
        return "";
    }
    }
    
    public static void testSimpleGene(){
    String gene = "";
    String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
    System.out.println("First DNA Strand is: " + dna1);
    gene = findSimpleGene(dna1);
    System.out.println("The gene is: " + gene);
    
    String dna2= "ATCATGAACAACGGA";
    System.out.println("Second DNA Strand is: " + dna2);
    gene = findSimpleGene(dna2);
    System.out.println("The gene is: " + gene);
    
    String dna3= "ATCGAATCCAAT";
    System.out.println("Third DNA Strand is: " + dna3);
    gene = findSimpleGene(dna3);
    System.out.println("The gene is: " + dna3);
    
    String dna4= "ATCATCATGGTGGTTTAAGAC";
    System.out.println("Fourth DNA Strand is: " + dna4);
    gene = findSimpleGene(dna4);
    System.out.println("The gene is: " + dna4);
    
    String dna5= "ATGCGCCGTAA";
    System.out.println("Fifth DNA Strand is: " + dna5);
    gene = findSimpleGene(dna5);
    System.out.println("The gene is: " + gene);
    }
    
}

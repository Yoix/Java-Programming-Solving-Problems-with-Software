
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        startIndex = dna.indexOf("ATG");
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != 1){
            int difference = (currentIndex - startIndex) % 3;
            if (difference == 0) {
                return currentIndex;
            }
            else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex +3);
    }
    
    public void testFindGene(){
        String dna= "AGDEGAASZZATAAAAA";
        System.out.println("The dna string is :" + dna);
        String gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
        
        dna= "aaaaaaATGaaaaaaaaaTAGaaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
        
        dna= "aaaaaaATGaaaaaaaaaTAGTTATGAaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
        
        dna= "aaaaaaATGaaaaaaaaaAAAAaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
    }
    
    public void printAllGenes(){
        String dna = "aaaaaaATGaaaaaaaaaTAGTTATGAaaa";
        dna ="aaaaaaATGaaaaaaaaaTAGaaaa";
        dna = "AGDEGAASZZATAAAAA";
        String findingGene = findGene(dna);
        
        while (true) {
            System.out.println("Printing all genes" + findingGene);
            if (findingGene.length() == -1){
                break;
            }
        }
    }
}

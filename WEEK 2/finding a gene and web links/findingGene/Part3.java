/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public static boolean twoOccurrences(String stringa, String stringb){
        int ocurrence = stringb.indexOf(stringa);
        if (ocurrence == -1){
            return false;
        } else {
            System.out.println("Ocurrence = " + ocurrence);
            return true;
        }
    }
    
    public static String lastPart(String stringa, String stringb){
        int ocurrence = stringb.indexOf(stringa);
        int startFrom = stringa.length();
        String finalPart = stringb.substring(ocurrence + startFrom);
        
        if(ocurrence == 1){
            return stringb;
        } else {
            return finalPart;
        }
    }
    
    public static void testing(){
        boolean ocurrence;
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println("Stringa: " + stringa + " and Stringb: " + stringb);
        ocurrence = twoOccurrences(stringa, stringb);
        System.out.println("Its a: " + ocurrence);
        
        stringa = "an";
        stringb = "banana";
        System.out.println("Stringa: " + stringa + " and Stringb: " + stringb);
        ocurrence = twoOccurrences(stringa, stringb);
        System.out.println("Its a: " + ocurrence);
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("Stringa: " + stringa + " and Stringb: " + stringb);
        ocurrence = twoOccurrences(stringa, stringb);
        System.out.println("Its a: " + ocurrence);        

        stringa = "an";
        stringb = "banana";
        System.out.println("The part of the string after " + stringa + " in " +stringb +" is "+ lastPart(stringa, stringb));        
    }
}

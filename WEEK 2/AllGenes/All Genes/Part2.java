
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public static int howMany(String stringa, String stringb){
        int counter = 0;
        int firstOccurrence = stringb.indexOf(stringa);
        if (firstOccurrence > -1){
            counter += 1;
            while (stringb.indexOf(stringa, firstOccurrence) != -1 && firstOccurrence != -1){
                counter += 1;
                firstOccurrence = stringb.indexOf(stringa, firstOccurrence + stringa.length());
        }
        counter += 1;
        }
        else {
            counter +=1;
        }
        return counter;
    }
    
    
}

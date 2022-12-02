
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
   void first() {
     
      String url = "https://www.dukelearntoprogram.com/course2/data/manylinks.html"; // update to https
      URLResource ur = new URLResource(url);
      for (String s : ur.words()) {
         // searching youtube URL
         String str = s.toLowerCase();
         int pos=str.indexOf("youtube");
         if(pos!=-1)
         {
          String quote = "\"";
          int firstIndex = s.lastIndexOf(quote, pos);
          int secondIndex = s.indexOf(quote,pos+1);
          System.out.println(s.subSequence(firstIndex, secondIndex));
         }
      }
   }

   public static void main(String[] args) {
      Part4 w = new Part4();
      w.first();
   }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.util.*;
public class ElapsedTime {
   public int getDays(GregorianCalendar g1, GregorianCalendar g2) {
      int elapsed = 0;
      GregorianCalendar gc1, gc2;
      if (g2.after(g1)) {
         gc2 = (GregorianCalendar) g2.clone();
         gc1 = (GregorianCalendar) g1.clone();
      }
      else   {
         gc2 = (GregorianCalendar) g1.clone();
         gc1 = (GregorianCalendar) g2.clone();
      }
      gc1.clear(Calendar.MILLISECOND);
      gc1.clear(Calendar.SECOND);
      gc1.clear(Calendar.MINUTE);
      gc1.clear(Calendar.HOUR_OF_DAY);
      gc2.clear(Calendar.MILLISECOND);
      gc2.clear(Calendar.SECOND);
      gc2.clear(Calendar.MINUTE);
      gc2.clear(Calendar.HOUR_OF_DAY);
      while ( gc1.before(gc2) ) {
         gc1.add(Calendar.DATE, 1);
         elapsed++;
      }
      return elapsed;
   }
   public int getMonths(GregorianCalendar g1, GregorianCalendar g2) {
      int elapsed = 0;
      GregorianCalendar gc1, gc2;
      if (g2.after(g1)) {
         gc2 = (GregorianCalendar) g2.clone();
         gc1 = (GregorianCalendar) g1.clone();
      }
      else   {
         gc2 = (GregorianCalendar) g1.clone();
         gc1 = (GregorianCalendar) g2.clone();
      }
      gc1.clear(Calendar.MILLISECOND);
      gc1.clear(Calendar.SECOND);
      gc1.clear(Calendar.MINUTE);
      gc1.clear(Calendar.HOUR_OF_DAY);
      gc1.clear(Calendar.DATE);
      gc2.clear(Calendar.MILLISECOND);
      gc2.clear(Calendar.SECOND);
      gc2.clear(Calendar.MINUTE);
      gc2.clear(Calendar.HOUR_OF_DAY);
      gc2.clear(Calendar.DATE);
      while ( gc1.before(gc2) ) {
         gc1.add(Calendar.MONTH, 1);
         elapsed++;
      }
      return elapsed;
   }
}


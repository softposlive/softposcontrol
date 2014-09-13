/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.util.*;
public class ElapsedMillis {
   public static void main(String[] args) {
      GregorianCalendar gc1 = new GregorianCalendar(1995, 11, 1, 3, 2, 1);
      GregorianCalendar gc2 = new GregorianCalendar(1995, 11, 1, 3, 2, 2);
      // the above two dates are one second apart
      Date d1 = gc1.getTime();
      Date d2 = gc2.getTime();
      long l1 = d1.getTime();
      long l2 = d2.getTime();
      long difference = l2 - l1;
      System.out.println("Elapsed milliseconds: " + difference);
   }
}    
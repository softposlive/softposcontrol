/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.util.*;
import java.text.*;
public class Apollo {
   public static void main(String[] args) {
      GregorianCalendar liftOffApollo11 = new GregorianCalendar(1969, Calendar.JULY, 16, 9, 32);
      Date d = liftOffApollo11.getTime();
      DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
      DateFormat df2 = DateFormat.getTimeInstance(DateFormat.SHORT);
      String s1 = df1.format(d);
      String s2 = df2.format(d);
      System.out.println(s1);
      System.out.println(s2);
   }
} 

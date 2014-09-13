/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.util.*;
public class LunarLanding {
   public long getElapsedSeconds(GregorianCalendar gc1, GregorianCalendar gc2) {
      Date d1 = gc1.getTime();
      Date d2 = gc2.getTime();
      long l1 = d1.getTime();
      long l2 = d2.getTime();
      long difference = Math.abs(l2 - l1);
      return difference / 1000;
   }
   public void calcHM(long timeInSeconds) {
      long hours, minutes, seconds;
      hours = timeInSeconds / 3600;
      timeInSeconds = timeInSeconds - (hours * 3600);
      minutes = timeInSeconds / 60;
      System.out.println(hours + " hour(s) " + minutes + " minute(s)" );
   }
   public static void main(String[] args) {
      GregorianCalendar lunarLanding = new GregorianCalendar(1969, Calendar.JULY, 20, 16, 17);
      GregorianCalendar lunarDeparture = new GregorianCalendar(1969, Calendar.JULY, 21, 13, 54);
      GregorianCalendar startEVA = new GregorianCalendar(1969, Calendar.JULY, 20, 22, 56);
      GregorianCalendar endEVA = new GregorianCalendar(1969, Calendar.JULY, 21, 1, 9);
      LunarLanding apollo = new LunarLanding();
      long eva = apollo.getElapsedSeconds(startEVA, endEVA);
      System.out.print("EVA duration = ");
      apollo.calcHM(eva);
      long lunarStay = apollo.getElapsedSeconds(lunarLanding, lunarDeparture);
      System.out.print("Lunar stay = ");
      apollo.calcHM(lunarStay);
   }
}          


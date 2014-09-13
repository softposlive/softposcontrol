/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.util.*;
public class Elapsed1 {
   public void calcHMS(int timeInSeconds) {
      int hours, minutes, seconds;
      hours = timeInSeconds / 3600;
      timeInSeconds = timeInSeconds - (hours * 3600);
      minutes = timeInSeconds / 60;
      timeInSeconds = timeInSeconds - (minutes * 60);
      seconds = timeInSeconds;
      System.out.println(hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)");
   }
   public static void main(String[] args) {
      Elapsed1 elap = new Elapsed1();
      elap.calcHMS(10000);
   }
}  


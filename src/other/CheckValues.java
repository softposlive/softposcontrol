/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.awt.Frame;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class CheckValues {
    
    public static void main(String [] args){
        
        int year = 1984;
        int month = 6;
        int day = 14;
        Calendar birthDay = new GregorianCalendar(year,month-1,day);
        CheckValues chk = new CheckValues();
        int numDays = chk.getBirthDay(birthDay, 15);
        System.out.println("numDays := "+numDays);
        if(numDays == 0)
            JOptionPane.showMessageDialog(new Frame(), "Today is birthday.");
        else if(numDays == -1)
            JOptionPane.showMessageDialog(new Frame(), "Scarcely birthday.");
        else
            JOptionPane.showMessageDialog(new Frame(), numDays + " more days to birthday.");
        
        System.exit(0);
    }
    
    public int getBirthDay(Calendar birthDay , int range){
        Calendar today = Calendar.getInstance();
        return getBirthDay(birthDay , today , range);
    }
    
    public int getBirthDay(Calendar birthDay, Calendar today, int range){
        today = resetCalendar(today);
        birthDay = resetCalendar(birthDay);
        
        //showDate(today,"today");        
        Calendar rangeDay = (Calendar)today.clone();  
        
        rangeDay.add(Calendar.DATE, range);
        //showDate(rangeDay,"toBirth");
        
        if(today.get(Calendar.YEAR)==rangeDay.get(Calendar.YEAR)){
            return normalChkBirthDay(birthDay , today, rangeDay);
        }else{
            return abnormalChkBirthDay(birthDay , today, rangeDay);
        }
    }
        
    private Calendar resetCalendar(Calendar cal){
        return new GregorianCalendar(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
    }
    
    private int normalChkBirthDay(Calendar birthDay , Calendar today, Calendar rangeDay){
        birthDay.set(Calendar.YEAR, today.get(Calendar.YEAR));        
        return getDays(birthDay , today, rangeDay);
    }
    
    private int abnormalChkBirthDay(Calendar birthDay , Calendar today, Calendar rangeDay){
        if(birthDay.get(Calendar.MONTH)==Calendar.DECEMBER){
            birthDay.set(Calendar.YEAR, today.get(Calendar.YEAR));
        }else if(birthDay.get(Calendar.MONTH)==Calendar.JANUARY){
            birthDay.set(Calendar.YEAR, rangeDay.get(Calendar.YEAR));
        }
        showDate(birthDay,"DDD");
        return getDays(birthDay , today, rangeDay);
    }
    
    private int getDays(Calendar birthDay , Calendar today, Calendar rangeDay){
        System.out.println("\n===================");
        System.out.println("BirthDay := "+birthDay.getTime());
        System.out.println("Today    := "+today.getTime());
        System.out.println("ToBirth  := "+rangeDay.getTime());
        System.out.println("===================\n");
        
        if(birthDay.compareTo(today) >= 0 & birthDay.compareTo(rangeDay) <=0){
            ElapsedTime et = new ElapsedTime();
            int day = et.getDays((GregorianCalendar)today, (GregorianCalendar)birthDay);
            return day;
        }else{ 
            return -1;
        }
    }
    
    public void showDate(Calendar today ,String s){
         System.out.println("\nShow Date "+s);
         System.out.println("\ttoday.get(today.DAY_OF_MONTH) ="+today.get(Calendar.DAY_OF_MONTH));
         System.out.println("\ttoday.get(today.MONTH + 1)    ="+(today.get(Calendar.MONTH)+1));
         System.out.println("\ttoday.get(today.DAY_OF_YEAR)  ="+today.get(Calendar.DAY_OF_YEAR));
         System.out.println("\ttoday.get(today.WEEK_OF_YEAR) ="+today.get(Calendar.WEEK_OF_YEAR));
         System.out.println("\ttoday.get(today.YEAR) ="+today.get(Calendar.YEAR));
         System.out.println(today.getTime());
    }
}

package softpos.gui;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import utilities.MySQLConnect;

public class PUtility {
    Font myfont = new Font("Tahoma", Font.PLAIN, 14);
    static SimpleDateFormat Dateft = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    Date date = new Date() ;

    public static Boolean ChkValidDate(Date tdate) {
        Boolean ReturnValue = true;
        String TempStr = "";
        try {
            TempStr = Dateft.format(tdate);
            ReturnValue = true;
        } catch (Exception e) {
            ReturnValue = false;
        }
        return ReturnValue;
    }
    public static Boolean ChkDate(String tdate) {
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean ReturnValue = true;
        Date TempDate = new Date() ;
        try {
           TempDate  = DateFmt.parse(tdate);
           if (TempDate.getYear()>2200) {
               JOptionPane.showMessageDialog(null, "กรุณาป้อนวันที่ให้ถูกต้อง...EXP(dd/MM/yyyy) โดยป้อนปีเป็นปี คศ.เท่านั้น...");
               ReturnValue = false ;
           } else {
              ReturnValue = true ;
           }
        } catch (ParseException ex) {
           JOptionPane.showMessageDialog(null, "กรุณาป้อนวันที่ให้ถูกต้อง...EXP(dd/MM/yyyy)");
           ReturnValue = false ;
        }
       
        return ReturnValue;
    }
    public static String ConvertReal(String TStr) {
        String TempStr = "" ;
        if (TStr.indexOf(",")>0){
            for (int i=0;i<TStr.length();i++) {
                char ch = TStr.charAt(i) ;
                String StrCh = "" ;
                StrCh = StrCh+ch ;
                if (!StrCh.equals(",")) {
                    TempStr = TempStr+StrCh ;
                }
            } 
        } else {
            TempStr = TStr ;
        }
        return TempStr ;
    }
    
    public static Calendar StrToCalendar(String tdate) {
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean ReturnValue = true;
        Calendar ca2 = new GregorianCalendar() ;
        Date TempDate = new Date() ;
        int dd = Integer.parseInt(tdate.substring(0, 2)) ;
        int mm = Integer.parseInt(tdate.substring(3, 5)) ;
        int yy = Integer.parseInt(tdate.substring(6, 10)) ;
        ca2.set(Calendar.YEAR, yy) ;
        ca2.set(Calendar.MONTH, mm-1) ;
        ca2.set(Calendar.DATE, dd) ;
        ca2.set(Calendar.HOUR, 0) ;
        ca2.set(Calendar.MINUTE, 0) ;
        ca2.set(Calendar.SECOND,0) ;
        return ca2;
    }
    public static Calendar GetEndMonth(String tdate) {
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean ReturnValue = true;
        Calendar ca2 = new GregorianCalendar();
        Date TempDate = new Date();
        int dd = Integer.parseInt(tdate.substring(0, 2));
        int mm = Integer.parseInt(tdate.substring(3, 5));
        int yy = Integer.parseInt(tdate.substring(6, 10));
        if ((mm == 1) || (mm == 3) || (mm == 5) || (mm == 7) || (mm == 8) || (mm == 10) || (mm == 12)) {
            dd = 31;
        } else {
            if ((mm == 4) || (mm == 6) || (mm == 9) || (mm == 11)) {
                dd = 30;
            } else {
                if ((yy == 2000) || (yy == 2004) || (yy == 2008) || (yy == 2012) || (yy == 2016) || (yy == 2020) || (yy == 2024) || (yy == 2028)) {
                    dd = 29;
                } else {
                    dd = 28;
                }
            }
        }
        ca2.set(Calendar.YEAR, yy);
        ca2.set(Calendar.MONTH, mm - 1);
        ca2.set(Calendar.DATE, dd);
        ca2.set(Calendar.HOUR, 0);
        ca2.set(Calendar.MINUTE, 0);
        ca2.set(Calendar.SECOND, 0);
        return ca2;
    }
    public static Boolean ChkEndMonth(String tdate) {
        SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Boolean ReturnValue = true;
        Calendar ca2 = new GregorianCalendar();
        Date TempDate = new Date();
        int dd = Integer.parseInt(tdate.substring(0, 2));
        int mm = Integer.parseInt(tdate.substring(3, 5));
        int yy = Integer.parseInt(tdate.substring(6, 10));
        int Tempdd = dd ;
        if ((mm == 1) || (mm == 3) || (mm == 5) || (mm == 7) || (mm == 8) || (mm == 10) || (mm == 12)) {
            dd = 31;
        } else {
            if ((mm == 4) || (mm == 6) || (mm == 9) || (mm == 11)) {
                dd = 30;
            } else {
                if ((yy == 2000) || (yy == 2004) || (yy == 2008) || (yy == 2012) || (yy == 2016) || (yy == 2020) || (yy == 2024) || (yy == 2028)) {
                    dd = 29;
                } else {
                    dd = 28;
                }
            }
        }
        if (Tempdd==dd) {
           ReturnValue = true ;  
        } else {
            ReturnValue = false ;
        }
        
        return ReturnValue;
    }
    public static int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end

            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(java.util.Calendar.DAY_OF_YEAR) -
                d1.get(java.util.Calendar.DAY_OF_YEAR);
        int y2 = d2.get(java.util.Calendar.YEAR);
        if (d1.get(java.util.Calendar.YEAR) != y2) {
            d1 = (java.util.Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
                d1.add(java.util.Calendar.YEAR, 1);
            } while (d1.get(java.util.Calendar.YEAR) != y2);
        }
        return days;
    }

    public static Boolean ShowConfirmMsg(String ConfirmMsg) {
        int option = JOptionPane.showConfirmDialog(null, ConfirmMsg, "Confirm Dialog ....", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public static String Addzero(String Str, int Len) {
        String TempStr = "";
        String ReturnStr = "";
        int StrLen = Str.trim().length();
        if (StrLen < Len) {
            for (int i = 1; i <= Len - StrLen; i++) {
                TempStr = TempStr + "0";
            }
            ReturnStr = TempStr + Str.trim();
        } else {
            ReturnStr = Str;
        }
        return ReturnStr;
    }

    public static boolean ChkNumValue(String Str) {
        String NumList[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "-",","};
        StringBuffer StrBuf = new StringBuffer();
        char ch;
        String Tmp;
        int Num = 0;
        int Chk = 0;
        for (int j = 0; j < Str.length(); j++) {
            ch = Str.charAt(j);
            Tmp = "";
            Tmp = Tmp + ch;

            Chk = 0;
            for (int i = 0; i < NumList.length; i++) {
                if (NumList[i].equals(Tmp)) {
                    Chk++;
                }
            }
            if (Chk > 0) {
                Num++;
            }
        }
        if (Num == Str.length() && Str.length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String DataFull(String Str, int Len) {
        String ReturnStr = "";
        String AddStr = "";
        if (Len < Str.length()) {
            ReturnStr = Str.substring(0, Len - 1);
        } else {
            for (int i = 1; i <= (Len - Str.length()); i++) {
                AddStr = AddStr + " ";
            }
            ReturnStr = AddStr + Str.trim();
        }
        return ReturnStr;
    }

    

    public static String CurDate() {
        Date d = new Date();
        String StrDate;
        return StrDate = Dateft.format(d);
    }

    public static void ShowMsg(String StrMsg) {
        //JOptionPane jd = new JOptionPane() ;

        JOptionPane.showMessageDialog(null, StrMsg, "Show Message Dialog...", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void ShowError(String StrMsg) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), StrMsg, "Show Error Dialog...", JOptionPane.ERROR_MESSAGE);

    }

    public static void ShowMyMsg(java.awt.Component TComponent, String StrMsg) {
        JOptionPane.showMessageDialog(TComponent, StrMsg, "Show Error Dialog...", JOptionPane.ERROR_MESSAGE);

    }

    public static void ShowWaring(String StrMsg) {
        JOptionPane.showMessageDialog(null, StrMsg, "Show Waring Dialog...", JOptionPane.WARNING_MESSAGE);
    }

    public static TranRecord[] addArray(TranRecord[] MyArray) {
        int aSize = MyArray.length;
        TranRecord[] NewArray;
        NewArray = new TranRecord[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }
    public static CreditRec[] addCrArray(CreditRec[] MyArray) {
        int aSize = MyArray.length;
        CreditRec[] NewArray;
        NewArray = new CreditRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }
    public static CouponRec[] addCuArray(CouponRec[] CuArray) {
        int aSize = CuArray.length;
        CouponRec[] NewArray;
        NewArray = new CouponRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = CuArray[i];
        }
        CuArray = NewArray;
        return CuArray;

    }
    public static PluRec[] addPluArray(PluRec[] MyArray) {
        int aSize = MyArray.length;
        PluRec[] NewArray;
        NewArray = new PluRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }
    public static HourlyRec[] addHourlyArray(HourlyRec[] MyArray) {
        int aSize = MyArray.length;
        HourlyRec[] NewArray;
        NewArray = new HourlyRec[aSize + 1];
        for (int i = 0; i <= aSize - 1; i++) {
            NewArray[i] = MyArray[i];
        }
        MyArray = NewArray;
        return MyArray;

    }
    public static String ReadRefBillNo(String Macno) {
        String StrRefBillNo = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            //Load Data From PosConfigSetup ;
            String SQLQuery = "select *from poshwsetup where terminal='" + Macno + "'";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                ShowError("กรุณากำหนดค่าเริ่มต้นก่อนการใช้งาน !!!!");
            } else {
                //DecimalFormat Decfmt = new DecimalFormat("########") ;
                int Refbillno = rec.getInt("receno1");
                StrRefBillNo = Integer.toString(Refbillno);
            }
            rec.close();
        } catch (SQLException e) {
            ShowError(e.getMessage());
        }
        return PUtility.Addzero(StrRefBillNo, 7);
    }
  
    public static void ClearTempPromotion(String Table) {
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UpdateQry = "delete from temppromotion where tableonno=" + "'" + Table + "'";
            stmt.executeUpdate(UpdateQry);
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }

    }

    public static Double RoundDecimal(Double TempAmount, String RoundType) {
        Double ReturnVal = 0.0;
        Double decimal = DecimalChk(TempAmount);
        Double number = NumberChk(TempAmount);
        if (RoundType == "U") {
            if (decimal > 0) {
                ReturnVal = number + 1;
            } else {
                ReturnVal = number;
            }
        } else if (RoundType == "D") {
            ReturnVal = number;
        } else if (RoundType == "N") {
            if (decimal >= 0.50) {
                ReturnVal = number + 1;
            } else {
                ReturnVal = number;
            }
        } else if (RoundType == "F") {
            if (decimal >= 0.75) {
                if (decimal >= 0.87) {
                    ReturnVal = number + 1;
                } else {
                    ReturnVal = number + 0.75;
                }
            } else {
                if (decimal >= 0.50) {
                    if (decimal >= 0.63) {
                        ReturnVal = number + 0.75;
                    } else {
                        ReturnVal = number + 0.50;
                    }
                } else {
                    if (decimal >= 0.25) {
                        if (decimal >= 0.38) {
                            ReturnVal = number + 0.50;
                        } else {
                            ReturnVal = number + 0.25;
                        }
                    } else {
                        if (decimal >= 0.13) {
                            ReturnVal = number + 0.25;
                        } else {
                            ReturnVal = number;
                        }
                    }
                }
            }

        }
        return number;
    }

    public static Double NumberChk(Double TempAmount) {
        Double ReturnVal = 0.0;
        String TempNum = "";
        String TempDec = "";
        TempNum = TempAmount.toString();
        int index = TempNum.indexOf(".");
        if (index != -1) {
            TempDec = TempNum.substring(0, index);
            ReturnVal = Double.parseDouble(TempDec);
        } else {
            ReturnVal = 0.0;
        }
        return ReturnVal;
    }

    public static Double DecimalChk(Double TempAmount) {
        Double ReturnVal = 0.0;
        String TempNum = "";
        String TempDec = "";
        TempNum = TempAmount.toString();
        int index = TempNum.indexOf(".");
        if (index != -1) {
            TempDec = TempNum.substring(index + 1);
            ReturnVal = Double.parseDouble(TempDec);
        } else {
            ReturnVal = 0.0;
        }
        return ReturnVal;
    }

    public static String SeekGroupName(String TCode) {
        String ReturnValues = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UserGroupFile = "select *from groupfile where groupcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = rec.getString("groupname");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnValues;
    }
    public static String SeekCreditName(String TCode) {
        String ReturnValues = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UserGroupFile = "select *from creditfile where crcode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = rec.getString("crname");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnValues;
    }
     public static String SeekCuponName(String TCode) {
        String ReturnValues = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UserGroupFile = "select *from cupon where cucode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = rec.getString("cuname");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnValues;
    }

    public static String SeekArName(String TCode) {
        String ReturnValues = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UserGroupFile = "select *from custfile where sp_code='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "*****";
            } else {
                ReturnValues = rec.getString("sp_desc");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnValues;
    }

    public static String SeekRegNo(String TCode) {
        String ReturnValues = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UserGroupFile = "select *from poshwsetup where terminal='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {

                ReturnValues = "";
            } else {
                ReturnValues = rec.getString("macno");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnValues;
    }
    public static String SeekPromotionName(String TCode) {
        String ReturnValues = "";
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UserGroupFile = "select *from protab where procode='" + TCode + "'";
            ResultSet rec = stmt.executeQuery(UserGroupFile);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnValues = "*****";
            } else {
                ReturnValues = rec.getString("prodesc");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnValues;
    }

    public static Boolean SeekPromotion2(String Macno, String TCode) {
        boolean ReturnVal = false;
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String SeekPromotion2 = "select *from promorion2 where (macno= '" + Macno + "') and (pcode= '" + TCode + "')";
            ResultSet rec = stmt.executeQuery(SeekPromotion2);
            rec.first();
            if (rec.getRow() == 0) {
                ReturnVal = false;
            } else {
                ReturnVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        return ReturnVal;
    }

   
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.sql.Statement;
import utilities.MySQLConnect;
/**
 *
 * @author root
 */
public class CreditCard_Controller {

    MySQLConnect dbcon = null;

    public CreditCard_Controller() {
       dbcon = new MySQLConnect();
       dbcon.dbconnect();
    }

    public boolean insertCredit(String CrCode,String CrBank,String CrName,String CrGetCardNo,String CrCharge,String CrRedule) {
        Statement stmt = null;
        boolean isInsert = false;
        int rowInsert = 0;
        
        if (!seekCredit(CrCode)) {
            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = "INSERT INTO creditfile values " +
                             "('" + CrCode + "'" +",'" + CrBank + "'" +",'" + CrName + "' " + 
                             ",'" + CrGetCardNo + "'," + "" + CrCharge + "" +"," + CrRedule + ",'')";                     

                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);
                if (rowInsert > 0) {
                    isInsert = true;
                }
            } catch (Exception e) {
                e.printStackTrace();                
            }
            return isInsert;
        } else {//update มีอยู่แล้ว

            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = " UPDATE creditfile SET CrBank = '" + CrBank + "',CrName = '" + CrName + "'," +
                             " CrGetCardNo = '" + CrGetCardNo + "',CrCharge = " + CrCharge + ",CrRedule = " + CrRedule + " " +
                             " WHERE CrCode='"+CrCode+"'";

                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);
                if (rowInsert > 0) {
                    isInsert = true;
                    System.out.print("Message : update copon is error ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("Error : update cupon is error ");
            }
            return isInsert;
        }
    }

    public boolean seekCredit(String CrCode) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT * FROM creditfile WHERE CrCode='"+CrCode+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Found");
                rs.close();
                return true;
            } else {
                return false;
            }            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean removeCredit(String CrCode) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from creditfile Where CrCode = '" + CrCode + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;                
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }

        return isRemove;
    }
}

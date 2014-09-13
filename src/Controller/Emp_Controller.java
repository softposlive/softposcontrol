/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import utilities.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import softpos.program.ThaiUtil;

/**
 *
 * @author root
 */
public class Emp_Controller {

    MySQLConnect dbcon = null;

    public Emp_Controller() {
        dbcon = new MySQLConnect();
        dbcon.dbconnect();
    }

    public List getOptionDetail() {
        Statement stmt = null;
        ResultSet rs = null;
        List opt = new ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT * FROM employ;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[2];
                data[0] = rs.getString("code");
                data[1] = ThaiUtil.ASCII2Unicode(rs.getString("name"));
                opt.add(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return opt;
    }

    public String getEmpDetail(String code) {
        Statement stmt = null;
        ResultSet rs = null;
        String opt = "";
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "select * from employ Where code = '" + code + "';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                opt = ThaiUtil.ASCII2Unicode(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return opt;
    }

    public boolean insertWaiter(String code, String opt) {
        Statement stmt = null;
        boolean isInsert = false;
        int rowInsert = 0;
        if (!seekEmp(code)) {

            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = "insert into employ  values ('" + code + "','" + opt + "',0.00,'');";
                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);
                if (rowInsert > 0) {
                    isInsert = true;
                    System.out.print("@@@ is insert success @@@");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.print("Error : insert groupfile is error ");
            }
            return isInsert;
        } else {
            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = "update employ set name = '"+ opt + "' where code = '" + code + "' ;";
                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);
                if (rowInsert > 0) {
                    isInsert = true;
                    System.out.print("@@@ is update success @@@");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.print("Error : update groupfile is error ");
            }
            return isInsert;
        }
    }

    public boolean removeWaiter(String code) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from employ Where code = '" + code + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;
                System.out.print("Error : remove optionfile success ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.print("Error : remove optionfile error ");
        }

        return isRemove;
    }

    public boolean seekEmp(String code) {
        Statement stmt;
        ResultSet rs;       
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "select * from employ  where code = '" + code + "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

    }
}

package Controller;

import utilities.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import softpos.program.ThaiUtil;

public class OptionGroup_Controller {

    MySQLConnect dbcon = null;

    public OptionGroup_Controller() {
        dbcon = new MySQLConnect();
        dbcon.dbconnect();
    }

    public List getOptionDetail() {
        Statement stmt = null;
        ResultSet rs = null;
        List opt = new ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT * FROM groupfile g inner join optionfile o on g.groupcode = o.pgroup order by pgroup;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[3];
                data[0] = rs.getString("PGroup");
                data[1] = ThaiUtil.ASCII2Unicode(rs.getString("groupName"));
                data[2] = ThaiUtil.ASCII2Unicode(rs.getString("OptionName"));
                opt.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opt;
    }

    public List getOptionDetail(String code) {
        Statement stmt = null;
        ResultSet rs = null;
        List opt = new ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "select * from optionfile Where PGroup = '" + code + "';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[2];
                data[0] = rs.getString("PGroup");
                data[1] = ThaiUtil.ASCII2Unicode(rs.getString("OptionName"));
                opt.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opt;
    }

    public boolean insertOption(String code, String opt) {
        Statement stmt = null;
        boolean isInsert = false;
        int rowInsert = 0;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "insert into optionfile  values ('" + code + "','" + ThaiUtil.Unicode2ASCII(opt) + "');";
            System.out.print(sql);
            rowInsert = stmt.executeUpdate(sql);
            if (rowInsert > 0) {
                isInsert = true;
                System.out.print("@@@ is insert success @@@");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error : insert groupfile is error ");
        }
        return isInsert;
    }

    public boolean removeOptionDetail(String code, String name) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from optionfile Where PGroup = '" + code + "' and OptionName = '" + ThaiUtil.Unicode2ASCII(name) + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;
                System.out.print("Error : remove optionfile success ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error : remove optionfile error ");
        }

        return isRemove;
    }

    public String seekGroup(String code) {
        Statement stmt = null;
        ResultSet rs = null;
        String name = "";
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "select * from groupfile g where groupcode = '" + code + "';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                name = ThaiUtil.ASCII2Unicode(rs.getString("groupName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import utilities.MySQLConnect;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class MenuSetUp_Controller {

    MySQLConnect dbcon = null;

    public MenuSetUp_Controller() {
        dbcon = new MySQLConnect();
        dbcon.dbconnect();
    }

    public List getMenuList() {
        Statement stmt = null;
        ResultSet rs = null;
        List opt = new ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT m.*,p.pdesc,p.pprice11 FROM menulist m inner join product p on m.PluCode = p.pcode order by MenuCode,MenuItem;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[6];
                data[0] = rs.getString("MenuCode");
                data[1] = rs.getString("MenuItem");
                data[2] = rs.getString("PluCode");
                data[3] = rs.getString("MenuActive");
                data[4] = rs.getString("p.pdesc");
                data[5] = rs.getString("p.pprice11");
                opt.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opt;
    }

    public List getMenuItem() {
        Statement stmt = null;
        ResultSet rs = null;
        List opt = new ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT * FROM menulist   group by MenuCode order by MenuCode;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[2];
                data[0] = rs.getString("MenuCode");
                data[1] = rs.getString("MenuActive");
                opt.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opt;
    }

    public String[] getProductDesc(String plu) {
        Statement stmt = null;
        ResultSet rs = null;
        String[] desc = new String[2];
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT pdesc,pprice11 FROM product   where pcode = '" + plu + "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                desc[0] = rs.getString("pdesc");
                desc[1] = rs.getString("pprice11");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desc;
    }

    public boolean insertMenuList(String txt1, String txt2, String txt3, String act) {
        Statement stmt = null;
        boolean isInsert = false;
        int rowInsert = 0;
        if (!seekMenu(txt1, txt2)) {
            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = "insert into menulist  values ('" + txt1 + "','" + txt2 + "','" + txt3 + "','" + act + "');";
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
        } else {
            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = "UPDATE menulist SET  plucode = '" + txt3 + "',menuactive = '" + act + "' WHERE MenuCode = '" + txt1 + "' AND MenuItem = '" + txt2 + "'";
                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);
                if (rowInsert > 0) {
                    isInsert = true;
                    System.out.print("@@@ is update success @@@");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("Error : update groupfile is error ");
            }
            return isInsert;
        }
    }

    public boolean seekMenu(String code, String item) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "select * from menulist  where menucode = '" + code + "' and menuItem = '" + item + "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean removeMenuList(String menu, String menuItem) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from menulist Where menucode = '" + menu + "' and menuItem = '" + menuItem + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;
                System.out.print("remove menulist success ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error : remove menulist error ");
        }

        return isRemove;
    }

    public boolean updateMenuActive(String menu,String mode) {
        Statement stmt = null;
        boolean isupdate = false;
        int rowInsert = 0;
        String sql = "";
        
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            if(mode.equals("Y")){
                sql = "UPDATE menulist SET menuactive = 'Y' WHERE MenuCode = '" + menu + "'";           
            }else{
                sql = "UPDATE menulist SET menuactive = 'N' WHERE MenuCode = '" + menu + "'";           
            }                    
            rowInsert = stmt.executeUpdate(sql);
            if (rowInsert > 0) {
                isupdate = true;
                System.out.print("@@@ is update success @@@");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error : update menulist is error ");
        }
        return isupdate;
    }
}

package Controller;

import utilities.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import softpos.program.ThaiUtil;

public class Cupon_Controller {

    SimpleDateFormat dateFmtE = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat dateFmtT = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    MySQLConnect dbcon = null;

    public Cupon_Controller() {
        dbcon = new MySQLConnect();
        dbcon.dbconnect();
    }

    public List getCuponDetail() {
        Statement stmt = null;
        ResultSet rs = null;
        List opt = new ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT * FROM cupon order by cucode;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[19];
                data[0] = rs.getString("CuCode");
                data[1] = ThaiUtil.ASCII2Unicode(rs.getString("CuName"));
                data[2] = rs.getDate("CuBegin").toString();
                data[3] = rs.getDate("CuEnd").toString();
                data[4] = rs.getString("CuStrDay");
                data[5] = rs.getString("CuType");
                data[6] = rs.getString("CuADisc");
                data[7] = rs.getString("CuADiscBath");
                data[8] = rs.getString("CuBDisc");
                data[9] = rs.getString("CuBDiscBath");
                data[10] = rs.getString("CuDisc");
                data[11] = rs.getString("CuDiscBath");
                data[12] = rs.getString("ChkMember");
                opt.add(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return opt;
    }

    public List getCuponList(String cupon) {
        Statement stmt = null;
        ResultSet rs = null;
        List desc = new java.util.ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT product.pcode,pdesc,pprice11 FROM product INNER JOIN cuponlist ON product.pcode = cuponlist.PCode  where cucode = '" + cupon + "' order by product.pcode;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[3];
                data[0] = rs.getString("product.pcode");
                data[1] = rs.getString("pdesc");
                data[2] = rs.getString("pprice11");
                desc.add(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return desc;
    }

    public String[] addProductToCopon(String plu) {
        Statement stmt = null;
        ResultSet rs = null;
        String[] product = null;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "SELECT pcode,pdesc,pprice11 FROM product   where pcode = '" + plu + "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                product = new String[2];
                product[0] = rs.getString("pcode");
                product[1] = rs.getString("pdesc");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }

    public boolean insertCupon(CuponBean bean, int pluCode) {
        Statement stmt = null;
        boolean isInsert = false;
        int rowInsert = 0;
        Date date = new Date();
        Date end = new Date();
        try {
            date = dateFmtT.parse(bean.getCuBegin());
            end = dateFmtT.parse(bean.getCuEnd());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (!seekCupon(bean.getCucode())) {
            try {
                stmt = (Statement) MySQLConnect.con.createStatement();

                String sql = "insert into cupon  (cuCode,Cuname,CuBegin,CuEnd,CuStrDay,CuType,CuDisc,CuDiscBath,ChkMember) " +
                        "values ('" + bean.getCucode() + "'" +
                        ",'" + bean.getCuName() + "'" +
                        ",'" + dateFmtE.format(date) + "'" +
                        ",'" + dateFmtE.format(end) + "'," + "'" + bean.getCuStrDay() + "'" +
                        ",'" + bean.getCuType() + "'" +
                        "," + bean.getCuDisc() + "" +
                        "," + bean.getCuDiscBath() + "" +
                        ",'" + bean.getChkMember() + "');";

                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);

                if (rowInsert > 0) {
                    isInsert = true;
                    System.out.print("@@@ is insert success @@@");

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return isInsert;
        } else {//update มีอยู่แล้ว

            try {
                stmt = (Statement) MySQLConnect.con.createStatement();
                String sql = "UPDATE cupon  " +
                        "set cuName = '" + bean.getCuName() + "'," +
                        "CuBegin = '" + dateFmtE.format(date) + "'," +
                        "CuEnd = '" + dateFmtE.format(end) + "'," +
                        "CuStrDay = '" + bean.getCuStrDay() + "'," +
                        "CuType = '" + bean.getCuType() + "'," +
                        "CuDisc = " + bean.getCuDisc() + "," +
                        "CuDiscBath = " + bean.getCuDiscBath() + " "
                        + "where cuCode = '" + bean.getCucode() + "'";

                System.out.print(sql);
                rowInsert = stmt.executeUpdate(sql);
                if (rowInsert > 0) {
                    isInsert = true;
                    System.out.print("Message : update copon is error ");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return isInsert;
        }
    }

    public boolean seekCupon(String code) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "select * from cupon  where cucode = '" + code + "';";
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

    public boolean removeCupon(String code) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from cupon Where cucode = '" + code + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;
                System.out.print("remove cupon success ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return isRemove;
    }

    public boolean removeCuponList(String code) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from cuponlist Where cucode = '" + code + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;
                System.out.print("remove cupon success ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return isRemove;
    }

    public boolean removePluCuponList(String code) {
        Statement stmt = null;
        boolean isRemove = false;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            String sql = "delete from cuponlist Where pcode = '" + code + "';";
            int deleteRow = stmt.executeUpdate(sql);
            if (deleteRow > 0) {
                isRemove = true;
                System.out.print("remove cupon success ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return isRemove;
    }

    public boolean insertCuponList(String cocode, String pcode) {
        Statement stmt = null;
        boolean isupdate = false;
        int rowInsert = 0;
        String sql = "";

        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            sql = "insert into cuponlist values('" + cocode + "','" + pcode + "')";
            System.out.println(sql);
            rowInsert = stmt.executeUpdate(sql);
            if (rowInsert > 0) {
                isupdate = true;
                System.out.print("@@@ is insert cuponlist success @@@");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isupdate;
    }
}

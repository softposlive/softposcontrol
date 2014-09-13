
package Controller;

import utilities.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import softpos.program.ThaiUtil;

public class Promotion_Controller {

    SimpleDateFormat dateFmtE = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat dateFmtT = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    MySQLConnect dbcon = null;

    public Promotion_Controller() {
        dbcon = new MySQLConnect();
        dbcon.dbconnect();
    }

    public List getPromotionAll() {
        Date CurDate = new Date() ;
        String StrCurDate = dateFmtE.format(CurDate) ;
        Statement stmt;
        ResultSet rs = null;
        List pro = new java.util.ArrayList();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            //String sql = "SELECT * FROM protab where (pdate2>='"+StrCurDate+"') "+" order by ProCode;";
            String sql = "SELECT * FROM protab order by ProCode;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = new String[41];
                data[0] = rs.getString("ProCode");
                data[1] = ThaiUtil.ASCII2Unicode(rs.getString("ProDesc"));
                try {                    
                    data[2] = dateFmtT.format(rs.getDate("PDate1"));
                    data[3] = dateFmtT.format(rs.getDate("PDate2"));
                } catch (SQLException ex) {
                    data[2] = "00/00/0000";
                    data[3] = "00/00/0000";
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                data[4] = rs.getString("PStrDay");
                data[5] = rs.getString("PTime1S");
                data[6] = rs.getString("PTime1E");
                data[7] = rs.getString("PDisc1");
                data[8] = rs.getString("PSPDisc1");
                data[9] = rs.getString("PTS1");

                data[10] = rs.getString("PTime2S");
                data[11] = rs.getString("PTime2E");
                data[12] = rs.getString("PDisc2");
                data[13] = rs.getString("PSPDisc2");
                data[14] = rs.getString("PTS2");

                data[15] = rs.getString("PTime3S");
                data[16] = rs.getString("PTime3E");
                data[17] = rs.getString("PDisc3");
                data[18] = rs.getString("PSPDisc3");
                data[19] = rs.getString("PTS3");
                data[20] = rs.getString("PType");
                
                data[21] = rs.getString("PSale1");
                data[22] = rs.getString("PFree1");
                data[23] = rs.getString("PSum1");
                data[24] = rs.getString("PDiscFree1");
                data[25] = rs.getString("PSale41");
                data[26] = rs.getString("PFree41");
                
                data[27] = rs.getString("PSale2");
                data[28] = rs.getString("PFree2");
                data[29] = rs.getString("PSum2");
                data[30] = rs.getString("PDiscFree2");
                data[31] = rs.getString("PSale42");
                data[32] = rs.getString("PFree42");
                
                data[33] = rs.getString("PSale3");
                data[34] = rs.getString("PFree3");
                data[35] = rs.getString("PSum3");
                data[36] = rs.getString("PDiscFree3");
                data[37] = rs.getString("PSale43");
                data[38] = rs.getString("PFree43");
                
                pro.add(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }
}

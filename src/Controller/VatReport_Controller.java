/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import utilities.MySQLConnect;
/**
 *
 * @author root
 */
public class VatReport_Controller {

    MySQLConnect dbcon = null;
    private SimpleDateFormat dateFmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private SimpleDateFormat dateFmtE = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");
    private BigDecimal totalVat = new  BigDecimal(0.00);
    private BigDecimal totalNVat = new  BigDecimal(0.00);
    private BigDecimal Vat = new  BigDecimal(0.00);
    MathContext mc = new MathContext(2,RoundingMode.HALF_UP);

    public VatReport_Controller() {
       dbcon = new MySQLConnect();
       dbcon.dbconnect();
    }
    
    public List<String[]> getCreditCardDetail(String startDate,String endDate) {
        Statement stmt = null;
        ResultSet rs = null;
        List data = new ArrayList();
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            d1 = dateFmtShow.parse(startDate);
            d2 = dateFmtShow.parse(endDate);
            String sql = "SELECT * FROM s_vat Where TDate Between '"+dateFmtE.format(d1)+"' and '"+dateFmtE.format(d2)+"'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] detail = new String[8];                
                try {
                    detail[0] = dateFmtShow.format(rs.getDate("TDate")); 
                } catch (Exception e) {
                    detail[0] = "00/00/0000"; 
                }
                detail[1] = rs.getString("Terminal"); 
                detail[2] = rs.getString("MacNo");
                detail[3] = rs.getString("StBill");
                detail[4] = rs.getString("SpBill");
                try {
                    detail[5] = doubleFmt.format(rs.getFloat("NetVat"));                    
                } catch (Exception e) {
                    detail[5] = rs.getString("NetVat");
                }
                try {
                    detail[6] = doubleFmt.format(rs.getFloat("NetNonVat"));
                } catch (Exception e) {
                    detail[6] = rs.getString("NetNonVat");
                }
                try {
                    detail[7] = doubleFmt.format(rs.getFloat("Vat"));
                } catch (Exception e) {
                    detail[7] = rs.getString("Vat");
                }       
                 
                this.totalVat = totalVat.add(new BigDecimal(rs.getDouble("NetVat"))); 
                this.totalNVat = totalNVat.add(new BigDecimal(rs.getDouble("NetNonVat")));
                this.Vat = Vat.add(new BigDecimal(rs.getDouble("Vat")));
                data.add(detail); 
                
            }          
        } catch (Exception e) {
            e.printStackTrace();
           
        }        
        return data;
    }
public BigDecimal[] getSumTotal(){
    BigDecimal[] sumVat = {totalVat,totalNVat,Vat};
    return  sumVat;
}
   public static void main(String argv[]) {
     VatReport_Controller d = new VatReport_Controller();
     d.getCreditCardDetail("01/07/1009", "31/07/2009");
  }
}

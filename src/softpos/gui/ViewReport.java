package softpos.gui;


import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.util.JRLoader;
import utilities.MySQLConnect;

public final class ViewReport {

    private Statement stmt;
    DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");
    SimpleDateFormat TFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat EFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private String sqlCompany = "SELECT * FROM company ";
    private String sqlBranch = "SELECT * FROM branch ";
    private String companyName = "";
    private String branchName = "";
    private String branchCode = "";
    private String tax = "";
        
    public ViewReport() {
        getCompany();
        getBranch();
    }

    public void printReportRJ(String date) {
        try {
            stmt.close();
            Map parameters = new HashMap();
            parameters.put("branchName", branchCode + " " + branchName);
            parameters.put("companyName", companyName);
            parameters.put("date", date);

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/reportfiles/sur_RJ.jasper"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, MySQLConnect.con);
            int pageSize = jasperPrint.getPages().size();
            if (pageSize > 0) {
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");
            } else {
                JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
            }
        } catch (Exception e) {
        }

    }

    public void printReportVat(String time, String date1, String date2) {
        String condition = " TDate Between '"+convertDate(date1)+"' and '"+convertDate(date2)+"'";         
        try {
            Map parameters = new HashMap();
            parameters.put("branchName", branchCode + " " + branchName);
            parameters.put("companyName", companyName);
            parameters.put("Tax", tax);
            parameters.put("date", time);
            parameters.put("condition", condition);

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/reportfiles/viewReportVat.jasper"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, MySQLConnect.con);
            int pageSize = jasperPrint.getPages().size();
            if (pageSize > 0) {
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");
            } else {
                JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void printReportRepSaleByTime(String time,String time2, String date1, String date2) {             
        try {
            Map parameters = new HashMap();
            parameters.put("branchName", branchCode + " " + branchName);
            parameters.put("companyName", companyName);
            parameters.put("Tax", tax);
            parameters.put("date", time);
            parameters.put("date2", time2);
            parameters.put("time1", date1);
            parameters.put("time2", date2);

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/reportfiles/RepSaleByTime_Report.jasper"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, MySQLConnect.con);
            int pageSize = jasperPrint.getPages().size();
            if (pageSize > 0) {
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");
            } else {
                JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void printReportRepSaleByMonth(String time) {             
        try {
            Map parameters = new HashMap();
            parameters.put("branchName", branchCode + " " + branchName);
            parameters.put("companyName", companyName);
            parameters.put("Tax", tax);
            parameters.put("date", time);            

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/reportfiles/RepSaleByMonth_Report.jasper"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, MySQLConnect.con);
            int pageSize = jasperPrint.getPages().size();
            if (pageSize > 0) {
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");
            } else {
                JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void printReportRepSaleandlost(String time,String[] header) {             
        try {
            Map parameters = new HashMap();
            parameters.put("branchName", branchCode + " " + branchName);
            parameters.put("companyName", companyName);
            parameters.put("Tax", tax);
            parameters.put("condition", time);   
            parameters.put("h1", header[0]);   
            parameters.put("h2", header[1]);   
            parameters.put("h3", header[2]);   
            parameters.put("h4", header[3]);   
            parameters.put("h5", header[4]);   
            parameters.put("h6", header[5]);   
            parameters.put("h7", header[6]);   

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/reportfiles/RepSaleandlost_Report.jasper"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, MySQLConnect.con);
            int pageSize = jasperPrint.getPages().size();
            if (pageSize > 0) {
                JasperViewer v = new JasperViewer(jasperPrint, false);
                JDialog j = new JDialog(new JFrame(), true);
                j.setTitle("Print");
                j.setSize(1024, 768);
                j.getContentPane().add(v.getContentPane());
                j.setLocationRelativeTo(null);
                j.setVisible(true);
                v.setTitle("Report...");
            } else {
                JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลที่ต้องการพิมพ์");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getCompany() {
        try {
            ResultSet rs2 = stmt.executeQuery(sqlCompany);
            if (rs2 != null) {
                if (rs2.next()) {
                    companyName = rs2.getString("Name");
                    tax = rs2.getString("tax");
                }
                rs2.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBranch() {
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sqlBranch);
            if (rs != null) {
                if (rs.next()) {
                    branchCode = rs.getString("Code");
                    branchName = rs.getString("Name");
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String convertDate(String convert) {
        String output = "";
        try {
            Date date = new Date();
            date = TFmt.parse(convert);
            output = EFmt.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    void printCompile() {
        String condition = " TDate Between '2010-10-01' and '2010-10-01'";
        try {
            Map parameters = new HashMap();
            parameters.put("branchName", branchCode + " " + branchName);
            parameters.put("companyName", companyName);
            parameters.put("Tax", tax);
            parameters.put("date", "");
            parameters.put("condition", condition);

            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/reportfiles/viewReportVat.jasper"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, MySQLConnect.con);
            jasperPrint.getPages().size();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
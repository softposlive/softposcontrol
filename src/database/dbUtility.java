package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Connection;

public class dbUtility {

    public static Connection con;
    public static String HostName;
    public static String DbName;
    public static String UserName;
    public static String Password;
    public static String PortNumber;
    public static String Char_Set;

    public void dbconnect() {
        try {
            getDbVar();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + HostName + ":" + PortNumber + "/" + DbName+"?characterEncoding=utf-8", UserName, Password);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Connection Error !!!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void getDbVar() {

        File fObject = new File("C:/softpos/connect.ini");
        if (!fObject.canRead()) {
            JOptionPane.showMessageDialog(null, "กรุณากำหนด File dbconnect.ini") ;
            System.exit(0);
        } else {
            try {
                boolean eof = false;
                String Macno = "";
                FileReader file = new FileReader("C:/spapplication/dbconfig/dbconnect.ini");
                BufferedReader buff = new BufferedReader(file);

                while (!eof) {
                    String line = buff.readLine();
                    if (line == null) {
                        eof = true;
                    } else {
                        if (!line.equals("")) {
                            String TempStr = line;
                            if (TempStr.substring(0, 1).equals("server")) {
                                HostName = TempStr.substring(2);
                            }
                            if (TempStr.substring(0, 1).equals("database")) {
                                DbName = TempStr.substring(2);
                            }
                            if (TempStr.substring(0, 1).equals("user")) {
                                UserName = TempStr.substring(2);
                            }
                            if (TempStr.substring(0, 1).equals("pass")) {
                                Password = TempStr.substring(2);
                            }
                            if (TempStr.substring(0, 1).equals("port")) {
                                PortNumber = TempStr.substring(2);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    /*
    HostName  = "localhost" ;
    DbName = "mycpssysutf" ;
    UserName = "root" ;
    Password = "" ;
    PortNumber = "3306" ;
     */
    }
}

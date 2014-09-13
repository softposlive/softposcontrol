package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnect {

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
            con = DriverManager.getConnection("jdbc:mysql://" + HostName + ":" + PortNumber + "/" + DbName, UserName, Password);   
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Connection Error !!!", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Connection Error !!!", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void getDbVar() {
        try {
            FileInputStream fs = new FileInputStream("C:/softpos/connect.ini");
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));
            String tmp;  
            while ((tmp = br.readLine()) != null)   {
                String []data = tmp.split(",", tmp.length());
                if(data[0].equalsIgnoreCase("server")){
                    HostName = data[1];
                }else if(data[0].equalsIgnoreCase("database")){
                    DbName = data[1];
                }else if(data[0].equalsIgnoreCase("user")){
                    UserName = data[1];
                }else if(data[0].equalsIgnoreCase("pass")){
                    Password = data[1];
                }else if(data[0].equalsIgnoreCase("port")){
                    PortNumber = data[1];
                }else if(data[0].equalsIgnoreCase("charset")){
                    Char_Set = data[1];
                }else if(data[0].equalsIgnoreCase("macno")){
                }else if(data[0].equalsIgnoreCase("language")){
                }
            }  
            br.close();
            ds.close();
            fs.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static ResultSet getResultSet(String sql) throws Exception {
        if(con==null){
            return null;
        }else{
            return getStatement().executeQuery(sql);
        }
    }
    
    public static int exeUpdate(String sql) throws Exception {
        if(con==null){
            return -1;
        }else{
            return con.createStatement().executeUpdate(sql);
        }
    }
    
    public static Statement getStatement() throws Exception {
        if(con==null){
            return null;
        }else{
            return con.createStatement();
        }
    }

    public static PreparedStatement getPreparedStatement(String sql) throws Exception {
        if(con==null){
            return null;
        }else{
            return con.prepareStatement(sql);
        }
    }
}

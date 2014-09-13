/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import directory_utility.DirectoryUtility;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtility_bak {

    FTPClient ftp;
    Frame frame = new Frame();
    JLabel lb;
    JProgressBar pb;
    String server;
    String user;
    String pass;
    int port = 21;

    public void setProgress(JLabel lb, JProgressBar pb) {
        this.lb = lb;
        this.pb = pb;
        this.pb.setMaximum(100);
        this.pb.setMinimum(0);
        this.pb.setStringPainted(true);
    }

    public boolean connect(String server, String user, String pass, int port) {
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.port = port;

        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(
                new PrintWriter(System.out)));

        ftp.setDefaultPort(port);
        // Sets the timeout in milliseconds to use when reading from the data connection.
        // 15 sec.
        ftp.setDataTimeout(1000 * 15);

        try {
            //JOptionPane.showMessageDialog(frame, "FTP server ทำการเชื่อมต่อ.\n");
            int reply;
            ftp.connect(server);

            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                // FTP server refused connection.
                JOptionPane.showMessageDialog(frame, "FTP server ปฏิเสธการเชื่อมต่อ.\n");
                return false;

            } else {
                // FTP server agree connection.;            
            }

        } catch (NoRouteToHostException e) {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException f) {
                }
            // FTP Server not found.
            }
            JOptionPane.showMessageDialog(frame, "ไม่พบ FTP Server : " + server);

            e.printStackTrace();
            return false;
        } catch (UnknownHostException e) {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException f) {
                }
            // Unknow FTP Server.
            }
            JOptionPane.showMessageDialog(frame, "ไมรู้จัก FTP Server : " + server);

            e.printStackTrace();
            return false;
        } catch (ConnectException e) {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException f) {
                }            // Error IOException.
            //JOptionPane.showMessageDialog(frame, "Connection refused\n" +
            //        "1.Check use port");
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage()+" ConnectException..");
            return false;
        } catch (SocketException e) {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(null, f.getMessage()+" SocketException..");
                }            // Error IOException.
            }
            JOptionPane.showMessageDialog(frame, "เกิดปัญหาการเชื่อมต่อ กรุณาตรวจเช็ค! " +
                    "การเชื่อมต่ออินเตอร์เน็ต หรือ ระบบเน็ตเวิค ของท่าน!!!");

            e.printStackTrace();
            return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()+" IOException..");
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException f) {                    
                }            // Error IOException.
            }
            JOptionPane.showMessageDialog(frame, "เกิดปัญหาการเชื่อมต่อ กรุณาตรวจเช็ค! " +
                    "การเชื่อมต่ออินเตอร์เน็ต หรือ ระบบเน็ตเวิค ของท่าน");

            e.printStackTrace();
            return false;
        }

        try {
            // Connection with user and password...
            if (!ftp.login(user, pass)) {

                ftp.logout();

                // Username or Password not correct!
                JOptionPane.showMessageDialog(frame, "Username หรือ Password ไม่ถูกต้อง!");

                // reset values
                ftp = null;
                this.server = null;
                this.user = null;
                this.pass = null;
                this.port = 21;
                return false;
            } else {
                // Complete connection.
                //JOptionPane.showMessageDialog(frame, "Complete connect FTP server that remote system is " + ftp.getSystemName());    
            }

        } catch (Exception e) {
            // unknow Exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Exception error");
            return false;
        }
        // Complete connection.
        return true;
    }

    public void disconnect() {
        if (ftp != null) {
            try {
                ftp.logout();
                ftp.disconnect();
                ftp = null;
                this.server = null;
                this.user = null;
                this.pass = null;
                this.port = 21;
            } catch (Exception ex) {
            }
        } else {
            System.err.println("FTP NullPointerException.");
        //JOptionPane.showMessageDialog(this, "FTP NullPointerException");
        }
    }
    
    public static void main(String[] args) {
        String data = "12";
        String []datas = data.split("/");
        for(String d:datas){
            System.err.println(d);
        }
    }
    
    public boolean checkFileExists(String server, String user, String pass, int port, String remoteFile){
        boolean fileExists = false;
        try {
            connect(server, user, pass, port);
            String []file = ftp.listNames("/");
            int count = 0;
            for(String f:file){
                count++;
                String []name = f.split("/",f.length());
                String nameExists = name[name.length-1];
                if(nameExists.equalsIgnoreCase(remoteFile)){
                    fileExists = true;
                    break;
                }else{
                    if(count==file.length){
                        fileExists = false;
                    }else{
                        continue;
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            fileExists = false;
        }
        
        return fileExists;
    }
    
    public boolean upload1(String localFile, String remoteFile) {
        InputStream input = null;
        OutputStream output = null;
        boolean success = false;
        try {
            if (true) {
                ftp.setFileType(FTP.BINARY_FILE_TYPE);            // Use passive mode as default because most of us are
            // behind firewalls these days.
            }
            ftp.enterLocalPassiveMode();
            //ftp.enterLocalActiveMode();

            // output is ftp server.
            // input is my computer.
            File f = new File(localFile);
            if (!f.exists()) {
                JOptionPane.showMessageDialog(frame, "Can not find in My computer : " + f.getPath());
                return false;
            }
            input = new FileInputStream(localFile);
            output = ftp.storeFileStream(remoteFile);

            if (output == null) {
                JOptionPane.showMessageDialog(frame, "Cannot upload to FTP Server. \nCannot find the path specified.  : " + remoteFile);
                return false;
            }

            byte[] buffer = new byte[1024];
            int len;

            f = new File(localFile);
            long max = f.length();
            long use = 0;

            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
                output.flush();
                use += len;

                pb.setValue((int) ((use * 100) / max));
                lb.setText(use + "/" + max + " Byte");
            }

            pb.setValue(100);
            lb.setText(use + "/" + max + " Byte");

            // Complete upload file.
            if(max==use){
                success = true;
            }

            if (input != null) {
                try {
                    input.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }            // Complete upload method
            }
            return success;

        } catch (Exception e) {
            // Have Error.
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "เกิดปัญหาการเชื่อมต่อ กรุณาตรวจเช็ค" +
                    "การเชื่อมต่ออินเตอร์เน็ต หรือ ระบบเน็ตเวิค ของท่าน");

            if (input != null) {
                try {
                    input.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean download1(String remoteFile, String localFile) {
        //JOptionPane.showMessageDialog(frame, "Download : " + remoteFile +" >>> " + localFile);

        OutputStream output = null;
        InputStream input = null;
        boolean success = false;
        pb.setValue(0);
        try {

            long max = 0;
            try {
                FTPFile[] files = ftp.listFiles(remoteFile);
                System.out.println("Number of files in dir: " + files.length);

                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i].getSize() / 1024.0 + " KByte");
                    max = files[i].getSize();
                }
                if (max == 0) {
                    //JOptionPane.showMessageDialog(frame, remoteFile + " not Found in FTP Server." );
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, remoteFile + " Have Error!! ");
                return false;
            }

            if (true) {
                ftp.setFileType(FTP.BINARY_FILE_TYPE);            // Use passive mode as default because most of us are
            // behind firewalls these days.
            //ftp.enterLocalPassiveMode();
            }
            ftp.enterLocalActiveMode();

            DirectoryUtility du = new DirectoryUtility();
            File localF = du.getFileAndCreateDir(localFile);

            // output is my computer.
            // input is ftp server.
            //JOptionPane.showMessageDialog(frame, "Download updateProgressBa   r: " + (input != null));
            output = new FileOutputStream(localF);
            input = ftp.retrieveFileStream(remoteFile);



            //long max = 0;
            long use = 0;
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
                output.flush();

                use += len;

                pb.setValue((int) ((use * 100) / max));
                lb.setText(use + "/" + max + " Byte");

            }

            pb.setValue(100);
            lb.setText(use + "/" + max + " Byte");

            // Complete download.
            success = true;

            if (input != null) {
                try {
                    input.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }            // Complete download method.            
            }
            return success;

        } catch (Exception e) {
            // Have Error.
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "เกิดปัญหาการเชื่อมต่อ กรุณาตรวจเช็ค" +
                    "การเชื่อมต่ออินเตอร์เน็ต หรือ ระบบเน็ตเวิค ของท่าน");

            if (input != null) {
                try {
                    input.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        }
    }
}

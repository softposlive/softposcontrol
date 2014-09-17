package other;

import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * This class is used to upload a file to a FTP server.
 * 
 * @author Muthu
 */
public class FTPUtility {
    FTPClient ftp;
    Frame frame = new Frame();
    JLabel lb;
    JProgressBar pb;
    String server;
    String user;
    String pass;
    int port = 21;
    
    /**
     * Upload a file to a FTP server. A FTP URL is generated with the
     * following syntax:
     * ftp://user:password@host:port/filePath;type=i.
     * 
     * @param ftpServer , FTP server address (optional port ':portNumber').
     * @param user , Optional user name to login.
     * @param password , Optional password for user.
     * @param fileName , Destination file name on FTP server (with optional
     *            preceding relative path, e.g. "myDir/myFile.txt").
     * @param source , Source file to upload.
     * @throws MalformedURLException, IOException on error.
     */
    public boolean upload(String ftpServer, String user, String password,
            String fileName, File source) throws MalformedURLException,
            IOException {
        boolean uploadSuccess = false;
        
        if (ftpServer != null && fileName != null && source != null) {
            StringBuffer sb = new StringBuffer("ftp://");
            // check for authentication else assume its anonymous access.
            if (user != null && password != null) {
                sb.append(user);
                sb.append(':');
                sb.append(password);
                sb.append('@');
            }
            sb.append(ftpServer);
            sb.append('/');
            sb.append(fileName);
            /*
             * type ==> a=ASCII mode, i=image (binary) mode, d= file directory
             * listing
             */
            sb.append(";type=i");

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                URL url = new URL(sb.toString());
                URLConnection urlc = url.openConnection();

                bos = new BufferedOutputStream(urlc.getOutputStream());
                bis = new BufferedInputStream(new FileInputStream(source));

                int i;
                // read byte by byte until end of stream
                this.pb.setIndeterminate(true);
                this.pb.setString("waitting...");
                while ((i = bis.read()) != -1) {
                    bos.write(i);
                }
                this.pb.setIndeterminate(false);
                this.pb.setString("");
                uploadSuccess = true;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        } else {
            uploadSuccess = false;
            System.out.println("Input not available.");
        }
        return uploadSuccess;
    }

    /**
     * Download a file from a FTP server. A FTP URL is generated with the
     * following syntax:
     * ftp://user:password@host:port/filePath;type=i.
     * 
     * @param ftpServer , FTP server address (optional port ':portNumber').
     * @param user , Optional user name to login.
     * @param password , Optional password for user.
     * @param fileName , Name of file to download (with optional preceeding
     *            relative path, e.g. one/two/three.txt).
     * @param destination , Destination file to save.
     * @throws MalformedURLException, IOException on error.
     */
    public boolean download(String ftpServer, String user, String password,
            String fileName, File destination) throws MalformedURLException,
            IOException {
        boolean downloadSuccess = false;
        
        if (ftpServer != null && fileName != null && destination != null) {
            StringBuffer sb = new StringBuffer("ftp://");
            // check for authentication else assume its anonymous access.
            if (user != null && password != null) {
                sb.append(user);
                sb.append(':');
                sb.append(password);
                sb.append('@');
            }
            sb.append(ftpServer);
            sb.append('/');
            sb.append(fileName);
            /*
             * type ==> a=ASCII mode, i=image (binary) mode, d= file directory
             * listing
             */
            sb.append(";type=i");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                URL url = new URL(sb.toString());
                URLConnection urlc = url.openConnection();

                bis = new BufferedInputStream(urlc.getInputStream());
                bos = new BufferedOutputStream(new FileOutputStream(
                        destination.getName()));

                int i;
                this.pb.setIndeterminate(true);
                this.pb.setString("waitting...");
                while ((i = bis.read()) != -1) {
                    bos.write(i);
                }
                this.pb.setIndeterminate(false);
                this.pb.setString("");
                downloadSuccess = true;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        } else {
            downloadSuccess = false;
            System.out.println("Input not available");
        }
        return downloadSuccess;
    }
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

        //} catch (NoRouteToHostException e) {
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
}

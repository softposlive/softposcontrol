package softpos.gui;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test {
    public static void main(String[] args) {
//        SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        FTPClient ftp = new FTPClient();
//        FTPFile []f;
//        try {
//            ftp.connect("122.155.1.203");
//            ftp.login("tobranch", "tobranch");
//            f = ftp.listFiles();
//            for(FTPFile fp:f){
//                System.out.println(fp.getName()+"="+simp.format(fp.getTimestamp().getTime()));
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Date d1 = new Date();
//        Date d2 = new Date();
//        System.out.println(d1.compareTo(d2));
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss",Locale.ENGLISH);
        SimpleDateFormat simp2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.ENGLISH);
        File file = new File("C:/spapplication/posmasterfile/111_masterl.zip");
        Date date = new Date(file.lastModified());
        System.out.println(date);
        System.out.println(simp.format(new Date()));
    }
}

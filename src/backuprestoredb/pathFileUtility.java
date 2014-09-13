package backuprestoredb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class pathFileUtility {

    public static String pathBackUp = "";
    public static String pathDateConfig = "";

    public void pathFileUtility() {
    }

    public boolean getPathFileConfig() {
        File fObject = new File("");
        if (!fObject.canRead()) {
            JOptionPane.showMessageDialog(null, "ไม่พบไฟล์สำหรับ อ่านค่า Config กรุณาตรวจสอบ") ;
            return false;
        } else {
            try {
                boolean eof = false;                
                FileReader file = new FileReader("");
                BufferedReader buff = new BufferedReader(file);

                while (!eof) {
                    String line = buff.readLine();
                    if (line == null) {
                        eof = true;
                    } else {
                        if (!line.equals("")) {
                            String TempStr = line;
                            if (TempStr.substring(0, 1).equals("1")) {
                                pathBackUp = TempStr.substring(2);
                            }
                            if (TempStr.substring(0, 1).equals("2")) {
                                pathDateConfig = TempStr.substring(2);
                            }

                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return true;
        }
    }
}

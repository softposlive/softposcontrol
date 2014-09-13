/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class Directory_Files_Controller {
    public String ReadLogFile(String local){
        File fObject = new File(local);
        String dayOfbackup = "0";
        if (!fObject.canRead()) {
            JOptionPane.showMessageDialog(null, "กรุณากำหนด File dbconnect.ini") ;
            System.exit(0);
        } else {
            try {
                boolean eof = false;
                String Macno = "";
                FileReader file = new FileReader(fObject.getPath());
                BufferedReader buff = new BufferedReader(file);

                while (!eof) {
                    String line = buff.readLine();
                    if (line == null) {
                        eof = true;
                    } else {
                        if (!line.equals("")) {                           
                            dayOfbackup = line;
                        }
                    }
                }
            } catch (Exception e) {
                
            }
        }
        return dayOfbackup;
     }
}

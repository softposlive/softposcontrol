/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;


import java.awt.Frame;
import javax.swing.UIManager;

public class LookAndFeelFrame extends Frame {

    public LookAndFeelFrame() {
        try {
            UIManager.installLookAndFeel("JGoodies Plastic 3D",
                    "com.jgoodies.plaf.plastic.Plastic3DLookAndFeel");
            //UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticLookAndFeel");
            UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.Plastic3DLookAndFeel");
        } catch (Throwable t) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
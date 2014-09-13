/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class JProgressBarSetValue extends JFrame {
  JProgressBar bar = new JProgressBar();
  JButton step = new JButton("Step");
    private JDialog dialog;

  public JProgressBarSetValue() {
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    step.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent e) {
//        int value = bar.getValue() + 7;
//        if (value > bar.getMaximum()) {
//          value = bar.getMaximum();
//        }
//        bar.setValue(value);
//      }
//    });
//
//    getContentPane().add(bar, BorderLayout.NORTH);
//    getContentPane().add(step, BorderLayout.EAST);
//    pack();
//    setVisible(true);
    JProgressBar pb = new JProgressBar(0,100);
pb.setPreferredSize(new Dimension(175,20));
pb.setString("Working");
pb.setStringPainted(true);
pb.setValue(0);

 

JLabel label = new JLabel("Progress: ");


//Then the label and progress bar are added to the panel:

JPanel center_panel = new JPanel();
center_panel.add(label);
center_panel.add(pb);


 

dialog = new JDialog((JFrame)null, "Working ...");
dialog.getContentPane().add(center_panel, BorderLayout.CENTER);
dialog.pack();
dialog.setVisible(true);


 

dialog.setLocationRelativeTo(null); // center on screen
dialog.setLocation(550,25); // position by coordinates
dialog.toFront(); // raise above other java windows
 pb.setValue(25);



dialog.dispose();


//To get more creative, one can add a button to the dialog, which will can close the dialog when pressed. The action is defined by adding an action listener to the button. Here we dynamically add the button, but it could be done at the beginning.

JButton button = new JButton("Done");
button.setActionCommand("done");
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt){
        if ( evt.getActionCommand().equals("done") ) {
            dialog.dispose();
        }
    }
});


//The button is added to a new JPanel object:

JPanel page_end_panel = new JPanel();
page_end_panel.add(button);


//Then the new panel is added to the dialog, which will make it appear immediately:

dialog.getContentPane().add(page_end_panel, BorderLayout.PAGE_END);


//It's not required, but if you wish to force the user to hit the button, the dialog can be made "modal", which will cause it to act like a JOptionPane. It seems that the visibility of the dialog must be toggled for the change in modality to take effect:

dialog.setModal(true);
dialog.pack();
dialog.setVisible(false);
dialog.setVisible(true);
  }

  public static void main(String arg[]) {
    int min = 0; 
    int max = 100;
    JProgressBar progress = new JProgressBar(min, max);

    // Play animation
    progress.setIndeterminate(true);
  }
}
/*
 


 */
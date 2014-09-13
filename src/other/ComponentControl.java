/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author root
 */
public class ComponentControl {
    private JTextPane txtPane;
    public void setTextPane(JTextPane txtPane){
        this.txtPane = txtPane;
    }
    
    //---Control TextPaneInfo
    public void addTextPane(String initString,String initStyle) {

        StyledDocument doc = txtPane.getStyledDocument();
        addStylesToDocument(doc);

        try {

            doc.insertString(doc.getLength(), initString, doc.getStyle(initStyle));

        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

    }

    public void addStylesToDocument(StyledDocument doc) {
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "Norasi");

        Style s = doc.addStyle("black", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.BLACK);
        StyleConstants.setFontSize(s, 15);

        s = doc.addStyle("orange", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.ORANGE);
        StyleConstants.setFontSize(s, 14);

        s = doc.addStyle("blue", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.BLUE);
        StyleConstants.setFontSize(s, 14);

        s = doc.addStyle("red", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.RED);
        StyleConstants.setFontSize(s, 14);

        s = doc.addStyle("magenta", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.MAGENTA);
        StyleConstants.setFontSize(s, 14);

    }
}

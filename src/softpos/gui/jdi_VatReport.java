package softpos.gui;

/*
 * jdi_VatReport.java
 *
 * Created on August 9, 2009, 4:13 PM
 */

import Controller.VatReport_Controller;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import utilities.DateChooser.DateChooseDialog;

/**
 *
 * @author  root
 */
public class jdi_VatReport extends javax.swing.JDialog {

    DefaultTableModel model;

    public jdi_VatReport(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //try {
        //    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        initComponents();
        setTableModel();
        txt1.setText(dateFmtShow.format(new Date()));
        txt2.setText(dateFmtShow.format(new Date()));
    }

    private void setTableModel() {
        model = (DefaultTableModel) tblvat.getModel();
        tblvat.setShowGrid(true);
        tblvat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblvat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblvat.setRowSelectionAllowed(true);
        tblvat.setShowGrid(true);
        tblvat.setBackground(java.awt.Color.WHITE);
        tblvat.setGridColor(Color.gray);

        JTableHeader header = tblvat.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));
        tblvat.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
        tblvat.setRowHeight(25);
        int colsize = model.getColumnCount();
        int[] width = {120, 100, 190, 100, 100, 120, 120, 120};
        for (int i = 0; i < colsize; i++) {
            tblvat.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
        }
        TableColumnModel tcm = tblvat.getColumnModel();
        DefaultTableCellRenderer d;

        d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(SwingConstants.LEADING);
        tcm.getColumn(2).setCellRenderer(d);

        d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(0).setCellRenderer(d);
        tcm.getColumn(1).setCellRenderer(d);
        tcm.getColumn(3).setCellRenderer(d);
        tcm.getColumn(4).setCellRenderer(d);

        d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(5).setCellRenderer(d);
        tcm.getColumn(6).setCellRenderer(d);
        tcm.getColumn(7).setCellRenderer(d);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt1 = new javax.swing.JFormattedTextField();
        txt2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();
        cmdProcess = new javax.swing.JButton();
        cmdPrint = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblvat = new javax.swing.JTable();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานภาษีมูลค่าเพิ่ม (Vat)");

        jPanel1.setBackground(new java.awt.Color(156, 236, 211));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jLabel2.setText("กำหนดช่วงวันที่");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N

        txt1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txt1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
        });

        txt2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txt2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
        });

        cmdDateChoose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose1.setFocusable(false);
        cmdDateChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jLabel1.setText("ถึง");

        cmdDateChoose2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

        cmdProcess.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        cmdProcess.setText("F5-ประมวลผล");
        cmdProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProcessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdProcess))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cmdPrint.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        cmdPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.gif"))); // NOI18N
        cmdPrint.setText("พิมพ์");
        cmdPrint.setFocusable(false);
        cmdPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrintActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.gif"))); // NOI18N
        jButton3.setText("ออก");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdPrint)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setVerticalAlignment(0);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(145, 147, 145), 2));

        tblvat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "วันที่ (Date)", "เลขเครื่อง", "รหัสสรรพากร D", "บิลเริ่มต้น", "บิลสิ้นสุด", "ยอดขาย Vat", "ยอดขาย NonVat", "ภาษี Vat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblvat);

        txt3.setBackground(new java.awt.Color(175, 251, 209));
        txt3.setEditable(false);
        txt3.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txt3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt3.setText("0.00");
        txt3.setFocusable(false);
        txt3.setRequestFocusEnabled(false);

        txt4.setBackground(new java.awt.Color(175, 251, 209));
        txt4.setEditable(false);
        txt4.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txt4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt4.setText("0.00");
        txt4.setFocusable(false);
        txt4.setRequestFocusEnabled(false);

        txt5.setBackground(new java.awt.Color(175, 251, 209));
        txt5.setEditable(false);
        txt5.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txt5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt5.setText("0.00");
        txt5.setFocusable(false);
        txt5.setRequestFocusEnabled(false);

        jLabel3.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jLabel3.setText("จำนวนรายการ");

        txt6.setBackground(new java.awt.Color(175, 251, 209));
        txt6.setEditable(false);
        txt6.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        txt6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt6.setText("0");
        txt6.setFocusable(false);
        txt6.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabel2.setVerticalAlignment(0);

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
// TODO add your handling code here:
    cmdDateChoose1();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
// TODO add your handling code here:
    cmdDateChoose2();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

private void txt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyPressed
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_F1) {
        cmdDateChoose1();
    }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        dispose();
    }
}//GEN-LAST:event_txt1KeyPressed

private void txt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_F1) {
        cmdDateChoose2();
    }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        txt1.requestFocus();
    }
}//GEN-LAST:event_txt2KeyPressed

private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        if (txt1.getText().length() > 0) {
            txt2.requestFocus();
        }
    }
}//GEN-LAST:event_txt1KeyReleased

private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased

    if (txt2.getText().length() > 0) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cmdProcess.requestFocus();
        }

    }

}//GEN-LAST:event_txt2KeyReleased

private void cmdProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProcessActionPerformed
    if (validateForm()) {
        int cnt = model.getRowCount();
        if(cnt>0){
            for(int i =0;i<cnt;i++){
                model.removeRow(0); 
            }
        }
        cmdLoadVatReport();
    }
}//GEN-LAST:event_cmdProcessActionPerformed

private void cmdPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrintActionPerformed
    if (validateForm()) {
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();
        ViewReport vat = new ViewReport();
        String time = txt1.getText()+" ถึง "+txt2.getText();
        vat.printReportVat(time, txt1.getText(), txt2.getText());        
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }
}//GEN-LAST:event_cmdPrintActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_jButton3ActionPerformed
    private boolean validateForm() {
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            d1 = dateFmtShow.parse(txt1.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "วันทีไม่ถูกต้อง");
            txt1.requestFocus();
            txt1.selectAll();
            return false;
        }
        try {
            d2 = dateFmtShow.parse(txt2.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "วันทีไม่ถูกต้อง");
            txt2.requestFocus();
            txt2.selectAll();
            return false;
        }
        if (d2.compareTo(d1) < 0) {
            JOptionPane.showMessageDialog(this, "วันทีไม่ถูกต้อง");
            txt2.requestFocus();
            txt2.selectAll();
            return false;
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jdi_VatReport dialog = new jdi_VatReport(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JButton cmdPrint;
    private javax.swing.JButton cmdProcess;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblvat;
    private javax.swing.JFormattedTextField txt1;
    private javax.swing.JFormattedTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    // End of variables declaration//GEN-END:variables
    SimpleDateFormat dateFmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat dateFmtE = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat NumberFmt = new DecimalFormat("##,###,##0");

    private void cmdDateChoose1() {
        Point point = cmdDateChoose2.getLocation();
        point.setLocation(point.getX(), point.getY());
        DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
        dcd.setVisible(true);
        // dcd.showDialog(new LookAndFeelFrame(), true, point);
        if (dcd.getSelectDate() != null) {
            txt1.setText(dateFmtShow.format(dcd.getSelectDate().getTime()));
        }
        txt1.requestFocus();
    }

    private void cmdDateChoose2() {
        Point point = cmdDateChoose2.getLocation();
        point.setLocation(point.getX(), point.getY());
        DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
        dcd.setVisible(true);
        if (dcd.getSelectDate() != null) {
            txt2.setText(dateFmtShow.format(dcd.getSelectDate().getTime()));
        }
        txt2.requestFocus();
    }

    private void cmdLoadVatReport() {
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();
        VatReport_Controller ctrl = new VatReport_Controller();
        List<String[]> data = ctrl.getCreditCardDetail(txt1.getText(), txt2.getText());
        String[] detail = null;
        for (int i = 0; i < data.size(); i++) {
            detail = data.get(i);
            model.addRow(detail);
        }
        BigDecimal[] sumVat = ctrl.getSumTotal();
        txt3.setText(doubleFmt.format(sumVat[0]));
        txt4.setText(doubleFmt.format(sumVat[1]));
        txt5.setText(doubleFmt.format(sumVat[2]));
        txt6.setText(NumberFmt.format(model.getRowCount()));
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }
}

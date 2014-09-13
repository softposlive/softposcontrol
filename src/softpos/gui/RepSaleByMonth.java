package softpos.gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import utilities.MySQLConnect;

public class RepSaleByMonth extends javax.swing.JDialog {

    SimpleDateFormat DateTimeFmt = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    SimpleDateFormat FileDateFmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    SimpleDateFormat YearFmt = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat FullTimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    DefaultTableModel model2;
    String Day1;
    String Day2;
    Date TempDate1;
    Date TempDate2;
    Date TempDate3;
    Date TempDate4;
    ArrayList<RjrepRecord> RjArray = new ArrayList<RjrepRecord>();
    RjrepRecord RjRec2 = new RjrepRecord();
    private String time = "";
    /** Creates new form RepSaleByTime */
    public RepSaleByMonth(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //try {
        //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        Font myfont = new Font("Norasi", Font.PLAIN, 14);
        UIManager.put("Label.font", myfont);
        UIManager.put("Button.font", myfont);
        initComponents();
        model2 = (DefaultTableModel) ShowTableLogin.getModel();
        ShowTableLogin.setShowGrid(true);
        ShowTableLogin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ShowTableLogin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ShowTableLogin.setRowSelectionAllowed(true);
        ShowTableLogin.setShowGrid(true);
        ShowTableLogin.setShowHorizontalLines(true);
        ShowTableLogin.setShowVerticalLines(true);
        ShowTableLogin.setGridColor(Color.black);

        JTableHeader header = ShowTableLogin.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

        int[] ColSize = {50, 70, 200, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
        for (int i = 0; i < 16; i++) {
            //int vColIndex = 0;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }

        DecimalFormat DoubleFmt = new DecimalFormat("##,###,##0.00");
        DecimalFormat IntegerFmt = new DecimalFormat("##,###,##0");
        DecimalFormat PersentFmt = new DecimalFormat("#,##0%");

        TableColumnModel tcm = ShowTableLogin.getColumnModel();

        TableTestFormatRenderer r = new TableTestFormatRenderer(IntegerFmt);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(3).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(4).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(5).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(6).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(7).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(8).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(9).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(10).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(11).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(12).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(13).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(14).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(15).setCellRenderer(r);



        txtDate1.setText(YearFmt.format(date));
        ClearTempRj();
        txtDate1.requestFocus();
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
        txtDate1 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        c1 = new javax.swing.JCheckBox();
        c2 = new javax.swing.JCheckBox();
        c3 = new javax.swing.JCheckBox();
        c4 = new javax.swing.JCheckBox();
        c5 = new javax.swing.JCheckBox();
        c6 = new javax.swing.JCheckBox();
        c7 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTableLogin = new javax.swing.JTable();
        bntOK = new javax.swing.JButton();
        bntPrint = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        bntPrint1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานยอดการขายประจำปี");
        setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงข้อมูลที่ (1)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 14))); // NOI18N

        txtDate1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy"))));
        txtDate1.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDate1ActionPerformed(evt);
            }
        });
        txtDate1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDate1FocusLost(evt);
            }
        });
        txtDate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate1KeyPressed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        c1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c1.setSelected(true);
        c1.setText("วันอาทิตย์");
        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });

        c2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c2.setSelected(true);
        c2.setText("วันจันทร์");

        c3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c3.setSelected(true);
        c3.setText("วันอังคาร");

        c4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c4.setSelected(true);
        c4.setText("วันพุธ");

        c5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c5.setSelected(true);
        c5.setText("วันพฤหัสฯ");

        c6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c6.setSelected(true);
        c6.setText("วันศุกร์");

        c7.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c7.setSelected(true);
        c7.setText("วันเสาร์");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c1)
                    .addComponent(c5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(c6)
                        .addGap(18, 18, 18)
                        .addComponent(c7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(c2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c3)
                        .addGap(18, 18, 18)
                        .addComponent(c4)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c1)
                    .addComponent(c2)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(c3)
                        .addComponent(c4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c6)
                    .addComponent(c5)
                    .addComponent(c7)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ShowTableLogin.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        ShowTableLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "DEPT", "Description", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม", "ยอดรวม (Total)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ShowTableLogin.setOpaque(false);
        ShowTableLogin.setRowHeight(25);
        ShowTableLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShowTableLoginKeyPressed(evt);
            }
        });
        ShowTableLogin.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                ShowTableLoginVetoableChange(evt);
            }
        });
        jScrollPane1.setViewportView(ShowTableLogin);

        bntOK.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntOK.setText("ประมวลผล (Process)");
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bntOKMouseReleased(evt);
            }
        });
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntPrint.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntPrint.setText("พิมพ์ (Print)");
        bntPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bntPrintMouseReleased(evt);
            }
        });
        bntPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntExit.setText("ESC-ออก (Exit)");
        bntExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bntExitMouseReleased(evt);
            }
        });
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntPrint1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntPrint1.setText("Calc File");
        bntPrint1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bntPrint1MouseReleased(evt);
            }
        });
        bntPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrint1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(414, 414, 414)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntExit, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bntPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bntPrint1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                            .addComponent(bntOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(bntOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        setSize(new java.awt.Dimension(1024, 778));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void ShowTableLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShowTableLoginKeyPressed
//int SelectedRow = GetSelectedRow() ;
    //if (ShowTableLogin.getValueAt(SelectedRow, 0).equals("T0")) {
    //      ShowTableLogin.enableInputMethods(true);
    //} else {
    //    ShowTableLogin.enableInputMethods(false);
    //    
    // }
}//GEN-LAST:event_ShowTableLoginKeyPressed

private void ShowTableLoginVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_ShowTableLoginVetoableChange
}//GEN-LAST:event_ShowTableLoginVetoableChange

private void bntOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntOKMouseReleased
    
}//GEN-LAST:event_bntOKMouseReleased

private void bntPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintActionPerformed
// TODO add your handling code here:
    bntPrintClick();
}//GEN-LAST:event_bntPrintActionPerformed

private void bntExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntExitMouseReleased
    
}//GEN-LAST:event_bntExitMouseReleased

private void bntPrintMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntPrintMouseReleased
// TODO add your handling code here:
    
}//GEN-LAST:event_bntPrintMouseReleased

private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_c1ActionPerformed

private void txtDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }

}//GEN-LAST:event_txtDate1KeyPressed

private void txtDate1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDate1FocusLost
}//GEN-LAST:event_txtDate1FocusLost

private void txtDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDate1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtDate1ActionPerformed

private void bntPrint1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntPrint1MouseReleased
// TODO add your handling code here:
}//GEN-LAST:event_bntPrint1MouseReleased

private void bntPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrint1ActionPerformed
    int cnt = model2.getRowCount();
    if(cnt>0){
        cmdExportToExcel();
    }
}//GEN-LAST:event_bntPrint1ActionPerformed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    // TODO add your handling code here:
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    // TODO add your handling code here:
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed
    public void bntExitClick() {
        this.dispose();
    }

    public void bntOKClick() {
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();

        ArrayList<RjrepRecord> RjArray = new ArrayList<RjrepRecord>();
        RjrepRecord RjRec2 = new RjrepRecord();
        ClearTempRj();
        String Year = txtDate1.getText();
        Day1 = GetDay1();
        String RjNo = "";
        Double Total = 0.0;
        String Month = "";
        setDaySelect();
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select rjno,sum(total),month(tdate) from rjfile where (year(tdate) ='" + Year + "') " +
                    "and (locate(dayofweek(tdate),'" + Day1 + "')>0) group by rjno,month(tdate) order by rjno,month(tdate)";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                int RowCount = model2.getRowCount();
                for (int i = 0; i <= RowCount - 1; i++) {
                    model2.removeRow(0);
                }
            } else {
                do {
                    RjNo = rec.getString("rjno");
                    Month = rec.getString("month(tdate)");
                    Total = rec.getDouble("sum(total)");
                    int RjSize = RjArray.size();
                    boolean found = false;
                    for (int i = 0; i < RjSize; i++) {
                        if (RjArray.get(i).Rjno.equals(RjNo)) {
                            System.out.println(RjRec2.Rjno + "   " + RjNo);
                            found = true;
                            if (Month.equals("1")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac1 = RjRec2.mac1 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("2")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac2 = RjRec2.mac2 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("3")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac3 = RjRec2.mac3 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("4")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac4 = RjRec2.mac4 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("5")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac5 = RjRec2.mac5 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("6")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac6 = RjRec2.mac6 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("7")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac7 = RjRec2.mac7 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("8")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac8 = RjRec2.mac8 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("9")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac9 = RjRec2.mac9 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("10")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac10 = RjRec2.mac10 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("11")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac11 = RjRec2.mac11 + Total;
                                RjArray.set(i, RjRec2);
                            }
                            if (Month.equals("12")) {
                                RjRec2 = RjArray.get(i);
                                RjRec2.mac12 = RjRec2.mac12 + Total;
                                RjArray.set(i, RjRec2);
                            }
                        }
                    }
                    if (!found) {
                        RjrepRecord RjRec = new RjrepRecord();
                        RjRec.Rjno = RjNo;
                        RjRec.Month = Month;

                        if (Month.equals("1")) {
                            RjRec.mac1 = Total;
                        }
                        if (Month.equals("2")) {
                            RjRec.mac2 = Total;
                        }
                        if (Month.equals("3")) {
                            RjRec.mac3 = Total;
                        }
                        if (Month.equals("4")) {
                            RjRec.mac4 = Total;
                        }
                        if (Month.equals("5")) {
                            RjRec.mac5 = Total;
                        }
                        if (Month.equals("6")) {
                            RjRec.mac6 = Total;
                        }
                        if (Month.equals("7")) {
                            RjRec.mac7 = Total;
                        }
                        if (Month.equals("8")) {
                            RjRec.mac8 = Total;
                        }
                        if (Month.equals("9")) {
                            RjRec.mac9 = Total;
                        }
                        if (Month.equals("10")) {
                            RjRec.mac10 = Total;
                        }
                        if (Month.equals("11")) {
                            RjRec.mac11 = Total;
                        }
                        if (Month.equals("12")) {
                            RjRec.mac12 = Total;
                        }
                        RjArray.add(RjRec);
                    }

                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        UpdateTempRj1(RjArray);

        LoadDataToGrid();
        ShowTableLogin.requestFocus();
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }

    public void UpdateTempRj1(ArrayList<RjrepRecord> RjArray) {
        int RjSize = RjArray.size();
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertQuery = "update temprjrep set mac1=?,mac2=?,mac3=?,mac4=?,mac5=?,mac6=?,mac7=?,mac8=?,mac9=?,mac10=?,mac11=?,mac12=?,mac13=? where rjno=? ";
            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
            for (int i = 0; i < RjSize; i++) {
                prm.setDouble(1, RjArray.get(i).mac1);
                prm.setDouble(2, RjArray.get(i).mac2);
                prm.setDouble(3, RjArray.get(i).mac3);
                prm.setDouble(4, RjArray.get(i).mac4);
                prm.setDouble(5, RjArray.get(i).mac5);
                prm.setDouble(6, RjArray.get(i).mac6);
                prm.setDouble(7, RjArray.get(i).mac7);
                prm.setDouble(8, RjArray.get(i).mac8);
                prm.setDouble(9, RjArray.get(i).mac9);
                prm.setDouble(10, RjArray.get(i).mac10);
                prm.setDouble(11, RjArray.get(i).mac11);
                prm.setDouble(12, RjArray.get(i).mac12);
                prm.setDouble(13, RjArray.get(i).mac1 + RjArray.get(i).mac2 + RjArray.get(i).mac3 + RjArray.get(i).mac4 +
                        RjArray.get(i).mac5 + RjArray.get(i).mac6 + RjArray.get(i).mac7 + RjArray.get(i).mac8 + RjArray.get(i).mac9 +
                        RjArray.get(i).mac10 + RjArray.get(i).mac11 + RjArray.get(i).mac12);
                prm.setString(14, RjArray.get(i).Rjno);

                prm.executeUpdate();
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void UpdateTempRj2(String TRjNo, Double TTotal) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertQuery = "update temprjrep set mac2=? where rjno=? ";
            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
            prm.setDouble(1, TTotal);
            prm.setString(2, TRjNo);
            prm.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void UpdateTempRj3(String TRjNo, Double mac3, Double mac4) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertQuery = "update temprjrep set mac3=?,mac4=? where rjno=? ";
            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
            prm.setDouble(1, mac3);
            prm.setDouble(2, mac4);
            prm.setString(3, TRjNo);
            prm.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void ClearTempRj() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String sqlDel = "delete from temprjrep";
            String InsertQuery = "insert into temprjrep(rjline, rjno, dept, tdesc) select rjline, rjno, dept, tdesc from surrj;";
            stmt.executeUpdate(sqlDel);
            stmt.executeUpdate(InsertQuery);
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void LoadDataToGrid() {

        //LoadData To SurRj

        try {
            Statement stmt2 = MySQLConnect.con.createStatement();
            String LoadTableFile2 = "select *from temprjrep order by rjline";
            ResultSet rec2 = stmt2.executeQuery(LoadTableFile2);
            Date dt = new Date();
            int RowCount = model2.getRowCount();
            for (int i = 0; i <= RowCount - 1; i++) {
                model2.removeRow(0);
            }
            rec2.first();
            if (rec2.getRow() == 0) {
            } else {
                do {
                    //JOptionPane.showMessageDialog(this, rec2.getInt("rjline"))  ;
                    Object[] input = {
                        rec2.getString("rjno"),
                        rec2.getString("dept"),
                        rec2.getString("tdesc"),
                        rec2.getDouble("mac1"),
                        rec2.getDouble("mac2"),
                        rec2.getDouble("mac3"),
                        rec2.getDouble("mac4"),
                        rec2.getDouble("mac5"),
                        rec2.getDouble("mac6"),
                        rec2.getDouble("mac7"),
                        rec2.getDouble("mac8"),
                        rec2.getDouble("mac9"),
                        rec2.getDouble("mac10"),
                        rec2.getDouble("mac11"),
                        rec2.getDouble("mac12"),
                        rec2.getDouble("mac13")
                    };
                    model2.addRow(input);
                } while (rec2.next());
                RowCount = model2.getRowCount();
                showCell(0, 0);
            }
            rec2.close();
            stmt2.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }

    }

    public void showCell(int row, int column) {
        Rectangle rect = ShowTableLogin.getCellRect(row, column, true);
        ShowTableLogin.scrollRectToVisible(rect);
        ShowTableLogin.clearSelection();
        ShowTableLogin.setRowSelectionInterval(row, row);
    }

    public String GetDay1() {
        String day = "";
        if (c1.isSelected()) {
            day += ",1";
        }
        if (c2.isSelected()) {
            day += ",2";
        }
        if (c3.isSelected()) {
            day += ",3";
        }
        if (c4.isSelected()) {
            day += ",4";
        }
        if (c5.isSelected()) {
            day += ",5";
        }
        if (c6.isSelected()) {
            day += ",6";
        }
        if (c7.isSelected()) {
            day += ",7";
        }
        return day;
    }

    public void bntPrintClick() {
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();
        ViewReport view = new ViewReport();//12/09  
        String date = "ประจำปี "+txtDate1.getText()+" ( "+time+ ")";
         
        view.printReportRepSaleByMonth(date);
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                RepSaleByMonth dialog = new RepSaleByMonth(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable ShowTableLogin;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntPrint;
    private javax.swing.JButton bntPrint1;
    private javax.swing.JCheckBox c1;
    private javax.swing.JCheckBox c2;
    private javax.swing.JCheckBox c3;
    private javax.swing.JCheckBox c4;
    private javax.swing.JCheckBox c5;
    private javax.swing.JCheckBox c6;
    private javax.swing.JCheckBox c7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtDate1;
    // End of variables declaration//GEN-END:variables

    public class TableTestFormatRenderer extends DefaultTableCellRenderer {

        private Format formatter;

        public TableTestFormatRenderer(Format formatter) {
            if (formatter == null) {
                throw new NullPointerException();
            }
            this.formatter = formatter;
        }

        protected void setValue(Object obj) {
            setText(obj == null ? "" : formatter.format(obj));
        }
    }

    private void cmdExportToExcel() {
        JFileChooser chooser = new JFileChooser();
        File file = null;
        DecimalFormat IntegerFmt = new DecimalFormat("##,###,##0");
        

        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = new File(chooser.getSelectedFile().getPath() + ".xls");
            try {
                HSSFWorkbook wb = new HSSFWorkbook();
// Make a worksheet in the XL document created
                HSSFSheet sheet = wb.createSheet();
// Create row at index zero ( Top Row)
                HSSFRow row = sheet.createRow((short) 0);
                HSSFRow row2 = sheet.createRow((short) 1);
                // sheet.addMergedRegion(new Region(0,(short)1,0,(short)5));
// Create a cell at index zero ( Top Left)
                HSSFCell cell10 = row.createCell((short) 0);               
                HSSFCell cell15 = row.createCell((short) 15);

                HSSFCell h1 = row2.createCell((short) 0);
                HSSFCell h2 = row2.createCell((short) 1);
                HSSFCell h3 = row2.createCell((short) 2);
                HSSFCell h4 = row2.createCell((short) 3);
                HSSFCell h5 = row2.createCell((short) 4);
                HSSFCell h6 = row2.createCell((short) 5);
                HSSFCell h7 = row2.createCell((short) 6);
                HSSFCell h8 = row2.createCell((short) 7);
                HSSFCell h9 = row2.createCell((short) 8);
                HSSFCell h10 = row2.createCell((short) 9);
                HSSFCell h11 = row2.createCell((short) 10);
                HSSFCell h12 = row2.createCell((short) 11);
                HSSFCell h13 = row2.createCell((short) 12);
                HSSFCell h14 = row2.createCell((short) 13);
                HSSFCell h15 = row2.createCell((short) 14);
                HSSFCell h16 = row2.createCell((short) 15);


// Lets make the cell a string type
                cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
                
                h1.setCellType(HSSFCell.CELL_TYPE_STRING);
                h2.setCellType(HSSFCell.CELL_TYPE_STRING);
                h3.setCellType(HSSFCell.CELL_TYPE_STRING);
                h4.setCellType(HSSFCell.CELL_TYPE_STRING);
                h5.setCellType(HSSFCell.CELL_TYPE_STRING);
                h6.setCellType(HSSFCell.CELL_TYPE_STRING);
                h7.setCellType(HSSFCell.CELL_TYPE_STRING);
                h8.setCellType(HSSFCell.CELL_TYPE_STRING);
                h9.setCellType(HSSFCell.CELL_TYPE_STRING);
                h10.setCellType(HSSFCell.CELL_TYPE_STRING);
                h11.setCellType(HSSFCell.CELL_TYPE_STRING);
                h12.setCellType(HSSFCell.CELL_TYPE_STRING);
                h13.setCellType(HSSFCell.CELL_TYPE_STRING);
                h14.setCellType(HSSFCell.CELL_TYPE_STRING);
                h15.setCellType(HSSFCell.CELL_TYPE_STRING);
                h16.setCellType(HSSFCell.CELL_TYPE_STRING);

// Type some content           
                HSSFCellStyle cellStyle0 = wb.createCellStyle();
                //cellStyle0.setFillPattern(HSSFCellStyle.BIG_SPOTS);
                cellStyle0.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cellStyle0.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                HSSFCellStyle cellStyle = wb.createCellStyle();
                cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
                cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
                cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //sheet.addMergedRegion(new Region(0,(short)1,0,(short)5));
                HSSFCellStyle cellStyle2 = wb.createCellStyle();
                cellStyle2.setFillForegroundColor(HSSFColor.WHITE.index);
                cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStyle2.setBottomBorderColor(HSSFColor.BLACK.index);
                cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStyle2.setLeftBorderColor(HSSFColor.BLACK.index);
                cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cellStyle2.setRightBorderColor(HSSFColor.BLACK.index);
                cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStyle2.setTopBorderColor(HSSFColor.BLACK.index);
                cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                HSSFCellStyle cellStyle3 = wb.createCellStyle();
                cellStyle3.setFillForegroundColor(HSSFColor.WHITE.index);
                cellStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStyle3.setBottomBorderColor(HSSFColor.BLACK.index);
                cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStyle3.setLeftBorderColor(HSSFColor.BLACK.index);
                cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cellStyle3.setRightBorderColor(HSSFColor.BLACK.index);
                cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStyle3.setTopBorderColor(HSSFColor.BLACK.index);

                HSSFCellStyle cellStyle4 = wb.createCellStyle();
                cellStyle4.setFillForegroundColor(HSSFColor.WHITE.index);
                cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStyle4.setBottomBorderColor(HSSFColor.BLACK.index);
                cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStyle4.setLeftBorderColor(HSSFColor.BLACK.index);
                cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cellStyle4.setRightBorderColor(HSSFColor.BLACK.index);
                cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStyle4.setTopBorderColor(HSSFColor.BLACK.index);
                cellStyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                
                HSSFCellStyle cellStyleh = wb.createCellStyle();
                cellStyleh.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cellStyleh.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStyleh.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStyleh.setBottomBorderColor(HSSFColor.BLACK.index);
                cellStyleh.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStyleh.setLeftBorderColor(HSSFColor.BLACK.index);
                cellStyleh.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cellStyleh.setRightBorderColor(HSSFColor.BLACK.index);
                cellStyleh.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStyleh.setTopBorderColor(HSSFColor.BLACK.index);
                cellStyleh.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                String date = "";
                if(c1.isSelected()){
                    date += "อาทิตย์";
                }
                if(c2.isSelected()){
                    date += " จันทร์";
                }
                if(c3.isSelected()){
                    date += " อังคาร";
                }
                if(c4.isSelected()){
                    date += " พุธ";
                }
                if(c5.isSelected()){
                    date += " พฤหัสบดี";
                }
                if(c6.isSelected()){
                    date += " ศุกร์";
                }
                if(c7.isSelected()){
                    date += " เสาร์";
                }
                if(!date.equals("")){
                    String temp = date;
                    date = "แสดงวัน "+temp;
                }
                cell10.setCellValue("รายงานยอดขายประจำปี "+txtDate1.getText() +" " +date);
                 

                h1.setCellValue("No");
                h2.setCellValue("Dept");
                h3.setCellValue("Description");
                h4.setCellValue("มกราคม");
                h5.setCellValue("กุมภาพันธ์");
                h6.setCellValue("มีนาคม");
                h7.setCellValue("เมษายน");
                h8.setCellValue("พฤษภาคม");
                h9.setCellValue("มิถุนายน");
                h10.setCellValue("กรกฏาคม");
                h11.setCellValue("สิงหาคม");
                h12.setCellValue("กันยายน");
                h13.setCellValue("ตุลาคม");
                h14.setCellValue("พฤศจิกายน");
                h15.setCellValue("ธันวาคม");
                h16.setCellValue("ยอดรวม");
                 

                cell10.setCellStyle(cellStyleh);
                cell15.setCellStyle(cellStyleh);
                
               
                sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 15));

                h1.setCellStyle(cellStyle);
                h2.setCellStyle(cellStyle);
                h3.setCellStyle(cellStyle);
                h4.setCellStyle(cellStyle);
                h5.setCellStyle(cellStyle);
                h6.setCellStyle(cellStyle);
                h7.setCellStyle(cellStyle);
                h8.setCellStyle(cellStyle);
                h9.setCellStyle(cellStyle);
                h10.setCellStyle(cellStyle);
                h11.setCellStyle(cellStyle);
                h12.setCellStyle(cellStyle);
                h13.setCellStyle(cellStyle);
                h14.setCellStyle(cellStyle);
                h15.setCellStyle(cellStyle);
                h16.setCellStyle(cellStyle);
                

                int cnt = model2.getRowCount();
                for (int i = 0; i < cnt; i++) {
                    HSSFRow rows = sheet.createRow((short) i + 2);
                    HSSFCell c1 = rows.createCell((short) 0);
                    HSSFCell c2 = rows.createCell((short) 1);
                    HSSFCell c3 = rows.createCell((short) 2);
                    HSSFCell c4 = rows.createCell((short) 3);
                    HSSFCell c5 = rows.createCell((short) 4);
                    HSSFCell c6 = rows.createCell((short) 5);
                    HSSFCell c7 = rows.createCell((short) 6);
                    HSSFCell c8 = rows.createCell((short) 7);
                    HSSFCell c9 = rows.createCell((short) 8);
                    HSSFCell c10 = rows.createCell((short) 9);
                    HSSFCell c11 = rows.createCell((short) 10);
                    HSSFCell c12 = rows.createCell((short) 11);
                    HSSFCell c13 = rows.createCell((short) 12);
                    HSSFCell c14 = rows.createCell((short) 13);
                    HSSFCell c15 = rows.createCell((short) 14);
                    HSSFCell c16 = rows.createCell((short) 15);
                   
                    c1.setCellType(HSSFCell.CELL_TYPE_STRING);
                    c2.setCellType(HSSFCell.CELL_TYPE_STRING);
                    c3.setCellType(HSSFCell.CELL_TYPE_STRING);
                    c4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c15.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c16.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

                    //DecFmt

                    c1.setCellValue((String) model2.getValueAt(i, 0));
                    c2.setCellValue((String) model2.getValueAt(i, 1));
                    c3.setCellValue((String) model2.getValueAt(i, 2));
                    c4.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 3))); 
                    c5.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 4)));
                    c6.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 5)));
                    c7.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 6)));
                    c8.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 7)));
                    c9.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 8)));
                    c10.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 9)));
                    c11.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 10)));
                    c12.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 11)));
                    c13.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 12)));
                    c14.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 13)));
                    c15.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 14)));
                    c16.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 15)));
                   // c17.setCellValue((String) model2.getValueAt(i, 16));



                    c1.setCellStyle(cellStyle2);
                    c2.setCellStyle(cellStyle2);
                    c3.setCellStyle(cellStyle3);
                    c4.setCellStyle(cellStyle4);
                    c5.setCellStyle(cellStyle4);
                    c6.setCellStyle(cellStyle4);
                    c7.setCellStyle(cellStyle4);
                    c8.setCellStyle(cellStyle4);
                    c9.setCellStyle(cellStyle4);
                    c10.setCellStyle(cellStyle4);
                    c11.setCellStyle(cellStyle4);
                    c12.setCellStyle(cellStyle4);
                    c13.setCellStyle(cellStyle4);
                    c14.setCellStyle(cellStyle4);
                    c15.setCellStyle(cellStyle4);
                    c16.setCellStyle(cellStyle4);
 
                }
                HSSFCellStyle cellStylelastrow = wb.createCellStyle();
                cellStylelastrow.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                cellStylelastrow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                cellStylelastrow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStylelastrow.setBottomBorderColor(HSSFColor.BLACK.index);
                cellStylelastrow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStylelastrow.setLeftBorderColor(HSSFColor.BLACK.index);
                cellStylelastrow.setBorderRight(HSSFCellStyle.BORDER_THIN);
                cellStylelastrow.setRightBorderColor(HSSFColor.BLACK.index);
                cellStylelastrow.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStylelastrow.setTopBorderColor(HSSFColor.BLACK.index);
                cellStylelastrow.setAlignment(HSSFCellStyle.ALIGN_CENTER);                
                
                
                 HSSFRow lastrow = sheet.createRow((short) cnt + 2);
                HSSFCell lastrowc0 = lastrow.createCell((short) 0);               
                HSSFCell lastrowc26 = lastrow.createCell((short) 15);                


                lastrowc0.setCellStyle(cellStylelastrow);                
                lastrowc26.setCellStyle(cellStylelastrow);
                sheet.addMergedRegion(new Region(cnt + 2, (short) 0, cnt + 2, (short) 15));
                
                //set width
                sheet.setColumnWidth((short) 0, (short) ((50 * 2) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 1, (short) ((50 * 2) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 2, (short) ((50 * 8) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 3, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 4, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 5, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 6, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 7, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 8, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 9, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 10, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 11, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 12, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 13, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 14, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 15, (short) ((50 * 4) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 16, (short) ((50 * 4) / ((double) 1 / 20)));
  

                FileOutputStream fOut = new FileOutputStream(file);
                wb.write(fOut);
                fOut.flush();
                fOut.close();
                JOptionPane.showMessageDialog(null, "Create Excel File successful");

            } catch (Exception e) {
                System.out.println("!!BANG!! xlCreate() : " + e);
                JOptionPane.showMessageDialog(null, "Create Excel File Error");
            }
        }
    }
     private void setDaySelect() {
        String time1 = "";
        if (c1.isSelected()) {
            time1 += "อาทิตย์";
        }
        if (c2.isSelected()) {
            time1 += " จันทร์";
        }
        if (c3.isSelected()) {
            time1 += " อังคาร";
        }
        if (c4.isSelected()) {
            time1 += " พุธ";
        }
        if (c5.isSelected()) {
            time1 += " พฤหัสบดี";
        }
        if (c6.isSelected()) {
            time1 += " ศุกร์";
        }
        if (c7.isSelected()) {
            time1 += " เสาร์";
        }
        if (!time1.equals("")) {
            String temp = time1;
            time1 = " แสดงวัน " + temp;
        }

        time = time1;
    }
}

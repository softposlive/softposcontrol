package softpos.gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import utilities.DateChooser.DateChooseDialog;
import utilities.MySQLConnect;

public class RepSaleandlost extends javax.swing.JDialog {

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
    String TDate1, TDate2, TDate3, TDate4, TDate5, TDate6, TDate7 = "";
    ArrayList<RjrepRecord> RjArray = new ArrayList<RjrepRecord>();
    RjrepRecord RjRec2 = new RjrepRecord();

    /** Creates new form RepSaleByTime */
    public RepSaleandlost(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //try {
        //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //} catch (Exception e) {
        //    e.printStackTrace();
        // }
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
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));

        int[] ColSize = {50, 70, 200, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
        for (int i = 0; i < 19; i++) {
            //int vColIndex = 0;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }

        DecimalFormat DoubleFmt = new DecimalFormat("##,###,##0.00");
        DecimalFormat IntegerFmt = new DecimalFormat("##,###,##0");
        DecimalFormat PersentFmt = new DecimalFormat("#,##0%");

        TableColumnModel tcm = ShowTableLogin.getColumnModel();

        TableTestFormatRenderer r = new TableTestFormatRenderer(IntegerFmt);

        DefaultTableCellRenderer tbc = new DefaultTableCellRenderer();
        tbc.setBackground(java.awt.Color.yellow);
        tbc.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tbc2 = new DefaultTableCellRenderer();
        tbc2.setBackground(java.awt.Color.magenta);
        tbc2.setHorizontalAlignment(SwingConstants.RIGHT);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(3).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(4).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(5).setCellRenderer(tbc2);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(6).setCellRenderer(tbc2);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(7).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(8).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(9).setCellRenderer(tbc2);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(10).setCellRenderer(tbc2);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(11).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(12).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(13).setCellRenderer(tbc2);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(14).setCellRenderer(tbc2);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(15).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(16).setCellRenderer(tbc);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(17).setCellRenderer(r);

        r = new TableTestFormatRenderer(IntegerFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(18).setCellRenderer(r);

        txtDate1.setText(DateFmt.format(date));
        txtDate2.setText(DateFmt.format(date));
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
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        cmdDateChoose2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTableLogin = new javax.swing.JTable();
        bntOK = new javax.swing.JButton();
        bntPrint = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        bntPrint1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานยอดขาย/เสีย");
        setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงข้อมูลที่ (1)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 14))); // NOI18N

        txtDate1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
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

        txtDate2.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtDate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate2KeyPressed(evt);
            }
        });

        cmdDateChoose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose1.setFocusable(false);
        cmdDateChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose1ActionPerformed(evt);
            }
        });

        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ShowTableLogin.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        ShowTableLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "DEPT", "Description", "ขาย", "เสีย", "ขาย", "เสีย", "ขาย", "เสีย", "ขาย", "เสีย", "ขาย", "เสีย", "ขาย", "เสีย", "ขาย", "เสีย", "รวมขาย", "รวมเสีย"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
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
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate2.requestFocus();
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
    if (cnt > 0) {
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();
        cmdExportToExcel();
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }
}//GEN-LAST:event_bntPrint1ActionPerformed

private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate1.requestFocus();
    }
}//GEN-LAST:event_txtDate2KeyPressed

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
// TODO add your handling code here:
    Point point = cmdDateChoose1.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        txtDate1.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    txtDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
// TODO add your handling code here:
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        txtDate2.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    txtDate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

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
        ClearTempRj();
        Day1 = GetDay1();
        try {
            TempDate1 = DateFmt.parse(txtDate1.getText());
            TempDate2 = DateFmt.parse(txtDate2.getText());
        } catch (ParseException ex) {
            //Logger.getLogger(RepSaleByTime.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showConfirmDialog(this, "กรุณาป้อนวันที่ให้ถูกต้อง...");
            return;
        }
        TDate1 = "";
        TDate2 = "";
        TDate3 = "";
        TDate4 = "";
        TDate5 = "";
        TDate6 = "";
        TDate7 = "";
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from rjfile where (tdate >='" + SqlDateFmt.format(TempDate1) + "') and (tdate<='" + SqlDateFmt.format(TempDate2) + "') " +
                    "and (locate(dayofweek(tdate),'" + Day1 + "')>0) group by tdate order by tdate";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                int RowCount = model2.getRowCount();
                for (int i = 0; i <= RowCount - 1; i++) {
                    model2.removeRow(0);
                }
            } else {
                do {
                    if (TDate1.equals("")) {
                        TDate1 = DateFmt.format(rec.getDate("tdate"));
                    } else if (TDate2.equals("")) {
                        TDate2 = DateFmt.format(rec.getDate("tdate"));
                    } else if (TDate3.equals("")) {
                        TDate3 = DateFmt.format(rec.getDate("tdate"));
                    } else if (TDate4.equals("")) {
                        TDate4 = DateFmt.format(rec.getDate("tdate"));
                    } else if (TDate5.equals("")) {
                        TDate5 = DateFmt.format(rec.getDate("tdate"));
                    } else if (TDate6.equals("")) {
                        TDate6 = DateFmt.format(rec.getDate("tdate"));
                    } else if (TDate7.equals("")) {
                        TDate7 = DateFmt.format(rec.getDate("tdate"));
                    }

                } while (rec.next());
            //JOptionPane.showMessageDialog(this, TDate1+" "+TDate2+" "+TDate3+" "+TDate4+" "+TDate5+" "+TDate6+" "+TDate7);
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from rjfile where (tdate >='" + SqlDateFmt.format(TempDate1) + "') and (tdate<='" + SqlDateFmt.format(TempDate2) + "') " +
                    "and (locate(dayofweek(tdate),'" + Day1 + "')>0) group by tdate,rjno order by tdate,rjno";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                int RowCount = model2.getRowCount();
                for (int i = 0; i <= RowCount - 1; i++) {
                    model2.removeRow(0);
                }
            } else {
                do {
                    if (TDate1.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac1", rec.getString("rjno"), rec.getDouble("total"));
                    } else if (TDate2.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac3", rec.getString("rjno"), rec.getDouble("total"));
                    } else if (TDate3.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac5", rec.getString("rjno"), rec.getDouble("total"));
                    } else if (TDate4.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac7", rec.getString("rjno"), rec.getDouble("total"));
                    } else if (TDate5.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac9", rec.getString("rjno"), rec.getDouble("total"));
                    } else if (TDate6.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac11", rec.getString("rjno"), rec.getDouble("total"));
                    } else if (TDate7.equals(DateFmt.format(rec.getDate("tdate")))) {
                        UpdateTempRj1("mac13", rec.getString("rjno"), rec.getDouble("total"));
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }

        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select r_date,pgroup,sum(r_amount) from prolost left join hprolost on prolost.r_no=hprolost.r_no " +
                    "left join product on prolost.r_pcode=product.pcode where (r_date >='" + SqlDateFmt.format(TempDate1) + "') and (r_date<='" + SqlDateFmt.format(TempDate2) + "') " +
                    "and (locate(dayofweek(r_date),'" + Day1 + "')>0) group by r_date,pgroup order by r_date,pgroup";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    //JOptionPane.showConfirmDialog(this, rec.getString("pgroup")+" "+rec.getString("pgroup").substring(2, 4));

                    if (TDate1.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac2", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    } else if (TDate2.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac4", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    } else if (TDate3.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac6", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    } else if (TDate4.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac8", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    } else if (TDate5.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac10", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    } else if (TDate6.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac12", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    } else if (TDate7.equals(DateFmt.format(rec.getDate("r_date")))) {
                        UpdateTempRj2("mac14", rec.getString("pgroup"), rec.getDouble("sum(r_amount)"));
                    }
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }

        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertQuery = "update temprjrep set mac15=mac1+mac3+mac5+mac7+mac9+mac11+mac13," +
                    "mac16=mac2+mac4+mac6+mac8+mac10+mac12+mac14";
            stmt.executeUpdate(InsertQuery);
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        LoadDataToGrid();
        ShowTableLogin.requestFocus();
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }

    public void UpdateTempRj1(String fieldname, String RjNo, Double Total) {
        int RjSize = RjArray.size();
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertQuery = "update temprjrep set " + fieldname + "=? where rjno=? ";
            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
            prm.setDouble(1, Total);
            prm.setString(2, RjNo);
            prm.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void UpdateTempRj2(String fieldname, String PGroup, Double Total) {
        int RjSize = RjArray.size();
        if(PGroup.length()<4){
            PGroup = "0000";
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertQuery = "update temprjrep set " + fieldname + "=? where dept=? ";
            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
            prm.setDouble(1, Total);
            prm.setString(2, PGroup.substring(2, 4));
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
            String InsertQuery = "insert into temprjrep(rjline, rjno, dept, tdesc) select rjline, rjno, dept, tdesc from surrj where dept <> ''";
            stmt.executeUpdate(sqlDel);
            stmt.executeUpdate(InsertQuery);
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void LoadDataToGrid() {
        String HeadX = "...";
        TableColumn col1 = ShowTableLogin.getColumnModel().getColumn(3);
        col1.setHeaderValue(HeadX);
        TableColumn col2 = ShowTableLogin.getColumnModel().getColumn(4);
        col2.setHeaderValue(HeadX);
        TableColumn col3 = ShowTableLogin.getColumnModel().getColumn(5);
        col3.setHeaderValue(HeadX);
        TableColumn col4 = ShowTableLogin.getColumnModel().getColumn(6);
        col4.setHeaderValue(HeadX);
        TableColumn col5 = ShowTableLogin.getColumnModel().getColumn(7);
        col5.setHeaderValue(HeadX);
        TableColumn col6 = ShowTableLogin.getColumnModel().getColumn(8);
        col6.setHeaderValue(HeadX);
        TableColumn col7 = ShowTableLogin.getColumnModel().getColumn(9);
        col7.setHeaderValue(HeadX);
        TableColumn col8 = ShowTableLogin.getColumnModel().getColumn(10);
        col8.setHeaderValue(HeadX);
        TableColumn col9 = ShowTableLogin.getColumnModel().getColumn(11);
        col9.setHeaderValue(HeadX);
        TableColumn col10 = ShowTableLogin.getColumnModel().getColumn(12);
        col10.setHeaderValue(HeadX);
        TableColumn col11 = ShowTableLogin.getColumnModel().getColumn(13);
        col11.setHeaderValue(HeadX);
        TableColumn col12 = ShowTableLogin.getColumnModel().getColumn(14);
        col12.setHeaderValue(HeadX);
        TableColumn col13 = ShowTableLogin.getColumnModel().getColumn(15);
        col13.setHeaderValue(HeadX);
        TableColumn col14 = ShowTableLogin.getColumnModel().getColumn(16);
        col14.setHeaderValue(HeadX);
        String Head15 = "ยอดรวมขาย";
        TableColumn col15 = ShowTableLogin.getColumnModel().getColumn(17);
        col15.setHeaderValue(Head15);
        String Head16 = "ยอดรวมเสีย";
        TableColumn col16 = ShowTableLogin.getColumnModel().getColumn(18);
        col16.setHeaderValue(Head16);

        //LoadData To SurRj
        if (!TDate1.equals("")) {
            String Head = TDate1;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(3);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(4);
            coly.setHeaderValue(Head2);
        }
        if (!TDate2.equals("")) {
            String Head = TDate2;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(5);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(6);
            coly.setHeaderValue(Head2);
        }
        if (!TDate3.equals("")) {
            String Head = TDate3;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(7);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(8);
            coly.setHeaderValue(Head2);
        }
        if (!TDate4.equals("")) {
            String Head = TDate4;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(9);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(10);
            coly.setHeaderValue(Head2);
        }
        if (!TDate5.equals("")) {
            String Head = TDate5;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(11);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(12);
            coly.setHeaderValue(Head2);
        }
        if (!TDate6.equals("")) {
            String Head = TDate6;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(13);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(14);
            coly.setHeaderValue(Head2);
        }
        if (!TDate7.equals("")) {
            String Head = TDate7;
            TableColumn col = ShowTableLogin.getColumnModel().getColumn(15);
            col.setHeaderValue(Head);
            String Head2 = "ขาย/เสีย)";
            TableColumn coly = ShowTableLogin.getColumnModel().getColumn(16);
            coly.setHeaderValue(Head2);
        }

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
                        rec2.getDouble("mac13"),
                        rec2.getDouble("mac14"),
                        rec2.getDouble("mac15"),
                        rec2.getDouble("mac16"),
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
        cmdPrintReport();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                RepSaleandlost dialog = new RepSaleandlost(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
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

    private void cmdPrintReport() {
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();
        TableColumn col = ShowTableLogin.getColumnModel().getColumn(3);
        TableColumn col2 = ShowTableLogin.getColumnModel().getColumn(5);
        TableColumn col3 = ShowTableLogin.getColumnModel().getColumn(7);
        TableColumn col4 = ShowTableLogin.getColumnModel().getColumn(9);
        TableColumn col5 = ShowTableLogin.getColumnModel().getColumn(11);
        TableColumn col6 = ShowTableLogin.getColumnModel().getColumn(13);
        TableColumn col7 = ShowTableLogin.getColumnModel().getColumn(15);
        String[] header = {col.getHeaderValue().toString(),col2.getHeaderValue().toString(),col3.getHeaderValue().toString(),col4.getHeaderValue().toString(),col5.getHeaderValue().toString(),col6.getHeaderValue().toString(),col7.getHeaderValue().toString()};
        ViewReport report = new ViewReport();
        report.printReportRepSaleandlost(getDaySelected(), header);
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
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
                HSSFRow row = sheet.createRow((short) 1);
                row.setHeight((short) ((30) / ((double) 1 / 20)));
                HSSFRow row2 = sheet.createRow((short) 2);
                HSSFRow row3 = sheet.createRow(3);
                // sheet.addMergedRegion(new Region(0,(short)1,0,(short)5));
// Create a cell at index zero ( Top Left)
                HSSFCell cell10 = row.createCell((short) 0);
                HSSFCell cell15 = row.createCell((short) 5);

                sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));
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
                HSSFCell h17 = row2.createCell((short) 16);
                HSSFCell h18 = row2.createCell((short) 17);
                HSSFCell h19 = row2.createCell((short) 18);

                HSSFCell hd1 = row3.createCell((short) 0);
                HSSFCell hd2 = row3.createCell((short) 1);
                HSSFCell hd3 = row3.createCell((short) 2);
                HSSFCell hd4 = row3.createCell((short) 3);
                HSSFCell hd5 = row3.createCell((short) 4);
                HSSFCell hd6 = row3.createCell((short) 5);
                HSSFCell hd7 = row3.createCell((short) 6);
                HSSFCell hd8 = row3.createCell((short) 7);
                HSSFCell hd9 = row3.createCell((short) 8);
                HSSFCell hd10 = row3.createCell((short) 9);
                HSSFCell hd11 = row3.createCell((short) 10);
                HSSFCell hd12 = row3.createCell((short) 11);
                HSSFCell hd13 = row3.createCell((short) 12);
                HSSFCell hd14 = row3.createCell((short) 13);
                HSSFCell hd15 = row3.createCell((short) 14);
                HSSFCell hd16 = row3.createCell((short) 15);
                HSSFCell hd17 = row3.createCell((short) 16);
                HSSFCell hd18 = row3.createCell((short) 17);
                HSSFCell hd19 = row3.createCell((short) 18);


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
                h17.setCellType(HSSFCell.CELL_TYPE_STRING);
                h18.setCellType(HSSFCell.CELL_TYPE_STRING);
                h19.setCellType(HSSFCell.CELL_TYPE_STRING);

                hd1.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd2.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd3.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd4.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd5.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd6.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd7.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd8.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd9.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd10.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd11.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd12.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd13.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd14.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd15.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd16.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd17.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd18.setCellType(HSSFCell.CELL_TYPE_STRING);
                hd19.setCellType(HSSFCell.CELL_TYPE_STRING);

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
                HSSFFont fnt = wb.createFont();
                fnt.setFontHeight((short) ((10) / ((double) 1 / 20)));
                cellStyleh.setFont(fnt);


                cell10.setCellValue("รายงานยอดขาย/เสีย " + txtDate1.getText() + " " + getDaySelected());

                h1.setCellValue("No");
                h2.setCellValue("Dept");
                h3.setCellValue("Description");
                TableColumn col = ShowTableLogin.getColumnModel().getColumn(3);
                TableColumn col2 = ShowTableLogin.getColumnModel().getColumn(5);
                TableColumn col3 = ShowTableLogin.getColumnModel().getColumn(7);
                TableColumn col4 = ShowTableLogin.getColumnModel().getColumn(9);
                TableColumn col5 = ShowTableLogin.getColumnModel().getColumn(11);
                TableColumn col6 = ShowTableLogin.getColumnModel().getColumn(13);
                TableColumn col7 = ShowTableLogin.getColumnModel().getColumn(15);


                h4.setCellValue(col.getHeaderValue().toString());
                //h5.setCellValue(col2.getHeaderValue().toString());
                h6.setCellValue(col2.getHeaderValue().toString());
                // h7.setCellValue(col4.getHeaderValue().toString());
                h8.setCellValue(col3.getHeaderValue().toString());
                // h9.setCellValue(col6.getHeaderValue().toString());
                h10.setCellValue(col4.getHeaderValue().toString());

                h12.setCellValue(col5.getHeaderValue().toString());

                h14.setCellValue(col6.getHeaderValue().toString());

                h16.setCellValue(col7.getHeaderValue().toString());

                h18.setCellValue("ยอดรวมขาย");
                h19.setCellValue("ยอดรวมเสีย");

                cell10.setCellStyle(cellStyle);
                cell15.setCellStyle(cellStyle);

                sheet.addMergedRegion(new Region(2, (short) 3, 2, (short) 4));
                sheet.addMergedRegion(new Region(2, (short) 5, 2, (short) 6));
                sheet.addMergedRegion(new Region(2, (short) 7, 2, (short) 8));
                sheet.addMergedRegion(new Region(2, (short) 9, 2, (short) 10));
                sheet.addMergedRegion(new Region(2, (short) 11, 2, (short) 12));
                sheet.addMergedRegion(new Region(2, (short) 13, 2, (short) 14));
                sheet.addMergedRegion(new Region(2, (short) 15, 2, (short) 16));
                sheet.addMergedRegion(new Region(2, (short) 0, 3, (short) 0));
                sheet.addMergedRegion(new Region(2, (short) 1, 3, (short) 1));
                sheet.addMergedRegion(new Region(2, (short) 2, 3, (short) 2));
                sheet.addMergedRegion(new Region(2, (short) 17, 3, (short) 17));
                sheet.addMergedRegion(new Region(2, (short) 18, 3, (short) 18));
//                
//                
//                sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) 0));
//                sheet.addMergedRegion(new Region(0, (short) 1, 1, (short) 1));
//                sheet.addMergedRegion(new Region(0, (short) 2, 1, (short) 2));
//                sheet.addMergedRegion(new Region(0, (short) 3, 1, (short) 3));
//                sheet.addMergedRegion(new Region(0, (short) 4, 1, (short) 4));
//                sheet.addMergedRegion(new Region(0, (short) 5, 1, (short) 5));

                sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 5));
                hd1.setCellValue("");
                hd2.setCellValue("");
                hd3.setCellValue("");

                hd4.setCellValue("ขาย");
                hd5.setCellValue("เสีย");

                hd6.setCellValue("ขาย");
                hd7.setCellValue("เสีย");

                hd8.setCellValue("ขาย");
                hd9.setCellValue("เสีย");

                hd10.setCellValue("ขาย");
                hd11.setCellValue("เสีย");

                hd12.setCellValue("ขาย");
                hd13.setCellValue("เสีย");

                hd14.setCellValue("ขาย");
                hd15.setCellValue("เสีย");

                hd16.setCellValue("ขาย");
                hd17.setCellValue("เสีย");
                hd18.setCellValue("");
                hd19.setCellValue("");

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
                h17.setCellStyle(cellStyle);
                h18.setCellStyle(cellStyle);
                h19.setCellStyle(cellStyle);

                hd1.setCellStyle(cellStyle);
                hd2.setCellStyle(cellStyle);
                hd3.setCellStyle(cellStyle);
                hd4.setCellStyle(cellStyle);
                hd5.setCellStyle(cellStyle);
                hd6.setCellStyle(cellStyle);
                hd7.setCellStyle(cellStyle);
                hd8.setCellStyle(cellStyle);
                hd9.setCellStyle(cellStyle);
                hd10.setCellStyle(cellStyle);
                hd11.setCellStyle(cellStyle);
                hd12.setCellStyle(cellStyle);
                hd13.setCellStyle(cellStyle);
                hd14.setCellStyle(cellStyle);
                hd15.setCellStyle(cellStyle);
                hd16.setCellStyle(cellStyle);
                hd17.setCellStyle(cellStyle);
                hd18.setCellStyle(cellStyle);
                hd19.setCellStyle(cellStyle);


                int cnt = model2.getRowCount();
                for (int i = 0; i < cnt; i++) {
                    HSSFRow rows = sheet.createRow((short) i + 4);
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
                    HSSFCell c17 = rows.createCell((short) 16);
                    HSSFCell c18 = rows.createCell((short) 17);
                    HSSFCell c19 = rows.createCell((short) 18);

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
                    c17.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c18.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c19.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

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
                    c17.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 16)));
                    c18.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 17)));
                    c19.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 18)));
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
                    c17.setCellStyle(cellStyle4);
                    c18.setCellStyle(cellStyle4);
                    c19.setCellStyle(cellStyle4);

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


                HSSFRow lastrow = sheet.createRow((short) cnt + 4);
                HSSFCell lastrowc0 = lastrow.createCell((short) 0);
                HSSFCell lastrowc26 = lastrow.createCell((short) 18);


                lastrowc0.setCellStyle(cellStylelastrow);
                lastrowc26.setCellStyle(cellStylelastrow);
                sheet.addMergedRegion(new Region(cnt + 4, (short) 0, cnt + 4, (short) 18));
//                
//                //set width
                sheet.setColumnWidth((short) 0, (short) ((50 * 2) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 1, (short) ((50 * 2) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 2, (short) ((50 * 8) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 3, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 4, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 5, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 6, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 7, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 8, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 9, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 10, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 11, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 12, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 13, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 14, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 15, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 16, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 17, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 18, (short) ((50 * 3) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 19, (short) ((50 * 3) / ((double) 1 / 20)));
//  

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

    private String getDaySelected() {
        List date = new ArrayList();
        if (c1.isSelected()) {
            date.add("อาทิตย์");
        }
        if (c2.isSelected()) {
            date.add(" จันทร์");
        }
        if (c3.isSelected()) {
            date.add(" อังคาร");
        }
        if (c4.isSelected()) {
            date.add(" พุธ");
        }
        if (c5.isSelected()) {
            date.add(" พฤหัสบดี");
        }
        if (c6.isSelected()) {
            date.add(" ศุกร์");
        }
        if (c7.isSelected()) {
            date.add(" เสาร์");
        }
        String dateSelect = txtDate1.getText()+" ถึง "+txtDate1.getText()+" แสดงวัน ";
        for (int i = 0; i < date.size(); i++) {
            if (i == date.size() - 1) {
                dateSelect += date.get(i);
            } else {
                dateSelect += date.get(i) + ",";
            }
        }
        return dateSelect;
    }
}

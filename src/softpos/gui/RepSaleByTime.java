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
import utilities.DateChooser.DateChooseDialog;
import utilities.MySQLConnect;

/**
 *
 * @author  root
 */
public class RepSaleByTime extends javax.swing.JDialog {

    SimpleDateFormat DateTimeFmt = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    SimpleDateFormat FileDateFmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
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
    private String[] time = null;
    
    /** Creates new form RepSaleByTime */
    public RepSaleByTime(java.awt.Frame parent, boolean modal) {
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

        int[] ColSize = {50, 70, 300, 150, 150, 150, 100};
        for (int i = 0; i < 7; i++) {
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

        r = new TableTestFormatRenderer(PersentFmt);
        r.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(6).setCellRenderer(r);

        txtDate1.setText(DateFmt.format(date));
        txtDate2.setText(DateFmt.format(date));
        txtDate3.setText(DateFmt.format(date));
        txtDate4.setText(DateFmt.format(date));
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
        jLabel4 = new javax.swing.JLabel();
        txtDate2 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        c1 = new javax.swing.JCheckBox();
        c2 = new javax.swing.JCheckBox();
        c3 = new javax.swing.JCheckBox();
        c4 = new javax.swing.JCheckBox();
        c5 = new javax.swing.JCheckBox();
        c6 = new javax.swing.JCheckBox();
        c7 = new javax.swing.JCheckBox();
        cmdDateChoose1 = new javax.swing.JButton();
        cmdDateChoose2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTableLogin = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtDate3 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDate4 = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        d1 = new javax.swing.JCheckBox();
        d2 = new javax.swing.JCheckBox();
        d3 = new javax.swing.JCheckBox();
        d4 = new javax.swing.JCheckBox();
        d5 = new javax.swing.JCheckBox();
        d6 = new javax.swing.JCheckBox();
        d7 = new javax.swing.JCheckBox();
        cmdDateChoose3 = new javax.swing.JButton();
        cmdDateChoose4 = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        bntPrint = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        bntPrint1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานยอดการขายตามช่วงเวลา");
        setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงข้อมูลที่ (1)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 14))); // NOI18N

        txtDate1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtDate1.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
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

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel4.setText("ถึง");

        txtDate2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtDate2.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        txtDate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate2KeyPressed(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "NO", "DEPT", "Description", "Rang Date1(Day)", "Rang Date2(Day)", "ผลต่าง(ปีนี้ & ปีที่แล้ว)", "ผลต่าง (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงข้อมูลที่ (2)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 14))); // NOI18N

        txtDate3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtDate3.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        txtDate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDate3ActionPerformed(evt);
            }
        });
        txtDate3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDate3FocusLost(evt);
            }
        });
        txtDate3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate3KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("ถึง");

        txtDate4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        txtDate4.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        txtDate4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate4KeyPressed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        d1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d1.setSelected(true);
        d1.setText("วันอาทิตย์");
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });

        d2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d2.setSelected(true);
        d2.setText("วันจันทร์");

        d3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d3.setSelected(true);
        d3.setText("วันอังคาร");

        d4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d4.setSelected(true);
        d4.setText("วันพุธ");

        d5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d5.setSelected(true);
        d5.setText("วันพฤหัสฯ");

        d6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d6.setSelected(true);
        d6.setText("วันศุกร์");

        d7.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        d7.setSelected(true);
        d7.setText("วันเสาร์");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d1)
                    .addComponent(d5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(d6)
                        .addGap(18, 18, 18)
                        .addComponent(d7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(d2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d3)
                        .addGap(18, 18, 18)
                        .addComponent(d4)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d1)
                    .addComponent(d2)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(d3)
                        .addComponent(d4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d6)
                    .addComponent(d5)
                    .addComponent(d7)))
        );

        cmdDateChoose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose3.setFocusable(false);
        cmdDateChoose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose3ActionPerformed(evt);
            }
        });

        cmdDateChoose4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose4.setFocusable(false);
        cmdDateChoose4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDate4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bntOK.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
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

        bntPrint.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bntPrint.setText("พิมพ์ (Print)");
        bntPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
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

        bntPrint1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bntPrint1.setText("Calc File");
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntExit, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bntPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bntPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bntOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
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

private void txtDate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDate3ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtDate3ActionPerformed

private void txtDate3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDate3FocusLost
// TODO add your handling code here:
}//GEN-LAST:event_txtDate3FocusLost

private void txtDate3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate3KeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate4.requestFocus();
    }
}//GEN-LAST:event_txtDate3KeyPressed

private void txtDate4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate4KeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate1.requestFocus();
    }
}//GEN-LAST:event_txtDate4KeyPressed

private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_d1ActionPerformed

private void bntOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntOKMouseReleased
   
}//GEN-LAST:event_bntOKMouseReleased

private void bntPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintActionPerformed
     bntPrintClick();
}//GEN-LAST:event_bntPrintActionPerformed

private void bntExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntExitMouseReleased
    
}//GEN-LAST:event_bntExitMouseReleased

private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_c1ActionPerformed

private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate3.requestFocus();
    }
}//GEN-LAST:event_txtDate2KeyPressed

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

private void bntPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrint1ActionPerformed
// TODO add your handling code here:
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

private void cmdDateChoose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose3ActionPerformed
// TODO add your handling code here:
    Point point = cmdDateChoose3.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        txtDate3.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    txtDate3.requestFocus();
}//GEN-LAST:event_cmdDateChoose3ActionPerformed

private void cmdDateChoose4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose4ActionPerformed
// TODO add your handling code here:
    Point point = cmdDateChoose4.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        txtDate4.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    txtDate4.requestFocus();
}//GEN-LAST:event_cmdDateChoose4ActionPerformed

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

            @Override
            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            }
        };
        t.start();
        ClearTempRj();
        time = setDaySelect();
        Day1 = GetDay1();
        Day2 = GetDay2();
        try {
            TempDate1 = DateFmt.parse(txtDate1.getText());
            TempDate2 = DateFmt.parse(txtDate2.getText());
            TempDate3 = DateFmt.parse(txtDate3.getText());
            TempDate4 = DateFmt.parse(txtDate4.getText());
        } catch (ParseException ex) {
            //Logger.getLogger(RepSaleByTime.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showConfirmDialog(this, "กรุณาป้อนวันที่ให้ถูกต้อง...");
            return;
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select rjno,sum(total) from rjfile where (tdate >='" + SqlDateFmt.format(TempDate1) + "') and (tdate<='" + SqlDateFmt.format(TempDate2) + "') " +
                    "and (locate(dayofweek(tdate),'" + Day1 + "')>0) group by rjno order by rjno";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                int RowCount = model2.getRowCount();
                for (int i = 0; i <= RowCount - 1; i++) {
                    model2.removeRow(0);
                }
            } else {
                do {
                    String XRJNo = rec.getString("rjno");
                    Double XTotal = rec.getDouble("sum(total)");
                    UpdateTempRj1(XRJNo, XTotal);
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select rjno,sum(total) from rjfile where (tdate >='" + SqlDateFmt.format(TempDate3) + "') and (tdate<='" + SqlDateFmt.format(TempDate4) + "') " +
                    "and (locate(dayofweek(tdate),'" + Day2 + "')>0) group by rjno order by rjno";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                int RowCount = model2.getRowCount();
                for (int i = 0; i <= RowCount - 1; i++) {
                    model2.removeRow(0);
                }
            } else {
                do {
                    String XRJNo = rec.getString("rjno");
                    Double XTotal = rec.getDouble("sum(total)");
                    UpdateTempRj2(XRJNo, XTotal);
                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
//        try {
//            Statement stmt = MySQLConnect.con.createStatement();
//            String LoadTableFile = "select *from temprjrep order by rjno";
//            ResultSet rec = stmt.executeQuery(LoadTableFile);
//            rec.first();
//            if (rec.getRow() == 0) {
//                int RowCount = model2.getRowCount();
//                for (int i = 0; i <= RowCount - 1; i++) {
//                    model2.removeRow(0);
//                }
//            } else {
//                do {
//                    double mac1 = rec.getDouble("mac1");
//                    double mac2 = rec.getDouble("mac2");
//                    double dif = mac1 - mac2;
//                    double difper = 0.0;
//                    if (mac2 > 0) {
//                        difper = dif / mac2;
//                    }
//                    String XRJNo = rec.getString("rjno");
//                    UpdateTempRj3(XRJNo, dif, difper);
//                } while (rec.next());
//            }
//            rec.close();
//            stmt.close();
//        } catch (SQLException e) {
//            PUtility.ShowError(e.getMessage());
//        }


        LoadDataToGrid();
        ShowTableLogin.requestFocus();
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }

    public void UpdateTempRj1(String TRjNo, Double TTotal) {
//        try {
//            Statement stmt = MySQLConnect.con.createStatement();
//            String InsertQuery = "update temprjrep set mac1=? where rjno=? ";
////            InsertQuery = "insert into temprjrep (rjno, mac1) values('"+TRjNo+"','"+TTotal+"')";
//            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
//            prm.setDouble(1, TTotal);
//            prm.setString(2, TRjNo);
//            prm.executeUpdate();
////            Statement stmt = MySQLConnect.con.createStatement();
////            stmt.executeUpdate(InsertQuery);
//            stmt.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }

    public void UpdateTempRj2(String TRjNo, Double TTotal) {
//        try {
//            Statement stmt = MySQLConnect.con.createStatement();
//            String InsertQuery = "update temprjrep set mac2=? where rjno=? ";
//            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
//            prm.setDouble(1, TTotal);
//            prm.setString(2, TRjNo);
//            prm.executeUpdate();
//            stmt.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }

    public void UpdateTempRj3(String TRjNo, Double mac3, Double mac4) {
//        try {
//            Statement stmt = MySQLConnect.con.createStatement();
//            String InsertQuery = "update temprjrep set mac3=?,mac4=? where rjno=? ";
//            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(InsertQuery);
//            prm.setDouble(1, mac3);
//            prm.setDouble(2, mac4);
//            prm.setString(3, TRjNo);
//            prm.executeUpdate();
//            stmt.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }

    public void ClearTempRj() {
//        try {
//            Statement stmt = MySQLConnect.con.createStatement();
//            String sqlDel = "delete from temprjrep";
//            String InsertQuery = "insert into temprjrep(rjline, rjno, dept, tdesc) select rjline, rjno, dept, tdesc from surrj;";
//            stmt.executeUpdate(sqlDel);
//            stmt.executeUpdate(InsertQuery);
//            stmt.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }

    public void LoadDataToGrid() {
        //LoadData To SurRj
        String Head3 = txtDate1.getText().substring(0, 5) + " - " + txtDate2.getText();
        TableColumn col = ShowTableLogin.getColumnModel().getColumn(3);
        col.setHeaderValue(Head3);

        String Head4 = txtDate3.getText().substring(0, 5) + " - " + txtDate4.getText();
        col = ShowTableLogin.getColumnModel().getColumn(4);
        col.setHeaderValue(Head4);
        JTableHeader header = ShowTableLogin.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

//        try {
//            Statement stmt2 = MySQLConnect.con.createStatement();
//            String LoadTableFile2 = "select * from temprjrep " +
//                    "order by rjline";
//            ResultSet rec2 = stmt2.executeQuery(LoadTableFile2);
//            Date dt = new Date();
//            int RowCount = model2.getRowCount();
//            for (int i = 0; i <= RowCount - 1; i++) {
//                model2.removeRow(0);
//            }
//            rec2.first();
//            if (rec2.getRow() == 0) {
//            } else {
//                do {
//                    Object[] input = {
//                        rec2.getString("rjno"),
//                        rec2.getString("dept"),
//                        rec2.getString("tdesc"),
//                        rec2.getDouble("mac1"),
//                        rec2.getDouble("mac2"),
//                        rec2.getDouble("mac3"),
//                        rec2.getDouble("mac4"),
//                    };
//                    model2.addRow(input);
//                } while (rec2.next());
//                RowCount = model2.getRowCount();
//                showCell(0, 0);
//            }
//            rec2.close();
//            stmt2.close();
//        } catch (SQLException e) {
//            PUtility.ShowError(e.getMessage());
//        }

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

    public String GetDay2() {
        String day = "";
        if (d1.isSelected()) {
            day += ",1";
        }
        if (d2.isSelected()) {
            day += ",2";
        }
        if (d3.isSelected()) {
            day += ",3";
        }
        if (d4.isSelected()) {
            day += ",4";
        }
        if (d5.isSelected()) {
            day += ",5";
        }
        if (d6.isSelected()) {
            day += ",6";
        }
        if (d7.isSelected()) {
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
        String t1 = txtDate1.getText() + " - " + txtDate2.getText() + " " + time[0];
        String t2 = txtDate3.getText() + " - " + txtDate4.getText() + " " + time[1];
        String date1 = txtDate1.getText().substring(0, 5) + " - " + txtDate2.getText();
        String date2 = txtDate3.getText().substring(0, 5) + " - " + txtDate4.getText();
        view.printReportRepSaleByTime(t1, t2, date1, date2);
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        t.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                RepSaleByTime dialog = new RepSaleByTime(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdDateChoose3;
    private javax.swing.JButton cmdDateChoose4;
    private javax.swing.JCheckBox d1;
    private javax.swing.JCheckBox d2;
    private javax.swing.JCheckBox d3;
    private javax.swing.JCheckBox d4;
    private javax.swing.JCheckBox d5;
    private javax.swing.JCheckBox d6;
    private javax.swing.JCheckBox d7;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    private javax.swing.JFormattedTextField txtDate3;
    private javax.swing.JFormattedTextField txtDate4;
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
        DecimalFormat PersentFmt = new DecimalFormat("#,##0%");
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
                HSSFRow row3 = sheet.createRow((short) 2);
                HSSFRow row4 = sheet.createRow((short) 3);
                // sheet.addMergedRegion(new Region(0,(short)1,0,(short)5));
// Create a cell at index zero ( Top Left)
                HSSFCell cell10 = row.createCell((short) 0);
                HSSFCell cell15 = row.createCell((short) 6);

                HSSFCell cell01 = row2.createCell((short) 0);
                HSSFCell cell02 = row2.createCell((short) 6);

                HSSFCell cell03 = row3.createCell((short) 0);
                HSSFCell cell04 = row3.createCell((short) 6);

                HSSFCell h1 = row4.createCell((short) 0);
                HSSFCell h2 = row4.createCell((short) 1);
                HSSFCell h3 = row4.createCell((short) 2);
                HSSFCell h4 = row4.createCell((short) 3);
                HSSFCell h5 = row4.createCell((short) 4);
                HSSFCell h6 = row4.createCell((short) 5);
                HSSFCell h7 = row4.createCell((short) 6);



// Lets make the cell a string type
                cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell15.setCellType(HSSFCell.CELL_TYPE_STRING);

                cell01.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell02.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell03.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell04.setCellType(HSSFCell.CELL_TYPE_STRING);


                h1.setCellType(HSSFCell.CELL_TYPE_STRING);
                h2.setCellType(HSSFCell.CELL_TYPE_STRING);
                h3.setCellType(HSSFCell.CELL_TYPE_STRING);
                h4.setCellType(HSSFCell.CELL_TYPE_STRING);
                h5.setCellType(HSSFCell.CELL_TYPE_STRING);
                h6.setCellType(HSSFCell.CELL_TYPE_STRING);
                h7.setCellType(HSSFCell.CELL_TYPE_STRING);


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
                    time1 = "แสดงวัน " + temp;
                }

                String time2 = "";
                if (d1.isSelected()) {
                    time2 += "อาทิตย์";
                }
                if (d2.isSelected()) {
                    time2 += " จันทร์";
                }
                if (d3.isSelected()) {
                    time2 += " อังคาร";
                }
                if (d4.isSelected()) {
                    time2 += " พุธ";
                }
                if (d5.isSelected()) {
                    time2 += " พฤหัสบดี";
                }
                if (d6.isSelected()) {
                    time2 += " ศุกร์";
                }
                if (d7.isSelected()) {
                    time2 += " เสาร์";
                }
                if (!time2.equals("")) {
                    String temp = time2;
                    time2 = "แสดงวัน " + temp;
                }




                String date1 = txtDate1.getText();
                date1 = date1.substring(0, 5);
                String date2 = txtDate3.getText();
                date2 = date2.substring(0, 5);


                cell10.setCellValue("รายงานยอดขายประจำปี");
                cell10.setCellStyle(cellStyle);
                cell15.setCellStyle(cellStyle);
                sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 6));

                cell01.setCellValue("ช่วงข้อมูลที่ 1 - " + txtDate1.getText() + " - " + txtDate2.getText() + " " + time1);
                cell01.setCellStyle(cellStyleh);
                cell02.setCellStyle(cellStyleh);
                sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 6));

                cell03.setCellValue("ช่วงข้อมุลที่ 2 - " + txtDate3.getText() + " - " + txtDate4.getText() + " " + time2);
                cell03.setCellStyle(cellStyleh);
                cell04.setCellStyle(cellStyleh);
                sheet.addMergedRegion(new Region(2, (short) 0, 2, (short) 6));

                h1.setCellValue("No");//31/07/2009               

                h2.setCellValue("Dept");
                h3.setCellValue("Description");
                h4.setCellValue(date1 + " - " + txtDate2.getText());
                h5.setCellValue(date2 + " - " + txtDate4.getText());
                h6.setCellValue("ผลต่าง(ปีนี้ & ปีที่แล้ว");
                h7.setCellValue("ผลต่าง %");



                sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 6));

                h1.setCellStyle(cellStyle);
                h2.setCellStyle(cellStyle);
                h3.setCellStyle(cellStyle);
                h4.setCellStyle(cellStyle);
                h5.setCellStyle(cellStyle);
                h6.setCellStyle(cellStyle);
                h7.setCellStyle(cellStyle);


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


                    c1.setCellType(HSSFCell.CELL_TYPE_STRING);
                    c2.setCellType(HSSFCell.CELL_TYPE_STRING);
                    c3.setCellType(HSSFCell.CELL_TYPE_STRING);
                    c4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    c7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);


                    //DecFmt

                    c1.setCellValue((String) model2.getValueAt(i, 0));
                    c2.setCellValue((String) model2.getValueAt(i, 1));
                    c3.setCellValue((String) model2.getValueAt(i, 2));
                    c4.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 3)));
                    c5.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 4)));
                    c6.setCellValue(IntegerFmt.format((Double) model2.getValueAt(i, 5)));
                    c7.setCellValue(PersentFmt.format((Double) model2.getValueAt(i, 6)));

                    c1.setCellStyle(cellStyle2);
                    c2.setCellStyle(cellStyle2);
                    c3.setCellStyle(cellStyle3);
                    c4.setCellStyle(cellStyle4);
                    c5.setCellStyle(cellStyle4);
                    c6.setCellStyle(cellStyle4);
                    c7.setCellStyle(cellStyle4);


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
                HSSFCell lastrowc26 = lastrow.createCell((short) 6);


                lastrowc0.setCellStyle(cellStylelastrow);
                lastrowc26.setCellStyle(cellStylelastrow);
                sheet.addMergedRegion(new Region(cnt + 4, (short) 0, cnt + 4, (short) 6));

                //set width
                sheet.setColumnWidth((short) 0, (short) ((50 * 2) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 1, (short) ((50 * 2) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 2, (short) ((50 * 8) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 3, (short) ((50 * 5) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 4, (short) ((50 * 5) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 5, (short) ((50 * 5) / ((double) 1 / 20)));
                sheet.setColumnWidth((short) 6, (short) ((50 * 4) / ((double) 1 / 20)));

                FileOutputStream fOut = new FileOutputStream(file);
                wb.write(fOut);
                fOut.flush();
                fOut.close();
                JOptionPane.showMessageDialog(null, "Create Excel File successful");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Create Excel File Error");
                System.out.println("!!BANG!! xlCreate() : " + e);
            }
        }
        
    }

    private String[] setDaySelect() {
        String time1 = "";
        String time2 = "";
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
            time1 = "แสดงวัน " + temp;
        }


        if (d1.isSelected()) {
            time2 += "อาทิตย์";
        }
        if (d2.isSelected()) {
            time2 += " จันทร์";
        }
        if (d3.isSelected()) {
            time2 += " อังคาร";
        }
        if (d4.isSelected()) {
            time2 += " พุธ";
        }
        if (d5.isSelected()) {
            time2 += " พฤหัสบดี";
        }
        if (d6.isSelected()) {
            time2 += " ศุกร์";
        }
        if (d7.isSelected()) {
            time2 += " เสาร์";
        }
        if (!time2.equals("")) {
            String temp = time2;
            time2 = "แสดงวัน " + temp;
        }
        String[] data = {time1, time2};
        return data;
    }
}


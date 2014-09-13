package softpos.gui;

import directory_utility.DirectoryUtility;
import java.awt.Color;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import other.FTPMainWindow;
import other.SetupFTPProperty;
import utilities.MySQLConnect;
import write_to_text.TextWriter;
import zip_utility.ZipUnzip;

public class SendStockToCenterByADSL extends javax.swing.JDialog {
     SimpleDateFormat DateTimeFmt = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat MonthFmt = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    SimpleDateFormat FileDateFmt = new SimpleDateFormat("yyMMdd", Locale.ENGLISH) ;
    SimpleDateFormat ZipDateFmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH) ;
    SimpleDateFormat StockDateFmt = new SimpleDateFormat("MMyy", Locale.ENGLISH) ;
    SimpleDateFormat FullTimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    SimpleDateFormat ShortTimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("#########0.00");
    DecimalFormat IntFmt = new DecimalFormat("#######0");
    Date date = new Date();
    Date EndofdayDate = new Date();
    String Branch_Name;
    String Branch_Code;
    String Branch_Type;
    String T_Stk = "";
    String TempPath = "C:/spapplication/temp";
    String StockPath = "C:/spapplication/sendstock";
    String DataZipPath = "C:/spapplication/sendstock";
    String PathConvert = "C:/spapplication/tempconvert";
    String FileCharset = "TIS-620";
    String _User = "XXXXXX";
    String STK = "A1" ;

    /** Creates new form SendStockToCenterByADSL */
    public SendStockToCenterByADSL(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        LoadDataFromFile();
        TDate1.setText(DateFmt.format(new Date()));
        TDate2.setText(DateFmt.format(new Date()));
        TMonth1.setText(DateFmt.format(PUtility.GetEndMonth(DateFmt.format(date)).getTime()));
        TranoutChk.setSelected(true);
        AdjustChk.setSelected(false) ;
        TDate1.selectAll() ;
        TDate1.requestFocus();
        loadDefaultRadio();
    }
    private void loadDefaultRadio(){
        String file1 = "C:/spapplication/dbconfig/ftp_local.ini";
        String file2 = "C:/spapplication/dbconfig/ftp_main.ini";
        String file3 = "C:/spapplication/dbconfig/ftp_secondary.ini";
        Properties prop = new Properties();
        try {
            //config1-----------------------------------------------
            FileInputStream input = new FileInputStream(file1);
            prop.load(input);
            if(prop.getProperty("default").equalsIgnoreCase("true")){
                cbxServer.setSelectedIndex(0);
            }else{
                //config2-----------------------------------------------
                input = new FileInputStream(file2);
                prop.load(input);
                if(prop.getProperty("default").equalsIgnoreCase("true")){
                    cbxServer.setSelectedIndex(1);
                }else{
                    //config3-----------------------------------------------
                    input = new FileInputStream(file3);
                    prop.load(input);
                    if(prop.getProperty("default").equalsIgnoreCase("true")){
                        cbxServer.setSelectedIndex(2);
                    }
                    input.close();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    public Boolean Chknotpost(Calendar Tempca1,Calendar Tempca2) {
        Boolean RetVal = false;
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from htranout where (r_date>='" + SqlDateFmt.format(Tempca1.getTime()) + "') and (r_date<='"+SqlDateFmt.format(Tempca2.getTime())+"') and (r_post<>'Y')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                JOptionPane.showMessageDialog(this, "ยังมีเอกสารการโอนสินค้าบางฉบับยังไม่ได้ POST ตัดสต็อกสินค้า...กรุณาทำการ POST สต็อกให้เรียบร้อยก่อนทำการส่งข้อมูล!!!") ;
                RetVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return RetVal;
    }
    public Boolean Chknotadjust(Calendar Tempca3) {
        Boolean RetVal = false;
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from hadjstock where (r_date='" + SqlDateFmt.format(Tempca3.getTime()) + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
                JOptionPane.showMessageDialog(this, "ไม่พบรายการตรวจนับสินค้า...กรุณาทำการตรวจนับสินค้าก่อนทำการส่งข้อมูล !!!") ;
                RetVal = true;
            } else {
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return RetVal;
    }
    private void LoadDataFromFile() {
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String SqlQuery = "select *from branch";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() != 0) {
                Branch_Code = rec.getString("code");
                Branch_Name = rec.getString("name");
                Branch_Type = rec.getString("btype");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public void bntOKClick() {
        Thread SendData = new Thread(new ProcessSend());
        SendData.start();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        TDate1 = new javax.swing.JFormattedTextField();
        TDate2 = new javax.swing.JFormattedTextField();
        TranoutChk = new javax.swing.JCheckBox();
        AdjustChk = new javax.swing.JCheckBox();
        TMonth1 = new javax.swing.JFormattedTextField();
        StkProcess = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();
        cmdDateChoose3 = new javax.swing.JButton();
        cmdDateChoose4 = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cbxServer = new javax.swing.JComboBox();
        cmdSystemSetup = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        pb = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(200, 241, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 73, 18), 5, true));

        jLabel1.setFont(new java.awt.Font("Norasi", 1, 24)); // NOI18N
        jLabel1.setText("โปรแกรมส่งยอดการโอนสินค้าระหว่างสาขา/ยอดสินค้าคงเหลือสิ้นเดือน (BY ADSL)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        TextArea.setColumns(20);
        TextArea.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        TextArea.setRows(5);
        TextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        TextArea.setFocusable(false);
        TextArea.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(TextArea);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        TDate1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        TDate1.setFocusCycleRoot(true);
        TDate1.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        TDate1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        TDate1.setOpaque(false);
        TDate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDate1KeyPressed(evt);
            }
        });

        TDate2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        TDate2.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        TDate2.setOpaque(false);
        TDate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDate2KeyPressed(evt);
            }
        });

        TranoutChk.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        TranoutChk.setText("ยอดการโอนสินค้า");
        TranoutChk.setFocusable(false);
        TranoutChk.setRequestFocusEnabled(false);

        AdjustChk.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        AdjustChk.setText("ยอดตรวจนับสินค้าสิ้นเดือน");
        AdjustChk.setFocusable(false);
        AdjustChk.setRequestFocusEnabled(false);

        TMonth1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        TMonth1.setFocusCycleRoot(true);
        TMonth1.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        TMonth1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        TMonth1.setOpaque(false);
        TMonth1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMonth1KeyPressed(evt);
            }
        });

        StkProcess.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        StkProcess.setForeground(new java.awt.Color(241, 25, 14));
        StkProcess.setText("StockProcess");

        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TranoutChk)
                    .addComponent(AdjustChk))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdDateChoose4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(StkProcess, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TranoutChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmdDateChoose4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AdjustChk)
                    .addComponent(StkProcess))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        bntOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu.jpg"))); // NOI18N
        bntOK.setText("F5-ตกลง (OK)");
        bntOK.setFocusable(false);
        bntOK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntOK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        bntExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logout.jpg"))); // NOI18N
        bntExit.setText("ออก (Exit)");
        bntExit.setFocusable(false);
        bntExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntExit.setIconTextGap(0);
        bntExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bntExitMouseReleased(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "กำหนดข้อมูลเพื่อส่งข้อมูลทาง ADSL ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 1, 14))); // NOI18N

        cbxServer.setBackground(new java.awt.Color(254, 254, 254));
        cbxServer.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        cbxServer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Local FTP Server", "Main FTP Server", "Secondary FTP Server" }));

        cmdSystemSetup.setBackground(java.awt.Color.orange);
        cmdSystemSetup.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        cmdSystemSetup.setText("System Setup");
        cmdSystemSetup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdSystemSetupMouseReleased(evt);
            }
        });
        cmdSystemSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSystemSetupActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, null, new java.awt.Color(1, 1, 1)));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lb.setText("0/0 Byte");
        jPanel12.add(lb);

        lblStatus.setForeground(new java.awt.Color(255, 0, 9));
        lblStatus.setText("lblStatus");
        jPanel12.add(lblStatus);

        jButton1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jButton1.setText("ส่งข้อมูลไปสำนักงานใหญ่");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cmdSystemSetup, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbxServer, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(cmdSystemSetup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bntOK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void TDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDate1KeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
        TDate2.selectAll(); 
        TDate2.requestFocus();
    } 
    if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
    }
       
}//GEN-LAST:event_TDate1KeyPressed

private void TDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDate2KeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
       TMonth1.selectAll() ; 
       TMonth1.requestFocus();
    }
if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
    }
}//GEN-LAST:event_TDate2KeyPressed

private void bntExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntExitMouseReleased
this.dispose();
}//GEN-LAST:event_bntExitMouseReleased

private void TMonth1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMonth1KeyPressed
// TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
       TDate1.selectAll() ; 
       TDate1.requestFocus();
        
    }
    if (evt.getKeyCode()==KeyEvent.VK_F5) {
        bntOKClick() ;
    }
}//GEN-LAST:event_TMonth1KeyPressed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
// TODO add your handling code here:
    java.awt.Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    utilities.DateChooser.DateChooseDialog dcd = new utilities.DateChooser.DateChooseDialog(new java.awt.Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        TDate1.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    TDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

private void cmdDateChoose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose3ActionPerformed
// TODO add your handling code here:
        java.awt.Point point = cmdDateChoose3.getLocation();
    point.setLocation(point.getX(), point.getY());
    utilities.DateChooser.DateChooseDialog dcd = new utilities.DateChooser.DateChooseDialog(new java.awt.Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        TDate2.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    TDate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose3ActionPerformed

private void cmdDateChoose4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose4ActionPerformed
// TODO add your handling code here:
        java.awt.Point point = cmdDateChoose4.getLocation();
    point.setLocation(point.getX(), point.getY());
    utilities.DateChooser.DateChooseDialog dcd = new utilities.DateChooser.DateChooseDialog(new java.awt.Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        TMonth1.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    TMonth1.requestFocus();
}//GEN-LAST:event_cmdDateChoose4ActionPerformed

boolean isUpload = false;

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick() ;
}//GEN-LAST:event_bntOKActionPerformed

private void cmdSystemSetupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSystemSetupMouseReleased
    // TODO add your handling code here:
    //if (SetupFTPProperty.getSetupFTPProperty() == null) {
    GetPassword frm = new GetPassword(null, true);
    frm.setVisible(true);
    if (frm.ValidPassword) {
        SetupFTPProperty.createSetup();
        SetupFTPProperty.showSetupFTPProperty();
        setVisible(false);
    }
    //}
}//GEN-LAST:event_cmdSystemSetupMouseReleased

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    new Thread(new Runnable() {

            public void run() {
                FTPMainWindow ftpmain = new FTPMainWindow();
                ftpmain.cmdUp2ActionPerformed(lb, lblStatus, pb, TextArea, cbxServer);
            }
        }).start();
}//GEN-LAST:event_jButton1ActionPerformed

private void cmdSystemSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSystemSetupActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_cmdSystemSetupActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SendStockToCenterByADSL dialog = new SendStockToCenterByADSL(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AdjustChk;
    private javax.swing.JLabel StkProcess;
    private javax.swing.JFormattedTextField TDate1;
    private javax.swing.JFormattedTextField TDate2;
    private javax.swing.JFormattedTextField TMonth1;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JCheckBox TranoutChk;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JComboBox cbxServer;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JButton cmdDateChoose3;
    private javax.swing.JButton cmdDateChoose4;
    private javax.swing.JButton cmdSystemSetup;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar pb;
    // End of variables declaration//GEN-END:variables

     class ProcessSend extends javax.swing.JDialog implements Runnable {

        public void run() {
            isUpload = false;
            Date TempDate = new Date();
            Date CurDate = new Date();
            Date EndDate = new Date();
            Calendar ca1 = new GregorianCalendar();
            Calendar ca2 = new GregorianCalendar();
            Calendar ca3 = new GregorianCalendar();
            Calendar Tempca = new GregorianCalendar();
            int CurCnt = 0;
            if (!PUtility.ChkDate(TDate1.getText())) {
                TDate1.requestFocus();
                return;
            } else {
                ca1 = PUtility.StrToCalendar(TDate1.getText());
            }
            if (!PUtility.ChkDate(TDate2.getText())) {
                TDate2.requestFocus();
                return;
            } else {
                ca2 = PUtility.StrToCalendar(TDate2.getText());
            }
            if (!PUtility.ChkDate(TMonth1.getText())) {
                TMonth1.requestFocus();
                return;
            } else {
                ca3 = PUtility.StrToCalendar(TMonth1.getText());
            }
            if (AdjustChk.isSelected()) {
//                if (!PUtility.ChkEndMonth(TMonth1.getText())) {
//                   JOptionPane.showMessageDialog(this, "ป้อนวันที่สิ้นเดือนไม่ถูกต้อง...") ;
//                   TMonth1.requestFocus();
//                   return;
//                }
            }
            CurCnt = 0;
            
            TextArea.selectAll();
            TextArea.cut();
            TextArea.setForeground(Color.BLACK);
            TextArea.append("********** Start Process **********\n");
            bntOK.setEnabled(false);
            bntExit.setEnabled(false);
            //ตรวsจสอบยอดการขายของ Cur TempDate
            ClearSendStockPath() ;
            if (TranoutChk.isSelected()) {
               TextArea.append("***ส่งข้อมูลการโอนสินค้าวันที่ (Date) "+"\n") ; 
               TextArea.append(DateFmt.format(ca1.getTime()) +" " +DateFmt.format(ca2.getTime())+"\n");
               TextArea.append("------------------------------------------" + "\n");
               if (Chknotpost(ca1,ca2)) {
                   TextArea.append("ยังมีรายการโอนที่ยังไม่ได้ทำการ POST" + "\n");
                   TextArea.append("ไม่สามารถส่งข้อมูลได้ !!!" + "\n");
               } else {
                   ProcessSendTranOut(ca1,ca2) ;
               }
            }
            if (AdjustChk.isSelected()) {
               TextArea.append("") ; 
               TextArea.append("***ส่งข้อมูลสินค้าคงเหลือสิ้นเดือน " +TMonth1.getText()+ "\n");
               TextArea.append("------------------------------------------" + "\n");
               if (Chknotadjust(ca3)) {
                   TextArea.append("ไม่พบรายการตรวจนับสินค้าสิ้นเดือน" +"\n");
                   TextArea.append("ไม่สามารถส่งข้อมูลได้ !!!" + "\n");
               } else {
                  ProcessStock(ca3);    
               }
            }
            TextArea.append("********* End of Process *********\n");
            bntOK.setEnabled(true);
            bntExit.setEnabled(true);
            isUpload = true;

            if(isUpload){
                int confirm = JOptionPane.showConfirmDialog(this, "ยืนยันการส่งข้อมูลไปสำนักงานใหญ่",
                        "", JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION){
                    FTPMainWindow ftpmain = new FTPMainWindow();
                    ftpmain.cmdUp2ActionPerformed(lb, lblStatus, pb, TextArea, cbxServer);
                }
            }
        }
        public void ClearSendStock() {
             try {
                 Statement stmt = (Statement) MySQLConnect.con.createStatement();
                 String SqlQuery = "delete from sendstock";
                 stmt.executeUpdate(SqlQuery);
             } catch (SQLException e) {
                 JOptionPane.showMessageDialog(this, e.getMessage());
             }
         }

        public void InsertSendStock(String PCode,Double TAdj,Calendar ca3) {
             try {
                 Statement stmt = (Statement) MySQLConnect.con.createStatement();
                 String SqlQuery = "insert into sendstock (s_bran,s_date,s_month,s_pcode,s_bqty,s_cost,s_amount) " +
                         "values ('"+Branch_Code+"','"+SqlDateFmt.format(new Date())+"','"+SqlDateFmt.format(ca3.getTime())+"','"+PCode+"',"+TAdj+",0,0)";
                 stmt.executeUpdate(SqlQuery);
             } catch (SQLException e) {
                 JOptionPane.showMessageDialog(this, e.getMessage());
             }
         }

        public double GetAdjStock(String PCode, Calendar Tempca3) {
             double RetValue = 0;
             try {
                 Statement stmt = (Statement) MySQLConnect.con.createStatement();
//                 String LoadTableFile = "select *from adjstock " +
//                         "left join hadjstock on hadjstock.r_no=adjstock.r_no " +
//                         "where (hadjstock.r_date='" + SqlDateFmt.format(Tempca3.getTime()) + "') and (r_pcode='" + PCode + "') order by r_entrydate,r_time";
                 String LoadTableFile = "select *from adjstock " +
                         "left join hadjstock on hadjstock.r_no=adjstock.r_no " +
                         "where (hadjstock.r_date='" + SqlDateFmt.format(Tempca3.getTime()) + "') order by r_entrydate,r_time";
                 ResultSet rec = stmt.executeQuery(LoadTableFile);
                 rec.first();
                 boolean Xfound = false ;
                 if (rec.getRow() == 0) {
                     RetValue = -1 ;
                 } else {
                     do {
                         String XPCode = rec.getString("r_pcode") ;
                         if (XPCode.equals(PCode)) {
                            RetValue = rec.getDouble("r_inhand");
                            Xfound = true ;
                         }
                     } while (rec.next());
                 }
                 rec.close();
                 stmt.close();
                 if (!Xfound) {
                     RetValue = -1 ;
                 }
             } catch (SQLException e) {
                 JOptionPane.showMessageDialog(this, e.getMessage());
             }
             return RetValue;
         }

        public void CreateSendStock(Calendar Tempca3) {
             try {
                 Statement stmt = (Statement) MySQLConnect.con.createStatement();
                 String LoadTableFile = "select *from stkfile left join product on product.pcode=stkfile.bpcode " +
                         "where (stkfile.bstk='" + STK + "')";
                 //String LoadTableFile = "select *from product inner join stkfile on product.pcode=stkfile.bpcode " +
                 //        "where (stkfile.bstk='" + STK + "') and (pstock='Y') and (pactive='Y')";
                 ResultSet rec = stmt.executeQuery(LoadTableFile);
                 rec.first();
                 int TotalTran = 0;
                 double TAdj = 0 ;
                 if (rec.getRow() == 0) {
                 } else {
                     do {
                         TAdj = 0;
                         StkProcess.setText(IntFmt.format(TotalTran)) ;
                         String PCode = rec.getString("bpcode");
                         TAdj = GetAdjStock(PCode, Tempca3);
                         if (TAdj!=-1) {
                             TotalTran ++ ;
                             InsertSendStock(PCode,TAdj,Tempca3) ;
                         }
                     } while (rec.next());
                 }
                 rec.close();
                 stmt.close();
             } catch (SQLException e) {
                 JOptionPane.showMessageDialog(this, e.getMessage());
             }
         }
        public void ClearTempPath() {
            File PathTemp = new File(TempPath);
            File PathTempConvert = new File(PathConvert);
            DirectoryUtility dirUtil = new DirectoryUtility();
            File fl[] = PathTemp.listFiles();
            File flConvert[] = PathTempConvert.listFiles();
            try {
                for (int i = 0; i < fl.length; i++) {
                    dirUtil.deleteDir(fl[i]);
                }
                if (!dirUtil.deleteDir(PathTemp)) {
                    JOptionPane.showMessageDialog(this, "Can'n Delete" + TempPath);
                }
                if (!dirUtil.createDir(PathTemp)) {
                    JOptionPane.showMessageDialog(this, "Can'n Create" + TempPath);
                }
                for (int i = 0; i < flConvert.length; i++) {
                    dirUtil.deleteDir(flConvert[i]);
                }
                if (!dirUtil.deleteDir(PathTempConvert)) {
                    JOptionPane.showMessageDialog(this, "Can'n Delete" + PathTempConvert);
                }
                if (!dirUtil.createDir(PathTempConvert)) {
                    JOptionPane.showMessageDialog(this, "Can'n Create" + PathTempConvert);
                }
            } catch (IOException ex) {
                Logger.getLogger(UpdateDataFromCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void ClearSendStockPath() {
            File PathTemp = new File(StockPath);
            DirectoryUtility dirUtil = new DirectoryUtility();
            File fl[] = PathTemp.listFiles();
            try {
                for (int i = 0; i < fl.length; i++) {
                    dirUtil.deleteDir(fl[i]);
                }
                if (!dirUtil.deleteDir(PathTemp)) {
                    JOptionPane.showMessageDialog(this, "Can'n Delete" + TempPath);
                }
                if (!dirUtil.createDir(PathTemp)) {
                    JOptionPane.showMessageDialog(this, "Can'n Create" + TempPath);
                }
            } catch (IOException ex) {
                Logger.getLogger(UpdateDataFromCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void copyfile(String srFile, String dtFile) {
            try {
                File f1 = new File(srFile);
                File f2 = new File(dtFile);
                InputStream in = new FileInputStream(f1);
                OutputStream out = new FileOutputStream(f2);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                System.out.println("File copied.");
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage() + " in the specified directory.");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public int GetActMonth(Date TempDate) {
            int ReturnVal = 0;
            Date Acterm = new Date();
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from company ";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    Acterm = rec.getDate("accterm");
                    int ActermYear = Integer.parseInt(SqlDateFmt.format(Acterm).substring(0, 4));
                    int TempDateYear = Integer.parseInt(SqlDateFmt.format(TempDate).substring(0, 4));
                    if (ActermYear == TempDateYear) {
                        ReturnVal = Integer.parseInt(SqlDateFmt.format(TempDate).substring(5, 7)) + 12;
                    } else {
                        if (ActermYear == TempDateYear + 1) {
                            ReturnVal = Integer.parseInt(SqlDateFmt.format(TempDate).substring(5, 7));
                        } else {
                            ReturnVal = 0;
                        }
                    }
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            return ReturnVal;
        }

        public void ConverCharSet(String inFile, String inCharsetName, String outFile, String outCharsetName) {
            DirectoryUtility dirUtil = new DirectoryUtility();
            try {
                dirUtil.getFileAndCreateDir(outFile);
            } catch (Exception ex) {
                Logger.getLogger(UpdateDataFromCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                InputStreamReader in = new InputStreamReader(
                        new FileInputStream(inFile), inCharsetName);
                OutputStreamWriter out = new OutputStreamWriter(
                        new FileOutputStream(outFile), outCharsetName);
                int c = in.read();
                int n = 0;
                while (c != -1) {
                    out.write(c);
                    n++;
                    c = in.read();
                }
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        }

        public void ProcessSendTranOut(Calendar ca1,Calendar ca2) {
            ClearTempPath();
            SendTranOut(ca1,ca2);
            String FileName = Branch_Code + "_" + "TR" + "_" + ZipDateFmt.format(new Date()) + ".zip";
            String FileZip = DataZipPath + "/" + FileName;
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(PathConvert, FileZip);
            TextArea.append(FileName + "....OK" + "\n");
        //Mark ZipFile =>archive
        }
        public void ProcessStock(Calendar ca3) {
            ClearTempPath();
            ClearSendStock() ;
            CreateSendStock(ca3) ;
            SendStock(ca3);
            String FileName = Branch_Code + "_" + "ST" + "_" + StockDateFmt.format(ca3.getTime()) + ".zip";
            String FileZip = DataZipPath + "/" + FileName;
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(PathConvert, FileZip);
            TextArea.append(FileName + "....OK" + "\n");
        //Mark ZipFile =>archive
        }

        public void SendTranOut(Calendar ca1,Calendar ca2) {
            String FileName = "TR" + FileDateFmt.format(new Date()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            String TempRecNo = "" ;
            int TotalRec = 0 ;
            int TotalTran = 0 ;
            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from htranout left join tranout on htranout.r_no=tranout.r_no where (htranout.r_date>='" + SqlDateFmt.format(ca1.getTime()) + "') and (htranout.r_date<='"+SqlDateFmt.format(ca2.getTime())+"') order by htranout.r_date,htranout.r_no,tranout.r_que";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    TempRecNo = rec.getString("r_no") ;
                    TotalRec++ ;
                    do {
                        if (!TempRecNo.equals(rec.getString("r_no"))) {
                            TempRecNo = rec.getString("r_no") ;
                            TotalRec++ ;
                        }
                        TotalTran ++ ; 
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(new Date()) + "\u0009" +
                                rec.getString("r_no") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("r_date")) + "\u0009" +
                                rec.getString("r_remark") + "\u0009" +
                                rec.getString("r_bran") + "\u0009" +
                                IntFmt.format(rec.getInt("r_total")) + "\u0009" +
                                IntFmt.format(rec.getInt("r_que")) + "\u0009" +
                                rec.getString("r_pcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_qty")) + "\u0009" +
                                DecFmt.format(0) + "\u0009" +
                                DecFmt.format(0) + "\u0009" +
                                rec.getString("r_unit") + "\u0009" +
                                rec.getString("r_user") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("r_entrydate"))+"\u0009"+
                                rec.getString("r_time")  ;
                                
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            TextArea.append("***จำนวนใบโอนทั้งสิ้น  : " +IntFmt.format(TotalRec)+" ใบ"+ "\n");
            TextArea.append("***จำนวนรายการทั้งสิ้น : " +IntFmt.format(TotalTran)+"รายการ"+"\n") ;
            TextArea.append("------------------------------------------" + "\n");
            if (!FileCharset.equals("UTF-8")) {
                String inFile = TempPath + '/' + FileName;
                String inCharset = "UTF-8";
                String outFile = PathConvert + '/' + FileName;
                String outCharset = FileCharset;
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + FileName;
            } else {
                centFileName = TempPath + '/' + FileName;
            }
        }
        public void SendStock(Calendar ca3) {
            String FileName = "ST" + StockDateFmt.format(ca3.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            int TotalTran = 0 ;
            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from sendstock " ;
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    //File fObject = new File(centFileName);
                    //if (!fObject.canRead()) {
                    //    TextWrite.writeToText(centFileName, null);
                    //}
                } else {
                    do {
                        //String TempStr = "";
                        TotalTran++ ;
                        //TempStr = TempStr + rec.getString("s_bran") + "\u0009" +
                        //        SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                        //        SqlDateFmt.format(rec.getDate("s_month")) + "\u0009" +
                        //        rec.getString("s_pcode") + "\u0009" +
                        //        DecFmt.format(rec.getDouble("s_bqty")) + "\u0009" +
                        //        DecFmt.format(rec.getDouble("s_cost")) + "\u0009" +
                        //        DecFmt.format(rec.getDouble("s_amount")) ;
                        //TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } 
            //FileName = "TID" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            centFileName = "/tmp" + '/' + FileName;
            File fObject = new File(centFileName);
            if (fObject.exists()) {
                fObject.delete();
            }
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *into outfile '" + centFileName + "' from sendstock ";
                stmt.execute(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            
            TextArea.append("***จำนวนรายการทั้งสิ้น : " +IntFmt.format(TotalTran)+"รายการ"+"\n") ;
            TextArea.append("------------------------------------------" + "\n");
            if (!FileCharset.equals("UTF-8")) {
                //String inFile = TempPath + '/' + FileName;
                String inFile = "/tmp" + '/' + FileName;
                String inCharset = "UTF-8";
                String outFile = PathConvert + '/' + FileName;
                String outCharset = FileCharset;
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + FileName;
            } else {
                centFileName = TempPath + '/' + FileName;
            }
        }
    }
}

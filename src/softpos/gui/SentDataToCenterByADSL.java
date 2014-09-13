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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import other.FTPUtility;
import other.SetupFTPProperty;
import utilities.MySQLConnect;
import write_to_text.TextWriter;
import zip_utility.ZipUnzip;

public class SentDataToCenterByADSL extends javax.swing.JDialog {
    SimpleDateFormat DateTimeFmt = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat SqlDateTimeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    SimpleDateFormat FileDateFmt = new SimpleDateFormat("yyMMdd", Locale.ENGLISH) ;
    SimpleDateFormat ZipDateFmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH) ;
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
    String DataZipPath = "C:/spapplication/archive";
    String PathConvert = "C:/spapplication/tempconvert";
    String FileCharset = "TIS-620";
    String _User = "XXXXXX";
    String STK = "A1" ;
    boolean isConnection ;
    int complete;
    int incomplete;
    private boolean cmdSumAct = false;
    boolean Make_Download;
    boolean EndProcess;

    /** Creates new form SentDataToCenterByADSL */
    public SentDataToCenterByADSL(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        ftp = new FTPUtility();
        LoadDataFromFile();
        PublicVar.SendDataAuto = false ;
        PublicVar.SendDataComplete = false ;
        TDate1.setText(DateFmt.format(date));
        TDate2.setText(DateFmt.format(date));
        SelectAllCheck() ;
        StkProcess.setText("") ;
        TDate1.selectAll() ;
        TDate1.requestFocus();
        loadDefaultRadio();
    }
    void loadDefaultRadio(){
        String file1 = "C:/spapplication/dbconfig/ftp_local.ini";
        String file2 = "C:/spapplication/dbconfig/ftp_main.ini";
        String file3 = "C:/spapplication/dbconfig/ftp_secondary.ini";
        prop = new Properties();
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
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public SentDataToCenterByADSL(java.awt.Frame parent, boolean modal ,Date SaleDate1 ,Date SaleDate2) {
        super(parent, modal);
        initComponents();
        
        ftp = new FTPUtility();
        LoadDataFromFile();
        TDate1.setText(DateFmt.format(SaleDate1));
        TDate2.setText(DateFmt.format(SaleDate2));
        DailySaleCheck.setSelected(true);
        MemberCheck.setSelected(true);
        ARCheck.setSelected(true);
        GiftCheck.setSelected(true);
        EJCheck.setSelected(true);
        StkProcess.setText("") ;
        PublicVar.SendDataAuto = true ;
        PublicVar.SendDataComplete = false ;
        loadDefaultRadio();
        if (CheckConnection()) {
           bntOKClick() ;
        }
        
    }
    public void SelectAllCheck() {
        DailySaleCheck.setSelected(true);
        MemberCheck.setSelected(true);
        ARCheck.setSelected(true);
        GiftCheck.setSelected(true);
        EJCheck.setSelected(true);
    }
    public void ProcessAuto() {
        if (CheckConnection()) {
           bntOKClick() ;
        }
    }
    public void bntOKClick() {
        Thread SendData = new Thread(new ProcessSend());
        SendData.start();
        
    }
    public void bntExitClick() {
        PUtility.ShowMsg("On Close Dialog...");
        this.dispose();
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
        jLabel2 = new javax.swing.JLabel();
        TDate1 = new javax.swing.JFormattedTextField();
        TDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose2 = new javax.swing.JButton();
        cmdDateChoose3 = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        DailySaleCheck = new javax.swing.JCheckBox();
        MemberCheck = new javax.swing.JCheckBox();
        ARCheck = new javax.swing.JCheckBox();
        GiftCheck = new javax.swing.JCheckBox();
        EJCheck = new javax.swing.JCheckBox();
        StkProcess = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cbxServer = new javax.swing.JComboBox();
        cmdSystemSetup = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        pb = new javax.swing.JProgressBar();
        bntSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jPanel1.setBackground(new java.awt.Color(200, 241, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 73, 18), 5, true));

        jLabel1.setFont(new java.awt.Font("Norasi", 1, 24)); // NOI18N
        jLabel1.setText("โปรแกรมสร้างไฟล์ส่งข้อมูลไปสำนักงานใหญ่ (BY ADSL)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel2.setText("ส่งข้อมูลประจำวันที่ : ");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(TDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmdDateChoose3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        bntOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu.jpg"))); // NOI18N
        bntOK.setText("F5-สร้าง Text File และส่งข้อมูลไปสำนักงานใหญ่ ");
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
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });
        bntExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bntExitKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "เลือกหัวข้อที่จะทำการส่งข้อมูล", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 1, 14))); // NOI18N

        DailySaleCheck.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        DailySaleCheck.setText("ข้อมูลยอดการขายประจำวัน (Daily Sales Data)");
        DailySaleCheck.setFocusable(false);
        DailySaleCheck.setRequestFocusEnabled(false);
        DailySaleCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DailySaleCheckActionPerformed(evt);
            }
        });

        MemberCheck.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        MemberCheck.setText("ข้อมูลสมาชิก (Member Data)");
        MemberCheck.setFocusable(false);
        MemberCheck.setRequestFocusEnabled(false);
        MemberCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemberCheckActionPerformed(evt);
            }
        });

        ARCheck.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        ARCheck.setText("ข้อมูลลูกหนี้ภายนอก (AR Data)");
        ARCheck.setFocusable(false);
        ARCheck.setRequestFocusEnabled(false);
        ARCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARCheckActionPerformed(evt);
            }
        });

        GiftCheck.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        GiftCheck.setText("ข้อมูลบัตรของขวัญ (Gift Voucher)");
        GiftCheck.setFocusable(false);
        GiftCheck.setRequestFocusEnabled(false);
        GiftCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GiftCheckActionPerformed(evt);
            }
        });

        EJCheck.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        EJCheck.setText("ข้อมูล EJ (Electronic Jounal)");
        EJCheck.setFocusable(false);
        EJCheck.setRequestFocusEnabled(false);
        EJCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EJCheckActionPerformed(evt);
            }
        });

        StkProcess.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        StkProcess.setForeground(new java.awt.Color(241, 25, 14));
        StkProcess.setText("StockProcess");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MemberCheck)
                    .addComponent(EJCheck)
                    .addComponent(GiftCheck)
                    .addComponent(ARCheck)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(DailySaleCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StkProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DailySaleCheck)
                    .addComponent(StkProcess))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MemberCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ARCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GiftCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EJCheck)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, null, new java.awt.Color(1, 1, 1)));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lb.setText("0/0 Byte");
        jPanel12.add(lb);

        lblStatus.setForeground(new java.awt.Color(255, 0, 9));
        lblStatus.setText("lblStatus");
        jPanel12.add(lblStatus);

        bntSend.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntSend.setText("ส่งข้อมูล จาก achive ไปสำนักงานใหญ่");
        bntSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSendActionPerformed(evt);
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addComponent(bntSend, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntSend, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbxServer, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(cmdSystemSetup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void TDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDate1KeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
        TDate2.selectAll(); 
        TDate2.requestFocus();        
    }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        dispose();
    }
}//GEN-LAST:event_TDate1KeyPressed

private void TDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDate2KeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
       TDate1.selectAll() ; 
       TDate1.requestFocus();
    }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        TDate1.requestFocus();
    }
}//GEN-LAST:event_TDate2KeyPressed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
// TODO add your handling code here:
    java.awt.Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    utilities.DateChooser.DateChooseDialog dcd = new utilities.DateChooser.DateChooseDialog(new java.awt.Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        TDate2.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    TDate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

private void cmdDateChoose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose3ActionPerformed
// TODO add your handling code here:
    java.awt.Point point = cmdDateChoose3.getLocation();
    point.setLocation(point.getX(), point.getY());
    utilities.DateChooser.DateChooseDialog dcd = new utilities.DateChooser.DateChooseDialog(new java.awt.Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    if (dcd.getSelectDate() != null) {
        TDate1.setText(DateFmt.format(dcd.getSelectDate().getTime()));
    }
    TDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose3ActionPerformed

private boolean isCheckServer = false;

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    if (CheckConnection()) {
       bntOKClick();
    }
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    this.dispose();
}//GEN-LAST:event_bntExitActionPerformed

private void cmdSystemSetupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSystemSetupMouseReleased
    // TODO add your handling code here:
    //if (SetupFTPProperty.getSetupFTPProperty() == null) {
    GetPassword frm = new GetPassword(null, true);
    frm.setVisible(true);
    if (frm.ValidPassword) {
        SetupFTPProperty.createSetup();
        SetupFTPProperty.showSetupFTPProperty();
        //setVisible(false);
    }
    //}
}//GEN-LAST:event_cmdSystemSetupMouseReleased

private void bntSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSendActionPerformed
    new Thread(new Runnable() {
            public void run() {
                FTPMainWindow ftpmain = new FTPMainWindow();
                ftpmain.cmdUp1ActionPerformed(TextArea, TDate1.getText(), TDate2.getText(),
                                    lb, lblStatus, pb, cbxServer);
            }
        }).start();
    
}//GEN-LAST:event_bntSendActionPerformed

private void DailySaleCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DailySaleCheckActionPerformed
    // TODO add your handling code here:
    SelectAllCheck() ;
}//GEN-LAST:event_DailySaleCheckActionPerformed

private void MemberCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberCheckActionPerformed
    // TODO add your handling code here:
    SelectAllCheck() ;
}//GEN-LAST:event_MemberCheckActionPerformed

private void ARCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARCheckActionPerformed
    // TODO add your handling code here:
    SelectAllCheck() ;
}//GEN-LAST:event_ARCheckActionPerformed

private void GiftCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GiftCheckActionPerformed
    // TODO add your handling code here:
    SelectAllCheck() ;
}//GEN-LAST:event_GiftCheckActionPerformed

private void EJCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EJCheckActionPerformed
    // TODO add your handling code here:
    SelectAllCheck() ;
}//GEN-LAST:event_EJCheckActionPerformed

private void bntExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bntExitKeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_bntExitKeyPressed

 public Boolean ChkSaleTransection(Calendar Tempca) {
    Boolean RetVal = false ;
   try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from s_sale where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    return RetVal;
}
 public void LoadDataFromFile() {
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
 private boolean CheckConnection() {
    loadConfFTPServer();
    if(isCheckServer){
        String server = prop.getProperty("host1");
        String user = prop.getProperty("usersend1");
        String pass = prop.getProperty("passsend1");
        int port = Integer.parseInt(prop.getProperty("port1"));

        ftp.setProgress(lb, pb);
        TextArea.append("\nกรุณารอสักครู่ระบบกำลังทำการติดต่อ FTP Server...\n");
        if (ftp.connect(server, user, pass, port)) {
            TextArea.append("การเชื่อมต่อสำเร็จ : "+server+"\n");
            return true ;
        } else {
            TextArea.append("การเชื่อมล้มเหลว : "+server+"\n");
            return false ;
        }
    }else{
        return true;
    }
}
private void loadConfFTPServer(){
        if(((String)cbxServer.getSelectedItem()).equals("Local FTP Server")){
            loadLocalServer();
            isCheckServer = false;
        }else if(((String)cbxServer.getSelectedItem()).equals("Main FTP Server")){
            loadMainServer();
            isCheckServer = true;
        }else{
            loadSecondaryServer();
            isCheckServer = true;
        }
        System.out.println("LoadConfFTPServer");
    }
private void loadLocalServer(){
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_local.ini");
            prop.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : "+"/spapplication/dbconfig/ftp_local.ini");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : "+"/spapplication/dbconfig/ftp_local.ini");
        }
    }
private void loadMainServer(){
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_main.ini");
            prop.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : "+"/spapplication/dbconfig/ftp_main.ini");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : "+"/spapplication/dbconfig/ftp_main.ini");
        }
    }
private void loadSecondaryServer(){
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_secondary.ini");
            prop.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : "+"/spapplication/dbconfig/ftp_main.ini");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : "+"/spapplication/dbconfig/ftp_main.ini");
        }
    }

public void UpdateOperationSendData(Date SaleDate) {
    Calendar CurDate = new GregorianCalendar();
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String UpdatePromotion = "update dayoperation set sendsaledata=?,sendsaledatauser=?,sendsaledatatime=? where operationdate=? ";
            PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(UpdatePromotion);
            prm.setString(1, "Y");
            prm.setString(2, PublicVar._User);
            prm.setString(3, SqlDateTimeFmt.format(CurDate.getTime()));
            prm.setString(4, SqlDateFmt.format(SaleDate));
            prm.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            PUtility.ShowError(e.getMessage());
        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SentDataToCenterByADSL dialog = new SentDataToCenterByADSL(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox ARCheck;
    private javax.swing.JCheckBox DailySaleCheck;
    private javax.swing.JCheckBox EJCheck;
    private javax.swing.JCheckBox GiftCheck;
    private javax.swing.JCheckBox MemberCheck;
    private javax.swing.JLabel StkProcess;
    private javax.swing.JFormattedTextField TDate1;
    private javax.swing.JFormattedTextField TDate2;
    public static javax.swing.JTextArea TextArea;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntSend;
    private javax.swing.JComboBox cbxServer;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JButton cmdDateChoose3;
    private javax.swing.JButton cmdSystemSetup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar pb;
    // End of variables declaration//GEN-END:variables
    Properties prop;
    FTPUtility ftp ;

    class ProcessSend extends javax.swing.JDialog implements Runnable {
        public void run() {
            Date TempDate = new Date();
            Date CurDate = new Date();
            Date EndDate = new Date();
            Calendar ca1 = new GregorianCalendar();
            Calendar ca2 = new GregorianCalendar();
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
            Tempca = ca1;
            CurCnt = 0;
            TextArea.selectAll();
            TextArea.cut();
            TextArea.setForeground(Color.BLACK);
            TextArea.append("********** Start Process **********\n");
            while (Tempca.compareTo(ca2) <= 0) {
                bntOK.setEnabled(false);
                bntExit.setEnabled(false);
                TDate1.setEditable(false) ;
                TDate2.setEditable(false) ;
                cmdDateChoose3.setEnabled(false) ;
                cmdDateChoose2.setEnabled(false);
                cbxServer.setEnabled(false) ;
                bntSend.setEnabled(false) ;

                CurCnt++;
                //ตรวจสอบยอดการขายของ Cur TempDate
                if (ChkSaleTransection(Tempca)) {
                    TextArea.append("***ส่งข้อมูลการขายประจำวันที่ " + DateFmt.format(Tempca.getTime()) + "\n");
                    TextArea.append("------------------------------------------" + "\n");
                    if (DailySaleCheck.isSelected()) {
                        ProcessDailySale(Tempca);
                    }
                    if (MemberCheck.isSelected()) {
                        ProcessMember(Tempca);
                    }
                    if (ARCheck.isSelected()) {
                        ProcessAr(Tempca);
                    }
                    if (GiftCheck.isSelected()) {
                        ProcrssGift(Tempca);
                    }
                    if (EJCheck.isSelected()) {
                        ProcessEJ(Tempca);
                    }

                    //ส่งข้อมูลไปสำนักงานใหญ่
                    FTPMainWindow ftpmain = new FTPMainWindow();
                    ftpmain.cmdUp1ActionPerformed(TextArea, TDate1.getText(), TDate2.getText(),
                        lb, lblStatus, pb, cbxServer);
                    PublicVar.SendDataComplete = true ;
                    UpdateOperationSendData(Tempca.getTime()) ;
                } else {
                    //JOptionPane.showMessageDialog(this, "ไม่พบข้อมูลการขายประจำวันที่ "+DateFmt.format(Tempca.getTime()));
                    TextArea.setForeground(Color.RED);
                    TextArea.append("Error..ไม่พบข้อมูลการขายประจำวันที่ " + DateFmt.format(Tempca.getTime()) + "\n");
                    TextArea.setForeground(Color.BLACK);
                }
                Tempca.add(Calendar.DATE, 1);
            }
            TextArea.append("********* End of Process *********\n");
            TextArea.append("");
            TextArea.append("กรุณากด ปุ่ม ออก(Exit)  เพื่อทำงานต่อไป...");
            if (PublicVar.SendDataAuto) {
                bntExit.setEnabled(true);
            } else {
                bntOK.setEnabled(true);
                bntExit.setEnabled(true);
                TDate1.setEditable(true) ;
                TDate2.setEditable(true) ;
                cmdDateChoose3.setEnabled(true) ;
                cmdDateChoose2.setEnabled(true);
                cbxServer.setEnabled(true) ;
                bntSend.setEnabled(true) ;
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

        public void ProcessDailySale(Calendar Tempca) {
            ClearTempPath();
            SendPlu(Tempca);
            SendTerminal(Tempca);
            SendCashier(Tempca);
            SendCredit(Tempca);
            SendCrDetail(Tempca);
            SendCupon(Tempca);
            SendBillAr(Tempca);
            SendTAr(Tempca);
            SendTCrAr(Tempca);
            SendRjFile(Tempca);
            SendInv(Tempca);
            SendHourly(Tempca);
            SendPromotion(Tempca);
            SendBillno(Tempca) ;
            SendS_Tran(Tempca) ;
            SendS_Vat(Tempca) ;
            SendS_Void(Tempca) ;
            SendS_Kictran(Tempca) ;
            //ForCharge
            SendBillnoCharge(Tempca) ;
            SendS_TranCharge(Tempca) ;
            //************************
            SendDayoperation(Tempca) ;
            SendUserChange(Tempca) ;
            SendStock(Tempca);
            SendHeader(Tempca) ;
            String FileName = Branch_Code + "_" + "DAI" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            String FileZip = DataZipPath + "/" + Branch_Code + "_" + "DAI" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(PathConvert, FileZip);
            TextArea.append(FileName + "....OK" + "\n");
        //Mark ZipFile =>archive
        }

        public void ProcessMember(Calendar Tempca) {
            ClearTempPath();
            SendHeader(Tempca) ;
            SendTMU(Tempca);
            SendTMP(Tempca);
            SendTM(Tempca);
            SendTMA(Tempca);
            SendSMS(Tempca) ;
            String FileName = Branch_Code + "_" + "MEM" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            String FileZip = DataZipPath + "/" + Branch_Code + "_" + "MEM" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            TextArea.append(FileName + "....OK" + "\n");
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(PathConvert, FileZip);
        //Make Zip =>archive
        }

        public void ProcessAr(Calendar Tempca) {
            ClearTempPath();
            SendHeader(Tempca) ;
            SendAR(Tempca);
            String FileName = Branch_Code + "_" + "ACR" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            String FileZip = DataZipPath + "/" + Branch_Code + "_" + "ACR" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            TextArea.append(FileName + "....OK" + "\n");
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(PathConvert, FileZip);
        //Make Zip =>archive
        }

        public void ProcrssGift(Calendar Tempca) {
            ClearTempPath();
            SendHeader(Tempca) ;
            SendGift(Tempca);
            String FileName = Branch_Code + "_" + "GIF" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            String FileZip = DataZipPath + "/" + Branch_Code + "_" + "GIF" + "_" + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            TextArea.append(FileName + "....OK" + "\n");
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(PathConvert, FileZip);
        //Make Zip =>archive
        }

        public void ProcessEJ(Calendar Tempca) {
            String EJZipPath = "C:/spapplication/ejpath/" + ZipDateFmt.format(Tempca.getTime());
            String FileName = Branch_Code + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            String FileZip = DataZipPath + "/" + Branch_Code + ZipDateFmt.format(Tempca.getTime()) + ".zip";
            TextArea.append(FileName + "....OK" + "\n");
            ZipUnzip uzip = new ZipUnzip();
            uzip.zip(EJZipPath, FileZip);
        //Make Zip =>archive

        }

        public void SendPlu(Calendar Tempca) {
            String FileName = "TP" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_sale left join product on pcode=s_pcode where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        double s_Price = 0.0;
                        if (rec.getDouble("s_qty") > 0) {
                            s_Price = rec.getDouble("s_amt") / rec.getDouble("s_qty");
                        }
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("s_pcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("e_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("e_amt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("e_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("e_net")) + "\u0009" +
                                DecFmt.format(rec.getDouble("t_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("t_amt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("t_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("t_net")) + "\u0009" +
                                DecFmt.format(rec.getDouble("d_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("d_amt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("d_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("d_net")) + "\u0009" +
                                DecFmt.format(rec.getDouble("p_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("p_amt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("p_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("p_net")) + "\u0009" +
                                DecFmt.format(rec.getDouble("w_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("w_amt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("w_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("w_net")) + "\u0009" +
                                DecFmt.format(rec.getDouble("s_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("s_amt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("s_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("s_net")) + "\u0009" +
                                DecFmt.format(s_Price) + "\u0009" +
                                _User + "\u0009" +
                                ShortTimeFmt.format(new Date());
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendTerminal2(Calendar Tempca) {
            String FileName = "TT" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from terminal where t_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("t_macno") + "\u0009" +
                                DecFmt.format(rec.getDouble("deptsum")) + "\u0009" +
                                DecFmt.format(rec.getDouble("dsales")) + "\u0009" +
                                DecFmt.format(rec.getDouble("salevat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("salenon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("svat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pcust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ncash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ncupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nmisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("misc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npaidout")) + "\u0009" +
                                DecFmt.format(rec.getDouble("paidout")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npaidin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("paidin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdiscb")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdiscb")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nvoid")) + "\u0009" +
                                DecFmt.format(rec.getDouble("void")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("refund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ngenrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("genrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nitemdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("itemdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdiscy")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdiscy")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdisc")) + "\u0009" +
                                "0" + "\u0009" +
                                "0" + "\u0009" +
                                DecFmt.format(rec.getDouble("ncharge")) + "\u0009" +
                                DecFmt.format(rec.getDouble("charge")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nservice")) + "\u0009" +
                                DecFmt.format(rec.getDouble("service")) + "\u0009" +
                                "0" + "\u0009" +
                                "XXXXXX" + "\u0009" +
                                SqlDateFmt.format(date) + "\u0009" +
                                DecFmt.format(rec.getDouble("ntrain")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disctrain")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ndisccu")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disccu")) + "\u0009" +
                                DecFmt.format(rec.getDouble("discdayend")) + "\u0009" +
                                "0" + "\u0009" +
                                DecFmt.format(rec.getDouble("neatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("eatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("eatinnet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ceatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ntakeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("takeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("takeawaynet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ctakeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ndelivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("delivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("deliverynet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cdelivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pintonet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cpinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nwhole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("whole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("wholenet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cwhole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nar")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ar"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendTerminal(Calendar Tempca) {
            String FileName = "TT" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from terminal where t_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("t_macno") + "\u0009" +
                                DecFmt.format(rec.getDouble("deptsum")) + "\u0009" +
                                DecFmt.format(rec.getDouble("dsales")) + "\u0009" +
                                DecFmt.format(rec.getDouble("salevat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("salenon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("svat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pcust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ncash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ncupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nmisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("misc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npaidout")) + "\u0009" +
                                DecFmt.format(rec.getDouble("paidout")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npaidin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("paidin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdiscb")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdiscb")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nvoid")) + "\u0009" +
                                DecFmt.format(rec.getDouble("void")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("refund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ngenrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("genrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nitemdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("itemdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdiscy")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdiscy")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdisc")) + "\u0009" +
                                "0" + "\u0009" +
                                "0" + "\u0009" +
                                DecFmt.format(rec.getDouble("ncharge")) + "\u0009" +
                                DecFmt.format(rec.getDouble("charge")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nservice")) + "\u0009" +
                                DecFmt.format(rec.getDouble("service")) + "\u0009" +
                                "0" + "\u0009" +
                                "XXXXXX" + "\u0009" +
                                SqlDateFmt.format(date) + "\u0009" +
                                DecFmt.format(rec.getDouble("ntrain")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disctrain")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ndisccu")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disccu")) + "\u0009" +
                                DecFmt.format(rec.getDouble("discdayend")) + "\u0009" +
                                "0" + "\u0009" +
                                DecFmt.format(rec.getDouble("neatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("eatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("eatinnet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ceatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ntakeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("takeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("takeawaynet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ctakeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ndelivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("delivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("deliverynet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cdelivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pintonet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cpinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nwhole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("whole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("wholenet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cwhole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nar")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ar"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendCashier(Calendar Tempca) {
            String FileName = "TC" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from cashier where t_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("t_cashno") + "\u0009" +
                                DecFmt.format(rec.getDouble("deptsum")) + "\u0009" +
                                DecFmt.format(rec.getDouble("dsales")) + "\u0009" +
                                DecFmt.format(rec.getDouble("salevat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("salenon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("svat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pcust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ncash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ncupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nmisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("misc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npaidout")) + "\u0009" +
                                DecFmt.format(rec.getDouble("paidout")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npaidin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("paidin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdiscb")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdiscb")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nvoid")) + "\u0009" +
                                DecFmt.format(rec.getDouble("void")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("refund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ngenrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("genrefund")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nitemdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("itemdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdiscy")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdiscy")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nsubdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("subdisc")) + "\u0009" +
                                "0" + "\u0009" +
                                "0" + "\u0009" +
                                DecFmt.format(rec.getDouble("ncharge")) + "\u0009" +
                                DecFmt.format(rec.getDouble("charge")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nservice")) + "\u0009" +
                                DecFmt.format(rec.getDouble("service")) + "\u0009" +
                                "0" + "\u0009" +
                                "XXXXXX" + "\u0009" +
                                SqlDateFmt.format(date) + "\u0009" +
                                DecFmt.format(rec.getDouble("ntrain")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disctrain")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ndisccu")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disccu")) + "\u0009" +
                                DecFmt.format(rec.getDouble("discdayend")) + "\u0009" +
                                "0" + "\u0009" +
                                DecFmt.format(rec.getDouble("neatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("eatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("eatinnet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ceatin")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ntakeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("takeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("takeawaynet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ctakeaway")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ndelivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("delivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("deliverynet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cdelivery")) + "\u0009" +
                                DecFmt.format(rec.getDouble("npinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pintonet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cpinto")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nwhole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("whole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("wholenet")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cwhole")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nar")) + "\u0009" +
                                DecFmt.format(rec.getDouble("ar"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendCredit(Calendar Tempca) {
            String FileName = "TCR" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select s_date,b_macno,b_cashier,b_crcode1,count(b_cramt1),sum(b_cramt1) from s_invoice " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "' and b_cramt1<>0 and b_void<>'V' " +
                        "group by b_macno,b_crcode1 order by b_macno,b_crcode1";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("b_macno") + "\u0009" +
                                "" + "\u0009" +
                                rec.getString("b_crcode1") + "\u0009" +
                                DecFmt.format(rec.getDouble("count(b_cramt1)")) + "\u0009" +
                                DecFmt.format(rec.getDouble("sum(b_cramt1)"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select s_date,b_macno,b_cashier,b_crcode1,count(b_cramt1),sum(b_cramt1) from s_invoice " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "' and b_cramt1<>0 and b_void<>'V' " +
                        "group by b_cashier,b_crcode1 order by b_cashier,b_crcode1";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("b_cashier") + "\u0009" +
                                "" + "\u0009" +
                                rec.getString("b_crcode1") + "\u0009" +
                                DecFmt.format(rec.getDouble("count(b_cramt1)")) + "\u0009" +
                                DecFmt.format(rec.getDouble("sum(b_cramt1)"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendCrDetail(Calendar Tempca) {
            String FileName = "TCD" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select b_refno,b_crcode1,b_cramt1,b_appcode1,DECODE(b_crcardno1,'snpfood') as cardno1,b_macno,b_cashier from s_credit " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("b_refno") + "\u0009" +
                                "" + "\u0009" +
                                rec.getString("b_crcode1") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cramt1")) + "\u0009" +
                                rec.getString("b_appcode1") + "\u0009" +
                                rec.getString("cardno1") + "\u0009" +
                                rec.getString("b_macno") + "\u0009" +
                                rec.getString("b_cashier") + "\u0009" +
                                "";
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendCupon(Calendar Tempca) {
            String FileName = "TCU" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_cupon " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "' and refund<>'V' ";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("macno") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("cucode") + "\u0009" +
                                DecFmt.format(rec.getDouble("cuquan")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cuamt"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendBillAr(Calendar Tempca) {
            String FileName = "TBA" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_billar " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("ref_no") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("ondate")) + "\u0009" +
                                DecFmt.format(rec.getDouble("stotal")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cupon")) + "\u0009" +
                                DecFmt.format(rec.getDouble("credit")) + "\u0009" +
                                rec.getString("terminal") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("fat") + "\u0009" +
                                rec.getString("uservoid");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendTAr(Calendar Tempca) {
            String FileName = "TTA" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_tar " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("ref_no") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("billdate")) + "\u0009" +
                                rec.getString("arcode") + "\u0009" +
                                rec.getString("billno") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("billdate")) + "\u0009" +
                                DecFmt.format(rec.getDouble("amount")) + "\u0009" +
                                rec.getString("terminal") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("fat") ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendTCrAr(Calendar Tempca) {
            String FileName = "TCA" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_tcrar " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("ref_no") + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("crcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("crcnt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("cramt")) + "\u0009" +
                                rec.getString("crapp") + "\u0009" +
                                rec.getString("crid") + "\u0009" +
                                rec.getString("terminal") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("fat");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendRjFile(Calendar Tempca) {
            String FileName = "TRJ" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from rjfile " +
                        "where tdate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr +
                                rec.getString("rjno") + "\u0009" +
                                rec.getString("sub") + "\u0009" +
                                DecFmt.format(rec.getDouble("mac1")) + "\u0009" +
                                DecFmt.format(rec.getDouble("mac2")) + "\u0009" +
                                DecFmt.format(rec.getDouble("mac3")) + "\u0009" +
                                DecFmt.format(rec.getDouble("total")) + "\u0009" +
                                DecFmt.format(rec.getDouble("nettotal")) + "\u0009" +
                                DecFmt.format(rec.getDouble("vat"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendInv(Calendar Tempca) {
            String FileName = "TI" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_invoice " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        String TPay = "";
                        if (rec.getDouble("b_cramt1") > 0) {
                            TPay = "1";
                        } else {
                            TPay = "2";
                        }
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("b_ondate")) + "\u0009" +
                                rec.getString("b_ontime") + "\u0009" +
                                rec.getString("b_macno") + "\u0009" +
                                rec.getString("b_cashier") + "\u0009" +
                                rec.getString("b_refno") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_total")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_nettotal")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_total") - rec.getDouble("b_nettotal") - rec.getDouble("b_serviceamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_serviceamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netvat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netnonvat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_vat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_prncnt")) + "\u0009" +
                                rec.getString("b_etd") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cust")) + "\u0009" +
                                TPay + "\u0009" +
                                rec.getString("b_void") + "\u0009" +
                                rec.getString("b_voiduser");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendBillno(Calendar Tempca) {
            String FileName = "s_billno" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_invoice " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("b_refno") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("b_ondate")) + "\u0009" +
                                rec.getString("b_ontime") + "\u0009" +
                                rec.getString("b_table")+"\u0009" +
                                rec.getString("b_macno") + "\u0009" +
                                rec.getString("b_cashier") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cust")) + "\u0009" +
                                rec.getString("b_etd") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_total")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_food")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_drink")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_product")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_service")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_serviceamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_itemdiscamt")) + "\u0009" +
                                rec.getString("b_memdisc") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_memdiscamt")) + "\u0009" +
                                rec.getString("b_subdisc") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_subdiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_subdiscbath")) + "\u0009" +
                                rec.getString("b_empdisc") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_empdiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_empdiscbath")) + "\u0009" +
                                rec.getString("b_fastdisc") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_fastdiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_fastdiscbath")) + "\u0009" +
                                rec.getString("b_traindisc") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_traindiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_traindiscbath")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_prodiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cupondiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_spadiscamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_adjamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_nettotal")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netfood")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netdrink")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netproduct")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netvat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_netnonvat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_vat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_payamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cash")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_giftvoucher")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_earnest")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_ton")) + "\u0009" +
                                rec.getString("b_crcode1") + "\u0009" +
                                rec.getString("b_cardno1") + "\u0009" +
                                rec.getString("b_appcode1") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_crcharge1")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_crchargeamt1")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cramt1")) + "\u0009" +
                                rec.getString("b_accrcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_accramt")) + "\u0009" +
                                rec.getString("b_memcode") + "\u0009" +
                                rec.getString("b_memname") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("b_membegin")) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("b_memend")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_memcursum")) + "\u0009" +
                                rec.getString("b_void") + "\u0009" +
                                rec.getString("b_voiduser") + "\u0009" +
                                rec.getString("b_voidtime") + "\u0009" +
                                IntFmt.format(rec.getDouble("b_billcopy")) + "\u0009" +
                                IntFmt.format(rec.getDouble("b_prncnt")) + "\u0009" +
                                rec.getString("b_prntime1") + "\u0009" +
                                rec.getString("b_prntime2") + "\u0009" +
                                rec.getString("b_userendofday") + "\u0009" +
                                rec.getString("b_timeendofday") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("b_dateendofday")) + "\u0009" +
                                rec.getString("b_chkbill") + "\u0009" +
                                rec.getString("b_chkbilltime") + "\u0009" +
                                rec.getString("b_cashtime") + "\u0009" +
                                IntFmt.format(rec.getDouble("b_waittime")) + "\u0009" +
                                rec.getString("b_crbank") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_crcardamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_crcurpoint")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_crsumpoint")) + "\u0009" +
                                rec.getString("barcodelost") + "\u0009" +
                                rec.getString("barcodelostuser") + "\u0009" +
                                rec.getString("barcodelostusername") + "\u0009" +
                                rec.getString("barcodelosttime") + "\u0009" +
                                rec.getString("employcode") + "\u0009" +
                                rec.getString("employname") + "\u0009" +
                                IntFmt.format(rec.getDouble("b_round")) + "\u0009" +
                                rec.getString("b_roundclose") + "\u0009" +
                                rec.getString("b_roundclosetime") + "\u0009" +
                                rec.getString("b_roundclosemac") ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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
        public void SendBillnoCharge(Calendar Tempca) {
            String FileName = "s_charge" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_charge " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        //+ "(s_date,b_refno,b_ondate,b_ontime,b_table,b_macno,b_cashier,b_cust,b_total,b_food,"
                        //+ "b_drink,b_product,b_chargecode,b_chargename,b_chargegroup,b_chargetype,b_chargeremark) "
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("b_refno") + "\u0009" +
                                rec.getString("b_ontime") + "\u0009" +
                                rec.getString("b_table")+"\u0009" +
                                SqlDateFmt.format(rec.getDate("b_ondate")) + "\u0009" +
                                rec.getString("b_macno") + "\u0009" +
                                rec.getString("b_cashier") + "\u0009" +
                                DecFmt.format(rec.getDouble("b_cust")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_food")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_drink")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_product")) + "\u0009" +
                                DecFmt.format(rec.getDouble("b_total")) + "\u0009" +
                                rec.getString("b_void") + "\u0009" +
                                rec.getString("b_voiduser") + "\u0009" +
                                rec.getString("b_voidtime") + "\u0009" +
                                rec.getString("b_chargecode") + "\u0009" +
                                rec.getString("b_chargename") + "\u0009" +
                                rec.getString("b_chargegroup") + "\u0009" +
                                IntFmt.format(rec.getInt("b_chargetype")) + "\u0009" +
                                rec.getString("b_chargeremark") ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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
        public void SendS_Tran(Calendar Tempca) {
            String FileName = "s_tran" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_tran " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("r_index") + "\u0009" +
                                rec.getString("r_refno") + "\u0009" +
                                rec.getString("r_table")+"\u0009" +
                                SqlDateFmt.format(rec.getDate("r_date")) + "\u0009" +
                                rec.getString("r_time") + "\u0009" +
                                rec.getString("macno") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("r_emp") + "\u0009" +
                                rec.getString("r_plucode") + "\u0009" +
                                rec.getString("r_pname") + "\u0009" +
                                rec.getString("r_unit") + "\u0009" +
                                rec.getString("r_group") + "\u0009" +
                                rec.getString("r_status") + "\u0009" +
                                rec.getString("r_normal") + "\u0009" +
                                rec.getString("r_discount") + "\u0009" +
                                rec.getString("r_service") + "\u0009" +
                                rec.getString("r_stock") + "\u0009" +
                                rec.getString("r_set") + "\u0009" +
                                rec.getString("r_vat") + "\u0009" +
                                rec.getString("r_type") + "\u0009" +
                                rec.getString("r_etd") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_quan")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_price")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_total")) + "\u0009" +
                                rec.getString("r_prtype") + "\u0009" +
                                rec.getString("r_prcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prquan")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prbath")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_pramt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_pradj")) + "\u0009" +
                                rec.getString("r_prcutype") + "\u0009" +
                                rec.getString("r_prcucode") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prcuquan")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prcudisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prcubath")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prcuamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prcuadj")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_redule")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_discbath")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_nettotal")) + "\u0009" +
                                rec.getString("r_kic") + "\u0009" +
                                rec.getString("r_kicprint") + "\u0009" +
                                rec.getString("r_refund") + "\u0009" +
                                rec.getString("r_void") + "\u0009" +
                                rec.getString("r_voiduser") + "\u0009" +
                                rec.getString("r_voidtime") + "\u0009" +
                                rec.getString("r_prchktype") + "\u0009" +
                                rec.getString("r_prsubtype") + "\u0009" +
                                rec.getString("r_prsubcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prsubquan")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prsubdisc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prsubbath")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prsubamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prsubadj")) + "\u0009" +
                                rec.getString("r_prchktype2") + "\u0009" +
                                rec.getString("r_prtype2") + "\u0009" +
                                rec.getString("r_prcode2") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prquan2")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prdisc2")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_prbath2")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_pramt2")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_pradj2")) + "\u0009" +
                                rec.getString("holdtable") + "\u0009" +
                                rec.getString("holdmacno") + "\u0009" +
                                rec.getString("holdcashier") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("holddate")) + "\u0009" +
                                rec.getString("holdtime") ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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
        public void SendS_TranCharge(Calendar Tempca) {
            String FileName = "s_trancharge" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_trancharge " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                         //s_date,r_index,r_refno,r_table,r_date,r_time,r_macno,r_cashier,r_etd,r_set,r_stock,r_plucode,
                         //r_pname,r_unit,r_group,r_emp,r_quan,r_grossprice,r_netprice,r_nettotal,
                         //r_grosstotal,r_pricedown,r_void,r_voiduser,r_voidtime,r_refund,
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("r_index") + "\u0009" +
                                rec.getString("r_refno") + "\u0009" +
                                rec.getString("r_table")+"\u0009" +
                                SqlDateFmt.format(rec.getDate("r_date")) + "\u0009" +
                                rec.getString("r_time") + "\u0009" +
                                rec.getString("r_macno") + "\u0009" +
                                rec.getString("r_cashier") + "\u0009" +
                                rec.getString("r_etd") + "\u0009" +
                                rec.getString("r_set") + "\u0009" +
                                rec.getString("r_stock") + "\u0009" +
                                rec.getString("r_plucode") + "\u0009" +
                                rec.getString("r_pname") + "\u0009" +
                                rec.getString("r_unit") + "\u0009" +
                                rec.getString("r_group") + "\u0009" +
                                rec.getString("r_emp") + "\u0009" +
                                DecFmt.format(rec.getDouble("r_quan")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_grossprice")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_netprice")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_nettotal")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_grosstotal")) + "\u0009" +
                                DecFmt.format(rec.getDouble("r_pricedown")) + "\u0009" +
                                rec.getString("r_void") + "\u0009" +
                                rec.getString("r_voiduser") + "\u0009" +
                                rec.getString("r_voidtime") + "\u0009" +
                                rec.getString("r_refund") ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendS_Vat(Calendar Tempca) {
            String FileName = "s_vat" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_vat " +
                        "where tdate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("tdate")) + "\u0009" +
                                rec.getString("terminal") + "\u0009" +
                                rec.getString("macno") + "\u0009" +
                                rec.getString("stbill")+"\u0009" +
                                rec.getString("spbill") + "\u0009" +
                                DecFmt.format(rec.getDouble("netvat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("netnonvat")) + "\u0009" +
                                DecFmt.format(rec.getDouble("vat"))  ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendS_Void(Calendar Tempca) {
            String FileName = "s_void" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_void " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("ref_no") + "\u0009" +
                                rec.getString("voiduser") + "\u0009" +
                                rec.getString("voidtime")+"\u0009" +
                                rec.getString("pindex") + "\u0009" +
                                rec.getString("macno") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("time") + "\u0009" +
                                rec.getString("vtable") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("date")) + "\u0009" +
                                rec.getString("pcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("amt")) ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendDayoperation(Calendar Tempca) {
            String FileName = "dayopt" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from dayoperation " +
                        "where operationdate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("operationdate")) + "\u0009" +
                                rec.getString("updatemaster") + "\u0009" +
                                rec.getString("updatemasteruser") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("updatemastertime")) + "\u0009" +
                                rec.getString("sale")+"\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("firstbilltime")) + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("lastbilltime")) + "\u0009" +
                                rec.getString("autosum") + "\u0009" +
                                rec.getString("autosumuser") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("autosumtime")) + "\u0009" +
                                rec.getString("rjfile") + "\u0009" +
                                rec.getString("rjfileuser") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("rjfiletime")) + "\u0009" +
                                rec.getString("adjstock") + "\u0009" +
                                rec.getString("adjstockuser") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("adjstocktime")) + "\u0009" +
                                rec.getString("endofday") + "\u0009" +
                                rec.getString("endofdayuser") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("endofdaytime")) + "\u0009" +
                                rec.getString("sendsaledata") + "\u0009" +
                                rec.getString("sendsaledatauser") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("sendsaledatatime")) ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendUserChange(Calendar Tempca) {
            String FileName = "userchange" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from userchange " +
                        "where changedate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                rec.getString("usercode") + "\u0009" +
                                rec.getString("newpassword") + "\u0009" +
                                rec.getString("changedate") + "\u0009" +
                                SqlDateTimeFmt.format(rec.getObject("changetime")) ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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
        public void SendHeader(Calendar Tempca) {
            String FileName = "headerfile" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            String TempStr = "";
            //File Version POS LINUX1.0 For Fist Version To 2011-08-01
            //File Version POS LINUX1.1 For Secennd Version
            //     Charge
            //     Order จอง
            //     Cake Order
            TempStr  = TempStr + "FILE VERSION :POS LINUX1.1" +"\u0009" +
                                 "BRANCH CODE  :"+Branch_Code+ "\u0009" +
                                 "BRANCH NAME  :"+Branch_Name+ "\u0009" +
                                 "CREATE DATE  :"+SqlDateTimeFmt.format(new Date());
            TextWrite.writeToText(centFileName, TempStr);
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

        public void SendS_Kictran(Calendar Tempca) {
            String FileName = "s_kictran" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_kictran " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("pdate")) + "\u0009" +
                                rec.getString("pcode") + "\u0009" +
                                rec.getString("pindex") + "\u0009" +
                                rec.getString("macno")+"\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("emp") + "\u0009" +
                                IntFmt.format(rec.getDouble("pitemno")) + "\u0009" +
                                rec.getString("ptable") + "\u0009" +
                                rec.getString("pkic") + "\u0009" +
                                rec.getTime("ptimein")+"\u0009" +
                                rec.getTime("ptimeout")+"\u0009" +
                                rec.getString("pvoid") + "\u0009" +
                                rec.getString("petd") + "\u0009" +
                                DecFmt.format(rec.getDouble("pqty")) + "\u0009" +
                                rec.getString("pflage") + "\u0009" +
                                rec.getTime("pwaittime")+"\u0009" +
                                rec.getTime("pservetime")+"\u0009" +
                                rec.getString("pserve") + "\u0009" +
                                rec.getString("ppayment") + "\u0009" +
                                rec.getString("pinvno") + "\u0009" +
                                rec.getTime("pwaitserve")+"\u0009" +
                                rec.getTime("pwaittotal") ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendHourly(Calendar Tempca) {
            String FileName = "TH" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            String TempMacNo = "";
            int C0 = 0;
            int C1 = 0;
            int C2 = 0;
            int C3 = 0;
            int C4 = 0;
            int C5 = 0;
            int C6 = 0;
            int C7 = 0;
            int C8 = 0;
            int C9 = 0;
            int C10 = 0;
            int C11 = 0;
            int C12 = 0;
            int C13 = 0;
            int C14 = 0;
            int C15 = 0;
            int C16 = 0;
            int C17 = 0;
            int C18 = 0;
            int C19 = 0;
            int C20 = 0;
            int C21 = 0;
            int C22 = 0;
            int C23 = 0;
            int SumC = 0;

            int B0 = 0;
            int B1 = 0;
            int B2 = 0;
            int B3 = 0;
            int B4 = 0;
            int B5 = 0;
            int B6 = 0;
            int B7 = 0;
            int B8 = 0;
            int B9 = 0;
            int B10 = 0;
            int B11 = 0;
            int B12 = 0;
            int B13 = 0;
            int B14 = 0;
            int B15 = 0;
            int B16 = 0;
            int B17 = 0;
            int B18 = 0;
            int B19 = 0;
            int B20 = 0;
            int B21 = 0;
            int B22 = 0;
            int B23 = 0;
            int SumB = 0;

            Double S0 = 0.0;
            Double S1 = 0.0;
            Double S2 = 0.0;
            Double S3 = 0.0;
            Double S4 = 0.0;
            Double S5 = 0.0;
            Double S6 = 0.0;
            Double S7 = 0.0;
            Double S8 = 0.0;
            Double S9 = 0.0;
            Double S10 = 0.0;
            Double S11 = 0.0;
            Double S12 = 0.0;
            Double S13 = 0.0;
            Double S14 = 0.0;
            Double S15 = 0.0;
            Double S16 = 0.0;
            Double S17 = 0.0;
            Double S18 = 0.0;
            Double S19 = 0.0;
            Double S20 = 0.0;
            Double S21 = 0.0;
            Double S22 = 0.0;
            Double S23 = 0.0;
            Double SumS = 0.0;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_invoice " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'  and b_void<>'V' order by b_macno";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {

                    TempMacNo = rec.getString("b_macno");
                    do {
                        if (!rec.getString("b_macno").equals(TempMacNo)) {
                            String TempStr = "";
                            TempStr = TempStr + Branch_Code + "\u0009" +
                                    SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                    TempMacNo + "\u0009" +
                                    DecFmt.format(B0) + "\u0009" +
                                    DecFmt.format(C0) + "\u0009" +
                                    DecFmt.format(S0) + "\u0009" +
                                    DecFmt.format(B1) + "\u0009" +
                                    DecFmt.format(C1) + "\u0009" +
                                    DecFmt.format(S1) + "\u0009" +
                                    DecFmt.format(B2) + "\u0009" +
                                    DecFmt.format(C2) + "\u0009" +
                                    DecFmt.format(S2) + "\u0009" +
                                    DecFmt.format(B3) + "\u0009" +
                                    DecFmt.format(C3) + "\u0009" +
                                    DecFmt.format(S3) + "\u0009" +
                                    DecFmt.format(B4) + "\u0009" +
                                    DecFmt.format(C4) + "\u0009" +
                                    DecFmt.format(S4) + "\u0009" +
                                    DecFmt.format(B5) + "\u0009" +
                                    DecFmt.format(C5) + "\u0009" +
                                    DecFmt.format(S5) + "\u0009" +
                                    DecFmt.format(B6) + "\u0009" +
                                    DecFmt.format(C6) + "\u0009" +
                                    DecFmt.format(S6) + "\u0009" +
                                    DecFmt.format(B7) + "\u0009" +
                                    DecFmt.format(C7) + "\u0009" +
                                    DecFmt.format(S7) + "\u0009" +
                                    DecFmt.format(B8) + "\u0009" +
                                    DecFmt.format(C8) + "\u0009" +
                                    DecFmt.format(S8) + "\u0009" +
                                    DecFmt.format(B9) + "\u0009" +
                                    DecFmt.format(C9) + "\u0009" +
                                    DecFmt.format(S9) + "\u0009" +
                                    DecFmt.format(B10) + "\u0009" +
                                    DecFmt.format(C10) + "\u0009" +
                                    DecFmt.format(S10) + "\u0009" +
                                    DecFmt.format(B11) + "\u0009" +
                                    DecFmt.format(C11) + "\u0009" +
                                    DecFmt.format(S11) + "\u0009" +
                                    DecFmt.format(B12) + "\u0009" +
                                    DecFmt.format(C12) + "\u0009" +
                                    DecFmt.format(S12) + "\u0009" +
                                    DecFmt.format(B13) + "\u0009" +
                                    DecFmt.format(C13) + "\u0009" +
                                    DecFmt.format(S13) + "\u0009" +
                                    DecFmt.format(B14) + "\u0009" +
                                    DecFmt.format(C14) + "\u0009" +
                                    DecFmt.format(S14) + "\u0009" +
                                    DecFmt.format(B15) + "\u0009" +
                                    DecFmt.format(C15) + "\u0009" +
                                    DecFmt.format(S15) + "\u0009" +
                                    DecFmt.format(B16) + "\u0009" +
                                    DecFmt.format(C16) + "\u0009" +
                                    DecFmt.format(S16) + "\u0009" +
                                    DecFmt.format(B17) + "\u0009" +
                                    DecFmt.format(C17) + "\u0009" +
                                    DecFmt.format(S17) + "\u0009" +
                                    DecFmt.format(B18) + "\u0009" +
                                    DecFmt.format(C18) + "\u0009" +
                                    DecFmt.format(S18) + "\u0009" +
                                    DecFmt.format(B19) + "\u0009" +
                                    DecFmt.format(C19) + "\u0009" +
                                    DecFmt.format(S19) + "\u0009" +
                                    DecFmt.format(B20) + "\u0009" +
                                    DecFmt.format(C20) + "\u0009" +
                                    DecFmt.format(S20) + "\u0009" +
                                    DecFmt.format(B21) + "\u0009" +
                                    DecFmt.format(C21) + "\u0009" +
                                    DecFmt.format(S21) + "\u0009" +
                                    DecFmt.format(B22) + "\u0009" +
                                    DecFmt.format(C22) + "\u0009" +
                                    DecFmt.format(S22) + "\u0009" +
                                    DecFmt.format(B23) + "\u0009" +
                                    DecFmt.format(C23) + "\u0009" +
                                    DecFmt.format(S23);
                            TextWrite.writeToText(centFileName, TempStr);
                            C0 = 0;
                            C1 = 0;
                            C2 = 0;
                            C3 = 0;
                            C4 = 0;
                            C5 = 0;
                            C6 = 0;
                            C7 = 0;
                            C8 = 0;
                            C9 = 0;
                            C10 = 0;
                            C11 = 0;
                            C12 = 0;
                            C13 = 0;
                            C14 = 0;
                            C15 = 0;
                            C16 = 0;
                            C17 = 0;
                            C18 = 0;
                            C19 = 0;
                            C20 = 0;
                            C21 = 0;
                            C22 = 0;
                            C23 = 0;
                            SumC = 0;

                            B0 = 0;
                            B1 = 0;
                            B2 = 0;
                            B3 = 0;
                            B4 = 0;
                            B5 = 0;
                            B6 = 0;
                            B7 = 0;
                            B8 = 0;
                            B9 = 0;
                            B10 = 0;
                            B11 = 0;
                            B12 = 0;
                            B13 = 0;
                            B14 = 0;
                            B15 = 0;
                            B16 = 0;
                            B17 = 0;
                            B18 = 0;
                            B19 = 0;
                            B20 = 0;
                            B21 = 0;
                            B22 = 0;
                            B23 = 0;
                            SumB = 0;

                            S0 = 0.0;
                            S1 = 0.0;
                            S2 = 0.0;
                            S3 = 0.0;
                            S4 = 0.0;
                            S5 = 0.0;
                            S6 = 0.0;
                            S7 = 0.0;
                            S8 = 0.0;
                            S9 = 0.0;
                            S10 = 0.0;
                            S11 = 0.0;
                            S12 = 0.0;
                            S13 = 0.0;
                            S14 = 0.0;
                            S15 = 0.0;
                            S16 = 0.0;
                            S17 = 0.0;
                            S18 = 0.0;
                            S19 = 0.0;
                            S20 = 0.0;
                            S21 = 0.0;
                            S22 = 0.0;
                            S23 = 0.0;
                            SumS = 0.0;
                            TempMacNo = rec.getString("b_macno");
                        }
                        String XTime = "";
                        String TempTime = "";
                        TempTime = rec.getString("b_ontime");
                        XTime = rec.getString("b_ontime").substring(0, 2);
                        if (XTime.equals("00")) {
                            B0++;
                            C0 = C0 + rec.getInt("b_cust");
                            S0 = S0 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("01")) {
                            B1++;
                            C1 = C1 + rec.getInt("b_cust");
                            S1 = S1 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("02")) {
                            B2++;
                            C2 = C2 + rec.getInt("b_cust");
                            S2 = S2 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("03")) {
                            B3++;
                            C3 = C0 + rec.getInt("b_cust");
                            S3 = S3 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("04")) {
                            B4++;
                            C4 = C4 + rec.getInt("b_cust");
                            S4 = S4 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("05")) {
                            B5++;
                            C5 = C5 + rec.getInt("b_cust");
                            S5 = S5 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("06")) {
                            B6++;
                            C6 = C6 + rec.getInt("b_cust");
                            S6 = S6 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("07")) {
                            B7++;
                            C7 = C7 + rec.getInt("b_cust");
                            S7 = S7 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("08")) {
                            B8++;
                            C8 = C8 + rec.getInt("b_cust");
                            S8 = S8 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("09")) {
                            B9++;
                            C9 = C9 + rec.getInt("b_cust");
                            S9 = S9 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("10")) {
                            B10++;
                            C10 = C10 + rec.getInt("b_cust");
                            S10 = S10 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("11")) {
                            B11++;
                            C11 = C11 + rec.getInt("b_cust");
                            S11 = S11 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("12")) {
                            B12++;
                            C12 = C12 + rec.getInt("b_cust");
                            S12 = S12 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("13")) {
                            B13++;
                            C13 = C13 + rec.getInt("b_cust");
                            S13 = S13 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("14")) {
                            B14++;
                            C14 = C14 + rec.getInt("b_cust");
                            S14 = S14 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("15")) {
                            B15++;
                            C15 = C15 + rec.getInt("b_cust");
                            S15 = S15 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("16")) {
                            B16++;
                            C16 = C16 + rec.getInt("b_cust");
                            S16 = S16 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("17")) {
                            B17++;
                            C17 = C17 + rec.getInt("b_cust");
                            S17 = S17 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("18")) {
                            B18++;
                            C18 = C18 + rec.getInt("b_cust");
                            S18 = S18 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("19")) {
                            B19++;
                            C19 = C19 + rec.getInt("b_cust");
                            S19 = S19 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("20")) {
                            B20++;
                            C20 = C20 + rec.getInt("b_cust");
                            S20 = S20 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("21")) {
                            B21++;
                            C21 = C21 + rec.getInt("b_cust");
                            S21 = S21 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("22")) {
                            B22++;
                            C22 = C22 + rec.getInt("b_cust");
                            S22 = S22 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }
                        if (XTime.equals("23")) {
                            B23++;
                            C23 = C23 + rec.getInt("b_cust");
                            S23 = S23 + (rec.getDouble("b_netvat") + rec.getDouble("b_netnonvat"));
                        }

                    } while (rec.next());
                }
                if (!TempMacNo.equals("")) {
                    String TempStr = "";
                    TempStr = TempStr + Branch_Code + "\u0009" +
                            SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                            TempMacNo + "\u0009" +
                            DecFmt.format(B0) + "\u0009" +
                            DecFmt.format(C0) + "\u0009" +
                            DecFmt.format(S0) + "\u0009" +
                            DecFmt.format(B1) + "\u0009" +
                            DecFmt.format(C1) + "\u0009" +
                            DecFmt.format(S1) + "\u0009" +
                            DecFmt.format(B2) + "\u0009" +
                            DecFmt.format(C2) + "\u0009" +
                            DecFmt.format(S2) + "\u0009" +
                            DecFmt.format(B3) + "\u0009" +
                            DecFmt.format(C3) + "\u0009" +
                            DecFmt.format(S3) + "\u0009" +
                            DecFmt.format(B4) + "\u0009" +
                            DecFmt.format(C4) + "\u0009" +
                            DecFmt.format(S4) + "\u0009" +
                            DecFmt.format(B5) + "\u0009" +
                            DecFmt.format(C5) + "\u0009" +
                            DecFmt.format(S5) + "\u0009" +
                            DecFmt.format(B6) + "\u0009" +
                            DecFmt.format(C6) + "\u0009" +
                            DecFmt.format(S6) + "\u0009" +
                            DecFmt.format(B7) + "\u0009" +
                            DecFmt.format(C7) + "\u0009" +
                            DecFmt.format(S7) + "\u0009" +
                            DecFmt.format(B8) + "\u0009" +
                            DecFmt.format(C8) + "\u0009" +
                            DecFmt.format(S8) + "\u0009" +
                            DecFmt.format(B9) + "\u0009" +
                            DecFmt.format(C9) + "\u0009" +
                            DecFmt.format(S9) + "\u0009" +
                            DecFmt.format(B10) + "\u0009" +
                            DecFmt.format(C10) + "\u0009" +
                            DecFmt.format(S10) + "\u0009" +
                            DecFmt.format(B11) + "\u0009" +
                            DecFmt.format(C11) + "\u0009" +
                            DecFmt.format(S11) + "\u0009" +
                            DecFmt.format(B12) + "\u0009" +
                            DecFmt.format(C12) + "\u0009" +
                            DecFmt.format(S12) + "\u0009" +
                            DecFmt.format(B13) + "\u0009" +
                            DecFmt.format(C13) + "\u0009" +
                            DecFmt.format(S13) + "\u0009" +
                            DecFmt.format(B14) + "\u0009" +
                            DecFmt.format(C14) + "\u0009" +
                            DecFmt.format(S14) + "\u0009" +
                            DecFmt.format(B15) + "\u0009" +
                            DecFmt.format(C15) + "\u0009" +
                            DecFmt.format(S15) + "\u0009" +
                            DecFmt.format(B16) + "\u0009" +
                            DecFmt.format(C16) + "\u0009" +
                            DecFmt.format(S16) + "\u0009" +
                            DecFmt.format(B17) + "\u0009" +
                            DecFmt.format(C17) + "\u0009" +
                            DecFmt.format(S17) + "\u0009" +
                            DecFmt.format(B18) + "\u0009" +
                            DecFmt.format(C18) + "\u0009" +
                            DecFmt.format(S18) + "\u0009" +
                            DecFmt.format(B19) + "\u0009" +
                            DecFmt.format(C19) + "\u0009" +
                            DecFmt.format(S19) + "\u0009" +
                            DecFmt.format(B20) + "\u0009" +
                            DecFmt.format(C20) + "\u0009" +
                            DecFmt.format(S20) + "\u0009" +
                            DecFmt.format(B21) + "\u0009" +
                            DecFmt.format(C21) + "\u0009" +
                            DecFmt.format(S21) + "\u0009" +
                            DecFmt.format(B22) + "\u0009" +
                            DecFmt.format(C22) + "\u0009" +
                            DecFmt.format(S22) + "\u0009" +
                            DecFmt.format(B23) + "\u0009" +
                            DecFmt.format(C23) + "\u0009" +
                            DecFmt.format(S23);
                    TextWrite.writeToText(centFileName, TempStr);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

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

        public void SendPromotion(Calendar Tempca) {
            String FileName = "TPR" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_promotion " +
                        "where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                rec.getString("r_refno") + "\u0009" +
                                rec.getString("terminal") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("prcode") + "\u0009" +
                                rec.getString("prtype") + "\u0009" +
                                rec.getString("pcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("pqty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("prtotalamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("pramt")) + "\u0009" +
                                rec.getString("flage");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendStock(Calendar Tempca) {
            String FileName = "TST" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            SimpleDateFormat DateFmtM = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH);
            double Sum1, Sum2, Sum3, SumAmt1, SumAmt2, SumAmt3 = 0.0;
            String TempStrDate = "01/" + DateFmtM.format(Tempca.getTime());
            Date TempDate = new Date();
            try {
                TempDate = DateFmt.parse(TempStrDate);
            } catch (ParseException ex) {
                Logger.getLogger(SentDataToCenterByADSL.class.getName()).log(Level.SEVERE, null, ex);
            }
            int TempAct = GetActMonth(TempDate) - 1;
            String TempFieldName = "bqty" + IntFmt.format(TempAct).trim();

            int TotalRec = 0;
            int CurRec = 0;
            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
               Statement stmt = (Statement) MySQLConnect.con.createStatement();
               String SQLQuery = "Drop TABLE stcardtemp " ;
               stmt.executeUpdate(SQLQuery);
               stmt.close();
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(this, e.getMessage());
            }
            try {
               Statement stmt = (Statement) MySQLConnect.con.createStatement();
               String SQLQuery = "Drop TABLE stcardtemp2 " ;
               stmt.executeUpdate(SQLQuery);
               stmt.close();
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(this, e.getMessage());
            }

            try {
               Statement stmt = (Statement) MySQLConnect.con.createStatement();
               String SQLQuery = "create TABLE stcardtemp " +
                       "Select S_PCode,S_Stk,S_Rem,Sum(S_In),Sum(S_Out),S_User from stcard " +
                       "where  s_stk='" + STK + "' and s_date='" + SqlDateFmt.format(Tempca.getTime()) + "' " +
                                    "group by s_pcode,s_rem ";
               stmt.executeUpdate(SQLQuery);
               //ResultSet rec = stmt.executeQuery(SQLQuery);
               stmt.close();
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(this, e.getMessage());
            }

            try {
               Statement stmt = (Statement) MySQLConnect.con.createStatement();
               String SQLQuery = "create TABLE stcardtemp2 " +
                       "select s_pcode,s_stk,sum(s_in),sum(s_out) from stcard  " +
                                    "where  s_stk='" + STK + "' and s_date>='" + SqlDateFmt.format(TempDate) + "' and s_date<'" + SqlDateFmt.format(Tempca.getTime()) + "' " +
                                    "group by s_pcode";
               stmt.executeUpdate(SQLQuery);
               //ResultSet rec = stmt.executeQuery(SQLQuery);
               stmt.close();
            } catch (SQLException e) {
               JOptionPane.showMessageDialog(this, e.getMessage());
            }

            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select pcode,pgroup,pdesc,pprice11 from product " +
                        "where pstock='Y' and pactive='Y'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        TotalRec++;
                    } while (rec.next());
                }
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        CurRec++;
                        StkProcess.setText("รายการที่ " + IntFmt.format(CurRec) + "/" + IntFmt.format(TotalRec));
                        String TempCode = rec.getString("pcode");
                        String TempName = rec.getString("pdesc");
                        String TempDept = rec.getString("pgroup");
                        double TempPrice = rec.getDouble("pprice11");
                        double TBOM = 0.0;
                        double TBUY = 0.0;
                        double TREC = 0.0;
                        double TTRI = 0.0;
                        double TTRO = 0.0;
                        double TLOS = 0.0;
                        double TFRE = 0.0;
                        double TRET = 0.0;
                        double TSAL = 0.0;
                        double TADJ = 0.0;
                        double TCon1 = 0.0 ;
                        double TCon2 = 0.0 ;
                        String TUser = "";
                        String TRem = "";
                        
                        try {
                            Statement stmt2 = (Statement) MySQLConnect.con.createStatement();
                            String SqlQuery2 = "select *from stkfile  " +
                                    "where  bpcode ='" + TempCode + "' and bstk='" + STK + "'";
                            ResultSet rec2 = stmt2.executeQuery(SqlQuery2);
                            rec2.first();
                            if (rec2.getRow() == 0) {
                            } else {
                                TempFieldName = "bqty" + IntFmt.format(TempAct).trim();
                                TBOM = rec2.getDouble(TempFieldName);
                            }
                            rec2.close();
                            stmt2.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                        try {
                            Statement stmt3 = (Statement) MySQLConnect.con.createStatement();
                            String SqlQuery3 = "select *from stcardtemp2  " +
                                    "where  s_pcode ='" + TempCode + "'";
                            ResultSet rec3 = stmt3.executeQuery(SqlQuery3);
                            rec3.first();
                            if (rec3.getRow() == 0) {
                            } else {
                                TBOM = TBOM + rec3.getDouble("sum(s_in)") - rec3.getDouble("sum(s_out)");
                            }
                            rec3.close();
                            stmt3.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }

                        try {
                            Statement stmt4 = (Statement) MySQLConnect.con.createStatement();
                            String SqlQuery4 = "select *from stcardtemp  " +
                                    "where  s_pcode ='" + TempCode + "'" ;
             
                            ResultSet rec4 = stmt4.executeQuery(SqlQuery4);
                            rec4.first();
                            if (rec4.getRow() == 0) {
                            } else {
                                do {
                                    if (rec4.getString("s_rem").equals("BUY")) {
                                        TBUY = rec4.getDouble("sum(s_in)");
                                    } else if (rec4.getString("s_rem").equals("REC")) {
                                        TREC = rec4.getDouble("sum(s_in)");
                                    } else if (rec4.getString("s_rem").equals("TRI")) {
                                        TTRI = rec4.getDouble("sum(s_in)");
                                    } else if (rec4.getString("s_rem").equals("TRO")) {
                                        TTRO = rec4.getDouble("sum(s_out)");
                                    } else if (rec4.getString("s_rem").equals("LOS")) {
                                        TLOS = rec4.getDouble("sum(s_out)");
                                    } else if (rec4.getString("s_rem").equals("FRE")) {
                                        TFRE = rec4.getDouble("sum(s_out)");
                                    } else if (rec4.getString("s_rem").equals("SAL")) {
                                        TSAL = rec4.getDouble("sum(s_out)");
                                    } else if (rec4.getString("s_rem").equals("CON+")) {
                                        TCon1 = rec4.getDouble("sum(s_in)");
                                    } else if (rec4.getString("s_rem").equals("CON-")) {
                                        TCon2 = rec4.getDouble("sum(s_out)");
                                    } else if (rec4.getString("s_rem").equals("ADJ")) {
                                        TADJ = rec4.getDouble("sum(s_out)") + rec4.getDouble("sum(s_in)");
                                    }
                                } while (rec4.next());
                            }
                            rec4.close();
                            stmt4.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }

                        try {
                            Statement stmt5 = (Statement) MySQLConnect.con.createStatement();
                            String SqlQuery5 = "select *from adjstock left join hadjstock on adjstock.r_no=hadjstock.r_no  " +
                                    "where  r_pcode ='" + TempCode + "' and r_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                            ResultSet rec5 = stmt5.executeQuery(SqlQuery5);
                            rec5.first();
                            if (rec5.getRow() == 0) {
                            } else {
                                TRET = rec5.getDouble("r_inhand");
                                TUser = rec5.getString("r_user");
                                TRem = rec5.getString("r_remark");
                            }
                            rec5.close();
                            stmt5.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, e.getMessage());
                        }
                        String TempStr = "";
                        TempStr = TempStr + TempCode + "\u0009" +
                                DecFmt.format(TBOM) + "\u0009" +
                                DecFmt.format(TBUY) + "\u0009" +
                                DecFmt.format(TREC) + "\u0009" +
                                DecFmt.format(TTRI) + "\u0009" +
                                DecFmt.format(TTRO) + "\u0009" +
                                DecFmt.format(TSAL) + "\u0009" +
                                DecFmt.format(TRET) + "\u0009" +
                                DecFmt.format(TLOS) + "\u0009" +
                                DecFmt.format(TFRE) + "\u0009" +
                                DecFmt.format(TADJ) + "\u0009" +
                                DecFmt.format(TBOM + TBUY + TREC + TTRI + TADJ +TCon1 - TTRO - TLOS - TFRE - TSAL-TCon2) + "\u0009" +
                                DecFmt.format(TempPrice) + "\u0009" +
                                TUser + "\u0009" +
                                TRem  + "\u0009" +
                                DecFmt.format(TCon1) + "\u0009" +
                                DecFmt.format(TCon2);
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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
            StkProcess.setText("");
        }

        public void SendGift(Calendar Tempca) {
            String FileName = "TGV" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();
            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from s_gift where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";

                        TempStr = TempStr + Branch_Code + "\u0009" +
                                SqlDateFmt.format(Tempca.getTime()) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("ondate")) + "\u0009" +
                                rec.getString("macno") + "\u0009" +
                                rec.getString("refno") + "\u0009" +
                                rec.getString("cashier") + "\u0009" +
                                rec.getString("giftbarcode") + "\u0009" +
                                rec.getString("gifttype") + "\u0009" +
                                rec.getString("giftprice") + "\u0009" +
                                rec.getString("giftmodel") + "\u0009" +
                                rec.getString("giftlot") + "\u0009" +
                                rec.getString("giftexp") + "\u0009" +
                                rec.getString("giftcode") + "\u0009" +
                                rec.getString("giftno") + "\u0009" +
                                DecFmt.format(rec.getDouble("giftamt")) + "\u0009" +
                                rec.getString("fat") + "\u0009" +
                                ShortTimeFmt.format(date);
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendTMU(Calendar Tempca) {
            String FileName = "TMU" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from memmaster where m_last='" + SqlDateFmt.format(Tempca.getTime()) + "'  order by m_code";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName,null);
                    }
                } else {
                    do {
                        String TempStr = "";

                        TempStr = TempStr + SqlDateFmt.format(rec.getDate("m_last")) + "\u0009" +
                                rec.getString("m_code") + "\u0009" +
                                rec.getString("m_sex") + "\u0009" +
                                rec.getString("m_status") + "\u0009" +
                                rec.getString("m_bran") + "\u0009" +
                                rec.getString("m_type") + "\u0009" +
                                rec.getString("m_nation") + "\u0009" +
                                rec.getString("m_name") + "\u0009" +
                                rec.getString("m_card") + "\u0009" +
                                rec.getString("m_email") + "\u0009" +
                                rec.getString("m_occu") + "\u0009" +
                                rec.getString("m_incom") + "\u0009" +
                                rec.getString("m_company") + "\u0009" +
                                rec.getString("m_addr1") + "\u0009" +
                                rec.getString("m_addr2") + "\u0009" +
                                rec.getString("m_addr3") + "\u0009" +
                                rec.getString("m_addr4") + "\u0009" +
                                rec.getString("m_addr5") + "\u0009" +
                                rec.getString("m_addr6") + "\u0009" +
                                rec.getString("m_post") + "\u0009" +
                                rec.getString("m_sone") + "\u0009" +
                                rec.getString("m_tel") + "\u0009" +
                                rec.getString("m_fax") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_brid")) + "\u0009" +
                                rec.getString("m_mobile") + "\u0009" +
                                rec.getString("m_office") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_begin")) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_end")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_disc")) + "\u0009" +
                                rec.getString("m_discrate") + "\u0009" +
                                rec.getString("m_wise") + "\u0009" +
                                DecFmt.format(rec.getDouble("m_chai")) + "\u0009" +
                                rec.getString("m_food") + "\u0009" +
                                rec.getString("m_flage");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendTMP(Calendar Tempca) {
            String FileName = "TMP" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from mtranplu where m_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + rec.getString("m_code") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_date")) + "\u0009" +
                                rec.getString("m_bran") + "\u0009" +
                                rec.getString("m_billno") + "\u0009" +
                                rec.getString("m_type") + "\u0009" +
                                rec.getString("m_pcode") + "\u0009" +
                                DecFmt.format(rec.getDouble("m_qty")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_price")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_grossamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_netamt")) + "\u0009" +
                                rec.getString("m_macno") + "\u0009" +
                                rec.getString("m_user");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendTM(Calendar Tempca) {
            String FileName = "TM" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from mtran where m_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + rec.getString("m_code") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_date")) + "\u0009" +
                                rec.getString("m_bran") + "\u0009" +
                                rec.getString("m_billno") + "\u0009" +
                                rec.getString("m_type") + "\u0009" +
                                DecFmt.format(rec.getDouble("m_grossamt")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_disc")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_netamt")) + "\u0009" +
                                rec.getString("m_dept") + "\u0009" +
                                DecFmt.format(rec.getDouble("m_deptamt")) + "\u0009" +
                                rec.getString("m_macno") + "\u0009" +
                                rec.getString("m_user") + "\u0009" +
                                rec.getString("m_time") + "\u0009" +
                                DecFmt.format(rec.getDouble("m_score"));
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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

        public void SendTMA(Calendar Tempca) {
            String FileName = "TMA" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from memaddtime where m_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = TempStr + "U01-09-2009"+ "\u0009" +
                                rec.getString("m_code") + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_date")) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_enddate")) + "\u0009" +
                                SqlDateFmt.format(rec.getDate("m_newdate")) + "\u0009" +
                                rec.getString("addtype")+"\u0009"+
                                DecFmt.format(rec.getDouble("m_amount")) + "\u0009" +
                                DecFmt.format(rec.getDouble("m_scoreamount")) + "\u0009" +
                                rec.getString("m_user");
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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
        public void SendSMS(Calendar Tempca) {
            String FileName = "SMSUSE" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = TempPath + '/' + FileName;
            TextWriter TextWrite = new TextWriter();

            //TextWrite.writeToText(TempFile, PrintMsg) ;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from smsuse where s_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    File fObject = new File(centFileName);
                    if (!fObject.canRead()) {
                        TextWrite.writeToText(centFileName, null);
                    }
                } else {
                    do {
                        String TempStr = "";
                        TempStr = SqlDateFmt.format(rec.getDate("s_date")) + "\u0009" +
                                rec.getString("s_time")+"\u0009"+
                                rec.getString("s_refno")+"\u0009"+
                                rec.getString("s_billno")+"\u0009"+
                                rec.getString("macno")+"\u0009"+
                                rec.getString("m_code")+"\u0009"+
                                rec.getString("sms_code")+"\u0009"+
                                rec.getString("branch_code")+"\u0009"+
                                rec.getString("cu_code")+"\u0009"+
                                DecFmt.format(rec.getDouble("net_amount")) + "\u0009" +
                                DecFmt.format(rec.getDouble("disc_amount")) + "\u0009" +
                                DecFmt.format(rec.getDouble("score")) ;
                        TextWrite.writeToText(centFileName, TempStr);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
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
        public void SendAR(Calendar Tempca) {
            DirectoryUtility dirUtil = new DirectoryUtility();
            String TempX = "/tmp";
            File fl = new File(TempX);
            File fllist[] = fl.listFiles();
            try {
                for (int i = 0; i < fllist.length; i++) {
                    dirUtil.deleteDir(fllist[i]);
                }
            } catch (IOException ex) {
                Logger.getLogger(UpdateDataFromCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            String FileName = "TAR" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            String centFileName;
            centFileName = "/tmp" + '/' + FileName;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *into outfile '" + centFileName + "' from accr where ardate='" + SqlDateFmt.format(Tempca.getTime()) + "' or arpdate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                stmt.execute(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (!FileCharset.equals("UTF-8")) {
                String inFile = "/tmp" + '/' + FileName;
                String inCharset = "UTF-8";
                String outFile = PathConvert + '/' + FileName;
                String outCharset = FileCharset;
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + FileName;
            } else {
                centFileName = "/tmp" + '/' + FileName;
            }

            FileName = "TCF" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            centFileName = "/tmp" + '/' + FileName;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *into outfile '" + centFileName + "' from custfile where sp_date='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                stmt.execute(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (!FileCharset.equals("UTF-8")) {
                String inFile = "/tmp" + '/' + FileName;
                String inCharset = "UTF-8";
                String outFile = PathConvert + '/' + FileName;
                String outCharset = FileCharset;
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + FileName;
            } else {
                centFileName = "/tmp" + '/' + FileName;
            }

            FileName = "TIN" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            centFileName = "/tmp" + '/' + FileName;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *into outfile '" + centFileName + "' from invcashdoc where invdate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                stmt.execute(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (!FileCharset.equals("UTF-8")) {
                String inFile = "/tmp" + '/' + FileName;
                String inCharset = "UTF-8";
                String outFile = PathConvert + '/' + FileName;
                String outCharset = FileCharset;
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + FileName;
            } else {
                centFileName = "/tmp" + '/' + FileName;
            }

            FileName = "TID" + FileDateFmt.format(Tempca.getTime()) + "." + Branch_Code;
            centFileName = "/tmp" + '/' + FileName;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *into outfile '" + centFileName + "' from invdetail where invdate='" + SqlDateFmt.format(Tempca.getTime()) + "'";
                stmt.execute(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            if (!FileCharset.equals("UTF-8")) {
                String inFile = "/tmp" + '/' + FileName;
                String inCharset = "UTF-8";
                String outFile = PathConvert + '/' + FileName;
                String outCharset = FileCharset;
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + FileName;
            } else {
                centFileName = "/tmp" + '/' + FileName;
            }
        }
    }
}

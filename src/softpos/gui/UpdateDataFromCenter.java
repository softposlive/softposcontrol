package softpos.gui;


import directory_utility.DirectoryUtility;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import other.FTPUtility;
import other.FTPUtility_bak;
import other.SetupFTPProperty;
import softpos.program.ThaiUtil;
import utilities.MySQLConnect;
import write_to_text.TextWriter;
import zip_utility.ZipUnzip;

public class UpdateDataFromCenter extends javax.swing.JDialog {
    String Branch_Name;
    String Branch_Code;
    String Branch_Type;
    String FromCenterPath = "C:/spapplication/fromcenter";
    String PathTemp = "C:/spapplication/temp";
    String PathConvert = "C:/spapplication/tempconvert";
    static SimpleDateFormat DateTimeFmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
    static SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    static SimpleDateFormat SqlDateTimeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    static DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    static DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    static int maxValue;
    static int minValue;
    static int curValue;
    static Timer timer;
    static boolean processStop;
    static String FileCharset = "TIS-620";
    boolean isConnection;
    int complete;
    int incomplete;
    private boolean cmdSumAct = false;
    boolean Make_Download;
    String PT1;
    String PT2;
    String PT3;
    String PT4;
    String PT5;
    static String curPath = "C:/tmp/";
    static String pathBackup = "C:/spapplication/backupdatabase/backupupdate/";
    static boolean finshprocess = true;

    public UpdateDataFromCenter(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        Font myfont = new Font("Norasi", Font.PLAIN, 14);
        UIManager.put("Label.font", myfont);
        UIManager.put("Button.font", myfont);
        initComponents();
        
        lb_msg.setText("กรุณารอสักครู่ กำลังตรวจสอบข้อมูล...");

        ftp = new FTPUtility();
        LoadDataFromFile();
        ClearVariable();
        loadDefaultRadio();
    }

    public UpdateDataFromCenter() {

    }

    private void loadDateLast() {
        try {
            FileInputStream fis = new FileInputStream(new File("C:/spapplication/log/updatemasterfile.log"));
            Properties pp = new Properties();
            pp.load(fis);
            String m1 = pp.getProperty(Branch_Type + "_masterl.zip");
            String m2 = pp.getProperty("mem_masterl.zip");
            String m3 = pp.getProperty("ACR_MASTER.zip");
            String m4 = pp.getProperty("GIFT_MASTER.zip");
            String m5 = pp.getProperty("emp_masterl.zip");
            SimpleDateFormat simp2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
            MAS_LAST.setText(DateTimeFmt.format(simp2.parse(m1)));
            MEM_LAST.setText(DateTimeFmt.format(simp2.parse(m2)));
            AR_LAST.setText(DateTimeFmt.format(simp2.parse(m3)));
            GIFT_LAST.setText(DateTimeFmt.format(simp2.parse(m4)));
            EMP_LAST.setText(DateTimeFmt.format(simp2.parse(m5)));
        } catch (Exception e) {
        }
    }

    public void LockProcess() {
        bntExit.setEnabled(false);
        bntExit.setFocusable(false);
        bntOldData.setEnabled(false);
        bntOldData.setFocusable(false);
        bntOK.setEnabled(false);
        bntOK.setFocusable(false);
        //add
        cmdSystemSetup.setEnabled(false);
        cbxServer.setEnabled(false);
    }

    public void UnLockProcess() {
        bntExit.setEnabled(true);
        bntExit.setFocusable(true);
        bntOldData.setEnabled(true);
        bntOldData.setFocusable(true);
        bntOK.setEnabled(true);
        bntOK.setFocusable(true);
        //add
        cmdSystemSetup.setEnabled(true);
        cbxServer.setEnabled(true);
    }

    public void ClearVariable() {
        MAS_FileName.setText("");
        MAS_FileDate.setText("");
        Master_Chk.setSelected(true);
        Master_Chk.setEnabled(true);

        MEM_FileName.setText("");
        MEM_FileDate.setText("");
        MEM_Chk.setSelected(true);
        MEM_Chk.setEnabled(true);

        AR_FileName.setText("");
        AR_FileDate.setText("");
        AR_Chk.setSelected(true);
        AR_Chk.setEnabled(true);

        GIFT_FileName.setText("");
        GIFT_FileDate.setText("");
        GIFT_Chk.setSelected(true);
        GIFT_Chk.setEnabled(true);

        EMP_FileName.setText("");
        EMP_FileDate.setText("");
        EMP_Chk.setSelected(true);
        EMP_Chk.setEnabled(true);
    }

    void loadDefaultRadio() {
        cbxServer.setSelectedIndex(-1);
        String file1 = "C:/spapplication/dbconfig/ftp_local.ini";
        String file2 = "C:/spapplication/dbconfig/ftp_main.ini";
        String file3 = "C:/spapplication/dbconfig/ftp_secondary.ini";
        prop = new Properties();
        try {
            //config1-----------------------------------------------
            FileInputStream input = new FileInputStream(file1);
            prop.load(input);
            if (prop.getProperty("default").equalsIgnoreCase("true")) {
                cbxServer.setSelectedIndex(0);
               
            }else{
                //config2-----------------------------------------------
                input = new FileInputStream(file2);
                prop.load(input);
                if (prop.getProperty("default").equalsIgnoreCase("true")) {
                    cbxServer.setSelectedIndex(1);
                }else{
                    //config3-----------------------------------------------
                    input = new FileInputStream(file3);
                    prop.load(input);
                    if (prop.getProperty("default").equalsIgnoreCase("true")) {
                        cbxServer.setSelectedIndex(2);
                    }
                    input.close();
                }
            }
        } catch (Exception e){
            cbxServer.setSelectedIndex(0);
        }
    }

    public int CheckPOSON() {
        int cnt = 0;
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String SqlQuery = "select count(*) from posuser where Onact='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            cnt = rec.getInt("count(*)");
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return cnt;
    }

    public void LoadDataFromFile() {
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String SqlQuery = "select *from branch";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() != 0) {
                Branch_Code = rec.getString("code");
                Branch_Name = ThaiUtil.ASCII2Unicode(rec.getString("name"));
//                Branch_Type = rec.getString("btype");
                PT1 = rec.getString("PT1");
                PT2 = rec.getString("PT2");
                PT3 = rec.getString("PT3");
                PT4 = rec.getString("PT4");
                PT5 = rec.getString("PT5");
                B_Code.setText(Branch_Code);
                B_Name.setText(Branch_Name);
                B_Type.setText(Branch_Type);

            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public int CheckFileZip() {
        int cntfile = 0;
        String MasterFileName = Branch_Type + "_masterl.zip";
        File fObject = new File(FromCenterPath + "/" + MasterFileName);
        if (fObject.exists()) {
            Master_Chk.setSelected(true);
            Master_Chk.setEnabled(true);
            cntfile = cntfile + 1;
        } else {
            Master_Chk.setSelected(false);
            Master_Chk.setEnabled(false);
        }

        String MemFileName = "mem_masterl.zip";
        File fObject2 = new File(FromCenterPath + "/" + MemFileName);
        if (fObject2.exists()) {
            MEM_Chk.setSelected(true);
            MEM_Chk.setEnabled(true);
            cntfile = cntfile + 1;
        } else {
            MEM_Chk.setSelected(false);
            MEM_Chk.setEnabled(false);
        }

        String ARFileName = "ACR_MASTER.zip";
        File fObject3 = new File(FromCenterPath + "/" + ARFileName);
        if (fObject3.exists()) {
            AR_Chk.setSelected(true);
            AR_Chk.setEnabled(true);
            cntfile = cntfile + 1;
        } else {
            AR_Chk.setSelected(false);
            AR_Chk.setEnabled(false);
        }

        String GiftFileName = "GIFT_MASTER.zip";
        File fObject4 = new File(FromCenterPath + "/" + GiftFileName);
        if (fObject4.exists()) {
            GIFT_Chk.setSelected(true);
            GIFT_Chk.setEnabled(true);
            cntfile = cntfile + 1;
        } else {
            GIFT_Chk.setSelected(false);
            GIFT_Chk.setEnabled(false);
        }

        String EmpFileName = "emp_masterl.zip";
        File fObject5 = new File(FromCenterPath + "/" + EmpFileName);
        if (fObject5.exists()) {
            EMP_Chk.setSelected(true);
            EMP_Chk.setEnabled(true);
            cntfile = cntfile + 1;
        } else {
            EMP_Chk.setSelected(false);
            EMP_Chk.setEnabled(false);
        }
        return cntfile;
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

    public void DeleteFileZip() {
        String MasterFileName = Branch_Type + "_masterl.zip";
        String M_FileDate = "";
        File fObject = new File(FromCenterPath + "/" + MasterFileName);
        Date M_LastModify = new Date();
        if (fObject.exists()) {
            fObject.delete();
        }

        String MemFileName = "mem_masterl.zip";
        String Mem_FileDate = "";
        File fObject2 = new File(FromCenterPath + "/" + MemFileName);
        Date Mem_LastModify = new Date();
        if (fObject2.exists()) {
            fObject2.delete();
        }

        String ARFileName = "ACR_MASTER.zip";
        String A_FileDate = "";
        File fObject3 = new File(FromCenterPath + "/" + ARFileName);
        Date AR_LastModify = new Date();
        if (fObject3.exists()) {
            fObject3.delete();
        }

        String GiftFileName = "GIFT_MASTER.zip";
        String Gift_FileDate = "";
        File fObject4 = new File(FromCenterPath + "/" + GiftFileName);
        Date Gift_LastModify = new Date();
        if (fObject4.exists()) {
            fObject4.delete();
        }
        String EmpFileName = "emp_masterl.zip";
        String Emp_FileDate = "";
        File fObject5 = new File(FromCenterPath + "/" + EmpFileName);
        Date Emp_LastModify = new Date();
        if (fObject5.exists()) {
            fObject5.delete();
        }
        String LogoFileName = Branch_Type+"_logosp.zip";
        File fObject6 = new File(FromCenterPath + "/" + LogoFileName);
        if (fObject6.exists()) {
            fObject6.delete();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        B_Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        B_Type = new javax.swing.JTextField();
        B_Code = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Master_Chk = new javax.swing.JCheckBox();
        MEM_Chk = new javax.swing.JCheckBox();
        GIFT_Chk = new javax.swing.JCheckBox();
        AR_Chk = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        MAS_FileName = new javax.swing.JTextField();
        MAS_FileDate = new javax.swing.JTextField();
        MEM_FileName = new javax.swing.JTextField();
        MEM_FileDate = new javax.swing.JTextField();
        AR_FileName = new javax.swing.JTextField();
        GIFT_FileName = new javax.swing.JTextField();
        AR_FileDate = new javax.swing.JTextField();
        GIFT_FileDate = new javax.swing.JTextField();
        EMP_Chk = new javax.swing.JCheckBox();
        EMP_FileName = new javax.swing.JTextField();
        EMP_FileDate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        MAS_LAST = new javax.swing.JTextField();
        MEM_LAST = new javax.swing.JTextField();
        AR_LAST = new javax.swing.JTextField();
        GIFT_LAST = new javax.swing.JTextField();
        EMP_LAST = new javax.swing.JTextField();
        ProgressBar = new javax.swing.JProgressBar();
        ProcessStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        cbxServer = new javax.swing.JComboBox();
        cmdSystemSetup = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        pb = new javax.swing.JProgressBar();
        bntOldData = new javax.swing.JButton();
        lb_msg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFocusable(false);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 73, 18), 2, true));
        jPanel1.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Norasi", 0, 28)); // NOI18N
        jLabel1.setText("โปรแกรม Update ข้อมูลจากสำนักงานใหญ่");
        jLabel1.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        bntOK.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        bntOK.setText("รับข้อมูลจากสำนักงานใหญ่");
        bntOK.setFocusable(false);
        bntOK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntOK.setIconTextGap(0);
        bntOK.setMargin(new java.awt.Insets(2, 1, 1, 1));
        bntOK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
        bntOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bntOKKeyPressed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        bntExit.setText("ออก (Exit)");
        bntExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntExit.setIconTextGap(0);
        bntExit.setMargin(new java.awt.Insets(2, 0, 0, 0));
        bntExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
        bntExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bntExitKeyPressed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        jLabel2.setText("สำหรับสาขา : ");
        jLabel2.setFocusable(false);

        B_Name.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        B_Name.setFocusable(false);
        B_Name.setMargin(new java.awt.Insets(0, 3, 0, 0));

        jLabel3.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        jLabel3.setText("ประเภทสาขา");
        jLabel3.setFocusable(false);

        B_Type.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        B_Type.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        B_Type.setFocusable(false);

        B_Code.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        B_Code.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        B_Code.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(B_Type, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(B_Code, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(B_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_Code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(B_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "เลือกรายการที่จะทำการ Update ข้อมูล", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 14))); // NOI18N
        jPanel3.setFocusable(false);

        Master_Chk.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        Master_Chk.setText("ข้อมูล Master File");
        Master_Chk.setFocusable(false);

        MEM_Chk.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        MEM_Chk.setText("ข้อมูลสมาชิก (Member)");
        MEM_Chk.setFocusable(false);

        GIFT_Chk.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        GIFT_Chk.setText("ข้อมูลบัตรของขวัญ");
        GIFT_Chk.setFocusable(false);

        AR_Chk.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        AR_Chk.setText("ข้อมูลลูกหนี้ภายนอก (AR)");
        AR_Chk.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(9, 16, 128));
        jLabel4.setText("ประเภทข้อมูล (Data Type)");
        jLabel4.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(9, 16, 128));
        jLabel5.setText("ชื่อแฟ้มข้อมูล (File Name)");
        jLabel5.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(9, 16, 128));
        jLabel6.setText("วันที่ข้อมูลปัจจุบัน");
        jLabel6.setFocusable(false);

        MAS_FileName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAS_FileName.setFocusable(false);

        MAS_FileDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAS_FileDate.setFocusable(false);

        MEM_FileName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MEM_FileName.setFocusable(false);

        MEM_FileDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MEM_FileDate.setFocusable(false);

        AR_FileName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AR_FileName.setFocusable(false);

        GIFT_FileName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        GIFT_FileName.setFocusable(false);

        AR_FileDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AR_FileDate.setFocusable(false);

        GIFT_FileDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        GIFT_FileDate.setFocusable(false);

        EMP_Chk.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        EMP_Chk.setText("ข้อมูลรายชื่อพนักงาน");
        EMP_Chk.setFocusable(false);

        EMP_FileName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        EMP_FileName.setFocusable(false);

        EMP_FileDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        EMP_FileDate.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(9, 16, 128));
        jLabel8.setText("วันที่ปรับปรุงล่าสุด");
        jLabel8.setFocusable(false);

        MAS_LAST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAS_LAST.setFocusable(false);

        MEM_LAST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MEM_LAST.setFocusable(false);

        AR_LAST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AR_LAST.setFocusable(false);

        GIFT_LAST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        GIFT_LAST.setFocusable(false);

        EMP_LAST.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        EMP_LAST.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(Master_Chk)
                            .addComponent(MEM_Chk)
                            .addComponent(AR_Chk)
                            .addComponent(GIFT_Chk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GIFT_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AR_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MAS_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MEM_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EMP_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(EMP_Chk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(MEM_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MAS_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMP_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GIFT_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AR_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MEM_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MAS_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EMP_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GIFT_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AR_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(MAS_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MAS_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MAS_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Master_Chk))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(MEM_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MEM_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MEM_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MEM_Chk))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(AR_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AR_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AR_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AR_Chk))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(GIFT_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GIFT_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GIFT_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GIFT_Chk))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(EMP_LAST, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMP_FileDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMP_FileName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMP_Chk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ProgressBar.setFocusable(false);

        ProcessStatus.setText("Process Status");
        ProcessStatus.setFocusable(false);

        TextArea.setColumns(20);
        TextArea.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        TextArea.setRows(5);
        TextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        TextArea.setFocusable(false);
        TextArea.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(TextArea);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "กำหนดรับข้อมูลทาง ADSL ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 14))); // NOI18N
        jPanel4.setFocusable(false);

        cbxServer.setBackground(new java.awt.Color(254, 254, 254));
        cbxServer.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        cbxServer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Local FTP Server", "Main FTP Server", "Secondary FTP Server" }));
        cbxServer.setFocusable(false);
        cbxServer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServerItemStateChanged(evt);
            }
        });

        cmdSystemSetup.setBackground(java.awt.Color.orange);
        cmdSystemSetup.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        cmdSystemSetup.setText("System Setup");
        cmdSystemSetup.setFocusable(false);
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
        jPanel12.setFocusable(false);
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lb.setText("0/0 Byte");
        lb.setFocusable(false);
        jPanel12.add(lb);

        lblStatus.setForeground(new java.awt.Color(255, 0, 9));
        lblStatus.setText("lblStatus");
        lblStatus.setFocusable(false);
        jPanel12.add(lblStatus);

        pb.setFocusable(false);

        bntOldData.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        bntOldData.setText("ตรวจสอบ/รับข้อมูล โดยไม่ต้อง Down Load");
        bntOldData.setFocusable(false);
        bntOldData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOldDataActionPerformed(evt);
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
                    .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntOldData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntOldData, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbxServer, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(cmdSystemSetup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lb_msg.setFont(new java.awt.Font("Norasi", 1, 18)); // NOI18N
        lb_msg.setForeground(java.awt.Color.red);
        lb_msg.setText("กรุณารอสักครู่ กำลังตรวจสอบข้อมูล...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ProcessStatus)
                            .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ProcessStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_msg))
                            .addComponent(bntExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntOK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntExitMouseReleased
}//GEN-LAST:event_bntExitMouseReleased

private void bntOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntOKMouseReleased
}//GEN-LAST:event_bntOKMouseReleased
    boolean isDownload = false;
private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    //download data from ftp auto
    if (CheckPOSON() == 0) {
        if (CheckConnection()) {
            Make_Download = true;
            LockProcess();
            Thread unZipFile = new Thread(new ProcessUnZipFile());
            unZipFile.start();
        }
    } else {
        PUtility.ShowMsg("ยังมีเครื่อง POS กำลังทำงานอยู่...กรุณาออกจากการทำงานก่อนทำการ Update ข้อมูลจากสำนักงานใหญ่... ");
    }
}//GEN-LAST:event_bntOKActionPerformed

private void cmdSystemSetupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSystemSetupMouseReleased
    // TODO add your handling code here:
    //if (SetupFTPProperty.getSetupFTPProperty() == null) {
    GetPassword frm = new GetPassword(null, true);
    frm.setVisible(true);
    if (frm.ValidPassword) {
        SetupFTPProperty.createSetup();
        SetupFTPProperty.showSetupFTPProperty();
    }
}//GEN-LAST:event_cmdSystemSetupMouseReleased

private void bntOldDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOldDataActionPerformed
    if (CheckPOSON() == 0) {
        Make_Download = false;
        LockProcess();
        Thread unZipFile = new Thread(new ProcessUnZipFile());
        unZipFile.start();
    } else {
        PUtility.ShowMsg("ยังมีเครื่อง POS กำลังทำงานอยู่...กรุณาออกจากการทำงานก่อนทำการ Update ข้อมูลจากสำนักงานใหญ่... ");
    }
}//GEN-LAST:event_bntOldDataActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    DeleteFileZip();
    this.dispose();
}//GEN-LAST:event_bntExitActionPerformed

private void bntExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bntExitKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_bntExitKeyPressed

private void bntOKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bntOKKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_bntOKKeyPressed

private void cmdSystemSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSystemSetupActionPerformed
}//GEN-LAST:event_cmdSystemSetupActionPerformed

//private WaitProcess w = new WaitProcess(null, true);
private int count = 0;

private void cbxServerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServerItemStateChanged
    if (cbxServer.getSelectedIndex()!=-1) {
        new Thread(new Runnable() {

            public void run() {
                if(count<1){
                    count++;
                    lb_msg.setText("กรุณารอสักครู่ กำลังตรวจสอบข้อมูล...");
                    System.err.println("COUNT= " + count);
                    ClearVariable();
                    if(CheckConnection()){
                        loadConfFTPServer();
                        loadDateLast();
                        loadFtpDateFile();
                        displayArrayList();

                        checkEnableChk();

                        lb_msg.setText("[]");
                    }
                    count = 0;
                }
            }
        }).start();
    }
    
}//GEN-LAST:event_cbxServerItemStateChanged

    private boolean CheckConnection() {
        loadConfFTPServer();
        if (isCheckServer) {
            String server = prop.getProperty("host1");
            String user = prop.getProperty("userreceive1");
            String pass = prop.getProperty("passreceive1");
            int port = Integer.parseInt(prop.getProperty("port1"));

            ftp.setProgress(lb, pb);
            TextArea.append("\nกรุณารอสักครู่ระบบกำลังทำการติดต่อ FTP Server...\n");
            if (ftp.connect(server, user, pass, port)) {
                TextArea.append("การเชื่อมต่อสำเร็จ : " + server + "\n");
                return true;
            } else {
                TextArea.append("การเชื่อมล้มเหลว : " + server + "\n");
                return false;
            }
        } else {
            return true;
        }
    }
    private boolean isCheckServer = false;

    private void loadConfFTPServer() {
        if (((String) cbxServer.getSelectedItem()).equals("Local FTP Server")) {
            isCheckServer = false;
            loadLocalServer();
        } else if (((String) cbxServer.getSelectedItem()).equals("Main FTP Server")) {
            isCheckServer = true;
            loadMainServer();
        } else {
            isCheckServer = true;
            loadSecondaryServer();
        }
        System.out.println("LoadConfFTPServer");
    }

    private void loadLocalServer() {
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_local.ini");
            prop.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : " + "/spapplication/dbconfig/ftp_local.ini");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : " + "/spapplication/dbconfig/ftp_local.ini");
        }
    }

    private void loadMainServer() {
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_main.ini");
            prop.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : " + "/spapplication/dbconfig/ftp_main.ini");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : " + "/spapplication/dbconfig/ftp_main.ini");
        }
    }

    private void loadSecondaryServer() {
        prop = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_secondary.ini");
            prop.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : " + "/spapplication/dbconfig/ftp_main.ini");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : " + "/spapplication/dbconfig/ftp_main.ini");
        }
    }
public ArrayList<String[]> arr = new ArrayList<String[]>();

    private void loadFtpDateFile() {
        arr = new ArrayList<String[]>();
        String f_MASTER = Branch_Type + "_masterl.zip";
        String f_MEM = "mem_masterl.zip";
        String f_ACR = "ACR_MASTER.zip";
        String f_GIFT = "GIFT_MASTER.zip";
        String f_EMP = "emp_masterl.zip";
        String f_LOGO = Branch_Type + "_logosp.zip";

        SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        String path_log = "C:/spapplication/log/updatemasterfile.log";
        if(!new File(path_log).exists()){
            try {
                new File(path_log).createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            FileInputStream input = new FileInputStream(path_log);
            Properties pp = new Properties();
            pp.load(input);
            String server = "";
            String user = "";
            String pass = "";
            String port = "";
            String local = "";
            String remote = "";

            try {
                //BranType =prop.getProperty("bran1");
                if (isCheckServer) {
                    server = prop.getProperty("host1");
                    user = prop.getProperty("userreceive1");
                    pass = prop.getProperty("passreceive1");
                    port = prop.getProperty("port1");
                }
                local = prop.getProperty("receive1");
                remote = prop.getProperty("borreceive1");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String fileName = f_MASTER;
            String bRemote = remote + fileName;

            Date date_ftp = getDateFromFTP(server, user, pass, bRemote);
            String dateFtp = "ไม่พบข้อมูลอัพเดต";
            if(date_ftp!=null){
                dateFtp = sim.format(date_ftp);
            }

            String dateLog = "ไม่พบข้อมูลอัพเดต";
            Date date_log = getDateLogTime(fileName);
            if(date_log!=null){
                dateLog = sim.format(date_log);
            }
            arr.add(new String[]{fileName,dateFtp,dateLog});

            fileName = f_MEM;
            bRemote = remote + fileName;

            date_ftp = getDateFromFTP(server, user, pass, bRemote);
            dateFtp = "ไม่พบข้อมูลอัพเดต";
            if(date_ftp!=null){
                dateFtp = sim.format(date_ftp);
            }

            dateLog = "ไม่พบข้อมูลอัพเดต";
            date_log = getDateLogTime(fileName);
            if(date_log!=null){
                dateLog = sim.format(date_log);
            }
            arr.add(new String[]{fileName,dateFtp,dateLog});

            fileName = f_ACR;
            bRemote = remote + fileName;

            date_ftp = getDateFromFTP(server, user, pass, bRemote);
            dateFtp = "ไม่พบข้อมูลอัพเดต";
            if(date_ftp!=null){
                dateFtp = sim.format(date_ftp);
            }

            dateLog = "ไม่พบข้อมูลอัพเดต";
            date_log = getDateLogTime(fileName);
            if(date_log!=null){
                dateLog = sim.format(date_log);
            }
            arr.add(new String[]{fileName,dateFtp,dateLog});

            fileName = f_GIFT;
            bRemote = remote + fileName;

            date_ftp = getDateFromFTP(server, user, pass, bRemote);
            dateFtp = "ไม่พบข้อมูลอัพเดต";
            if(date_ftp!=null){
                dateFtp = sim.format(date_ftp);
            }

            dateLog = "ไม่พบข้อมูลอัพเดต";
            date_log = getDateLogTime(fileName);
            if(date_log!=null){
                dateLog = sim.format(date_log);
            }
            arr.add(new String[]{fileName,dateFtp,dateLog});


            fileName = f_EMP;
            bRemote = remote + fileName;

            date_ftp = getDateFromFTP(server, user, pass, bRemote);
            dateFtp = "ไม่พบข้อมูลอัพเดต";
            if(date_ftp!=null){
                dateFtp = sim.format(date_ftp);
            }

            dateLog = "ไม่พบข้อมูลอัพเดต";
            date_log = getDateLogTime(fileName);
            if(date_log!=null){
                dateLog = sim.format(date_log);
            }
            arr.add(new String[]{fileName,dateFtp,dateLog});

            fileName = f_LOGO;
            bRemote = remote + fileName;

            date_ftp = getDateFromFTP(server, user, pass, bRemote);
            dateFtp = "ไม่พบข้อมูลอัพเดต";
            if(date_ftp!=null){
                dateFtp = sim.format(date_ftp);
            }

            dateLog = "ไม่พบข้อมูลอัพเดต";
            date_log = getDateLogTime(fileName);
            if(date_log!=null){
                dateLog = sim.format(date_log);
            }
            arr.add(new String[]{fileName,dateFtp,dateLog});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void displayArrayList(){
        //display arraylist
        String []data = (String[])arr.get(0);
        MAS_FileName.setText(data[0]);
        MAS_FileDate.setText(data[1]);
        MAS_LAST.setText(data[2]);

        data = (String[])arr.get(1);
        MEM_FileName.setText(data[0]);
        MEM_FileDate.setText(data[1]);
        MEM_LAST.setText(data[2]);

        data = (String[])arr.get(2);
        AR_FileName.setText(data[0]);
        AR_FileDate.setText(data[1]);
        AR_LAST.setText(data[2]);

        data = (String[])arr.get(3);
        GIFT_FileName.setText(data[0]);
        GIFT_FileDate.setText(data[1]);
        GIFT_LAST.setText(data[2]);

        data = (String[])arr.get(4);
        EMP_FileName.setText(data[0]);
        EMP_FileDate.setText(data[1]);
        EMP_LAST.setText(data[2]);
    }
    private void showCompleteDownLoad() {
        TextArea.append("Download สำเร็จ := ");
        TextArea.append(complete + "\n");
        TextArea.append("Download ไม่สำเร็จ := ");
        TextArea.append(incomplete + "\n");
    }

    private void showCompleteDownLoad(int complete, int incomplete) {
        TextArea.append("Download สำเร็จ := ");
        TextArea.append(complete + "\n");
        TextArea.append("Download ไม่สำเร็จ := ");
        TextArea.append(incomplete + "\n");
    }

    private Date getDateFromFTP(String host, String user, String pass, String fileName) {
        SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        FTPClient ftpCheck = new FTPClient();
        FTPFile[] f;
        Date date = null;
        if(isCheckServer){
            try {
                ftpCheck.connect(host);
                ftpCheck.login(user, pass);
                f = ftpCheck.listFiles();
                for (FTPFile fp : f) {
                    if (fileName.equalsIgnoreCase(fp.getName())) {
    //                    System.out.println(fp.getName() + "=" + simp.format(fp.getTimestamp().getTime()));
                        date = fp.getTimestamp().getTime();
                        break;
                    }
                }
                ftpCheck.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
                date = null;
            }
        }else{
            //check in local
            File file = new File(fileName);
            if(file.exists()){
                Long lastModified = file.lastModified();
                date = new Date(lastModified);
            }
        }
        return date;
    }

    private Date getDateLogTime(String program) {
        File path = new File("C:/spapplication/log/updatemasterfile.log");
        Date date = null;
        try {
            if (path.exists()) {
                FileInputStream file = new FileInputStream(path);
                Properties pp = new Properties();
                pp.load(file);
                String d = pp.getProperty(program);
                SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss");
                date = simp.parse(d);
            } else {
                date = null;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            date = null;
        }

        return date;
    }
    private int TYPE_UPDATE = 1;
    private int TYPE_SKIP = -1;
    private int TYPE_NOUPDATE = 0;

    private int checkDateToUpdate(String fileName) {
        String host = null, user = null, pass = null;
        String remote = null, local = null;
        int port = 21;
        try {
            if (isCheckServer) {
                host = prop.getProperty("host1");
                user = prop.getProperty("userreceive1");
                pass = prop.getProperty("passreceive1");
                port = Integer.parseInt(prop.getProperty("port1"));
            }
            local = prop.getProperty("receive1");
            remote = prop.getProperty("borreceive1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String bLocal = local + fileName;
        String bRemote = remote + fileName;

        //----------------------------------------------------------------------------
        //d1 = วันที่บน server
        //d2 = วันที่ในล็อกไฟล์
        int TYPE = 0;
        Date d1 = getDateFromFTP(host, user, pass, bRemote);
        Date d2 = getDateLogTime(fileName);
        if (d1 == null) {
            TYPE = TYPE_SKIP;
        }
        if (d1 != null && d2 == null) {
            TYPE = TYPE_UPDATE;
        }
        if (d1 != null && d2 != null) {
            System.out.println("date1(Server): " + d1);
            System.out.println("date2(Log): " + d2);
            if (d1.compareTo(d2) == 0) {
                TYPE = TYPE_NOUPDATE;
            } else if (d1.compareTo(d2) == -1) {
                TYPE = TYPE_NOUPDATE;
            } else {
                TYPE = TYPE_UPDATE;
            }
            System.out.println("TYPE: " + TYPE);
        }

        return TYPE;
    }

    public static void main(String[] args) {
        UpdateDataFromCenter update = new UpdateDataFromCenter();
        update.checkDateToUpdate("111_masterl.zip");
    }
    private String logStr = "";

    void down1() {
        complete = 0;
        incomplete = 0;

        TextArea.append("\n(1) รับข้อมูล Master File   จากสำนักงานใหญ่");
        TextArea.append("\n----------------------------\n");

        String server = null, user = null, pass = null;
        File f = null;
        int port = 21;

        String remote = null, local = null;

        try {
            if (isCheckServer) {
                server = prop.getProperty("host1");
                user = prop.getProperty("userreceive1");
                pass = prop.getProperty("passreceive1");
                port = Integer.parseInt(prop.getProperty("port1"));
            }
            local = prop.getProperty("receive1");
            remote = prop.getProperty("borreceive1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Bran-Type_Master
        String fileName = Branch_Type + "_masterl.zip";
        String bLocal = local + fileName;
        String bRemote = remote + fileName;

        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        if (isCheckServer) {
            download(server, user, pass, port, bLocal, bRemote);
            logStr += fileName + "=" + simp.format(getDateFromFTP(server, user, pass, bRemote)) + "\n";
        } else {
            copyFile(server, user, pass, port, bLocal, bRemote);//to
            logStr += fileName + "=" + simp.format(new Date(new File(bRemote).lastModified())) + "\n";
        }

        //Bran-Type_logosp
        fileName = Branch_Type + "_logosp.zip";
        bLocal = local + fileName;
        bRemote = remote + fileName;
        if (isCheckServer) {
            downFile(server, user, pass, port, bLocal, bRemote);
        } else {
            copyFile(server, user, pass, port, bLocal, bRemote);
        }

        if (!cmdSumAct) {
            showCompleteDownLoad();
        }
    }

    void down2() {
        complete = 0;
        incomplete = 0;

        TextArea.append("\n(2) รับข้อมูลสมาชิก   จากสำนักงานใหญ่");
        TextArea.append("\n----------------------------\n");

        String server = null, user = null, pass = null;
        File f = null;
        int port = 21;

        String remote = null, local = null;

        //MEM_Master
        try {
            if (isCheckServer) {
                server = prop.getProperty("host2");
                user = prop.getProperty("userreceive2");
                pass = prop.getProperty("passreceive2");
                port = Integer.parseInt(prop.getProperty("port2"));
            }
            local = prop.getProperty("receive2");
            remote = prop.getProperty("borreceive1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        String fileName = "mem_masterl.zip";
        String bLocal = local + fileName;
        String bRemote = remote + fileName;
            if (isCheckServer) {
                download(server, user, pass, port, bLocal, bRemote);
                logStr += fileName + "=" + simp.format(getDateFromFTP(server, user, pass, bRemote)) + "\n";
            } else {
                copyFile(server, user, pass, port, bLocal, bRemote);//to
                logStr += fileName + "=" + simp.format(new Date(new File(bRemote).lastModified())) + "\n";
            }
        if (!cmdSumAct) {
            showCompleteDownLoad();
        }
    }

    void down3() {
        complete = 0;
        incomplete = 0;

        TextArea.append("\n(3) รับข้อมูล ลูกหนี้ภายนอก จากสำนักงานใหญ่");
        TextArea.append("\n----------------------------\n");

        String server = null, user = null, pass = null;
        File f = null;
        int port = 21;

        String remote = null, local = null;

        try {
            if (isCheckServer) {
                server = prop.getProperty("host1");
                user = prop.getProperty("userreceive1");
                pass = prop.getProperty("passreceive1");
                port = Integer.parseInt(prop.getProperty("port1"));
            }
            local = prop.getProperty("receive1");
            remote = prop.getProperty("borreceive1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        //ARC_Master
        String fileName = "ACR_MASTER.zip";
        String bLocal = local + fileName;
        String bRemote = remote + fileName;
            if (isCheckServer) {
                download(server, user, pass, port, bLocal, bRemote);
                logStr += fileName + "=" + simp.format(getDateFromFTP(server, user, pass, bRemote)) + "\n";
            } else {
                copyFile(server, user, pass, port, bLocal, bRemote);//to
                logStr += fileName + "=" + simp.format(new Date(new File(bRemote).lastModified())) + "\n";
            }
        if (!cmdSumAct) {
            showCompleteDownLoad();
        }
    }

    void down4() {
        complete = 0;
        incomplete = 0;

        TextArea.append("\n(4) รับข้อมูลบัตรของขวัญ   จากสำนักงานใหญ่");
        TextArea.append("\n----------------------------\n");

        String server = null, user = null, pass = null;
        File f = null;
        int port = 21;

        String remote = null, local = null;

        //MEM_Master
        try {
            if (isCheckServer) {
                server = prop.getProperty("host3");
                user = prop.getProperty("userreceive3");
                pass = prop.getProperty("passreceive3");
                port = Integer.parseInt(prop.getProperty("port3"));
            }
            local = prop.getProperty("receive3");
            remote = prop.getProperty("borreceive1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        String fileName = "GIFT_MASTER.zip";
        String bLocal = local + fileName;
        String bRemote = remote + fileName;
            if (isCheckServer) {
                download(server, user, pass, port, bLocal, bRemote);
                logStr += fileName + "=" + simp.format(getDateFromFTP(server, user, pass, bRemote)) + "\n";
            } else {
                copyFile(server, user, pass, port, bLocal, bRemote);//to
                logStr += fileName + "=" + simp.format(new Date(new File(bRemote).lastModified())) + "\n";
            }
        if (!cmdSumAct) {
            showCompleteDownLoad();
        }
    }

    void down5() {
        complete = 0;
        incomplete = 0;

        TextArea.append("\n(5) รับข้อมูลรหัสพนักงาน   จากสำนักงานใหญ่");
        TextArea.append("\n----------------------------\n");

        String server = null, user = null, pass = null;
        File f = null;
        int port = 21;

        String remote = null, local = null;

        //MEM_Master
        try {
            if (isCheckServer) {
                server = prop.getProperty("host3");
                user = prop.getProperty("userreceive3");
                pass = prop.getProperty("passreceive3");
                port = Integer.parseInt(prop.getProperty("port3"));
            }
            local = prop.getProperty("receive3");
            remote = prop.getProperty("borreceive3");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat simp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        String fileName = "emp_masterl.zip";
        String bLocal = local + fileName;
        String bRemote = remote + fileName;
            if (isCheckServer) {
                download(server, user, pass, port, bLocal, bRemote);
                logStr += fileName + "=" + simp.format(getDateFromFTP(server, user, pass, bRemote)) + "\n";
            } else {
                copyFile(server, user, pass, port, bLocal, bRemote);//to
                logStr += fileName + "=" + simp.format(new Date(new File(bRemote).lastModified())) + "\n";
            }
        if (!cmdSumAct) {
            showCompleteDownLoad();
        }
    }

    private boolean download(String ftpServer, String user, String password, int port,
            String source, String fileName) {
        boolean success = false;

        FTPUtility_bak ftp3 = new FTPUtility_bak();
        ftp3.setProgress(lb, pb);

        String name = null;
        StringTokenizer st = new StringTokenizer(source, "/");
        for (int i = 0; i < st.countTokens(); i++) {
            if (i == st.countTokens() - 1) {
                name = st.nextToken();
            } else {
                st.nextToken();
            }
        }

        lblStatus.setText("Download " + name);

        if (ftp3.connect(ftpServer, user, password, port)) {
            try {
                if (ftp3.download1(fileName, source)) {
                    success = true;
                } else {
                    //JOptionPane.showMessageDialog(this, "Can not upload server");
                    success = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            //JOptionPane.showMessageDialog(this, "Can not Connect server");
            success = false;
        }
        ftp3.disconnect();
        return success;
    }

    private void downFile(String server, String user, String pass, int port, String local, String remote) {
        if (!checkData(server, user, port, local, remote)) {
            TextArea.append(local + " => ไม่สำเร็จ ตรวจสอบ ไฟล์ ftp_" + prop.getProperty("config") + ".ini\n");
            incomplete++;
        } else {
            if (download(server, user, pass, port, local, remote)) {
                TextArea.append(local + " => สำเร็จ\n");
                complete++;
            } else {
                TextArea.append(local + " => ไม่สำเร็จ download ล้มเหลว\n");
                incomplete++;
            }
        }
    }

    public void copyFile(String server, String user, String pass, int port, String dtFile, String srFile) {
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
            complete++;
            in.close();
            out.close();
            System.out.println("File copied.");
        } catch (FileNotFoundException ex) {
            incomplete++;
            System.out.println(ex.getMessage() + " in the specified directory.");
        } catch (IOException e) {
            incomplete++;
            System.out.println(e.getMessage());
        }
    }

    private boolean checkData(String server, String user, int port, String local, String remote) {
        if (server == null || user == null || local == null || remote == null) {
            JOptionPane.showMessageDialog(this, "Please check data in ftp" + prop.getProperty("config") + ".ini.");
            return false;
        } else {
            return true;
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                UpdateDataFromCenter dialog = new UpdateDataFromCenter(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AR_Chk;
    private javax.swing.JTextField AR_FileDate;
    private javax.swing.JTextField AR_FileName;
    private javax.swing.JTextField AR_LAST;
    private javax.swing.JTextField B_Code;
    private javax.swing.JTextField B_Name;
    private javax.swing.JTextField B_Type;
    private javax.swing.JCheckBox EMP_Chk;
    private javax.swing.JTextField EMP_FileDate;
    private javax.swing.JTextField EMP_FileName;
    private javax.swing.JTextField EMP_LAST;
    private javax.swing.JCheckBox GIFT_Chk;
    private javax.swing.JTextField GIFT_FileDate;
    private javax.swing.JTextField GIFT_FileName;
    private javax.swing.JTextField GIFT_LAST;
    private javax.swing.JTextField MAS_FileDate;
    private javax.swing.JTextField MAS_FileName;
    private javax.swing.JTextField MAS_LAST;
    private javax.swing.JCheckBox MEM_Chk;
    private javax.swing.JTextField MEM_FileDate;
    private javax.swing.JTextField MEM_FileName;
    private javax.swing.JTextField MEM_LAST;
    private javax.swing.JCheckBox Master_Chk;
    private javax.swing.JLabel ProcessStatus;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton bntOldData;
    private javax.swing.JComboBox cbxServer;
    private javax.swing.JButton cmdSystemSetup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb_msg;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar pb;
    // End of variables declaration//GEN-END:variables
    Properties prop;
    FTPUtility ftp;

    private void checkEnableChk() {
        //check สถานะ ของ masterfile ก่อนการอัพโหลดข้อมูล
        String M1 = Branch_Type + "_masterl.zip";
        String M2 = "mem_masterl.zip";
        String M3 = "ACR_MASTER.zip";
        String M4 = "GIFT_MASTER.zip";
        String M5 = "emp_masterl.zip";

        Master_Chk.setEnabled(true);
        MEM_Chk.setEnabled(true);
        AR_Chk.setEnabled(true);
        GIFT_Chk.setEnabled(true);
        EMP_Chk.setEnabled(true);

        if (checkDateToUpdate(M1) == TYPE_NOUPDATE) {
            //delay time...
//            WaitDialog wait = new WaitDialog(null, true, Master_Chk.getText());
//            wait.setVisible(true);
//            Master_Chk.setSelected(false);
//            if (WaitDialog.isAnswer) {
                Master_Chk.setSelected(false);
//            }
            TextArea.append("ข้อมูล Master File ถูกอัพเดตแล้วเมื่อ " + MAS_LAST.getText() + " \n");
        } else {
            Master_Chk.setSelected(false);
            if (checkDateToUpdate(M1) == TYPE_SKIP) {
                Master_Chk.setEnabled(false);
            }else{
                Master_Chk.setSelected(true);
            }
        }
        if (checkDateToUpdate(M2) == TYPE_NOUPDATE) {
            //delay time...
//            WaitDialog wait = new WaitDialog(null, true, MEM_Chk.getText());
//            wait.setVisible(true);
//            MEM_Chk.setSelected(false);
//            if (WaitDialog.isAnswer) {
                MEM_Chk.setSelected(false);
//            }
            TextArea.append("ข้อมูล Mem Master ถูกอัพเดตแล้วเมื่อ " + MEM_LAST.getText() + " \n");
        } else {
            MEM_Chk.setSelected(false);
            if (checkDateToUpdate(M2) == TYPE_SKIP) {
                MEM_Chk.setEnabled(false);
            }else{
                MEM_Chk.setSelected(true);
            }
        }
        if (checkDateToUpdate(M3) == TYPE_NOUPDATE) {
            //delay time...
//            WaitDialog wait = new WaitDialog(null, true, AR_Chk.getText());
//            wait.setVisible(true);
//            AR_Chk.setSelected(false);
//            if (WaitDialog.isAnswer) {
                AR_Chk.setSelected(false);
//            }
            TextArea.append("ข้อมูล ACR Master ถูกอัพเดตแล้วเมื่อ " + AR_LAST.getText() + " \n");
        } else {
            AR_Chk.setSelected(false);
            if (checkDateToUpdate(M3) == TYPE_SKIP) {
                AR_Chk.setEnabled(false);
            }else{
                AR_Chk.setSelected(true);
            }
        }

        if (checkDateToUpdate(M4) == TYPE_NOUPDATE) {
            //delay time...
//            WaitDialog wait = new WaitDialog(null, true, GIFT_Chk.getText());
//            wait.setVisible(true);
//            GIFT_Chk.setSelected(false);
//            if (WaitDialog.isAnswer) {
                GIFT_Chk.setSelected(false);
//            }
            TextArea.append("ข้อมูล Gift Master ถูกอัพเดตแล้วเมื่อ " + GIFT_LAST.getText() + " \n");
        } else {
            GIFT_Chk.setSelected(false);
            if (checkDateToUpdate(M4) == TYPE_SKIP) {
                GIFT_Chk.setEnabled(false);
            }else{
                GIFT_Chk.setSelected(true);
            }
        }
        if (checkDateToUpdate(M5) == TYPE_NOUPDATE) {
            //delay time...
//            WaitDialog wait = new WaitDialog(null, true, EMP_Chk.getText());
//            wait.setVisible(true);
//            EMP_Chk.setSelected(false);
//            if (WaitDialog.isAnswer) {
                EMP_Chk.setSelected(false);
//            }
            TextArea.append("ข้อมูล Employee Master ถูกอัพเดตแล้วเมื่อ " + EMP_LAST.getText() + " \n");
        } else {
            EMP_Chk.setSelected(false);
            if (checkDateToUpdate(M5) == TYPE_SKIP) {
                EMP_Chk.setEnabled(false);
            }else{
                EMP_Chk.setSelected(true);
            }
        }
    }

    class DeterminProgressBar extends javax.swing.JDialog {

        public void DeterminProgressBar() {
            ProgressBar.setMaximum(minValue);
            ProgressBar.setMaximum(maxValue);
            ProgressBar.setValue(0);
            ProgressBar.setStringPainted(true);
            timer = new Timer(100, new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (curValue > maxValue) {
                        return;
                    }
                    ProgressBar.setValue(curValue);
                }
            });
            timer.start();
        }
    }

    class ProcessUnZipFile extends javax.swing.JDialog implements Runnable {
        int maxValue;
        int minValue;
        JOptionPane Jop;

        public void run() {
            finshprocess = false;
            TextArea.setForeground(Color.black);
            if (Make_Download) {
                cmdSumAct = true;
                int completeTotal = 0;
                int incompleteTotal = 0;
                if (Master_Chk.isSelected()) {
                    down1();
                    completeTotal += complete;
                    incompleteTotal += incomplete;
                }
                if (MEM_Chk.isSelected()) {
                    down2();
                    completeTotal += complete;
                    incompleteTotal += incomplete;
                }
                if (AR_Chk.isSelected()) {
                    down3();
                    completeTotal += complete;
                    incompleteTotal += incomplete;
                }

                if (GIFT_Chk.isSelected()) {
                    down4();
                    completeTotal += complete;
                    incompleteTotal += incomplete;
                }

                if (EMP_Chk.isSelected()) {
                    down5();
                    completeTotal += complete;
                    incompleteTotal += incomplete;
                }

                showCompleteDownLoad(completeTotal, incompleteTotal);
                cmdSumAct = false;
                isDownload = true;
            }
            if (CheckFileZip() > 0) {
                LoadDataFromFile();
                Backuptable();

                TextArea.selectAll();
                TextArea.cut();
                TextArea.append("***** Start Process *****\n");
                File TempPath = new File(PathTemp);
                File TempPathConvert = new File(PathConvert);
                DirectoryUtility dirUtil = new DirectoryUtility();
                File fl[] = TempPath.listFiles();
                File flConvert[] = TempPathConvert.listFiles();
                try {
                    for (int i = 0; i < fl.length; i++) {
                        dirUtil.deleteDir(fl[i]);
                    }
                    if (!dirUtil.deleteDir(TempPath)) {
                        JOptionPane.showMessageDialog(this, "Can'n Delete" + TempPath);
                    }
                    if (!dirUtil.createDir(TempPath)) {
                        JOptionPane.showMessageDialog(this, "Can'n Create" + TempPath);
                    }
                    for (int i = 0; i < flConvert.length; i++) {
                        dirUtil.deleteDir(flConvert[i]);
                    }
                    if (!dirUtil.deleteDir(TempPathConvert)) {
                        JOptionPane.showMessageDialog(this, "Can'n Delete" + TempPathConvert);
                    }
                    if (!dirUtil.createDir(TempPathConvert)) {
                        JOptionPane.showMessageDialog(this, "Can'n Create" + TempPathConvert);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (Master_Chk.isSelected()) {
                    String zipFile = FromCenterPath + "/" + MAS_FileName.getText();
                    ProcessStatus.setText("UnZip : " + zipFile);
                    ZipUnzip zipUtil = new ZipUnzip();
                    if (!zipUtil.unzip(zipFile, PathTemp)) {
                        JOptionPane.showMessageDialog(this, "ไฟล์ข้อมูล Master " + zipFile + "ไม่สมบูรณ์ กรุณา โหลดไฟล์ใหม่...");
                        TextArea.append("Unzip " + MAS_FileName.getText() + "...Error \n");
                        Master_Chk.setSelected(false);
                    } else {
                        TextArea.append("Unzip " + MAS_FileName.getText() + "...OK \n");
                    }
                }
                if (MEM_Chk.isSelected()) {
                    String zipFile = FromCenterPath + "/" + MEM_FileName.getText();
                    ProcessStatus.setText("UnZip : " + zipFile);
                    ZipUnzip zipUtil = new ZipUnzip();
                    if (!zipUtil.unzip(zipFile, PathTemp)) {
                        JOptionPane.showMessageDialog(this, "ไฟล์ข้อมูล สมาชิก(Member) " + zipFile + "ไม่สมบูรณ์ กรุณา โหลดไฟล์ใหม่...");
                        TextArea.append("Unzip " + MEM_FileName.getText() + "...Error \n");
                        MEM_Chk.setSelected(false);
                    } else {
                        TextArea.append("Unzip " + MEM_FileName.getText() + "...OK \n");
                    }
                }
                if (AR_Chk.isSelected()) {
                    String zipFile = FromCenterPath + "/" + AR_FileName.getText();
                    ProcessStatus.setText("UnZip : " + zipFile);
                    ZipUnzip zipUtil = new ZipUnzip();
                    if (!zipUtil.unzip(zipFile, PathTemp)) {
                        JOptionPane.showMessageDialog(this, "ไฟล์ข้อมูล ลูกหนี้ภายนอก " + zipFile + "ไม่สมบูรณ์ กรุณา โหลดไฟล์ใหม่...");
                        TextArea.append("Unzip " + AR_FileName.getText() + "...Error \n");
                        AR_Chk.setSelected(false);
                    } else {
                        TextArea.append("Unzip " + AR_FileName.getText() + "...OK \n");
                    }
                }
                if (GIFT_Chk.isSelected()) {
                    String zipFile = FromCenterPath + "/" + GIFT_FileName.getText();
                    ProcessStatus.setText("UnZip : " + zipFile);
                    ZipUnzip zipUtil = new ZipUnzip();
                    if (!zipUtil.unzip(zipFile, PathTemp)) {
                        JOptionPane.showMessageDialog(this, "ไฟล์ข้อมูล บัตรของขวัญ " + zipFile + "ไม่สมบูรณ์ กรุณา โหลดไฟล์ใหม่...");
                        TextArea.append("Unzip " + GIFT_FileName.getText() + "...Error \n");
                        GIFT_Chk.setSelected(false);
                    } else {
                        TextArea.append("Unzip " + GIFT_FileName.getText() + "...OK \n");
                    }
                }
                if (EMP_Chk.isSelected()) {
                    String zipFile = FromCenterPath + "/" + EMP_FileName.getText();
                    ProcessStatus.setText("UnZip : " + zipFile);
                    ZipUnzip zipUtil = new ZipUnzip();
                    if (!zipUtil.unzip(zipFile, PathTemp)) {
                        JOptionPane.showMessageDialog(this, "ไฟล์ข้อมูล รหัสพนักงาน " + zipFile + "ไม่สมบูรณ์ กรุณา โหลดไฟล์ใหม่...");
                        TextArea.append("Unzip " + EMP_FileName.getText() + "...Error \n");
                        EMP_Chk.setSelected(false);
                    } else {
                        TextArea.append("Unzip " + EMP_FileName.getText() + "...OK \n");
                    }
                }
                //Update Logosp.jpg
                String LogospFileName = Branch_Type + "_logosp.zip";
                File fObject = new File(FromCenterPath + "/" + LogospFileName);
                String srFile = FromCenterPath + "/" + LogospFileName;
                String crFile = "C:/spapplication/dbconfig/logosp.jpg";
                if (fObject.exists()) {
                    copyfile(srFile, crFile);
                }

                if (Master_Chk.isSelected()) {
                    TextArea.append("------------------------------------------------- \n");
                    processStop = false;
                    if (!processStop) {
                        ProcessCredit();
                    }
                    if (!processStop) {
                        ProcessCupon();
                    }
                    if (!processStop) {
                        ProcessCuList();
                    }
                    if (!processStop) {
                        ProcessPromotion();
                    }
                    if (!processStop) {
                        ProcessGroup();
                    }
                    if (!processStop) {
                        ProcessBank();
                    }
                    if (!processStop) {
                        ProcessVender();
                    }
                    if (!processStop) {
                        ProcessProduct();
                    }
                    if (!processStop) {
                        ProcessSubPlu();
                    }
                    if (!processStop) {
                        ProcessBranch();
                    }
                    if (!processStop) {
                        ProcessPosConfig();
                    }
                    if (!processStop) {
                        ProcessUserGroup();
                    }
                    if (!processStop) {
                        ProcessPUser();
                    }
                    if (!processStop) {
                        Processeffective();
                        UpdateEfective();
                    }
                    //For Cherge
                    if (!processStop) {
                        srFile = PathTemp + '/' + "chargemaster.txt";
                        fObject = new File(srFile);
                        if (fObject.exists()) {
                           ProcessChargMaster();
                        }
                    }
                    if (!processStop) {
                        srFile = PathTemp + '/' + "chargebranch.txt";
                        fObject = new File(srFile);
                        if (fObject.exists()) {
                            ProcessChargeBranch();
                        }
                    }
                    //For Mix and Match
                    if (!processStop) {
                        srFile = PathTemp + '/' + "pingredent.txt";
                        fObject = new File(srFile);
                        if (fObject.exists()) {
                            ProcessPingredent();
                        }
                    }

                    //*******************
                }
                if (MEM_Chk.isSelected()) {
                    TextArea.append("------------------------------------------------- \n");
                    if (!processStop) {
                        ProcessMember();
                    }
                    if (!processStop) {
                        ProcessMPoint();
                    }
                    if (!processStop) {
                        ProcessMBranch();
                    }
                    if (!processStop) {
                        ProcessSMS();
                    }
                }
                if (AR_Chk.isSelected()) {
                    TextArea.append("------------------------------------------------- \n");
                    if (!processStop) {
                        ProcessCust();
                    }
                    if (!processStop) {
                        ProcessAR();
                    }
                }
                if (GIFT_Chk.isSelected()) {
                    TextArea.append("------------------------------------------------- \n");
                    if (!processStop) {
                        ProcessGiftPrice();
                    }
                    if (!processStop) {
                        ProcessGiftStatus();
                    }
                }
                if (EMP_Chk.isSelected()) {
                    TextArea.append("------------------------------------------------- \n");
                    if (!processStop) {
                        ProcessEmploy();
                    }
                }
                if (!processStop) {
                    UpdateOperation();

                    SimpleDateFormat simp1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    SimpleDateFormat simp2 = new SimpleDateFormat("yyyyMMddHHmmss");
                    //write log
                    TextWriter write = new TextWriter();
                    new File("C:/spapplication/log/updatemasterfile.log").delete();
                    for (int i = 0; i < arr.size(); i++) {
                        String []dd = (String[])arr.get(i);
                        //---------------
                        if(dd[1].equals("ไม่พบข้อมูลอัพเดต")){
                            dd[1] = dd[2];
                        }
                        if(i==0){
                            if(!Master_Chk.isSelected()){
                                dd[1] = dd[2];
                            }
                        }
                        if(i==1){
                            if(!MEM_Chk.isSelected()){
                                dd[1] = dd[2];
                            }
                        }
                        if(i==2){
                            if(!AR_Chk.isSelected()){
                                dd[1] = dd[2];
                            }
                        }
                        if(i==3){
                            if(!GIFT_Chk.isSelected()){
                                dd[1] = dd[2];
                            }
                        }
                        if(i==4){
                            if(!EMP_Chk.isSelected()){
                                dd[1] = dd[2];
                            }
                        }
                        if(i==5){
                            dd[1] = "";
                        }
                        //---------------
                        try {
                            if(!dd[1].equals("")){
                                dd[1] = simp2.format(simp1.parse(dd[1]));
                            }
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        if(i!=5){
                            write.writeToText("C:/spapplication/log/updatemasterfile.log",
                                    dd[0]+"="+dd[1]);
                        }
                    }

                    TextArea.append("----------------จบการทำงาน--------------------- \n");
                    JOptionPane.showMessageDialog(this, "การรับข้อมูลจากสำนักงานใหญ่...สมบูรณ์");
                } else {
                    Restoretable();
                    TextArea.append("---------การรับข้อมูลมีปัญหา !!!!------------------ \n");
                    TextArea.setForeground(Color.red);
                    JOptionPane.showMessageDialog(this, "การรับข้อมูลจากสำนักงานใหญ่...,มีปัญหาบางรายการ...กรุณาตรวจสอบ !!! \n กรุณา Update ข้อมูลจากสำนักงานใหญ่ ภายหลัง ...");
                }
            } else {
//                JOptionPane.showMessageDialog(this, "ไม่พบ Master File จากสำนักงานใหญ่...ไม่สามารถ Update ข้อมูลได้ !!!");
                JOptionPane.showMessageDialog(null, "ไม่มีการปรับปรุงข้อมูลใหม่จากสำนักงานใหญ่...","",
                    JOptionPane.WARNING_MESSAGE);
//                ClearVariable();
            }
            finshprocess = true;
            //write log file to update masterfile
//            TextWriter tw = new TextWriter();
//            if (!logStr.equals("")) {
//                new File("/spapplication/log/updatemasterfile.log").delete();
//                tw.writeToText("/spapplication/log/updatemasterfile.log", logStr);
//            }
//            logStr = "";
            UnLockProcess();
        }

        public void UpdateEfective() {
            String eff_date = "";
            String pcode = "";
            double price1 = 0.00;
            double price2 = 0.00;
            double price3 = 0.00;
            double price4 = 0.00;
            double price5 = 0.00;
            String promotion1 = "";
            String promotion2 = "";
            String promotion3 = "";
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from effective where eff_date <= '" + SqlDateFmt.format(new Date()) + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() > 0) {
                    do {
                        eff_date = SqlDateFmt.format(rec.getDate("eff_date"));
                        pcode = rec.getString("pcode");
                        price1 = rec.getDouble("price1");
                        price2 = rec.getDouble("price2");
                        price3 = rec.getDouble("price3");
                        price4 = rec.getDouble("price4");
                        price5 = rec.getDouble("price5");
                        promotion1 = rec.getString("promotion1");
                        promotion2 = rec.getString("promotion2");
                        promotion3 = rec.getString("promotion3");
                        UpdateEffProduct(eff_date, pcode, price1, price2, price3, price4, price5, promotion1, promotion2, promotion3);
                        DeleteEffProduct(eff_date, pcode);
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                PUtility.ShowError(e.getMessage());
                processStop = true;
            }
        }

        public void UpdateEffProduct(String eff_date, String pcode, double price1, double price2, double price3, double price4, double price5, String promotion1, String promotion2, String promotion3) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update product set pprice11=?,pprice12=?,pprice13=?,pprice14=?,pprice15=?,ppromotion1=?,ppromotion2=?,ppromotion3=? "
                        + " where pcode=? ";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setDouble(1, price1);
                prm.setDouble(2, price2);
                prm.setDouble(3, price3);
                prm.setDouble(4, price4);
                prm.setDouble(5, price5);
                prm.setString(6, promotion1);
                prm.setString(7, promotion2);
                prm.setString(8, promotion3);
                prm.setString(9, pcode);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }
        }

        public void DeleteEffProduct(String eff_date, String pcode) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from effective where eff_date= '" + eff_date + "' and pcode = '" + pcode + "'";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                PUtility.ShowError(e.getMessage());
            }
        }

        public void Restoretable() {
            TextArea.append("***** กำลังเรียกคืนข้อมูล กรุณารอสักครู่ *****\n");
            String filename = "";
            String tempfile = "";
            String tblname = "";
            File f;
            tblname = "creditfile";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "cupon";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "cuponlist";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "protab";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "groupfile";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "bankfile";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "vender";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "product";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "pset";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "branfile";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "posconfigsetup";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "usergroup";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "posuser";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "effective";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            tblname = "memmaster";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "mpointtype";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "smstable";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "custfile";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "accr";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "giftprice";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "giftstatus";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "empmaster";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "branch";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }
            tblname = "chargemaster";
            filename = pathBackup + tblname + ".txt";
            tempfile = curPath + tblname + ".txt";
            f = new File(filename);
            if (f.exists()) {
                copyFile(filename, curPath + new File(filename).getName());
                execSqlUpdate("delete from " + tblname);
                execSqlUpdate("load data infile '" + tempfile + "' into table " + tblname + " charset utf8");
            }

            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update branch set pt1=?,pt2=?,pt3=?,pt4=?,pt5=?";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, PT1);
                prm.setString(2, PT2);
                prm.setString(3, PT3);
                prm.setString(4, PT4);
                prm.setString(5, PT5);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }

        public void Backuptable() {
            TextArea.append("***  กำลังสำรองข้อมูล กรุณารอสักครู่ ***\n");
            String filename = "";
            File f;
            //Creditfile
            filename = curPath + "creditfile" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From creditfile ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //Cupon
            filename = curPath + "cupon" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From cupon ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //Cuponlist ;
            filename = curPath + "cuponlist" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From cuponlist ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //protab
            filename = curPath + "protab" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From protab ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //groupfile
            filename = curPath + "groupfile" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From groupfile ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //bankfile
            filename = curPath + "bankfile" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From bankfile ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //vender
            filename = curPath + "vender" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From vender ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //product
            filename = curPath + "product" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From product ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //pset
            filename = curPath + "pset" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From pset ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //branfile
            filename = curPath + "branfile" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From branfile ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //posconfigsetup
            filename = curPath + "posconfigsetup" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From posconfigsetup ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //usergroup
            filename = curPath + "usergroup" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From usergroup ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //posuser
            filename = curPath + "posuser" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From posuser ");
            copyFile(filename, pathBackup + new File(filename).getName());

            //effective
            filename = curPath + "effective" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From effective ");
            copyFile(filename, pathBackup + new File(filename).getName());

            //memmaster
            filename = curPath + "memmaster" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From memmaster ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //mpointtype
            filename = curPath + "mempointtype" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From mpointtype ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //smstable
            filename = curPath + "smstable" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From smstable ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //custfile
            filename = curPath + "cuustfile" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From custfile ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //accr
            filename = curPath + "accr" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From accr ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //giftprice
            filename = curPath + "giftprice" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From giftprice ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //giftstatus
            filename = curPath + "giftstatus" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From giftstatus ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //empmaster
            filename = curPath + "empmaster" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From empmaster ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //Branch
            filename = curPath + "branch" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From branch ");
            copyFile(filename, pathBackup + new File(filename).getName());
            //chargemaster
            filename = curPath + "chargemaster" + ".txt";
            f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            execSql("Select * INTO OUTFILE '" + filename + "' From chargemaster ");
            copyFile(filename, pathBackup + new File(filename).getName());
        }

        public void copyFile(String des, String target) {
            new File(target).getParentFile().mkdir();
            if (des.equals(target)) {
                System.out.println("คุณกำลังจะ copy ไฟล์เดิม");
                return;
            } else {
                File f = new File(target);
                if (f.exists()) {
                    f.delete();
                }
                try {
                    InputStream in = new FileInputStream(des);
                    OutputStream out = new FileOutputStream(target);
                    long max = 0;
                    max = new File(des).length();
                    byte[] buf = new byte[1024];
                    int len;
                    long use = 0;
                    while ((len = in.read(buf)) > 0) {
                        use += len;
                        out.write(buf, 0, len);
                        out.flush();
                    }
                    in.close();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void execSql(String sqls) {
            PreparedStatement pstmt = null;
            try {
                String sql = sqls;
                pstmt = MySQLConnect.con.prepareStatement(sql);
                pstmt.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pstmt.close();
                } catch (Exception ex) {
                }
            }
        }

        public void execSqlUpdate(String sqls) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = sqls;
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                PUtility.ShowError(e.getMessage());
                e.printStackTrace();
            }
        }

        public void UpdateOperation() {
            if (SeekOperation()) {
                UpdateOperationSale();
            } else {
                InsertOperationSale();
            }
        }

        public boolean SeekOperation() {
            boolean RetVal = false;
            Calendar CurDate = new GregorianCalendar();
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String LoadTableFile = "select *from dayoperation where operationdate = '" + SqlDateFmt.format(CurDate.getTime()) + "'";
                ResultSet rec = stmt.executeQuery(LoadTableFile);
                rec.first();
                if (rec.getRow() == 0) {
                    RetVal = false;
                } else {
                    RetVal = true;
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return RetVal;
        }

        public void InsertOperationSale() {
            Calendar CurDate = new GregorianCalendar();
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String UpdatePromotion = "insert into dayoperation (operationdate,updatemaster,updatemasteruser,updatemastertime) values (?,?,?,?) ";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(UpdatePromotion);
                prm.setString(1, SqlDateFmt.format(CurDate.getTime()));
                prm.setString(2, "Y");
                prm.setString(3, PublicVar._User);
                prm.setString(4, SqlDateTimeFmt.format(CurDate.getTime()));
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                PUtility.ShowError(e.getMessage());
            }
        }

        public void UpdateOperationSale() {
            Calendar CurDate = new GregorianCalendar();
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String UpdatePromotion = "update dayoperation set updatemaster=?,updatemasteruser=?,updatemastertime=? where operationdate=? ";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(UpdatePromotion);
                prm.setString(1, "Y");
                prm.setString(2, PublicVar._User);
                prm.setString(3, SqlDateTimeFmt.format(CurDate.getTime()));
                prm.setString(4, SqlDateFmt.format(CurDate.getTime()));
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                PUtility.ShowError(e.getMessage());
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

        public void ConverCharSet(String inFile, String inCharsetName, String outFile, String outCharsetName) {
            DirectoryUtility dirUtil = new DirectoryUtility();
            try {
                dirUtil.getFileAndCreateDir(outFile);
            } catch (Exception ex) {
                ex.printStackTrace();
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

        public int ChkTotalRec(String fileName) {
            int totalRec = 0;
            File fObject = new File(fileName);
            if (!fObject.canRead()) {
                processStop = true;
                JOptionPane.showMessageDialog(this, "Error File " + fileName);
                return totalRec;
            }
            try {
                FileReader file = new FileReader(fileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    while (!eof) {
                        String line;
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            totalRec++;
                        }
                    }
                    buff.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            return totalRec;
        }

        public void ProcessCredit() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Credit.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Credit.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Credit.Txt";
            } else {
                centFileName = PathTemp + '/' + "Credit.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            CreditFile cr = new CreditFile();
            cr.ClearCredit();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Credit : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cr.InsertCredit(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูลบัตรเครดิต             : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูลบัตรเครดิต             : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessCupon() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Cupon.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Cupon.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Cupon.Txt";
            } else {
                centFileName = PathTemp + '/' + "Cupon.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            CuponFile cu = new CuponFile();
            cu.ClearCupon();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Cupon : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertCupon(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูลบัตรคูปอง              : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูลบัตรคูปอง              : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessCuList() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "CuList.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "CuList.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "CuList.Txt";
            } else {
                centFileName = PathTemp + '/' + "CuList.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            CuponFile cu = new CuponFile();
            cu.ClearCuponList();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Cupon List : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertCuList(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูลบัตรคูปอง(รายการ PLU)  : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูลบัตรคูปอง(รายการ PLU)  : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessPromotion() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "ProTab.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "ProTab.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "ProTab.Txt";
            } else {
                centFileName = PathTemp + '/' + "ProTab.Txt";
            }
            //centFileName = PathTemp+'/'+"ProTab.Txt" ;
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            PromotionFile cu = new PromotionFile();
            cu.ClearProtab();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Promotion : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertProtab(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล Promotion             : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล Promotion             : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessGroup() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Depart.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Depart.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Depart.Txt";
            } else {
                centFileName = PathTemp + '/' + "Depart.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            GroupFile cu = new GroupFile();
            cu.ClearGroup();
            try {

                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Group : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertGroup(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล แผนกสินค้า            : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล แผนกสินค้า            : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessChargMaster() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "chargemaster.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "chargemaster.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "chargemaster.txt";
            } else {
                centFileName = PathTemp + '/' + "chargemaster.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            ChargeMaster cu = new ChargeMaster();
            cu.ClearChargeMaster();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Charge Master : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertChargeMaster(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล Charge Master       : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล Charge Master       : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessPingredent() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "pingredent.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "pingredent.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "pingredent.txt";
            } else {
                centFileName = PathTemp + '/' + "pingredent.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            Ingredent cu = new Ingredent();
            cu.ClearIngredent();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Ingredent : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertIngredent(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล Ingredent       : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล Ingredent       : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessChargeBranch() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "chargebranch.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "chargebranch.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "chargebranch.txt";
            } else {
                centFileName = PathTemp + '/' + "chargebranch.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            ChargeBranch cu = new ChargeBranch();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Charge Branch : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.UpdateChargeBranch(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล Charge Branch       : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล Charge Branch       : " + IntFmt.format(curRec) + "... Error \n");
            }
        }
        public void Processeffective() {
            String LogospFileName = PathTemp + '/' + "effective.txt";
            File fObject = new File(LogospFileName);
            if (!fObject.exists()) {
                return;
            }
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "effective.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "effective.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "effective.txt";
            } else {
                centFileName = PathTemp + '/' + "effective.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            Effective cu = new Effective();
            cu.ClearEffective();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Effective : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertEffective(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล การกำหนดราคาขายล่วงหน้า            : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล การกำหนดราคาขายล่วงหน้า            : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessBank() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Bank.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Bank.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Bank.Txt";
            } else {
                centFileName = PathTemp + '/' + "Bank.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            BankFile cu = new BankFile();
            cu.ClearBank();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Bank : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertBank(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล ธนาคาร            : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล ธนาคาร            : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessVender() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Supplier.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Supplier.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Supplier.Txt";
            } else {
                centFileName = PathTemp + '/' + "Supplier.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            VenderFile cu = new VenderFile();
            //cu.ClearVender();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Vender : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.SeekVender(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล ผู้จำหน่าย         : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล ผู้จำหน่าย         : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessProduct() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Templu.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Templu.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Templu.Txt";
            } else {
                centFileName = PathTemp + '/' + "Templu.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            ProductFile cu = new ProductFile();
            //cu.ClearVender();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Product : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            //System.out.print(StrS.length) ;
                            cu.SeekProduct(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสสินค้า        : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสสินค้า        : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessSubPlu() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "PSet.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "PSet.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "PSet.Txt";
            } else {
                centFileName = PathTemp + '/' + "PSet.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            PSetFile cu = new PSetFile();
            //cu.ClearVender();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Product Set : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.SeekPSet(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รายการชุดสินค้า   : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รายการชุดสินค้า   : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessBranch() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "Bran.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "Bran.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "Bran.Txt";
            } else {
                centFileName = PathTemp + '/' + "Bran.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            BranchFile cu = new BranchFile();
            cu.ClearBranch();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Branch : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertBranch(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสสาขา       : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสสาขา        : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessPosConfig() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "PosCon.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "PosCon.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "PosCon.Txt";
            } else {
                centFileName = PathTemp + '/' + "PosCon.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            PosConfigFile cu = new PosConfigFile();
            cu.ClearPosConfig();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Pos Config : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertPosConfig(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล ค่าเริ่มต้นระบบ     : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล ค่าเรมต้นระบบ     : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessUserGroup() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "UGroup.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "UGroup.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "UGroup.Txt";
            } else {
                centFileName = PathTemp + '/' + "UGroup.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            UGroupFile cu = new UGroupFile();
            cu.ClearUGroup();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process User Group : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertUGroup(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสกลุ่มผู้ใช้งาน     : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสกลุ่มผู้ใช้งาน     : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessPUser() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "UPos.Txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "UPos.Txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "UPos.Txt";
            } else {
                centFileName = PathTemp + '/' + "UPos.Txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            PUserFile cu = new PUserFile();
            cu.ClearPUser();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process POS User : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertPUser(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสผู้ใช้งาน     : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสผู้ใช้งาน     : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessMember() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "mast.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "mast.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "mast.txt";
            } else {
                centFileName = PathTemp + '/' + "mast.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            MemberFile cu = new MemberFile();
            cu.ClearMember();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();

                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Member : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            if (StrS.length < 11) {
                                for (int i = 0; i < StrS.length; i++) {
                                    JOptionPane.showMessageDialog(this, IntFmt.format(i) + " " + StrS[i]);
                                }
                            }
                            cu.SeekMember(StrS);

                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสสมาชิก     : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสสมาชิก๔     : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessSMS() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "sms.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "sms.txt";
                String outCharset = "UTF-8";
                File fObject = new File(inFile);
                if (!fObject.exists()) {
                    return;
                }
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "sms.txt";
            } else {
                centFileName = PathTemp + '/' + "sms.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            SMSFile cu = new SMSFile();
            cu.ClearSMS();
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();

                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        String line = "";
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process SMS : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertSMS(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล SMS     : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล SMS     : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessMPoint() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "mpoint.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "mpoint.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "mpoint.txt";
            } else {
                centFileName = PathTemp + '/' + "mpoint.txt";
            }
            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            MPointFile cu = new MPointFile();
            cu.ClearMPoint();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();

                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process M-Point : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertMPonint(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล ตารางแต้มสมาชิก  : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล ตารางแต้มสมาชิก  : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessMBranch() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "mbran.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "mbran.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "mbran.txt";
            } else {
                centFileName = PathTemp + '/' + "mbran.txt";
            }
            int totalRec = 0;
            int curRec = 0;
            MBranchFile cu = new MBranchFile();
            cu.ClearMBranch();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();

                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            //curRec++ ;
                            ProcessStatus.setText("Process M-Branch : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            if (StrS[0].equals(Branch_Code)) {
                                curRec++;
                                totalRec++;
                                cu.UpdateMBranch(StrS);
                            }
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล การคำนวณแต้มสาขา  : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล การคำนวณแต้มสาขา  : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessCust() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "CustFile.TXT";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "CustFile.TXT";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "CustFile.TXT";
            } else {
                centFileName = PathTemp + '/' + "CustFile.TXT";
            }

            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            CustFile cu = new CustFile();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Customer File : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.SeekCust(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสลูกหนี้    : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสลูกหนี้    : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessAR() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "ArFile.TXT";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "ArFile.TXT";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "ArFile.TXT";
            } else {
                centFileName = PathTemp + '/' + "ArFile.TXT";
            }
            int totalRec = 0;
            int curRec = 0;
            ArFile cu = new ArFile();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            //curRec++ ;
                            ProcessStatus.setText("Process AR File : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            if (StrS[0].equals(Branch_Code)) {
                                curRec++;
                                totalRec++;
                                cu.UpdateAr(StrS);
                            }
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล การตัดลูกหนี้    : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล การตัดลูกหนี้    : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessGiftPrice() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "giftprice.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "giftprice.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "giftprice.txt";
            } else {
                centFileName = PathTemp + '/' + "giftprice.txt";
            }

            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            GiftPriceFile cu = new GiftPriceFile();
            cu.ClearGiftPrice();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process GiftPrice : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertGiftPrice(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล ตารางราคาบัตรของขวัญ  : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล ตารางราคาบัตรของขวัญ  : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessGiftStatus() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "giftstatus.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "giftstatus.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "giftstatus.txt";
            } else {
                centFileName = PathTemp + '/' + "giftstatus.txt";
            }

            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            GiftStatusFile cu = new GiftStatusFile();
            cu.ClearGiftStatus();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();
                try {
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Gift Status : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertGiftStatus(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล สถานะบัตรของขวัญ  : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล สถานะบัตรของขวัญ  : " + IntFmt.format(curRec) + "... Error \n");
            }
        }

        public void ProcessEmploy() {
            String centFileName;
            if (!FileCharset.equals("UTF-8")) {
                String inFile = PathTemp + '/' + "employ.txt";
                String inCharset = FileCharset;
                String outFile = PathConvert + '/' + "employ.txt";
                String outCharset = "UTF-8";
                ConverCharSet(inFile, inCharset, outFile, outCharset);
                centFileName = PathConvert + '/' + "employ.txt";
            } else {
                centFileName = PathTemp + '/' + "employ.txt";
            }

            int totalRec = ChkTotalRec(centFileName);
            int curRec = 0;
            GiftStatusFile cu = new GiftStatusFile();
            cu.ClearEmploy();
            String line;
            try {
                FileReader file = new FileReader(centFileName);
                BufferedReader buff = new BufferedReader(file);
                boolean eof = false;
                Runtime r = Runtime.getRuntime();
                try {
                    ProgressBar.setValue(0);
                    while (!eof) {
                        line = buff.readLine();
                        if (line == null) {
                            eof = true;
                        } else {
                            curRec++;
                            ProcessStatus.setText("Process Employ Code : " + IntFmt.format(curRec) + "/" + IntFmt.format(totalRec));
                            if (totalRec != 0) {
                                ProgressBar.setValue((int) ((curRec * 100) / totalRec));
                            } else {
                                ProgressBar.setValue(0);
                            }
                            String StrS[] = line.split("\u0009", line.length());
                            cu.InsertEmploy(StrS);
                        }
                    }
                    buff.close();
                    TextArea.append("ข้อมูล รหัสพนักงาน  : " + IntFmt.format(curRec) + "... OK \n");
                } catch (IOException ex) {
                    processStop = true;
                }
            } catch (FileNotFoundException ex) {
                processStop = true;
            }
            if (processStop) {
                TextArea.append("ข้อมูล รหัสพนักงาน  : " + IntFmt.format(curRec) + "... Error \n");
            }
        }
    } //end SubClass

    class CreditFile {

        public void ClearCredit() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from creditfile ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertCredit(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into creditfile (crbank,crcode,crname,crcharge,crgetcardno,crredule) "
                            + "values (?,?,?,?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[1]);
                    prm.setString(3, StrS[2]);
                    double TempAmt = Double.parseDouble(StrS[3]);
                    prm.setDouble(4, TempAmt);
                    prm.setString(5, StrS[4]);
                    double TempAmt2 = Double.parseDouble(StrS[5]);
                    prm.setDouble(6, TempAmt2);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class CuponFile {

        public void ClearCupon() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from cupon ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void ClearCuponList() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from cuponlist ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertCupon(String StrS[]) {
            double TempAmt = 0.0;
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into cupon (cucode,cuname,cubegin,cuend,cutype,custrday,cudisc,cudiscbath,"
                            + "chkmember,cubarcode,redulediscount,reduleservice,cuminsale,custrtype,fixbranch) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[1]);
                    prm.setString(2, StrS[2]);
                    prm.setString(3, StrS[3]);
                    prm.setString(4, StrS[4]);
                    prm.setString(5, StrS[5]);
                    prm.setString(6, StrS[6]);
                    TempAmt = Double.parseDouble(StrS[7]);
                    prm.setDouble(7, TempAmt);
                    TempAmt = Double.parseDouble(StrS[8]);
                    prm.setDouble(8, TempAmt);
                    prm.setString(9, StrS[9]);
                    prm.setString(10, StrS[10]);
                    prm.setString(11, StrS[11]);
                    prm.setString(12, StrS[12]);
                    TempAmt = Double.parseDouble(StrS[13]);
                    prm.setDouble(13, TempAmt);
                    prm.setString(14, StrS[14]);
                    prm.setString(15, StrS[15]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }

        public void InsertCuList(String StrS[]) {
            if (!StrS[0].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into cuponlist (cucode,pcode) "
                            + "values ('" + StrS[0] + "','" + StrS[1] + "')";
                    //PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    //prm.setString(1, StrS[0]);
                    //prm.setString(2, StrS[1]);
                    //prm.executeUpdate();
                    stmt.execute(SqlQuery);
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class PromotionFile {

        public void ClearProtab() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from protab ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertProtab(String StrS[]) {
            if (!StrS[1].equals("")) {
                double TempAmt = 0.0;
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into protab (procode,prodesc,pstrday,pdate1,pdate2,ptime1s,ptime1e,"
                            + "pdisc1,pspdisc1,pts1,ptime2s,ptime2e,pdisc2,pspdisc2,pts2,ptime3s,ptime3e,pdisc3,pspdisc3,pts3,"
                            + "ptype,psale1,pfree1,psum1,pdiscfree1,psale41,pfree41,psale2,pfree2,psum2,pdiscfree2,psale42,pfree42,"
                            + "psale3,pfree3,psum3,pdiscfree3,psale43,pfree43,redulediscount,fixbranch) values (?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[1]);
                    prm.setString(2, StrS[2]);
                    prm.setString(3, StrS[3]);
                    prm.setString(4, StrS[4]);
                    prm.setString(5, StrS[5]);
                    prm.setString(6, StrS[6]);
                    prm.setString(7, StrS[7]);

                    TempAmt = Double.parseDouble(StrS[8]);
                    prm.setDouble(8, TempAmt);
                    TempAmt = Double.parseDouble(StrS[9]);
                    prm.setDouble(9, TempAmt);

                    prm.setString(10, StrS[10]);

                    prm.setString(11, StrS[11]);
                    prm.setString(12, StrS[12]);
                    TempAmt = Double.parseDouble(StrS[13]);
                    prm.setDouble(13, TempAmt);
                    TempAmt = Double.parseDouble(StrS[14]);
                    prm.setDouble(14, TempAmt);

                    prm.setString(15, StrS[15]);

                    prm.setString(16, StrS[16]);
                    prm.setString(17, StrS[17]);
                    TempAmt = Double.parseDouble(StrS[18]);
                    prm.setDouble(18, TempAmt);
                    TempAmt = Double.parseDouble(StrS[19]);
                    prm.setDouble(19, TempAmt);
                    prm.setString(20, StrS[20]);

                    prm.setString(21, StrS[21]);

                    TempAmt = Double.parseDouble(StrS[22]);
                    prm.setDouble(22, TempAmt);
                    TempAmt = Double.parseDouble(StrS[23]);
                    prm.setDouble(23, TempAmt);
                    TempAmt = Double.parseDouble(StrS[24]);
                    prm.setDouble(24, TempAmt);
                    TempAmt = Double.parseDouble(StrS[25]);
                    prm.setDouble(25, TempAmt);
                    TempAmt = Double.parseDouble(StrS[26]);
                    prm.setDouble(26, TempAmt);
                    TempAmt = Double.parseDouble(StrS[27]);
                    prm.setDouble(27, TempAmt);
                    TempAmt = Double.parseDouble(StrS[28]);
                    prm.setDouble(28, TempAmt);
                    TempAmt = Double.parseDouble(StrS[29]);
                    prm.setDouble(29, TempAmt);
                    TempAmt = Double.parseDouble(StrS[30]);
                    prm.setDouble(30, TempAmt);
                    TempAmt = Double.parseDouble(StrS[31]);
                    prm.setDouble(31, TempAmt);
                    TempAmt = Double.parseDouble(StrS[32]);
                    prm.setDouble(32, TempAmt);
                    TempAmt = Double.parseDouble(StrS[33]);
                    prm.setDouble(33, TempAmt);

                    TempAmt = Double.parseDouble(StrS[34]);
                    prm.setDouble(34, TempAmt);
                    TempAmt = Double.parseDouble(StrS[35]);
                    prm.setDouble(35, TempAmt);
                    TempAmt = Double.parseDouble(StrS[36]);
                    prm.setDouble(36, TempAmt);
                    TempAmt = Double.parseDouble(StrS[37]);
                    prm.setDouble(37, TempAmt);
                    TempAmt = Double.parseDouble(StrS[38]);
                    prm.setDouble(38, TempAmt);
                    TempAmt = Double.parseDouble(StrS[39]);
                    prm.setDouble(39, TempAmt);
                    prm.setString(40, StrS[40]);
                    prm.setString(41, StrS[41]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class GroupFile {

        public void ClearGroup() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from groupfile ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertGroup(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into groupfile (groupcode,groupname) "
                            + "values (?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[1]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    processStop = true;
                }
            }
        }
    } //end class CreditFile
    class ChargeMaster {

        public void ClearChargeMaster() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from chargemaster ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertChargeMaster(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into chargemaster (chargecode,chargename,chargegroup,chargebegindate," +
                            "chargeexpdate,chargeamount,chargestatus,lastupdatefromhr) "
                            + "values (?,?,?,?,?,?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[1]);
                    prm.setString(3, StrS[2]);
                    prm.setString(4, StrS[3]);
                    prm.setString(5, StrS[4]);
                    prm.setString(6, StrS[5]);
                    prm.setString(7, StrS[6]);
                    prm.setString(8, StrS[7]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class Ingredent {

        public void ClearIngredent() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from pingedent ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertIngredent(String StrS[]) {
            double TempAmt = 0.0 ;
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into pingedent (pcode,pingcode,pingqty) "
                            + "values (?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[1]);
                    TempAmt = Double.parseDouble(StrS[2]);
                    prm.setDouble(3, TempAmt);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class ChargeBranch {
        public void UpdateChargeBranch(String StrS[]) {
            if (StrS[0].equals(PublicVar.Branch_Code)) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "update branch set usercharge=?,chargestart=?,chargeexp=? " +
                            "where code=?" ;
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[1]);
                    prm.setString(2, StrS[2]);
                    prm.setString(3, StrS[3]);
                    prm.setString(4, StrS[0]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    processStop = true;
                }
            }
        }
    } //end class CreditFile
    class Effective {

        public void ClearEffective() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from effective ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertEffective(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into effective (eff_date,pcode,price1,price2,price3,price4,price5,promotion1,promotion2,promotion3) "
                            + "values (?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[1]);
                    prm.setString(3, StrS[2]);
                    prm.setString(4, StrS[3]);
                    prm.setString(5, StrS[4]);
                    prm.setString(6, StrS[5]);
                    prm.setString(7, StrS[6]);
                    prm.setString(8, StrS[7]);
                    prm.setString(9, StrS[8]);
                    prm.setString(10, StrS[9]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class BankFile {

        public void ClearBank() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from bankfile ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertBank(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into bankfile (bcode,bname) "
                            + "values (?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[1]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class VenderFile {

        public void ClearVender() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from vender ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void SeekVender(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from vender where vcode='" + StrS[0] + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    InsertVender(StrS);
                } else {
                    UpdateVender(StrS);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertVender(String StrS[]) {
            if (!StrS[0].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into vender (vcode,vname,vaddress,vprovince,vcontack,vtel,vfax,vremark,vcrterm,vcramount) "
                            + "values (?,?,?,?,?,?,?,?,?,?) ";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setString(2, StrS[2]);
                    prm.setString(3, StrS[3]);
                    prm.setString(4, StrS[4]);
                    prm.setString(5, StrS[5]);
                    prm.setString(6, StrS[6]);
                    prm.setString(7, StrS[7]);
                    prm.setString(8, "");
                    prm.setDouble(9, 0);
                    prm.setDouble(10, 0);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }

        public void UpdateVender(String StrS[]) {
            if (!StrS[0].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "update vender set vname=?,vaddress=?,vprovince=?,vcontack=?,vtel=?,"
                            + "vfax=?,vremark=?,vcrterm=?,vcramount=? where vcode=? ";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[2]);
                    prm.setString(2, StrS[3]);
                    prm.setString(3, StrS[4]);
                    prm.setString(4, StrS[5]);
                    prm.setString(5, StrS[6]);
                    prm.setString(6, StrS[7]);
                    prm.setString(7, "");
                    prm.setDouble(8, 0);
                    prm.setDouble(9, 0);
                    prm.setString(10, StrS[0]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class ProductFile {

        public void ClearProduct() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from product ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void SeekProduct(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from product where pcode='" + StrS[1] + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    InsertProduct(StrS);
                } else {
                    UpdateProduct(StrS);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertProduct(String StrS[]) {
            if (StrS.length==37) {
                if (!StrS[1].equals("")) {
                    try {
                        Statement stmt = (Statement) MySQLConnect.con.createStatement();
                        String SqlQuery = "insert into product (pcode,pbarcode,pdesc,preferent,punit1,parea,"
                                + "pnormal,pdiscount,pvat,ptype,pset,pstatus,pstock,pkic,pservice,pgroup,pvender,"
                                + "pprice11,pprice12,pprice13,pprice14,pprice15,ppromotion1,ppromotion2,ppromotion3,"
                                + "pmax,pmin,pscost,pacost,plcost,premark,pactive,pfoodtype) "
                                + "values (?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?)";
                        PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                        prm.setString(1, StrS[1]);
                        prm.setString(2, StrS[2]);
                        prm.setString(3, FilterNameToMYSQL.trueName(StrS[3]));
                        prm.setString(4, StrS[4]);
                        prm.setString(5, StrS[5]);
                        prm.setString(6, StrS[6]);
                        prm.setString(7, StrS[7]);
                        prm.setString(8, StrS[8]);
                        prm.setString(9, StrS[9]);
                        prm.setString(10, StrS[10]);
                        prm.setString(11, StrS[11]);
                        prm.setString(12, StrS[12]);
                        prm.setString(13, StrS[13]);
                        prm.setString(14, StrS[14]);
                        prm.setString(15, StrS[15]);
                        prm.setString(16, StrS[16]);
                        prm.setString(17, StrS[17]);

                        prm.setDouble(18, Double.parseDouble(StrS[18]));
                        prm.setDouble(19, Double.parseDouble(StrS[19]));
                        prm.setDouble(20, Double.parseDouble(StrS[20]));
                        prm.setDouble(21, Double.parseDouble(StrS[21]));
                        prm.setDouble(22, Double.parseDouble(StrS[22]));
                        prm.setString(23, StrS[23]);
                        prm.setString(24, StrS[24]);
                        prm.setString(25, StrS[25]);

                        prm.setDouble(26, Double.parseDouble(StrS[26]));
                        prm.setDouble(27, Double.parseDouble(StrS[27]));
                        prm.setDouble(28, Double.parseDouble(StrS[28]));
                        prm.setDouble(29, Double.parseDouble(StrS[29]));
                        prm.setDouble(30, Double.parseDouble(StrS[30]));
                        prm.setString(31, StrS[31]);
                        prm.setString(32, StrS[33]);
                        prm.setString(33, StrS[34]);
                        prm.executeUpdate();
                        stmt.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                        processStop = true;
                    }
                }
            } else {   //For Charge Procree
                if (!StrS[1].equals("")) {
                    try {
                        Statement stmt = (Statement) MySQLConnect.con.createStatement();
                        String SqlQuery = "insert into product (pcode,pbarcode,pdesc,preferent,punit1,parea,"
                                + "pnormal,pdiscount,pvat,ptype,pset,pstatus,pstock,pkic,pservice,pgroup,pvender,"
                                + "pprice11,pprice12,pprice13,pprice14,pprice15,ppromotion1,ppromotion2,ppromotion3,"
                                + "pmax,pmin,pscost,pacost,plcost,premark,pactive,pfoodtype,charge1,charge2,psetselect,psetselectqty) "
                                + "values (?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?)";
                        PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                        prm.setString(1, StrS[1]);
                        prm.setString(2, StrS[2]);
                        prm.setString(3, FilterNameToMYSQL.trueName(StrS[3]));
                        prm.setString(4, StrS[4]);
                        prm.setString(5, StrS[5]);
                        prm.setString(6, StrS[6]);
                        prm.setString(7, StrS[7]);
                        prm.setString(8, StrS[8]);
                        prm.setString(9, StrS[9]);
                        prm.setString(10, StrS[10]);
                        prm.setString(11, StrS[11]);
                        prm.setString(12, StrS[12]);
                        prm.setString(13, StrS[13]);
                        prm.setString(14, StrS[14]);
                        prm.setString(15, StrS[15]);
                        prm.setString(16, StrS[16]);
                        prm.setString(17, StrS[17]);

                        prm.setDouble(18, Double.parseDouble(StrS[18]));
                        prm.setDouble(19, Double.parseDouble(StrS[19]));
                        prm.setDouble(20, Double.parseDouble(StrS[20]));
                        prm.setDouble(21, Double.parseDouble(StrS[21]));
                        prm.setDouble(22, Double.parseDouble(StrS[22]));
                        prm.setString(23, StrS[23]);
                        prm.setString(24, StrS[24]);
                        prm.setString(25, StrS[25]);

                        prm.setDouble(26, Double.parseDouble(StrS[26]));
                        prm.setDouble(27, Double.parseDouble(StrS[27]));
                        prm.setDouble(28, Double.parseDouble(StrS[28]));
                        prm.setDouble(29, Double.parseDouble(StrS[29]));
                        prm.setDouble(30, Double.parseDouble(StrS[30]));
                        prm.setString(31, StrS[31]);
                        prm.setString(32, StrS[33]);
                        prm.setString(33, StrS[34]);
                        prm.setString(34, StrS[36]);
                        prm.setString(35, StrS[37]);
                        prm.setString(36, StrS[38]);
                        prm.setInt(37, Integer.parseInt(StrS[39]));
                        prm.executeUpdate();
                        stmt.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                        processStop = true;
                    }
                }

            }
        }

        public void UpdateProduct(String StrS[]) {
            if (StrS.length==37) {
                if (!StrS[1].equals("")) {
                    try {
                        Statement stmt = (Statement) MySQLConnect.con.createStatement();
                        String SqlQuery = "update product set pbarcode=?,pdesc=?,preferent=?,punit1=?,parea=?,"
                                + "pnormal=?,pdiscount=?,pvat=?,ptype=?,pset=?,pstatus=?,pstock=?,pkic=?,pservice=?,pgroup=?,pvender=?,"
                                + "pprice11=?,pprice12=?,pprice13=?,pprice14=?,pprice15=?,ppromotion1=?,ppromotion2=?,ppromotion3=?,"
                                + "pmax=?,pmin=?,pscost=?,pacost=?,plcost=?,premark=?,pactive=?,pfoodtype=? where pcode=? ";
                        PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);

                        prm.setString(1, StrS[2]);
                        prm.setString(2, FilterNameToMYSQL.trueName(StrS[3]));
                        prm.setString(3, StrS[4]);
                        prm.setString(4, StrS[5]);
                        prm.setString(5, StrS[6]);
                        prm.setString(6, StrS[7]);
                        prm.setString(7, StrS[8]);
                        prm.setString(8, StrS[9]);
                        prm.setString(9, StrS[10]);
                        prm.setString(10, StrS[11]);
                        prm.setString(11, StrS[12]);
                        prm.setString(12, StrS[13]);
                        prm.setString(13, StrS[14]);
                        prm.setString(14, StrS[15]);
                        prm.setString(15, StrS[16]);
                        prm.setString(16, StrS[17]);

                        prm.setDouble(17, Double.parseDouble(StrS[18]));
                        prm.setDouble(18, Double.parseDouble(StrS[19]));
                        prm.setDouble(19, Double.parseDouble(StrS[20]));
                        prm.setDouble(20, Double.parseDouble(StrS[21]));
                        prm.setDouble(21, Double.parseDouble(StrS[22]));
                        prm.setString(22, StrS[23]);
                        prm.setString(23, StrS[24]);
                        prm.setString(24, StrS[25]);

                        prm.setDouble(25, Double.parseDouble(StrS[26]));
                        prm.setDouble(26, Double.parseDouble(StrS[27]));
                        prm.setDouble(27, Double.parseDouble(StrS[28]));
                        prm.setDouble(28, Double.parseDouble(StrS[29]));
                        prm.setDouble(29, Double.parseDouble(StrS[30]));
                        prm.setString(30, StrS[31]);
                        prm.setString(31, StrS[33]);
                        prm.setString(32, StrS[34]);

                        prm.setString(33, StrS[1]);
                        prm.executeUpdate();
                        stmt.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                        processStop = true;
                    }
                }///////
            } else {  //For Charge Update
                if (!StrS[1].equals("")) {
                    try {
                        Statement stmt = (Statement) MySQLConnect.con.createStatement();
                        String SqlQuery = "update product set pbarcode=?,pdesc=?,preferent=?,punit1=?,parea=?,"
                                + "pnormal=?,pdiscount=?,pvat=?,ptype=?,pset=?,pstatus=?,pstock=?,pkic=?,pservice=?,pgroup=?,pvender=?,"
                                + "pprice11=?,pprice12=?,pprice13=?,pprice14=?,pprice15=?,ppromotion1=?,ppromotion2=?,ppromotion3=?,"
                                + "pmax=?,pmin=?,pscost=?,pacost=?,plcost=?,premark=?,pactive=?,pfoodtype=?,charge1=?,charge2=?,psetselect=?,psetselectqty=? where pcode=? ";
                        PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);

                        prm.setString(1, StrS[2]);
                        prm.setString(2, FilterNameToMYSQL.trueName(StrS[3]));
                        prm.setString(3, StrS[4]);
                        prm.setString(4, StrS[5]);
                        prm.setString(5, StrS[6]);
                        prm.setString(6, StrS[7]);
                        prm.setString(7, StrS[8]);
                        prm.setString(8, StrS[9]);
                        prm.setString(9, StrS[10]);
                        prm.setString(10, StrS[11]);
                        prm.setString(11, StrS[12]);
                        prm.setString(12, StrS[13]);
                        prm.setString(13, StrS[14]);
                        prm.setString(14, StrS[15]);
                        prm.setString(15, StrS[16]);
                        prm.setString(16, StrS[17]);

                        prm.setDouble(17, Double.parseDouble(StrS[18]));
                        prm.setDouble(18, Double.parseDouble(StrS[19]));
                        prm.setDouble(19, Double.parseDouble(StrS[20]));
                        prm.setDouble(20, Double.parseDouble(StrS[21]));
                        prm.setDouble(21, Double.parseDouble(StrS[22]));
                        prm.setString(22, StrS[23]);
                        prm.setString(23, StrS[24]);
                        prm.setString(24, StrS[25]);

                        prm.setDouble(25, Double.parseDouble(StrS[26]));
                        prm.setDouble(26, Double.parseDouble(StrS[27]));
                        prm.setDouble(27, Double.parseDouble(StrS[28]));
                        prm.setDouble(28, Double.parseDouble(StrS[29]));
                        prm.setDouble(29, Double.parseDouble(StrS[30]));
                        prm.setString(30, StrS[31]);
                        prm.setString(31, StrS[33]);
                        prm.setString(32, StrS[34]);
                        prm.setString(33, StrS[36]);
                        prm.setString(34, StrS[37]);
                        prm.setString(35, StrS[38]);
                        prm.setInt(36, Integer.parseInt(StrS[39]));

                        prm.setString(37, StrS[1]);
                        prm.executeUpdate();
                        stmt.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e);
                        processStop = true;
                    }
                }///////
            }
        }
    } //end class CreditFile

    class PSetFile {

        public void ClearPSet() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from pset ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void SeekPSet(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from pset where pcode='" + StrS[0] + "' and psubcode='" + StrS[1] + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    InsertPSet(StrS);
                } else {
                    UpdatePSet(StrS);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertPSet(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into pset (pcode,psubcode,psubqty) values (?,?,?)";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[0]);
                prm.setString(2, StrS[1]);
                prm.setInt(3, Integer.parseInt(StrS[2]));

                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }

        public void UpdatePSet(String StrS[]) {

            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update pset set psubqty=? where pcode=? and psubcode=?";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);

                prm.setString(1, StrS[2]);
                prm.setString(2, StrS[0]);
                prm.setString(3, StrS[1]);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }
        }
    } //end class CreditFile

    class BranchFile {

        public void ClearBranch() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from branfile ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertBranch(String StrS[]) {
            double TempAmt = 0.0;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into branfile (code,name,addressno,locality,subprovince,province,"
                        + "post,tel_no,fax_no,e_mail,manager,location_area,ser_area,cou_area,kic_area,"
                        + "tot_area,cost,charge,flagecost,gp,flagegp) values (?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?)";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[0]);
                prm.setString(2, StrS[1]);
                prm.setString(3, StrS[2]);
                prm.setString(4, StrS[3]);
                prm.setString(5, StrS[4]);
                prm.setString(6, StrS[5]);
                prm.setString(7, StrS[6]);
                prm.setString(8, StrS[7]);
                prm.setString(9, StrS[8]);
                prm.setString(10, StrS[9]);
                prm.setString(11, StrS[10]);

                prm.setString(12, StrS[11]);
                TempAmt = Double.parseDouble(StrS[12]);
                prm.setDouble(13, TempAmt);
                TempAmt = Double.parseDouble(StrS[13]);
                prm.setDouble(14, TempAmt);
                TempAmt = Double.parseDouble(StrS[14]);
                prm.setDouble(15, TempAmt);
                TempAmt = Double.parseDouble(StrS[15]);
                prm.setDouble(16, TempAmt);
                TempAmt = Double.parseDouble(StrS[16]);
                prm.setDouble(17, TempAmt);
                TempAmt = Double.parseDouble(StrS[17]);
                prm.setDouble(18, TempAmt);

                prm.setString(19, StrS[18]);
                TempAmt = Double.parseDouble(StrS[19]);
                prm.setDouble(20, TempAmt);
                prm.setString(21, StrS[20]);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }
    } //end class CreditFile

    class PosConfigFile {

        public void ClearPosConfig() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from posconfigsetup ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertPosConfig(String StrS[]) {
            double TempAmt = 0.0;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into posconfigsetup (p_terminal,p_vat,p_service,p_vatprn,p_vattype,p_billcopy,"
                        + "p_billcopyone,p_empuse,p_codeprn,p_checkbillbeforcash,p_printdetailonrecp,p_printrecpmessage,"
                        + "p_memdisc,p_memdiscchk,p_memdiscget,p_memdiscmax,p_fastdisc,p_fastdiscchk,p_fastdiscget,p_fastdiscmax,"
                        + "p_empdisc,p_empdiscchk,p_empdiscget,p_empdiscmax,p_traindisc,p_traindiscchk,p_traindiscget,p_traindiscmax,"
                        + "p_subdisc,p_subdiscchk,p_subdiscget,p_subdiscmax,p_discbathchk,p_discbathmax,p_discround,p_serviceround,"
                        + "p_serchkbysaletype,p_discchkbysaletype,memaddtimeamt,memaddtimescore) values (?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[0]);
                prm.setDouble(2, Double.parseDouble(StrS[1]));
                prm.setDouble(3, Double.parseDouble(StrS[2]));
                prm.setString(4, StrS[3]);
                prm.setString(5, StrS[4]);
                prm.setInt(6, Integer.parseInt(StrS[5]));
                prm.setString(7, StrS[6]);

                prm.setString(8, StrS[8]);
                prm.setString(9, StrS[9]);
                prm.setString(10, StrS[10]);
                prm.setString(11, StrS[11]);
                prm.setString(12, StrS[12]);
                prm.setString(13, StrS[13]);
                prm.setString(14, StrS[14]);
                prm.setString(15, StrS[15]);
                prm.setString(16, StrS[16]);
                prm.setString(17, StrS[17]);
                prm.setString(18, StrS[18]);
                prm.setString(19, StrS[19]);
                prm.setString(20, StrS[20]);
                prm.setString(21, StrS[21]);
                prm.setString(22, StrS[22]);
                prm.setString(23, StrS[23]);
                prm.setString(24, StrS[24]);
                prm.setString(25, StrS[25]);
                prm.setString(26, StrS[26]);
                prm.setString(27, StrS[27]);
                prm.setString(28, StrS[28]);
                prm.setString(29, StrS[29]);
                prm.setString(30, StrS[30]);
                prm.setString(31, StrS[31]);
                prm.setString(32, StrS[32]);
                prm.setString(33, StrS[33]);
                prm.setString(34, StrS[34]);


                prm.setString(35, StrS[37]);
                prm.setString(36, StrS[38]);
                prm.setString(37, StrS[39]);
                prm.setString(38, StrS[40]);

                TempAmt = Double.parseDouble(StrS[41]);
                prm.setDouble(39, TempAmt);
                TempAmt = Double.parseDouble(StrS[42]);
                prm.setDouble(40, TempAmt);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }
    } //end class CreditFile

    class UGroupFile {

        public void ClearUGroup() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from usergroup ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertUGroup(String StrS[]) {
            double TempAmt = 0.0;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into usergroup (username,password,name,onact,macno,sale1,sale2,sale3,sale4,"
                        + "sale5,sale6,sale7,sale8,sale9,sale10,sale11,sale12,sale13,sale14,sale15,sale16,sale17,sale18,"
                        + "sale19,sale20,sale21,sale22,sale23,sale24,sale25,sale26,sale27,sale28,sale29,sale30,sale31,sale32,"
                        + "sale33,sale34,sale35,sale36,cont0,cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9,cont10,"
                        + "cont11,cont12,cont13,cont14,cont15,stock0,stock1,stock2,stock3,stock4,stock5,stock6,stock7,stock8,"
                        + "stock9,stock10,stock11,stock12,stock13,stock14,stock15,stock16,stock17,stock18,stock19,stock20,"
                        + "stock21,stock22,stock23,stock24,stock25,stock26,stock27,stock28,stock29,stock30,stock31,stock32,"
                        + "stock33,stock34,stock35,stock36,stock37,stock38,stock39,stock40,stock41,stock42,stock43,stock44,stock45) "
                        + "values (?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?)";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);


                prm.setString(1, StrS[0]);
                prm.setString(2, StrS[1]);
                prm.setString(3, StrS[2]);
                prm.setString(4, StrS[3]);
                prm.setString(5, StrS[4]);
                prm.setString(6, StrS[5]);
                prm.setString(7, StrS[6]);
                prm.setString(8, StrS[7]);
                prm.setString(9, StrS[8]);
                prm.setString(10, StrS[9]);

                prm.setString(11, StrS[10]);
                prm.setString(12, StrS[11]);
                prm.setString(13, StrS[12]);
                prm.setString(14, StrS[13]);
                prm.setString(15, StrS[14]);
                prm.setString(16, StrS[15]);
                prm.setString(17, StrS[16]);
                prm.setString(18, StrS[17]);
                prm.setString(19, StrS[18]);
                prm.setString(20, StrS[19]);

                prm.setString(21, StrS[20]);
                prm.setString(22, StrS[21]);
                prm.setString(23, StrS[22]);
                prm.setString(24, StrS[23]);
                prm.setString(25, StrS[24]);
                prm.setString(26, StrS[25]);
                prm.setString(27, StrS[26]);
                prm.setString(28, StrS[27]);
                prm.setString(29, StrS[28]);
                prm.setString(30, StrS[29]);

                prm.setString(31, StrS[30]);
                prm.setString(32, StrS[31]);
                prm.setString(33, StrS[32]);
                prm.setString(34, StrS[33]);
                prm.setString(35, StrS[34]);
                prm.setString(36, StrS[35]);
                prm.setString(37, StrS[36]);
                prm.setString(38, StrS[37]);
                prm.setString(39, StrS[38]);
                prm.setString(40, StrS[39]);

                prm.setString(41, StrS[40]);
                prm.setString(42, StrS[41]);
                prm.setString(43, StrS[42]);
                prm.setString(44, StrS[43]);
                prm.setString(45, StrS[44]);
                prm.setString(46, StrS[45]);
                prm.setString(47, StrS[46]);
                prm.setString(48, StrS[47]);
                prm.setString(49, StrS[48]);
                prm.setString(50, StrS[49]);

                prm.setString(51, StrS[50]);
                prm.setString(52, StrS[51]);
                prm.setString(53, StrS[52]);
                prm.setString(54, StrS[53]);
                prm.setString(55, StrS[54]);
                prm.setString(56, StrS[55]);
                prm.setString(57, StrS[56]);
                prm.setString(58, StrS[57]);
                prm.setString(59, StrS[58]);
                prm.setString(60, StrS[59]);

                prm.setString(61, StrS[60]);
                prm.setString(62, StrS[61]);
                prm.setString(63, StrS[62]);
                prm.setString(64, StrS[63]);
                prm.setString(65, StrS[64]);
                prm.setString(66, StrS[65]);
                prm.setString(67, StrS[66]);
                prm.setString(68, StrS[67]);
                prm.setString(69, StrS[68]);
                prm.setString(70, StrS[69]);

                prm.setString(71, StrS[70]);
                prm.setString(72, StrS[71]);
                prm.setString(73, StrS[72]);
                prm.setString(74, StrS[73]);
                prm.setString(75, StrS[74]);
                prm.setString(76, StrS[75]);
                prm.setString(77, StrS[76]);
                prm.setString(78, StrS[77]);
                prm.setString(79, StrS[78]);
                prm.setString(80, StrS[79]);

                prm.setString(81, StrS[80]);
                prm.setString(82, StrS[81]);
                prm.setString(83, StrS[82]);
                prm.setString(84, StrS[83]);
                prm.setString(85, StrS[84]);
                prm.setString(86, StrS[85]);
                prm.setString(87, StrS[86]);
                prm.setString(88, StrS[87]);
                prm.setString(89, StrS[88]);
                prm.setString(90, StrS[89]);

                prm.setString(91, StrS[90]);
                prm.setString(92, StrS[91]);
                prm.setString(93, StrS[92]);
                prm.setString(94, StrS[93]);
                prm.setString(95, StrS[94]);
                prm.setString(96, StrS[95]);
                prm.setString(97, StrS[96]);
                prm.setString(98, StrS[97]);
                prm.setString(99, StrS[98]);
                prm.setString(100, StrS[99]);

                prm.setString(101, StrS[100]);
                prm.setString(102, StrS[101]);
                prm.setString(103, StrS[102]);

                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }
    } //end class CreditFile 

    class PUserFile {

        public void ClearPUser() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from posuser ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertPUser(String StrS[]) {
            int Chtext = StrS[104].indexOf("00:00:00");
            String Chdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            if (Chtext == -1) {
                Chdate = StrS[104];
            }
            System.out.println(Chdate);
            double TempAmt = 0.0;
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into posuser (username,password,usergroup,name,onact,macno,sale1,sale2,sale3,sale4,"
                        + "sale5,sale6,sale7,sale8,sale9,sale10,sale11,sale12,sale13,sale14,sale15,sale16,sale17,sale18,"
                        + "sale19,sale20,sale21,sale22,sale23,sale24,sale25,sale26,sale27,sale28,sale29,sale30,sale31,sale32,"
                        + "sale33,sale34,sale35,sale36,cont0,cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9,cont10,"
                        + "cont11,cont12,cont13,cont14,cont15,stock0,stock1,stock2,stock3,stock4,stock5,stock6,stock7,stock8,"
                        + "stock9,stock10,stock11,stock12,stock13,stock14,stock15,stock16,stock17,stock18,stock19,stock20,"
                        + "stock21,stock22,stock23,stock24,stock25,stock26,stock27,stock28,stock29,stock30,stock31,stock32,"
                        + "stock33,stock34,stock35,stock36,stock37,stock38,stock39,stock40,stock41,stock42,stock43,stock44,stock45,"
                        + "lastchangepassword,branchchange) "
                        + "values ('" + StrS[0] + "','"
                        + StrS[1] + "','"
                        + StrS[2] + "','"
                        + StrS[3] + "','"
                        + StrS[4] + "','"
                        + StrS[5] + "','"
                        + StrS[6] + "','"
                        + StrS[7] + "','"
                        + StrS[8] + "','"
                        + StrS[9] + "','"
                        + StrS[10] + "','"
                        + StrS[11] + "','"
                        + StrS[12] + "','"
                        + StrS[13] + "','"
                        + StrS[14] + "','"
                        + StrS[15] + "','"
                        + StrS[16] + "','"
                        + StrS[17] + "','"
                        + StrS[18] + "','"
                        + StrS[19] + "','"
                        + StrS[20] + "','"
                        + StrS[21] + "','"
                        + StrS[22] + "','"
                        + StrS[23] + "','"
                        + StrS[24] + "','"
                        + StrS[25] + "','"
                        + StrS[26] + "','"
                        + StrS[27] + "','"
                        + StrS[28] + "','"
                        + StrS[29] + "','"
                        + StrS[30] + "','"
                        + StrS[31] + "','"
                        + StrS[32] + "','"
                        + StrS[33] + "','"
                        + StrS[34] + "','"
                        + StrS[35] + "','"
                        + StrS[36] + "','"
                        + StrS[37] + "','"
                        + StrS[38] + "','"
                        + StrS[39] + "','"
                        + StrS[40] + "','"
                        + StrS[41] + "','"
                        + StrS[42] + "','"
                        + StrS[43] + "','"
                        + StrS[44] + "','"
                        + StrS[45] + "','"
                        + StrS[46] + "','"
                        + StrS[47] + "','"
                        + StrS[48] + "','"
                        + StrS[49] + "','"
                        + StrS[50] + "','"
                        + StrS[51] + "','"
                        + StrS[52] + "','"
                        + StrS[53] + "','"
                        + StrS[54] + "','"
                        + StrS[55] + "','"
                        + StrS[56] + "','"
                        + StrS[57] + "','"
                        + StrS[58] + "','"
                        + StrS[59] + "','"
                        + StrS[60] + "','"
                        + StrS[61] + "','"
                        + StrS[62] + "','"
                        + StrS[63] + "','"
                        + StrS[64] + "','"
                        + StrS[65] + "','"
                        + StrS[66] + "','"
                        + StrS[67] + "','"
                        + StrS[68] + "','"
                        + StrS[69] + "','"
                        + StrS[70] + "','"
                        + StrS[71] + "','"
                        + StrS[72] + "','"
                        + StrS[73] + "','"
                        + StrS[74] + "','"
                        + StrS[75] + "','"
                        + StrS[76] + "','"
                        + StrS[77] + "','"
                        + StrS[78] + "','"
                        + StrS[79] + "','"
                        + StrS[80] + "','"
                        + StrS[81] + "','"
                        + StrS[82] + "','"
                        + StrS[83] + "','"
                        + StrS[84] + "','"
                        + StrS[85] + "','"
                        + StrS[86] + "','"
                        + StrS[87] + "','"
                        + StrS[88] + "','"
                        + StrS[89] + "','"
                        + StrS[90] + "','"
                        + StrS[91] + "','"
                        + StrS[92] + "','"
                        + StrS[93] + "','"
                        + StrS[94] + "','"
                        + StrS[95] + "','"
                        + StrS[96] + "','"
                        + StrS[97] + "','"
                        + StrS[98] + "','"
                        + StrS[99] + "','"
                        + StrS[100] + "','"
                        + StrS[101] + "','"
                        + StrS[102] + "','"
                        + StrS[103] + "','"
                        + Chdate + "','"
                        + StrS[105] + "')";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }
    } //end class CreditFile 

    class MemberFile {

        public void ClearMember() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from memmaster ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void SeekMember(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from memmaster where m_code='" + StrS[0] + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    InsertMember(StrS);
                } else {
                    UpdateMember(StrS);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertMember(String StrS[]) {

            if (!StrS[0].equals("")) {
                double TempAmt = 0.0;
                String M_Tel = "";
                String M_Mobile = "";
                String M_Office = "";
                if (StrS.length == 13) {
                    M_Tel = StrS[11] ;
                }
                if (StrS.length == 14) {
                    M_Tel = StrS[11] ;
                    M_Mobile = StrS[12] ;
                }
                if (StrS.length == 15) {
                    M_Tel = StrS[11] ;
                    M_Mobile = StrS[12] ;
                    M_Office = StrS[13] ;
                }
                if(M_Tel.length()>25){
                    M_Tel = M_Tel.substring(25);
                }
                if(M_Mobile.length()>25){
                    M_Mobile = M_Mobile.substring(25);
                }
                if(M_Office.length()>25){
                    M_Office = M_Office.substring(25);
                }

                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into memmaster (m_code,m_name,m_type,m_begin,m_end,m_brid,"
                            + "m_active,m_bran,m_score,m_lsev,m_sum,m_tel,m_mobile,m_office,m_barcode) "
                            + "values ('" + StrS[0] + "','" + FilterNameToMYSQL.trueName(StrS[1]) + "','" + StrS[2] + "','" + StrS[3] + "','" + StrS[4] + "','" + StrS[5]
                            + "','" + StrS[6] + "','" + StrS[7] + "'," + Double.parseDouble(StrS[8]) + ",'" + StrS[9] + "'," + Double.parseDouble(StrS[10]) + ",'" + M_Tel
                            + "','" + M_Mobile + "','" + M_Office + "','" + StrS[14] + "') ";

                    stmt.executeUpdate(SqlQuery);
                    stmt.close();
                } catch (SQLException e) {
                    TextArea.append("Member Error Code : " + StrS[0] + "-" + StrS[1] + "\n" + e + "\n");
                    //JOptionPane.showMessageDialog(null, e) ;
                    //processStop = true;
                }
            }
        }

        public void UpdateMember(String StrS[]) {
            double TempAmt = 0.0;
            if (!StrS[0].equals("")) {
                String M_Tel = "";
                String M_Mobile = "";
                String M_Office = "";
                if (StrS.length == 13) {
                    M_Tel = StrS[11] ;
                }
                if (StrS.length == 14) {
                    M_Tel = StrS[11] ;
                    M_Mobile = StrS[12] ;
                }
                if (StrS.length == 15) {
                    M_Tel = StrS[11] ;
                    M_Mobile = StrS[12] ;
                    M_Office = StrS[13] ;
                }
                if(M_Tel.length()>25){
                    M_Tel = M_Tel.substring(25);
                }
                if(M_Mobile.length()>25){
                    M_Mobile = M_Mobile.substring(25);
                }
                if(M_Office.length()>25){
                    M_Office = M_Office.substring(25);
                }
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "update memmaster set m_name='" + FilterNameToMYSQL.trueName(StrS[1]) + "',"
                            + "m_type='" + StrS[2] + "',"
                            + "m_begin='" + StrS[3] + "',"
                            + "m_end='" + StrS[4] + "',"
                            + "m_brid='" + StrS[5] + "',"
                            + "m_active='" + StrS[6] + "',"
                            + "m_bran='" + StrS[7] + "',"
                            + "m_score=" + Double.parseDouble(StrS[8]) + ","
                            + "m_lsev='" + StrS[9] + "',"
                            + "m_sum=" + Double.parseDouble(StrS[10]) + ","
                            + "m_tel='" + M_Tel + "',"
                            + "m_mobile='" + M_Mobile + "',"
                            + "m_office='" + M_Office + "',"
                            + "m_barcode='" + StrS[14] + "' "
                            + " where m_code='" + StrS[0] + "'";


                    stmt.executeUpdate(SqlQuery);
                    stmt.close();
                } catch (SQLException e) {
                    TextArea.append("Member Error Code : " + StrS[0] + "-" + StrS[1] + "\n" + e + "\n");
                    //JOptionPane.showMessageDialog(null, e) ;
                    //processStop = true;
                }
            }
        }
    } //end class CreditFile

    class MPointFile {

        public void ClearMPoint() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from mpointtype ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertMPonint(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into mpointtype (ptcode,ptname,ptstrday,ptstartdate,ptenddate,"
                        + "ptstarttime1,ptendtime1,ptrate1,ptstarttime2,ptendtime2,ptrate2,"
                        + "ptstarttime3,ptendtime3,ptrate3) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[0]);
                prm.setString(2, StrS[1]);
                prm.setString(3, StrS[2]);
                prm.setString(4, StrS[3]);
                prm.setString(5, StrS[4]);
                prm.setString(6, StrS[5]);
                prm.setString(7, StrS[6]);
                prm.setDouble(8, Double.parseDouble(StrS[7]));

                prm.setString(9, StrS[8]);
                prm.setString(10, StrS[9]);
                prm.setDouble(11, Double.parseDouble(StrS[10]));

                prm.setString(12, StrS[11]);
                prm.setString(13, StrS[12]);
                prm.setDouble(14, Double.parseDouble(StrS[13]));


                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }
        }
    } //end class CreditFile

    class MBranchFile {

        public void UpdateMBranch(String StrS[]) {
            String PT1 = "";
            String PT2 = "";
            String PT3 = "";
            String PT4 = "";
            String PT5 = "";
            if (StrS.length == 2) {
                PT1 = StrS[1];
            }
            if (StrS.length == 3) {
                PT1 = StrS[1];
                PT2 = StrS[2];
            }
            if (StrS.length == 4) {
                PT1 = StrS[1];
                PT2 = StrS[2];
                PT3 = StrS[3];
            }
            if (StrS.length == 5) {
                PT1 = StrS[1];
                PT2 = StrS[2];
                PT3 = StrS[3];
                PT4 = StrS[4];
            }
            if (StrS.length == 6) {
                PT1 = StrS[1];
                PT2 = StrS[2];
                PT3 = StrS[3];
                PT4 = StrS[4];
                PT5 = StrS[5];
            }
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update branch set pt1=?,pt2=?,pt3=?,pt4=?,pt5=?";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, PT1);
                prm.setString(2, PT2);
                prm.setString(3, PT3);
                prm.setString(4, PT4);
                prm.setString(5, PT5);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }
        }

        public void ClearMBranch() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update branch set pt1=?,pt2=?,pt3=?,pt4=?,pt5=?";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, "");
                prm.setString(2, "");
                prm.setString(3, "");
                prm.setString(4, "");
                prm.setString(5, "");
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }
        }
    } //end class CreditFile

    class CustFile {

        public void ClearCust() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from custfile ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void SeekCust(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "select *from custfile where sp_code='" + StrS[0] + "'";
                ResultSet rec = stmt.executeQuery(SqlQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    InsertCust(StrS);
                } else {
                    UpdateCust(StrS);
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertCust(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into custfile (sp_code,sp_desc,sp_addr1,sp_addr2,sp_zip,contack,"
                        + "tel,fax,remark,sp_date,sp_tax,sp_cr,sp_cramt,lastdate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[0]);
                prm.setString(2, StrS[1]);
                prm.setString(3, StrS[2]);
                prm.setString(4, StrS[3]);
                prm.setString(5, StrS[4]);
                prm.setString(6, StrS[5]);
                prm.setString(7, StrS[6]);
                prm.setString(8, StrS[7]);
                prm.setString(9, StrS[8]);
                prm.setString(10, StrS[9]);
                prm.setString(11, StrS[10]);
                prm.setInt(12, Integer.parseInt(StrS[11]));
                prm.setDouble(13, Double.parseDouble(StrS[12]));
                prm.setString(14, StrS[13]);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }

        public void UpdateCust(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update custfile set sp_desc=?,sp_addr1=?,sp_addr2=?,sp_zip=?,contack=?,"
                        + "tel=?,fax=?,remark=?,sp_date=?,sp_tax=?,sp_cr=?,sp_cramt=?,lastdate=? where sp_code=?";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[1]);
                prm.setString(2, StrS[2]);
                prm.setString(3, StrS[3]);
                prm.setString(4, StrS[4]);
                prm.setString(5, StrS[5]);
                prm.setString(6, StrS[6]);
                prm.setString(7, StrS[7]);
                prm.setString(8, StrS[8]);
                prm.setString(9, StrS[9]);
                prm.setString(10, StrS[10]);
                prm.setInt(11, Integer.parseInt(StrS[11]));
                prm.setDouble(12, Double.parseDouble(StrS[12]));
                prm.setString(13, StrS[13]);
                prm.setString(14, StrS[0]);
                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }
    } //end class CreditFile

    class ArFile {

        public void UpdateAr(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "update accr set arpaytype=?,ardocbill=?,ardocpay=?,aramtpay=?,"
                        + "aramtcr=?,arbdate=?,arpdate=?,arflage=?,arbranpay=? where arno=?";
                PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                prm.setString(1, StrS[17]);
                prm.setString(2, StrS[18]);
                prm.setString(3, StrS[20]);
                prm.setDouble(4, Double.parseDouble(StrS[24]));
                prm.setDouble(5, Double.parseDouble(StrS[25]));
                prm.setString(6, StrS[26]);
                prm.setString(7, StrS[27]);
                prm.setString(8, StrS[29]);
                prm.setString(9, StrS[19]);
                prm.setString(10, StrS[1]);

                prm.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                processStop = true;
            }

        }
    } //end class CreditFile

    class GiftPriceFile {

        public void ClearGiftPrice() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from giftprice ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertGiftPrice(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into giftprice (pricecode,priceamt) "
                            + "values (?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[0]);
                    prm.setDouble(2, Double.parseDouble(StrS[1]));
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class GiftStatusFile {

        public void ClearGiftStatus() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from giftstatus ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void ClearEmploy() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from empmaster ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertGiftStatus(String StrS[]) {
            if (!StrS[1].equals("")) {
                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into giftstatus (gcode,gno,gstatus) "
                            + "values (?,?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, StrS[1]);
                    prm.setString(2, StrS[2]);
                    prm.setString(3, StrS[3]);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }

        public void InsertEmploy(String StrS[]) {
            if (!StrS[0].equals("")) {
                String empCode = "";
                String empName = "";
                if (StrS[0].length() > 13) {
                    empCode = StrS[0].substring(0, 13);
                } else {
                    empCode = StrS[0];
                }
                if (StrS[1].length() > 13) {
                    empName = StrS[1].substring(0, 40);
                } else {
                    empName = StrS[1];
                }

                try {
                    Statement stmt = (Statement) MySQLConnect.con.createStatement();
                    String SqlQuery = "insert into empmaster (emp_code,emp_name) "
                            + "values (?,?)";
                    PreparedStatement prm = (PreparedStatement) MySQLConnect.con.prepareStatement(SqlQuery);
                    prm.setString(1, empCode);
                    prm.setString(2, empName);
                    prm.executeUpdate();
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                    processStop = true;
                }
            }
        }
    } //end class CreditFile

    class SMSFile {

        public void ClearSMS() {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "delete from smstable ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                processStop = true;
            }
        }

        public void InsertSMS(String StrS[]) {
            try {
                Statement stmt = (Statement) MySQLConnect.con.createStatement();
                String SqlQuery = "insert into smstable (m_code,sms_code,branch_code,cu_code,exp_date,use_status,active_status) "
                        + "values ('" + StrS[0] + "','" + StrS[1] + "','" + StrS[2] + "','" + StrS[3] + "','" + StrS[4] + "','" + StrS[5] + "','" + StrS[6] + "') ";
                stmt.executeUpdate(SqlQuery);
                stmt.close();
            } catch (SQLException e) {
                //TextArea.append("SMS Error Code : "+StrS[0]+"-"+StrS[1] +"\n"+e+"\n") ;
                //JOptionPane.showMessageDialog(null, e) ;
                //processStop = true;
            }
        }
    }
} //end 



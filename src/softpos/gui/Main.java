package softpos.gui;

import backuprestoredb.backUp_Db;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import utilities.MySQLConnect;

public final class Main extends javax.swing.JFrame {
    UserRecord Inv_UserRec = new UserRecord();
    SimpleDateFormat Datefmtshow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat DateTimeFmt = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat SqlDateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DateFmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat TimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    SimpleDateFormat FileDateFmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH) ;
    SimpleDateFormat FullTimeFmt = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    SimpleDateFormat ShortTimeFmt = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    public Main() {
        LoadLoginForm();
        initComponents();
        String TempStr = Inv_UserRec.UserCode + " " + Inv_UserRec.UserName + "  (" + Inv_UserRec.LoginTime + ")";
        txtUser.setText(TempStr);
    }

    public void LoadLoginForm() {
        this.setVisible(false);
        LoginDialog frm = new LoginDialog(this, true);
        frm.setVisible(true);
        this.setVisible(true);
        String User = frm.getLoinUser();
        Inv_UserRec.GetUserAction(User);
    }

    public void ShowUserError() {
        JOptionPane.showMessageDialog(this, "พนักงานรหัสนี้ ไม่สามารถเข้าทำงานรายการนี้ได้...");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItem27 = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JSeparator();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem48 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ระบบควบคุมเครื่อง POS (POS Control )");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(230, 221, 221));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        jPanel1.setForeground(new java.awt.Color(98, 18, 18));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(245, 247, 247));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(192, 7, 18));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ระบบควบคุมเครื่อง POS (POS Control)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("ผู้ใช้งาน (User)");

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("Copyright @2014 Softpos Co,.LTD All Right Reserved");

        jLabel6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel6.setText("Register To : Softpos Co,LTD. (02-XXXXXXX-X)");

        txtUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtUser.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 470, 190));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(228, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.PALETTE_LAYER);

        menuBar.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N

        fileMenu.setText("แฟ้มข้อมูล (File Setup)");
        fileMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        openMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        openMenuItem.setText("กำหนดรายละเอียดสาขา (Branch)");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saveMenuItem.setText("กำหนดข้อมูลเริ่มต้นระบบ (POS-Control Setup)");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saveAsMenuItem.setText("กำหนดรายละเอียดเครื่อง Cashier (POS Setup)");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        exitMenuItem.setText("กำหนดรายการบัตรเครดิต (Credit Card Setup)");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem1.setText("กำหนดข้อมูลตารางโปรโมชั่น (Promotion Setup)");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem2.setText("กำหนดบัตรข้อมูลบัตรคูปองพิเศษ (Special Coupon)");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem3.setText("กำหนดรายการ Option (สำหรับกลุ่มสินค้า)");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem3);
        fileMenu.add(jSeparator11);

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem4.setText("กำหนดรหัสบริกร (พนักงานรับ Order)");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem4);

        jMenuItem20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem20.setText("กำหนดโซน  (Table Zone)");
        fileMenu.add(jMenuItem20);

        jMenuItem21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem21.setText("กำหนดเบอร์โต๊ะ (Table Setting)");
        fileMenu.add(jMenuItem21);
        fileMenu.add(jSeparator3);

        jMenuItem5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem5.setText("กำหนดรหัสเลขเมนู");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem5);
        fileMenu.add(jSeparator1);

        jMenuItem6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem6.setText("กำหนดรหัสกลุ่มผู้ใช้งาน (User Group Setup)");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem7.setText("กำหนดสิทธิการใช้งานของ User (User Setup)");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem7);

        jMenuItem22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem22.setText("ยกเลิกการทำงาน Cashier (Logout User)");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem22);
        fileMenu.add(jSeparator2);

        jMenuItem23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem23.setText("แฟ้มข้อมูลรหัสสินค้า (PLU Setup)");
        fileMenu.add(jMenuItem23);

        jMenuItem24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem24.setText("แฟ้มข้อมูลวัตถุดิบ (Ingredient Setup)");
        fileMenu.add(jMenuItem24);

        jMenuItem25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem25.setText("แฟ้มข้อมูลกลุ่มสินค้า");
        fileMenu.add(jMenuItem25);

        jMenuItem26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem26.setText("แฟ้มข้อมูลผู้ขาย");
        fileMenu.add(jMenuItem26);
        fileMenu.add(jSeparator12);

        jMenuItem27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem27.setText("แฟ้มข้อมูลบริษัท (Company Setup)");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem27);

        menuBar.add(fileMenu);

        editMenu.setText("ปิดยอดการขายประจำวัน/รายงานการขายประจำวัน");
        editMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        cutMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cutMenuItem.setText("ปิดยอดการขายประจำวัน (End Of Day)");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);
        editMenu.add(jSeparator9);

        jMenu3.setText("ตรวจสอบยอดการขายระหว่างวัน");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem28.setText("รายงานตรวจสอบยอดการขายระหว่างวัน (Current Sale Report)");
        jMenu3.add(jMenuItem28);
        jMenu3.add(jSeparator13);

        jMenuItem29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem29.setText("รายงานยอดการขายแยกตามเครื่อง (Terminal Report)");
        jMenu3.add(jMenuItem29);

        jMenuItem30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem30.setText("รายงานยอดขายแยกตามพนักงานขาย (Cashier Report)");
        jMenu3.add(jMenuItem30);

        jMenuItem31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem31.setText("รายงานยอดขายตามรหัสสินค้า (PLU Report)");
        jMenu3.add(jMenuItem31);

        jMenuItem32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem32.setText("รายงานการขายตามช่วงเวลา (Hourly Report)");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem32);

        editMenu.add(jMenu3);

        menuBar.add(editMenu);

        jMenu4.setText("รายงานการขาย/วิเคราะห์การขาย(Sale/Analyst Report)");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem8.setText("รายงานการขายแยกตามเครื่อง (Terminal Report)");
        jMenu4.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem9.setText("รายงานการขายแยกตามพนักงานขาย (Cashier Report)");
        jMenu4.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem10.setText("รายงานการขายแยกตามรหัสสินค้า (Sale By PLU Report)");
        jMenu4.add(jMenuItem10);

        jMenuItem14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem14.setText("รายงานสรุปการขายตามกลุ่มสินค้า (Sale By Group/Deportment Report)");
        jMenu4.add(jMenuItem14);

        jMenuItem15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem15.setText("รายงานสรุปการขายตามผู้ขาย (Sale By Vender Report)");
        jMenu4.add(jMenuItem15);
        jMenu4.add(jSeparator4);

        jMenuItem19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem19.setText("รายงานการขายตามช่วงเวลา (Hourly Report)");
        jMenu4.add(jMenuItem19);

        jMenuItem33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem33.setText("รายงานการออกใบกำกับภาษีอย่างย่อ (Receipt Report)");
        jMenu4.add(jMenuItem33);

        jMenuItem34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem34.setText("รายงานการรับชำระด้วยบัตรเครดิต (Credit Card Report)");
        jMenu4.add(jMenuItem34);

        jMenuItem35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem35.setText("รายงานการ Void/ การคืนสินค้า (Void Report)");
        jMenu4.add(jMenuItem35);

        jMenuItem36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem36.setText("รายงานการยกเลิกการขาย (Refund Report)");
        jMenu4.add(jMenuItem36);
        jMenu4.add(jSeparator14);

        jMenuItem37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem37.setText("รายงานอันดับสินค้าขายดี (Top Sale Report)");
        jMenu4.add(jMenuItem37);

        jMenuItem38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem38.setText("รายงานการให้ส่วนลดบัตรคูปองพิเศษ (Special Coupon Discount)");
        jMenu4.add(jMenuItem38);

        jMenuItem39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem39.setText("รายงานการให้ส่วนลดโปรโมชัน (Promotion Sale Report)");
        jMenu4.add(jMenuItem39);

        jMenuItem40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem40.setText("รายงานการขายสินค้าแยกตามพนักงานขาย (Cashier/PLU Report)");
        jMenu4.add(jMenuItem40);

        jMenuItem41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem41.setText("รายงานรายละเอียดการขายตามช่วงเวลา (Hourly/PLU Report)");
        jMenu4.add(jMenuItem41);

        jMenuItem42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem42.setText("รายงานการขายแยกตามพนักงานเดินบิล (Take Order/PLU Report)");
        jMenu4.add(jMenuItem42);

        jMenuItem43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem43.setText("รายงานรายละเอียดรายรับประจำวัน (Dialy Sale Report)");
        jMenu4.add(jMenuItem43);

        jMenuItem44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem44.setText("รายงานรายละเอียดการขายแยกตามวันที่ (Sale Detail Report)");
        jMenu4.add(jMenuItem44);

        jMenuItem45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem45.setText("รายงานการขายแยกตามโซน (Zone/Table Sale Report)");
        jMenu4.add(jMenuItem45);

        jMenuItem46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem46.setText("รายงานการใช้วัตถุดิบจากการขาย (Ingredient Report)");
        jMenu4.add(jMenuItem46);

        jMenuItem47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem47.setText("รายงานการใช้วัตถุดิบแยกตามรหัสสินค้า");
        jMenu4.add(jMenuItem47);
        jMenu4.add(jSeparator15);

        jMenuItem48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem48.setText("รายงานภาษีขาย (Vat Report)");
        jMenu4.add(jMenuItem48);

        menuBar.add(jMenu4);

        jMenu1.setText("รับ-ส่งข้อมูลไปสำนักงานใหญ่");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem12.setText("ส่งข้อมูลไปสำนักงานใหญ่ (Send Data to Center)");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);
        jMenu1.add(jSeparator6);

        jMenuItem11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem11.setText("บันทึกรับข้อมูลจากสำนักงานใหญ่ (Recive Data From Center)");
        jMenu1.add(jMenuItem11);
        jMenu1.add(jSeparator7);

        jMenuItem17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem17.setText("ระบบสำรองข้อมูล (Backup Data)/ระบบเรียกคืนข้อมูล (Restore Data)");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem17);
        jMenu1.add(jSeparator5);

        jMenuItem16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem16.setText("ประมวลผลสิ้นปี");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem16);

        menuBar.add(jMenu1);

        helpMenu.setText("ออกจากระบบ");
        helpMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem18.setText("ออกจากระบบ (Exit)");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem18);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
    System.exit(0);
}//GEN-LAST:event_jMenuItem18ActionPerformed

private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
    if (Inv_UserRec.Cont1.equals("Y")) {
        BranchSetup frm = new BranchSetup(this, true);
        frm.setVisible(true);
    } else {
        ShowUserError();
    }

}//GEN-LAST:event_openMenuItemActionPerformed

private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
    if (Inv_UserRec.Cont2.equals("Y")) {
        POSConfigSetup frm = new POSConfigSetup(this, true);
        frm.setVisible(true);
    } else {
        ShowUserError();
    }

}//GEN-LAST:event_saveMenuItemActionPerformed

private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
    if (Inv_UserRec.Cont3.equals("Y")) {
        POSHardwareSetup frm = new POSHardwareSetup(this, true);
        frm.setVisible(true);
    } else {
        ShowUserError();
    }
}//GEN-LAST:event_saveAsMenuItemActionPerformed

private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
    if (Inv_UserRec.Cont9.equals("Y")) {
        Date SaleDate = new Date() ;
        boolean ProcessOK = false ;
        try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String SqlQuery = "select *from billno order by b_ondate";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                PUtility.ShowMsg("ไม่พบข้อมูลการขายในแฟ้มข้อมูล...กรุณาทำการขายก่อนทำการปิดยอดการขายประจำวัน (End Of Day)  !!!");
                ProcessOK = false ;
            } else {
                SaleDate = rec.getDate("B_OnDate") ;
                ProcessOK = true ;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        if (ProcessOK) {
            PublicVar.SaleDate = SaleDate ;
            Endofday frm = new Endofday(this, true);
            frm.setVisible(true);
        }
    } else {
        ShowUserError();
    }
}//GEN-LAST:event_cutMenuItemActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
// TODO add your handling code here:
    jdi_option_Group jdi = new jdi_option_Group(new Frame(), true);
    jdi.setVisible(true);
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
// TODO add your handling code here:
    jdi_EmpSetting dialog = new jdi_EmpSetting(new javax.swing.JFrame(), true);
    dialog.setVisible(true);
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
// TODO add your handling code here:
    jdi_menuSetup dialog = new jdi_menuSetup(new javax.swing.JFrame(), true);
    dialog.setVisible(true);
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
// TODO add your handling code here:
    if (Inv_UserRec.Cont5.equals("Y")) {
        jdi_Special_Promotion_Setup jdi = new jdi_Special_Promotion_Setup(this, true);
        jdi.setVisible(true);
    } else {
        ShowUserError();
    }
    
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
// TODO add your handling code here:
    if (Inv_UserRec.Cont6.equals("Y")) {
        jdi_Special_Cupon_Setup dialog = new jdi_Special_Cupon_Setup(this, true);
        dialog.setVisible(true);
    } else {
        ShowUserError();
    }
    
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
// TODO add your handling code here:
    
}//GEN-LAST:event_jMenuItem16ActionPerformed

private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
    backUp_Db jdi = new backUp_Db(new Frame(), true);
    jdi.setVisible(true);
}//GEN-LAST:event_jMenuItem17ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
    if (Inv_UserRec.Cont7.equals("Y")) {
        jdi_UserGroupSetup dialog = new jdi_UserGroupSetup(new javax.swing.JFrame(), true);
    dialog.setVisible(true);
    } else {
        ShowUserError();
    }
    
}//GEN-LAST:event_jMenuItem6ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
   if (Inv_UserRec.Cont7.equals("Y")) {
       jdi_UserSetup dialog = new jdi_UserSetup(new javax.swing.JFrame(), true);
    dialog.setVisible(true);
    } else {
        ShowUserError();
    }
    
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
    UpdateDataFromCenter frm = new UpdateDataFromCenter(this, true);
    frm.setVisible(true);
}//GEN-LAST:event_jMenuItem12ActionPerformed
private boolean CheckAutoSum(Date SaleDate) {
    boolean RetVal = false ;
     try {
            Statement stmt = (Statement) MySQLConnect.con.createStatement();
            String SqlQuery = "select *from dayoperation where operationdate='"+SqlDateFmt.format(SaleDate)+"'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
               RetVal = false ;
            } else {
                if (rec.getString("autosum").equals("Y")){
                    RetVal = true ;
                } else {
                    RetVal = false ;
                }
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    return RetVal ;
}

private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
    // TODO add your handling code here:
    if (Inv_UserRec.Cont4.equals("Y")) {
        jdi_CreditCardSetup jdi = new jdi_CreditCardSetup(this, true);
        jdi.setVisible(true);
    } else {
        ShowUserError();
    }
}//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        RepSaleByTime frm = new RepSaleByTime(this, true);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem27ActionPerformed
private void TestPrint() {
        ViewReport view = new ViewReport();
        view.printCompile();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}

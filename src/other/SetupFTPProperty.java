package other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import write_to_text.TextWriter;

public class SetupFTPProperty extends javax.swing.JFrame {

    public SetupFTPProperty() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
        initComponents();
        setLocationRelativeTo(this);
        
        refresh();
        txtUserReceiveS4.setVisible(false);
        txtPassReceiveS4.setVisible(false);
        txtUserReceiveM4.setVisible(false);
        txtPassReceiveM4.setVisible(false);
        loadDefaultRadio();
    }
    void loadDefaultRadio(){
        String file1 = "C:/spapplication/dbconfig/ftp_local.ini";
        String file2 = "C:/spapplication/dbconfig/ftp_main.ini";
        String file3 = "C:/spapplication/dbconfig/ftp_secondary.ini";
        Properties pp = new Properties();
        try {
            //config1-----------------------------------------------
            FileInputStream input = new FileInputStream(file1);
            pp.load(input);
            if(pp.getProperty("default").equalsIgnoreCase("true")){
                rb1.setSelected(true);
            }
            input.close();
            //config2-----------------------------------------------
            input = new FileInputStream(file2);
            pp.load(input);
            if(pp.getProperty("default").equalsIgnoreCase("true")){
                rb2.setSelected(true);
            }
            input.close();
            //config3-----------------------------------------------
            input = new FileInputStream(file3);
            pp.load(input);
            if(pp.getProperty("default").equalsIgnoreCase("true")){
                rb3.setSelected(true);
            }
            input.close();
        } catch (Exception ex) {
            rb1.setSelected(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        cmdExitSecondary = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelLocalServer = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        txtSaleL1 = new javax.swing.JTextField();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        txtEndL1 = new javax.swing.JTextField();
        txtBORL1 = new javax.swing.JTextField();
        txtEJL1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        txtReceiveL1 = new javax.swing.JTextField();
        jLabel152 = new javax.swing.JLabel();
        txtBORReceiveL1 = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        txtReceiveL2 = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        txtBORReceiveL2 = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        txtReceiveL3 = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        txtBORReceiveL3 = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        txtReceiveL4 = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        panelMainServer = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtHostM1 = new javax.swing.JTextField();
        txtUserSendM1 = new javax.swing.JTextField();
        txtUserReceiveM1 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtPassSendM1 = new javax.swing.JTextField();
        txtPassReceiveM1 = new javax.swing.JTextField();
        txtPortM1 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtBranM1 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtHostM2 = new javax.swing.JTextField();
        txtUserSendM2 = new javax.swing.JTextField();
        txtUserReceiveM2 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtPassSendM2 = new javax.swing.JTextField();
        txtPassReceiveM2 = new javax.swing.JTextField();
        txtPortM2 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel156 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtSaleM1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtEndM1 = new javax.swing.JTextField();
        txtBORM1 = new javax.swing.JTextField();
        txtEJM1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtReceiveM1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtBORReceiveM1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtReceiveM2 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtBORReceiveM2 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        txtHostM3 = new javax.swing.JTextField();
        txtUserSendM3 = new javax.swing.JTextField();
        txtUserReceiveM3 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txtPassSendM3 = new javax.swing.JTextField();
        txtPassReceiveM3 = new javax.swing.JTextField();
        txtPortM3 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txtReceiveM3 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txtBORReceiveM3 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        txtHostM4 = new javax.swing.JTextField();
        txtUserSendM4 = new javax.swing.JTextField();
        txtUserReceiveM4 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        txtPassSendM4 = new javax.swing.JTextField();
        txtPassReceiveM4 = new javax.swing.JTextField();
        txtPortM4 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel175 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtReceiveM4 = new javax.swing.JTextField();
        rb2 = new javax.swing.JRadioButton();
        panelSecondaryServer = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        txtHostS1 = new javax.swing.JTextField();
        txtUserSendS1 = new javax.swing.JTextField();
        txtUserReceiveS1 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txtPassSendS1 = new javax.swing.JTextField();
        txtPassReceiveS1 = new javax.swing.JTextField();
        txtPortS1 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        txtBranS1 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtHostS2 = new javax.swing.JTextField();
        txtUserSendS2 = new javax.swing.JTextField();
        txtUserReceiveS2 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        txtPassSendS2 = new javax.swing.JTextField();
        txtPassReceiveS2 = new javax.swing.JTextField();
        txtPortS2 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtSaleS1 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtEndS1 = new javax.swing.JTextField();
        txtBORS1 = new javax.swing.JTextField();
        txtEJS1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel160 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtReceiveS1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtBORReceiveS1 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        txtReceiveS2 = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        txtBORReceiveS2 = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        txtHostS3 = new javax.swing.JTextField();
        txtUserSendS3 = new javax.swing.JTextField();
        txtUserReceiveS3 = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        txtPassSendS3 = new javax.swing.JTextField();
        txtPassReceiveS3 = new javax.swing.JTextField();
        txtPortS3 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        txtReceiveS3 = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        txtBORReceiveS3 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        txtHostS4 = new javax.swing.JTextField();
        txtUserSendS4 = new javax.swing.JTextField();
        txtUserReceiveS4 = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        txtPassSendS4 = new javax.swing.JTextField();
        txtPassReceiveS4 = new javax.swing.JTextField();
        txtPortS4 = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel176 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        txtReceiveS4 = new javax.swing.JTextField();
        rb3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DataTranfer Setup Property");
        setAlwaysOnTop(true);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cmdExitSecondary.setFont(new java.awt.Font("Norasi", 1, 14));
        cmdExitSecondary.setText("ออก (Exit)");
        cmdExitSecondary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitSecondaryActionPerformed(evt);
            }
        });

        cmdSave.setFont(new java.awt.Font("Norasi", 1, 14));
        cmdSave.setText("Save (บันทึก)");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Norasi", 0, 14));

        panelLocalServer.setFont(new java.awt.Font("Norasi", 0, 14));

        jPanel7.setBackground(new java.awt.Color(254, 254, 254));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(java.awt.Color.pink);

        jLabel145.setFont(new java.awt.Font("Norasi", 1, 18)); // NOI18N
        jLabel145.setText("กำหนดค่าสำหรับการส่งข้อมูลยอดขายไปยังสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel145)
        );

        jLabel146.setText("Sale Data Path");

        jLabel147.setText("End Month Stock Path");

        jLabel148.setText("BOR Server Data Path");

        jLabel149.setText("BOR Server EJ Path");

        txtBORL1.setBackground(new java.awt.Color(254, 252, 181));

        txtEJL1.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel149)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addComponent(jLabel146))
                                .addComponent(jLabel147)
                                .addComponent(jLabel148)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSaleL1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEJL1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBORL1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEndL1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(txtSaleL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel147)
                    .addComponent(txtEndL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel148)
                    .addComponent(txtBORL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel149)
                    .addComponent(txtEJL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel9.setBackground(new java.awt.Color(254, 254, 254));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel40.setBackground(java.awt.Color.pink);

        jLabel150.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel150.setText("กำหนดค่าสำหรับรับข้อมูล Master File จากสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel150)
        );

        jLabel151.setText("Receive Data Path");

        jLabel152.setText("BOR Server Data Path");

        txtBORReceiveL1.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel152)
                            .addComponent(jLabel151))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtReceiveL1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBORReceiveL1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel151))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel152)
                    .addComponent(txtBORReceiveL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel41.setBackground(new java.awt.Color(254, 254, 254));
        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel42.setBackground(java.awt.Color.pink);

        jLabel153.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel153.setText("กำหนดค่าสำหรับรับข้อมูลสมาชิกจากสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(4, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel153)
        );

        jLabel154.setText("Receive Data Path");

        jLabel155.setText("BOR Server Data Path");

        txtBORReceiveL2.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.LEADING, 0, 490, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel155)
                            .addComponent(jLabel154))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtReceiveL2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBORReceiveL2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveL2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel154))
                .addGap(0, 0, 0)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155)
                    .addComponent(txtBORReceiveL2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel44.setBackground(new java.awt.Color(254, 254, 254));
        jPanel44.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel45.setBackground(java.awt.Color.pink);

        jLabel163.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel163.setText("กำหนดค่าสำหรับรับข้อมูลบัตรกำนัลจากสำนักงานหญ่");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel163, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel163)
        );

        jLabel164.setText("Receive Data Path");

        jLabel165.setText("BOR Server Data Path");

        txtBORReceiveL3.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.Alignment.LEADING, 0, 490, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel165)
                            .addComponent(jLabel164))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtReceiveL3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBORReceiveL3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveL3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel164))
                .addGap(0, 0, 0)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel165)
                    .addComponent(txtBORReceiveL3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel47.setBackground(new java.awt.Color(254, 254, 254));
        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel48.setBackground(java.awt.Color.pink);

        jLabel173.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel173.setText("กำหนดค่าสำหรับโปรแกรมหลัก");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel173)
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel173)
        );

        jLabel174.setText("Receive Program Path");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel174)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReceiveL4))
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveL4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel174))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGroup1.add(rb1);
        rb1.setFont(new java.awt.Font("Norasi", 1, 18));
        rb1.setSelected(true);
        rb1.setText("กำหนดเป็นช่องทางการรับส่งข้อมูลหลัก");

        javax.swing.GroupLayout panelLocalServerLayout = new javax.swing.GroupLayout(panelLocalServer);
        panelLocalServer.setLayout(panelLocalServerLayout);
        panelLocalServerLayout.setHorizontalGroup(
            panelLocalServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLocalServerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLocalServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLocalServerLayout.createSequentialGroup()
                        .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(525, Short.MAX_VALUE))
                    .addGroup(panelLocalServerLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(472, 472, 472))
                    .addGroup(panelLocalServerLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(472, 472, 472))
                    .addGroup(panelLocalServerLayout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(472, 472, 472))
                    .addGroup(panelLocalServerLayout.createSequentialGroup()
                        .addGroup(panelLocalServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(472, Short.MAX_VALUE))))
        );
        panelLocalServerLayout.setVerticalGroup(
            panelLocalServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLocalServerLayout.createSequentialGroup()
                .addComponent(rb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ช่องทางการติดต่อ Local Server", panelLocalServer);

        panelMainServer.setFont(new java.awt.Font("Norasi", 0, 14));

        jPanel30.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel56.setText("Host Name/IP");

        jLabel57.setText("User Send");

        jLabel58.setText("User Receive");

        jLabel59.setText("Password");

        jLabel60.setText("Password");

        txtPortM1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel61.setText("Port");

        jLabel62.setText("Bran-Type");
        jLabel62.setEnabled(false);

        txtBranM1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBranM1.setEnabled(false);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserSendM1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txtUserReceiveM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassSendM1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel30Layout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassReceiveM1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtHostM1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(txtPortM1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBranM1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtHostM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtPassSendM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(txtUserSendM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserReceiveM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60)
                        .addComponent(txtPassReceiveM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel58))
                .addGap(1, 1, 1)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(txtPortM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(txtBranM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel63.setForeground(new java.awt.Color(71, 17, 0));
        jLabel63.setText("กำหนดข้อมูลสำหรับการส่งข้อมูลไป BOR และการรับข้อมูล Master File");

        jLabel64.setForeground(new java.awt.Color(71, 17, 0));
        jLabel64.setText("กำหนดข้อมูลสำหรับรับส่งข้อมูลสมาชิก (Member)");

        jPanel31.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel65.setText("Host Name/IP");

        jLabel66.setText("User Send");

        jLabel67.setText("User Receive");

        jLabel68.setText("Password");

        jLabel69.setText("Password");

        txtPortM2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel70.setText("Port");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel67)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserSendM2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtUserReceiveM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtHostM2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassReceiveM2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel68))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPortM2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassSendM2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txtHostM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPortM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addGap(1, 1, 1)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(txtPassSendM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66)
                    .addComponent(txtUserSendM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserReceiveM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel69)
                        .addComponent(txtPassReceiveM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel67))
                .addGap(12, 12, 12))
        );

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(254, 201, 149));

        jLabel156.setFont(new java.awt.Font("Norasi", 1, 18)); // NOI18N
        jLabel156.setText("กำหนดค่าสำหรับการส่งข้อมูลยอดขายไปยังสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel156)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel156)
        );

        jLabel39.setText("Sale Data Path");

        jLabel40.setText("End Month Stock Path");

        jLabel41.setText("BOR Server Data Path");

        jLabel42.setText("BOR Server EJ Path");

        txtBORM1.setBackground(new java.awt.Color(254, 252, 181));

        txtEJM1.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addComponent(jLabel39))
                                .addComponent(jLabel40)
                                .addComponent(jLabel41)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEJM1)
                            .addComponent(txtBORM1)
                            .addComponent(txtEndM1)
                            .addComponent(txtSaleM1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtSaleM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtEndM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtBORM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtEJM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(254, 254, 254));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel16.setBackground(new java.awt.Color(254, 201, 149));

        jLabel157.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel157.setText("กำหนดค่าสำหรับรับข้อมูล Master File จากสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel157)
        );

        jLabel44.setText("Receive Data Path");

        jLabel45.setText("BOR Server Data Path");

        txtBORReceiveM1.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBORReceiveM1)
                            .addComponent(txtReceiveM1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtBORReceiveM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel17.setBackground(new java.awt.Color(254, 254, 254));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel18.setBackground(new java.awt.Color(254, 201, 149));

        jLabel158.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel158.setText("กำหนดค่าสำหรับรับข้อมูลสมาชิกจากสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel158)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel158)
        );

        jLabel71.setText("Receive Data Path");

        jLabel72.setText("BOR Server Data Path");

        txtBORReceiveM2.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel71))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBORReceiveM2)
                            .addComponent(txtReceiveM2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addGap(0, 0, 0)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtBORReceiveM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel73.setForeground(new java.awt.Color(71, 17, 0));
        jLabel73.setText("กำหนดข้อมูลสำหรับรับขอ้มูลบัตรของขวัญ (Gift Voucher)");

        jPanel32.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel74.setText("Host Name/IP");

        jLabel75.setText("User Send");

        jLabel76.setText("User Receive");

        jLabel77.setText("Password");

        jLabel78.setText("Password");

        txtPortM3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel79.setText("Port");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel76)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserSendM3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtUserReceiveM3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtHostM3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassReceiveM3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel79)
                            .addComponent(jLabel77))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPortM3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassSendM3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(txtHostM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPortM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addGap(1, 1, 1)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(txtPassSendM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75)
                    .addComponent(txtUserSendM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserReceiveM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel78)
                        .addComponent(txtPassReceiveM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel76))
                .addGap(0, 0, 0))
        );

        jPanel19.setBackground(new java.awt.Color(254, 254, 254));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel20.setBackground(new java.awt.Color(254, 201, 149));

        jLabel166.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel166.setText("กำหนดค่าสำหรับรับข้อมูลบัตรกำนัลจากสำนักงานหญ่");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel166)
        );

        jLabel81.setText("Receive Data Path");

        jLabel82.setText("BOR Server Data Path");

        txtBORReceiveM3.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel82)
                            .addComponent(jLabel81))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBORReceiveM3)
                            .addComponent(txtReceiveM3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81))
                .addGap(0, 0, 0)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(txtBORReceiveM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel83.setForeground(new java.awt.Color(71, 17, 0));
        jLabel83.setText("กำหนดข้อมูลสำหรับ Update Application");

        jPanel33.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel84.setText("Host Name/IP");

        jLabel85.setText("User Name");

        jLabel87.setText("Password");

        txtPortM4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel89.setText("Port");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel84)
                    .addComponent(jLabel85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUserSendM4, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtUserReceiveM4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtHostM4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtPassReceiveM4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel89)
                            .addComponent(jLabel87))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPortM4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassSendM4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(txtHostM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPortM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addGap(1, 1, 1)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(txtPassSendM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85)
                    .addComponent(txtUserSendM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserReceiveM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassReceiveM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel21.setBackground(new java.awt.Color(254, 254, 254));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel22.setBackground(new java.awt.Color(254, 201, 149));

        jLabel175.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel175.setText("กำหนดค่าสำหรับโปรแกรมหลัก");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel175)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel175)
        );

        jLabel91.setText("Receive Program Path");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReceiveM4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(txtReceiveM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        buttonGroup1.add(rb2);
        rb2.setFont(new java.awt.Font("Norasi", 1, 18));
        rb2.setText("กำหนดเป็นช่องทางการรับส่งข้อมูลหลัก");

        javax.swing.GroupLayout panelMainServerLayout = new javax.swing.GroupLayout(panelMainServer);
        panelMainServer.setLayout(panelMainServerLayout);
        panelMainServerLayout.setHorizontalGroup(
            panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainServerLayout.createSequentialGroup()
                .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMainServerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63))
                    .addGroup(panelMainServerLayout.createSequentialGroup()
                        .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainServerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel64))
                            .addGroup(panelMainServerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel73))
                            .addGroup(panelMainServerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel83)
                                .addGap(275, 275, 275))
                            .addGroup(panelMainServerLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel19, 0, 436, Short.MAX_VALUE)
                                .addComponent(jPanel17, 0, 436, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        panelMainServerLayout.setVerticalGroup(
            panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainServerLayout.createSequentialGroup()
                .addComponent(rb2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainServerLayout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(panelMainServerLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelMainServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelMainServerLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMainServerLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ช่องทางการติดต่อหลัก(Main Server)", panelMainServer);

        panelSecondaryServer.setFont(new java.awt.Font("Norasi", 0, 14));

        jPanel34.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel93.setText("Host Name/IP");

        jLabel94.setText("User Send");

        jLabel95.setText("User Receive");

        jLabel96.setText("Password");

        jLabel97.setText("Password");

        txtPortS1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel98.setText("Port");

        jLabel99.setText("Bran-Type");
        jLabel99.setEnabled(false);

        txtBranS1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBranS1.setEnabled(false);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel95)
                    .addComponent(jLabel93)
                    .addComponent(jLabel94)
                    .addComponent(jLabel98))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserSendS1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txtUserReceiveS1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel96)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassSendS1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel97)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassReceiveS1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtHostS1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(txtPortS1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel99)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBranS1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(txtHostS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(txtPassSendS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94)
                    .addComponent(txtUserSendS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserReceiveS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel97)
                        .addComponent(txtPassReceiveS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel95))
                .addGap(1, 1, 1)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(txtPortS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99)
                    .addComponent(txtBranS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel100.setForeground(new java.awt.Color(71, 17, 0));
        jLabel100.setText("กำหนดข้อมูลสำหรับการส่งข้อมูลไป BOR และการรับข้อมูล Master File");

        jLabel101.setForeground(new java.awt.Color(71, 17, 0));
        jLabel101.setText("กำหนดข้อมูลสำหรับรับส่งข้อมูลสมาชิก (Member)");

        jPanel35.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel102.setText("Host Name/IP");

        jLabel103.setText("User Send");

        jLabel104.setText("User Receive");

        jLabel105.setText("Password");

        jLabel106.setText("Password");

        txtPortS2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel107.setText("Port");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel104)
                    .addComponent(jLabel102)
                    .addComponent(jLabel103))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserSendS2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(txtUserReceiveS2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(jLabel105)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassSendS2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(jLabel106)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassReceiveS2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(txtHostS2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPortS2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(jLabel107)
                    .addComponent(txtPortS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHostS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(txtPassSendS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103)
                    .addComponent(txtUserSendS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserReceiveS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel106)
                        .addComponent(txtPassReceiveS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel104))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(254, 254, 254));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBackground(new java.awt.Color(169, 227, 225));

        jLabel159.setFont(new java.awt.Font("Norasi", 1, 18)); // NOI18N
        jLabel159.setText("กำหนดค่าสำหรับการส่งข้อมูลยอดขายไปยังสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel159)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel159)
        );

        jLabel48.setText("Sale Data Path");

        jLabel49.setText("End Month Stock Path");

        jLabel50.setText("BOR Server Data Path");

        jLabel51.setText("BOR Server EJ Path");

        txtBORS1.setBackground(new java.awt.Color(254, 252, 181));

        txtEJS1.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel51)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addComponent(jLabel48))
                                .addComponent(jLabel49)
                                .addComponent(jLabel50)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEJS1)
                            .addComponent(txtBORS1)
                            .addComponent(txtEndS1)
                            .addComponent(txtSaleS1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtSaleS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtEndS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtBORS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtEJS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(new java.awt.Color(254, 254, 254));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel23.setBackground(new java.awt.Color(169, 227, 225));

        jLabel160.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel160.setText("กำหนดค่าสำหรับรับข้อมูล Master File จากสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel160)
        );

        jLabel53.setText("Receive Data Path");

        jLabel54.setText("BOR Server Data Path");

        txtBORReceiveS1.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBORReceiveS1)
                            .addComponent(txtReceiveS1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(txtBORReceiveS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel24.setBackground(new java.awt.Color(254, 254, 254));
        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel25.setBackground(new java.awt.Color(169, 227, 225));

        jLabel161.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel161.setText("กำหนดค่าสำหรับรับข้อมูลสมาชิกจากสำนักงานใหญ่");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel161)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel161)
        );

        jLabel108.setText("Receive Data Path");

        jLabel109.setText("BOR Server Data Path");

        txtBORReceiveS2.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel109)
                            .addComponent(jLabel108))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBORReceiveS2)
                            .addComponent(txtReceiveS2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiveS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel108))
                .addGap(0, 0, 0)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(txtBORReceiveS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel110.setForeground(new java.awt.Color(71, 17, 0));
        jLabel110.setText("กำหนดข้อมูลสำหรับรับขอ้มูลบัตรของขวัญ (Gift Voucher)");

        jPanel36.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel111.setText("Host Name/IP");

        jLabel112.setText("User Send");

        jLabel113.setText("User Receive");

        jLabel114.setText("Password");

        jLabel115.setText("Password");

        txtPortS3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel116.setText("Port");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel113)
                    .addComponent(jLabel111)
                    .addComponent(jLabel112))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserSendS3, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(txtUserReceiveS3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel114)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassSendS3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel115)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassReceiveS3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(txtHostS3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPortS3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(jLabel116)
                    .addComponent(txtPortS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHostS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(txtPassSendS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel112)
                    .addComponent(txtUserSendS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserReceiveS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel115)
                        .addComponent(txtPassReceiveS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel113))
                .addGap(0, 0, 0))
        );

        jPanel26.setBackground(new java.awt.Color(254, 254, 254));
        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel27.setBackground(new java.awt.Color(169, 227, 225));

        jLabel167.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel167.setText("กำหนดค่าสำหรับรับข้อมูลบัตรกำนัลจากสำนักงานหญ่");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel167)
        );

        jLabel118.setText("Receive Data Path");

        jLabel119.setText("BOR Server Data Path");

        txtBORReceiveS3.setBackground(new java.awt.Color(254, 252, 181));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel119)
                            .addComponent(jLabel118))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBORReceiveS3)
                            .addComponent(txtReceiveS3, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(txtReceiveS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(txtBORReceiveS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel120.setForeground(new java.awt.Color(71, 17, 0));
        jLabel120.setText("กำหนดข้อมูลสำหรับ Update Application");

        jPanel37.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel121.setText("Host Name/IP");

        jLabel122.setText("User Name");

        jLabel124.setText("Password");

        txtPortS4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel126.setText("Port");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel121)
                    .addComponent(jLabel122))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserSendS4, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(txtUserReceiveS4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addComponent(jLabel124)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassSendS4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(txtPassReceiveS4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(txtHostS4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel126)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPortS4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(jLabel126)
                    .addComponent(txtPortS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHostS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel124)
                    .addComponent(txtPassSendS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel122)
                    .addComponent(txtUserSendS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserReceiveS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassReceiveS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel28.setBackground(new java.awt.Color(254, 254, 254));
        jPanel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel29.setBackground(new java.awt.Color(169, 227, 225));

        jLabel176.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        jLabel176.setText("กำหนดค่าสำหรับโปรแกรมหลัก");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel176)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel176)
        );

        jLabel128.setText("Receive Program Path");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel128)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReceiveS4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(txtReceiveS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        buttonGroup1.add(rb3);
        rb3.setFont(new java.awt.Font("Norasi", 1, 18));
        rb3.setText("กำหนดเป็นช่องทางการรับส่งข้อมูลหลัก");

        javax.swing.GroupLayout panelSecondaryServerLayout = new javax.swing.GroupLayout(panelSecondaryServer);
        panelSecondaryServer.setLayout(panelSecondaryServerLayout);
        panelSecondaryServerLayout.setHorizontalGroup(
            panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSecondaryServerLayout.createSequentialGroup()
                .addGroup(panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSecondaryServerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel100))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSecondaryServerLayout.createSequentialGroup()
                        .addGroup(panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rb3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel101))
                            .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel110))
                            .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel120)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel24, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, 0, 450, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel28, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(137, 137, 137)))
                .addContainerGap())
        );
        panelSecondaryServerLayout.setVerticalGroup(
            panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                .addComponent(rb3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSecondaryServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addGroup(panelSecondaryServerLayout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ช่องทางการติดต่อสำรอง (Secondary Server)", panelSecondaryServer);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExitSecondary, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(636, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdExitSecondary, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1024)/2, (screenSize.height-768)/2, 1024, 768);
    }// </editor-fold>//GEN-END:initComponents

private void cmdSaveMain() {
    TextWriter tw = new TextWriter();
    String file = "C:/spapplication/dbconfig/ftp_main.ini";
    File f = new File(file);
    if(f.exists()){
        if(!f.delete()){
            JOptionPane.showMessageDialog(this, "Can not clear file : " + f);
            return;
        }
    }

    String k ;

    if(rb2.isSelected()){
        k = "default=true";
    }else{
        k = "default=false";
    }

    tw.writeToText(file, k);

    k = "config=main";
    tw.writeToText(file, k);

    //Head Host Server
    k = txtHostM1.getText().trim();
    k = "host1="+k;
    tw.writeToText(file ,k);
    k = txtUserSendM1.getText().trim();
    k = "usersend1="+k;
    tw.writeToText(file ,k);
    k = txtPassSendM1.getText().trim();
    k = "passsend1="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveM1.getText().trim();
    k = "userreceive1="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveM1.getText().trim();
    k = "passreceive1="+k;
    tw.writeToText(file ,k);
    k = txtPortM1.getText().trim();
    k = "port1="+k;
    tw.writeToText(file ,k);
    k = txtBranM1.getText().trim();
    k = "bran1="+k;
    tw.writeToText(file ,k);

    //Head Host Path
    k = txtSaleM1.getText().trim();
    k = "sale1="+k;
    tw.writeToText(file ,k);
    k = txtEndM1.getText().trim();
    k = "end1="+k;
    tw.writeToText(file ,k);
    k = txtBORM1.getText().trim();
    k = "bor1="+k;
    tw.writeToText(file ,k);
    k = txtEJM1.getText().trim();
    k = "ej1="+k;
    tw.writeToText(file ,k);
    k = txtReceiveM1.getText().trim();
    k = "receive1="+k;
    tw.writeToText(file ,k);
    k = txtBORReceiveM1.getText().trim();
    k = "borreceive1="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    //Member Server
    k = txtHostM2.getText().trim();
    k = "host2="+k;
    tw.writeToText(file ,k);
    k = txtUserSendM2.getText().trim();
    k = "usersend2="+k;
    tw.writeToText(file ,k);
    k = txtPassSendM2.getText().trim();
    k = "passsend2="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveM2.getText().trim();
    k = "userreceive2="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveM2.getText().trim();
    k = "passreceive2="+k;
    tw.writeToText(file ,k);
    k = txtPortM2.getText().trim();
    k = "port2="+k;
    tw.writeToText(file ,k);

    //Member Path
    k = txtReceiveM2.getText().trim();
    k = "receive2="+k;
    tw.writeToText(file ,k);
    k = txtBORReceiveM2.getText().trim();
    k = "borreceive2="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    //Gif Voucher Server
    k = txtHostM3.getText().trim();
    k = "host3="+k;
    tw.writeToText(file ,k);
    k = txtUserSendM3.getText().trim();
    k = "usersend3="+k;
    tw.writeToText(file ,k);
    k = txtPassSendM3.getText().trim();
    k = "passsend3="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveM3.getText().trim();
    k = "userreceive3="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveM3.getText().trim();
    k = "passreceive3="+k;
    tw.writeToText(file ,k);
    k = txtPortM3.getText().trim();
    k = "port3="+k;
    tw.writeToText(file ,k);

    //Gif Voucher Path
    k = txtReceiveM3.getText().trim();
    k = "receive3="+k;
    tw.writeToText(file ,k);
    k = txtBORReceiveM3.getText().trim();
    k = "borreceive3="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    //Product Server
    k = txtHostM4.getText().trim();
    k = "host4="+k;
    tw.writeToText(file ,k);
    k = txtUserSendM4.getText().trim();
    k = "usersend4="+k;
    tw.writeToText(file ,k);
    k = txtPassSendM4.getText().trim();
    k = "passsend4="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveM4.getText().trim();
    k = "userreceive4="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveM4.getText().trim();
    k = "passreceive4="+k;
    tw.writeToText(file ,k);
    k = txtPortM4.getText().trim();
    k = "port4="+k;
    tw.writeToText(file ,k);

    //Product Path
    k = txtReceiveM4.getText().trim();
    k = "receive4="+k;
    tw.writeToText(file ,k);
    k = "";
    k = "borreceive4="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    System.out.println("Save Complete.");
}

private void cmdSaveSecondary() {
    TextWriter tw = new TextWriter();
    String file = "C:/spapplication/dbconfig/ftp_secondary.ini";
    File f = new File(file);
    if(f.exists()){
        if(!f.delete()){
            JOptionPane.showMessageDialog(this, "Can not clear file : " + f);
            return;
        }
    }

    String k ;

    if(rb3.isSelected()){
        k = "default=true";
    }else{
        k = "default=false";
    }
    tw.writeToText(file, k);

    k = "config=secondary";
    tw.writeToText(file, k);

    //Head Host Server
    k = txtHostS1.getText().trim();
    k = "host1="+k;
    tw.writeToText(file ,k);
    k = txtUserSendS1.getText().trim();
    k = "usersend1="+k;
    tw.writeToText(file ,k);
    k = txtPassSendS1.getText().trim();
    k = "passsend1="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveS1.getText().trim();
    k = "userreceive1="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveS1.getText().trim();
    k = "passreceive1="+k;
    tw.writeToText(file ,k);
    k = txtPortS1.getText().trim();
    k = "port1="+k;
    tw.writeToText(file ,k);
    k = txtBranS1.getText().trim();
    k = "bran1="+k;
    tw.writeToText(file ,k);

    //Head Host Path
    k = txtSaleS1.getText().trim();
    k = "sale1="+k;
    tw.writeToText(file ,k);
    k = txtEndS1.getText().trim();
    k = "end1="+k;
    tw.writeToText(file ,k);
    k = txtBORS1.getText().trim();
    k = "bor1="+k;
    tw.writeToText(file ,k);
    k = txtEJS1.getText().trim();
    k = "ej1="+k;
    tw.writeToText(file ,k);
    k = txtReceiveS1.getText().trim();
    k = "receive1="+k;
    tw.writeToText(file ,k);
    k = txtBORReceiveS1.getText().trim();
    k = "borreceive1="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    //Member Server
    k = txtHostS2.getText().trim();
    k = "host2="+k;
    tw.writeToText(file ,k);
    k = txtUserSendS2.getText().trim();
    k = "usersend2="+k;
    tw.writeToText(file ,k);
    k = txtPassSendS2.getText().trim();
    k = "passsend2="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveS2.getText().trim();
    k = "userreceive2="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveS2.getText().trim();
    k = "passreceive2="+k;
    tw.writeToText(file ,k);
    k = txtPortS2.getText().trim();
    k = "port2="+k;
    tw.writeToText(file ,k);

    //Member Path
    k = txtReceiveS2.getText().trim();
    k = "receive2="+k;
    tw.writeToText(file ,k);
    k = txtBORReceiveS2.getText().trim();
    k = "borreceive2="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    //Gif Voucher Server
    k = txtHostS3.getText().trim();
    k = "host3="+k;
    tw.writeToText(file ,k);
    k = txtUserSendS3.getText().trim();
    k = "usersend3="+k;
    tw.writeToText(file ,k);
    k = txtPassSendS3.getText().trim();
    k = "passsend3="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveS3.getText().trim();
    k = "userreceive3="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveS3.getText().trim();
    k = "passreceive3="+k;
    tw.writeToText(file ,k);
    k = txtPortS3.getText().trim();
    k = "port3="+k;
    tw.writeToText(file ,k);

    //Gif Voucher Path
    k = txtReceiveS3.getText().trim();
    k = "receive3="+k;
    tw.writeToText(file ,k);
    k = txtBORReceiveS3.getText().trim();
    k = "borreceive3="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");

    //Product Server
    k = txtHostS4.getText().trim();
    k = "host4="+k;
    tw.writeToText(file ,k);
    k = txtUserSendS4.getText().trim();
    k = "usersend4="+k;
    tw.writeToText(file ,k);
    k = txtPassSendS4.getText().trim();
    k = "passsend4="+k;
    tw.writeToText(file ,k);
    k = txtUserReceiveS4.getText().trim();
    k = "userreceive4="+k;
    tw.writeToText(file ,k);
    k = txtPassReceiveS4.getText().trim();
    k = "passreceive4="+k;
    tw.writeToText(file ,k);
    k = txtPortS4.getText().trim();
    k = "port4="+k;
    tw.writeToText(file ,k);

    //Product Path
    k = txtReceiveS4.getText().trim();
    k = "receive4="+k;
    tw.writeToText(file ,k);
    k = "";
    k = "borreceive4="+k;
    tw.writeToText(file ,k);

    tw.writeToText(file, "");
}

private void cmdExitSecondaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitSecondaryActionPerformed
    dispose();
}//GEN-LAST:event_cmdExitSecondaryActionPerformed

private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
    cmdSaveLocal();
    cmdSaveMain();
    cmdSaveSecondary();
}//GEN-LAST:event_cmdSaveActionPerformed


private void cmdSaveLocal() {
    TextWriter tw = new TextWriter();
    String file = "C:/spapplication/dbconfig/ftp_local.ini";
    File f = new File(file);
    if (f.exists()) {
        if (!f.delete()) {
            JOptionPane.showMessageDialog(this, "Can not clear file : " + f);
            return;
        }
    }

    String k;
    if(rb1.isSelected()){
        k = "default=true";
    }else{
        k = "default=false";
    }

    tw.writeToText(file, k);

    k = "config=local";
    tw.writeToText(file, k);

    //Head Host Server
    k = "";
    k = "host1=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "usersend1=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passsend1=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "userreceive1=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passreceive1=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "port1=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "bran1=" + k;
    tw.writeToText(file, k);

    //Head Host Path
    k = txtSaleL1.getText().trim();
    k = "sale1=" + k;
    tw.writeToText(file, k);
    k = txtEndL1.getText().trim();
    k = "end1=" + k;
    tw.writeToText(file, k);
    k = txtBORL1.getText().trim();
    k = "bor1=" + k;
    tw.writeToText(file, k);
    k = txtEJL1.getText().trim();
    k = "ej1=" + k;
    tw.writeToText(file, k);
    k = txtReceiveL1.getText().trim();
    k = "receive1=" + k;
    tw.writeToText(file, k);
    k = txtBORReceiveL1.getText().trim();
    k = "borreceive1=" + k;
    tw.writeToText(file, k);

    tw.writeToText(file, "");

    //Member Server
    k = "";
    k = "host2=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "usersend2=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passsend2=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "userreceive2=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passreceive2=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "port2=" + k;
    tw.writeToText(file, k);

    //Member Path
    k = txtReceiveL2.getText().trim();
    k = "receive2=" + k;
    tw.writeToText(file, k);
    k = txtBORReceiveL2.getText().trim();
    k = "borreceive2=" + k;
    tw.writeToText(file, k);

    tw.writeToText(file, "");

    //Gif Voucher Server
    k = "";
    k = "host3=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "usersend3=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passsend3=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "userreceive3=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passreceive3=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "port3=" + k;
    tw.writeToText(file, k);

    //Gif Voucher Path
    k = txtReceiveL3.getText().trim();
    k = "receive3=" + k;
    tw.writeToText(file, k);
    k = txtBORReceiveL3.getText().trim();
    k = "borreceive3=" + k;
    tw.writeToText(file, k);

    tw.writeToText(file, "");

    //Product Server
    k = "";
    k = "host4=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "usersend4=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passsend4=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "userreceive4=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "passreceive4=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "port4=" + k;
    tw.writeToText(file, k);

    //Product Path
    k = txtReceiveL4.getText().trim();
    k = "receive4=" + k;
    tw.writeToText(file, k);
    k = "";
    k = "borreceive4=" + k;
    tw.writeToText(file, k);

    tw.writeToText(file, "");

    System.out.println("Save Complete.");
}

    public static void main(String args[]) {
        setup = new SetupFTPProperty();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {         
                setup.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdExitSecondary;
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelLocalServer;
    private javax.swing.JPanel panelMainServer;
    private javax.swing.JPanel panelSecondaryServer;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JTextField txtBORL1;
    private javax.swing.JTextField txtBORM1;
    private javax.swing.JTextField txtBORReceiveL1;
    private javax.swing.JTextField txtBORReceiveL2;
    private javax.swing.JTextField txtBORReceiveL3;
    private javax.swing.JTextField txtBORReceiveM1;
    private javax.swing.JTextField txtBORReceiveM2;
    private javax.swing.JTextField txtBORReceiveM3;
    private javax.swing.JTextField txtBORReceiveS1;
    private javax.swing.JTextField txtBORReceiveS2;
    private javax.swing.JTextField txtBORReceiveS3;
    private javax.swing.JTextField txtBORS1;
    private javax.swing.JTextField txtBranM1;
    private javax.swing.JTextField txtBranS1;
    private javax.swing.JTextField txtEJL1;
    private javax.swing.JTextField txtEJM1;
    private javax.swing.JTextField txtEJS1;
    private javax.swing.JTextField txtEndL1;
    private javax.swing.JTextField txtEndM1;
    private javax.swing.JTextField txtEndS1;
    private javax.swing.JTextField txtHostM1;
    private javax.swing.JTextField txtHostM2;
    private javax.swing.JTextField txtHostM3;
    private javax.swing.JTextField txtHostM4;
    private javax.swing.JTextField txtHostS1;
    private javax.swing.JTextField txtHostS2;
    private javax.swing.JTextField txtHostS3;
    private javax.swing.JTextField txtHostS4;
    private javax.swing.JTextField txtPassReceiveM1;
    private javax.swing.JTextField txtPassReceiveM2;
    private javax.swing.JTextField txtPassReceiveM3;
    private javax.swing.JTextField txtPassReceiveM4;
    private javax.swing.JTextField txtPassReceiveS1;
    private javax.swing.JTextField txtPassReceiveS2;
    private javax.swing.JTextField txtPassReceiveS3;
    private javax.swing.JTextField txtPassReceiveS4;
    private javax.swing.JTextField txtPassSendM1;
    private javax.swing.JTextField txtPassSendM2;
    private javax.swing.JTextField txtPassSendM3;
    private javax.swing.JTextField txtPassSendM4;
    private javax.swing.JTextField txtPassSendS1;
    private javax.swing.JTextField txtPassSendS2;
    private javax.swing.JTextField txtPassSendS3;
    private javax.swing.JTextField txtPassSendS4;
    private javax.swing.JTextField txtPortM1;
    private javax.swing.JTextField txtPortM2;
    private javax.swing.JTextField txtPortM3;
    private javax.swing.JTextField txtPortM4;
    private javax.swing.JTextField txtPortS1;
    private javax.swing.JTextField txtPortS2;
    private javax.swing.JTextField txtPortS3;
    private javax.swing.JTextField txtPortS4;
    private javax.swing.JTextField txtReceiveL1;
    private javax.swing.JTextField txtReceiveL2;
    private javax.swing.JTextField txtReceiveL3;
    private javax.swing.JTextField txtReceiveL4;
    private javax.swing.JTextField txtReceiveM1;
    private javax.swing.JTextField txtReceiveM2;
    private javax.swing.JTextField txtReceiveM3;
    private javax.swing.JTextField txtReceiveM4;
    private javax.swing.JTextField txtReceiveS1;
    private javax.swing.JTextField txtReceiveS2;
    private javax.swing.JTextField txtReceiveS3;
    private javax.swing.JTextField txtReceiveS4;
    private javax.swing.JTextField txtSaleL1;
    private javax.swing.JTextField txtSaleM1;
    private javax.swing.JTextField txtSaleS1;
    private javax.swing.JTextField txtUserReceiveM1;
    private javax.swing.JTextField txtUserReceiveM2;
    private javax.swing.JTextField txtUserReceiveM3;
    private javax.swing.JTextField txtUserReceiveM4;
    private javax.swing.JTextField txtUserReceiveS1;
    private javax.swing.JTextField txtUserReceiveS2;
    private javax.swing.JTextField txtUserReceiveS3;
    private javax.swing.JTextField txtUserReceiveS4;
    private javax.swing.JTextField txtUserSendM1;
    private javax.swing.JTextField txtUserSendM2;
    private javax.swing.JTextField txtUserSendM3;
    private javax.swing.JTextField txtUserSendM4;
    private javax.swing.JTextField txtUserSendS1;
    private javax.swing.JTextField txtUserSendS2;
    private javax.swing.JTextField txtUserSendS3;
    private javax.swing.JTextField txtUserSendS4;
    // End of variables declaration//GEN-END:variables

    private static SetupFTPProperty setup;
    
    public static SetupFTPProperty getSetupFTPProperty(){
        return setup;
    }
    public static void createSetup(){
        setup = new SetupFTPProperty();
    }
    
    public static void showSetupFTPProperty(){
        setup.refresh();
        setup.setVisible(true);
    }
    
    Properties main;
    Properties secondary;
    Properties local;
    
    private boolean loadLocalServer(){
        local = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_local.ini");
            local.load(input);
            input.close();             
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : "+"C:/spapplication/dbconfig/ftp_local.ini");
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : "+"C:/spapplication/dbconfig/ftp_local.ini");
            return false;
        }      
    }    
    private boolean loadMainServer(){
        main = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_main.ini");
            main.load(input);
            input.close();             
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : "+"C:/spapplication/dbconfig/ftp_main.ini");
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : "+"C:/spapplication/dbconfig/ftp_main.ini");
            return false;
        }      
    }
    private boolean loadSecondaryServer(){
        secondary = new Properties();
        try {
            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_secondary.ini");
            secondary.load(input);
            input.close();    
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Not Found : "+"C:/spapplication/dbconfig/ftp_secondary.ini");
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error with file : "+"C:/spapplication/dbconfig/ftp_secondary.ini");
            return false;
        }      
    }
    private void refresh(){
        if(loadLocalServer())
            setValuesLocalFrom();
        if(loadMainServer())
            setValuesMainFrom();
        if(loadSecondaryServer())
            setValuesSecondaryFrom();
    }
    private void setValuesLocalFrom() {
        txtBORL1.setText(local.getProperty("bor1"));
        txtBORReceiveL1.setText(local.getProperty("borreceive1"));
        txtBORReceiveL2.setText(local.getProperty("borreceive2"));
        txtBORReceiveL3.setText(local.getProperty("borreceive3"));
//        txtBORReceiveL4.setText(local.getProperty("borreceive4"));
//        txtBranL1.setText(local.getProperty("bran1"));
        txtEJL1.setText(local.getProperty("ej1"));
        txtEndL1.setText(local.getProperty("end1"));
//        txtHostL1.setText(local.getProperty("host1"));
//        txtHostL2.setText(local.getProperty("host2"));
//        txtHostL3.setText(local.getProperty("host3"));
//        txtHostL4.setText(local.getProperty("host4"));
//        txtPassReceiveL1.setText(local.getProperty("passreceive1"));
//        txtPassReceiveL2.setText(local.getProperty("passreceive2"));
//        txtPassReceiveL3.setText(local.getProperty("passreceive3"));
//        txtPassReceiveL4.setText(local.getProperty("passreceive4"));
//        txtPassSendL1.setText(local.getProperty("passsend1"));
//        txtPassSendL2.setText(local.getProperty("passsend2"));
//        txtPassSendL3.setText(local.getProperty("passsend3"));
//        txtPassSendL4.setText(local.getProperty("passsend4"));
//        txtPortL1.setText(local.getProperty("port1"));
//        txtPortL2.setText(local.getProperty("port2"));
//        txtPortL3.setText(local.getProperty("port3"));
//        txtPortL4.setText(local.getProperty("port4"));
        txtReceiveL1.setText(local.getProperty("receive1"));
        txtReceiveL2.setText(local.getProperty("receive2"));
        txtReceiveL3.setText(local.getProperty("receive3"));
        txtReceiveL4.setText(local.getProperty("receive4"));
        txtSaleL1.setText(local.getProperty("sale1"));
//        txtUserReceiveL1.setText(local.getProperty("userreceive1"));
//        txtUserReceiveL2.setText(local.getProperty("userreceive2"));
//        txtUserReceiveL3.setText(local.getProperty("userreceive3"));
//        txtUserReceiveL4.setText(local.getProperty("userreceive4"));
//        txtUserSendL1.setText(local.getProperty("usersend1"));
//        txtUserSendL2.setText(local.getProperty("usersend2"));
//        txtUserSendL3.setText(local.getProperty("usersend3"));
//        txtUserSendL4.setText(local.getProperty("usersend4"));
    }
    
    private void setValuesMainFrom() {
        txtBORM1.setText(main.getProperty("bor1"));
        txtBORReceiveM1.setText(main.getProperty("borreceive1"));
        txtBORReceiveM2.setText(main.getProperty("borreceive2"));
        txtBORReceiveM3.setText(main.getProperty("borreceive3"));
//        txtBORReceiveM4.setText(main.getProperty("borreceive4"));
        txtBranM1.setText(main.getProperty("bran1"));
        txtEJM1.setText(main.getProperty("ej1"));
        txtEndM1.setText(main.getProperty("end1"));
        txtHostM1.setText(main.getProperty("host1"));
        txtHostM2.setText(main.getProperty("host2"));
        txtHostM3.setText(main.getProperty("host3"));
        txtHostM4.setText(main.getProperty("host4"));
        txtPassReceiveM1.setText(main.getProperty("passreceive1"));
        txtPassReceiveM2.setText(main.getProperty("passreceive2"));
        txtPassReceiveM3.setText(main.getProperty("passreceive3"));
        txtPassReceiveM4.setText(main.getProperty("passreceive4"));
        txtPassSendM1.setText(main.getProperty("passsend1"));
        txtPassSendM2.setText(main.getProperty("passsend2"));
        txtPassSendM3.setText(main.getProperty("passsend3"));
        txtPassSendM4.setText(main.getProperty("passsend4"));
        txtPortM1.setText(main.getProperty("port1"));
        txtPortM2.setText(main.getProperty("port2"));
        txtPortM3.setText(main.getProperty("port3"));
        txtPortM4.setText(main.getProperty("port4"));
        txtReceiveM1.setText(main.getProperty("receive1"));
        txtReceiveM2.setText(main.getProperty("receive2"));
        txtReceiveM3.setText(main.getProperty("receive3"));
        txtReceiveM4.setText(main.getProperty("receive4"));
        txtSaleM1.setText(main.getProperty("sale1"));
        txtUserReceiveM1.setText(main.getProperty("userreceive1"));
        txtUserReceiveM2.setText(main.getProperty("userreceive2"));
        txtUserReceiveM3.setText(main.getProperty("userreceive3"));
        txtUserReceiveM4.setText(main.getProperty("userreceive4"));
        txtUserSendM1.setText(main.getProperty("usersend1"));
        txtUserSendM2.setText(main.getProperty("usersend2"));
        txtUserSendM3.setText(main.getProperty("usersend3"));
        txtUserSendM4.setText(main.getProperty("usersend4"));
    }

    private void setValuesSecondaryFrom() {
        txtBORS1.setText(secondary.getProperty("bor1"));
        txtBORReceiveS1.setText(secondary.getProperty("borreceive1"));
        txtBORReceiveS2.setText(secondary.getProperty("borreceive2"));
        txtBORReceiveS3.setText(secondary.getProperty("borreceive3"));
//        txtBORReceiveS4.setText(secondary.getProperty("borreceive4"));
        txtBranS1.setText(secondary.getProperty("bran1"));
        txtEJS1.setText(secondary.getProperty("ej1"));
        txtEndS1.setText(secondary.getProperty("end1"));
        txtHostS1.setText(secondary.getProperty("host1"));
        txtHostS2.setText(secondary.getProperty("host2"));
        txtHostS3.setText(secondary.getProperty("host3"));
        txtHostS4.setText(secondary.getProperty("host4"));
        txtPassReceiveS1.setText(secondary.getProperty("passreceive1"));
        txtPassReceiveS2.setText(secondary.getProperty("passreceive2"));
        txtPassReceiveS3.setText(secondary.getProperty("passreceive3"));
        txtPassReceiveS4.setText(secondary.getProperty("passreceive4"));
        txtPassSendS1.setText(secondary.getProperty("passsend1"));
        txtPassSendS2.setText(secondary.getProperty("passsend2"));
        txtPassSendS3.setText(secondary.getProperty("passsend3"));
        txtPassSendS4.setText(secondary.getProperty("passsend4"));
        txtPortS1.setText(secondary.getProperty("port1"));
        txtPortS2.setText(secondary.getProperty("port2"));
        txtPortS3.setText(secondary.getProperty("port3"));
        txtPortS4.setText(secondary.getProperty("port4"));
        txtReceiveS1.setText(secondary.getProperty("receive1"));
        txtReceiveS2.setText(secondary.getProperty("receive2"));
        txtReceiveS3.setText(secondary.getProperty("receive3"));
        txtReceiveS4.setText(secondary.getProperty("receive4"));
        txtSaleS1.setText(secondary.getProperty("sale1"));
        txtUserReceiveS1.setText(secondary.getProperty("userreceive1"));
        txtUserReceiveS2.setText(secondary.getProperty("userreceive2"));
        txtUserReceiveS3.setText(secondary.getProperty("userreceive3"));
        txtUserReceiveS4.setText(secondary.getProperty("userreceive4"));
        txtUserSendS1.setText(secondary.getProperty("usersend1"));
        txtUserSendS2.setText(secondary.getProperty("usersend2"));
        txtUserSendS3.setText(secondary.getProperty("usersend3"));
        txtUserSendS4.setText(secondary.getProperty("usersend4"));
    }

}

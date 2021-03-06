/*
 * restore_Db.java
 *
 * Created on July 30, 2009, 7:34 PM
 */
package backuprestoredb;

import java.awt.Cursor;
import java.io.File;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import utilities.MySQLConnect;

/**
 *
 * @author  root
 */
public class restore_Db extends javax.swing.JDialog {

    DecimalFormat doubleFmt = new DecimalFormat("##,###,##0");
    private File file = null;
    private String dbName = "";
    private String user = "";
    private String pass = "";
    private String host = "";

    public restore_Db(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //try {
        //    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        initComponents();
        name.setText("");
        size.setText("");
        set.setText("");
        txtwait.setText("");
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
        txtfileName = new javax.swing.JTextField();
        btn_browse = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        set = new javax.swing.JLabel();
        size = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtwait = new javax.swing.JLabel();
        btn_restore = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restore Backup");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtfileName.setFont(new java.awt.Font("Norasi", 0, 14));
        txtfileName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfileNameKeyReleased(evt);
            }
        });

        btn_browse.setFont(new java.awt.Font("Norasi", 0, 14));
        btn_browse.setText("Browse File");
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Norasi", 0, 14));
        jLabel1.setText("Browse File (.sql) to Restore Backup");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Norasi", 0, 14));
        jLabel2.setText("File :");

        jLabel3.setFont(new java.awt.Font("Norasi", 0, 14));
        jLabel3.setText("Character Set :");

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14));
        jLabel4.setText("Total Data Size :");

        name.setFont(new java.awt.Font("Norasi", 0, 14));
        name.setText("name");

        set.setFont(new java.awt.Font("Norasi", 0, 14));
        set.setText("set");

        size.setFont(new java.awt.Font("Norasi", 0, 14));
        size.setText("size");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(set, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(set))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(size))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14));
        jLabel5.setText("Information");

        txtwait.setFont(new java.awt.Font("Norasi", 0, 13));
        txtwait.setForeground(new java.awt.Color(223, 15, 15));
        txtwait.setText("Information");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtfileName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtwait, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtwait))
        );

        btn_restore.setFont(new java.awt.Font("Norasi", 1, 14));
        btn_restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pencil_16.png"))); // NOI18N
        btn_restore.setText("Restore Backup");
        btn_restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restoreActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(235, 233, 231));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Norasi", 1, 30));
        jLabel6.setForeground(new java.awt.Color(223, 44, 44));
        jLabel6.setText("Restore Backup");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(472, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_restore, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_restore, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-732)/2, (screenSize.height-409)/2, 732, 409);
    }// </editor-fold>//GEN-END:initComponents

    class MyFilter extends javax.swing.filechooser.FileFilter {

        public boolean accept(File file) {
            String filename = file.getName();
            return filename.endsWith(".sql");
        }

        public String getDescription() {
            return "*.sql";
        }
    }
private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
// TODO add your handling code here:
    JFileChooser fileChooser = new JFileChooser(new File("."));
    fileChooser.addChoosableFileFilter(new MyFilter());

    //fileChooser.showOpenDialog(null);
    // JFileChooser chooser = new JFileChooser(); 


    int returnVal = fileChooser.showOpenDialog(this);//chooser.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
        file = new File(fileChooser.getSelectedFile().getPath());
        if (file.getName().endsWith((".sql"))) {
            txtfileName.setText(file.getPath());
            name.setText(file.getPath());
            set.setText("utf-8");
            size.setText(doubleFmt.format(file.length()) + " byte");
        } else {
            JOptionPane.showMessageDialog(this, "ไฟล์ไม่ถูกต้อง", "Wrong file type", JOptionPane.ERROR_MESSAGE);
        }

    }
}//GEN-LAST:event_btn_browseActionPerformed

private void btn_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restoreActionPerformed
// TODO add your handling code here:
    if (txtfileName.getText().length() > 0 && file.exists()) {
        MySQLConnect db = new MySQLConnect();
        db.dbconnect();
        host = MySQLConnect.HostName;
        dbName = MySQLConnect.DbName;
        user = MySQLConnect.UserName;
        pass = MySQLConnect.Password;
        String filename = file.getName();
//        if (filename.indexOf(dbName) != 0) {
//            JOptionPane.showMessageDialog(this,"database ที่ต้องการ restore ไม่ตรงกับ database ที่มีอยู่");
//        }
//        else{
        Thread t = new Thread() {

            public void run() {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                
                txtfileName.setEnabled(false);
                btn_browse.setEnabled(false);
                btn_restore.setEnabled(false);
                cmdRestroe();
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                txtwait.setText("");
                txtfileName.setEnabled(true);
                btn_browse.setEnabled(true);
                btn_restore.setEnabled(true);
            }
        };
        t.start();
    // }
    }




}//GEN-LAST:event_btn_restoreActionPerformed

private void txtfileNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfileNameKeyReleased
    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        String pathName = txtfileName.getText();
        try {
            File file = new File(pathName);
            if (file.exists()) {
                if (file.getName().endsWith((".sql"))) {
                    txtfileName.setText(file.getPath());
                    name.setText(file.getPath());
                    set.setText("utf-8");
                    size.setText(doubleFmt.format(file.length()) + " byte");
                } else {
                    JOptionPane.showMessageDialog(this, "ไฟล์ไม่ถูกต้อง", "Wrong file type", JOptionPane.ERROR_MESSAGE);
                    txtfileName.selectAll();
                    name.setText("");
                    set.setText("");
                    size.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "ไม่พบไฟล์ที่ต้องการ");
                txtfileName.selectAll();
                name.setText("");
                set.setText("");
                size.setText("");
            }
        } catch (Exception e) {
        }
    }
}//GEN-LAST:event_txtfileNameKeyReleased
    private void cmdRestroe() {
        jProgressBar1.setIndeterminate(true);
       // if (dropDatabase(dbName)) {
            String[] executeCmd = new String[]{"/usr/bin/mysql", dbName, "--user=" + user, "--password=" + pass, "-e", " source " + file.getPath() + ""};
        //String executeCmd = "/usr/bin/mysql mycpssys --user=root --password=1234 -e source "+file.getPath()+"";
      
        System.out.println(executeCmd);
            try {///root/Desktop/tempMunuUser.sql
                Runtime rta = Runtime.getRuntime();
                Process p = rta.exec(executeCmd);

                if (p.waitFor() == 0) {
                    txtwait.setText("ระบบกำลังเรียกคืนข้อมูล กรุณารอ.....");
                    JOptionPane.showMessageDialog(this, "Restore database is successful", "Restore database", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Restore database error", "Restore database", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Restore Database is error! ,Please try again");
//        }

        jProgressBar1.setIndeterminate(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                restore_Db dialog = new restore_Db(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_restore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel set;
    private javax.swing.JLabel size;
    private javax.swing.JTextField txtfileName;
    private javax.swing.JLabel txtwait;
    // End of variables declaration//GEN-END:variables

    private boolean dropDatabase(String dbName) {
        Statement stmt = null;
        String sql = "Drop database " + dbName + "";
        int result = -1;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            result = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //if (result > -1) {
            return createDatabase(dbName);
       // } else {
       //     return false;
       // }
    }

    private boolean createDatabase(String dbName) {
        Statement stmt = null;
        String sql = "Create database " + dbName + "";
        int result = -1;
        try {
            stmt = (Statement) MySQLConnect.con.createStatement();
            result = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result > -1) {
            //JOptionPane.showMessageDialog(this,"created");
            return true;
        } else {
            return false;
        }
    }
}

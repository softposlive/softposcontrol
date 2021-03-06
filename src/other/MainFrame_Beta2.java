/*
 * MainFrame.java
 *
 * Created on May 30, 2009, 8:32 PM
 */

package other;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author  root
 */
public class MainFrame_Beta2 extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame_Beta2() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
        initComponents();
        setLocationRelativeTo(this);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdConnectTest = new javax.swing.JButton();
        cmdDisconnectTest = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaArea = new javax.swing.JTextArea();
        cmdUp1 = new javax.swing.JButton();
        cmdUp2 = new javax.swing.JButton();
        cmdUp3 = new javax.swing.JButton();
        cmdUp4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        pb = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        cmdDown1 = new javax.swing.JButton();
        cmdDown2 = new javax.swing.JButton();
        cmdDown3 = new javax.swing.JButton();
        cmdDown4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmdCodeTest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FTP Beta 2");

        cmdConnectTest.setText("ติดต่อสำนักงานใหญ่");
        cmdConnectTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConnectTestActionPerformed(evt);
            }
        });

        cmdDisconnectTest.setText("ยกเลิกการติดต่อ");
        cmdDisconnectTest.setEnabled(false);
        cmdDisconnectTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDisconnectTestActionPerformed(evt);
            }
        });

        txaArea.setColumns(20);
        txaArea.setRows(5);
        jScrollPane1.setViewportView(txaArea);

        cmdUp1.setText("1");
        cmdUp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUp1ActionPerformed(evt);
            }
        });

        cmdUp2.setText("2");
        cmdUp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUp2ActionPerformed(evt);
            }
        });

        cmdUp3.setText("3");
        cmdUp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUp3ActionPerformed(evt);
            }
        });

        cmdUp4.setText("1+2+3");
        cmdUp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUp4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblStatus.setText("status");

        lb.setText("0/0 Byte");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(lb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 15));
        jLabel3.setText("< Upload >");

        cmdDown1.setText("1");
        cmdDown1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDown1ActionPerformed(evt);
            }
        });

        cmdDown2.setText("2");
        cmdDown2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDown2ActionPerformed(evt);
            }
        });

        cmdDown3.setText("3");
        cmdDown3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDown3ActionPerformed(evt);
            }
        });

        cmdDown4.setText("1+2+3");
        cmdDown4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDown4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 15));
        jLabel4.setText("< Download >");

        cmdCodeTest.setText("CodeTest");
        cmdCodeTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCodeTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdDown2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addComponent(cmdDown1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addComponent(cmdDown3, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                    .addComponent(cmdDown4, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdCodeTest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdUp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdUp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdUp1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                            .addComponent(cmdUp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdConnectTest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdDisconnectTest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(23, 23, 23)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdConnectTest)
                                .addComponent(cmdDisconnectTest))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUp1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUp2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUp3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUp4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdDown1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDown2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDown3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDown4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addComponent(cmdCodeTest))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
FTPUtility ftp = new FTPUtility();
private void cmdConnectTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConnectTestActionPerformed
    String server = "chaopaya.dyndns.info";
    String user = "pinto";
    String pass = "pinto";
    int port = 21;
    ftp.setProgress(lb, pb);
    cmdConnectTest.setEnabled(false);
    if (ftp.connect(server, user, pass, port)) {
        txaArea.append("Connect to FTP Server : " + server + "\n");       
        cmdDisconnectTest.setEnabled(true);
    } else {
        cmdConnectTest.setEnabled(true);
        txaArea.append("Can not connect to FTP Server : " + server + "\n");
    }
}//GEN-LAST:event_cmdConnectTestActionPerformed

private void cmdDisconnectTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDisconnectTestActionPerformed
    txaArea.append("Disconnect from FTP Server : " + "\n");
    ftp.disconnect();
    cmdConnectTest.setEnabled(true);
    cmdDisconnectTest.setEnabled(false);
}//GEN-LAST:event_cmdDisconnectTestActionPerformed

private void cmdUp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUp1ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            up1();
        }
    });
    t.start();
}//GEN-LAST:event_cmdUp1ActionPerformed

private void cmdUp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUp2ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            up2();
        }
    });
    t.start();
}//GEN-LAST:event_cmdUp2ActionPerformed

private void cmdUp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUp3ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            up3();
        }
    });
    t.start();
}//GEN-LAST:event_cmdUp3ActionPerformed

private void cmdUp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUp4ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            up4();
        }
    });
    t.start();
}//GEN-LAST:event_cmdUp4ActionPerformed

private void cmdDown1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDown1ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            down1();
        }
    });
    t.start();
}//GEN-LAST:event_cmdDown1ActionPerformed

private void cmdDown2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDown2ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            down2();
        }
    });
    t.start();
}//GEN-LAST:event_cmdDown2ActionPerformed

private void cmdDown3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDown3ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            down3();
        }
    });
    t.start();
}//GEN-LAST:event_cmdDown3ActionPerformed

private void cmdDown4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDown4ActionPerformed
    Thread t = new Thread(new Runnable(){
          
        public void run(){
            down4();
        }
    });
    t.start();
}//GEN-LAST:event_cmdDown4ActionPerformed

private void cmdCodeTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCodeTestActionPerformed
    
    
}//GEN-LAST:event_cmdCodeTestActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame_Beta2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCodeTest;
    private javax.swing.JButton cmdConnectTest;
    private javax.swing.JButton cmdDisconnectTest;
    private javax.swing.JButton cmdDown1;
    private javax.swing.JButton cmdDown2;
    private javax.swing.JButton cmdDown3;
    private javax.swing.JButton cmdDown4;
    private javax.swing.JButton cmdUp1;
    private javax.swing.JButton cmdUp2;
    private javax.swing.JButton cmdUp3;
    private javax.swing.JButton cmdUp4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar pb;
    private javax.swing.JTextArea txaArea;
    // End of variables declaration//GEN-END:variables

    int complete = 0;
    int incomplete = 0;

    boolean cmdUp4Act = false;
    
    void showComplete(){
        txaArea.append("Upload Complete = "+complete +"\n");
        txaArea.append("Upload incomplete = "+incomplete +"\n");
    }
    
    void up1(){
        complete = 0;
        incomplete = 0;
        
        String server = "chaopaya.dyndns.info";
        String user = "pinto";
        String pass = "pinto";
        int port = 21;
        String localFile = "/root/localFileFTP/High Performance MySQL (2004).chm";
        String remoteFile = "/Choonew/High Performance MySQL (2004).chm";
 
        File f = new File(localFile);
        if(!f.exists()){
            JOptionPane.showMessageDialog(this, "Can not File in your computer :" + f.getPath());
            return;
        } 
        
        lblStatus.setText("Upload " + f.getName());
        
        FTPUtility_bak ftp2 = new FTPUtility_bak();
        ftp2.setProgress(lb, pb);
        
        if(ftp2.connect(server,user,pass,port)){
            if(ftp2.upload1(localFile,remoteFile)){
                complete++;
                txaArea.append(f.getPath()+" :=> complete\n");
            }else{
                JOptionPane.showMessageDialog(this, "Can not upload server");
                incomplete++;
                txaArea.append(f.getPath()+" :=> incomplete\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Can not Connect server");
            incomplete++;
            txaArea.append(f.getPath()+" :=> incomplete\n");
        }
        ftp2.disconnect();
        
        if(!cmdUp4Act)
            showComplete();
    }
    
    void up2(){
        complete = 0;
        incomplete = 0;
        
        String server = "chaopaya.dyndns.info";
        String user = "pinto";
        String pass = "pinto";
        int port = 21;
        String localFile = "/root/localFileFTP/Teach Yourself SQL In 24 Hours, 3rd Edition (2002).chm";
        String remoteFile = "/Choonew/Teach Yourself SQL In 24 Hours, 3rd Edition (2002).chm";
        
        FTPUtility_bak ftp1 = new FTPUtility_bak();
        ftp1.setProgress(lb, pb);
        
        File f = new File(localFile);
        if(!f.exists()){
            JOptionPane.showMessageDialog(this, "Can not File in your computer :" + f.getPath());
            return;
        }
        lblStatus.setText("Upload " + f.getName());
        
        if(ftp1.connect(server,user,pass,port)){
            if(ftp1.upload1(localFile,remoteFile)){
                complete++;
                txaArea.append(f.getPath()+" :=> complete\n");
            }else{
                JOptionPane.showMessageDialog(this, "Can not upload server");
                incomplete++;
                txaArea.append(f.getPath()+" :=> incomplete\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Can not Connect server");     
            incomplete++;
            txaArea.append(f.getPath()+" :=> incomplete\n");
        }
        ftp1.disconnect();
        
        if(!cmdUp4Act)
            showComplete();
    }
    
     void up3(){
        complete = 0;
        incomplete = 0;
        
        String server = "chaopaya.dyndns.info";
        String user = "pinto";
        String pass = "pinto";
        int port = 21;
        String localFile = "/root/localFileFTP/The Art Of C++ (2004).chm";
        String remoteFile = "/Choonew/The Art Of C++ (2004).chm";
        
        File f = new File(localFile);
        if(!f.exists()){
            JOptionPane.showMessageDialog(this, "Can not File in your computer :" + f.getPath());
            return;
        }
        
        lblStatus.setText("Upload " + f.getName());
        
        FTPUtility_bak ftp3 = new FTPUtility_bak();
        ftp3.setProgress(lb, pb);
        
        // upload 1 file/1 connect
        if(ftp3.connect(server,user,pass,port)){
            if(ftp3.upload1(localFile,remoteFile)){
                complete++;
                txaArea.append(f.getPath()+" :=> complete\n");
            }else{
                JOptionPane.showMessageDialog(this, "Can not upload server");
                incomplete++;
                txaArea.append(f.getPath()+" :=> incomplete\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Can not Connect server");          
            incomplete++;
            txaArea.append(f.getPath()+" :=> incomplete\n");
        }
        ftp3.disconnect();
        
        if(!cmdUp4Act)
            showComplete();
    }
    
    void up4(){
        
        cmdUp4Act = true;
        int completeTotal = 0;
        int incompleteTotal = 0;
        
        up1();
        completeTotal += complete;
        incompleteTotal += incomplete;
        up2();
        completeTotal += complete;
        incompleteTotal += incomplete;
        up3();
        completeTotal += complete;
        incompleteTotal += incomplete;
        
        txaArea.append("Upload Complete = "+completeTotal +"\n");
        txaArea.append("Upload incomplete = "+incompleteTotal +"\n");
        cmdUp4Act = false;
    }
    
    void down1(){
        complete = 0;
        incomplete = 0;
        
        String server = "chaopaya.dyndns.info";
        String user = "pinto";
        String pass = "pinto";
        int port = 21;
        String localFile = "/root/localFileFTP/High Performance MySQL (2004).chm";
        String remoteFile = "/Choonew/High Performance MySQL (2004).chm";
        
        lblStatus.setText("Download " + remoteFile);
        
        FTPUtility_bak ftp4 = new FTPUtility_bak();
        ftp4.setProgress(lb, pb);
        
        if(ftp4.connect(server,user,pass,port)){
            if(ftp4.download1(remoteFile,localFile)){
                complete++;
                txaArea.append(remoteFile+" :=> complete\n");
            }else{
                JOptionPane.showMessageDialog(this, "Can not upload server");
                incomplete++;
                txaArea.append(remoteFile+" :=> incomplete\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Can not Connect server");
            incomplete++;
            txaArea.append(remoteFile+" :=> incomplete\n");
        }
        ftp4.disconnect();
        
        if(!cmdUp4Act)
            showComplete();
    }
    
    void down2(){
        complete = 0;
        incomplete = 0;
        
        String server = "chaopaya.dyndns.info";
        String user = "pinto";
        String pass = "pinto";
        int port = 21;
        String localFile = "/root/localFileFTP/Teach Yourself SQL In 24 Hours, 3rd Edition (2002).chm";
        String remoteFile = "/Choonew/Teach Yourself SQL In 24 Hours, 3rd Edition (2002).chm";
        
        lblStatus.setText("Download " + remoteFile);
        
        FTPUtility_bak ftp5 = new FTPUtility_bak();
        ftp5.setProgress(lb, pb);
        
        if(ftp5.connect(server,user,pass,port)){
            if(ftp5.download1(remoteFile,localFile)){
                complete++;
                txaArea.append(remoteFile+" :=> complete\n");
            }else{
                JOptionPane.showMessageDialog(this, "Can not upload server");
                incomplete++;
                txaArea.append(remoteFile+" :=> incomplete\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Can not Connect server");
            txaArea.append(remoteFile+" :=> incomplete\n");
            incomplete++;
        }
        ftp5.disconnect();
        
        if(!cmdUp4Act)
            showComplete();
    }
    
    void down3(){
        complete = 0;
        incomplete = 0;
        
        String server = "chaopaya.dyndns.info";
        String user = "pinto";
        String pass = "pinto";
        int port = 21;
        String localFile = "/root/localFileFTP/The Art Of C++ (2004).chm";
        String remoteFile = "/Choonew/The Art Of C++ (2004).chm";
        
        lblStatus.setText("Download " + remoteFile);
        
        FTPUtility_bak ftp6 = new FTPUtility_bak();
        ftp6.setProgress(lb, pb);
        
        // upload 1 file/1 connect
        if(ftp6.connect(server,user,pass,port)){
            if(ftp6.download1(remoteFile,localFile)){
                complete++;
                txaArea.append(remoteFile+" :=> complete\n");
            }else{
                JOptionPane.showMessageDialog(this, "Can not upload server");
                incomplete++;
                txaArea.append(remoteFile+" :=> incomplete\n");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Can not Connect server");
            incomplete++;
            txaArea.append(remoteFile+" :=> incomplete\n");
        }
        ftp6.disconnect();
        
        if(!cmdUp4Act)
            showComplete();
    }
    
    void down4(){
        
        cmdUp4Act = true;
        int completeTotal = 0;
        int incompleteTotal = 0;
        
        down1();
        completeTotal += complete;
        incompleteTotal += incomplete;
        down2();
        completeTotal += complete;
        incompleteTotal += incomplete;
        down3();
        completeTotal += complete;
        incompleteTotal += incomplete;
        
        txaArea.append("Download Complete = "+completeTotal +"\n");
        txaArea.append("Download incomplete = "+incompleteTotal +"\n");
        cmdUp4Act = false;
    }
      
}

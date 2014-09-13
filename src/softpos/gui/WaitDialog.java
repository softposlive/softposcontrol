package softpos.gui;


public class WaitDialog extends javax.swing.JDialog {
    public static boolean isAnswer = false;

    public WaitDialog(java.awt.Frame parent, boolean modal, String program) {
        super(parent, modal);
        initComponents();
        setLocation(1000, 0);
        lb_master.setText(program+" เคยอัพเดตไปแล้ว \n" +
                "ท่านต้องการจะอัพเดตข้อมูลเดิม อีกครั้งหรือไม่ ?\n" +
                "NO ไม่ต้องการอัพเดต, UPDATE สำหรับการอัพเดต");
        btn_no.requestFocus();
        new Thread(new Runnable() {

            public void run() {
                int count = 5;
                while(true){
                    try {
                        lb_time.setText(""+count);
                        count--;
                        Thread.sleep(1000);
                        if(count==0){
                            isAnswer = false;
                            dispose();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_time = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lb_master = new javax.swing.JTextArea();
        btn_ok = new javax.swing.JButton();
        btn_no = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(6, 6, 7), 1, true));

        lb_time.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        lb_time.setForeground(java.awt.Color.red);
        lb_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_time.setText("5");
        lb_time.setFocusable(false);

        lb_master.setBackground(new java.awt.Color(226, 23, 40));
        lb_master.setColumns(20);
        lb_master.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        lb_master.setForeground(java.awt.Color.white);
        lb_master.setRows(5);
        lb_master.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(249, 250, 242), 3, true));
        jScrollPane1.setViewportView(lb_master);

        btn_ok.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        btn_ok.setText("UPDATE");
        btn_ok.setFocusable(false);
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        btn_no.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        btn_no.setText("NO");
        btn_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_noActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_no, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_time)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_no, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_time))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_noActionPerformed
        isAnswer = false;
        dispose();
    }//GEN-LAST:event_btn_noActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        btn_no.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        isAnswer = true;
        dispose();
    }//GEN-LAST:event_btn_okActionPerformed

    public static void main(String args[]) {
        WaitDialog wait = new WaitDialog(null, true, "");
        wait.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_no;
    private javax.swing.JButton btn_ok;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lb_master;
    private javax.swing.JLabel lb_time;
    // End of variables declaration//GEN-END:variables

}

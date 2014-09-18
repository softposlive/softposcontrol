package softpos.gui;

import Controller.Emp_Controller;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

public class jdi_EmpSetting extends javax.swing.JDialog {

    DefaultTableModel model;

    public jdi_EmpSetting(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        model = (DefaultTableModel) tblEmp.getModel();
        tblEmp.setShowGrid(true);
        tblEmp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblEmp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmp.setRowSelectionAllowed(true);
        tblEmp.setShowGrid(true);
        tblEmp.setGridColor(Color.gray);

        tblEmp.getColumnModel().getColumn(0).setPreferredWidth(130);
        tblEmp.getColumnModel().getColumn(1).setPreferredWidth(350);
        JTableHeader header = tblEmp.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));
        tblEmp.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
        tblEmp.setRowHeight(25);
        loadDataToGrid();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmp = new javax.swing.JTable();
        btn_cancle = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_remove = new javax.swing.JButton();
        btn_remove1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuu_cancle = new javax.swing.JMenuItem();
        menu_save = new javax.swing.JMenuItem();
        menu_remove = new javax.swing.JMenuItem();
        menu_exit = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("กำหนดรหัสบริกร");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(213, 205, 200));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(234, 230, 237));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel1.setText("รหัสบริกร (Emp)");

        jLabel2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel2.setText("ชื่อบริกร");

        txt1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt1FocusLost(evt);
            }
        });
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
        });

        txt2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt2MouseClicked(evt);
            }
        });
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        tblEmp.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        tblEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสบริกร", "ชื่อบริกร"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmp.setRowHeight(20);
        tblEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpMouseClicked(evt);
            }
        });
        tblEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEmpKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblEmp);
        if (tblEmp.getColumnModel().getColumnCount() > 0) {
            tblEmp.getColumnModel().getColumn(0).setResizable(false);
            tblEmp.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_cancle.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_cancle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancle.gif"))); // NOI18N
        btn_cancle.setText("ยกเลิก");
        btn_cancle.setToolTipText("Cancel all change");
        btn_cancle.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_cancle.setRequestFocusEnabled(false);
        btn_cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancleActionPerformed(evt);
            }
        });

        btn_save.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
        btn_save.setText("บันทึกข้อมูล");
        btn_save.setToolTipText("Save or Update Item");
        btn_save.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_save.setRequestFocusEnabled(false);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_remove.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.gif"))); // NOI18N
        btn_remove.setText("ลบ");
        btn_remove.setToolTipText("Remove Item");
        btn_remove.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_remove.setRequestFocusEnabled(false);
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        btn_remove1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_remove1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.gif"))); // NOI18N
        btn_remove1.setText("Exit");
        btn_remove1.setToolTipText("Remove Item");
        btn_remove1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_remove1.setRequestFocusEnabled(false);
        btn_remove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remove1ActionPerformed(evt);
            }
        });

        jMenu1.setText("ฟังก์ชันการทำงาน");
        jMenu1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N

        menuu_cancle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuu_cancle.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        menuu_cancle.setText("ยกเลิกการแก้ไข (Cancel All)");
        menuu_cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuu_cancleActionPerformed(evt);
            }
        });
        jMenu1.add(menuu_cancle);

        menu_save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menu_save.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        menu_save.setText("บันทึกข้อมูล (Save/Update)");
        menu_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_saveActionPerformed(evt);
            }
        });
        jMenu1.add(menu_save);

        menu_remove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menu_remove.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        menu_remove.setText("ลบข้อมูล (Remove)");
        menu_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_removeActionPerformed(evt);
            }
        });
        jMenu1.add(menu_remove);

        menu_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menu_exit.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        menu_exit.setText("ออกจากการทำงาน");
        menu_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_exitActionPerformed(evt);
            }
        });
        jMenu1.add(menu_exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_remove1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(510, 696));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt2MouseClicked
}//GEN-LAST:event_txt2MouseClicked

private void btn_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancleActionPerformed
    clearForm();
}//GEN-LAST:event_btn_cancleActionPerformed

private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed

    int rowSelect = tblEmp.getSelectedRow();
    if (rowSelect > -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "คุณต้องการลบข้อมูลรายการนี้หรือไม่...?", "Confirm", JOptionPane.YES_NO_OPTION);
        String pcode_remove = model.getValueAt(rowSelect, 0).toString();

        if (confirm == JOptionPane.YES_OPTION) {
            Emp_Controller waiter = new Emp_Controller();
            if (waiter.removeWaiter(pcode_remove)) {
                model.removeRow(rowSelect);
                JOptionPane.showMessageDialog(this, "ลบข้อมูลบริกรแล้ว");
            } else {
                JOptionPane.showMessageDialog(this, "Error : เกิดข้อผิดพลาดไม่สามารถลบข้อมูลบริกร");
            }
        }
        clearForm();
    }
}//GEN-LAST:event_btn_removeActionPerformed

private void tblEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpMouseClicked

    if (evt.getClickCount() == 2) {
        int rowSelect = tblEmp.getSelectedRow();
        String pcode_display = model.getValueAt(rowSelect, 0).toString();
        String pdesc_display = model.getValueAt(rowSelect, 1).toString();
        if (txt1.getText().equals("")) {
            txt1.setText(pcode_display);
            txt2.setText(pdesc_display);
        } else {
            if (!pcode_display.equals(txt1.getText())) {
                int confirm = JOptionPane.showConfirmDialog(this, "คุณมีการแก้ไข หรือมีการเพิ่มข้อมูลอยู่.. คุณต้องการยกเลิกการเพิ่มหรือการแก้ไขนี้หรือไม่", "Confirm", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    txt1.setText(pcode_display);
                    txt2.setText(pdesc_display);
                }
            }
        }

        txt1.setEditable(false);


    }
}//GEN-LAST:event_tblEmpMouseClicked

private void txt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyPressed
}//GEN-LAST:event_txt1KeyPressed

private void menuu_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuu_cancleActionPerformed
// TODO add your handling code here:
    clearForm();
}//GEN-LAST:event_menuu_cancleActionPerformed

private void menu_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveActionPerformed
// TODO add your handling code here:
    btn_saveActionPerformed(null);

}//GEN-LAST:event_menu_saveActionPerformed

private void menu_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_removeActionPerformed
// TODO add your handling code here:
    btn_removeActionPerformed(evt);
}//GEN-LAST:event_menu_removeActionPerformed

private void menu_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_exitActionPerformed
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_menu_exitActionPerformed

private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_txt2KeyReleased

private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (!txt1.getText().trim().equals("")) {
            loadDataToGrid(txt1.getText());
            txt2.requestFocus();
            txt1.setEditable(false);
        }
    }
}//GEN-LAST:event_txt1KeyReleased

private void tblEmpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmpKeyReleased
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_tblEmpKeyReleased

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    if (txt1.isEnabled()) {
        txt1.requestFocus();
    }
}//GEN-LAST:event_formWindowActivated

private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost
}//GEN-LAST:event_txt1FocusLost

private void btn_remove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remove1ActionPerformed
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_btn_remove1ActionPerformed

private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
// TODO add your handling code here:
    if (txt1.getText().trim().equals("") || txt2.getText().trim().equals("")) {
    } else {
        Emp_Controller waiter = new Emp_Controller();
        if (waiter.insertWaiter(txt1.getText(), txt2.getText())) {
            clearForm();
            loadDataToGrid();
            JOptionPane.showMessageDialog(this, "บันทึกข้อมูลบริกรแล้ว");
        } else {
            JOptionPane.showMessageDialog(this, "Error : เกิดความผิดพลาดไม่สามารถบันทึกข้อมูลบริกรได้");
        }
    }
}//GEN-LAST:event_btn_saveActionPerformed
    public void loadDataToGrid() {
        Emp_Controller waiter = new Emp_Controller();
        List list = waiter.getOptionDetail();
        int cnt = model.getRowCount();
        if (cnt > 0) {
            for (int i = 0; i < cnt; i++) {
                model.removeRow(0);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String[] data = (String[]) list.get(i);
            String[] aa = {data[0], data[1]};
            model.addRow(aa);
        }

        txt1.requestFocus();
    }

    public void loadDataToGrid(String code) {
        Emp_Controller waiter = new Emp_Controller();
        String list = waiter.getEmpDetail(code);
        int cnt = model.getRowCount();
        for (int i = 0; i < cnt; i++) {
            String search = (String) model.getValueAt(i, 0);
            if (search.equals(code)) {
                tblEmp.clearSelection();
                tblEmp.setRowSelectionInterval(0, i);
                break;
            }
        }
        txt2.setText(list);
    }

    public void clearForm() {
        txt1.setEditable(true);
        txt1.setText("");
        txt2.setText("");
        txt1.requestFocus();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jdi_EmpSetting dialog = new jdi_EmpSetting(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancle;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_remove1;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem menu_exit;
    private javax.swing.JMenuItem menu_remove;
    private javax.swing.JMenuItem menu_save;
    private javax.swing.JMenuItem menuu_cancle;
    private javax.swing.JTable tblEmp;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    // End of variables declaration//GEN-END:variables
}

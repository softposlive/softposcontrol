package softpos.gui;

import Controller.OptionGroup_Controller;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

public class jdi_option_Group extends javax.swing.JDialog {

    DefaultTableModel tblProduct_model;

    public jdi_option_Group(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        
        tblProduct_model = (DefaultTableModel) tblProduct.getModel();
        tblProduct.setShowGrid(true);
        tblProduct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblProduct.setRowSelectionAllowed(true);
        tblProduct.setShowGrid(true);
        tblProduct.setGridColor(Color.gray);

        tblProduct.getColumnModel().getColumn(0).setPreferredWidth(130);
        tblProduct.getColumnModel().getColumn(1).setPreferredWidth(350);
        JTableHeader header = tblProduct.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));
        tblProduct.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
        tblProduct.setRowHeight(25);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pcode = new javax.swing.JTextField();
        pdesc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        option = new javax.swing.JTextField();
        btn_save = new javax.swing.JButton();
        btn_display = new javax.swing.JButton();
        bnt_remove = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();

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
        setTitle("กำหนดรายการ Option (สำหรับกลุ่มสินค้า)");
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
        jLabel1.setText("รหัสแผนก");

        pcode.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        pcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pcodeMouseExited(evt);
            }
        });
        pcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pcodeKeyReleased(evt);
            }
        });

        pdesc.setBackground(new java.awt.Color(250, 217, 184));
        pdesc.setEditable(false);
        pdesc.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        pdesc.setFocusable(false);
        pdesc.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(pcode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pcode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        tblProduct.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblProduct.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "กลุ่ม", "รายการ Option"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.setRowHeight(20);
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        tblProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduct);
        if (tblProduct.getColumnModel().getColumnCount() > 0) {
            tblProduct.getColumnModel().getColumn(0).setResizable(false);
            tblProduct.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel3.setBackground(new java.awt.Color(234, 230, 237));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        option.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        option.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optionMouseClicked(evt);
            }
        });
        option.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                optionKeyReleased(evt);
            }
        });

        btn_save.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_save.setText("ตกลง");
        btn_save.setToolTipText("Create new item");
        btn_save.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_save.setRequestFocusEnabled(false);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(option, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(option, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btn_display.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_display.setText("แสดงรายการทั้งหมด");
        btn_display.setToolTipText("Cancel all change");
        btn_display.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_display.setRequestFocusEnabled(false);
        btn_display.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_displayActionPerformed(evt);
            }
        });

        bnt_remove.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bnt_remove.setText("ลบข้อมูล");
        bnt_remove.setToolTipText("Save or Update Item");
        bnt_remove.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bnt_remove.setRequestFocusEnabled(false);
        bnt_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_removeActionPerformed(evt);
            }
        });

        btn_exit.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_exit.setText("ออก (Exit)");
        btn_exit.setToolTipText("Remove Item");
        btn_exit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn_exit.setRequestFocusEnabled(false);
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_display, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(bnt_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnt_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_display, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(510, 629));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bnt_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_removeActionPerformed
    int row = tblProduct.getSelectedRow();
    if (row > -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "คุณต้องการลบข้อมูลรายการนี้หรือไม่...?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String code = tblProduct_model.getValueAt(row, 0).toString();
            String name = tblProduct_model.getValueAt(row, 1).toString();
            OptionGroup_Controller group = new OptionGroup_Controller();
            if (group.removeOptionDetail(code, name)) {
                tblProduct_model.removeRow(row);
            }
        }

    }
}//GEN-LAST:event_bnt_removeActionPerformed

private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
    dispose();
}//GEN-LAST:event_btn_exitActionPerformed

private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
}//GEN-LAST:event_tblProductMouseClicked

private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
// TODO add your handling code here:
    int selectRow = tblProduct.getSelectedRow();
    OptionGroup_Controller group = new OptionGroup_Controller();
    if (selectRow > -1) {
        JOptionPane.showMessageDialog(this, "รายการนี้มีการบันไว้แล้ว");
        option.selectAll();
    } else {
        String code = pcode.getText();
        String opt = option.getText();
        if (code.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุรหัสกลุ่มสินค้า");
            pcode.selectAll();
            pcode.requestFocus();
        } else if (pdesc.getText().equals("**** GroupFile Error ****")) {
            JOptionPane.showMessageDialog(this, "รหัสสินค้าไม่ถูกต้อง");
            pcode.requestFocus();
        } else if (opt.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุรายการ Option");
            option.requestFocus();
        } else {
            if (group.insertOption(code, opt)) {
                clearForm();
                setDataToGrid(group.getOptionDetail(code));
            }

        }
    }

}//GEN-LAST:event_btn_saveActionPerformed
    private void setDataToGrid(List option) {
        for (int i = 0; i < option.size(); i++) {
            String[] data = (String[]) option.get(i);
            String[] tbl = {data[0], data[1]};
            tblProduct_model.addRow(tbl);
        }
    }

    private void clearForm() {
        int tblsize = tblProduct_model.getRowCount();
        for (int i = 0; i < tblsize; i++) {
            tblProduct_model.removeRow(0);
        }
        option.setText("");
    }
private void tblProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductKeyReleased
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }
}//GEN-LAST:event_tblProductKeyReleased

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
}//GEN-LAST:event_formWindowActivated

private void optionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionMouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_optionMouseClicked

private void optionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optionKeyReleased
// TODO add your handling code here:
    int rowCnt = tblProduct.getRowCount();
    String search = option.getText();
    for (int i = 0; i < rowCnt; i++) {
        tblProduct.clearSelection();
        if (search.equals(tblProduct_model.getValueAt(i, 1).toString())) {
            tblProduct.setRowSelectionInterval(0, i);
            break;
        }

    }

}//GEN-LAST:event_optionKeyReleased

private void pcodeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pcodeMouseExited
// TODO add your handling code here:
}//GEN-LAST:event_pcodeMouseExited

private void pcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pcodeKeyReleased
// TODO add your handling code here:    
    String code = pcode.getText();
    int tblsize = tblProduct_model.getRowCount();
    for (int i = 0; i < tblsize; i++) {
        tblProduct_model.removeRow(0);
    }
    pdesc.setText("");
    if (!code.trim().equals("")) {
        OptionGroup_Controller group = new OptionGroup_Controller();
        String groupName = group.seekGroup(code);
        if (groupName != null && !groupName.equals("")) {
            pdesc.setText(groupName);
            setDataToGrid(group.getOptionDetail(code));
        } else {
            pdesc.setText("**** GroupFile Error ****");
        }
    }
   if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
       if(!pdesc.getText().equals("**** GroupFile Error ****")&& !pdesc.getText().trim().equals("")){
           option.requestFocus();           
       }
   }

}//GEN-LAST:event_pcodeKeyReleased

private void btn_displayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_displayActionPerformed
    jdi_option_ShowAll dialog = new jdi_option_ShowAll(new javax.swing.JFrame(), true);
    dialog.setDataToGrid();
    dialog.setVisible(true);
}//GEN-LAST:event_btn_displayActionPerformed

private void optionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optionKeyPressed
// TODO add your handling code here:
    if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE){
        pcode.setText("");
        pcode.requestFocus();
        pdesc.setText("");
    }
}//GEN-LAST:event_optionKeyPressed

    public static void main(String args[]) {
        jdi_option_Group jdi = new jdi_option_Group(new Frame(), true);
        jdi.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_remove;
    private javax.swing.JButton btn_display;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField option;
    private javax.swing.JTextField pcode;
    private javax.swing.JTextField pdesc;
    private javax.swing.JTable tblProduct;
    // End of variables declaration//GEN-END:variables
}

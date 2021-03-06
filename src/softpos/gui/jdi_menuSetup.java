package softpos.gui;


import Controller.MenuSetUp_Controller;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * jdi_menuSetup.java
 *
 * Created on July 1, 2009, 9:20 PM
 */
import javax.swing.table.JTableHeader;

/**
 *
 * @author  root
 */
public class jdi_menuSetup extends javax.swing.JDialog {

    DefaultTableModel model;
    DefaultTableModel model2;
    DecimalFormat doubleFmt = new DecimalFormat("##,###,##0.00");

    public jdi_menuSetup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
//        try {
//            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        initComponents();
        model = (DefaultTableModel) tblDetail.getModel();
        tblDetail.setShowGrid(true);
        tblDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDetail.setRowSelectionAllowed(true);
        tblDetail.setShowGrid(true);
        tblDetail.setGridColor(Color.gray);

        tblDetail.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblDetail.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblDetail.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetail.getColumnModel().getColumn(3).setPreferredWidth(250);
        tblDetail.getColumnModel().getColumn(4).setPreferredWidth(100);

        JTableHeader header = tblDetail.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));
        tblDetail.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
        tblDetail.setRowHeight(25);

        model2 = (DefaultTableModel) tblMenu.getModel();
        tblMenu.setShowGrid(true);
        tblMenu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMenu.setRowSelectionAllowed(true);
        tblMenu.setShowGrid(true);
        tblMenu.setGridColor(Color.gray);

        tblMenu.getColumnModel().getColumn(0).setPreferredWidth(145);
        tblMenu.getColumnModel().getColumn(1).setPreferredWidth(80);
        JTableHeader header2 = tblMenu.getTableHeader();
        header2.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));
        tblMenu.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 14));
        tblMenu.setRowHeight(25);
        loadDatatoGrid();
        loadDatatoMenuItem();
    }

    private void clearForm() {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        comboACT.setSelectedIndex(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        pop1 = new javax.swing.JMenuItem();
        pop2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        comboACT = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtOK = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetail = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        btn_exit = new javax.swing.JButton();

        pop1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        pop1.setText("เลือกทุกรายการในเมนูเล่มนี้");
        pop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop1ActionPerformed(evt);
            }
        });
        jPopupMenu.add(pop1);

        pop2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        pop2.setText("ยกเลิกทุกรายการในเมนูเล่มนี้");
        pop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pop2ActionPerformed(evt);
            }
        });
        jPopupMenu.add(pop2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Setup");
        setFont(new java.awt.Font("Norasi", 0, 10)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(147, 202, 238));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel1.setText("รหัสเล่ม (Menu)");

        txt1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel4.setText("Menu Item");

        txt2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2KeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel3.setText("รหัสสินค้า (PLU)");

        txt3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt3KeyReleased(evt);
            }
        });

        txt4.setBackground(new java.awt.Color(250, 217, 184));
        txt4.setEditable(false);
        txt4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N

        txt5.setBackground(new java.awt.Color(250, 217, 184));
        txt5.setEditable(false);
        txt5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txt5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        comboACT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Y", "N" }));

        jLabel2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel2.setText("ชื่อสินค้า (Description)");

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("ราคาขาย");

        jLabel6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel6.setText("ACT");

        txtOK.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtOK.setText("ตกลง OK");
        txtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOKActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jButton2.setText("ลบ Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jButton3.setText("ยกเลิก");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboACT, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(txtOK, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(txtOK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboACT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblDetail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเล่ม Menu", "Menu Item", "รหัสสินค้า (PLU)", "ชื่อสินค้า (Description)", "ราคาขาย", "ACT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblDetailMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetail);
        if (tblDetail.getColumnModel().getColumnCount() > 0) {
            tblDetail.getColumnModel().getColumn(0).setResizable(false);
            tblDetail.getColumnModel().getColumn(1).setResizable(false);
            tblDetail.getColumnModel().getColumn(2).setResizable(false);
            tblDetail.getColumnModel().getColumn(3).setResizable(false);
            tblDetail.getColumnModel().getColumn(4).setResizable(false);
            tblDetail.getColumnModel().getColumn(5).setResizable(false);
        }

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเล่ม Menu", "ACT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMenuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMenu);
        if (tblMenu.getColumnModel().getColumnCount() > 0) {
            tblMenu.getColumnModel().getColumn(0).setResizable(false);
            tblMenu.getColumnModel().getColumn(1).setResizable(false);
        }

        btn_exit.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        btn_exit.setText("ออก (Exit)");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOKActionPerformed
// TODO add your handling code here:
    if (validateForm()) {
        if (foundProduct()) {
            MenuSetUp_Controller menu = new MenuSetUp_Controller();
            if (menu.insertMenuList(txt1.getText(), txt2.getText(), txt3.getText(), comboACT.getSelectedItem().toString())) {
                refreshTable();                
            }
        }

    }

}//GEN-LAST:event_txtOKActionPerformed
    private boolean validateForm() {
        if (txt1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุเล่ม Menu !");
            txt1.requestFocus();
            return false;
        } else if (txt2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุ Menu Item !");
            txt2.requestFocus();
            return false;
        } else if (txt3.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "กรุณาระบุรหัส PLU !");
            txt3.requestFocus();
            return false;
        } else {
            try {
                Integer.parseInt(txt2.getText()); 
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "กรุณาระบุรหัส Menu Item ด้วยตัวเลข !");
                txt2.setText("");
                txt2.requestFocus();                
                return false;
            }
            
        }
    }
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    int row = tblDetail.getSelectedRow();
    if (row > -1) {
        int confirm = JOptionPane.showConfirmDialog(this, "คุณต้องการลบข้อมูลรายการนี้หรือไม่...?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String menu = (String) model.getValueAt(row, 0);
            String menuItem = (String) model.getValueAt(row, 1);
            MenuSetUp_Controller ctrl = new MenuSetUp_Controller();
            if (ctrl.removeMenuList(menu, menuItem)) {
                model.removeRow(row);
            }
        }
    }
}//GEN-LAST:event_jButton2ActionPerformed

private void tblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuMouseClicked
// TODO add your handling code here:
    int row = tblMenu.getSelectedRow();
    if (row > -1) {
        if (evt.getButton() == 3) {
            jPopupMenu.show(tblMenu, evt.getX(), evt.getY());
        }
    }

}//GEN-LAST:event_tblMenuMouseClicked

private void tblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseClicked
// TODO add your handling code here:
    int row = tblDetail.getSelectedRow();
    String menu = (String) model.getValueAt(row, 0);
    String menuItem = (String) model.getValueAt(row, 1);
    String plu = (String) model.getValueAt(row, 2);
    String desc = (String) model.getValueAt(row, 3);
    String price = (String) model.getValueAt(row, 4);
    String act = (String) model.getValueAt(row, 5);
    if (row > -1) {
        txt1.setText(menu);
        txt2.setText(menuItem);
        txt3.setText(plu);
        txt4.setText(desc);
        txt5.setText(price);
        if (act.equals("Y")) {
            comboACT.setSelectedIndex(0);
        } else {
            comboACT.setSelectedIndex(1);
        }
    }

}//GEN-LAST:event_tblDetailMouseClicked

private void tblDetailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetailMouseEntered
// TODO add your handling code here:
}//GEN-LAST:event_tblDetailMouseEntered

private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
// TODO add your handling code here:
    dispose();
}//GEN-LAST:event_btn_exitActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    clearForm();
}//GEN-LAST:event_jButton3ActionPerformed

private void txt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt3KeyReleased
// TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            if(!txt3.equals("")){
                foundProduct();
            }
        }
    }

 private boolean foundProduct() {
        String plu = txt3.getText();
        String search = "";
        String price = "";
        String desc = "";
        boolean bln = false;
        int fnd = 0;
        int cnt = model.getRowCount();
        for (int i = 0; i < cnt; i++) {
            search = (String) model.getValueAt(i, 2);
            if (plu.compareTo(search) == 0) {
                price = (String) model.getValueAt(i, 4);
                desc = (String) model.getValueAt(i, 3);
                fnd = 1;
                break;
            }
        }
        if (fnd == 0) {
            MenuSetUp_Controller menu = new MenuSetUp_Controller();
            String[] pro = menu.getProductDesc(plu);
            desc = pro[0];
            price = pro[1];
        }
        if (desc != null) {
            txt5.setText(price);
            txt4.setText(desc);
            bln = true;
        } else {
            JOptionPane.showMessageDialog(this, "ไม่พบรายการสินค้านี้");
            txt3.setText("");
        }
        return bln;
}//GEN-LAST:event_txt3KeyReleased

private void pop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop1ActionPerformed
    int row = tblMenu.getSelectedRow();
    if (row > -1) {
        MenuSetUp_Controller setup = new MenuSetUp_Controller();
        String menu = (String) tblMenu.getValueAt(row, 0);
        if (setup.updateMenuActive(menu, "Y")) {
            JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลแล้ว");
            refreshTable();
        }
    }
}//GEN-LAST:event_pop1ActionPerformed
    
private void pop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pop2ActionPerformed
    int row = tblMenu.getSelectedRow();
    if (row > -1) {
        MenuSetUp_Controller setup = new MenuSetUp_Controller();
        String menu = (String) tblMenu.getValueAt(row, 0);
        if (setup.updateMenuActive(menu, "N")) {
            JOptionPane.showMessageDialog(this, "แก้ไขข้อมูลแล้ว");
            refreshTable();
        }
    }
}//GEN-LAST:event_pop2ActionPerformed

private void txt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyPressed
// TODO add your handling code here:
    if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
       if(!txt1.getText().equals("")){
        txt2.requestFocus();
    } 
    }
    
}//GEN-LAST:event_txt1KeyPressed

private void txt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyPressed
// TODO add your handling code here:
   
}//GEN-LAST:event_txt2KeyPressed

private void txt3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt3KeyPressed
// TODO add your handling code here:
     if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
       if(!txt4.getText().equals("")){
        txtOK.requestFocus();
    } 
    }
}//GEN-LAST:event_txt3KeyPressed
   
private void refreshTable() {
        int cnt = model.getRowCount();
        int cnt2 = model2.getRowCount();
        for (int i = 0; i < cnt; i++) {
            model.removeRow(0);
        }
        for (int i = 0; i < cnt2; i++) {
            model2.removeRow(0);
        }
        loadDatatoGrid();
        loadDatatoMenuItem();
        clearForm();
    }
public void loadDatatoGrid() {
        MenuSetUp_Controller menu = new MenuSetUp_Controller();
        java.util.List menulist = menu.getMenuList();
        for (int i = 0; i < menulist.size(); i++) {
            String[] data = (String[]) menulist.get(i);
            Float price = Float.parseFloat(data[5]);
            String[] tbl = {data[0], data[1], data[2], data[4], doubleFmt.format(price), data[3]};
            model.addRow(tbl);
        }
    }

    public void loadDatatoMenuItem() {
        MenuSetUp_Controller menu = new MenuSetUp_Controller();
        java.util.List menulist = menu.getMenuItem();
        for (int i = 0; i < menulist.size(); i++) {
            String[] data = (String[]) menulist.get(i);
            String[] tbl = {data[0], data[1]};
            model2.addRow(tbl);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                jdi_menuSetup dialog = new jdi_menuSetup(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_exit;
    private javax.swing.JComboBox comboACT;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem pop1;
    private javax.swing.JMenuItem pop2;
    private javax.swing.JTable tblDetail;
    private javax.swing.JTable tblMenu;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JButton txtOK;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DateChoseDialog.java
 *
 * Created on 05-Aug-2009, 10:53:13
 */

package utilities.DateChooser;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author choonew
 */
public final class DateChooseDialog extends javax.swing.JDialog {

    private GregorianCalendar curDay;
    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
    private boolean ok;
    private String day;
    private Timer time;
    
    private Calendar selectDate;

    public boolean isOk() {
        return ok;
    }

    public String getDay() {
        return day;
    }

    /** Creates new form DateChoseDialog */
    public DateChooseDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        initTable(tblCalendar);
        spYear.setValue(2009);
        curDay = (GregorianCalendar) Calendar.getInstance();
        lbToday.setText("วันนี้ : "+df.format(new Date()));
        time = new Timer(1000,new TimeShow());
        time.start();
        spYear.setEditor(new JSpinner.NumberEditor(spYear, "####0"));
        tblCalendar.requestFocus();
        showCalendar();
        
        //tblCalendar.requestFocus(true);
        //System.out.println("Start");
    }
    public DateChooseDialog(java.awt.Frame parent, boolean modal, Point point) {
        this(parent,modal);
        this.setLocation(point);
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
        cbMonth = new javax.swing.JComboBox();
        spYear = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCalendar = new javax.swing.JTable();
        cmdForward = new javax.swing.JButton();
        cmdBack = new javax.swing.JButton();
        lbToday = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Calendar");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setToolTipText("");

        cbMonth.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        cbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฏาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม" }));
        cbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMonthActionPerformed(evt);
            }
        });
        cbMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbMonthKeyPressed(evt);
            }
        });

        spYear.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        spYear.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2009), null, null, Integer.valueOf(1)));
        spYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spYearStateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCalendar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0", "1", "2", "3", "4", "5", "6"},
                {"7", "8", "9", "10", "11", "12", "13"},
                {"14", "15", "16", "17", "18", "19", "20"},
                {"21", "22", "23", "24", "25", "26", "27"},
                {"28", "29", "30", "31", "32", "33", "34"},
                {"35", "36", "37", "38", "39", "40", "41"}
            },
            new String [] {
                "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        tblCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblCalendarMouseReleased(evt);
            }
        });
        tblCalendar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCalendarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblCalendarKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblCalendar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );

        cmdForward.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdForward.setText(">>");
        cmdForward.setFocusable(false);
        cmdForward.setRequestFocusEnabled(false);
        cmdForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdForwardActionPerformed(evt);
            }
        });

        cmdBack.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdBack.setText("<<");
        cmdBack.setFocusable(false);
        cmdBack.setRequestFocusEnabled(false);
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        lbToday.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        lbToday.setForeground(new java.awt.Color(102, 0, 0));
        lbToday.setText("วันนี้ :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbMonth, 0, 149, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spYear, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbToday, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(cmdForward, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spYear, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmdForward)
                    .addComponent(cmdBack)
                    .addComponent(lbToday))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-290)/2, (screenSize.height-253)/2, 290, 253);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCalendarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCalendarMouseReleased
        if(evt.getClickCount() == 2)
            initSelectDate();
}//GEN-LAST:event_tblCalendarMouseReleased

    private void tblCalendarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCalendarKeyReleased
//        if(evt.getKeyCode()== evt.VK_ENTER){
//            initSelectDate();
//        }
}//GEN-LAST:event_tblCalendarKeyReleased

    private void cmdForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdForwardActionPerformed
        curDay.add(Calendar.MONTH, +1);
        showCalendar();
        //       System.out.println("Forward");
}//GEN-LAST:event_cmdForwardActionPerformed

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        curDay.add(Calendar.MONTH, -1);
        showCalendar();
        //       System.out.println("back");
}//GEN-LAST:event_cmdBackActionPerformed

    private void cbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMonthActionPerformed
        doInputAction();
    }//GEN-LAST:event_cbMonthActionPerformed

    private void spYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spYearStateChanged
        //System.out.println("<<<DDDDD>>>");
        doInputAction();
    }//GEN-LAST:event_spYearStateChanged

private void cbMonthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbMonthKeyPressed
    if (evt.getKeyCode() == evt.VK_ESCAPE) {
        selectDate = null;
        dispose();
    }
}//GEN-LAST:event_cbMonthKeyPressed

private void tblCalendarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCalendarKeyPressed
        if(evt.getKeyCode() == evt.VK_ESCAPE){
            selectDate = null;
            dispose();
        }
        else if(evt.getKeyCode()== evt.VK_ENTER){
            initSelectDate();
        }
        else if(evt.getKeyCode() == evt.VK_UP){
            int index = tblCalendar.getSelectedRow();
            if(index == 0){
                curDay.add(Calendar.MONTH, +1);
                showCalendar();
                tblCalendar.setRowSelectionInterval(0, index);
            }
        }
        else if(evt.getKeyCode() == evt.VK_DOWN){
            int index = tblCalendar.getSelectedRow();
            if(index == 5){
                curDay.add(Calendar.MONTH, -1);
                showCalendar();
                tblCalendar.setRowSelectionInterval(0, index);
            }
        }
         else if(evt.getKeyCode() == evt.VK_LEFT){
            int index = tblCalendar.getSelectedColumn();
            int inrow = tblCalendar.getSelectedRow();
            if(index == 0){
                curDay.add(Calendar.YEAR, -1);
                showCalendar();
                tblCalendar.setRowSelectionInterval(0, inrow);
            }
        }
        else if(evt.getKeyCode() == evt.VK_RIGHT){
            int index = tblCalendar.getSelectedColumn();
            int inrow = tblCalendar.getSelectedRow();
            if(index == 6){
                curDay.add(Calendar.YEAR, +1);
                showCalendar();
                tblCalendar.setRowSelectionInterval(0, inrow);
            }
        }
}//GEN-LAST:event_tblCalendarKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DateChooseDialog dialog = new DateChooseDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbMonth;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdForward;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbToday;
    private javax.swing.JSpinner spYear;
    private javax.swing.JTable tblCalendar;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel model;

    private void initTable(JTable table) {
        //Setting column size
        model = (DefaultTableModel) table.getModel();
        table.setShowGrid(true);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setShowGrid(true);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);

        JTableHeader header = table.getTableHeader();
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 13));
        table.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 12));
        table.setRowHeight(17);

        table.setCellSelectionEnabled(true);

        //Setting column size
        TableColumn column = null;
        int[] colSize = {37, 38, 37, 38, 37, 37, 37};
        //tblShow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < colSize.length; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(colSize[i]);
            column.setResizable(false);
        }

        TableColumnModel tcm = table.getColumnModel();

        DefaultTableCellRenderer d;

        d = new DefaultTableCellRenderer();
        d.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(0).setCellRenderer(d);
        tcm.getColumn(1).setCellRenderer(d);
        tcm.getColumn(2).setCellRenderer(d);
        tcm.getColumn(3).setCellRenderer(d);
        tcm.getColumn(4).setCellRenderer(d);
        tcm.getColumn(5).setCellRenderer(d);
        tcm.getColumn(6).setCellRenderer(d);

    }

    private int numMonth() {
        int n = -1;
        switch (curDay.get(Calendar.MONTH)) {
            case 0:
                n = 31;
                break;
            case 1:
                if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                    n = 29;
                } else {
                    n = 28;
                }
                break;
            case 2:
                n = 31;
                break;
            case 3:
                n = 30;
                break;
            case 4:
                n = 31;
                break;
            case 5:
                n = 30;
                break;
            case 6:
                n = 31;
                break;
            case 7:
                n = 31;
                break;
            case 8:
                n = 30;
                break;
            case 9:
                n = 31;
                break;
            case 10:
                n = 30;
                break;
            case 11:
                n = 31;
                break;
        }
        return n;
    }

    private void showCalendar() {
        initMonthYearShow(curDay);
        resetTableModel(model);
        curDay.set(Calendar.DAY_OF_MONTH, 1);
        int index = curDay.get(Calendar.DAY_OF_WEEK) - 1;
        int numMonth = numMonth();
        int numDay = 1;
        int[] weeks = new int[42];
        boolean chk = false;
        for (int i = 0; i < 43; i++) {
            if (i == index) {
                weeks[i] = numDay;
                chk = true;
                numDay++;
            } else if (chk == true) {
                if (numDay <= numMonth) {
                    weeks[i] = numDay;
                    numDay++;
                } else {
                }
            } else {
                weeks[i] = 0;
            }
        }

        String[] week1 = new String[7];
        String[] week2 = new String[7];
        String[] week3 = new String[7];
        String[] week4 = new String[7];
        String[] week5 = new String[7];
        String[] week6 = new String[7];
        week1 = addWeek(weeks, week1, 1);
        week2 = addWeek(weeks, week2, 2);
        week3 = addWeek(weeks, week3, 3);
        week4 = addWeek(weeks, week4, 4);
        week5 = addWeek(weeks, week5, 5);
        week6 = addWeek(weeks, week6, 6);

        model.addRow(week1);
        model.addRow(week2);
        model.addRow(week3);
        model.addRow(week4);
        model.addRow(week5);
        model.addRow(week6);
     
        
        
    }

    private String[] addWeek(int[] weeks, String[] week, int weekofmount) {
        if (weekofmount == 1) {
            for (int i = 0; i < week.length; i++) {
                int day = weeks[i];
                week[i] = day == 0 ? "" : String.valueOf(day);
            }
        } else if (weekofmount == 2) {
            for (int i = 0; i < week.length; i++) {
                int day = weeks[i + 7];
                week[i] = day == 0 ? "" : String.valueOf(day);
            }
        } else if (weekofmount == 3) {
            for (int i = 0; i < week.length; i++) {
                int day = weeks[i + 14];
                week[i] = day == 0 ? "" : String.valueOf(day);
            }
        } else if (weekofmount == 4) {
            for (int i = 0; i < week.length; i++) {
                int day = weeks[i + 21];
                week[i] = day == 0 ? "" : String.valueOf(day);
            }
        } else if (weekofmount == 5) {
            for (int i = 0; i < week.length; i++) {
                int day = weeks[i + 28];
                week[i] = day == 0 ? "" : String.valueOf(day);
            }
        } else if (weekofmount == 6) {
            for (int i = 0; i < week.length; i++) {
                int day = weeks[i + 35];
                week[i] = day == 0 ? "" : String.valueOf(day);
            }
        }
        return week;
    }

    private void resetTableModel(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
    }

    /**
     * @return the selectDate
     */
    public Calendar getSelectDate() {
        return selectDate;
    }

    private void initSelectDate() {
        int row = tblCalendar.getSelectedRow();
        int column = tblCalendar.getSelectedColumn();
        String ndate = (String) model.getValueAt(row, column);
        if (!ndate.equals("")) {
            int dayn = Integer.parseInt(ndate);
            int month = cbMonth.getSelectedIndex();
            int year;
            try {
                year = Integer.parseInt(String.valueOf(spYear.getValue()));
                Calendar sDate = Calendar.getInstance();
                sDate.set(Calendar.YEAR, year);
                sDate.set(Calendar.MONTH, month);
                sDate.set(Calendar.DAY_OF_MONTH, dayn);

                //System.out.println("Your Select Date is "+dateShowFmt.format(sDate.getTime()));
                //System.out.println("Today : "+dateShowFmt.format(new Date()));
                selectDate = sDate;
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Input Year Incorrect!!!");
                selectDate = null;
            }
            //System.out.println("Your Select Date is " + dateShowFmt.format(selectDate.getTime()));
        }
    }
    
    private void initMonthYearShow(Calendar cal) {
        SimpleDateFormat m = new SimpleDateFormat("M", Locale.ENGLISH);
        SimpleDateFormat y = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        cbMonth.setSelectedIndex(Integer.parseInt(m.format(cal.getTime())) - 1);
        spYear.setValue(Integer.parseInt(y.format(cal.getTime())));

    }

    private void doInputAction() {
        int month = cbMonth.getSelectedIndex();
        int year = Integer.parseInt(String.valueOf(spYear.getValue()));
//        System.out.println("MY "+month+ " "+year);
        curDay = new GregorianCalendar(year, month, 1);
        showCalendar();
    }

    private class TimeShow implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            lbToday.setText("วันนี้ : " + df.format(new Date()));
        }
    }
}

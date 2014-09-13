package softpos.gui;


import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import softpos.program.ThaiUtil;
import write_to_text.TextWriter;

public class PPrint {
    static SerialPort serialPort;
    static InputStream inputStream;
    static OutputStream outputStream;
    static Enumeration portList;
    static CommPortIdentifier portId;
    static boolean OpenStatus = false;
    static String XLine1 = "";
    static String XLine2 = "";
    static String XLine3 = "";
    static String XLine4 = "";
    static boolean PrintLine4 = true;
    static SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
    static SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    static SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    static DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    static Date date = new Date();
    static int LineCount = 0;
    boolean EJPrint = false;

    public void PPrint() {
    }

    public boolean testPrintTermal(String path_dev){
        PublicVar.HWrec_PrnType = "6";
        if (OpenPrint(path_dev)) {
            InitPrinter();
            Print_Str("********BEGIN TEST-PAGE**********");
            Print_Str("-----device\t status-----");
            Print_Str("-----"+path_dev+"\t connected-----");
            Print_Str("Test Printer Line...1");
            Print_Str("Test Printer Line...2");
            Print_Str("Test Printer Line...3");
            Print_Str("Test Printer Line...4");
            Print_Str("Test Printer Line...5");
            Print_Str("*********END TEST-PAGE**********");
            Print_Str(" ") ;
            Print_Str(" ") ;
            Print_Str(" ") ;
            Print_Str(" ") ;
            Print_Str(" ") ;
            Print_Str(" ") ;
            Print_Str(" ") ;
            CutPaper();
            closePrintExit();
            return true;
        } else {
            return false;
        }
    }
    public boolean testDrawer(String path_dev){
        PublicVar.HWrec_DrType = "1";
        PublicVar.HWrec_DrPort = path_dev;
        if (OpenPrint(path_dev)) {
           OpenDrawer();
           closePrintExit();
        }
        return true ;
    }

    public Boolean OpenPrint(String PortName) {
        if (OpenStatus) {
            return OpenStatus;
        }
        if (PublicVar.HWrec_PrnType.equals("6")) {
            OpenStatus = false;
            portList = CommPortIdentifier.getPortIdentifiers();
            while (portList.hasMoreElements()) {
                portId = (CommPortIdentifier) portList.nextElement();
                //System.out.println(portId.getName());
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(PortName)) {
                        //if (portId.getName().equals("/dev/term/a")) 
                        try {
                            serialPort = (SerialPort) portId.open("SimpleWriteApp", 1000);
                        } catch (PortInUseException e) {
                            PUtility.ShowMsg("Can not Open Port...1");
                        }
                        try {
                            outputStream = serialPort.getOutputStream();
                        } catch (IOException e) {
                            PUtility.ShowMsg("Can not Open Port...2");
                        }
                        try {
                            try {
                                //PUtility.ShowMsg(serialPort.toString());
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            serialPort.setSerialPortParams(19200, //boardrate
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT);
                            OpenStatus = true;
                        } catch (UnsupportedCommOperationException e) {
                            PUtility.ShowMsg("Can not Open Port...3");
                        }
                    }
                }
            }
        } else {
            OpenStatus = false;
            portList = CommPortIdentifier.getPortIdentifiers();

            while (portList.hasMoreElements()) {
                portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(PortName)) {
                        //if (portId.getName().equals("/dev/term/a")) 
                        try {
                            serialPort = (SerialPort) portId.open("SimpleWriteApp", 1000);
                        } catch (PortInUseException e) {
                            PUtility.ShowMsg("Can not Open Port...1");
                        }
                        try {
                            outputStream = serialPort.getOutputStream();
                        } catch (IOException e) {
                            PUtility.ShowMsg("Can not Open Port...2");
                        }
                        try {
                            try {
                                //PUtility.ShowMsg(serialPort.toString());
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            serialPort.setSerialPortParams(9600, //boardrate
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT);
                            OpenStatus = true;
                        } catch (UnsupportedCommOperationException e) {
                            PUtility.ShowMsg("Can not Open Port...3");
                        }
                    }
                }
            }
        }
        //PUtility.ShowMsg("Open OK...");
        return OpenStatus;
    }

    public void OpenDrawer() {
        if ((!PublicVar.HWrec_DrPort.equals("NONE")) & (PublicVar.HWrec_DrType.equals("1"))) {
            String TempRate = PublicVar.HWrec_DrCom.trim();
            byte Rate = 49;
            try {
                int StrRate = Integer.parseInt(TempRate);
                Rate = (byte) StrRate;
            } catch (Exception e) {
            }

            try {
                byte Str[] = {27, 112, Rate}; //init Printer
                outputStream.write(Str);
                //outputStream.flush();
                LineCount = 0;
                PrintLine4 = false;
            } catch (IOException ex) {
                PUtility.ShowError(ex.getMessage());
            }
        }

    }

    public void InitPrinter() {
        //Print_Str(Str.toString()) ;
        try {
            byte Str[] = {27, 64, 1}; //init Printer

            outputStream.write(Str);
            byte Str2[] = {27, 33, 1}; //Set to Nmormal Fornt

            outputStream.write(Str2);
            outputStream.flush();
            LineCount = 0;
            PrintLine4 = false;
        } catch (IOException ex) {
            PUtility.ShowError(ex.getMessage());
        }
    }

    public static void Get_Line(String St) {

        int SetMode[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int list1[] = {232, 233, 234, 235, 236};
        int list2[] = {128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138,
            139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149,
            150, 151, 152, 153, 154, 155, 156, 157, 158,
            209, 212, 213, 214, 215, 219, 220, 221, 222, 231, 232,
            233, 234, 235, 236, 237, 251, 252, 253, 254
        };

        int list4[] = {216, 217};
        char ch;

        String Level1 = "";
        String Level2 = "";
        String Level3 = "";
        String Level4 = "";

        int I = 0;
        while (I <= St.length() - 1) {   // Check TIS Upper }

            ch = St.charAt(I);
            if (SearchArray((int) ch, SetMode) != -1) {
                //Level2=Level2+ch ;
            } else {
                if (SearchArray((int) ch, list1) != -1) {
                    Level1 = Level1.substring(0, Level1.length() - 1) + ch;
                } else {
                    if (SearchArray((int) ch, list2) != -1) {
                        Level2 = Level2.substring(0, Level2.length() - 1) + ch;
                    } else {
                        if (SearchArray((int) ch, list4) != -1) {
                            Level4 = Level4.substring(0, Level4.length() - 1) + ch;

                        } else {
                            Level1 = Level1 + " ";
                            Level2 = Level2 + " ";
                            Level3 = Level3 + ch;
                            Level4 = Level4 + " ";
                        }
                    }
                }
            }
            I++;
        }
        XLine1 = Level1;
        XLine2 = Level2;
        XLine3 = Level3;
        XLine4 = Level4;

    }

    public void Print_Str(String PrintMsg) {
        if (PublicVar.HWrec_PrnType.equals("6")) {
            try {
                XLine1 = PrintMsg + "\n";
                Thread.sleep(50);
                outputStream.write(XLine1.getBytes("tis-620"));
            } catch (InterruptedException ex) {
                Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                PUtility.ShowError(ex.getMessage());
            }
        } else {

            Get_Line(ThaiUtil.Unicode2ASCII(PrintMsg));
            try {
                try {
                    //outputStream.flush();
                    if (XLine1.trim().length() > 0) {
                        XLine1 = ThaiUtil.ASCII2Unicode(XLine1 + "\n");
                        byte LineSpace[] = {27, 51, 15, 1};
                        byte LineSpace2[] = {27, 51, 5, 1};
                        String TempPrint = "\n";
                        if (XLine2.trim().length() > 0) {
                            outputStream.write(LineSpace2);
                        } else {
                            outputStream.write(LineSpace);
                        }
                        Thread.sleep(230);
                        outputStream.write(XLine1.getBytes("tis-620"));
                        LineCount = LineCount + 1;
                        PrintLine4 = false;
                    }
                    if (XLine2.trim().length() > 0) {
                        XLine2 = ThaiUtil.ASCII2Unicode(XLine2 + "\n");
                        byte LineSpace[] = {27, 51, 17, 1};
                        outputStream.write(LineSpace);
                        Thread.sleep(230);
                        outputStream.write(XLine2.getBytes("tis-620"));
                        PrintLine4 = false;
                        LineCount = LineCount + 1;
                    }
                    if (XLine3.trim().length() >= 0) {
                        XLine3 = ThaiUtil.ASCII2Unicode(XLine3 + "\n");
                        byte LineSpace[] = {27, 51, 15, 1};
                        byte LineSpace2[] = {27, 51, 18, 1};
                        String TempPrint = "\n";
                        if (XLine4.trim().length() > 0) {
                            outputStream.write(LineSpace);
                        } else {
                            outputStream.write(LineSpace2);
                        }
                        Thread.sleep(190);
                        outputStream.write(XLine3.getBytes("tis-620"));
                        PrintLine4 = false;
                        LineCount = LineCount + 1;
                    }
                    if (XLine4.trim().length() > 0) {
//                        System.out.println(Integer.toString(XLine4.trim().length()));
                        XLine4 = ThaiUtil.ASCII2Unicode(XLine4 + "\n");
                        byte LineSpace4[] = {27, 51, 0};
                        outputStream.write(LineSpace4);
                        Thread.sleep(230);
                        outputStream.write(XLine4.getBytes("tis-620"));
                        PrintLine4 = true;
                        LineCount = LineCount + 1;
                    } else {
                        PrintLine4 = false;
                    }
                } catch (IOException ex) {
                    //PUtility.ShowError(ex.getMessage());
//                    System.out.println(ex);
                    ex.printStackTrace();
                }
            } catch (InterruptedException ex) {
                PUtility.ShowError(ex.getMessage());
            }
        }
        String TempFile = PublicVar.HWRec_EJDailyPath + "/log" + PublicVar.MacNo + ".gif";
        TextWriter TextWrite = new TextWriter();
        File fObject = new File(TempFile);
        if (!fObject.canRead()) {
            TextWrite.writeToText(TempFile, "");
        }
        TextWrite.writeToText(TempFile, PrintMsg);
        if (EJPrint) {
            String TempBill = PublicVar.HWRec_EJDailyPath + "/tempbill.txt";
            if (!fObject.canRead()) {
                TextWrite.writeToText(TempBill, "");
            }
            TextWrite.writeToText(TempBill, PrintMsg);
        }
    }

    public void closePrintExit() {

        if (serialPort != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                PUtility.ShowError(e.getMessage());
            }
            serialPort.close();
        }
        OpenStatus = false;
    }

    // Metod for Print tility
    public static int SearchArray(int key, int[] list) {
        int ans = -1;
        for (int i = 0; i < list.length; i++) {
            if (key == list[i]) {
                ans = i;
            }
        }
        return ans;
    }

    public void CutPaper() {
        try {
            byte Str[] = {27, 105, 0}; //init Printer

            outputStream.write(Str);
        } catch (IOException ex) {
            PUtility.ShowError(ex.getMessage());
        }
    }
}


package softpos.gui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class FinalcialRec {
    String MacNo1 = "";
    String Macno2 = "" ;
    String Cashier1 = "" ;
    String Cashier2 = "" ;
    Double Dept_Sum = 0.0 ;
    Double Service  = 0.0 ;
    int ServiceCnt = 0 ;
    Double Charge = 0.0 ;
    int ChargeCnt = 0 ;
    
    Double Vip_Disc = 0.0 ;  //As Mem_Disc
    int Vip_DiscCnt = 0 ;
    
    Double Emp_Disc = 0.0 ;
    int Emp_DiscCnt = 0 ;
    
    Double Fast_Disc = 0.0 ;
    int Fast_DiscCnt = 0 ;
    
    Double Train_Disc =0.0 ;
    int Train_DiscCnt = 0;
    
    Double Sub_Disc = 0.0 ;
    int Sub_DiscCnt = 0;
    
    Double Gen_Refund = 0.0;
    int Gen_RefundCnt = 0;
    
    Double Cupon_Disc = 0.0 ;
    int Cupon_DiscCnt = 0;
    
    Double Promotion = 0.0 ;
    int PromotionCnt = 0;
    
    Double Spacial = 0.0 ;
    int SpacialCnt = 0;
    
    Double Item_Disc = 0.0 ;
    int Item_DiscCnt = 0;
    
    Double Net_Sale = 0.0 ;
    Double Cash  = 0.0 ;
    int CashCnt = 0;
    Double Gift = 0.0 ;
    int GiftCnt =0;
    Double Earnest = 0.0 ;
    int EarnestCnt = 0;
    Double ArPayment = 0.0 ;
    int ArPaymentCnt = 0 ;
    Double Credit_Card = 0.0 ;
    int Credit_CardCnt = 0;
    Double Paid_In = 0.0 ;
    int Paid_InCnt = 0;
    Double Paid_Out = 0.0 ;
    int Paid_OutCnt = 0;
    Double Cash_In_Draw =0.0 ;
    Double SaleVat = 0.0 ;
    Double SaleNonVat = 0.0 ;
    Double VatAmt = 0.0 ;
    int CntBill = 0 ;
    String StBill = "" ;
    String SpBill = "" ;
    int CntBillVoid = 0 ;
    Double AmtBillVoid = 0.0 ;
    Double VoidValue = 0.0 ;
    int CntVoid = 0 ;
    Double Eat_In_Amt = 0.0 ;
    Double Take_AwayAmt = 0.0 ;
    Double DeliveryAmt = 0.0 ;
    Double PintoAmt = 0.0 ;
    Double WholeAmt = 0.0 ;
    Double Food = 0.0 ;
    Double Drink = 0.0 ;
    Double Product = 0.0 ;
    int Customer = 0;
    int Eat_In_Cnt = 0 ;
    int Take_AwayCnt = 0;
    int DeliveryCnt = 0;
    int PintoCnt = 0 ;
    int WholeCnt = 0 ;
    int Eat_In_Cust = 0 ;
    int Take_AwayCust = 0;
    int DeliveryCust = 0;
    int PintoCust = 0 ;
    int WholeCust = 0 ;
    int NMist = 0;
    Double Mist = 0.0 ;
    Double Eat_In_Net = 0.0 ;
    Double Take_AwayNet = 0.0 ;
    Double DeliveryNet = 0.0 ;
    Double PintoNet = 0.0 ;
    Double WholeNet = 0.0 ;
    
    public void ClearRec() {
        MacNo1 = "";
        Macno2 = "" ;
        Cashier1 = "" ;
        Cashier2 = "" ;
        Dept_Sum = 0.0 ;
        Service  = 0.0 ;
        ServiceCnt = 0 ;
        Charge = 0.0 ;
        ChargeCnt = 0 ;
    
        Vip_Disc = 0.0 ;  //As Mem_Disc
        Vip_DiscCnt = 0 ;
    
        Emp_Disc = 0.0 ;
        Emp_DiscCnt = 0 ;
    
        Fast_Disc = 0.0 ;
        Fast_DiscCnt = 0 ;
    
        Train_Disc =0.0 ;
        Train_DiscCnt = 0;
    
        Sub_Disc = 0.0 ;
        Sub_DiscCnt = 0;
    
        Gen_Refund = 0.0;
        Gen_RefundCnt = 0;
    
        Cupon_Disc = 0.0 ;
        Cupon_DiscCnt = 0;
    
        Promotion = 0.0 ;
        PromotionCnt = 0;
    
        Spacial = 0.0 ;
        SpacialCnt = 0;
    
        Item_Disc = 0.0 ;
        Item_DiscCnt = 0;
    
        Net_Sale = 0.0 ;
        Cash  = 0.0 ;
        CashCnt = 0;
        Gift = 0.0 ;
        GiftCnt =0;
        Earnest = 0.0 ;
        EarnestCnt = 0;
        ArPayment = 0.0 ;
        ArPaymentCnt = 0 ;
        Credit_Card = 0.0 ;
        Credit_CardCnt = 0;
        Paid_In = 0.0 ;
        Paid_InCnt = 0;
        Paid_Out = 0.0 ;
        Paid_OutCnt = 0;
        Cash_In_Draw =0.0 ;
        SaleVat = 0.0 ;
        SaleNonVat = 0.0 ;
        VatAmt = 0.0 ;
        CntBill = 0 ;
        StBill = "" ;
        SpBill = "" ;
        CntBillVoid = 0 ;
        AmtBillVoid = 0.0 ;
        VoidValue = 0.0 ;
        CntVoid = 0 ;
        Eat_In_Amt = 0.0 ;
        Take_AwayAmt = 0.0 ;
        DeliveryAmt = 0.0 ;
        PintoAmt = 0.0 ;
        WholeAmt = 0.0 ;
        Food = 0.0 ;
        Drink = 0.0 ;
        Product = 0.0 ;
        Customer = 0;
        Eat_In_Cnt = 0 ;
        Take_AwayCnt = 0;
        DeliveryCnt = 0;
        PintoCnt = 0 ;
        WholeCnt = 0 ;
        Eat_In_Cust = 0 ;
        Take_AwayCust = 0;
        DeliveryCust = 0;
        PintoCust = 0 ;
        WholeCust = 0 ;
        NMist = 0;
        Mist = 0.0 ;
        Eat_In_Net = 0.0 ;
        Take_AwayNet = 0.0 ;
        DeliveryNet = 0.0 ;
        PintoNet = 0.0 ;
        WholeNet = 0.0 ;
}
}



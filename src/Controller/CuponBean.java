/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 *
 * @author root
 */
public class CuponBean {
private String cucode = "";
private String cuName = "";
private String cuBegin ="";
private String cuEnd = "";
private String cuStrDay = "";
private String cuType ="";
private Float cuADisc = 0.00f ;
private Float cuADiscBath = 0.00f;
private Float cuBDisc = 0.00f;
private Float cuBDiscBath = 0.00f;
private Float cuDisc = 0.00f;
private Float cuDiscBath = 0.00f;
private String chkMember ="";
private String barcode = "" ;
private String strtype = "" ;
private Float salemin=0.00f ;
private String fixbranch = "" ;
private String ReduleDiscount ;
private String ReduleService ;


    public String getCucode() {
        return cucode;
    }
    public String getBarcode() {
        return barcode;
    }
    public String getstrtype() {
        return strtype;
    }
    public Float getsalemin() {
        return salemin ;
    }
    public String getfixbranch() {
        return fixbranch ;
    }
    public void setCucode(String cucode) {
        this.cucode = cucode;
    }
    public void setBarcode(String Barcode) {
        this.barcode = Barcode;
    }
    public void setstrtype(String strtype) {
        this.strtype = strtype;
    }
    public void setsalemin(Float salemin) {
        this.salemin = salemin;
    }

    public void setfixbranch(String fixbranch) {
       this.fixbranch = fixbranch ;
    }

    public String getCuName() {
        return cuName;
    }
    public String getReduleDiscount() {
        return ReduleDiscount;
    }
    public String getReduleService() {
        return ReduleService;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    public String getCuBegin() {
        return cuBegin;
    }

    public void setCuBegin(String cuBegin) {
        this.cuBegin = cuBegin;
    }

    public String getCuEnd() {
        return cuEnd;
    }

    public void setCuEnd(String cuEnd) {
        this.cuEnd = cuEnd;
    }

    public String getCuStrDay() {
        return cuStrDay;
    }

    public void setCuStrDay(String cuStrDay) {
        this.cuStrDay = cuStrDay;
    }

    public String getCuType() {
        return cuType;
    }

    public void setCuType(String cuType) {
        this.cuType = cuType;
    }

    public Float getCuADisc() {
        return cuADisc;
    }

    public void setCuADisc(Float cuADisc) {
        this.cuADisc = cuADisc;
    }

    public Float getCuADiscBath() {
        return cuADiscBath;
    }

    public void setCuADiscBath(Float cuADiscBath) {
        this.cuADiscBath = cuADiscBath;
    }

    public Float getCuBDisc() {
        return cuBDisc;
    }

    public void setCuBDisc(Float cuBDisc) {
        this.cuBDisc = cuBDisc;
    }

    public Float getCuBDiscBath() {
        return cuBDiscBath;
    }

    public void setCuBDiscBath(Float cuBDiscBath) {
        this.cuBDiscBath = cuBDiscBath;
    }

    public Float getCuDisc() {
        return cuDisc;
    }

    public void setCuDisc(Float cuDisc) {
        this.cuDisc = cuDisc;
    }

    public Float getCuDiscBath() {
        return cuDiscBath;
    }

    public void setCuDiscBath(Float cuDiscBath) {
        this.cuDiscBath = cuDiscBath;
    }

    public String getChkMember() {
        return chkMember;
    }

    public void setChkMember(String chkMember) {
        this.chkMember = chkMember;
    }
    public void setReduleDiscount(String ReduleDiscount) {
        this.ReduleDiscount = ReduleDiscount;
    }
    public void setReduleService(String ReduleService) {
        this.ReduleService = ReduleService;
    }
}

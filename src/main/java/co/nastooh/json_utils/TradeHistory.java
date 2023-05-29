package co.nastooh.json_utils;

import com.google.gson.Gson;
import org.json.JSONObject;

import javax.persistence.Entity;


public class TradeHistory {
    private float pPbSeaCotJ;
    private String insCode;
    private float pTran;
    private int xqVarPJDrPRf;
    private int nTran;
    private int canceled;
    private String iSensVarP;
    private int iAnuTran;
    private int qTitNgJ;
    private int hEven;
    private int dEven;
    private int pPhSeaCotJ;
    private long qTitTran;

    public float getpPbSeaCotJ() {
        return pPbSeaCotJ;
    }

    public void setpPbSeaCotJ(float pPbSeaCotJ) {
        this.pPbSeaCotJ = pPbSeaCotJ;
    }

    public String getInsCode() {
        return insCode;
    }

    public void setInsCode(String insCode) {
        this.insCode = insCode;
    }

    public float getpTran() {
        return pTran;
    }

    public void setpTran(float pTran) {
        this.pTran = pTran;
    }

    public int getXqVarPJDrPRf() {
        return xqVarPJDrPRf;
    }

    public void setXqVarPJDrPRf(int xqVarPJDrPRf) {
        this.xqVarPJDrPRf = xqVarPJDrPRf;
    }

    public int getnTran() {
        return nTran;
    }

    public void setnTran(int nTran) {
        this.nTran = nTran;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public String getiSensVarP() {
        return iSensVarP;
    }

    public void setiSensVarP(String iSensVarP) {
        this.iSensVarP = iSensVarP;
    }

    public int getiAnuTran() {
        return iAnuTran;
    }

    public void setiAnuTran(int iAnuTran) {
        this.iAnuTran = iAnuTran;
    }

    public int getqTitNgJ() {
        return qTitNgJ;
    }

    public void setqTitNgJ(int qTitNgJ) {
        this.qTitNgJ = qTitNgJ;
    }

    public int gethEven() {
        return hEven;
    }

    public void sethEven(int hEven) {
        this.hEven = hEven;
    }

    public int getdEven() {
        return dEven;
    }

    public void setdEven(int dEven) {
        this.dEven = dEven;
    }

    public int getpPhSeaCotJ() {
        return pPhSeaCotJ;
    }

    public void setpPhSeaCotJ(int pPhSeaCotJ) {
        this.pPhSeaCotJ = pPhSeaCotJ;
    }

    public long getqTitTran() {
        return qTitTran;
    }

    public void setqTitTran(long qTitTran) {
        this.qTitTran = qTitTran;
    }

    @Override
    public String toString() {
        return "TradeHistory{" +
                "pTran=" + pTran +
                ", hEven=" + hEven +
                ", qTitTran=" + qTitTran +
                '}';
    }

    public static TradeHistory[] getTheList(String findTrades){

        // parsing the String result to an array of JSON:
        JSONObject findDaysJson = new JSONObject(findTrades);
        String closingPriceString = findDaysJson.get("tradeHistory").toString();
        Gson gson = new Gson();

        return gson.fromJson(closingPriceString, TradeHistory[].class);
    }
}

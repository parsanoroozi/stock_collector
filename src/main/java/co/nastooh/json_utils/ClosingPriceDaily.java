package co.nastooh.json_utils;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClosingPriceDaily {
    private float priceChange;
    private float priceMin;
    private float priceMax;
    private float priceYesterday;
    private float priceFirst;
    private boolean last;
    private int id;
    private String insCode;
    private int dEven;
    private int hEven;
    private float pClosing;
    private boolean iClose;
    private boolean yClose;
    private int pDrCotVal;
    private long zTotTran;
    private long qTotTran5J;
    private long qTotCap;

    public float getPriceMin() {
        return priceMin;
    }

    public float getPriceMax() {
        return priceMax;
    }

    public float getPriceYesterday() {
        return priceYesterday;
    }

    public float getPriceFirst() {
        return priceFirst;
    }

    public float getpClosing() {
        return pClosing;
    }

    public long getqTotTran5J() {
        return qTotTran5J;
    }

    public long getqTotCap() {
        return qTotCap;
    }

    public int getdEven() {
        return dEven;
    }

    public long getzTotTran() {
        return zTotTran;
    }

    @Override
    public String toString() {
        return "ClosingPriceDaily{" +
                "priceMin=" + priceMin +
                ", priceMax=" + priceMax +
                ", priceYesterday=" + priceYesterday +
                ", priceFirst=" + priceFirst +
                ", dEven=" + dEven +
                ", pClosing=" + pClosing +
                ", zTotTran=" + zTotTran +
                ", qTotTran5J=" + qTotTran5J +
                ", qTotCap=" + qTotCap +
                '}';
    }

    public static ClosingPriceDaily[] getTheList(String findDays){

        // parsing the String result to an array of JSON:
        JSONObject findDaysJson = new JSONObject(findDays);
        String closingPriceString = findDaysJson.get("closingPriceDaily").toString();
        Gson gson = new Gson();

        return gson.fromJson(closingPriceString, ClosingPriceDaily[].class);
    }
}

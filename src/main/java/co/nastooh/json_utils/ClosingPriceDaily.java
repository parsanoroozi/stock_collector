package co.nastooh.json_utils;

public class ClosingPriceDaily {
    private int priceChange;
    private int priceMin;
    private int priceMax;
    private int priceYesterday;
    private int priceFirst;
    private boolean last;
    private int id;
    private String insCode;
    private int dEven;
    private int hEven;
    private int pClosing;
    private boolean iClose;
    private boolean yClose;
    private int pDrCotVal;
    private int zTotTran;
    private int qTotTran5J;
    private long qTotCap;

    public int getdEven() {
        return dEven;
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
}

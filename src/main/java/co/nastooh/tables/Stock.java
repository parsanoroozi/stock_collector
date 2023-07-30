package co.nastooh.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="stock")
public class Stock {
    @Id
    private String id;
    private String isin;
    private String stock_name;
    private String company_name;
    private int last_update;
    private float first_price;
    private float last_price;
    private float transaction_price;
    private long transaction_count;
    private long transaction_volume;
    private long transaction_value;
    private float day_min_price;
    private float day_max_price;
    private float yesterday_price;
    private long base_volume;
    private float max_allowed_price;
    private float min_allowed_price;
    private long stock_count;
    @OneToMany(mappedBy = "stock")
    private List<Daily> days = new ArrayList<>();

    public List<Daily> getDays() {
        return days;
    }

    public void setDays(List<Daily> days) {
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setLast_update(int last_update) {
        this.last_update = last_update;
    }

    public void setFirst_price(float first_price) {
        this.first_price = first_price;
    }

    public void setLast_price(float last_price) {
        this.last_price = last_price;
    }

    public void setTransaction_price(float transaction_price) {
        this.transaction_price = transaction_price;
    }

    public void setTransaction_count(long transaction_count) {
        this.transaction_count = transaction_count;
    }

    public void setTransaction_volume(long transaction_volume) {
        this.transaction_volume = transaction_volume;
    }

    public void setTransaction_value(long transaction_value) {
        this.transaction_value = transaction_value;
    }

    public void setDay_min_price(float day_min_price) {
        this.day_min_price = day_min_price;
    }

    public void setDay_max_price(float day_max_price) {
        this.day_max_price = day_max_price;
    }

    public void setYesterday_price(float yesterday_price) {
        this.yesterday_price = yesterday_price;
    }

    public void setBase_volume(long base_volume) {
        this.base_volume = base_volume;
    }

    public void setMax_allowed_price(float max_allowed_price) {
        this.max_allowed_price = max_allowed_price;
    }

    public void setMin_allowed_price(float min_allowed_price) {
        this.min_allowed_price = min_allowed_price;
    }

    public void setStock_count(long stock_count) {
        this.stock_count = stock_count;
    }

    @Override
    public String toString() {
        return "Stocks{" +"\n" +
                "id=" + id + "\n" +
                ", isin='" + isin + '\'' +"\n" +
                ", stock_name='" + stock_name + '\'' +"\n" +
                ", company_name='" + company_name + '\'' +"\n" +
                ", last_update=" + last_update +"\n" +
                ", first_price=" + first_price +"\n" +
                ", last_price=" + last_price +"\n" +
                ", transaction_price=" + transaction_price +"\n" +
                ", transaction_count=" + transaction_count +"\n" +
                ", transaction_volume=" + transaction_volume +"\n" +
                ", transaction_value=" + transaction_value +"\n" +
                ", day_min_price=" + day_min_price +"\n" +
                ", day_max_price=" + day_max_price +"\n" +
                ", yesterday_price=" + yesterday_price +"\n" +
                ", base_volume=" + base_volume +"\n" +
                ", max_allowed_price=" + max_allowed_price +"\n" +
                ", min_allowed_price=" + min_allowed_price +"\n" +
                ", stock_count=" + stock_count +"\n" +
                '}';
    }
}

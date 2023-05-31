package co.nastooh.tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="daily")
public class Daily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float min_price;
    private float max_price;
    private float yesterday_price;
    private float first_price;
    private float last_price;
    private int date;
    private long transaction_count;
    private long transaction_volume;
    private long transaction_value;
    @ManyToOne
    private Stock stock;
    @OneToMany(mappedBy = "daily")
    private List<Trade> trades = new ArrayList<>();

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public long getId() {
        return id;
    }

    public float getMin_price() {
        return min_price;
    }

    public void setMin_price(float min_price) {
        this.min_price = min_price;
    }

    public float getMax_price() {
        return max_price;
    }

    public void setMax_price(float max_price) {
        this.max_price = max_price;
    }

    public float getYesterday_price() {
        return yesterday_price;
    }

    public void setYesterday_price(float yesterday_price) {
        this.yesterday_price = yesterday_price;
    }

    public float getFirst_price() {
        return first_price;
    }

    public void setFirst_price(float first_price) {
        this.first_price = first_price;
    }

    public float getLast_price() {
        return last_price;
    }

    public void setLast_price(float last_price) {
        this.last_price = last_price;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public long getTransaction_count() {
        return transaction_count;
    }

    public void setTransaction_count(long transaction_count) {
        this.transaction_count = transaction_count;
    }

    public long getTransaction_volume() {
        return transaction_volume;
    }

    public void setTransaction_volume(long transaction_volume) {
        this.transaction_volume = transaction_volume;
    }

    public long getTransaction_value() {
        return transaction_value;
    }

    public void setTransaction_value(long transaction_value) {
        this.transaction_value = transaction_value;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "id='" + id + '\'' +
                ", min_price=" + min_price +
                ", max_price=" + max_price +
                ", yesterday_price=" + yesterday_price +
                ", first_price=" + first_price +
                ", last_price=" + last_price +
                ", date=" + date +
                ", transaction_count=" + transaction_count +
                ", transaction_volume=" + transaction_volume +
                ", transaction_value=" + transaction_value +
                ", stock=" + stock +
                ", trades=" + trades +
                '}';
    }
}

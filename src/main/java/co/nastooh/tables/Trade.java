package co.nastooh.tables;

import javax.persistence.*;

@Entity
@Table(name="trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    private int time;
    private long transaction_volume;
    @ManyToOne
    private Daily daily;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getTransaction_volume() {
        return transaction_volume;
    }

    public void setTransaction_volume(long transaction_volume) {
        this.transaction_volume = transaction_volume;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", time=" + time +
                ", transaction_volume=" + transaction_volume +
                ", daily=" + daily +
                '}';
    }
}

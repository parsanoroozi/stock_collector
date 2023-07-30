package co.nastooh.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="utils")
public class UtilsTable {
    @Id
    private int id;
    private boolean history_finished;
    private Long last_daily_update;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHistory_finished() {
        return history_finished;
    }

    public void setHistory_finished(boolean history_finished) {
        this.history_finished = history_finished;
    }

    public Long getLast_daily_update() {
        return last_daily_update;
    }

    public void setLast_daily_update(Long last_daily_update) {
        this.last_daily_update = last_daily_update;
    }

    @Override
    public String toString() {
        return "UtilsTable{" +
                "id=" + id +
                ", history_finished=" + history_finished +
                ", last_daily_update=" + last_daily_update +
                '}';
    }
}

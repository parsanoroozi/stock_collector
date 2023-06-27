package co.nastooh.engines.realtime_engine;


import co.nastooh.crawlers.StocksCrawler;
import co.nastooh.tables.Stock;
import co.nastooh.transactions.StockTransaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RealTimeCollector {

    public static void update(){

        // fetching stocks:
        ArrayList<Stock> stockList = StocksCrawler.collectStocks();

        //  inserting stocks into the database or updating them if they already exist:
        StockTransaction.insert(stockList);

        // updating detailed daily:
        DailyUpdate.run(stockList);
    }

    public static void run(){
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                //update
                RealTimeCollector.update();
            }
        },0,120000);
    }

    public static Long updateEvery2Min(Long lastUpdateTime){
        // defining the current time:
        Long currentTime = Instant.now().getEpochSecond();

        if (currentTime - lastUpdateTime > 120){
            // update
            RealTimeCollector.update();
            return currentTime;
        }
        return lastUpdateTime;
    }
}

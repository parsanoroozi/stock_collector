package co.nastooh.engines.realtime_engine;

import co.nastooh.crawlers.DailiesCrawler;
import co.nastooh.crawlers.TradesCrawler;
import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import co.nastooh.transactions.UtilsTransaction;
import co.nastooh.transactions.DailyTransaction;
import co.nastooh.transactions.TradeTransaction;

import java.time.Instant;
import java.util.ArrayList;

public class DailyUpdate {

    public static void run(ArrayList<Stock> stockList){

        // defining the current time:
        long currentTime = Instant.now().getEpochSecond();

        // if it has been more than one day since last update:
        if(currentTime - UtilsTransaction.lastDetailedUpdate() > 86400){

            // modify last update time:
            UtilsTransaction.saveNewUpdateDate(currentTime);

            for (Stock stock : stockList){

                addDailiesWithTrades(stock);
            }
        }

    }

    private static void addDailiesWithTrades(Stock stock){

        // fetching a stocks trading day:
        ArrayList<Daily> dailyList = DailiesCrawler.collectDailies(stock);

        // continue adding new days till reaching an already added record
        // we iterate from the first index because the new dates comes in first:
        for (Daily daily : dailyList){

            // checking if the daily exist:
            if (DailyTransaction.checkIfExists(daily)){
                // we have reached to a repeated point, so we quit:
                break;

            }else{
                // inserting daily:
                DailyTransaction.insertOneDaily(daily);

                // skip if the day has no trades:
                if (daily.getTransaction_volume() == 0) continue;

                // fetching a days trades:
                ArrayList<Trade> tradeList = TradesCrawler.collectTrades(daily);
                // inserting the trades of a date into the database:
                TradeTransaction.run(tradeList);
            }
        }
    }
}

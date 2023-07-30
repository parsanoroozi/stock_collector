package co.nastooh.engines.historical_engine;

import co.nastooh.crawlers.DailiesCrawler;
import co.nastooh.crawlers.TradesCrawler;
import co.nastooh.engines.realtime_engine.RealTimeCollector;
import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import co.nastooh.transactions.DailyTransaction;
import co.nastooh.transactions.StockTransaction;
import co.nastooh.transactions.TradeTransaction;

import java.util.ArrayList;

public class HistoryCollector {
    public static void run()  {

        // getting stocks:
        ArrayList<Stock> stockList = StockTransaction.getStockList();

        // initiating the lastUpdate time for interrupting every 2 minutes and update stocks
        Long lastUpdateTime = 0L;

        // iterating over stocks(from the last added stock...):
        for (int i = GetIndex.stock(stockList) ; i < stockList.size() ; i++){
            // stock report log:
            System.out.println("stock: " + (i+1) + "/" + stockList.size());

            // fetching a stocks trading day:
            ArrayList<Daily> dailyList = DailiesCrawler.collectDailies(stockList.get(i));
            //  inserting a stocks trading days into the database:
            DailyTransaction.insert(dailyList);

            // go through days and save trades:
            for (int j = GetIndex.daily(dailyList) ; j < dailyList.size() ; j++){
                // daily report log:
                System.out.println("day: "+(j+1) +"/"+dailyList.size()
                        +" date: " + dailyList.get(j).getDate()
                        +", stock: "+(i+1)+"/"+stockList.size()
                        +" id: " + stockList.get(i).getId());

                // updating the stocks if it has been 2 minutes since the last update:
                lastUpdateTime = RealTimeCollector.updateEvery2Min(lastUpdateTime);

                // skip if the day has no trades:
                if (dailyList.get(j).getTransaction_volume() == 0) continue;

                // fetching a days trades:
                ArrayList<Trade> tradeList = TradesCrawler.collectTrades(dailyList.get(j));

                // skip if the tradeList length is 0:
                if (tradeList.size() == 0) continue;

                // inserting the trades of a date into the database:
                TradeTransaction.insert(tradeList);
            }
            System.out.println();
        }
        System.out.println("Historical stock collecting has been finished successfully!");
    }
}

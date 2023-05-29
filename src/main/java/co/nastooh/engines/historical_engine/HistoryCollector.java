package co.nastooh.engines.historical_engine;

import co.nastooh.crawlers.DailiesCrawler;
import co.nastooh.crawlers.StocksCrawler;
import co.nastooh.crawlers.TradesCrawler;
import co.nastooh.engines.realtime_engine.RealTimeCollector;
import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import co.nastooh.transactions.InsertOrUpdateDailies;
import co.nastooh.transactions.InsertOrUpdateStocks;
import co.nastooh.transactions.InsertOrUpdateTrades;

import java.util.ArrayList;

public class HistoryCollector {
    public static void run()  {

        // getting stocks
        ArrayList<Stock> stockList = StocksCrawler.collectStocks();
        //  inserting stocks into the database or updating them if they already exist:
        InsertOrUpdateStocks.run(stockList);

        // initiating the lastUpdate time for interrupting every 2 minutes and update stocks
        Long lastUpdateTime = 0L;

        // defining log utils:
        long StocksRecordsCount;
        long TotalRecordsCount = 0;
        int stocksNumber = 0;

        // iterating over stocks:
        for (Stock stock : stockList){

            StocksRecordsCount = 0;
            stocksNumber +=1;

            // fetching a stocks trading day:
            ArrayList<Daily> dailyList = DailiesCrawler.collectDailies(stock);
            //  inserting a stocks trading days into the database:
            InsertOrUpdateDailies.run(dailyList);

            System.out.println("number of days : " + dailyList.size());
            for (Daily daily : dailyList){

                // updating the stocks if it has been 2 minutes since the last update:
                lastUpdateTime = RealTimeCollector.updateEvery2Min(lastUpdateTime);

                // skip if the day has no trades:
                if (daily.getTransaction_volume() == 0) continue;

                // fetching a days trades:
                ArrayList<Trade> tradeList = TradesCrawler.collectTrades(daily);
                // inserting the trades of a date into the database:
                InsertOrUpdateTrades.run(tradeList);

                // updating counters:
                StocksRecordsCount += tradeList.size();
                TotalRecordsCount += tradeList.size();
            }
            System.out.println("Stock number: " + stocksNumber);
            System.out.println("change stock: " + stock.getId());
            System.out.println("StocksRecordsCount:  " + StocksRecordsCount);
            System.out.println("TOTAL RECORD COUNT  ----->  " + TotalRecordsCount);
            System.out.println();
        }
        System.out.println("stocks count: " + stockList.size());
    }
}

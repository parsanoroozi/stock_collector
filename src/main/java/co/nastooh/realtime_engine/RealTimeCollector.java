package co.nastooh.realtime_engine;


import co.nastooh.crawlers.StocksCrawler;
import co.nastooh.tables.Stock;
import co.nastooh.transactions.InsertOrUpdateStocks;

import java.util.ArrayList;

public class RealTimeCollector {
    public static void realTimeCrawler(){

        // fetching stocks:
        ArrayList<Stock> stocks = StocksCrawler.collectStocks();

        //  inserting stocks into the database or updating them if they already exist:
        InsertOrUpdateStocks.run(stocks);

    }
}

package co.nastooh.engines.historical_engine;

import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.transactions.DailyTransaction;

import java.util.ArrayList;

public class GetStockIndex {

    public static int run(ArrayList<Stock> stockList){

        // get the last daily added:
        Daily lastDaily = DailyTransaction.getLastDailyAdded();
        // return the index of stock we're looking for:
        if (!stockList.contains(lastDaily.getStock())) return 0;
        else return stockList.indexOf(lastDaily.getStock());
    }
}

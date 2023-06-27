package co.nastooh.engines.historical_engine;

import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;
import co.nastooh.tables.Trade;
import co.nastooh.transactions.DailyTransaction;
import co.nastooh.transactions.TradeTransaction;

import java.util.ArrayList;
import java.util.Objects;

public class GetIndex {

    public static int stock(ArrayList<Stock> stockList){

        // get the last daily added:
        Daily lastDaily = DailyTransaction.getLastDailyAdded();

        // return the index of stock we're looking for:
        int index = 0;
        if (lastDaily != null){
            for(int i = 0; i < stockList.size(); i++){
                if (Objects.equals(stockList.get(i).getId(), lastDaily.getStock().getId())){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public static int daily(ArrayList<Daily> dailyList){


        // get the last Trade added:
        Trade lastTrade = TradeTransaction.getLastTradeAdded();

        // return the index of daily we're looking for:
        int index = 0;
        if (lastTrade != null){
            for(int i = 0; i < dailyList.size(); i++){
                if (Objects.equals(dailyList.get(i).getDate(), lastTrade.getDaily().getDate())){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }
}

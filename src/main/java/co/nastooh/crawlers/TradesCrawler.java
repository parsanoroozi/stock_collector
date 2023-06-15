package co.nastooh.crawlers;

import co.nastooh.json_utils.TradeHistory;
import co.nastooh.tables.Daily;
import co.nastooh.tables.Trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TradesCrawler {

    public static ArrayList<Trade> collectTrades(Daily daily){

        // fetching trades of a day:
        String tradesDate = Integer.toString(daily.getDate());
        String tradesApiRes = Utils.fetch(Utils.getFindTradesURL(daily.getStock().getId(),tradesDate));

        // return if the fetch result is empty:
        if (tradesApiRes.length() == 0) return new ArrayList<>();

        // parsing the string result into a JSON array:
        System.out.println("trades api result: " + tradesApiRes.substring(0,19));
        TradeHistory[] dayTrades = TradeHistory.getTheList(tradesApiRes);

        // defining the trade list:
        ArrayList<Trade> tradeList = new ArrayList<>();

        // iterating though json array and get every trade object
        for(TradeHistory item : dayTrades) {
            Trade trade = new Trade();
            // filling trade fields:
            trade.setPrice(item.getpTran());
            trade.setTime(item.gethEven());
            trade.setTransaction_volume(item.getqTitTran());
            trade.setDaily(daily);
            tradeList.add(trade);
        }

        // returning the trade list:
        return tradeList;
    }
}

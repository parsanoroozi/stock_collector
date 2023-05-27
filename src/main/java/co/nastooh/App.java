package co.nastooh;


import co.nastooh.json_utils.ClosingPriceDaily;
import co.nastooh.json_utils.TradeHistory;
import co.nastooh.playground.FindAllCount;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import com.google.gson.Gson;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
//        System.setProperty("http.proxyhost", "127.0.0.1");
//        System.setProperty("http.proxyport", "3128");
//        // getting stocks
//        String StockListRes = null;
//        try {
//            StockListRes = Jsoup.connect("http://old.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0")
//                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
//                    .ignoreContentType(true).get().text();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String[] data = StockListRes.split("@")[2].split(";");
//        for (String item : data){
//            System.out.println(item.split(",")[0] + " " + item.split(",")[1] + " " + item.split(",")[2]);
//        }
//        System.out.println(data.length);
//
//        // getting days:
//        System.out.println("getting Days");
//        String StockID = "32244060193084591";
//        String findDaysURL = "http://cdn.tsetmc.com/api/ClosingPrice/GetClosingPriceDailyList/"+StockID+"/100000";
//        String findDays = null;
//        try {
//            findDays = Jsoup.connect(findDaysURL)
//                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
//                    .ignoreContentType(true).get().text();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        JSONObject findDaysJson = new JSONObject(findDays);
//        String closingPriceString = findDaysJson.get("closingPriceDaily").toString();
//        Gson gson = new Gson();
//        ClosingPriceDaily[] closingPriceDailyArray = gson.fromJson(closingPriceString, ClosingPriceDaily[].class);
//        for (ClosingPriceDaily obj : closingPriceDailyArray){
//            System.out.println(obj);
//        }
//        System.out.println(closingPriceDailyArray.length);
//
//        // getting trades:
//        System.out.println("getting Trades");
//        String TradesDate = Integer.toString(closingPriceDailyArray[1].getdEven());
//        String findTradesURL = "http://cdn.tsetmc.com/api/Trade/GetTradeHistory/"+StockID+"/"+TradesDate+"/false";
//        String findTrades = null;
//        try {
//            findTrades = Jsoup.connect(findTradesURL)
//                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
//                    .ignoreContentType(true).get().text();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("after fetch");
//        JSONObject findTradesJson = new JSONObject(findTrades);
//        String tradeHistoryString = findTradesJson.get("tradeHistory").toString();
//        TradeHistory[] TradeHistoryArray = gson.fromJson(tradeHistoryString, TradeHistory[].class);
//        for (TradeHistory obj : TradeHistoryArray){
//            System.out.println(obj);
//        }
//        System.out.println(TradeHistoryArray.length);

        FindAllCount.main();

    }
}

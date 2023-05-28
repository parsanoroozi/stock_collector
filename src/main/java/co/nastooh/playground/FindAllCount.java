package co.nastooh.playground;

import co.nastooh.json_utils.ClosingPriceDaily;
import co.nastooh.json_utils.TradeHistory;
import co.nastooh.tables.Stock;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class FindAllCount {
    public static void main(){


        System.setProperty("http.proxyhost", "127.0.0.1");
        System.setProperty("http.proxyport", "3128");
        // getting stocks
        String StockListRes = null;
        try {
            StockListRes = Jsoup.connect("http://old.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0")
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                    .ignoreContentType(true).get().text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] data = StockListRes.split("@")[2].split(";");
        String StockID = null;
        String findDaysURL = "http://cdn.tsetmc.com/api/ClosingPrice/GetClosingPriceDailyList/"+StockID+"/100000";
        String findDays = null;
        String TradesDate = null;
        String findTradesURL = "http://cdn.tsetmc.com/api/Trade/GetTradeHistory/"+StockID+"/"+TradesDate+"/false";
        String findTrades = null;
        long StocksRecordsCount;
        long TotalRecordsCount = 0;
        int stocksNumber = 0;
        for(String item : data){
            Stock stock = new Stock();
            // filling stock fields:
            stock.setId(item.split(",")[0]);
            stock.setIsin(item.split(",")[1]);
            stock.setStock_name(item.split(",")[2]);
            stock.setCompany_name(item.split(",")[3]);
            stock.setLast_update(parseInt(item.split(",")[4]));
            stock.setFirst_price(parseFloat(item.split(",")[5]));
            stock.setLast_price(parseFloat(item.split(",")[6]));
            stock.setTransaction_price(parseFloat(item.split(",")[7]));
            stock.setTransaction_count(parseLong(item.split(",")[8]));
            stock.setTransaction_volume(parseLong(item.split(",")[9]));
            stock.setTransaction_value(parseLong(item.split(",")[10]));
            stock.setDay_min_price(parseFloat(item.split(",")[11]));
            stock.setDay_max_price(parseFloat(item.split(",")[12]));
            stock.setYesterday_price(parseFloat(item.split(",")[13]));
            stock.setBase_volume(parseLong(item.split(",")[15]));
            stock.setMax_allowed_price(parseFloat(item.split(",")[19]));
            stock.setMin_allowed_price(parseFloat(item.split(",")[20]));
            stock.setStock_count(parseLong(item.split(",")[21]));
            System.out.println(stock);
        }


        for (String item : data){
            Random rand = new Random();
            String hostNumber =
                    Integer.toString(rand.nextInt(255))+"."
                    +Integer.toString(rand.nextInt(255))
                    +"."+Integer.toString(rand.nextInt(255))+"."
                    +Integer.toString(rand.nextInt(255))+".";
            System.setProperty("http.proxyhost", hostNumber);
            String portNumber = Integer.toString(rand.nextInt(1024));
            System.setProperty("http.proxyport", portNumber);
            System.out.println("host: " + hostNumber + ", port: " + portNumber);

            StocksRecordsCount = 0;
            stocksNumber +=1;
            System.out.println("Stock nummber: " + stocksNumber);
            System.out.println("change stock: " + item.split(",")[0]);

            StockID = item.split(",")[0];
//            StockID = "70067781851300399";
            findDaysURL = "http://cdn.tsetmc.com/api/ClosingPrice/GetClosingPriceDailyList/"+StockID+"/100000";
            try {
                findDays = Jsoup.connect(findDaysURL)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                        .ignoreContentType(true).get().text();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject findDaysJson = new JSONObject(findDays);
            String closingPriceString = findDaysJson.get("closingPriceDaily").toString();
            Gson gson = new Gson();
            ClosingPriceDaily[] closingPriceDailyArray = gson.fromJson(closingPriceString, ClosingPriceDaily[].class);
            System.out.println("number of days : " + closingPriceDailyArray.length);

            for (ClosingPriceDaily dayObj : closingPriceDailyArray){
                if (dayObj.getzTotTran() == 0) continue;
                System.out.println(dayObj);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

//                System.out.println("change date: " + item.split(",")[2] + " to " + TradesDate);
                TradesDate = Integer.toString(dayObj.getdEven());
                findTradesURL = "http://cdn.tsetmc.com/api/Trade/GetTradeHistory/"+StockID+"/"+TradesDate+"/false";
                try {
                    findTrades = Jsoup.connect(findTradesURL)
                            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                            .ignoreContentType(true).get().text();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JSONObject findTradesJson = new JSONObject(findTrades);
                String tradeHistoryString = findTradesJson.get("tradeHistory").toString();
                TradeHistory[] TradeHistoryArray;
                if(tradeHistoryString.length() > 10) {
                    TradeHistoryArray = gson.fromJson(tradeHistoryString, TradeHistory[].class);
                }else continue;
                for(TradeHistory tradeObj : TradeHistoryArray){
                    System.out.println(tradeObj);
                }
//                System.out.println("records count : " + TradeHistoryArray.length);
                StocksRecordsCount += TradeHistoryArray.length;
                TotalRecordsCount += TradeHistoryArray.length;
//                System.out.println("TOTAL RECORD COUNT : " + TotalRecordsCount);
            }
            System.out.println("StocksRecordsCount:  " + StocksRecordsCount);
            System.out.println("TOTAL RECORD COUNT  ----->  " + TotalRecordsCount);
            System.out.println();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("stocks count: " + data.length);

    }
}

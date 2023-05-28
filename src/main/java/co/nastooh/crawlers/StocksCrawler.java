package co.nastooh.crawlers;

import co.nastooh.tables.Stock;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class StocksCrawler {
    public static ArrayList<Stock> collectStocks(){

        // setting proxy:
        System.setProperty("http.proxyhost", "127.0.0.1");
        System.setProperty("http.proxyport", "3128");

        // fetching stocks:
        String stocksApiRes = null;
        try {
            stocksApiRes = Jsoup.connect("http://old.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0")
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                    .ignoreContentType(true)
                    .get()
                    .text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // parsing the result:
        String[] stockList = stocksApiRes.split("@")[2].split(";");

        // defining the Stock list:
        ArrayList<Stock> stocks = new ArrayList<Stock>();

        // iterating though string array and get every stock object
        for(String item : stockList) {

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
            stocks.add(stock);
        }

        // returning the stock list:
        return stocks;
    }
}

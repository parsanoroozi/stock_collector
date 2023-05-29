package co.nastooh.crawlers;

import co.nastooh.tables.Stock;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class StocksCrawler {
    public static ArrayList<Stock> collectStocks(){

        // fetching stocks:
        String stocksApiRes = Utils.fetch(Utils.getFindStocksURL());

        // parsing the result:
        String[] stockListStr = stocksApiRes.split("@")[2].split(";");

        // defining the Stock list:
        ArrayList<Stock> stockList = new ArrayList<>();

        // iterating though string array and get every stock object
        for(String item : stockListStr) {

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
            stockList.add(stock);
        }

        // returning the stock list:
        return stockList;
    }
}

package co.nastooh.crawlers;

import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    private static WebDriver driver;
    private static String findStocksURL = "http://old.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0";
    private static String findDaysURL;
    private static String findTradesURL;
    private static Random rand = new Random();

    public static void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.silentOutput","true");
        Utils.driver = new ChromeDriver(options);
    }

    public static void turnOffLogs(){
        // turning off hibernate logs:
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.OFF);
        // turning off selenium logs:
        Logger seleniumLogger = Logger.getLogger("org.seleniumhq.selenium");
        seleniumLogger.setLevel(Level.OFF);
    }

    public static String getFindStocksURL() {
        return findStocksURL;
    }

    public static String getFindDaysURL(String StockID) {
        Utils.findDaysURL = "http://cdn.tsetmc.com/api/ClosingPrice/GetClosingPriceDailyList/"+StockID+"/100000";
        return findDaysURL;
    }

    public static String getFindTradesURL(String StockID, String Date) {
        Utils.findTradesURL = "http://cdn.tsetmc.com/api/Trade/GetTradeHistory/"+StockID+"/"+Date+"/false";
        return findTradesURL;
    }

    public static String fetch(String url){

        // setting a new IP address for every request:
        String hostNumber =
                        rand.nextInt(255)+"."
                        +rand.nextInt(255)+"."
                        +rand.nextInt(255)+"."
                        +rand.nextInt(255);
        System.setProperty("http.proxyhost", hostNumber);

        // setting a new Port number for every request:
        String portNumber = Integer.toString(rand.nextInt(1024));
        System.setProperty("http.proxyport", portNumber);
        // fetching the url
        driver.get(url);
        String response = Jsoup.parse(driver.getPageSource()).text();

        // if the server returns 50, we give it a 5s rest and then we request again
        if(response.startsWith("50")){
            while (response.startsWith("50")){

                try {
                    TimeUnit.SECONDS.sleep(5);
                    driver.get(url);
                    response = Jsoup.parse(driver.getPageSource()).text();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return response;
    }
}

package co.nastooh.crawlers;

import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    private static WebDriver driver;
    private static final String findStocksURL = "http://old.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0";
    private static String findDaysURL;
    private static String findTradesURL;
    private static final Random rand = new Random();

    // setting up Chrome web driver:
    public static void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        Utils.driver = new ChromeDriver(options);
    }

    // turning off Hibernate and extra Selenium logs:
    public static void turnOffLogs(){
        // turning off hibernate logs:
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.OFF);
        // turning off selenium logs:
        Logger seleniumLogger = Logger.getLogger("org.seleniumhq.selenium");
        seleniumLogger.setLevel(Level.OFF);
        // turning off web driver logs:
        Logger driverLogger = Logger.getLogger("org.openqa.selenium");
        driverLogger.setLevel(Level.OFF);
    }

    // returns the static Stocks url:
    public static String getFindStocksURL() {
        return findStocksURL;
    }

    // returns the dynamic Daily url:
    public static String getFindDaysURL(String StockID) {
        Utils.findDaysURL = "http://cdn.tsetmc.com/api/ClosingPrice/GetClosingPriceDailyList/"+StockID+"/100000";
        return findDaysURL;
    }

    // returns the dynamic Trade url:
    public static String getFindTradesURL(String StockID, String Date) {
        Utils.findTradesURL = "http://cdn.tsetmc.com/api/Trade/GetTradeHistory/"+StockID+"/"+Date+"/false";
        return findTradesURL;
    }

    // The outer fetching method:
    public static String fetch(String url){
        // defining Exception flag and  response:
        boolean ExceptionOccurred = true;
        String response=null;
        // while there is an Exception(about chrome Driver):
        int EmptyTimes = 0;
        while(ExceptionOccurred){
            try{
                response = innerFetch(url);
                if(Objects.equals(response, "{\"tradeHistory\":[]}") || response.startsWith("JSON")){
                    EmptyTimes++;
                    if(EmptyTimes==10) ExceptionOccurred = false;
                    System.out.println(EmptyTimes + " tradeHistory was empty: " + response.substring(0,19));
                    driver.quit();
                    setDriver();
                } else {
                    ExceptionOccurred = false;
                }
            }catch (Exception e){
                System.out.println("Driver timed out");
                try{
                    driver.quit();
                    setDriver();
                }catch (Exception e2){
                    System.out.println("second fail...");
                }
            }
        }
        return response;
    }

    // the inner fetching method:
    private static String innerFetch(String url){
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
        String response = null;
        System.out.println("fetch: " + url);
        driver.get(url);
        response = Jsoup.parse(driver.getPageSource()).text();

        // if the server returns server error, we give it a 5s rest and then we request again
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
        // returning the fetched response:
        return response;
    }
}

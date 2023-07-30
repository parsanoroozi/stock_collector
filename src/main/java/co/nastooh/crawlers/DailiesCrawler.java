package co.nastooh.crawlers;

import co.nastooh.json_utils.ClosingPriceDaily;
import co.nastooh.tables.Daily;
import co.nastooh.tables.Stock;

import java.util.ArrayList;

public class DailiesCrawler {

    public static ArrayList<Daily> collectDailies(Stock stock){

        // fetching a stocks trading days:
        String dailiesApiRes = Utils.fetch(Utils.getFindDaysURL(stock.getId()));

        // parsing the string result into a JSON array:
        ClosingPriceDaily[] stockDays = ClosingPriceDaily.getTheList(dailiesApiRes);

        // defining the daily list:
        ArrayList<Daily> dailyList = new ArrayList<>();

        // iterating though json array and get every daily object
        for(ClosingPriceDaily item : stockDays) {

            Daily daily = new Daily();
            // filling daily fields:
            daily.setMin_price(item.getPriceMin());
            daily.setMax_price(item.getPriceMax());
            daily.setYesterday_price(item.getPriceYesterday());
            daily.setFirst_price(item.getPriceFirst());
            daily.setLast_price(item.getpClosing());
            daily.setDate(item.getdEven());
            daily.setTransaction_count(item.getzTotTran());
            daily.setTransaction_volume(item.getqTotTran5J());
            daily.setTransaction_value(item.getqTotCap());
            daily.setStock(stock);
            dailyList.add(daily);
        }

        // returning the daily list:
        return dailyList;
    }
}

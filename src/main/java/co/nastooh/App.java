package co.nastooh;


import co.nastooh.crawlers.Utils;
import co.nastooh.engines.historical_engine.HistoryCollector;
import co.nastooh.engines.realtime_engine.RealTimeCollector;
import co.nastooh.transactions.UtilsTransaction;
//http://cdn.tsetmc.com/api/Trade/GetTradeHistory/42599305106713939/20200509/false

public class App
{
    public static void main( String[] args )
    {
        // setting the chrome driver for fetching data
        Utils.setDriver();

        // turning off hibernate and selenium logs:
        Utils.turnOffLogs();

        Utils.fetch("http://cdn.tsetmc.com/api/Trade/GetTradeHistory/42599305106713939/20200311/false");
        // is history process over:
        boolean isHistoryOver = UtilsTransaction.isHistoryOver();

        if(isHistoryOver)
        {
           // fetching realtime updates :
            RealTimeCollector.run();
        }
        else
        {
           // fetching history of the trades:
            HistoryCollector.run();
        }
    }
}

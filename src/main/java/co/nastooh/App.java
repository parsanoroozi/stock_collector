package co.nastooh;


import co.nastooh.crawlers.Utils;
import co.nastooh.engines.historical_engine.HistoryCollector;
import co.nastooh.engines.realtime_engine.RealTimeCollector;
import co.nastooh.transactions.UtilsTransaction;


public class App
{
    public static void main( String[] args )
    {
        // setting the chrome driver for fetching data
        Utils.setDriver();

        // turning off hibernate and selenium logs:
        Utils.turnOffLogs();

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

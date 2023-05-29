package co.nastooh;


import co.nastooh.crawlers.Utils;
import co.nastooh.engines.historical_engine.HistoryCollector;
import co.nastooh.engines.realtime_engine.RealTimeCollector;
import co.nastooh.transactions.GetUtils;


public class App
{
    public static void main( String[] args )
    {
        // setting the chrome driver for fetching data
        Utils.setDriver();

        // is history process over:
        boolean isHistoryOver = GetUtils.isHistoryOver();

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

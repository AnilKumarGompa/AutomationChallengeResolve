package com.ResolveIDC.Utils;

import com.ResolveIDC.DataManager.ConfigManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    private static ConfigManager sys = new ConfigManager();
    /**
     * Get Wait time specified in property file
     * @param waitType time for wait
     * @return returns value of wait time in integer
     */
    public static int getWaitTime(String waitType)
    {
        int waitTime = 15;
        String wait;
        if(waitType!=null&&!waitType.equalsIgnoreCase(""))
        {
            wait = sys.getProperty(waitType);
        }
        else
        {
//            log.error("WaitType cannot be empty...defaulting to MEDIUM WAIT");
            wait = sys.getProperty("WAIT.MEDIUM");
        }
        try
        {
            waitTime = Integer.parseInt(wait);
        }
        catch(NumberFormatException e)
        {
//            log.error("Please check the config file. Wait values must be a number...defaulting to 15 seconds");
        }
        return waitTime;
    }



}

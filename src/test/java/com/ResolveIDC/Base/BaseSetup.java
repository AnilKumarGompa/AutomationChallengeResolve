/**************************************** PURPOSE **********************************

 - This class contains the code related to Basic setup of TestSuites such as Instantiating Browser,
 - Launching Browser

 USAGE
 - Inherit this BaseClass for any TestSuite class. You don't have to write any @Beforeclass and @AfterClass
 - actions in your TestSuite Classes

 - Example:
 --import Com.Base
 --- public class <TestSuiteClassName> extends BaseClass
 */

package com.ResolveIDC.Base;


import com.ResolveIDC.DataManager.ConfigManager;
import com.ResolveIDC.Listeners.TestListener;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.apache.log4j.Logger;

import java.io.File;


@Listeners({TestListener.class})
public class BaseSetup {
    public WebDriver driver;
    ConfigManager configManager = new ConfigManager();
    public ConfigManager appData = new ConfigManager("App");
    Logger log = Logger.getLogger("BaseSetup");
    private String userDir=System.getProperty("user.dir");
    private String fileSeparator=File.separator;
    /**
     * Getter method for WebDriver
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Setter method for WebDriver
     * @param driver driver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeSuite
    public void beforeSuite(){
        //configuring log4j.xml file for generating logs
        DOMConfigurator.configure(System.getProperty("user.dir")+ fileSeparator+"ConfigFiles"+fileSeparator+"log4j.xml");
    }

    public void initiateDriver(){
        try{
            configManager.getProperty("ChromeDriver");
            System.setProperty(configManager.getProperty("ChromeDriver"), userDir+"\\"+configManager.getProperty("ChromeDriverPath"));
            driver = new ChromeDriver();
            setDriver(driver);
            driver.manage().window().maximize();
            log.info("Maximize the driver");
        }
        catch (Exception e){
            log.error("Exception occurs while launching the driver "+ e.getMessage());
        }
    }

    @BeforeMethod
    public void launchApplication(){
        try{
            initiateDriver();
            ConfigManager config = new ConfigManager("App");
            driver.get(System.getProperty("user.dir")+"//"+config.getProperty("URL"));
        }
        catch (Exception e){
            log.error("Exception occurs while launching the URL/application"+ e.getMessage());
        }

    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}

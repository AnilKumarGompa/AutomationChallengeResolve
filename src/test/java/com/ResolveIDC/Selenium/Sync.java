package com.ResolveIDC.Selenium;

import com.ResolveIDC.Utils.Asserts;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;


public class Sync implements TimeOut{

    public Asserts asserts = new Asserts();
    private WebDriver driver;
    Logger log = Logger.getLogger("Sync");

    public Sync(WebDriver driver){
        this.driver = driver;
    }

    public Sync(){
    }

    /**
     * Method- Get wait time based on input
     * @param optionalWaitArray
     * @return
     */
    protected int getWaitTime(int[] optionalWaitArray)
    {
        if(optionalWaitArray.length<=0)
        {
            return MEDIUMWAIT;
        }
        else
        {
            return optionalWaitArray[0];
        }
    }
    /**
     * Method -  Waits for an element till the element is clickable
     * @param friendlyWebElementName user friendly message
     * @param locator (By) locator object of the element to be found
     * @param optionWaitTime (int) The time in seconds to wait until returning a failure
     * @return - True (Boolean) if element is located and is clickable within timeout period else false
     */
    protected boolean waitUntilElementIsClickable(By locator, String friendlyWebElementName, int... optionWaitTime)
    {
        int waitTime =  getWaitTime(optionWaitTime);
        boolean bFlag = false;
        try
        {
            log.info(getTestCaseName()+"Waiting until " + friendlyWebElementName+" is clickable");
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            if(driver.findElement((locator)).isDisplayed())
            {
                bFlag = true;
                log.info(getTestCaseName()+friendlyWebElementName + " is displayed and is clickable");
            }
        }

        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+"Unable to find " + friendlyWebElementName +" in DOM in time "+waitTime+" Seconds - NoSuchElementException");
            Assert.fail("Unable to find " + friendlyWebElementName +" in DOM in time "+waitTime+" Seconds - NoSuchElementException");
        }
        catch (TimeoutException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + "was not displayed in time - "+waitTime+" Seconds - TimeoutException");
            Assert.fail(friendlyWebElementName + " was not displayed in time - "+waitTime+" Seconds - TimeoutException");
        }
        catch (Exception e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + "was not displayed - " + e);
            Assert.fail(friendlyWebElementName + " was not displayed");
        }
        return bFlag;
    }

    /**
     * Method - wait for element to load and retun true if element is present in DOM, else return false
     * @param sElementName user friendly message
     * @param element (By) locator object of the element to be found
     * @param waitTime (int) The time in seconds to wait until returning a failure
     * @return - True (Boolean) if element is located and is clickable within timeout period else false
     */
    public boolean waitForElementToLoad(WebElement element, String sElementName, int waitTime) {
        try {
            log.info(getTestCaseName()  + "- Waiting until element " + sElementName + " is visible in time " + waitTime
                    + " secs");
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.error(getTestCaseName() + "- Element " + sElementName + " was not visible in time - " + waitTime);
            e.printStackTrace();
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            log.error(
                    getTestCaseName() + "- Element " + element + "is not attached to the page document" + e.getStackTrace());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            log.error("Unable to find the element " + sElementName);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Method - is to wait for some amount of time which is given in seconds.
     * @param sec waiting time
     */
    protected void waitForSecs(int sec)
    {
        log.info(getTestCaseName()+"Waiting for "+sec+ " seconds.");
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"Unable to wait for given "+sec +" seconds.");
            e.printStackTrace();
        }
    }

    /**
     * Method - Get the test case name which is currently executing
     * @return current execution method (String)
     */
    public String getTestCaseName()
    {
        int len= Thread.currentThread().getStackTrace().length;
        String testCaseName = "null";
        for(int tcName=0;tcName<=len;tcName++)
        {
            if(Thread.currentThread().getStackTrace()[tcName].getMethodName().contains("invoke"))
            {
                testCaseName = Thread.currentThread().getStackTrace()[tcName-1].getMethodName();
                break;
            }
        }
        return testCaseName+":";
    }
}

package com.ResolveIDC.Selenium;

import com.ResolveIDC.DataManager.ConfigManager;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class SafeActions extends Sync implements TimeOut{
    //Local WebDriver instance
    private WebDriver driver;
    Logger log =Logger.getLogger("SafeActions");
    private ConfigManager sys = new ConfigManager();

    //Constructor to initialize the local WebDriver variable with the WebDriver variable that,
    //has been passed from each PageParts Java class
    public SafeActions(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }

    /**
     * Method - Safe Method for User Click, waits until the element is loaded and then performs a click action
     * @param locator locator
     * @param optionWaitTime optionWaitTime
     */

    public void safeClick(By locator, String friendlyWebElementName, int... optionWaitTime)
    {
        int waitTime = 0;
        try
        {
            waitTime =  getWaitTime(optionWaitTime);
            if(waitUntilElementIsClickable(locator, friendlyWebElementName,waitTime))
            {
                scrollIntoElementView(locator,friendlyWebElementName);
                WebElement element = driver.findElement(locator);
                element.click();
                log.info(getTestCaseName()+" Clicked on the " + friendlyWebElementName);
            }
            else
            {
                log.error(getTestCaseName()+friendlyWebElementName + " is not clickable in time - "+waitTime+" Seconds");
                Assert.fail( friendlyWebElementName + " is not clickable in time - "+waitTime+" Seconds");
            }
        }
        catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
            Assert.fail(friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + " was not found in DOM in time - "+waitTime+" Seconds"+" - NoSuchElementException");
            Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" Seconds"+" - NoSuchElementException");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+ friendlyWebElementName + " was not clickable" + " - " + e);
            Assert.fail(friendlyWebElementName + " was not found on the web page");
        }
    }

    /**
     * Get the wait time based on the input parameter
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
     * Method - Scroll to view specified web element
     * @param locator - locator value by which an element is located
     */
    public void scrollIntoElementView(By locator,String friendlyWebElementName)
    {
        try
        {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(locator));
            log.info("Scroll into element "+friendlyWebElementName);
        }
        catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName +" is not attached to the page document");
            Assert.fail(friendlyWebElementName +" is not attached to the page document");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + " was not found in DOM");
            Assert.fail(friendlyWebElementName +" was not found in DOM");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"Unable to scroll the page to find "+ friendlyWebElementName + " - " + e);
            Assert.fail("Unable to scroll the page to find "+ friendlyWebElementName);
        }
    }

    /**
     * Method - check for element is displayed or present in DOM and return true or false, wait for element to load
     * @param ele - locator
     * @param sElementName - locators discription
     * @param waitTime - waiting time
     * @return true or false
     */
    public boolean isElementPresent(By ele, String sElementName, int waitTime) {
        boolean bFlag = false;
        log.info(getTestCaseName() + "- Waiting for presence of element " + sElementName + " in time " + waitTime + " secs");
        try {
            waitForSecs(1);
            WebElement element = driver.findElement(ele);
            waitForElementToLoad(element, sElementName, waitTime);
            if (element.isDisplayed() || element.isEnabled()) {
                bFlag = true;
                log.info(getTestCaseName() + "- Element for " + sElementName + " is displayed");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            log.error(getTestCaseName() + "- There is no such element for " + sElementName);
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (TimeoutException e) {
            log.error(getTestCaseName() + "- Element for " + sElementName + " was not displayed in time - " + waitTime
                    + e.getStackTrace());
            e.printStackTrace();
        } catch (Exception e) {
            log.error(getTestCaseName() + "- Element for " + sElementName + " is not displayed" + e.getStackTrace());
        }
        return bFlag;
    }

    /**
     * Method - Safe Method for User clear and Type, waits until the element is loaded and then enters some text
     * @param locator locator
     * @param text text to enter in Textbox
     * @param optionWaitTime optionWaitTime
     */
    public void safeClearAndType(By locator, String text, String friendlyWebElementName, int... optionWaitTime)
    {
        int waitTime=0;
        try
        {
            waitTime =  getWaitTime(optionWaitTime);
            if(isElementPresent(locator, friendlyWebElementName, waitTime))
            {
                scrollIntoElementView(locator,friendlyWebElementName);
                WebElement element=driver.findElement(locator);
                element.click();
                element.clear();
                element.sendKeys(text);
                log.info(getTestCaseName()+"Clear and Entered - '" + text + " into " + friendlyWebElementName);
            }
            else
            {
                log.error(getTestCaseName()+"Unable to clear and enter " + text + " in  " + friendlyWebElementName+" in time - "+waitTime+" Seconds");
                Assert.fail("Unable to clear and enter " + text + " in  " + friendlyWebElementName+" in time - "+waitTime+" Seconds");
            }
        }
        catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName()+"Text " + text + " to be clear and entered in the   " + friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
            Assert.fail("Text " + text + " to be clear and entered in the   " + friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+"Text " + text + " to be clear and entered in the   " + friendlyWebElementName + " is not attached to the page document in time - "+waitTime+" - NoSuchElementException");
            Assert.fail("Text " + text + " to be clear and entered in the   " + friendlyWebElementName + " is not attached to the page document in time - "+waitTime+" - NoSuchElementException");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"Unable to clear and enter '" + text + "' text in   -"+ friendlyWebElementName + " - " + e);
            Assert.fail("Unable to clear and enter '" + text + "' text in  -"+ friendlyWebElementName+" Some Exception");
        }
    }

    /**
     * Method - Get text of an element from the DOM, wait until element is loaded in DOM
     * @param locator - locator value by which an element is located
     * @param waitTime - Time to wait for an element
     * @return - returns the text value from element
     */
    public String safeGetText(By locator,String friendlyWebElementName,int waitTime)
    {
        String sValue =null;
        try
        {
            if(isElementPresent(locator,friendlyWebElementName, waitTime))
            {
                sValue = driver.findElement(locator).getText();
            }
            else
            {
                Assert.fail("Unable to find "+ friendlyWebElementName+" in time - "+waitTime);
            }

        }
        catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName +" is not attached to the page document - StaleElementReferenceException");
            Assert.fail(friendlyWebElementName +" is not attached to the page document - StaleElementReferenceException");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");
            Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"Unable to get the text from "+ friendlyWebElementName + " - " + e);
            Assert.fail("Unable to find "+ friendlyWebElementName);
        }
        return sValue;
    }

    /**
     * Method - Get text of all the elements, add them in list and return. Wait until elements are loaded in DOM
     * @param locator - locator value by which an element is located
     * @param friendlyWebElementName - locator friendly name/description
     * @param waitTime - Time to wait for an element
     * @return list of text for all the element
     */
    public List<String> getAllElementText(By locator, String friendlyWebElementName, int... waitTime){
        List<String> textList = new ArrayList<String>();
        try{
            List<WebElement> elementList = driver.findElements(locator);
            for (WebElement element: elementList)
            {
                textList.add(element.getText());
            }
        }catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName +" is not attached to the page document - StaleElementReferenceException");
            Assert.fail(friendlyWebElementName +" is not attached to the page document - StaleElementReferenceException");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+friendlyWebElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");
            Assert.fail(friendlyWebElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"Unable to get the text from "+ friendlyWebElementName + " - " + e);
            Assert.fail("Unable to find "+ friendlyWebElementName);
        }
        return textList;
    }

    /**
     * Method - Safe Method for clear, waits until the element is loaded and then clear the text
     * @param locator locator
     * @param optionWaitTime optionWaitTime
     */
    public void safeClear(By locator, String friendlyWebElementName, int... optionWaitTime)
    {
        int waitTime=0;
        try
        {
            waitTime =  getWaitTime(optionWaitTime);
            if(isElementPresent(locator, friendlyWebElementName, waitTime))
            {
                scrollIntoElementView(locator,friendlyWebElementName);
                WebElement element=driver.findElement(locator);
                element.click();
                element.clear();
                element.sendKeys(Keys.ENTER);
                waitForSecs(2);
                log.info(getTestCaseName()+"clear text" + friendlyWebElementName);
            }
            else
            {
                log.error(getTestCaseName()+"Unable to clear text in  " + friendlyWebElementName+" in time - "+waitTime+" Seconds");
                Assert.fail("Unable to clear text in  " + friendlyWebElementName+" in time - "+waitTime+" Seconds");
            }
        }
        catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName() + friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
            Assert.fail("Unable to clear text in the   " + friendlyWebElementName + " is not attached to the page document - StaleElementReferenceException");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+"Clear the text " + friendlyWebElementName + " is not attached to the page document in time - "+waitTime+" - NoSuchElementException");
            Assert.fail("Clear the text in the   " + friendlyWebElementName + " is not attached to the page document in time - "+waitTime+" - NoSuchElementException");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"\"Clear the text in   -"+ friendlyWebElementName + " - " + e);
            Assert.fail("Unable to clear text in  -"+ friendlyWebElementName+" Some Exception");
        }
    }
    /**
     * Purpose- Method For performing drag and drop operations
     * @param Sourcelocator,Destinationlocator source and destination locators
     * @param waitTime wait time
     */
    public void dragAndDrop(By Sourcelocator, By Destinationlocator, String friendly_SourceElementName,String friendly_DestinationElementName,int waitTime)
    {
        try
        {
            if(isElementPresent(Sourcelocator,friendly_SourceElementName, waitTime))
            {
                scrollIntoElementView(Sourcelocator,friendly_SourceElementName);
                WebElement source = driver.findElement(Sourcelocator);
                waitForSecs(2);
                if(isElementPresent(Destinationlocator,friendly_DestinationElementName, waitTime))
                {
                    scrollIntoElementView(Destinationlocator,friendly_DestinationElementName);
                    WebElement destination = driver.findElement(Destinationlocator);
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                            + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                            + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                            + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                            + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                            + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                            + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                            + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                            + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                            + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                            + "var dropEvent = createEvent('drop');\n"
                            + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                            + "var dragEndEvent = createEvent('dragend');\n"
                            + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                            + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                            + "simulateHTML5DragAndDrop(source,destination);", source, destination);
                    waitForSecs(2);
                    log.info(getTestCaseName()+"Dragged the "+ friendly_SourceElementName + " and dropped in to " + friendly_DestinationElementName);
                }
                else
                {
                    log.error(getTestCaseName()+"Destination Element "+friendly_DestinationElementName+" was not displayed to drop");
                    Assert.fail("Destination Element "+friendly_DestinationElementName+" was not displayed to drop");
                }
            }
            else
            {
                log.error(getTestCaseName()+"Source Element "+friendly_SourceElementName+" was not displayed to drag");
                Assert.fail("Source Element "+friendly_SourceElementName+" was not displayed to drag");
            }
        }
        catch(StaleElementReferenceException e)
        {
            log.error(getTestCaseName()+friendly_SourceElementName + "or"+ friendly_DestinationElementName +"is not attached to the page document - StaleElementReferenceException");
            Assert.fail(friendly_SourceElementName + "or"+ friendly_DestinationElementName +"is not attached to the page document - StaleElementReferenceException");
        }
        catch (NoSuchElementException e)
        {
            log.error(getTestCaseName()+friendly_SourceElementName + "or"+ friendly_DestinationElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");
            Assert.fail(friendly_SourceElementName + "or"+ friendly_DestinationElementName + " was not found in DOM in time - "+waitTime+" - NoSuchElementException");
        }
        catch(Exception e)
        {
            log.error(getTestCaseName()+"Some exception occurred while performing drag and drop operation" + " - " + e);
            Assert.fail("Some exception occurred while performing drag and drop operation");
        }
    }
}

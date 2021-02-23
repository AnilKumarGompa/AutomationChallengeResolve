package com.ResolveIDC.Pages.Modules;

import com.ResolveIDC.Pages.Locators.HomePageLocators;
import com.ResolveIDC.Selenium.SafeActions;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import java.util.List;

public class HomePage extends SafeActions implements HomePageLocators {
    private WebDriver driver;
    Logger log = Logger.getLogger("HomePage");
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void clickOnOpenModule(){
        safeClick(OPEN_MODULE_BUTTON,"Open Module Button",MEDIUMWAIT);
    }

    public void validateModuleTabIsDisplayed(){
        if(!isElementPresent(MODULE_TAB,"Module Header Tab",MEDIUMWAIT))
            asserts.toBeFail("Module Header Tab is not displayed");
    }

    public void fillModuleDate(String name, String city){
        safeClearAndType(NAME_INPUT_FILED,name,"Name",MEDIUMWAIT);
        safeClearAndType(CITY_INPUT_FILED,city,"City",MEDIUMWAIT);
        safeClick(OPTION_1_CHECKBOX,"Option 1",MEDIUMWAIT);
        safeClick(OPTION_2_CHECKBOX,"Option 2",MEDIUMWAIT);
        safeClick(ENTER_DATA_BUTTON,"Enter Data",MEDIUMWAIT);
    }

    public void closeModuleTab(){
        safeClick(CLOSE_BUTTON,"Close",MEDIUMWAIT);
    }

    public void validateEnteredNameAndCityIsDisplayedUnderOpenModuleButton(String expectedName,String expectedCity){
        String actualName = safeGetText(NAME_VALUE,"Name",MEDIUMWAIT);
        asserts.expectToBeEqual(actualName,expectedName);
        String actualCity = safeGetText(CITY_VALUE,"City",MEDIUMWAIT);
        asserts.expectToBeEqual(actualCity,expectedCity);
    }

    public void clickOnClickMeDropdown(){
        safeClick(CLICK_ME_DROPDOWN,"Click me dropdown",MEDIUMWAIT);
    }

    public void validateSearchInputFiledAndDropdownValuesDisplayed(){
        if(!isElementPresent(SEARCH_INPUT,"Search input field",MEDIUMWAIT)){
            asserts.toBeFail("Search input filed is not displayed");
        }
        log.info("Search input filed is displayed");

        if(!isElementPresent(CLICK_ME_DROP_DOWN_VALUES,"Click me dropdown values",MEDIUMWAIT)){
            asserts.toBeFail("Click me dropdown values are not displayed");
        }
        log.info("Click me dropdown values are displayed");
    }

    public void printAllDropdownValues(){
        List<String> dropdownValues = getAllElementText(CLICK_ME_DROP_DOWN_VALUES,"Click me dropdown values",MEDIUMWAIT);
        log.info(dropdownValues);
    }

    public void enterTextInSearchInputField(String value){
        safeClearAndType(SEARCH_INPUT,value, "Search input field",MEDIUMWAIT);
    }

    public void validateDropDownIsDisplayed(String expectedValue){
        List<String> dropdownValues = getAllElementText(CLICK_ME_DROP_DOWN_VALUES,"Click me dropdown values",MEDIUMWAIT);
        log.info(dropdownValues);
        asserts.expectToBeTrue(dropdownValues.toString().contains(expectedValue), "Actual Dropdown values:"+dropdownValues.toString()+"doest not contains Expected Dropdown : "+expectedValue);
    }

    public void validateDropDownIsNotDisplayed(String expectedValue){
        List<String> dropdownValues = getAllElementText(CLICK_ME_DROP_DOWN_VALUES,"Click me dropdown values",MEDIUMWAIT);
        log.info(dropdownValues);
        asserts.expectToBeFalse(dropdownValues.toString().contains(expectedValue), "Actual Dropdown values:"+dropdownValues.toString()+" contains Expected Dropdown : "+expectedValue);
    }

    public void enterTextInSearchBox(String value){
        safeClearAndType(SEARCH_BOX,value, "Search box field",MEDIUMWAIT);
    }

    public void validateNumberOfRowsDisplayedCountryName(int numberOfRows, String expectedCountry){
        List<String> dropdownValues = getAllElementText(TABLE_DISPLAYED_ROWS,"Click me dropdown values",MEDIUMWAIT);
        if(dropdownValues.size()==numberOfRows){
            for (String actualValue: dropdownValues) {
                asserts.expectToBeTrue(actualValue.contains(expectedCountry),"Displayed Row -"+actualValue+" does not contains Expected country: "+expectedCountry);
            }
        }
        else{
            asserts.toBeFail("Displayed rows are not expected row count");
        }
    }
    public void clearSearchBox(){
        safeClear(SEARCH_BOX, "Search box field",MEDIUMWAIT);
    }

    public void validatedDisplayedRowCount(int numberOfRows){
        List<String> dropdownValues = getAllElementText(TABLE_DISPLAYED_ROWS,"Click me dropdown values",MEDIUMWAIT);
        if(dropdownValues.size()!=numberOfRows){
            asserts.toBeFail("Displayed rows:"+dropdownValues.size()+" is not equals expected row count:"+numberOfRows);
        }
    }

    public void dragAndDropSourceButtonDragMeToDestination(){
        dragAndDrop(DRAG_BUTTON,DROP_BOX,"Source Drag me button","Destination box",MEDIUMWAIT);
    }
    public void validateDragMeButtonDraggedInsideTheBox(){
        if(!isElementPresent(DRAG_BUTTON_IN_DROP_BOX,"Drag Me button Inside the box",MEDIUMWAIT)){
            asserts.toBeFail("Drag Me button not displayed Inside the box");
        }
        log.info("Drag Me button Inside the box");
    }
}

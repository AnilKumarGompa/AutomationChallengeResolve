package com.ResolveIDC.TestSuits;

import com.ResolveIDC.Base.BaseSetup;
import com.ResolveIDC.Pages.Modules.HomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ResolveAutomationChallenge extends BaseSetup {

    HomePage homePage;

    @Test
    public void test1(){
        homePage = new HomePage(getDriver());
        homePage.clickOnOpenModule();
        homePage.validateModuleTabIsDisplayed();
        homePage.fillModuleDate(appData.getProperty("Name"),appData.getProperty("City"));
        homePage.closeModuleTab();
        homePage.validateEnteredNameAndCityIsDisplayedUnderOpenModuleButton(appData.getProperty("Name"),appData.getProperty("City"));
    }


    @Test
    public void test2(){
        homePage = new HomePage(getDriver());
        homePage.clickOnClickMeDropdown();
        homePage.validateSearchInputFiledAndDropdownValuesDisplayed();
        homePage.printAllDropdownValues();
        homePage.enterTextInSearchInputField(appData.getProperty("SearchInput1_Angular"));
        homePage.validateDropDownIsDisplayed(appData.getProperty("SearchInput1_Angular"));
        homePage.validateDropDownIsDisplayed(appData.getProperty("SearchInput2_Angular"));
        homePage.enterTextInSearchInputField(appData.getProperty("SearchInput3_ReactJs"));
        homePage.validateDropDownIsNotDisplayed(appData.getProperty("SearchInput3_ReactJs"));
    }

    @Test
    public void test3(){
        homePage = new HomePage(getDriver());
        homePage.enterTextInSearchBox(appData.getProperty("SearchBoxInput_USA"));
        homePage.validateNumberOfRowsDisplayedCountryName(Integer.parseInt(appData.getProperty("RowCountTwo")),appData.getProperty("SearchBoxInput_USA"));
        homePage.clearSearchBox();
        homePage.validatedDisplayedRowCount(Integer.parseInt(appData.getProperty("RowCountFour")));
    }

    @Test
    public void test4(){
        homePage = new HomePage(getDriver());
        homePage.dragAndDropSourceButtonDragMeToDestination();
        homePage.validateDragMeButtonDraggedInsideTheBox();
    }
}

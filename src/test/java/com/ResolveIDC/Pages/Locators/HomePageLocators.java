package com.ResolveIDC.Pages.Locators;

import org.openqa.selenium.By;

public interface HomePageLocators {

    By MODULE_TAB = By.xpath("//*[@class='modal-title'][normalize-space()='Modal Header']");
    By NAME_INPUT_FILED = By.id("name");
    By CITY_INPUT_FILED = By.id("city");
    By ENTER_DATA_BUTTON = By.id("enter");
    By OPTION_1_CHECKBOX = By.xpath("//*[normalize-space()='Option 1']//input");
    By OPTION_2_CHECKBOX = By.xpath("//*[normalize-space()='Option 2']//input");
    By OPEN_MODULE_BUTTON = By.cssSelector("[data-toggle='modal']");
    By CLOSE_BUTTON = By.className("close");
    By NAME_VALUE = By.id("nameVal");
    By CITY_VALUE = By.id("cityVal");
    By CLICK_ME_DROPDOWN = By.cssSelector("button[data-toggle='dropdown']");
    By SEARCH_INPUT = By.id("myInput");
    By CLICK_ME_DROP_DOWN_VALUES = By.xpath("//*[contains(@class,'dropdown-menu')]//li[not(@style)]");
    By SEARCH_BOX = By.id("searchMe");
    By TABLE_DISPLAYED_ROWS = By.xpath("//*[@id='someTable']//tr[not(contains(@style,'display'))]");
    By DRAG_BUTTON = By.id("drag1");
    By DROP_BOX = By.id("div1");
    By DRAG_BUTTON_IN_DROP_BOX = By.cssSelector("[id='div1'] [id='drag1']");
}

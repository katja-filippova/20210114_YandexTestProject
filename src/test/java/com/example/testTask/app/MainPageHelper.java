package com.example.testTask.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageHelper extends BaseHelper {

    public MainPageHelper(WebDriver wd) {
        super(wd);
    }

    public void goToYandexMarket(By locator) {
        clickOnElement(locator);
    }
}

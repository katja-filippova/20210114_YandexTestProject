package com.example.testTask.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MarketHeaderHelper extends BaseHelper {

    public MarketHeaderHelper(WebDriver wd) {
        super(wd);
    }

    public void selectComputerDepartment(By locator) {
        clickOnElement(By.cssSelector("#pokupki"));
        clickOnElement(locator);
    }
}

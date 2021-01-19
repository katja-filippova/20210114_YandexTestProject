package com.example.testTask.app;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static com.google.common.io.Files.copy;

public abstract class BaseHelper {

    WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void fillInForm(By locator, String keys) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(keys);
    }

    public void clickOnElement(By locator) {
        wd.findElement(locator).click();
    }

    public void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshot" + System.currentTimeMillis() + ".png");

        copy(tmp, screenshot);

        return screenshot.getAbsolutePath();
    }

}

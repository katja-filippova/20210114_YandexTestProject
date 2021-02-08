package com.example.testTask.app;

import com.example.testTask.test.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {


    EventFiringWebDriver wd;

    MainPageHelper mainPageHelper;
    MarketHeaderHelper marketHeaderHelper;
    MarketItemHelper marketItemHelper;
    String browser;

    public static class MyListener extends AbstractWebDriverEventListener {
        public Logger logger = LoggerFactory.getLogger(TestBase.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Start search" + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            logger.info(by + "found. ");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            logger.error(throwable.toString());
        }

    }

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        switch (browser) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", "C://Dev//chromedriver_win32//chromedriver.exe");
                wd = new EventFiringWebDriver(new ChromeDriver());
                break;
            case BrowserType.FIREFOX:
                System.setProperty("driver.browser.geckodriver", "C://Dev//geckodriver-v0.28.0-win64//geckodriver.exe");
                wd = new EventFiringWebDriver(new FirefoxDriver());
                break;
            case BrowserType.EDGE:
                System.setProperty("webdriver.edge.driver", "C://Dev//edgedriver_win64//msedgedriver.exe");
                wd = new EventFiringWebDriver(new EdgeDriver());
                break;
        }

        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.navigate().to("https://yandex.ru/");

        mainPageHelper = new MainPageHelper(wd);
        marketHeaderHelper = new MarketHeaderHelper(wd);
        marketItemHelper = new MarketItemHelper(wd);

        wd.register(new MyListener());
    }

    public void stop() {
        wd.quit();
    }


    public MainPageHelper getMainPageHelper() {
        return mainPageHelper;
    }

    public MarketHeaderHelper getMarketHeaderHelper() {
        return marketHeaderHelper;
    }

    public MarketItemHelper getMarketItemHelper() {
        return marketItemHelper;
    }
}

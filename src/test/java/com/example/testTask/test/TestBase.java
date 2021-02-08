package com.example.testTask.test;

import com.example.testTask.app.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    public Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.EDGE));

    @BeforeMethod
    public void startTest(Method m, Object[] objects){
        logger.info("Start test " + m.getName());
    }

    @BeforeClass
    public void setUp(){
        app.init();
    }

    @AfterClass(enabled = false)
    public void tearDown(){
        app.stop();
    }

    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result) throws IOException {
        if (result.isSuccess()){
            logger.info("PASSED: test method " + result.getMethod().getMethodName());
        }else {
            logger.error("FAILED: test method " + result.getMethod().getMethodName() + "\n" + "Screenshot: " + app.getMainPageHelper().takeScreenshot());

        }
    }
}

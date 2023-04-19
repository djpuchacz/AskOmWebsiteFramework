package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void startDriver(){
        driver = new DriverManager().initializeDriver();
    }
    @Parameters("browser")
    @AfterMethod
    public void quitDriver(@Optional String browser, ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            File destFile = new File("scr" + File.separator +  browser + File.separator);
        }

        driver.quit();
    }
}

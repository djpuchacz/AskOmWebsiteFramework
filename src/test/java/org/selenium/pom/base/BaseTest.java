package org.selenium.pom.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import static java.sql.DriverManager.getDriver;

public class BaseTest {
    protected WebDriver driver;
    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
        if(browser == null) browser = "CHROME";

        driver = new DriverManager().initializeDriver();
    }
    @Parameters("browser")
    @AfterMethod
    public void quitDriver(@Optional String browser, ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            File destFile = new File("scr" + File.separator +  browser + File.separator + result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destFile);
        }

        driver.quit();
    }
    //https://www.browserstack.com/guide/take-screenshots-in-selenium
    private void takeScreenshot(File destFile) throws IOException {
        //TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        TakesScreenshot takesScreenshot =((TakesScreenshot)driver);
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }
}

package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();//121
    private void setDriver(WebDriver driver){ //121
        this.driver.set(driver);
    }
    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
        browser = System.getProperty("browser", browser ); // 110, 113, 164, 176 use for JVM argument or Maven property; disable if you want running via click on run on class or test name or via mvn clean test -Dbrowser=CHROME
        //browser = System.getProperty("browser", "CHROME"); // 110, 176 disable if you run with testng.xml via click on run; enable if you want running via click on run on class or test name or via mvn clean test -Dbrowser=CHROME
        if(browser == null) browser = "CHROME"; //disable if you run with testng.xml via click on run; enable if you want running via click on run on class or test name
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public void quitDriver(@Optional String browser, ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            File destFile = new File("scr" + File.separator +  browser + File.separator + result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + ".png");
            //takeScreenshot(destFile);
            takeScreenshotUsingAShot(destFile); //185
        }
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
        getDriver().quit();
    }
    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie>seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    //https://www.browserstack.com/guide/take-screenshots-in-selenium
    private void takeScreenshot(File destFile) throws IOException {
        //TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        TakesScreenshot takesScreenshot =((TakesScreenshot)driver);
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    private void takeScreenshotUsingAShot(File destFile){
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());
        try{
            ImageIO.write(screenshot.getImage(), "PNG", destFile);
    }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}


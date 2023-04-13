package org.selenium.pom.factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;

public class DriverManager {
        public WebDriver initializeDriver() {
           /* //System.setProperty("webdriver.gecko.driver","C:\Webdrivers\\geckodriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //options.addArguments("--headless=new");
            options.addArguments();
            WebDriver driver = new ChromeDriver(options);
            //WebDriver driver = new ChromeDriver();*/


            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            return driver;
        }
}

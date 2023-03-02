package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;

public class DriverManager {
        public WebDriver initializeDriver() {
            WebDriver driver = new ChromeDriver();
            //WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            return driver;
        }
}

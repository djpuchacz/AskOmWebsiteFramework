package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.selenium.pom.constants.DriverType;

public class DriverManager {
        public WebDriver initializeDriver(String browser) {
            WebDriver driver;

            switch (DriverType.valueOf(browser)){
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    //options.addArguments("--remote-allow-origins=*");
                    //options.addArguments("--headless=new");
                    options.addArguments();
                    driver = new ChromeDriver(options);
                    //driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    FirefoxOptions option = new FirefoxOptions();
                    option.setBinary(firefoxBinary);
                    option.setHeadless(false);  // <-- headless set here
                    driver = new FirefoxDriver(option);

                    //driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalStateException("Invalid browser name: " + browser);
            }

            driver.manage().window().maximize();
            return driver;
        }
}

package org.selenium.pom.factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.selenium.pom.constants.DriverType;

import java.util.Locale;

public class DriverManager {
        public WebDriver initializeDriver(String browser) {
            WebDriver driver;
            browser = System.getProperty("browser", browser ); // 113 use for JVM argument or Maven property (110)

            //browser = System.getProperty("browser", "FIREFOX");

            switch (DriverType.valueOf(browser)){
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    //options.addArguments("--headless=new");
                    //options.addArguments();
                    driver = new ChromeDriver(options);
                    //driver = new ChromeDriver();*/
                    break;
                case FIREFOX:
                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    FirefoxOptions option = new FirefoxOptions();
                    option.setBinary(firefoxBinary);
                    option.setHeadless(true);  // <-- headless set here
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

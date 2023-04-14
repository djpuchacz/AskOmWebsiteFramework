package org.selenium.pom.factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;

public class DriverManager {
        public WebDriver initializeDriver() {
            WebDriver driver;
            String browser = System.getProperty("browser");

            switch (browser){
                case "Chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    //options.addArguments("--headless=new");
                    options.addArguments();
                    driver = new ChromeDriver(options);
                    //driver = new ChromeDriver();
                    break;
                case "Firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalStateException("Invalid browser name: " + browser);
            }
           /* //System.setProperty("webdriver.gecko.driver","C:\Webdrivers\\geckodriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //options.addArguments("--headless=new");
            options.addArguments();
            WebDriver driver = new ChromeDriver(options);
            //WebDriver driver = new ChromeDriver();*/

            driver.manage().window().maximize();
            return driver;
        }
}

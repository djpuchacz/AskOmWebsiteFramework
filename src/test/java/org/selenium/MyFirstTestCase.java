package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "E:\\JavaProjects\\MasterSeleniumFramework\\chromedriver.exe");
        driver.get("https://askomdch.com");

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.
                enterTextInSearchFld("Blue").
                clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        storePage.clickAddToCartBtn();


        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
                "Search results: “Blue”"); //to check if search functionality works correct - search page should loaded
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='View cart']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes");
        driver.findElement(By.cssSelector(".checkout-button")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.id("billing_email")).sendKeys("askomdch1410@gmail.com");
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),
                "Thank you. Your order has been received.");
    }

    @Test
    public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
                "Search results: “Blue”"); //to check if search functionality works correct - search page should loaded
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[title='View cart']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(),
                "Blue Shoes");
        driver.findElement(By.cssSelector(".checkout-button")).click();
        driver.findElement(By.className("showlogin")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("demouser1410");
        driver.findElement(By.id("password")).sendKeys("demopwd");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.id("billing_email")).clear();
        driver.findElement(By.id("billing_email")).sendKeys("askomdch1410@gmail.com");
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),
                "Thank you. Your order has been received.");
        driver.quit();
    }
}

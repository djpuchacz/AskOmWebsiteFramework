package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com");

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
               /* enterTextInSearchFld("Blue").
                clickSearchBtn();*/

        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        Thread.sleep(2000);
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.
                enterFirstName("demo").
                enterLastName("user").
                enterAddressLineOne("San Francisco").
                enterCity("San Francisco").
                enterPostCode("94188").
                enterEmail("askomdch1410@gmail.com").
                placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        Thread.sleep(2000);
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        Thread.sleep(3000);

        checkoutPage.
                login("demouser1410","demopwd").
                enterFirstName("demo").
                enterLastName("user").
                enterAddressLineOne("San Francisco").
                enterCity("San Francisco").
                enterPostCode("94188").
                enterEmail("askomdch1410@gmail.com").
                placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }
}

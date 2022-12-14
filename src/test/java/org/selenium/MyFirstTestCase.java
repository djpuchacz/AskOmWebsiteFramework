package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        Thread.sleep(2000);
        storePage.clickAddToCartBtn(product.getName());
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User("demouser1410", "demopwd");

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        Thread.sleep(2000);
        storePage.clickAddToCartBtn(product.getName());
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        Thread.sleep(3000);

        checkoutPage.
                login(user.getUsername(), user.getPassword()).
                setBillingAddress(billingAddress).
                //enterEmail("askomdch1410@gmail.com").
                placeOrder();
        Thread.sleep(2000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }
}

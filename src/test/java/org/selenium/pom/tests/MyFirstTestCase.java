package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {

    //@Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).//143
                load().
                navigateToStoreUsingMenu().//91
        //storePage.isLoaded();
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        //Assert.assertTrue(storePage.getTitle().contains("Search results: "));

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        //cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    //@Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().//91
        //storePage.isLoaded();
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        //Assert.assertTrue(storePage.getTitle().contains("Search results: "));

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        //cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();

        checkoutPage.
                login(user).
                setBillingAddress(billingAddress).
                //enterEmail("askomdch1410@gmail.com").
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }

    //@Test
    public void dummyTest() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().//91
                //storePage.isLoaded();
                        search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        //Assert.assertTrue(storePage.getTitle().contains("Search results: "));

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        //cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();

        checkoutPage.
                login(user).
                setBillingAddress(billingAddress).
                //enterEmail("askomdch1410@gmail.com").
                        selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you handsome. Your order has been received.");
    }
}


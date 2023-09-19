package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException { //162
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 3);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting

        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException { //163
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        String userName = "demouser" + new FakerUtils().generateRandomNumber(); //user account create
        User user = new User().
                setUsername(userName).
                setPassword("demopwd").
                setEmail(userName + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi(); //register of above user
        signUpApi.register(user);

        CartApi cartApi = new CartApi(signUpApi.getCookies()); //add product to a card as a guest
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 3);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        //Thread.sleep(5000);
        injectCookiesToBrowser(signUpApi.getCookies()); //cookies injecting in order to log in
        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}

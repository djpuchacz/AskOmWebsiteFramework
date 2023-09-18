package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException { //158,161 user is log in
        String userName = "demouser" + new FakerUtils().generateRandomNumber(); //user account create
        User user = new User().
                setUsername(userName).
                setPassword("demopwd").
                setEmail(userName + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi(); //register of above user
        signUpApi.register(user);

        CartApi cartApi = new CartApi(); //add product to a card as a guest
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 3);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.
                clickHereToLoginLink().
                login(user);
        Thread.sleep(5000);

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}

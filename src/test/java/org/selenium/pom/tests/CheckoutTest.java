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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

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
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException { //163
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
        injectCookiesToBrowser(signUpApi.getCookies()); //cookies injecting in order to log in
        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
    @Test
    public void guestCheckoutUsingCashOnDelivery() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 3);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting

        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectCashOnDelivery().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
    @Test
    public void loginAndCheckoutUsingCashOnDelivery() throws IOException {
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
        injectCookiesToBrowser(signUpApi.getCookies()); //cookies injecting in order to log in
        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectCashOnDelivery().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void guestCheckoutUsingFreeShippingCoupon() throws ParseException {
        String freeShippingCode = "freeship";
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 3);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting

        checkoutPage.load().
                clickHereToEnterYourCode().
                enterCode(freeShippingCode).
                clickApplyCoupon();

        Assert.assertEquals(checkoutPage.getCouponAppliedSuccessNotice(), "Coupon code applied successfully.");
        Assert.assertEquals(checkoutPage.getTotalValue(freeShippingCode) , checkoutPage.calculateTotalSum(freeShippingCode));
    }
    @Test
    public void guestCheckoutUsingOffcart5Coupon() throws ParseException {
        String offCartCode = "offcart5";
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 3);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting

        checkoutPage.load().
                clickHereToEnterYourCode().
                enterCode(offCartCode).
                clickApplyCoupon();

        Assert.assertEquals(checkoutPage.getCouponAppliedSuccessNotice(), "Coupon code applied successfully.");
        Assert.assertEquals(checkoutPage.getTotalValue(offCartCode), checkoutPage.calculateTotalSum(offCartCode));
    }

    @Test
    public void guestCheckoutUsingOff25Coupon() throws ParseException {
        String offCartCode = "off25";
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 3);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting

        checkoutPage.load().
                clickHereToEnterYourCode().
                enterCode(offCartCode).
                clickApplyCoupon();

        Assert.assertEquals(checkoutPage.getCouponAppliedSuccessNotice(), "Coupon code applied successfully.");
        Assert.assertEquals(checkoutPage.getTotalValue(offCartCode), checkoutPage.calculateTotalSum(offCartCode));
    }
    @DataProvider(name = "getCouponName")
    public Object[][] couponName(){
        return new Object [][] {
            {"freeship"},
            {"offcart5"},
            {"off25"}
        };
    }
    @Test(dataProvider = "getCouponName")
    public void guestCheckoutUsingCoupon(String coupon) throws ParseException {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 3);
        injectCookiesToBrowser(cartApi.getCookies()); //cookies injecting

        checkoutPage.load().
                clickHereToEnterYourCode().
                enterCode(coupon).
                clickApplyCoupon();

        Assert.assertEquals(checkoutPage.getCouponAppliedSuccessNotice(), "Coupon code applied successfully.");
        Assert.assertEquals(checkoutPage.getTotalValue(coupon), checkoutPage.calculateTotalSum(coupon));
    }
}

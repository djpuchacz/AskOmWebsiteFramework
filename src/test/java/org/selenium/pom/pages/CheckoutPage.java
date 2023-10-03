package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import static jdk.internal.agent.Agent.getText;

public class CheckoutPage extends BasePage {
    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressLineOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By successCouponNotice = By.xpath("//div[@role='alert']");

    private final By clickHereToLoginLink = By.className("showlogin");
    private final By clickHereToEnterYourCode = By.className("showcoupon");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By couponFld = By.id("coupon_code");
    private final By loginBtn = By.name("login");
    private final By applyCouponBtn = By.name("apply_coupon");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");

    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown = By.id("select2-billing_state-container");

    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    //private final By cashOnDeliveryBtn = By.cssSelector(".wc_payment_method.payment_method_cod");
    //private final By cashOnDeliveryBtn = By.id("payment_method_cod");
    private final By cashOnDeliveryBtn = By.xpath("//input[@id='payment_method_cod']");
    private final By productName = By.cssSelector("td[class='product-name']");
    private final By errorText = By.xpath("//div[@class='woocommerce-notices-wrapper']//li[1]");
    private final By amountValue = By.xpath("//*[@id=\"order_review\"]/table/tfoot/tr[4]/td/strong/span/bdi/text()");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {//89
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(lastNameFld).clear();
        driver.findElement(lastNameFld).sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) { //107
        /*Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);*/
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        driver.findElement(addressLineOneFld).clear();
        driver.findElement(addressLineOneFld).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName) { //107
       /* Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);*/
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(billingPostCodeFld).clear();
        driver.findElement(billingPostCodeFld).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
        return this;
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }


    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;

    }
    public CheckoutPage clickHereToEnterYourCode() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToEnterYourCode)).click();
        return this;

    }
    public CheckoutPage enterCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponFld)).sendKeys(code);
        return this;
    }
    public CheckoutPage clickApplyCoupon() {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponBtn)).click();
        return this;
    }
    public String getCouponAppliedSuccessNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successCouponNotice)).getText();
    }

    public CheckoutPage enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }


    private CheckoutPage waitForLoginBtnToDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage login(User user) {
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn().
                waitForLoginBtnToDisappear();
    }

    public CheckoutPage selectDirectBankTransfer() { //94
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }

    public CheckoutPage selectCashOnDelivery() {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryBtn));
        //WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(cashOnDeliveryBtn));
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }


    public String getProductName() { //161
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public String getErrorText() { //161
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText)).getText();
    }

    public String getAmountStringValue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(amountValue)).getText();
    }
    public double getAmountValue(){
        return Double.parseDouble(getAmountStringValue());
    }
    public double calculateDiscount(){
        return getAmountValue()-5.0;
    }
}

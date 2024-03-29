package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


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

    //basket values and radio buttons
    private final By subTotalValue = By.xpath("//tr[@class='cart-subtotal']//bdi[1]");
    private final By shippingStdValue = By.xpath("//tr[@class='woocommerce-shipping-totals shipping']//bdi[1]");
    private final By taxValue = By.xpath("//tr[@class='tax-rate tax-rate-us-ca-ca-state-tax-1']//span[@class='woocommerce-Price-amount amount']");
    //private final By taxValue = By.cssSelector("tr[class='tax-rate tax-rate-us-ca-ca-state-tax-1'] span[class='woocommerce-Price-amount amount']");
    private final By amountValue = By.xpath("//tr[@class='order-total']//bdi[1]");
    private final By freeShippingRadioButton = By.xpath("//input[@id='shipping_method_0_free_shipping2']");
    private final By offCart5Coupon = By.xpath("//th[normalize-space()='Coupon: offcart5']");
    private final By off25Coupon = By.xpath("//th[normalize-space()='Coupon: off25']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {//89
        WebElement e = wait.until(visibilityOfElementLocated(firstNameFld));
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
        WebElement e = wait.until(visibilityOfElementLocated(billingEmailFld));
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
        return wait.until(visibilityOfElementLocated(successNotice)).getText();
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
        wait.until(visibilityOfElementLocated(couponFld)).sendKeys(code);
        return this;
    }

    public CheckoutPage clickApplyCoupon() {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponBtn)).click();
        return this;
    }

    public String getCouponAppliedSuccessNotice() {
        return wait.until(visibilityOfElementLocated(successCouponNotice)).getText();
    }

    public CheckoutPage enterUserName(String username) {
        wait.until(visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }


    private CheckoutPage waitForLoginBtnToDisappear() {
        wait.until(invisibilityOfElementLocated(loginBtn));
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
        //elementToBeSelected
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }


    public String getProductName() { //161
        return wait.until(visibilityOfElementLocated(productName)).getText();
    }

    public String getErrorText() { //161
        return wait.until(visibilityOfElementLocated(errorText)).getText();
    }
//get subtotal value
    //https://stackoverflow.com/questions/52332081/convert-input-double-or-float-value-into-us-currency-format-in-java
    //https://www.javatpoint.com/internationalizing-currency
    //https://copyprogramming.com/howto/how-to-format-number-as-currency-string-in-java?utm_content=cmp-true
    //https://stackoverflow.com/questions/20351323/removing-dollar-and-comma-from-string


    public double getSubTotalValue() throws ParseException {
        String temp = wait.until(visibilityOfElementLocated(subTotalValue)).getText();
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        Number number = format.parse(temp);
        return Double.parseDouble(number.toString());
    }

    public double getStdShippingValue() throws ParseException {
        String temp = wait.until(visibilityOfElementLocated(shippingStdValue)).getText();
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        Number number = format.parse(temp);
        return Double.parseDouble(number.toString());
        //return Double.parseDouble(wait.until(ExpectedConditions.visibilityOfElementLocated(shippingStdValue)).getText().substring(1));
    }

    public double getTaxValue() throws ParseException {
        String temp = wait.until(visibilityOfElementLocated(taxValue)).getText();
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        Number number = format.parse(temp);
        return Double.parseDouble(number.toString());

    }

    public double getTotalValue(String coupon) throws ParseException {
        switch (coupon) {
            case "freeship":
                //wait.until(ExpectedConditions.textToBe(free, "Coupon: off25"));
                wait.until(ExpectedConditions.elementToBeSelected(freeShippingRadioButton));
                String free = wait.until(visibilityOfElementLocated(amountValue)).getText();
                NumberFormat formatFree = NumberFormat.getCurrencyInstance(Locale.US);
                Number numberFree = formatFree.parse(free);
                return Double.parseDouble(numberFree.toString());
            case "offcart5":
                wait.until(ExpectedConditions.textToBe(offCart5Coupon, "Coupon: offcart5"));
                String five = wait.until(visibilityOfElementLocated(amountValue)).getText();
                NumberFormat formatFive = NumberFormat.getCurrencyInstance(Locale.US);
                Number numberFive = formatFive.parse(five);
                return Double.parseDouble(numberFive.toString());
            case "off25":
                wait.until(ExpectedConditions.textToBe(off25Coupon, "Coupon: off25"));
                String twentyFive = wait.until(visibilityOfElementLocated(amountValue)).getText();
                NumberFormat formatTwentyFive = NumberFormat.getCurrencyInstance(Locale.US);
                Number numberTwentyFive = formatTwentyFive.parse(twentyFive);
                return Double.parseDouble(numberTwentyFive.toString());
            default:
                String temp = wait.until(visibilityOfElementLocated(amountValue)).getText();
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
                Number number = format.parse(temp);
                return Double.parseDouble(number.toString());
        }
    }

    public double calculateTotalSum(String coupon) throws ParseException {
        switch (coupon) {
            case "":
                return getSubTotalValue() + getStdShippingValue() + getTaxValue();
            case "freeship":
                return getSubTotalValue() + getTaxValue();
            case "offcart5":
                return (getSubTotalValue() + getStdShippingValue() + getTaxValue()) - 5.0;
            case "off25":
                return ((getSubTotalValue() - 0.25 * (getSubTotalValue()) + getTaxValue() + getStdShippingValue()));
            default:
                return getSubTotalValue();
        }
    }
}



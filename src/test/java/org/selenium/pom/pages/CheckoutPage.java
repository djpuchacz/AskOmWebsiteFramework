package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {
     private final By firstNameFld = By.id("billing_first_name");
     private final By lastNameFld = By.id("billing_last_name");
     private final By addressLineOneFld = By.id("billing_address_1");
     private final By billingCityFld = By.id("billing_city");
     private final By billingPostCodeFld = By.id("billing_postcode");
     private final By billingEmailFld = By.id("billing_email");
     private final By placeOrderBtn = By.id("place_order");
     private final By successNotice = By.cssSelector(".woocommerce-notice");

     private final By clickHereToLoginLink = By.className("showlogin");
     private final By usernameFld = By.id("username");
     private final By passwordFld = By.id("password");
     private final By loginBtn = By.name("login");
     private final By overlay = By.cssSelector(".blockUI.blockOverlay");

        //driver.findElement(By.id("billing_address_1")
        //driver.findElement(By.id("billing_last_name")).sendKeys("user");
        //driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        //driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        //driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        //driver.findElement(By.id("billing_email")).sendKeys("askomdch1410@gmail.com");
        //driver.findElement(By.id("place_order")).click();

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameFld).clear();
        driver.findElement(firstNameFld).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        driver.findElement(lastNameFld).clear();
        driver.findElement(lastNameFld).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        driver.findElement(addressLineOneFld).clear();
        driver.findElement(addressLineOneFld).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city){
        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode){
        driver.findElement(billingPostCodeFld).clear();
        driver.findElement(billingPostCodeFld).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
                enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
        return this;
    }

    public CheckoutPage placeOrder(){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE: " + overlays.size());
        if (overlays.size() > 0) {
             new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS ARE INVISIBLE");
        }
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return driver.findElement(successNotice).getText();
    }

    public CheckoutPage clickHereToLoginLink(){
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }

    public CheckoutPage enterUserName(String username){
        driver.findElement(usernameFld).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        driver.findElement(passwordFld).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return this;
    }

    public CheckoutPage login(String username, String password){
       return enterUserName(username).
               enterPassword(password).
               clickLoginBtn();
    }
}

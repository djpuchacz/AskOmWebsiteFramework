package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchFld(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(txt);
        //driver.findElement(searchFld).sendKeys(txt);
        return this;
    }
    public StorePage load(){
        load("/store");
        return this;
    }

    public StorePage search(String txt) {
       enterTextInSearchFld(txt).clickSearchBtn();
        driver.findElement(searchBtn).click();
        return this;
    }

    public StorePage clickSearchBtn(){
       wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }
    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.urlContains("/store"));

    }
    public String getTitle() {
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName) {
        By addToCartBtn = getAddToCartBtnElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}

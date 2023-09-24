package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {
    public ProductThumbnail productThumbnail;
    private final By storeMenuLink = By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public HomePage(WebDriver driver) {
        super(driver);
        //myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public StorePage navigateToStoreUsingMenu() {

        driver.findElement(storeMenuLink).click();
        return new StorePage(driver); // navigate to other page - the same in all websites (fluent interface)
    }
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }
}

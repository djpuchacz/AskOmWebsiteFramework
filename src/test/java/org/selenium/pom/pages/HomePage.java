package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {//175
    private ProductThumbnail productThumbnail;
    private MyHeader myHeader;

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }
    public MyHeader getMyHeader() {
        return myHeader;
    }

    public void setProductThumbnail(ProductThumbnail productThumbnail) {
        this.productThumbnail = productThumbnail;
    }
    public void setMyHeader(MyHeader myHeader) {
        this.myHeader = myHeader;   }

    public HomePage(WebDriver driver) {//175
        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }


}

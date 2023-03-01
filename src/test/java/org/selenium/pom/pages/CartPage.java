package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
   /* private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private  final By cartHeading = By.cssSelector(".has-text-align-center");*/
    @FindBy(css = "td[class='product-name'] a") private WebElement productName;
    @FindBy(how = How.CSS, using = ".checkout-button") private WebElement checkoutBtn;
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);//96
    }

    /*public Boolean isLoaded(){//91
        return wait.until(ExpectedConditions.textToBe(cartHeading, "Cart"));
    }*/

   public String getProductName(){
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();//96
   }

   public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
   }


// driver.findElement(By.cssSelector(".checkout-button")).click();
}

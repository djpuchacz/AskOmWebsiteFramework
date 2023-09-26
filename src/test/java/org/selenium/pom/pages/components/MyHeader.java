package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {
    private final By storeMenuLink = By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver); // navigate to other page - the same in all websites (fluent interface)
    }
}

package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.sql.DriverManager.getDriver;

public class NavigationTest extends BaseTest {
    @Test
    public void navigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();//91
        Assert.assertEquals(storePage.getTitle(), "Store");
    }
}

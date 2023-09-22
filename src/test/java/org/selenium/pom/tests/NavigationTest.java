package org.selenium.pom.tests;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.sql.DriverManager.getDriver;

public class NavigationTest extends BaseTest {
    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {
        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();//91
        Assert.assertEquals(storePage.getTitle(), "Store");
    }
    @Test
    public void navigateFromStoreToProduct() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver()).
                load().
                navigateToTheProduct(product.getId());
        Assert.assertEquals(productPage.getProductTitle(), product.getName());
    }
    @Test
    public void navigateFromHomeToFeaturedProduct() throws IOException, InterruptedException {
        Product product = new Product(1215);
        ProductPage productPage = new HomePage(getDriver()).
                load().
                navigateToTheProduct(product.getId());
        Thread.sleep(5000);
        Assert.assertEquals(productPage.getProductTitle(), product.getName());
    }
}

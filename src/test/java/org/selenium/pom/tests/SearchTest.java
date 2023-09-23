package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.sql.DriverManager.getDriver;

public class SearchTest extends BaseTest { //144
    @Test
    public void searchWithPartialMatch(){
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver()).
                load().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }
    @Test
    public void searchWithExactMatch() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver()).
                load().
                searchExactMatch(product.getName());
        Assert.assertEquals(productPage.getProductTitle(),product.getName());
    }
}

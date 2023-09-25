package org.selenium.pom.tests;

import org.selenium.pom.DataProviders.MyDataProvider;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest  { //145
    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                getProductThumbnail().
                clickAddToCartBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
    @Test
    public void addFeaturedProductToCard() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new HomePage(getDriver()).
                load().
                getProductThumbnail().
                clickAddToCartBtn(product.getName()).
                clickViewCart();
        System.out.println(product.getName());
        System.out.println(cartPage.getProductName());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addFeaturedProductToCardWithDataProvider(Product product) throws IOException { //167-170
        CartPage cartPage = new HomePage(getDriver()).
                load().
                clickAddToCartBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
    @Test
    public void addToCartFromProductPage() throws IOException {
        Product product = new Product(1215);
        String productNameSeparatedByDash = product.getName().toLowerCase().replaceAll(" ", "-");
        ProductPage productPage = new ProductPage(getDriver()).
               loadProduct(productNameSeparatedByDash).
                addToCart();
        Assert.assertTrue(productPage.getAlert().contains("“" + product.getName() +"” has been added to your cart."));
    }


}

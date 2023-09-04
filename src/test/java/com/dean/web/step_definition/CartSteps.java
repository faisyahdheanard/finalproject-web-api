package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.List;

public class CartSteps extends BaseTest {
    CartPage cartPage;
    @When("user click add to cart button for these product:")
    public void userClickAddToCartButtonOnTheseProduct(List<String> products) {
        cartPage = new CartPage(driver);
        cartPage.addProductToCart(products);
    }

    @When("user go to the cart page")
    public void userGoToTheCartPage() throws InterruptedException {
        cartPage = new CartPage(driver);
        Thread.sleep(2000);
        cartPage.goToCartPage();
        cartPage.validateOnCartPage();
    }

    @Then("the cart should contain:")
    public void theCartShoudContain(List<String> products) {
        cartPage.validateProductPresence(products);
    }

    @And("the calculated total price is accurate")
    public void theCalculatedTotalPriceIsAccurate() {
        cartPage.verifyTotalPrice();

    }

    @When("user click add to cart button {int} times for these product:")
    public void userClickAddToCartButtonTimesForTheseProduct(int itemCount, List<String> products) {
        cartPage = new CartPage(driver);
        cartPage.addProductToCart_Quantity(itemCount, products);
    }

    @Then("the cart's item count for these product should be {int}")
    public void theCartSItemCountForProductShouldBe(int itemCount, List<String> products) {
        cartPage.validateProductPresence_Quantity(itemCount, products);
    }

    @And("user click delete button to these product:")
    public void userClickDeleteButtonToTheseProduct(List<String> products) {
        cartPage.deleteProduct(products);
    }

    @When("user click add to cart button for all product")
    public void userClickAddToCartButtonForAllProduct() throws InterruptedException{
        cartPage = new CartPage(driver);
        cartPage.addProductToCart_All();
    }

    @Then("the cart should contain all product")
    public void theCartShouldContainAllProduct() throws InterruptedException {
        Thread.sleep(2000);
        cartPage.validateProductPresence_All();
    }
    @And("user click add to cart for {string} product")
    public void userClickAddToCartForProduct(String itemCount) throws InterruptedException {
        cartPage = new CartPage(driver);
        if(itemCount.equals("one")) {
            Thread.sleep(2000);
            cartPage.addProductToCart(Collections.singletonList("Samsung galaxy s6"));
        } else if(itemCount.equals("all")) {
            Thread.sleep(2000);
            cartPage.addProductToCart_All();
        }
    }

    @Then("user will be directed back to the cart page")
    public void userWillBeDirectedBackToTheCartPage() {
        cartPage = new CartPage(driver);
        cartPage.validateOnCartPage();
    }
}

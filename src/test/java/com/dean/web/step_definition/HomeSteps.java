package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSteps extends BaseTest {
    HomePage homePage;
    @Then("user will be directed back to the home page")
    public void userWillBeDirectedToTheHomePage() {
        homePage = new HomePage(driver);
        homePage.validateOnHomePage();
    }

    @When("user click {string} category button")
    public void userClickCategoryButton(String category) throws InterruptedException{
        Thread.sleep(1500);
        homePage = new HomePage(driver);
        homePage.clickCategory(category);
    }

    @Then("only {string} products should be displayed")
    public void onlyProductsShouldBeDisplayed(String categoru) throws InterruptedException {
        Thread.sleep(1500);
        homePage.validateCategoryPresence(categoru);
    }

    @And("user click Next button")
    public void userClickNextButton() {
        homePage.clickNextButton();
    }

    @When("user click Previous button")
    public void userClickPreviousButton() {
        homePage.clickPreviousButton();
    }

    @Then("the first page of all products is displayed")
    public void theFirstPageOfAllProductsIsDisplayed() throws InterruptedException{
        homePage = new HomePage(driver);
        Thread.sleep(1500);
        homePage.validateTheFirstPageProduct();
    }

    @Then("the second page of products should be displayed")
    public void theSecondPageOfProductsShouldBeDisplayed() throws InterruptedException {
        Thread.sleep(1500);
        homePage.validateTheSecondPageProduct();
    }

    @Then("user will be directed back to the first page of all products")
    public void userWillBeDirectedBackToTheFirstPageOfAllProducts() throws InterruptedException {
        Thread.sleep(1500);
        homePage.validateTheFirstPageProduct();
    }

    @When("user click product name")
    public void userClickProductName() {
        homePage = new HomePage(driver);
        homePage.clickProductName();
    }

    @Then("the product details on the homepage match the product details on the product detail page")
    public void theProductDetailsOnTheHomepageMatchTheProductDetailsOnTheProductDetailPage() throws InterruptedException {
        Thread.sleep(1500);
        homePage.validateMatchProduct();
    }

    @And("user click home menu")
    public void userClickHomeMenu() {
        homePage = new HomePage(driver);
        homePage.clickHomeMenu();
    }
}

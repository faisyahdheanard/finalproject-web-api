package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.HomePage;
import io.cucumber.java.en.Given;

public class BrowserSteps extends BaseTest {
    HomePage homePage;
    @Given("user is on home page")
    public void userIsOnHomePage() {
        homePage = new HomePage(driver);
        homePage.goToHomePage();
    }
}


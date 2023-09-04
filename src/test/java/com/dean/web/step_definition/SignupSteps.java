package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.SignupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupSteps extends BaseTest {
    SignupPage signupPage;

    @When("user click signup menu")
    public void userClickSignupMenu() {
        signupPage = new SignupPage(driver);
        signupPage.gotToSignupPage();
        signupPage.velidateOnSignUpPage();
    }

    @And("user input username with {string}")
    public void userInputUsernameWith(String username) {
        signupPage.inputUsername(username);
    }

    @And("user input password with {string}")
    public void userInputPasswordWith(String password) {
        signupPage.inputPassword(password);
    }

    @And("user click signup button")
    public void userClickSignupButton() {
        signupPage.clickSignUpButton();
    }

    @And("user click close button on signup page")
    public void userClickCloseButtonOnSignupPage() {
        signupPage.clickCloseButton();
    }

    @Then("there will be a message said {string}")
    public void thereWillBeAMessageSaid(String message) throws InterruptedException{
        Thread.sleep(1500);
        signupPage = new SignupPage(driver);
        signupPage.validateMessagePresence(message);
    }
}

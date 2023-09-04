package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest {
    LoginPage loginPage;

    @When("user click login menu")
    public void userClickLoginMenu() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.validateOnLoginPage();
    }

    @And("user click login button")
    public void userClickLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("user login with username {string}")
    public void userLoginWithUsername(String username) {
        loginPage.inputUsername(username);
    }

    @And("user login with password {string}")
    public void userLoginWithPassword(String password) {
        loginPage.inputPassword(password);
    }

    @And("user successfully logged in and there will be an element Welcome {string}")
    public void userSuccessfullyLoggedInAndThereWillBeAnElementWelcome(String username) throws InterruptedException{
        loginPage.validateUserLoggedin(username);
    }

    @And("user click close button on login page")
    public void userClickCloseButtonOnLoginPage() {
        loginPage.clickCloseButton();
    }

    @When("user already logged in")
    public void userAlreadyLoggedIn() throws InterruptedException {
        loginPage = new LoginPage(driver);
        Thread.sleep(1500);
        loginPage.goToLoginPage();
        Thread.sleep(1500);
        loginPage.inputUsername("372742");
        loginPage.inputPassword("brunomars");
        Thread.sleep(1500);
        loginPage.clickLoginButton();
    }
}

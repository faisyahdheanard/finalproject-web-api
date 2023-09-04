package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By loginMenu = By.cssSelector("[id=\"login2\"]");
    By loginTitle = By.cssSelector("[id=\"logInModalLabel\"]");
    By usernameTextBox = By.cssSelector("[id=\"loginusername\"]");
    By passwordTextBox = By.cssSelector("[id=\"loginpassword\"]");
    By closeButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[1]");
    By loginButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    By welcomeElement = By.cssSelector("[id=\"nameofuser\"]");

    public void goToLoginPage() {
        driver.findElement(loginMenu).click();
    }
    public void validateOnLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
        assertTrue(title.isDisplayed());
        assertEquals("Log in", title.getText());
    }

    public void inputUsername(String username) {
        Preferences prefs = Preferences.userRoot().node("com.dean.web");
        switch (username) {
            case "existing_account":
                String existing_uname = prefs.get("created_account", null);
                driver.findElement(usernameTextBox).sendKeys(existing_uname);
                break;
            case "existing_account_with_space":
                String existing_uname_with_space = "   " + prefs.get("created_account", null) + "   ";
                driver.findElement(usernameTextBox).sendKeys(existing_uname_with_space);
                break;
            default:
                driver.findElement(usernameTextBox).sendKeys(username);
                break;
        }
    }

    public void inputPassword(String password) {
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement closeElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeElement.click();
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginElement.click();
    }

    public void validateUserLoggedin(String username) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Preferences prefs = Preferences.userRoot().node("com.dean.web");
        WebElement welcomeUser = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeElement));
        assertTrue(welcomeUser.isDisplayed());

        switch (username) {
            case "existing_account":
                Thread.sleep(2000);
                String existing_uname = prefs.get("created_account", null);
                assertEquals("Welcome " + existing_uname, welcomeUser.getText());
                break;
            default:
                assertEquals("Welcome " + username, welcomeUser.getText());
                break;
        }
    }
}

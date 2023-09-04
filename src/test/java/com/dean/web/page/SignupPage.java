package com.dean.web.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.prefs.Preferences;

import static com.dean.web.page.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupPage {
    WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    By signUpMenu = By.cssSelector("[id=\"signin2\"]");
    By signUpTitle = By.cssSelector("[id=\"signInModalLabel\"]");
    By usernameTextBox = By.cssSelector("[id=\"sign-username\"]");
    By passwordTextBox = By.cssSelector("[id=\"sign-password\"]");
    By closeButton = By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[1]");
    By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");

    public void velidateOnSignUpPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpTitle));
        assertTrue(title.isDisplayed());
        assertEquals("Sign up", title.getText());
    }

    public void gotToSignupPage() {
        driver.findElement(signUpMenu).click();
    }

    public void inputUsername(String username) {
        Preferences prefs = Preferences.userRoot().node("com.dean.web");
        switch (username) {
            case "random_username":
                String randomUsername = generateRandomAlphabetString(10);
                driver.findElement(usernameTextBox).sendKeys(randomUsername);
                prefs.put("created_account", randomUsername);
                break;
            case "random_num":
                String randomNumber = generateRandomNumericString(10);
                driver.findElement(usernameTextBox).sendKeys(randomNumber);
                break;
            case "random_specialChar":
                String randomSpecialChar = generateRandomSpecialCharacterString(10);
                driver.findElement(usernameTextBox).sendKeys(randomSpecialChar);
                break;
            case "existing_account":
                String existing_uname = prefs.get("created_account", null);
                driver.findElement(usernameTextBox).sendKeys(existing_uname);
                break;
            case ">MAX_CHAR":
                String randomMaxChar = generateRandomAlphabetString(64);
                driver.findElement(usernameTextBox).sendKeys(randomMaxChar);
                break;
            case "<MIN_CHAR":
                String randomMinChar = generateRandomAlphabetString(2);
                driver.findElement(usernameTextBox).sendKeys(randomMinChar);
                break;
            case "random_username_with_space":
                String randomUsernameWithSpace = "  " + generateRandomAlphabetString(5) + "  ";
                driver.findElement(usernameTextBox).sendKeys(randomUsernameWithSpace);
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

    public void clickSignUpButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement signupElement = wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        signupElement.click();
    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement closeElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeElement.click();
    }

    public void validateMessagePresence(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        assertEquals(message, alert.getText());
        alert.accept();
    }
}

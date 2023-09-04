package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContactPage {
    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    By contactManu = By.xpath("//a[contains(text(), 'Contact')]");
    By contactTitle = By.cssSelector("#exampleModalLabel");
    By emailTextBox = By.cssSelector("[id=\"recipient-email\"]");
    By nameTextBox = By.id("recipient-name");
    By messageTextBox = By.id("message-text");
    By closeButton = By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[1]");
    By sendMessageButton = By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]");

    public void validateOnContactPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(contactTitle));
        assertTrue(title.isDisplayed());
        assertEquals("New message", title.getText());
    }

    public void goToContactPage() {
        driver.findElement(contactManu).click();
    }

    public void inputEmail(String email) {
        driver.findElement(emailTextBox).sendKeys(email);
    }

    public void inputName(String name) {
        driver.findElement(nameTextBox).sendKeys(name);
    }

    public void inputMessage(String message) {
        switch (message) {
            case "<MIN_CHAR":
                String minChar = Utils.generateRandomAlphabetString(3);
                driver.findElement(messageTextBox).sendKeys(minChar);
                break;
            case ">MAX_CHAR":
                String maxChar = Utils.generateRandomAlphabetString(301);
                driver.findElement(messageTextBox).sendKeys(maxChar);
                break;
            default:
                driver.findElement(messageTextBox).sendKeys(message);
                break;
        }
    }

    public void clickSendMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement sendMessageElement = wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));
        sendMessageElement.click();
    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement closeElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeElement.click();
    }
}

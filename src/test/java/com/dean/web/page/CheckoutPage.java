package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    By checkoutTitle = By.id("orderModalLabel");
    By nameTextBox = By.id("name");
    By countryTextBox = By.id("country");
    By cityTextBox = By.id("city");
    By creditCardTextBox = By.id("card");
    By monthTextBox = By.id("month");
    By yearTextBox = By.id("year");
    By closeButton = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[1]");
    By purchaseButton = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By okButton = By.xpath("//button[contains(text(), 'OK')]");
    By notificationSuccess = By.xpath("/html/body/div[10]/h2");
    By totalFromCheckoutPage = By.id("totalm");

    //FROM CART PAGE
    By totalFromCartPage = By.id("totalp");


    public void clickPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement placeOrderElement = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderElement.click();
    }

    public void validatePaymentFormPresence() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTitle));
        assertTrue(title.isDisplayed());
        assertEquals("Place order", title.getText());

        //VALIDATE THAT THE PRESENT PRICE IS EQUAL FROM THE CART PAGE
        String totalCartPage = driver.findElement(totalFromCartPage).getText();
        String totalCheckoutPage = driver.findElement(totalFromCheckoutPage).getText();

        assertTrue(totalCheckoutPage.contains(totalCartPage));
    }

    public void fillThePaymentDataForm(String name, String country, String city, String creditCard, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameTextBox));
        nameField.sendKeys(name);

        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(countryTextBox));
        countryField.sendKeys(country);

        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(cityTextBox));
        cityField.sendKeys(city);

        WebElement creditCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(creditCardTextBox));
        creditCardField.sendKeys(creditCard);

        WebElement monthField= wait.until(ExpectedConditions.visibilityOfElementLocated(monthTextBox));
        monthField.sendKeys(month);

        WebElement yearField = wait.until(ExpectedConditions.visibilityOfElementLocated(yearTextBox));
        yearField.sendKeys(year);
    }

    public void clickPurchaseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement purchaseElement = wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseElement.click();
    }

    public void verifyTransactionIsSuccessfull(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //ALERT PRESENCE
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationSuccess));
        assertTrue(notification.isDisplayed());
        assertEquals(message, notification.getText());

        driver.findElement(okButton).click();

    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement closeElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeElement.click();
    }

}


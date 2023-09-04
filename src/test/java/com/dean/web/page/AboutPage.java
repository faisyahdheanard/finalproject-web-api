package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AboutPage {
    WebDriver driver;

    public AboutPage(WebDriver driver) {
        this.driver = driver;
    }

    By aboutMenu = By.xpath("//a[contains(text(), 'About us')]");
    By aboutTitle = By.id("videoModalLabel");
    By closeButton = By.xpath("//*[@id=\"videoModal\"]/div/div/div[3]/button");
    By aboutInformation = By.id("example-video");

    public void validateOnAboutPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutTitle));
        assertTrue(title.isDisplayed());
        assertEquals("About us", title.getText());
    }
    public void goToAboutPage() {
        driver.findElement(aboutMenu).click();
    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement closeElement = wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeElement.click();
    }

    public void validateAboutInformation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement information = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutInformation));
        assertTrue(information.isDisplayed());
    }
}

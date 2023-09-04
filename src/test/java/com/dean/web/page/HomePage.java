package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By productStoreTitle = By.cssSelector("[id=\"nava\"]");
    By phonesCategory = By.xpath("//div[@class=\"list-group\"]/a[2]");
    By laptopsCategory = By.xpath("//div[@class=\"list-group\"]/a[3]");
    By monitorsCategory = By.xpath("//div[@class=\"list-group\"]/a[4]");
    By nextButton = By.id("next2");
    By previousButton = By.id("prev2");
    By productCard = By.cssSelector("[class=\"col-lg-4 col-md-6 mb-4\"]");
    By homeMenu = By.xpath("//a[contains(text(), 'Home')]");

    public void goToHomePage() {
        driver.get("https://www.demoblaze.com/");
    }

    public void validateOnHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(productStoreTitle));
        assertTrue(title.isDisplayed());
        assertEquals("PRODUCT STORE", title.getText());
    }

    public void clickCategory(String category) {
        Map<String, By> categoryMap = new HashMap<>();
        categoryMap.put("Phone", phonesCategory);
        categoryMap.put("Laptop", laptopsCategory);
        categoryMap.put("Monitor", monitorsCategory);

        By categoryLocator = categoryMap.get(category);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        if (categoryMap.containsKey(category)) {
            WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(categoryLocator));
            categoryElement.click();
        } else {
            throw new IllegalArgumentException("Category not found: " + category);
        }
    }

    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement nextElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextElement.click();
    }

    public void clickPreviousButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement previousElement = wait.until(ExpectedConditions.elementToBeClickable(previousButton));
        previousElement.click();
    }


    //USING UTILS
    public void validateTheSecondPageProduct() {
        List<String> expectedProductNames = Arrays.asList(
                "Apple monitor 24", "MacBook air", "Dell i7 8gb",
                "2017 Dell 15.6 Inch", "ASUS Full HD", "MacBook Pro"
        );
        Utils.validateProductPresence(driver, productCard, expectedProductNames);
    }

    public void validateTheFirstPageProduct() {
        List<String> expectedProductNames = Arrays.asList(
                "Samsung galaxy s6", "Nokia lumia 1520", "Nexus 6", "Samsung galaxy s7",
                "Iphone 6 32gb", "Sony xperia z5", "HTC One M9", "Sony vaio i5", "Sony vaio i7"
        );
        Utils.validateProductPresence(driver, productCard, expectedProductNames);
    }

    public void validateCategoryPresence(String category) {
        List<String> expectedProductNames = null;
        switch (category) {
            case "Phone":
                expectedProductNames = Arrays.asList(
                        "Samsung galaxy s6", "Nokia lumia 1520", "Nexus 6", "Samsung galaxy s7",
                        "Iphone 6 32gb", "Sony xperia z5", "HTC One M9"
                );
                break;
            case "Laptop":
                expectedProductNames = Arrays.asList(
                        "Sony vaio i5", "Sony vaio i7", "MacBook air",
                        "Dell i7 8gb", "2017 Dell 15.6 Inch", "MacBook Pro"
                );
                break;
            case "Monitor":
                expectedProductNames = Arrays.asList(
                        "Apple monitor 24", "ASUS Full HD"
                );
                break;
        }
        Utils.validateProductPresence(driver, productCard, expectedProductNames);
    }


    public void clickProductName() {
        //SAMSUNG GALAXY S6
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]")));
        productLink.click();
    }

    public void validateMatchProduct() throws InterruptedException {
        driver.navigate().back();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        int totalPages = 2;

        for(int page = 1; page <= totalPages; page++) {
            Thread.sleep(1500);
            int expectedProductCount = driver.findElements(productCard).size();
            for (int numBlock = 1; numBlock <= expectedProductCount; numBlock++) {
                Thread.sleep(1500);
                WebElement productLink = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[" + numBlock + "]/div/div/h4/a"));
                String expectedName = productLink.getText();
                String expectedPrice = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[" + numBlock + "]/div/div/h5")).getText();

                productLink.click();

                Thread.sleep(1500);
                String actualName = driver.findElement(By.cssSelector("h2.name")).getText();
                String actualPrice = driver.findElement(By.className("price-container")).getText();
                Thread.sleep(1500);
                assertEquals(expectedName, actualName);
                assertTrue(actualPrice.contains(expectedPrice));
                driver.navigate().back();
            }
            if (page < totalPages) {
                WebElement nextElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
                nextElement.click();
            }
        }
    }

    public void clickHomeMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement homeElement = wait.until(ExpectedConditions.elementToBeClickable(homeMenu));
        homeElement.click();
    }
}

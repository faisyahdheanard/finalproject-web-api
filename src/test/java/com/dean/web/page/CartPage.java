package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productNameOnHomePage(String name) {
        return By.xpath("//a[contains(text(), '" + name + "')]");
    }

    private By productNameOnCartPage(String name) {
        return By.xpath("//td[contains(text(), '" + name + "')]");
    }

    By cartMenu = By.id("cartur");
    By cartTitle = By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/h2");
    By addToCartButton = By.cssSelector("a.btn.btn-success.btn-lg");
    By totalPriceElement = By.id("totalp");
    By priceCard = By.xpath("//*[@id=\"tbodyid\"]/tr/td[3]");
    By deleteButton = By.xpath("//td[4]/a");


    //FROM HOMEPAGE
    By productCard = By.cssSelector("[class=\"col-lg-4 col-md-6 mb-4\"]");
    By productName = By.xpath(".//div/div/h4/a");
    By nextButton = By.id("next2");


    public void goToCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement cartElement = wait.until(ExpectedConditions.elementToBeClickable(cartMenu));
        cartElement.click();
    }

    public void validateOnCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        assertTrue(title.isDisplayed());
        assertEquals("Products", title.getText());
    }

    public void addProductToCart(List<String> products) {
        for (String product : products) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productNameOnHomePage(product)));
            productLink.click();

            WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartElement.click();

            wait.until(ExpectedConditions.alertIsPresent()).accept();
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    public void validateProductPresence(List<String> products) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        for (String expectedProduct : products) {
            WebElement productsInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameOnCartPage(expectedProduct)));
            String actualProduct = productsInCart.getText();

            assertEquals(expectedProduct, actualProduct);
        }
    }

    public void addProductToCart_Quantity(int itemCount, List<String> products) {
        for (String product : products) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productNameOnHomePage(product)));
            productLink.click();

            WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            for (int i = 1; i <= itemCount; i++) {
                addToCartElement.click();
                wait.until(ExpectedConditions.alertIsPresent()).accept();
            }
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    public void validateProductPresence_Quantity(int itemCount, List<String> products) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        for (String product : products) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productNameOnCartPage(product)));
            int productCount = driver.findElements(productNameOnCartPage(product)).size();

            assertEquals(itemCount, productCount);
        }
    }

    public void verifyTotalPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> priceElements = driver.findElements(priceCard);
        int totalPrice = 0;

        for (WebElement priceElement : priceElements) {
            WebElement price = wait.until(ExpectedConditions.visibilityOf(priceElement));
            String productPrice = price.getText();
            int productPrices = Integer.parseInt(productPrice);

            totalPrice += productPrices;
        }

        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceElement));
        assertEquals(String.valueOf(totalPrice), totalElement.getText());

    }

    public void deleteProduct(List<String> products) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        for (String product : products) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productNameOnCartPage(product)));

            WebElement productRow = driver.findElement(productNameOnCartPage(product));
            WebElement deleteElement = wait.until(ExpectedConditions.elementToBeClickable(productRow.findElement(deleteButton)));

            deleteElement.click();
        }
    }

    public void addProductToCart_All() throws InterruptedException {
        Preferences prefs = Preferences.userRoot().node("com.dean.web");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<String> addedProductNames = new ArrayList<>();
        int totalPages = 2;

        for (int page = 1; page <= totalPages; page++) {
            Thread.sleep(1000);
            List<WebElement> productElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCard));
            for (int i = 0; i < productElements.size(); i++) {
                Thread.sleep(1000);
//                System.out.println("Success Home Page");
                String index_product = String.valueOf(i+1);
                WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(.//div/div/h4/a)["+index_product+"]"))));
                String expectedName = productLink.getText();
                addedProductNames.add(expectedName);
                productLink.click();
//                System.out.println(expectedName);

                WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
                addToCart.click();
//                System.out.println("Success Add To Cart");

                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
//                System.out.println("Success Accept Alert");

                driver.navigate().back();
                driver.navigate().back();
//                System.out.println("Success Navigate Back");

                prefs.put("addedProductNames", String.join(",", addedProductNames));
            }

            if (page < totalPages) {
                Thread.sleep(2000);
                WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
                next.click();
            }
        }
    }
    public void validateProductPresence_All() throws InterruptedException {
        Preferences prefs = Preferences.userRoot().node("com.dean.web");
        String[] expectedNames = prefs.get("addedProductNames", null).split(",");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<String> productOnCart = new ArrayList<>();
        List<WebElement> productsName = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")));

        for (WebElement productName : productsName) {
            String nameOnCart = productName.getText();
            productOnCart.add(nameOnCart);
        }

        List<String> failedAssertions = new ArrayList<>();

        for (String expectedName : expectedNames) {
            if (!productOnCart.contains(expectedName.trim())) {
                failedAssertions.add("Product '" + expectedName + "' is not present in the cart.");
            }
        }

        Thread.sleep(5000);
        assertTrue(failedAssertions.isEmpty(), "One or more assertions failed:\n" + String.join("\n", failedAssertions));
    }
}

package com.dean.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utils {


    //RANDOM STRING (FOR LOGIN AND SIGNUP)
    public static String generateRandomAlphabetString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            randomString.append(alphabet.charAt(index));
        }
        return randomString.toString();
    }

    public static String generateRandomNumericString(int length) {
        String number = "0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(number.length());
            randomString.append(number.charAt(index));
        }
        return randomString.toString();
    }

    public static String generateRandomSpecialCharacterString(int length) {
        String specialChar = "`~!@#$%^&*()_+-=[{]}|;:',></?";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(specialChar.length());
            randomString.append(specialChar.charAt(index));
        }
        return randomString.toString();
    }


    //PRODUCT PRESENCE (FOR HOMEPAGE)
    public static void validateProductPresence(WebDriver driver, By productCard, List<String> expectedProductNames) {
        //VERIFY NUM OF PRODUCT
        List<WebElement> product = driver.findElements(productCard);
        int expectedProductCount = expectedProductNames.size();
        assertEquals(expectedProductCount, product.size());

        //VERIFY NAME OF PRODUCT
        for(int numBlock = 1; numBlock <= expectedProductCount; numBlock++) {
            WebElement actualProductName = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[" + numBlock + "]/div/div/h4/a"));
            assertEquals(expectedProductNames.get(numBlock-1), actualProductName.getText());
        }
    }
}

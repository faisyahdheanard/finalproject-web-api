package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.ContactPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class ContactSteps extends BaseTest {
    ContactPage contactPage;

    @When("user click contact menu")
    public void userClickContactMenu() throws InterruptedException {
        contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        contactPage.validateOnContactPage();
    }

    @And("user input contact email with {string}")
    public void userInputContactEmailWith(String email) {
        contactPage.inputEmail(email);
    }

    @And("user input contact name with {string}")
    public void userInputContactNameWith(String name) {
        contactPage.inputName(name);
    }

    @And("user input message with {string}")
    public void userInputMessageWith(String message) {
        contactPage.inputMessage(message);
    }

    @And("user click send message button")
    public void userClickSendMessageButton() throws InterruptedException {
        Thread.sleep(1500);
        contactPage.clickSendMessage();
    }

    @And("user click Close button on contact page")
    public void userClickCloseButtonOnContactPage() throws InterruptedException {
        Thread.sleep(1500);
        contactPage.clickCloseButton();
    }
}

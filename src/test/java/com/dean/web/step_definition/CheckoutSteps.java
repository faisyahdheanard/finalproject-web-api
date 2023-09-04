package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.Map;

public class CheckoutSteps extends BaseTest {
    CheckoutPage checkoutPage;

    @And("user click Place Order button")
    public void userClickPlaceOrderButton() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPlaceOrder();
    }

    @And("user should be able to see payment data form")
    public void userShouldBeAbleToSeePaymentDataForm() throws InterruptedException {
        Thread.sleep(3000);
        checkoutPage.validatePaymentFormPresence();
    }

    @And("user fill the payment data with:")
    public void userFillThePaymentDataWith(Map<String, String> paymentData) throws InterruptedException{
        String name = paymentData.get("Name");
        String country = paymentData.get("Country");
        String city = paymentData.get("City");
        String creditCard = paymentData.get("Credit card");
        String month = paymentData.get("Month");
        String year = paymentData.get("Year");

        checkoutPage.fillThePaymentDataForm(name, country, city, creditCard, month, year);
    }


    @And("click Purchase button")
    public void clickPurchaseButton() throws InterruptedException {
        Thread.sleep(1500);
        checkoutPage.clickPurchaseButton();
    }

    @Then("the transaction is successful and there will be a pop up said {string}")
    public void theTransactionIsSuccessfulAndThereWillBeAPopUpSaid(String message) {
        checkoutPage.verifyTransactionIsSuccessfull(message);
    }

    @And("user click Close button on payment page")
    public void userClickCloseButtonOnPaymentPage() {
        checkoutPage.clickCloseButton();
    }

    @Then("<message>")
    public void message() {
    }

    @And("user fill the payment data {string}")
    public void userFillThePaymentDataWithBlankData(String dataType) {
        if(dataType.equals("with blank data")) {
            checkoutPage.fillThePaymentDataForm("", "", "", "", "", "");
        } else if(dataType.equals("without name")) {
            checkoutPage.fillThePaymentDataForm("", "country", "city", "777", "12", "2002");
        } else if(dataType.equals("without city and country")) {
            checkoutPage.fillThePaymentDataForm("Faisyah Dheana", "", "", "777", "12", "2002");
        } else if(dataType.equals("without credit card")) {
            checkoutPage.fillThePaymentDataForm("Faisyah Dheana", "country", "city", "", "12", "2002");
        } else if(dataType.equals("without month and year")) {
            checkoutPage.fillThePaymentDataForm("Faisyah Dheana", "country", "city", "777", "", "");
        }
    }
}

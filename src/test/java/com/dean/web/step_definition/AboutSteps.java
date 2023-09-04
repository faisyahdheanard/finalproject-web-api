package com.dean.web.step_definition;

import com.dean.web.BaseTest;
import com.dean.web.page.AboutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AboutSteps extends BaseTest {
    AboutPage aboutPage;
    @When("user click About us menu")
    public void userClickAboutUsMenu() {
        aboutPage = new AboutPage(driver);
        aboutPage.goToAboutPage();
        aboutPage.validateOnAboutPage();

    }

    @Then("user should be able to see information about the company's")
    public void userShouldBeAbleToSeeInformationAboutTheCompanyS() {
        aboutPage.validateAboutInformation();
    }

    @And("user click Close button on About us page")
    public void userClickCloseButtonOnAboutUsPage() throws InterruptedException{
        Thread.sleep(1500);
        aboutPage = new AboutPage(driver);
        aboutPage.clickCloseButton();
    }
}

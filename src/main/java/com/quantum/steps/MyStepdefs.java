package com.quantum.steps;

import com.codoid.products.exception.FilloException;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.quantum.Utils.CommonMethods;
import com.quantum.pages.NewUserRegistration;
import com.quantum.pages.ShoppingCart;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import parallelTest.CrossBrowserScript;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;

import java.io.IOException;

import static com.quantum.Utils.CommonMethods.driver;
//import static parallelTest.CrossBrowserScript.driver;

@QAFTestStepProvider
public class MyStepdefs {

    CommonMethods cm = new CommonMethods();
    CrossBrowserScript cs = new CrossBrowserScript();

    @Given("^I am on Naveen Automation Labs page$")
    public void iAmOnNaveenAutoLabs() throws Exception {
//        new CrossBrowserScript().setup("Chrome");
        driver.get(ConfigurationManager.getBundle().getPropertyValue("baseurl"));
        driver.manage().window().maximize();
    }

    @When("I read the TestData for the Test ID \"([^\"]*)\" from the sheet \"([^\"]*)\"$")
    public void iReadTheTestData(String TestID, String SheetName) throws IOException {
            cm.getScenarioTestData(TestID, SheetName);
    }

    @Then("^I Register the Account for New User$")
    public void iRegisterNewUserAccount() {
        new NewUserRegistration().clickOnRegisterLink();
        new NewUserRegistration().fillUserDetailsAndCreateTheAccount();
        new NewUserRegistration().verifyUserCreatedConfirmationMessage();
    }

    @Then("^I Verify the most expensive laptop is macbook pro$")
    public void iVerifyMostExpensiveLaptop() {
        new ShoppingCart().verifyMostExpensiveLaptop();
    }

    @Then("^I add products to the cart$")
    public void iAddProductsToCart() {

        new ShoppingCart().addProductsToCart();
    }
    @Then("^I Verify the total cost of Items$")
    public void iVerifyTotalCostofItems() {

        new ShoppingCart().verifyTotalCostofItems();
    }
    @And("^I Logout the application$")
    public void iSignedoff() {

        new NewUserRegistration().iLoggedOffTheApplication();
    }
    @Then("^I Verify user able to access for \"([^\"]*)\"$")
    public void iVerifyUserAccess(String TestID) throws FilloException, IOException {
        new NewUserRegistration().enterUserDetails();
        new NewUserRegistration().iVerifyUserAccessedTheApplication(TestID);

    }

}

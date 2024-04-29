package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InventoryPage;
import pages.LoginPage;

public class Steps {

    private final LoginPage login;
    private final InventoryPage inventory;
    public Steps() {

        login = new LoginPage(BaseSteps.driver);
        inventory = new InventoryPage(BaseSteps.driver);
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        login.loadLoginPage();
    }

    @When("I enter Username as {string} and Password as {string}")
    public void iEnterUsernameAsAndPasswordAs(String user, String password) {
        login.enterUsername(user);
        login.enterPassword(password);
    }

    @And("I click on the Login button")
    public void iClickOnTheLoginButton() {
        login.clickLoginButton();
    }

    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        inventory.landingInventory();
    }
}

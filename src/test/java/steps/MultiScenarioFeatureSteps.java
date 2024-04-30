package steps;

import common.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

import java.util.ArrayList;
import java.util.List;

// This is the class for the Cucumber step definitions for multiple scenarios.
public class MultiScenarioFeatureSteps {

    // Define a Utilities object for utility functions.
    private final Utilities util = new Utilities();

    // Define page objects for each of the pages in the application.
    private final LoginPage login;
    private final GeneralPage generalPage;
    private final InventoryPage inventory;
    private final CartPage cart;
    private final CheckoutPage checkout;
    private final CheckoutOverviewPage checkoutOverview;

    // Define lists to store product prices and cart prices for comparison.
    private List<String> productPrices = new ArrayList<>();
    private List<String> cartPrices = new ArrayList<>();

    // Constructor for the MultiScenarioFeatureSteps class.
    public MultiScenarioFeatureSteps(){
        // Initialize the page objects.
        login = new LoginPage(BaseSteps.driver);
        generalPage = new GeneralPage(BaseSteps.driver);
        inventory = new InventoryPage(BaseSteps.driver);
        cart = new CartPage(BaseSteps.driver);
        checkout = new CheckoutPage(BaseSteps.driver);
        checkoutOverview = new CheckoutOverviewPage(BaseSteps.driver);
    }

    // Step definition for the scenario step "I am on the SauceDemo login page".
    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        // Load the login page.
        login.loadLoginPage();
    }

    // Step definition for the scenario step "I enter Username as {string} and Password as {string}".
    @When("I enter Username as {string} and Password as {string}")
    public void iEnterUsernameAsAndPasswordAs(String user, String password) {
        // Enter the username and password.
        login.enterUsername(user);
        login.enterPassword(password);
    }

    // Step definition for the scenario step "I click on the Login button".
    @And("I click on the Login button")
    public void iClickOnTheLoginButton() {
        // Click the login button.
        login.clickLoginButton();
    }

    // Step definition for the scenario step "I should get an error message indicating the user is locked".
    @Then("I should get an error message indicating the user is locked")
    public void iShouldSeeAnErrorMessageIndicatingTheUserIsLocked() {
        // The expected error message.
        String message = "Epic sadface: Sorry, this user has been locked out.";
        // Check if the error message is as expected.
        login.iShouldSeeAnErrorMessageIndicatingTheUserIsLocked(message);
    }

    // Step definition for the scenario step "I login to the SauceDemo website with valid credentials".
    @Given("I login to the SauceDemo website with valid credentials")
    public void loginToTheSauceDemoWebsiteWithValidCredentials() {
        // The valid credentials.
        String user = "standard_user";
        String password = "secret_sauce";
        // Load the login page, enter the credentials, and click the login button.
        login.loadLoginPage();
        login.enterUsername(user);
        login.enterPassword(password);
        login.clickLoginButton();
    }

    // Step definition for the scenario step "I should see {string}".
    @Then("I should see {string}")
    public void iShouldSee(String expected) {
        // Check if the expected text is displayed.
        login.iShouldSee(expected);
    }

    // Step definition for the scenario step "I click on menu icon on top left of the header".
    @Then("I click on menu icon on top left of the header")
    public void iClickOnMenuIconOnTopLeftOfTheHeader() {
        // Click the menu icon.
        generalPage.clickOnMenuIconOnTopLeftOfTheHeader();
    }

    // Step definition for the scenario step "I click on {string} button on the displayed menu".
    @And("I click on {string} button on the displayed menu")
    public void clickOnButtonByNameOnTheDisplayedMenu(String buttonName) {
        // Click the button with the given name.
        generalPage.clickOnButtonByNameOnTheDisplayedMenu(buttonName);
    }

    // Step definition for the scenario step "Verify prices captured from Products page are the same as Cart page".
    @And("Verify prices captured from Products page are the same as Cart page")
    public void verifyPricesCapturedFromProductsPageAreTheSameAsCartPage() {
        // Check if the prices captured from the Products page are the same as those from the Cart page.
        Assert.assertEquals(productPrices, cartPrices);
    }

    // Step definition for the scenario step "I should be redirected to the home page".
    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        // Check if the user is redirected to the home page.
        inventory.landingInventory();
    }

    // Step definition for the scenario step "I add {string} to the cart from the Products page".
    @When("I add {string} to the cart from the Products page")
    public void iAddProductToTheCartFromTheProductsPage(String productName) {
        // Add the product with the given name to the cart.
        inventory.addProductByNameToCart(productName);
    }

    // Step definition for the scenario step "I click on the cart icon on Products Page".
    @Then("I click on the cart icon on Products Page")
    public void clickOnTheCartIconOnProductsPage() {
        // Click the cart icon.
        inventory.clickOnCartIcon();
    }

    // Step definition for the scenario step "I change the Product Sort to {string} on the Products page".
    @When("I change the Product Sort to {string} on the Products page")
    public void changeTheProductSortToParameterOnTheProductsPage(String sortType) {
        // Click the sort type button and change the product sort type.
        inventory.clickOnSortTypeButtonOnProductsPage();
        inventory.changeProductSortByType(sortType);
    }

    // Step definition for the scenario step "Verify the displayed name on the sort filter is {string}".
    @And("Verify the displayed name on the sort filter is {string}")
    public void verifyTheDisplayedNameOnTheSortFilterIsAName(String sortType) {
        // Check if the displayed name on the sort filter is as expected.
        inventory.verifyTheDisplayedNameOnTheSortFilterIs(sortType);
    }

    // Step definition for the scenario step "Verify prices from products are in ascending order".
    @And("Verify prices from products are in ascending order")
    public void verifyPricesFromProductsAreInAscendingOrder() {
        // Check if the prices from the products are in ascending order.
        inventory.verifyPricesFromProductsAreInAscendingOrder();
    }

    // Step definition for the scenario step "Verify the Remove button is enabled for product {string}".
    @And("Verify the Remove button is enabled for product {string}")
    public void verifyTheRemoveButtonIsEnabledForProductByName(String productName) {
        // Check if the Remove button is enabled for the product with the given name.
        inventory.verifyTheRemoveButtonIsEnabledForProductByName(productName);
    }

    // Step definition for the scenario step "I save the price of product {string} from Product page".
    @Then("I save the price of product {string} from Product page")
    public void saveThePriceOfProductNameFromProductPage(String productName) {
        // Save the price of the product with the given name from the Product page.
        productPrices = inventory.saveThePriceOfProductNameFromProductPage(productName);
    }

    // Step definition for the scenario step "Verify the value from cart is {string}".
    @And("Verify the value from cart is {string}")
    public void verifyTheValueFromCartIsANumber(String number) {
        // Check if the value from the cart is as expected.
        cart.verifyTheValueFromCartIs(number);
    }

    // Step definition for the scenario step "I click on {string} on the Cart page".
    @When("I click on {string} on the Cart page")
    public void iClickOnButtonNameOnTheCartPage(String buttonName) {
        // Click the button with the given name on the Cart page.
        cart.clickOnButtonOnCartPage(buttonName);
    }

    // Step definition for the scenario step "I save the price of product {string} from Cart page".
    @Then("I save the price of product {string} from Cart page")
    public void saveThePriceOfProductByNameFromCartPage(String productName) {
        // Save the price of the product with the given name from the Cart page.
        cartPrices = cart.saveThePriceOfProductByNameFromCartPage(productName);
    }

    // Step definition for the scenario step "I remove the product {string} on the Cart page".
    @Then("I remove the product {string} on the Cart page")
    public void removeTheProductNameOnTheCartPage(String productName) {
        // Remove the product with the given name on the Cart page.
        cart.removeProductByNameOnCartPage(productName);
    }

    // Step definition for the scenario step "I capture the quantity of items added to cart from {string} on the Cart page".
    @Then("I capture the quantity of items added to cart from {string} on the Cart page")
    public void captureTheQuantityOfItemsAddedToCartFromANameOnTheCartPage(String productName) {
        // Capture the quantity of items added to cart from the product with the given name on the Cart page.
        cart.captureTheQuantityOfItemsAddedToCartFromANameOnTheCartPage(productName);
    }

    // Step definition for the scenario step "I capture the value of cart from the Cart page".
    @Then("I capture the value of cart from the Cart page")
    public void captureTheValueOfCartFromTheCartPage() {
        // Capture the value of the cart from the Cart page.
        cart.captureTheValueOfCartFromTheCartPage();
    }

    // Step definition for the scenario step "Verify quantity and value are the same as captured on Cart page".
    @And("Verify quantity and value are the same as captured on Cart page")
    public void verifyQuantityAndValueAreTheSameAsCapturedOnCartPage() {
        // Check if the quantity and value are the same as those captured on the Cart page.
        cart.verifyQuantityAndValueAreTheSameAsCapturedOnCartPage();
    }

    // Step definition for the scenario step "I fill the checkout information with random data".
    @Then("I fill the checkout information with random data")
    public void iFillTheCheckoutInformationWithRandomData() {
        // Generate random data and fill the checkout information with it.
        String randomData = util.randomAlphanumeric(6);
        checkout.fillCheckoutInformation(randomData);
    }

    // Step definition for the scenario step "I click on continue button on checkout page".
    @Then("I click on continue button on checkout page")
    public void clickOnContinueButtonOnCheckoutPage() {
        // Click the continue button on the checkout page.
        checkout.clickOnContinueButtonOnCheckoutPage();
    }

    // Step definition for the scenario step "I click on Finish on checkout overview page".
    @Then("I click on Finish on checkout overview page")
    public void clickOnFinishOnCheckoutOverviewPage() {
        // Click the Finish button on the checkout overview page.
        checkoutOverview.clickOnFinishOnCheckoutOverviewPage();
    }

    // Step definition for the scenario step "Verify item total is same first product price captured".
    @Then("Verify item total is same first product price captured")
    public void verifyItemTotalIsSameFirstProductPriceCaptured() {
        // Check if the item total is the same as the first product price captured.
        checkoutOverview.verifyItemTotalIsSameFirstProductPriceCaptured(productPrices);
    }

    // Step definition for the scenario step "Capture message {string} from checkout complete page".
    @And("Capture message {string} from checkout complete page")
    public void captureAMessageFromCheckoutCompletePage(String message) {
        // Capture the message from the checkout complete page.
        checkoutOverview.captureAMessageFromCheckoutCompletePage(message);
    }
}


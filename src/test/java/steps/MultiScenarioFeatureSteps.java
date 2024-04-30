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

public class MultiScenarioFeatureSteps {

    private final Utilities util = new Utilities();
    private final LoginPage login;
    private final GeneralPage generalPage;
    private final InventoryPage inventory;
    private List<String> productPrices = new ArrayList<>();
    private final CartPage cart;
    private List<String> cartPrices = new ArrayList<>();
    private final CheckoutPage checkout;
    private final CheckoutOverviewPage checkoutOverview;

    public MultiScenarioFeatureSteps(){
        login = new LoginPage(BaseSteps.driver);
        generalPage = new GeneralPage(BaseSteps.driver);
        inventory = new InventoryPage(BaseSteps.driver);
        cart = new CartPage(BaseSteps.driver);
        checkout = new CheckoutPage(BaseSteps.driver);
        checkoutOverview = new CheckoutOverviewPage(BaseSteps.driver);
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


    @Then("I should get an error message indicating the user is locked")
    public void iShouldSeeAnErrorMessageIndicatingTheUserIsLocked() {
        String message = "Epic sadface: Sorry, this user has been locked out.";
        login.iShouldSeeAnErrorMessageIndicatingTheUserIsLocked(message);
    }

    @Given("I login to the SauceDemo website with valid credentials")
    public void loginToTheSauceDemoWebsiteWithValidCredentials() {
        String user = "standard_user";
        String password = "secret_sauce";
        login.loadLoginPage();
        login.enterUsername(user);
        login.enterPassword(password);
        login.clickLoginButton();
    }

    @Then("I should see {string}")
    public void iShouldSee(String expected) {
        login.iShouldSee(expected);
    }

    @Then("I click on menu icon on top left of the header")
    public void iClickOnMenuIconOnTopLeftOfTheHeader() {
        generalPage.clickOnMenuIconOnTopLeftOfTheHeader();
    }

    @And("I click on {string} button on the displayed menu")
    public void clickOnButtonByNameOnTheDisplayedMenu(String buttonName) {
        generalPage.clickOnButtonByNameOnTheDisplayedMenu(buttonName);
    }

    @And("Verify prices captured from Products page are the same as Cart page")
    public void verifyPricesCapturedFromProductsPageAreTheSameAsCartPage() {
        Assert.assertEquals(productPrices, cartPrices);
    }

    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        inventory.landingInventory();
    }

    @When("I add {string} to the cart from the Products page")
    public void iAddProductToTheCartFromTheProductsPage(String productName) {
        inventory.addProductByNameToCart(productName);
    }

    @Then("I click on the cart icon on Products Page")
    public void clickOnTheCartIconOnProductsPage() {
        inventory.clickOnCartIcon();
    }

    @When("I change the Product Sort to {string} on the Products page")
    public void changeTheProductSortToParameterOnTheProductsPage(String sortType) {
        inventory.clickOnSortTypeButtonOnProductsPage();
        inventory.changeProductSortByType(sortType);
    }

    @And("Verify the displayed name on the sort filter is {string}")
    public void verifyTheDisplayedNameOnTheSortFilterIsAName(String sortType) {
        inventory.verifyTheDisplayedNameOnTheSortFilterIs(sortType);
    }

    @And("Verify prices from products are in ascending order")
    public void verifyPricesFromProductsAreInAscendingOrder() {
        inventory.verifyPricesFromProductsAreInAscendingOrder();
    }

    @And("Verify the Remove button is enabled for product {string}")
    public void verifyTheRemoveButtonIsEnabledForProductByName(String productName) {
        inventory.verifyTheRemoveButtonIsEnabledForProductByName(productName);
    }

    @Then("I save the price of product {string} from Product page")
    public void saveThePriceOfProductNameFromProductPage(String productName) {
        productPrices = inventory.saveThePriceOfProductNameFromProductPage(productName);
    }

    @And("Verify the value from cart is {string}")
    public void verifyTheValueFromCartIsANumber(String number) {
        cart.verifyTheValueFromCartIs(number);
    }

    @When("I click on {string} on the Cart page")
    public void iClickOnButtonNameOnTheCartPage(String buttonName) {
        cart.clickOnButtonOnCartPage(buttonName);
    }

    @Then("I save the price of product {string} from Cart page")
    public void saveThePriceOfProductByNameFromCartPage(String productName) {
        cartPrices = cart.saveThePriceOfProductByNameFromCartPage(productName);
    }

    @Then("I remove the product {string} on the Cart page")
    public void removeTheProductNameOnTheCartPage(String productName) {
        cart.removeProductByNameOnCartPage(productName);
    }

    @Then("I capture the quantity of items added to cart from {string} on the Cart page")
    public void captureTheQuantityOfItemsAddedToCartFromANameOnTheCartPage(String productName) {
        cart.captureTheQuantityOfItemsAddedToCartFromANameOnTheCartPage(productName);
    }

    @Then("I capture the value of cart from the Cart page")
    public void captureTheValueOfCartFromTheCartPage() {
        cart.captureTheValueOfCartFromTheCartPage();
    }

    @And("Verify quantity and value are the same as captured on Cart page")
    public void verifyQuantityAndValueAreTheSameAsCapturedOnCartPage() {
        cart.verifyQuantityAndValueAreTheSameAsCapturedOnCartPage();
    }

    @Then("I fill the checkout information with random data")
    public void iFillTheCheckoutInformationWithRandomData() {
        String randomData = util.randomAlphanumeric(6);
        checkout.fillCheckoutInformation(randomData);
    }

    @Then("I click on continue button on checkout page")
    public void clickOnContinueButtonOnCheckoutPage() {
        checkout.clickOnContinueButtonOnCheckoutPage();
    }

    @Then("I click on Finish on checkout overview page")
    public void clickOnFinishOnCheckoutOverviewPage() {
        checkoutOverview.clickOnFinishOnCheckoutOverviewPage();
    }

    @Then("Verify item total is same first product price captured")
    public void verifyItemTotalIsSameFirstProductPriceCaptured() {
        checkoutOverview.verifyItemTotalIsSameFirstProductPriceCaptured(productPrices);
    }

    @And("Capture message {string} from checkout complete page")
    public void captureAMessageFromCheckoutCompletePage(String message) {
        checkoutOverview.captureAMessageFromCheckoutCompletePage(message);
    }
}

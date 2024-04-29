package steps;

import io.cucumber.java.en.Given;
import pages.LoginPage;

public class Steps {

    private final LoginPage login;

    public Steps() {

        login = new LoginPage(BaseSteps.driver);
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        login.loadLoginPage();
    }
}

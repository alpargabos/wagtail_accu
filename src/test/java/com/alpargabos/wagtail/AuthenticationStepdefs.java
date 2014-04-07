package com.alpargabos.wagtail;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class AuthenticationStepdefs extends BaseStepdefs{

    @Given("^I am a twitter user")
    public void I_am_a_twitter_user() throws Throwable {
        username = "wagtailbirdapp";
    }

    @When("^I grant access to my account for Wagtail$")
    public void I_grant_access_to_my_account_for_Wagtail() throws Throwable {
        input = createInputStreamFrom("1234567");
        initWagtail(simulator.getServerForLogin(username));
        wagtail.login();
    }

    @Then("^I will be greeted on my full name$")
    public void I_will_be_greeted_on_my_full_name() throws Throwable {
        assertTrue(output.toString().contains("You are logged in as: " + username));
    }

    @When("^I don't grant access to my account for Wagtail$")
    public void I_don_t_grant_access_to_my_account_for_Wagtail() throws Throwable {
        input = createInputStreamFrom("1234567");
        initWagtail(simulator.getServerForInvalidLogin());
        wagtail.login();
    }

    @Then("^I will be notified about the unsuccessful login$")
    public void I_will_be_notified_about_the_unsuccessful_login() throws Throwable {
        assertTrue(output.toString().contains("pin code is not valid"));
    }
}

package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.PrintWriter;

import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthenticationStepdefs{

    private String username;
    private Wagtail wagtail;
    private TwitterSimulator simulator = new TwitterSimulator();
    private Reader input = mock(Reader.class);
    private PrintWriter writer = mock(PrintWriter.class);

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Given("^I am a twitter user")
    public void I_am_a_twitter_user() throws Throwable {
        username = "wagtailbirdapp";
    }

    @When("^I grant access to my account for Wagtail$")
    public void I_grant_access_to_my_account_for_Wagtail() throws Throwable {
        MockWebServer server = simulator.getServerForLogin(username);
        wagtail = new Wagtail(server.getUrl("/twitter/"));
        wagtail.setInput(input);
        wagtail.setOutput(writer);
        wagtail.login();
    }

    @Then("^I will be greeted on my full name$")
    public void I_will_be_greeted_on_my_full_name() throws Throwable {
        verify(writer).write(matches("You are logged in as: " + username));
    }

}

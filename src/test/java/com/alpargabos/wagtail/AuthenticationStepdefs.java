package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertTrue;

public class AuthenticationStepdefs {

    private String username;
    private Wagtail wagtail;
    private TwitterSimulator simulator = new TwitterSimulator();
    private InputStream input;
    private OutputStream output;


    @Before
    public void setUp() {
        output = new OutputStream() {
            StringBuilder content = new StringBuilder();

            @Override
            public void write(int i) throws IOException {
                System.out.print((char) i);
                content.append((char) i);
            }
            public String toString() {
                return content.toString();
            }
        };
    }

    @After
    public void tearDown() {

    }

    @Given("^I am a twitter user")
    public void I_am_a_twitter_user() throws Throwable {
        username = "wagtailbirdapp";
    }

    @When("^I grant access to my account for Wagtail$")
    public void I_grant_access_to_my_account_for_Wagtail() throws Throwable {
        initWagtail(simulator.getServerForLogin(username));
        wagtail.login();
    }

    private InputStream createInputStreamFrom(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    @Then("^I will be greeted on my full name$")
    public void I_will_be_greeted_on_my_full_name() throws Throwable {
        assertTrue(output.toString().contains("You are logged in as: " + username));
    }

    @When("^I don't grant access to my account for Wagtail$")
    public void I_don_t_grant_access_to_my_account_for_Wagtail() throws Throwable {
        initWagtail(simulator.getServerForInvalidLogin());
        wagtail.login();
    }

    @Then("^I will be notified about the unsuccessful login$")
    public void I_will_be_notified_about_the_unsuccessful_login() throws Throwable {
        assertTrue(output.toString().contains("pin code is not valid"));
    }

    private void initWagtail(MockWebServer server) {
        wagtail = new Wagtail(server.getUrl("/twitter/"));
        input = createInputStreamFrom("1234567");
        wagtail.setInput(input);
        wagtail.setOutput(output);
    }

}

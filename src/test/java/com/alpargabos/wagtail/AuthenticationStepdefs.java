package com.alpargabos.wagtail;

import com.squareup.okhttp.mockwebserver.MockWebServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationStepdefs {

    private String username;
    private Wagtail wagtail;
    private TwitterSimulator simulator = new TwitterSimulator();
    private Reader input = mock(Reader.class);
    private PrintWriter writer;
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
        writer = new PrintWriter(output);
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
        MockWebServer server = simulator.getServerForLogin(username);
        wagtail = new Wagtail(server.getUrl("/twitter/"));
        wagtail.setInput(input);
        wagtail.setOutput(writer);

        when(input.getUserInput()).thenReturn("1234567");
        wagtail.login();
    }

    @Then("^I will be greeted on my full name$")
    public void I_will_be_greeted_on_my_full_name() throws Throwable {
        assertTrue(output.toString(),output.toString().contains("You are logged in as: " + username));
    }

}

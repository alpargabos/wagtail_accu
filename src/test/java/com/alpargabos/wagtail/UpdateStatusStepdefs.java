package com.alpargabos.wagtail;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class UpdateStatusStepdefs extends BaseStepdefs{

    @Given("^I am wagtail user$")
    public void I_am_wagtail_user() throws Throwable {
        username = "wagtailbirdapp";
    }

    @When("^I update my status to: \"([^\"]*)\"$")
    public void I_update_my_status_to(String text) throws Throwable {
        input = createInputStreamFrom("1234567\n"+text+"\n");
        initWagtail(simulator.getServerForStatusUpdate(text));
        wagtail.login();
        wagtail.writeStatus();
    }

    @Then("^the following will appear on my time line$")
    public void the_following_will_appear_on_my_time_line(String status) throws Throwable {
        assertTrue("Output: [" + output.toString() + "] not contains "+ status, output.toString().contains(status));
    }
}

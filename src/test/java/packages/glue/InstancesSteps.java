package packages.glue;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import packages.Helpers.RequestManager;

public class InstancesSteps {

    public Response response;
    public RequestManager requestManager = new RequestManager();
    public String endpoint;


    @Given("^I have \"([^\"]*)\" endpoint$")
    public void iHaveEndpoint(String function) {

        endpoint = requestManager.getMainEndpoint() + requestManager.getFunction(function);
        Serenity.getCurrentSession().put("endpoint",endpoint);

    }

    @When("^I send a request to that endpoint")
    public void iSendARequestFor() {
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint);

    }

    @Then("^I should see \"([^\"]*)\" value$")
    public void iShouldSeeValue(String instanceValue) {

        requestManager.assertInstance(instanceValue, response);

    }


    @And("^Response should has a (\\d+) status code$")
    public void responseShouldHasAStatusCode(int code) {
        requestManager.assertStatusCode(code, response);

    }
}

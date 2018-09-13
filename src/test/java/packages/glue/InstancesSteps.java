package packages.glue;


import packages.Helpers.RequestManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

public class InstancesSteps {

    public Response response;
    public RequestManager requestManager = new RequestManager();
    public String endpoint;
    public String hostValue = "localhost";
    public String portValue = "7070";
    public String storeNamesValue = "[[all-songs, song-play-count, top-five-songs, top-five-songs-by-genre]]";


    @Given("^I want to search the \"([^\"]*)\"$")
    public void iWantToSearchThe(String function) {

        endpoint = requestManager.getMainEndpoint() + requestManager.getFunction(function);
        Serenity.getCurrentSession().put("endpoint", endpoint);

    }

    @When("^I made the search")
    public void iSendARequestFor() {
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint);

    }

    @Then("^I should see all the instances")
    public void iShouldSeeAllTheInstances() {
        requestManager.assertStatusCode(200, response);
        requestManager.assertInstance("host", hostValue, response);
        requestManager.assertInstance("port", portValue, response);
        requestManager.assertInstance("storeNames", storeNamesValue, response);

    }

}

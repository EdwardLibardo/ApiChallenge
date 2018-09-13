package packages.glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import packages.Helpers.RequestManager;

public class TopFiveSteps {
    private RequestSpecification query;
    public Response response;
    public RequestManager requestManager = new RequestManager();
    public String endpoint;
    public String topFiveSongs = "";

    @Given("^I want to see the top five of songs with their characteristics$")
    public void iHaveSomeSongs() {
        endpoint = requestManager.getMainEndpoint();
    }

    @When("^I search the \"([^\"]*)\" of songs$")
    public void iSearchTheTopFiveOfTheSongsList(String function) {
        endpoint += requestManager.getFunction(function);
        Serenity.getCurrentSession().put("endpoint", endpoint);
        topFiveSongs = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint);
    }


    @Then("^I can verify that there are (\\d+) songs$")
    public void iCanVerifyThatThereAreFiveSongs(int numberOfSongs) {
        requestManager.assertNumberOfSongs(numberOfSongs, response);
    }


}

package packages.glue;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import packages.Helpers.RequestManager;

public class GenreSteps {

    public Response response;
    public RequestManager requestManager = new RequestManager();


    @When("^I send a request for a \"([^\"]*)\"$")
    public void iSendARequestForA(String genre) {
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint + genre);
    }


    @Then("^I see the top (\\d+) song$")
    public void iSeeTheTopSong(int songs) {
        requestManager.assertNumberOfSongs(songs, response);

    }


    @Then("^I don't get songs$")
    public void iDonTGetSongs() {
        requestManager.assertStatusCode(404, response);

    }
}

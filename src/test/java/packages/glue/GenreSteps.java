package packages.glue;

import packages.Helpers.RequestManager;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

public class GenreSteps {

    public Response response;
    public RequestManager requestManager = new RequestManager();

    @When("^I made a search for a genre$")
    public void iSendARequestForAGenre(DataTable genre){
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint + genre.raw().get(0).get(0));
    }

    @Then("^I can see the top (\\d+) songs$")
    public void iSeeTheTopSong(int songs) {

        requestManager.assertNumberOfSongs(songs, response);

    }


    @Then("^I don't get any song$")
    public void iDonTGetSongs() {
        requestManager.assertStatusCode(404, response);

    }


}

package packages.glue;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import packages.Helpers.RequestManager;


public class IdSteps {

    public Response response;
    public RequestManager requestManager = new RequestManager();
    public String endpoint;
    private String specificSong = "";

    @Given("^I can find a \"([^\"]*)\"$")
    public void iHaveSomeSongsWithId(String function) {
        endpoint = requestManager.getMainEndpoint() + requestManager.getFunction(function);
        Serenity.getCurrentSession().put("endpoint", endpoint);
        specificSong = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint);
    }

    @When("^I search this song by id$")
    public void iWriteAOfTheSong(DataTable songId) {
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint + songId.raw().get(1).get(0));
    }

    @Then("^I can verify that this is the desired song$")
    public void iCanVerifyThatThisIsTheDesiredSong(DataTable song) {
        requestManager.assertSongCharacteristics(song.raw(), response);
    }

    //---------------------------------------------------------------------------------

    @When("^I try to search a song with invalid id")
    public void iTryToSearch(DataTable songId) {
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint + songId.raw().get(1).get(0));
    }

    @Then("^I can not find a the song and i receive a error message$")
    public void iCanNotFindATheSongAndIReceiveAErrorMessage() {
        requestManager.assertStatusCode(404, response);
        System.out.println("Error... The son can not be founded");
    }


}

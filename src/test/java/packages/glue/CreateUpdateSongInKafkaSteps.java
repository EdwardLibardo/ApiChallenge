package packages.glue;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import packages.Helpers.RequestManager;
import packages.kafkaApi.ProducerSong;

public class CreateUpdateSongInKafkaSteps {
    private RequestManager requestManager = new RequestManager();
    private ProducerSong producerSong;
    private Response response;


    public CreateUpdateSongInKafkaSteps() {
        producerSong = new ProducerSong();
    }

    @Given("^I have a new song to upload$")
    public void iHaveANewSongToUpload(DataTable song) {
        producerSong.createSong(Long.parseLong(song.raw().get(1).get(0)), song.raw().get(1).get(1),
                song.raw().get(1).get(2), song.raw().get(1).get(3), song.raw().get(1).get(4));
    }

    @When("^I upload the new song$")
    public void iUploadTheNewSong() {
        producerSong.sendSong();
    }

    @And("^I search the song in the application$")
    public void iSearchTheSongInTheApplication(DataTable songId) {
        String endpoint = (String) Serenity.getCurrentSession().get("endpoint");
        response = requestManager.sendRequest(endpoint + songId.raw().get(1).get(0));
    }

    @Then("^I should see the song fields$")
    public void iShouldSeeTheSongFields(DataTable song) {
        requestManager.assertStatusCode(200,response);
        requestManager.assertSongCharacteristics(song.raw(),response);
    }

    @Given("^I have song Uploaded$")
    public void iHaveSongUploaded(DataTable song) {
        iHaveANewSongToUpload(song);
        iUploadTheNewSong();
    }

    @And("^I modify the song$")
    public void iModifyTheSong(DataTable song) {
        iHaveANewSongToUpload(song);
    }

    @And("^I delete the song$")
    public void iDeleteTheSong(DataTable id){
        producerSong.createSong();
        producerSong.sendSong(Long.parseLong(id.raw().get(1).get(0)));
    }

    @Then("^I should get a response with no songs$")
    public void iShouldGetAResponseWithNoSongs(){
        requestManager.assertStatusCode(404,response);
    }
}

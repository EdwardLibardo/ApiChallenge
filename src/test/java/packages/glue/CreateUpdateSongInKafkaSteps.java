package packages.glue;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import packages.kafkaApi.ProducerSong;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.CoreMatchers.containsString;

public class CreateUpdateSongInKafkaSteps {
    private ProducerSong producerSong;
    private Properties properties = new Properties();
    private Response response;
    private String url;
    private RequestSpecification query;


    public CreateUpdateSongInKafkaSteps() {
        try {
            properties.load(new FileReader("src/test/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url-song");
        producerSong = new ProducerSong();
    }

    @Given("^I am a producer of the application$")
    public void iAmAProducerOfTheApplication() {

    }

    @And("^I create a new valid song with an id (\\d+)$")
    public void iCreateANewValidSongWithAnId(long id) {
        producerSong.createSong(id, properties.getProperty("song-album"), properties.getProperty("song-artist"),
                properties.getProperty("song-name"), properties.getProperty("song-genre"));
    }

    @When("^I send the song to the application$")
    public void iSendTheSongToTheApplication() {
        producerSong.sendSong();
    }

    @And("^I send a request to the API looking for the song with an id (\\d+)$")
    public void iSendARequestToTheAPILookingForTheSongWithAnId(int id) {
        response = this.query.when().get(url + String.valueOf(id));
    }

    @Then("^I should receive a (\\d+) status code$")
    public void iShouldReceiveAStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @And("^I should receive all the song fields directly from the API$")
    public void iShouldReceiveAllTheSongFieldsDirectlyFromTheAPI() {
        response.then().assertThat().body(containsString("{\n" +
                "    \"artist\": \"" + properties.getProperty("song-artist") + "\",\n" +
                "    \"album\": \"" + properties.getProperty("song-album") + "\",\n" +
                "    \"name\": \"" + properties.getProperty("song-name") + "\"\n" +
                "}"));
    }

    @And("^I modify the song with an id (\\d+) and send it again to the application$")
    public void iModifyTheSongWithAnIdAndSendItAgainToTheApplication(long id) {
        producerSong.createSong(id, properties.getProperty("song-album-mod"), properties.getProperty("song-artist-mod"),
                properties.getProperty("song-name-mod"), properties.getProperty("song-genre-mod"));
    }

    @And("^I should receive all the song modified fields directly from the API$")
    public void iShouldReceiveAllTheSongModifiedFieldsDirectlyFromTheAPI() {
        response.then().assertThat().body(containsString("{\n" +
                "    \"artist\": \"" + properties.getProperty("song-artist-mod") + "\",\n" +
                "    \"album\": \"" + properties.getProperty("song-album-mod") + "\",\n" +
                "    \"name\": \"" + properties.getProperty("song-name-mod") + "\"\n" +
                "}"));
    }

    @Given("^I have a new song to upload$")
    public void iHaveANewSongToUpload(DataTable song) {
        List<List<String>> songFields = song.raw();
        producerSong.createSong(Long.parseLong(songFields.get(1).get(0)), songFields.get(1).get(1),
                songFields.get(1).get(2), songFields.get(1).get(3), songFields.get(1).get(4));
    }

    @When("^I upload the new song$")
    public void iUploadTheNewSong() {
        producerSong.sendSong();
    }

    @And("^I search the song in the application$")
    public void iSearchTheSongInTheApplication(DataTable songId) {
        query = given().contentType(ContentType.JSON);
        response = this.query.when().get(url + songId.raw().get(1).get(0));
    }

    @Then("^I should see the song fields$")
    public void iShouldSeeTheSongFields(DataTable song) {
        List<List<String>> songFields = song.raw();
        response.then().assertThat().body(containsString("{\"artist\":\""+ songFields.get(1).get(0)+"\",\"album\":\""+ songFields.get(1).get(1)+"\",\"name\":\""+ songFields.get(1).get(2)+"\"}"));
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
}

package packages.pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import packages.Helpers.JsonHelper;
import packages.Helpers.PropertiesReader;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;


public class Methods {

    private RequestSpecification query;
    private Response response;
    PropertiesReader reader;
    Properties props = new Properties();
    String topFiveEndpoint = "";
    String idEndpoint = "";
    JsonHelper jsonHelper = new JsonHelper();
    JsonArray topFiveJson;
    JsonObject chemicalWarfareSong;

    public void readMainEndpoint() {
        reader = new PropertiesReader();
        System.out.println("main endpoint " + reader.getMenu());
    }

    public void addTopFiveToTheMainUrl() {
        topFiveEndpoint = reader.getMenu() + reader.getTopFive();
        System.out.println("top five endpoint " + topFiveEndpoint);
        query = given();
        response = query.when().get(topFiveEndpoint);
    }


    public void VerifyThatThereAreFiveSongs(int numberOfSongs) {
        topFiveJson = jsonHelper.getJsonObjectListFromResponse(response.then());
        Assert.assertEquals("It was expected " + numberOfSongs + " but the result was: " + topFiveJson.size(), topFiveJson.size(), numberOfSongs);
        System.out.println(topFiveJson.size());
    }


    public void PrintTheSongCalledChemicalWarfare() {
        response.then().assertThat().statusCode(200).assertThat().body(containsString("name"));
        response.getBody().print();
    }


    public void SearchChemicalWarfareSong() {
        idEndpoint = reader.getMenu() + reader.getid() + "1";
        System.out.println("Id endpoint " + idEndpoint);
        query = given();
        response = query.when().get(idEndpoint);
    }

    public void SaveTheSongCalledChemicalInJsonObject(String artist) {
        chemicalWarfareSong = jsonHelper.getJsonObjectFromResponse(response.then());
        String actualArtists = chemicalWarfareSong.get("artist").getAsString();
        Assert.assertEquals(actualArtists, artist);
    }


    public void addTheInvalidIdToTheEndpoint(String id) {
        idEndpoint = reader.getMenu() + reader.getid() + id;
        System.out.println("Id endpoint " + idEndpoint);
        query = given();
        response = query.when().get(idEndpoint);
    }

    public void obtain404AsResponse() {
        response.then().assertThat().statusCode(404);
    }

    public void messageWithTheIdInvalid() {
        System.out.println("With this id you can not search a song");
    }


}
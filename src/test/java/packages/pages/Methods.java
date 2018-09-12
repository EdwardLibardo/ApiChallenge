package packages.pages;

import com.google.gson.JsonArray;
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


    public void SaveFiveSongs() {


    }

    public void VerifyThatThereAreFiveSongs() {
        topFiveJson = jsonHelper.getJsonObjectListFromResponse(response.then());
        System.out.println(topFiveJson+" 5 canciones");
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

    public void SaveTheSongCalledChemicalInJsonObject() {
        JsonHelper jsonHelper = new JsonHelper();
        JsonArray genres;
    }

    public void VerifyTheSongChemical() {
        topFiveJson = jsonHelper.getJsonObjectListFromResponse(response.then());
        Assert.assertEquals("It was expected  but the result was: " + topFiveJson.size(), topFiveJson.size());
    }


    public void addTheInvalidIdtoTheEndpoint(String id) {
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
package packages.Helpers;

import com.google.gson.JsonArray;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.CoreMatchers.containsString;

public class RequestManager {

    public static Properties properties;

    public RequestManager() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/config.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public String getMainEndpoint() {
        return properties.getProperty("mainEndpoint");
    }

    @Step
    public String getFunction(String function) {
        return properties.getProperty(function);
    }

    @Step
    public Response sendRequest(String endpoint) {
        return given().contentType("application/json").when().get(endpoint);
    }

    @Step
    public void assertInstance(String instanceValue, String value, Response response) {
        Assert.assertTrue(response.then().extract().response().getBody().jsonPath().get(instanceValue).toString().contains(value));
    }

    @Step
    public void assertStatusCode(int code, Response response) {
        response.then().assertThat().statusCode(code);
    }

    @Step
    public void assertNumberOfSongs(int songs, Response response) {
        JsonHelper jsonHelper = new JsonHelper();
        JsonArray songsList;
        songsList = jsonHelper.getJsonObjectListFromResponse(response.then());
        Assert.assertEquals("It was expected " + songs + " but the result was: " + songsList.size(), songsList.size(), songs);
    }

    @Step
    public void assertSongCharacteristics(List<List<String>> songFields, Response response) {
        response.then().assertThat().body(containsString("{\"artist\":\"" + songFields.get(1).get(0)
                + "\",\"album\":\"" + songFields.get(1).get(1) + "\",\"name\":\"" + songFields.get(1).get(2) + "\"}"));
    }



}

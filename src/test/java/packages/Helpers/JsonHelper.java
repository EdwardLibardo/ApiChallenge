package packages.Helpers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class JsonHelper {

    public JsonObject getJsonObjectFromResponse(ValidatableResponse response) {
        String jsonString = response.extract().response().body().print();
        return new JsonParser().parse(jsonString).getAsJsonObject();
    }

    public JsonArray getJsonObjectListFromResponse(ValidatableResponse response) {
        String jsonString = response.extract().response().body().print();
        return new JsonParser().parse(jsonString).getAsJsonArray();
    }

}



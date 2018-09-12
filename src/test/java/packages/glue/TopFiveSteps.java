package packages.glue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import packages.pages.Methods;

public class TopFiveSteps {
    private RequestSpecification query;
    private Response response;
    Methods TopFive = new Methods();

    @Given("^I obtain the endpoint from the properties file$")
    public void iHaveSomeSongs() {
        TopFive.readMainEndpoint();
    }

    @When("^I search the top five of songs$")
    public void iSearchTheTopFiveOfTheSongsList() {
        TopFive.addTopFiveToTheMainUrl();
    }

    @Then("^I should receive 5 songs$")
    public void iCanVerifyThatIObtainFiveSongs() {
        TopFive.PrintTheSongCalledChemicalWarfare();
    }


    @And("^I can verify that there are (\\d+) songs$")
    public void iCanVerifyThatThereAreSongs(int numberOfSongs)  {
       TopFive.VerifyThatThereAreFiveSongs(numberOfSongs);
    }
}

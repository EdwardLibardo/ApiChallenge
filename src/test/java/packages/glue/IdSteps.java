package packages.glue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import packages.pages.Methods;

import static io.restassured.RestAssured.given;

public class IdSteps {
    Methods songwithid = new Methods();
    Methods songWithIvalidID = new Methods();

    @Given("^I can find the Chemical Warfare song$")
    public void iHaveSomeSongsWithId() {
        songwithid.readMainEndpoint();
    }

    @When("^I search this song$")
    public void iWriteAOfTheSong() {
        songwithid.SearchChemicalWarfareSong();
    }

    @Then("^I can see the song and its characteristics$")
    public void iCanSeeTheSongAndItsCharacteristics() {
        songwithid.PrintTheSongCalledChemicalWarfare();
    }

    @And("^I can verify that this is the desired song$")
    public void iCanVerifyThatThisIsTheDesiredSong() {
        songwithid.SaveTheSongCalledChemicalInJsonObject("Dead Kennedys");
    }


    //---------------------------------------------------------------------------------

    @Given("^There are a few songs with a valid id$")
    public void thereAreAFewSongsWithAValidId() {
        songWithIvalidID.readMainEndpoint();
    }

    @When("^I write a wrong \"([^\"]*)\"$")
    public void iWriteAWrong(String invalidid) {
        songWithIvalidID.addTheInvalidIdToTheEndpoint(invalidid);
    }

    @Then("^I can not find a song in the data base$")
    public void iCanNotFindASongInTheDataBase() {
        songWithIvalidID.obtain404AsResponse();
    }

    @And("^I obtain a message of error$")
    public void iObtainAMessageOfError() {
        songWithIvalidID.messageWithTheIdInvalid();
    }


}

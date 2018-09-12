package packages.glue;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import packages.pages.Methods;

import static io.restassured.RestAssured.given;

public class idSteps {
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

    @Given("^That i have the Chemical Warfare Song$")
    public void thatIHaveTheChemicalWarfareSong() {
        songwithid.SaveTheSongCalledChemicalInJsonObject();
    }

    @When("^I verify that this song is the same with the expected son$")
    public void iVerifyThatThisSongIsTheSameWithTheExpectedSon() {
    songwithid.VerifyTheSongChemical();
    }

    @Then("^I can obtain the same characteristics$")
    public void iCanObtainTheSameCharacteristics() {

    }

    //---------------------------------------------------------------------------------

    @Given("^There are a few songs with a valid id$")
    public void thereAreAFewSongsWithAValidId() {
        songWithIvalidID.readMainEndpoint();
    }

    @When("^I write a wrong \"([^\"]*)\"$")
    public void iWriteAWrong(String invalidid) {
        songWithIvalidID.addTheInvalidIdtoTheEndpoint(invalidid);
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

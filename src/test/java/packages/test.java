package packages;
        import cucumber.api.CucumberOptions;
        import cucumber.api.java.After;
        import cucumber.api.java.Before;
        import net.serenitybdd.cucumber.CucumberWithSerenity;
        import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features",
        glue = "packages")


public class test {

}

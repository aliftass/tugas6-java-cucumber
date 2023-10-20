package saucedemo.cucumber.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/saucedemo/cucumber/features",
        glue = "saucedemo.cucumber.stepdef",
        plugin = "html:target/HTML_report.html",
        tags = "@Regression"
)

public class run {
}

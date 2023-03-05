package org.example.AcceptableTest;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "use-cases",
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"org.example.AcceptableTest"}
        )

public class acceptableTest {
}

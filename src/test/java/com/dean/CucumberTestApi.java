package com.dean;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.dean.api"},
        features = {"src/test/resources/api"},
        plugin = {"pretty","html:reports/test-report-api.html", "json:reports/test-report-api.json"}
)

public class CucumberTestApi {
}

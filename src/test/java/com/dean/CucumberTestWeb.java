package com.dean;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.dean.web"},
        features = {"src/test/resources/web"},
        plugin = {"pretty","html:reports/test-report-web.html", "json:reports/test-report-web.json"}
)
public class CucumberTestWeb {
}

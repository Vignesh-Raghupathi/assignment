package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= "Feature", 
 				 glue="testcases", 
 				 stepNotifications=true,
 				 plugin = { "pretty","html:reports/cucumber-reports.html" },
 				 monochrome = true)
public class RunnerClass {

}

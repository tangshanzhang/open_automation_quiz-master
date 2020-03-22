package Runner;

import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"/TestNGProject/open_automation_quiz-master/src/main/resources/features/test.feature"},
		glue=("cn.ianzhang.automation"),
		tags = {"@tag"},
		plugin = {"json:target/cucumber/cucumber.json", "html:target/cucumber", "pretty", "junit:target/junit"})

public class CucumberRunner extends AbstractTestNGCucumberTests{

	
}

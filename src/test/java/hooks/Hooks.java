package hooks;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;
import utils.ExtentManager;

public class Hooks {

    public static ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        DriverFactory.initDriver();
        test = ExtentManager.getInstance().createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            test.fail("Test Failed");
        } else {
            test.pass("Test Passed");
        }

        ExtentManager.getInstance().flush();
        DriverFactory.getDriver().quit();
    }
}
package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        DriverFactory.initDriver();
        System.out.println("Starting Scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());
        } else {
            System.out.println("Scenario Passed: " + scenario.getName());
        }

        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }
    }
}
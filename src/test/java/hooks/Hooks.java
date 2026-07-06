package hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {

        System.out.println("======================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("======================================");

        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        try {

            if (scenario.isFailed()) {

                System.out.println("Scenario Failed: " + scenario.getName());

                if (driver != null) {

                    File screenshot = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

                    File destination = new File(
                            "target/screenshots/"
                                    + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                                    + ".png");

                    destination.getParentFile().mkdirs();

                    Files.copy(
                            screenshot.toPath(),
                            destination.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);

                    System.out.println(
                            "Screenshot saved: "
                                    + destination.getAbsolutePath());

                    byte[] screenshotBytes =
                            ((TakesScreenshot) driver)
                                    .getScreenshotAs(OutputType.BYTES);

                    scenario.attach(
                            screenshotBytes,
                            "image/png",
                            "Failure Screenshot");
                }

            } else {
                System.out.println("Scenario Passed: " + scenario.getName());
            }

        } catch (IOException e) {

            System.out.println(
                    "Screenshot capture failed: " + e.getMessage());

        } finally {

            try {

                if (driver != null) {
                    driver.quit();
                    System.out.println("Browser closed successfully.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error while closing browser: "
                                + e.getMessage());
            }

            System.out.println("======================================");
            System.out.println("Completed Scenario: " + scenario.getName());
            System.out.println("======================================");
        }
    }
}
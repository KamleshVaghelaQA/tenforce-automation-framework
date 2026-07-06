package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false"));

        try {

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                System.out.println("Running in HEADLESS mode");

                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            } else {
                System.out.println("Running in NORMAL mode");
            }

            driver = new ChromeDriver(options);

        } catch (Exception e1) {

            System.out.println("Chrome not available, trying Firefox");

            try {
                driver = new FirefoxDriver();
            } catch (Exception e2) {

                System.out.println("Firefox not available, trying Edge");
                driver = new EdgeDriver();
            }
        }

        if (!headless) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
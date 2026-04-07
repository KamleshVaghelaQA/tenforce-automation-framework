package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver initDriver() {

		try {
			driver = new ChromeDriver();
		} catch (Exception e1) {
			System.out.println("Chrome not available, trying Firefox");
			try {
				driver = new FirefoxDriver();
			} catch (Exception e2) {
				System.out.println("Firefox not available, trying Edge");
				driver = new EdgeDriver();
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
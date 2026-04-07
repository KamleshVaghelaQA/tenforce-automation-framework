package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait; // ✅ Declare wait

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ✅ Initialize wait
	}

	private By agreeButton = By.xpath("//button[contains(text(),'I Agree')]");
	
	private By aboutUsLocator = By.linkText("About us");
	
	private By careerLocator = By.xpath("//a[normalize-space()='Careers']");
	
	// ✅ Handle Cookie Popup
	public void handleCookiePopup() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(agreeButton));

			if (driver.findElement(agreeButton).isDisplayed()) {
				driver.findElement(agreeButton).click();
				System.out.println("Cookie popup handled");
			}

		} catch (TimeoutException e) {
			System.out.println("No cookie popup displayed");
		} catch (Exception e) {
			System.out.println("Popup not found, continuing execution");
		}
		 
	    JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void openWebsite() {
		driver.get("https://www.tenforce.com/");
		handleCookiePopup(); // ✅ call here
	}
	
	public void clickCareer() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);

	    try {
	        // Wait for About Us to be clickable
	        WebElement aboutUs = wait.until(
	                ExpectedConditions.elementToBeClickable(aboutUsLocator)
	        );

	        // Scroll into view
	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].scrollIntoView({block:'center'});", aboutUs);

	        // Hover 
	        actions.moveToElement(aboutUs).perform();

	        // Wait for Career link
	        WebElement career = wait.until(
	                ExpectedConditions.elementToBeClickable(careerLocator)
	        );

	        // Click with fallback
	        try {
	            career.click();
	        } catch (Exception e) {
	            ((JavascriptExecutor) driver)
	                    .executeScript("arguments[0].click();", career);
	        }

	        // Validate navigation
	        wait.until(ExpectedConditions.or(
	                ExpectedConditions.urlContains("career"),
	                ExpectedConditions.urlContains("careers")
	        ));

	        System.out.println("Career page opened successfully");

	    } catch (TimeoutException e) {
	        System.out.println("Timeout: About/Career not clickable");
	        System.out.println("Current URL: " + driver.getCurrentUrl());
	        throw e;

	    } catch (StaleElementReferenceException e) {
	        System.out.println("Stale element, retrying...");

	        // Retry once
	        WebElement career = wait.until(
	                ExpectedConditions.elementToBeClickable(careerLocator)
	        );
	        career.click();

	    } catch (Exception e) {
	        System.out.println("Error while clicking Career: " + e.getMessage());
	        System.out.println("Current URL: " + driver.getCurrentUrl());
	        throw new RuntimeException("Failed to click Career link", e);
	    }
	}
	
}
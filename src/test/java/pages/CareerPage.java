package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CareerPage {

    WebDriver driver;
    WebDriverWait wait;

    public CareerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
    private By lifeAtTenforce = By.cssSelector("a[data-type='people']");
    private By peopleSection = By.xpath("//article[@data-filter-type='people' and not(contains(@style,'display: none'))]");
    private By internsArticle = By.partialLinkText("Life of two interns");
    private By jobSectionText = By.xpath("//a[contains(@href,'jobs@tenforce.com')]");
  

    public void openLifeAtTenforce() {
        try {
            // Click tab
            WebElement tab = wait.until(
                    ExpectedConditions.elementToBeClickable(lifeAtTenforce)
            );

            tab.click();

            // ✅ Wait for content to appear (NOT URL)
            wait.until(ExpectedConditions.visibilityOfElementLocated(peopleSection));

            System.out.println("Life at Tenforce section opened successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void openInternArticle() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(internsArticle)
            );
            element.click();
            System.out.println("Opened Intern Article");

        } catch (TimeoutException e) {
            System.out.println("Timeout: Intern article not clickable");
        } catch (Exception e) {
            System.out.println("Error opening Intern article: " + e.getMessage());
        }
    }  
    
    public void verifyJobSectionText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String expectedFullText = "Feel free to send your CV to jobs@tenforce.com";
     //   String expectedText = "Feel free to send your CV";

        try {
            // Wait for element
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(jobSectionText)
            );

            // Get full text (includes both spans)
            String actualText = element.getText().trim();

            System.out.println("Actual Text: " + actualText);

            // Assertion (use contains - more stable)
            if (!actualText.toLowerCase().contains(expectedFullText.toLowerCase())) {
                throw new AssertionError(
                        "❌ Text mismatch! Expected to contain: [" + expectedFullText +
                        "] but found: [" + actualText + "]"
                );
            }

            System.out.println("✅ Job openings text verified successfully");

        } catch (TimeoutException e) {
            System.out.println("❌ Element not visible within timeout");
            throw new AssertionError("Job section not found", e);

        } catch (Exception e) {
            System.out.println("❌ Error during verification: " + e.getMessage());
            throw new RuntimeException("Text verification failed", e);
        }
    }
}
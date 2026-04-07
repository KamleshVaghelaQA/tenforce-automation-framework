package stepdefinations;

import io.cucumber.java.en.*;
import pages.ArticlePage;
import pages.CareerPage;
import pages.HomePage;
import utils.DriverFactory;

import org.openqa.selenium.WebDriver;



public class CareerSteps {

	WebDriver driver;

	HomePage homePage;
	CareerPage careerPage;
	ArticlePage articlePage;

	@Given("User launches Tenforce website")
	public void user_launches_tenforce_website() {

		driver = DriverFactory.getDriver();

		homePage = new HomePage(driver);
		careerPage = new CareerPage(driver);
		articlePage = new ArticlePage(driver);
		
		// Launch URL from config
		homePage.openWebsite();

	}

	@When("User navigates to Career page")
	public void user_navigates_to_career_page() throws InterruptedException {

		homePage.clickCareer();
	}

	@When("User opens Life at Tenforce section")
	public void user_opens_life_at_tenforce_section() {

		careerPage.openLifeAtTenforce();
	}

	@When("User opens {string} article")
	public void user_opens_article(String articleName) {

		careerPage.openInternArticle();

		// Optional validation (good practice)
		String title = driver.getTitle();
		org.junit.Assert.assertTrue(title.contains("Life"));
	}

	@When("User scrolls through the article")
	public void user_scrolls_through_the_article() {

		articlePage.scrollArticle();
	}

	@Then("User verifies job section text")
	public void user_verifies_job_section_text() {
		homePage.clickCareer();
		careerPage.verifyJobSectionText();
	}
}
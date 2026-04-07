package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ArticlePage {

    WebDriver driver;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollArticle() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

   /* public void goBack() {
        driver.navigate().back();
        driver.navigate().back();
    }
   */ 
}
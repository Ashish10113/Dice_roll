
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
public class CheckSauceLabsHomePage {
@Test

public void site_header_is_on_home_page() {

    WebDriver browser = new FirefoxDriver();

    //Firefox's proxy driver executable is in a folder already
    //  on the host system's PATH environment variable.
    browser.get("http://saucelabs.com");
    WebElement header = browser.findElement(By.id("page-header"));
    assertTrue((header.isDisplayed()));

    browser.close();        
   }
}
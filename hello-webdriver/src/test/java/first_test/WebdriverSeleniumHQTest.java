package first_test;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WebdriverSeleniumHQTest {

    @Test
    public void commonTermSearchResultsNotEmpty() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get("http://seleniumhq.org");
        WebElement dd = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='search']")));
        dd.sendKeys("selenium");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("bla - bla - bla");

        List<WebElement> results = wait.until(new Function<WebDriver, List<WebElement>>(){
            public List<WebElement> apply(WebDriver driver){
                return driver.findElements(By.xpath("//td[@class='gssb_e']//tr/td[not(@class)]"));
            }
        });

        Assert.assertTrue(results.size()>0, "The list is empty");

        Thread.sleep(10000);
        driver.quit();
    }
}

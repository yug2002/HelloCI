package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class HelloWebdriver {
    public static void main(String [] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get("http://seleniumhq.org");
        WebElement dd = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name='search']")));

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("bla - bla - bla");

        List<WebElement> results = wait.until(new Function<WebDriver, List<WebElement>>(){
            public List<WebElement> apply(WebDriver driver){
                return driver.findElements(By.xpath("//*[@name='search']"));
            }
        });
        results.get(0).sendKeys("selenium");

        Thread.sleep(10000);
        driver.quit();
    }
}

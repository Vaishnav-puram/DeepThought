import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.management.StringValueExp;
import java.time.Duration;

public class InvalidCredentials {
    WebDriver driver;

    @BeforeTest
    public void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeTest
    public void setupFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @DataProvider(name = "browsers")
    public Object[][] getBrowsers() {
        return new Object[][]{
                {new ChromeDriver(), "https://beta.deepthought.education/login"},
                {new FirefoxDriver(), "https://beta.deepthought.education/login"}
        };
    }

    @Test(dataProvider = "browsers")
    public void loginFailure(WebDriver driver, String url){
        driver.get(url);
        driver.findElement(By.id("username")).sendKeys("vaishnav");
        driver.findElement(By.id("password")).sendKeys("vaishnav");
        driver.findElement(By.id("login")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String actual=driver.findElement(By.xpath("//p[contains(text(),'Invalid login credentials')]")).getText();
        Assert.assertEquals(actual,"Invalid login credentials");
        driver.close();
    }
}

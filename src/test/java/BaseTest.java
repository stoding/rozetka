import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.Homepage;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Homepage homepage = new Homepage(driver);
        homepage.openHomePage();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import steps.BaseSteps;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeClass
    public void openHomePage() {
        BaseSteps baseSteps = new BaseSteps(driver);
        baseSteps.navigateToHomePage();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}

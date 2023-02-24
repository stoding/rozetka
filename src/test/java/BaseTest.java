import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.open;

@Guice
public class BaseTest {
    protected static final String ROZETKA_HOMEPAGE_URL = "https://rozetka.com.ua/";
    public static WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        Configuration.timeout = 10000;
        open(ROZETKA_HOMEPAGE_URL);
    }



    @AfterTest
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}

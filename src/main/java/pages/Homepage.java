package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class Homepage extends BasePage {

    public static final String LOGIN_FIELD_VALIDATION_MESSAGE_XPATH = "//input[@id='auth_email']/following-sibling::p";
    public static final String LOGIN_WINDOW_XPATH = "//h3[contains(text(),'Вход')]";
    public static final String LOGIN_FIELD_XPATH = "//input[@id='auth_email']";
    public static final String LOGIN_ICON_XPATH = "//li[@class='header-actions__item header-actions__item--user']//button";
    public static final String ROZETKA_HOMEPAGE_ADDRESS = "https://rozetka.com.ua/";
    public static final String LOGIN_BUTTON_XPATH = "//button[contains(text(), 'Войти')]";

    public Homepage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(ROZETKA_HOMEPAGE_ADDRESS);
    }

    public boolean loginIconClick() {
        driver.findElement(By.xpath(LOGIN_ICON_XPATH)).click();
        return isDisplayed(LOGIN_WINDOW_XPATH);
    }


    public void inputLogin(String login) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("auth_email")))).sendKeys(login, Keys.TAB);
    }

    public String getValidationMessage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        if (isDisplayed(LOGIN_FIELD_VALIDATION_MESSAGE_XPATH)) {
            return driver.findElement(By.xpath(LOGIN_FIELD_VALIDATION_MESSAGE_XPATH)).getText();
        } else return "";
    }

    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("auth_pass")))).sendKeys(password, Keys.TAB);
    }

    public void loginButtonClick() {
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }

    public void inputSearchQuery(String input) {
        driver.findElement(By.name("search")).sendKeys(input);
    }

    public List<WebElement> getSearchSuggestionList() {
        return driver.findElements(By.xpath("//ul[@class='search-suggest__group']//a"));
    }

    public void clickSearchButton() {
        driver.findElement(By.xpath("//button[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']")).click();
    }

    public void openElectronicsCategory() {
        driver.findElement(By.xpath("//li/a[@class='menu-categories__link' and contains(text(),'Смартфоны, ТВ и электроника')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='portal__heading ng-star-inserted']"))));
    }

    public void openAppliancesCategory() {
        driver.findElement(By.xpath("//div[@class='fat-wrap']//a[contains(@href,'https://bt.rozetka.com.ua/')]")).click();
    }
}

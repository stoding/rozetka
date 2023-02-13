package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Homepage extends BasePage {

    private static final String LOGIN_FIELD_VALIDATION_MESSAGE = "//input[@id='auth_email']/following-sibling::p";
    private static final String LOGIN_WINDOW = "//h3[contains(text(),'Вход')]";
    private static final String LOGIN_ICON = "//li[@class='header-actions__item header-actions__item--user']//button";
    private static final String LOGIN_BUTTON = "//button[contains(text(), 'Войти')]";
    private static final String SEARCH_FIELD = "//input[@name='search']";
    private static final String SEARCH_SUGGESTION_LIST = "//ul[@class='search-suggest__group']//a";
    private static final String LOGIN_FIELD = "//input[@id='auth_email']";

    public Homepage(WebDriver driver) {
        super(driver);
    }


    public void loginIconClick() {
        driver.findElement(By.xpath(LOGIN_ICON)).click();

    }

    public boolean loginWindowIsDisplayed() {
        return isDisplayed(LOGIN_WINDOW, 2000);
    }

    public void inputLogin(String login) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("auth_email")))).sendKeys(login, Keys.TAB);
    }

    public String getValidationMessage() {
        if (isDisplayed(LOGIN_FIELD_VALIDATION_MESSAGE, 100)) {
            return driver.findElement(By.xpath(LOGIN_FIELD_VALIDATION_MESSAGE)).getText();
        } else return "";
    }

    public void searchFieldClear() {
        fieldClear(SEARCH_FIELD);
    }

    public void inputPassword(String password) {
        driver.findElement(By.id("auth_pass")).sendKeys(password, Keys.TAB);
    }

    public void loginButtonClick() {
        driver.findElement(By.xpath(LOGIN_BUTTON)).click();
    }

    public void enterSearchQuery(String input) {
        driver.findElement(By.name("search")).sendKeys(input);
    }

    public List<WebElement> getSearchSuggestionList() {
        return driver.findElements(By.xpath(SEARCH_SUGGESTION_LIST));
    }

    public void loginFieldClear() {
        fieldClear(LOGIN_FIELD);
    }
}

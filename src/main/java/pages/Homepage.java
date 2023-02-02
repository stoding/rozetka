package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Homepage extends BasePage {

    private static final String LOGIN_FIELD_VALIDATION_MESSAGE_XPATH = "//input[@id='auth_email']/following-sibling::p";
    private static final String LOGIN_WINDOW_XPATH = "//h3[contains(text(),'Вход')]";
    private static final String LOGIN_ICON_XPATH = "//li[@class='header-actions__item header-actions__item--user']//button";
    private static final String LOGIN_BUTTON_XPATH = "//button[contains(text(), 'Войти')]";

    public Homepage(WebDriver driver) {
        super(driver);
    }


    public void loginIconClick() {
        driver.findElement(By.xpath(LOGIN_ICON_XPATH)).click();

    }
    public boolean loginWindowIsDisplayed(){
        return isDisplayed(LOGIN_WINDOW_XPATH, 2000);
    }

    public void inputLogin(String login) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("auth_email")))).sendKeys(login, Keys.TAB);
    }

    public String getValidationMessage() {
        if (isDisplayed(LOGIN_FIELD_VALIDATION_MESSAGE_XPATH, 100)) {
            return driver.findElement(By.xpath(LOGIN_FIELD_VALIDATION_MESSAGE_XPATH)).getText();
        } else return "";
    }

    public void searchFieldClear() {
        fieldClear("//input[@name='search']");
    }

    public void inputPassword(String password) {
        driver.findElement(By.id("auth_pass")).sendKeys(password, Keys.TAB);
    }

    public void loginButtonClick() {
        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }

    public void enterSearchQuery(String input) {
        driver.findElement(By.name("search")).sendKeys(input);
    }

    public List<WebElement> getSearchSuggestionList() {
        return driver.findElements(By.xpath("//ul[@class='search-suggest__group']//a"));
    }

    public void loginFieldClear() {
        fieldClear("//input[@id='auth_email']");
    }
}

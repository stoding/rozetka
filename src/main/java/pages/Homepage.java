package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Homepage extends BasePage {

    public static final String LOGIN_FIELD_VALIDATION_MESSAGE_XPATH = "//input[@id='auth_email']/following-sibling::p";
    public static final String LOGIN_WINDOW_XPATH = "//h3[contains(text(),'Вход')]";
    public static final String LOGIN_ICON_XPATH = "//li[@class='header-actions__item header-actions__item--user']//button";
    public static final String LOGIN_BUTTON_XPATH = "//button[contains(text(), 'Войти')]";

    public Homepage(WebDriver driver) {
        super(driver);
    }


    public boolean loginIconClick() {
        driver.findElement(By.xpath(LOGIN_ICON_XPATH)).click();
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
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("auth_pass")))).sendKeys(password, Keys.TAB);
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

    public void clickSearchButton() {
        driver.findElement(By.xpath("//button[@class='button button_color_green button_size_medium search-form__submit ng-star-inserted']")).click();
    }

    public void clickElectronicsCategory() {
        driver.findElement(By.xpath("//li/a[@class='menu-categories__link' and contains(text(),'Смартфоны, ТВ и электроника')]")).click();
        waitForCategoryPortalPageLoaded();
    }

    public void clickAppliancesCategory() {
        driver.findElement(By.xpath("//div[@class='fat-wrap']//a[contains(@href,'https://bt.rozetka.com.ua/')]")).click();
        waitForCategoryPortalPageLoaded();
    }

    public void clickHouseHoldItemsCategory() {
        driver.findElement(By.xpath("//ul[@class='menu-categories menu-categories_type_main']//a[contains(@href,'tovary-dlya-doma')]")).click();
        waitForCategoryPortalPageLoaded();
    }

    public void loginFieldClear() {
        fieldClear("//input[@id='auth_email']");
    }
}

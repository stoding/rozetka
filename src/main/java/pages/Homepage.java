package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Homepage extends BasePage {

    private static final String LOGIN_TEXT_FIELD_VALIDATION_MESSAGE = "//input[@id='auth_email']/following-sibling::p";
    private static final String LOGIN_WINDOW = "//h3[contains(text(),'Вход')]";
    private static final String LOGIN_ICON = "//li[@class='header-actions__item header-actions__item--user']//button";
    private static final String LOGIN_BUTTON = "//button[contains(text(), 'Войти')]";
    private static final String SEARCH_FIELD = "//input[@name='search']";
    private static final String SEARCH_SUGGESTION_LIST = "//ul[@class='search-suggest__group']//a";
    private static final String LOGIN_FIELD = "//input[@id='auth_email']";
    private static final String LOGIN_TEXT_FIELD = "#auth_email";
    private static final String PASSWORD_TEXT_FIELD = "#auth_pass";
    private static final String CAPTCHA_FRAME = "//iframe[@title='reCAPTCHA']";
    private static final String MENU_FOR_USER_SIGNED_IN = "//div[@class='main-auth-wrap']";

    public void loginIconClick() {
        $x(LOGIN_ICON).click();
    }

    public boolean loginWindowIsDisplayed() {
        return $x(LOGIN_WINDOW).shouldBe(visible).isDisplayed();
    }

    public void inputLogin(String login) {
        $(LOGIN_TEXT_FIELD).sendKeys(login, Keys.TAB);
    }

    public String getValidationMessage() {
        if ($x(LOGIN_TEXT_FIELD_VALIDATION_MESSAGE).isDisplayed()) {
            return $x(LOGIN_TEXT_FIELD_VALIDATION_MESSAGE).getText();
        } else return null;
    }

    public void searchFieldClear() {
        $x(SEARCH_FIELD).clear();
    }

    public void inputPassword(String password) {
        $(PASSWORD_TEXT_FIELD).sendKeys(password, Keys.TAB);
    }

    public void loginButtonClick() {
        $x(LOGIN_BUTTON).click();
    }

    public void enterSearchQuery(String input) {
        $x(SEARCH_FIELD).sendKeys(input);
    }

    public ElementsCollection getSearchSuggestionList() {
        return $$x(SEARCH_SUGGESTION_LIST);
    }

    public void loginFieldClear() {
        $x(LOGIN_FIELD).clear();
    }

    public boolean isCaptchaDisplayed() {
        return $x(CAPTCHA_FRAME).shouldBe(visible).isDisplayed();
    }

    public boolean isRegisteredUserMenuDisplayed() {
        return $x(MENU_FOR_USER_SIGNED_IN).isDisplayed();
    }

    public void waitForSearchSuggestionListAppear() {
        $x(SEARCH_SUGGESTION_LIST).shouldBe(visible);
    }
}

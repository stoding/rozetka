package steps;

import org.openqa.selenium.WebDriver;

public class LoginSteps extends BaseSteps {

    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    public boolean loginFormOpen() {
        return homepage.loginIconClick();
    }


    public String loginFormLoginValidationMessage(String login) {
        homepage.inputLogin(login);
        return homepage.getValidationMessage();
    }

    public void loginFieldClean() {
        homepage.fieldClear("//input[@id='auth_email']");
    }

    public void inputLogin(String login, String password) {
        homepage.inputLogin(login);
        homepage.inputPassword(password);
        homepage.loginButtonClick();
    }

    public boolean captchaIsDisplayed() {
        return homepage.isDisplayed("//iframe[@title='reCAPTCHA']");
    }

    public boolean registeredUserMenuIsDisplayed() {
        return homepage.isDisplayed("//div[@class='main-auth-wrap']");
    }
}

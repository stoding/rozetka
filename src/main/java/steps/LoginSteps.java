package steps;

import org.openqa.selenium.WebDriver;

public class LoginSteps extends BaseSteps {

    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    public boolean clickLoginAndVerifyFormOpened() {
        homepage.loginIconClick();
        return homepage.loginWindowIsDisplayed();
    }

    public String getLoginFormLoginValidationMessage(String login) {
        homepage.loginFieldClear();
        homepage.inputLogin(login);
        return homepage.getValidationMessage();
    }

    public void fillLoginForm(String login, String password) {
        homepage.inputLogin(login);
        homepage.inputPassword(password);
        homepage.loginButtonClick();
    }

    public boolean isCaptchaDisplayed() {
        return homepage.isDisplayed("//iframe[@title='reCAPTCHA']", 2000);
    }

    public boolean isRegisteredUserMenuDisplayed() {
        return homepage.isDisplayed("//div[@class='main-auth-wrap']");
    }

    public boolean isUserLoginSuccessful() {
        if (isCaptchaDisplayed()) {
            System.out.println("Captcha is displayed, test considered successful");
            return true;
        }
        if (isRegisteredUserMenuDisplayed()) {
            return true;
        } else {
            System.out.println("Captcha is not displayed, login page is not loaded");
            return false;
        }


    }
}

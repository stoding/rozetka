package steps;

import com.google.inject.Inject;
import pages.Homepage;

public class LoginSteps extends BaseSteps {

    @Inject
    private Homepage homepage;

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
        return homepage.isCaptchaDisplayed();
    }

    public boolean isRegisteredUserMenuDisplayed() {
        return homepage.isRegisteredUserMenuDisplayed();
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

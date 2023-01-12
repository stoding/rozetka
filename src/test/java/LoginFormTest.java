import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.LoginSteps;

import static org.assertj.core.api.Assertions.*;


public class LoginFormTest extends BaseTest {

    public static final String USER_LOGIN_VALID = "sting.mat@gmail.com";
    public static final String USER_PASSWORD_VALID = "Qwert12345";

    @Test
    public void loginFormOpen() {
        LoginSteps loginSteps = new LoginSteps(driver);
        assertThat(loginSteps.loginFormOpen()).isTrue();
    }

    @DataProvider(name = "loginParams")
    public Object[][] setLoginParams() {
        return new Object[][]{
                {"0971234567", ""},
                {"80971234567", ""},
                {"380971234567", ""},
                {"+380971234567", ""},
                {" ", "Введен неверный адрес эл.почты или номер телефона"},
                {"mail", "Введен неверный адрес эл.почты или номер телефона"},
                {"    mail@mail.com", "Введен неверный адрес эл.почты или номер телефона"},
                {"mail@mail", "Введен неверный адрес эл.почты или номер телефона"},
                {"mail!#@mail.com", "Введен неверный адрес эл.почты или номер телефона"},
                {"@mail.com", "Введен неверный адрес эл.почты или номер телефона"},
                {"mail@", "Введен неверный адрес эл.почты или номер телефона"},
                {"<mail>@mail.com", "Введен неверный адрес эл.почты или номер телефона"}
        };
    }

    //Відокремив тест від перевірки відкриття вікна для логіну щоб тест на валідацію полів працював швидше
    @Test(dataProvider = "loginParams", dependsOnMethods = "loginFormOpen", priority = 1)
    public void loginValidationMessageCheck(String login, String expectedMessage) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginFieldClean();
        assertThat(loginSteps.loginFormLoginValidationMessage(login)).isEqualTo(expectedMessage);
    }

    @Test(priority = 2)
    public void loginWithValidParameters() {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.navigateToHomePage();
        assertThat(loginSteps.loginFormOpen()).isTrue();
        loginSteps.loginUser(USER_LOGIN_VALID, USER_PASSWORD_VALID);
        try {
            assertThat(loginSteps.captchaIsDisplayed()).isTrue();
        } catch (AssertionError e) {
            assertThat(loginSteps.registeredUserMenuIsDisplayed()).withFailMessage("Captcha was not displayed, login unsuccessful").isTrue();
        }
    }
}

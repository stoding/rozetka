import com.google.inject.Inject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.LoginSteps;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;


public class LoginFormTest extends BaseTest {

    @Inject
    LoginSteps loginSteps;

    private static final String USER_LOGIN_VALID = "sting.mat@gmail.com";
    private static final String USER_PASSWORD_VALID = "Qwert12345";

    @Test
    public void loginFormDisplayedTest() {
        assertThat(loginSteps.clickLoginAndVerifyFormOpened()).isTrue();
    }

    @DataProvider(name = "loginParams")
    public Object[][] setLoginParams() {
        return new Object[][]{
                {"0971234567", null},
                {"80971234567", null},
                {"380971234567", null},
                {"+380971234567", null},
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
    @Test(dataProvider = "loginParams", dependsOnMethods = "loginFormDisplayedTest", priority = 1)
    public void loginValidationTest(String login, String expectedMessage) {
        assertThat(loginSteps.getLoginFormLoginValidationMessage(login)).isEqualTo(expectedMessage);
    }

    @Test(priority = 2)
    public void loginWithValidParametersTest() {
        open(ROZETKA_HOMEPAGE_URL);
        assertThat(loginSteps.clickLoginAndVerifyFormOpened()).isTrue();
        loginSteps.fillLoginForm(USER_LOGIN_VALID, USER_PASSWORD_VALID);
        assertThat(loginSteps.isUserLoginSuccessful()).isTrue();

    }
}

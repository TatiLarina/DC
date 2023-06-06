package dostavka_tsvetov.LK.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.LK.page.LoginPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {

    private static final Faker faker = new Faker(new Locale("en"));

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
    }
    @BeforeEach
    void before() {
        DataHelper.openLoginPage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("3.1 Ввод зарегистрированной пары емайл-пароль")
    void validLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        loginPage.validLogin(authInfo);
        closeWebDriver();
    }

    @Test
    @DisplayName("3.2 Ввод несуществующей пары емайл-пароль")
    void wrongLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.generateRandomUser();
        loginPage.wrongLogin(authInfo);
    }
/*
    // 3.3 Валидация поля емайл
    @Test
    void invalidEmail() {
        var loginPage = new LoginPage();
        String password = "123456";
        String email = "i@ya.";
        loginPage.invalidEmail(email, password);
        email = "@ya.ru";
        loginPage.invalidEmail(email, password);
        email = "iya.ru";
        loginPage.invalidEmail(email, password);
        email = ".ru";
        loginPage.invalidEmail(email, password);
        email = "";
        loginPage.invalidEmail(email, password);
        email = "test";
        loginPage.invalidEmail(email, password);
    }
*/
    @Test
    @DisplayName("3.4 Предупреждение \"Введите пароль\"")
    void ifNotPassword() {
        var loginPage = new LoginPage();
        String email = faker.internet().emailAddress();
        loginPage.notPassword(email);
    }

    @Test
    @DisplayName("3.5 Переход на страницу регистрации")
    void clickRegistration() {
        var loginPage = new LoginPage();
        loginPage.newRegistration();
        String expected = "Регистрация";
        String actual = $(".lk_header__h2").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3.6 Переход на страницу восстановления пароля")
    void clickForgetPassword() {
        var loginPage = new LoginPage();
        loginPage.forgetPass();
        String expected = "Восстановление пароля";
        String actual = $(".lk_header__h2").getText().trim();
        assertEquals(expected, actual);
    }

}

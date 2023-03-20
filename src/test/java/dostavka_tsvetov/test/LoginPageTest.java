package dostavka_tsvetov.test;

import com.github.javafaker.Faker;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {

    private static Faker faker = new Faker(new Locale("en"));

    @BeforeEach
    void before() {
        DataHelper.openLoginPage();
    }

    // 3.1 Ввод зарегистрированной пары емайл-пароль
    @Test
    void validLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        loginPage.validLogin(authInfo);
        closeWebDriver();
    }

    // 3.2 Ввод несуществующей пары емайл-пароль
    @Test
    void wrongLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.generateRandomUser();
        loginPage.wrongLogin(authInfo);
    }

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

    // 3.4 Предупреждение "Введите пароль"
    @Test
    void ifNotPassword() {
        var loginPage = new LoginPage();
        String email = faker.internet().emailAddress();
        loginPage.notPassword(email);
    }

    // 3.5 Переход на страницу регистрации
    @Test
    void clickRegistration() {
        var loginPage = new LoginPage();
        loginPage.newRegistration();
        String expected = "Регистрация";
        String actual = $(".lk_header__h2").getText().trim();
        assertEquals(expected, actual);
    }

    // 3.6 Переход на страницу восстановления пароля
    @Test
    void clickForgetPassword() {
        var loginPage = new LoginPage();
        loginPage.forgetPass();
        String expected = "Восстановление пароля";
        String actual = $(".lk_header__h2").getText().trim();
        assertEquals(expected, actual);
    }



}

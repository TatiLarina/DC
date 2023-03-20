package dostavka_tsvetov.test;

import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.page.RegistrationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPageTest {

    @BeforeEach
    void before() {
        DataHelper.openRegistrationPage();
    }

    //2.1 Регистрация с валидными данными
    @Test
    void successRegistration() {
        var registrationPage = new RegistrationPage();
        var userInfo = DataHelper.generateNewUser();
        registrationPage.registration(userInfo);
        closeWebDriver();
    }

    // 2.2 Уже зарегистрированный емайл
    @Test
    void alertAlreadyRegistered() {
        var registrationPage = new RegistrationPage();
        var userInfo = DataHelper.oldUser();
        registrationPage.alreadyRegisteredUser(userInfo);
        String expected = "Пользователь с таким e-mail уже зарегистрирован.";
        String actual = $(".alert_lk.active").getText().trim();
        assertEquals(expected, actual);
    }

    //2.3 Отсутствие галочки "С условиями..."
    @Test
    void agreeCheckbox() {
        var registrationPage = new RegistrationPage();
        registrationPage.agreeCheckBoxClick();
        $("#submit_register").shouldBe(disabled);
        registrationPage.agreeCheckBoxClick();
        $("#submit_register").shouldBe(visible);
    }
}

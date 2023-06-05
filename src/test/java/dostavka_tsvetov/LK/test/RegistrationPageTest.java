package dostavka_tsvetov.LK.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import dostavka_tsvetov.LK.data.DataHelper;
import dostavka_tsvetov.LK.page.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPageTest {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
    }
    @BeforeEach
    void before() {
        DataHelper.openRegistrationPage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
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

    //2.4.1 Валидация поля имя и телефон
    @Test
    void invalidNamee() {
        var registrationPage = new RegistrationPage();
        String name = "123!.";
        registrationPage.inputName(name);
        String expected = "";
        String actual = $("#input-firstname").getText().trim();
        assertEquals(expected, actual);

    }

    //2.4.2 Валидация поля телефон
    @Test
    void invalidPhone() {
        var registrationPage = new RegistrationPage();
        String phone = "qwerty,.фыва";
        registrationPage.inputPhone(phone);
        String expected = "";
        String actual = $("#input-telephone").getText().trim();
        assertEquals(expected, actual);
    }

    // 2.5 Валидация поля емайл (доработать)
    @Test
    void invalidEmail() {
        var registrationPage = new RegistrationPage();
        String password = "123456";
        String email = "i@ya.";
        registrationPage.invalidEmail(email, password);
        email = "@ya.ru";
        registrationPage.invalidEmail(email, password);
        email = "iya.ru";
        registrationPage.invalidEmail(email, password);
        email = ".ru";
        registrationPage.invalidEmail(email, password);
        email = "";
        registrationPage.invalidEmail(email, password);
        email = "test";
        registrationPage.invalidEmail(email, password);
    }

}

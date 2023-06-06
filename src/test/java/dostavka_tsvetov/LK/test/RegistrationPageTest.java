package dostavka_tsvetov.LK.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.LK.page.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    @DisplayName("2.1 Регистрация с валидными данными")
    void successRegistration() {
        var registrationPage = new RegistrationPage();
        var userInfo = DataHelper.generateNewUser();
        registrationPage.registration(userInfo);
        closeWebDriver();
    }

    @Test
    @DisplayName("2.2 Уже зарегистрированный емайл")
    void alertAlreadyRegistered() {
        var registrationPage = new RegistrationPage();
        var userInfo = DataHelper.oldUser();
        registrationPage.alreadyRegisteredUser(userInfo);
        String expected = "Пользователь с таким e-mail уже зарегистрирован.";
        String actual = $(".alert_lk.active").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.3 Отсутствие галочки \"С условиями...\"")
    void agreeCheckbox() {
        var registrationPage = new RegistrationPage();
        registrationPage.agreeCheckBoxClick();
        $("#submit_register").shouldBe(disabled);
        registrationPage.agreeCheckBoxClick();
        $("#submit_register").shouldBe(visible);
    }

    @Test
    @DisplayName("2.4.1 Валидация поля имя и телефон")
    void invalidName() {
        var registrationPage = new RegistrationPage();
        String name = "123!.";
        registrationPage.inputName(name);
        String expected = "";
        String actual = $("#input-firstname").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.4.2 Валидация поля телефон")
    void invalidPhone() {
        var registrationPage = new RegistrationPage();
        String phone = "qwerty,.фыва";
        registrationPage.inputPhone(phone);
        String expected = "";
        String actual = $("#input-telephone").getText().trim();
        assertEquals(expected, actual);
    }

    // (доработать)
    @ParameterizedTest
    @DisplayName("2.5 Валидация поля емайл")
    @CsvSource({
            "i@ya.", "@ya.ru", "iya.ru", ".ru", "", "test"
    })
    void invalidEmail(String email) {
        var registrationPage = new RegistrationPage();
        String password = "123456";
        registrationPage.invalidEmail(email, password);
    }

}

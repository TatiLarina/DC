package dostavka_tsvetov.LK.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.LK.page.ForgottenPasswordPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForgottenPassPageTest {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
    }

    @BeforeEach
    void before() {
        DataHelper.openForgottenPasswordPage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("3.2 Ввод незарегистрированного емайла (или любого другого текста)")
    void wrongEmail() {
        var forgottenPassPage = new ForgottenPasswordPage();
        forgottenPassPage.invalidEmail("test");
        String expected = "Введенный адрес электронной почты не найден, пожалуйста попробуйте еще раз!";
        String actual = $(".alert_lk.active").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3.3 Отправка пустого поля емайл")
    void entryEmail() {
        var forgottenPassPage = new ForgottenPasswordPage();
        forgottenPassPage.entryEmail();
        String expected = "Введите E-mail.";
        String actual = $(".alert_lk").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3.1 Восстановление пароля зарегистированного пользователя")
    void validEmail() {
        var forgottenPassPage = new ForgottenPasswordPage();
        forgottenPassPage.validEmail("t.p.larina@ya.ru");
        String expected = "Новый пароль был отправлен на ваш адрес электронной почты.";
        String actual = $(".lk_alert__success").getText().trim();
        assertEquals(expected, actual);
    }

}

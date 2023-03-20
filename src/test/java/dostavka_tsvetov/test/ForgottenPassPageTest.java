package dostavka_tsvetov.test;

import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.page.ForgottenPasswordPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForgottenPassPageTest {

    @BeforeEach
    void before() {
        DataHelper.openForgottenPasswordPage();
    }

    // 3.2 Ввод незарегистрированного емайла (или любого другого текста)
    @Test
    void wrongEmail() {
        var forgottenPassPage = new ForgottenPasswordPage();
        forgottenPassPage.invalidEmail("test");
        String expected = "Введенный адрес электронной почты не найден, пожалуйста попробуйте еще раз!";
        String actual = $(".alert_lk.active").getText().trim();
        assertEquals(expected, actual);
    }

    // 3.3 Отправка пустого поля емайл
    @Test
    void entryEmail() {
        var forgottenPassPage = new ForgottenPasswordPage();
        forgottenPassPage.entryEmail();
        String expected = "Введите E-mail.";
        String actual = $(".alert_lk").getText().trim();
        assertEquals(expected, actual);
    }

    // 3.1 Восстановление пароля зарегистированного пользователя
    // После выполнения проверить, что на указанную в тесте почту приходит новый пароль!
    @Test
    void validEmail() {
        var forgottenPassPage = new ForgottenPasswordPage();
        forgottenPassPage.validEmail("t.p.larina@ya.ru");
        String expected = "Новый пароль был отправлен на ваш адрес электронной почты.";
        String actual = $(".lk_alert__success").getText().trim();
        assertEquals(expected, actual);
    }

}

package dostavka_tsvetov.LK.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Страница восстановления пароля
public class ForgottenPasswordPage {

    // Элементы страницы
    private SelenideElement emailField = $("#email_input");
    private SelenideElement resetButton = $("#button_for_reset");
    private SelenideElement emailText = $(".user_email.accent");
    private SelenideElement alertEmail = $(".alert_lk");
    private SelenideElement alertSuccess = $(".lk_alert__success");

    public ForgottenPasswordPage() {
        $(".pass_wrapwe").shouldBe(visible);
    }

    // Метод проверки валидности пароля
    public void validEmail(String email) {
        emailField.setValue(email);
        resetButton.click();
        emailText.shouldBe(visible);
        alertSuccess.waitUntil(visible, 15000);
    }

    // Если сразу нажать на Далее
    public void entryEmail() {
        resetButton.click();
        alertEmail.shouldBe(visible);
    }

    // Если ввести незарегистрированный емайл или любой другой текст
    public void invalidEmail(String email) {
        emailField.setValue(email);
        resetButton.click();
    }



}

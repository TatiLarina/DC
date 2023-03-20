package dostavka_tsvetov.LK.page;

import com.codeborne.selenide.SelenideElement;
import dostavka_tsvetov.LK.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Страница авторизации
public class LoginPage {

    // Элементы страницы
    private SelenideElement emailField = $("#input-email");
    private SelenideElement passwordField = $("#input-password");
    private SelenideElement loginButton = $(".lk__button__black");
    private SelenideElement alertWrongLogin = $(".alert_lk__p");

    private SelenideElement warningEmail = $(".warning_div.email_pattern");
    private SelenideElement warningPassword = $(".warning_div.password");
    private SelenideElement registrationButton = $(".lk__button__white");

    private SelenideElement forgetPasswordButton = $(".lk__button__href");

    // Метод авторизации с верным емайлом и паролем
    public DashboardPage validLogin(DataHelper.AuthInfo info) {
        emailField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new DashboardPage();
    }

    // Метод авторизации с неверным емайлом или паролем
    public void wrongLogin(DataHelper.AuthInfo info) {
        emailField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        alertWrongLogin.shouldBe(visible);
    }

    // Метод валидации поля емайл
    public void invalidEmail(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        loginButton.click();
        warningEmail.shouldBe(visible);
    }

    // Попытка авторизации без введения пароля
    public void notPassword(String email) {
        emailField.setValue(email);
        loginButton.click();
        warningPassword.shouldBe(visible);
    }

    // Нажатие кнопки "Регистрация"
    public RegistrationPage newRegistration() {
        registrationButton.click();
        return new RegistrationPage();
    }

    // Нажатие кнопки "Забыли пароль"
    public ForgottenPasswordPage forgetPass() {
        forgetPasswordButton.click();
        return new ForgottenPasswordPage();
    }

}

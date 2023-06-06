package dostavka_tsvetov.LK.page;

import com.codeborne.selenide.SelenideElement;
import dostavka_tsvetov.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Страница регистрации
public class RegistrationPage {

    // Элементы страницы
    private final SelenideElement nameField = $("#input-firstname");
    private final SelenideElement phoneField = $("#input-telephone");
    private final SelenideElement emailField = $("#input-email");
    private final SelenideElement passwordField = $("#input-password");
    private final SelenideElement repeatPassField = $("#input-confirm");
    private final SelenideElement agreeCheckbox = $("#agree_checkbox");
    private final SelenideElement registerButton = $("#submit_register");
    private final SelenideElement alertWarning = $(".alert_lk.active");
    private final SelenideElement warningEmail = $(".warning_div.email_pattern");
    public RegistrationPage() {
        $("#account").shouldBe(visible);
    }

    public DashboardPage registration(DataHelper.UserInfo info) {
        nameField.setValue(info.getName());
        phoneField.setValue(info.getPhone());
        emailField.setValue(info.getEmail());
        passwordField.setValue(info.getPassword());
        repeatPassField.setValue(info.getPassword());
        registerButton.click();
        return new DashboardPage();
    }

    public void alreadyRegisteredUser(DataHelper.UserInfo info) {
        nameField.setValue(info.getName());
        phoneField.setValue(info.getPhone());
        emailField.setValue(info.getEmail());
        passwordField.setValue(info.getPassword());
        repeatPassField.setValue(info.getPassword());
        registerButton.click();
        alertWarning.shouldBe(visible);
    }

    public void invalidEmail(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        repeatPassField.setValue(password);
        registerButton.click();
        warningEmail.shouldBe(visible);

    }

    public void agreeCheckBoxClick() {
        agreeCheckbox.click();
    }

    public void inputName(String text) {
        nameField.setValue(text);
    }

    public void inputPhone(String text) {
        phoneField.setValue(text);
    }

    public void inputEmail(String text) {
        emailField.setValue(text);
    }

    public void inputPassword(String text) {
        passwordField.setValue(text);
    }

    public void inputRepeatPass(String text) {
        repeatPassField.setValue(text);
    }


}

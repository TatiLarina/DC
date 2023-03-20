package dostavka_tsvetov.page;

import com.codeborne.selenide.SelenideElement;
import dostavka_tsvetov.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Страница регистрации
public class RegistrationPage {

    // Элементы страницы
    private SelenideElement nameField = $("#input-firstname");
    private SelenideElement phoneField = $("#input-telephone");
    private SelenideElement emailField = $("#input-email");
    private SelenideElement passwordField = $("#input-password");
    private SelenideElement repeatPassField = $("#input-confirm");
    private SelenideElement agreeCheckbox = $("#agree_checkbox");
    private SelenideElement registerButton = $("#submit_register");
    private SelenideElement alertWarning = $(".alert_lk.active");
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

    public void agreeCheckBoxClick() {
        agreeCheckbox.click();
    }




}

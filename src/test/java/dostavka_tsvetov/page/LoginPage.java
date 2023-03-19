package dostavka_tsvetov.page;

import com.codeborne.selenide.SelenideElement;
import dostavka_tsvetov.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[id=input-email]");
    private SelenideElement passwordField = $("[id=input-password]");
    private SelenideElement loginButton = $(".lk__button__black");
    private SelenideElement alertWrongLogin = $(".alert_lk__p");

    public DashboardPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new DashboardPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        alertWrongLogin.shouldBe(visible);
    }

}

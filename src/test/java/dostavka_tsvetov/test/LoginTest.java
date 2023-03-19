package dostavka_tsvetov.test;

import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.page.LoginPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    void validLogin() {
        DataHelper.openSite();
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        loginPage.validLogin(authInfo);
    }

    @Test
    void invalidLogin() {
        DataHelper.openSite();
        var loginPage = new LoginPage();
        var authInfo = DataHelper.generateRandomUser();
        loginPage.invalidLogin(authInfo);
    }



}

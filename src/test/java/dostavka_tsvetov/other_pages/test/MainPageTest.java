package dostavka_tsvetov.other_pages.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.other_pages.page.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class MainPageTest {

    private final MainPage mainPage = new MainPage();

    @BeforeAll
    static void before() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
        DataHelper.openMainPage();

    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Узнать статус заказа")
    void orderInfoClick() {
        mainPage.orderInfoClick();
        mainPage.orderCheck(DataHelper.actualNum().getNumber());
        mainPage.moreClick();
        $(".simpleorder_header__h1").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Проверка навигационного меню")
    void navMenu() {
        mainPage.clickDelivery();
        mainPage.clickPayment();
        mainPage.clickAssurance();
        mainPage.clickContacts();
        mainPage.clickAbout();
    }
}

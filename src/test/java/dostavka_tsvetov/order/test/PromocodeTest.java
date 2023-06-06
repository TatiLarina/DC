package dostavka_tsvetov.order.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.order.page.MainPage;
import dostavka_tsvetov.order.page.OrderPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 2 Применить промокод
public class PromocodeTest {
    OrderPage orderPage = new OrderPage();
    @BeforeAll
    static void before() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
        DataHelper.openMainPage();
        var mainPage = new MainPage();
        mainPage.addToCart(1);
        DataHelper.openOrderPage();
        Configuration.timeout = 15_000;
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("2.1 Применить недействительный промокод")
    void nonActivePromocode() {
        orderPage.setPromocodeField("test");
        String expected = "Промокод не действителен";
        String actual = orderPage.setWrongPromoText();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2.2 Применить действующий промокод")
    void actualPromocode() {
        int count = orderPage.setTotalPrice();
        orderPage.setPromocodeField("bant");
        String expected = "Скидка 5 %";
        String actual = orderPage.setActualPromoText();
        assertEquals(expected, actual);
        int discount = (int) Math.ceil(count*0.95);
        String expected2 = (discount/1000)+" "+(discount%1000)+" ₽";
        String actual2 = $(".checkout_total__price").getText().trim();
        assertEquals(expected2, actual2);
        String expected3 = "Оплатить "+discount+" ₽";
        String actual3 = $(".checkout_confirm__btn").getText().trim();
        assertEquals(expected3, actual3);
    }

    @Test
    @DisplayName("2.3 Удалить примененный промокод")
    void deletePromocode() {
        orderPage.setPromocodeField("test");
        orderPage.clickDeletePromo();
        String expected = "";
        String actual = orderPage.getPromocode();
        assertEquals(expected, actual);
    }

    // 2.4 Промокод бесплатный подарок

    @Test
    @DisplayName("2.5 Промокод скидка 300 р")
    void actualPromocode300() {
        int count = orderPage.setTotalPrice();
        orderPage.setPromocodeField("welcome");
        String expected = "Скидка 300 ₽";
        String actual = orderPage.setActualPromoText();
        assertEquals(expected, actual);
        int discount = count - 300;
        String expected2 = (discount/1000)+" "+(discount%1000)+" ₽";
        String actual2 = $(".checkout_total__price").getText().trim();
        assertEquals(expected2, actual2);
        String expected3 = "Оплатить "+discount+" ₽";
        String actual3 = $(".checkout_confirm__btn").getText().trim();
        assertEquals(expected3, actual3);
    }



}

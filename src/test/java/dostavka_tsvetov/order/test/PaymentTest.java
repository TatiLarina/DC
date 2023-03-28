package dostavka_tsvetov.order.test;

import dostavka_tsvetov.order.page.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @BeforeEach
    void before() {
        open("https://dostavka-tsvetov.com/");
        var mainPage = new MainPage();
        mainPage.addToCart(0);
        open("https://dostavka-tsvetov.com/index.php?route=checkout/simple");
        $("#firstname").clear();
        $("#firstname").setValue("Тестовый заказ");
        $("#email").clear();
        $("#email").setValue("t.p.larina@ya.ru");
        $("#delivery_date").click();
    }

    @AfterEach
    void after() {
        closeWebDriver();
    }

    // 9.1 Оплата картами Visa/Mastercard
    @Test
    void paymentByPaykeeper() {
        $("#selectPayments").click();
        $(byName("paykeeper")).click();
        $("#confirm_checkout").click();
        $("#cardForm").waitUntil(visible, 5000);
        String urlPay = url();
        urlPay.indexOf("https://ishop.multicarta.ru/");
    }

    // 9.2 Сбербанк и яндекс.деньги
    @Test
    void paymentByYoomoney() {
        $("#selectPayments").click();
        $(byName("yoomoney")).click();
        $("#confirm_checkout").click();
        $("#continue-button").click();
        $(".ThemeLocal__StyledThemeLocal-sc-1y2ad8h-0").waitUntil(visible, 5000);
        String urlPay = url();
        urlPay.indexOf("https://yoomoney.ru/");
    }


    // 9.3 Банковские карты
    @Test
    void paymentByPayonline() {
        $("#selectPayments").click();
        $(byName("payonline")).click();
        $("#confirm_checkout").click();
        $(".payment-container").waitUntil(visible, 5000);
        String urlPay = url();
        urlPay.indexOf("https://form.payonlinesystem.com/");
    }

    // 9.4 Банковской картой при получении
    @Test
    void paymentWithCard() {
        $("#selectPayments").click();
        $(byName("cod_cart")).click();
        $("#confirm_checkout").click();
        $(".success_header").waitUntil(visible, 5000);
        String expected = "Заказ отправлен!";
        String actual = $(".success_header__h1").getText().trim();
        assertEquals(expected, actual);
    }

    // 9.5 Оплата зарубежной картой
    @Test
    void paymentByIntellectmoney() {
        $("#selectPayments").click();
        $(byName("intellectmoney")).click();
        $("#confirm_checkout").click();
        $(".payment-card").waitUntil(visible, 5000);
        String urlPay = url();
        urlPay.indexOf("https://merchant.intellectmoney.ru/");
    }

    // 9.6 Наличными при получении
    @Test
    void paymentWithCash() {
        $("#selectPayments").click();
        $(byName("cod")).click();
        $("#confirm_checkout").click();
        $(".success_header").waitUntil(visible, 5000);
        String expected = "Заказ отправлен!";
        String actual = $(".success_header__h1").getText().trim();
        assertEquals(expected, actual);
    }

    // 9.7 Безналичный перевод
    @Test
    void paymentByCsh_plusplus() {
        $("#selectPayments").click();
        $(byName("cash_plusplus")).click();
        $("#confirm_checkout").click();
        $(".pull-right").click();
        $(".oplata-item__title").waitUntil(visible, 5000);
        String expected = "Заказ успешно оформлен!";
        String actual = $(".oplata-item__title").getText().trim();
        assertEquals(expected, actual);
    }

}

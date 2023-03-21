package dostavka_tsvetov.order.test;

import dostavka_tsvetov.order.data.DataHelper;
import dostavka_tsvetov.order.page.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 2 Применить промокод
public class PromocodeTest {

    @BeforeAll
    static void before() {
        DataHelper.openMainPage();
        var mainPage = new MainPage();
        mainPage.addToCart(1);
        open("https://www.dostavka-tsvetov.com/index.php?route=checkout/simple");
    }

    // 2.1 Применить недействительный промокод
    @Test
    void nonActivePromocode() {
        $("#promo-kod").setValue("test");
        $(".checkout_total").click();
        $(".checkout_coupon__warning").shouldBe(visible);
        String expected = "Промокод не действителен";
        String actual = $(".checkout_coupon__warning").getText().trim();
        assertEquals(expected, actual);
    }

    // 2.2 Применить действующий промокод
    @Test
    void actualPromocode() {
        String s1 = $(".checkout_total__price").getText().trim();
        String[] s2_array = s1.split("\\D+");
        int count = Integer.parseInt(String.join("", s2_array));
        $("#promo-kod").setValue("bant");
        $(".checkout_total").click();
        $(".checkout_coupon__discount").shouldBe(visible);
        String expected = "Скидка 5 %";
        String actual = $(".checkout_coupon__discount").getText().trim();
        assertEquals(expected, actual);
        int discount = (int) Math.ceil(count*0.95);
        String expected2 = Integer.toString(discount/1000)+" "+Integer.toString(discount%1000)+" ₽";
        String actual2 = $(".checkout_total__price").getText().trim();
        assertEquals(expected2, actual2);
        String expected3 = "Оплатить "+Integer.toString(discount)+" ₽";
        String actual3 = $(".checkout_confirm__btn").getText().trim();
        assertEquals(expected3, actual3);
    }

    // 2.3 Удалить примененный промокод
    @Test
    void deletePromocode() {
        $("#promo-kod").setValue("test");
        $(".checkout_total").click();
        $(".button_delete_coupon").click();
        String expected = "";
        String actual = $("#promo-kod").getText().trim();
        assertEquals(expected, actual);
    }

    // 2.4 Промокод бесплатный подарок

    // 2.5 Промокод скидка 500 р
    @Test
    void actualPromocode500() {
        String s1 = $(".checkout_total__price").getText().trim();
        String[] s2_array = s1.split("\\D+");
        int count = Integer.parseInt(String.join("", s2_array));
        $("#promo-kod").setValue("welcome");
        $(".checkout_total").click();
        $(".checkout_coupon__discount").shouldBe(visible);
        String expected = "Скидка 500 ₽";
        String actual = $(".checkout_coupon__discount").getText().trim();
        assertEquals(expected, actual);
        int discount = count - 500;
        String expected2 = Integer.toString(discount/1000)+" "+Integer.toString(discount%1000)+" ₽";
        String actual2 = $(".checkout_total__price").getText().trim();
        assertEquals(expected2, actual2);
        String expected3 = "Оплатить "+Integer.toString(discount)+" ₽";
        String actual3 = $(".checkout_confirm__btn").getText().trim();
        assertEquals(expected3, actual3);
    }



}

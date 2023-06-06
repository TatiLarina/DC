package dostavka_tsvetov.order.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import dostavka_tsvetov.data.DataHelper;
import dostavka_tsvetov.order.page.ProductPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductPageTest {
    private final ProductPage productPage = new ProductPage();
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
        DataHelper.openProductPage();
        Configuration.timeout = 15_000;
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Купить в один клик
    @Test
    @DisplayName("Купить в один клик - пустые поля")
    void buyOneClickEntryField() {
        productPage.buyOneClick("", "");
        $(".modal_form__input.name.error").shouldBe(visible);
        $(".modal_form__input.telephone.error").shouldBe(visible);
        productPage.closeOneClick();
    }

    @Test
    @DisplayName("Купить в один клик - пустой телефон")
    void buyOneClickEntryFieldPhone() {
        productPage.buyOneClick("Тестовый заказ", "");
        $(".modal_form__input.telephone.error").shouldBe(visible);
        productPage.closeOneClick();
    }

    @Test
    @DisplayName("Купить в один клик - пустое имя")
    void buyOneClickEntryFieldName() {
        productPage.buyOneClick("", "0000000000");
        $(".modal_form__input.name.error").shouldBe(visible);
        productPage.closeOneClick();
    }

    @Test
    @DisplayName("Успешная покупка в один клик")
    void buyOneClickSuccess() {
        productPage.buyOneClick("Тестовый заказ", "0000000000");
        $(".oneclick_success_block").shouldBe(visible);
        $(".product_information_buttons__buyOneClick__close").click();
        $(".modalOnClick").shouldBe(hidden);
    }

      /*
    // Добавление в корзину
    @Test
    void addToCart() {
        $(".product_information_buttons__addCart").click();

    }

    // Переход на Правила возврата
    @Test
    void returnPolicy() {
        var productPage = new ProductPage();
        $$(".utp_item").get(1).click();
        switchTo().window(1);
        String expected = "Гарантии";
        String actual = $(".assurance_header__h1").getText().trim();
        assertEquals(expected, actual);
    }

*/
}

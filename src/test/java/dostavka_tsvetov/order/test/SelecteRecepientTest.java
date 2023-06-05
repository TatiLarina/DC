package dostavka_tsvetov.order.test;

import dostavka_tsvetov.order.data.DataHelper;
import dostavka_tsvetov.order.page.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

// 3 Выбрать получателя букета
public class SelecteRecepientTest {

    /*
    @BeforeAll
    static void before() {
        DataHelper.openMainPage();
        var mainPage = new MainPage();
        mainPage.addToCart(2);
        open("https://www.dostavka-tsvetov.com/index.php?route=checkout/simple");
    }

    // 3.1 Выбрать получателем другого человека
    @Test
    void anotherPeople() {
        $$(".who_will_receive__box label").get(0).click();
        $(".recipient_contacts").shouldBe(visible);
        $("#optionsForReveiver").shouldBe(visible);
    }

    // 3.2 Выбрать получателем себя
    @Test
    void myself() {
        $$(".who_will_receive__box label").get(1).click();
        $(".recipient_contacts").shouldBe(hidden);
    }

    // 3.3 Выбрать   получателем букета последовательно Другого человека, затем себя и снова Другого человека
    @Test
    void switching() {
        $$(".who_will_receive__box label").get(0).click();
        $(".recipient_contacts").shouldBe(visible);
        $("#optionsForReveiver").shouldBe(visible);
        $$(".who_will_receive__box label").get(1).click();
        $(".recipient_contacts").shouldBe(hidden);
        $$(".who_will_receive__box label").get(0).click();
        $(".recipient_contacts").shouldBe(visible);
        $("#optionsForReveiver").shouldBe(visible);
    }

     */

}

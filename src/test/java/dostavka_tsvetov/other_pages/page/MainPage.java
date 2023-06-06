package dostavka_tsvetov.other_pages.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    // Навигационное меню
    List<SelenideElement> menu = $$(".navbar_top");
    SelenideElement orderInfoButton = menu.get(0);
    SelenideElement delivery = menu.get(1);
    SelenideElement payment = menu.get(2);
    SelenideElement assurance = menu.get(3);
    SelenideElement contacts = menu.get(4);
    SelenideElement about = menu.get(5);

    SelenideElement orderInfoBlock = $("#order_info");
    SelenideElement orderNumField = $("#order-num");
    SelenideElement checkButton = $("#button-order");
    SelenideElement messageOrder = $(".returned_info");
    SelenideElement moreButton = $(".returned_info__href");
    SelenideElement closeButton = $(".close_order__info");

    public void orderInfoClick() {
        orderInfoButton.click();
        orderInfoBlock.shouldBe(Condition.visible);
    }

    public void orderCheck(String number) {
        orderNumField.setValue(number);
        checkButton.click();
        messageOrder.shouldBe(Condition.visible);
    }

    public void closeOrderCheck() {
        checkButton.click();
    }

    public void moreClick() {
        moreButton.click();
    }

    public void clickDelivery() {
        delivery.click();
        $(".delivery_header__h1").shouldBe(Condition.visible);
    }

    public void clickPayment() {
        payment.click();
        $(".oplata_header__h1").shouldBe(Condition.visible);
    }

    public void clickAssurance() {
        assurance.click();
        $(".assurance_header__h1").shouldBe(Condition.visible);
    }

    public void clickContacts() {
        contacts.click();
        $(".category_name__h1").shouldBe(Condition.visible);
    }

    public void clickAbout() {
        about.click();
        $(".studioflor_header__h1").shouldBe(Condition.visible);
    }

}

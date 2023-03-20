package dostavka_tsvetov.order.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {
    private ElementsCollection wishlistHeart = $$("#530");

    // Элементы купить в один клик
    private SelenideElement buttonOneClick = $(".product_information_buttons__buyOneClick");
    private SelenideElement blockOneClick = $("#buy_one_click");
    private SelenideElement nameField = $("#oneclick_name");
    private SelenideElement phoneField = $("#oneclick_telephone");
    private SelenideElement buttonSendOrder = $(".btn_confirm_one_click");
    private SelenideElement successText = $(".oneclick_success_text");
    private SelenideElement buttonGood = $(".product_information_buttons__buyOneClick__close");

    public void buyOneClick(String name, String phone) {
        buttonOneClick.click();
        blockOneClick.shouldBe(visible);
        nameField.setValue(name);
        phoneField.setValue(phone);
        buttonSendOrder.click();
        successText.shouldBe(visible);
        buttonGood.click();
        successText.shouldBe(disabled);
    }
    public void clickHeart(int number) {
        wishlistHeart.get(number).click();
    }



}

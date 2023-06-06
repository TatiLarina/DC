package dostavka_tsvetov.order.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {
    private final ElementsCollection wishlistHeart = $$("#530");

    // Элементы купить в один клик
    private final SelenideElement buttonOneClick = $(".product_information_buttons__buyOneClick");
    private final SelenideElement blockOneClick = $("#buy_one_click");
    private final SelenideElement nameField = $("#oneclick_name");
    private final SelenideElement phoneField = $("#oneclick_telephone");
    private final SelenideElement buttonSendOrder = $(".btn_confirm_one_click");
    private final SelenideElement closeOneClick = $(".close_onclick");
    private final SelenideElement successText = $(".oneclick_success_text");
    private final SelenideElement buttonGood = $(".product_information_buttons__buyOneClick__close");

    public void buyOneClick(String name, String phone) {
        buttonOneClick.click();
        blockOneClick.shouldBe(visible);
        nameField.setValue(name);
        phoneField.setValue(phone);
        buttonSendOrder.click();
    }


    public void clickHeart(int number) {
        wishlistHeart.get(number).click();
    }

    public void closeOneClick() {
        closeOneClick.click();
    }



}

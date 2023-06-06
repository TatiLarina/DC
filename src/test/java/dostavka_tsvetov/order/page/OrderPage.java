package dostavka_tsvetov.order.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {
    private final SelenideElement promocodeField =  $("#promo-kod");
    private final SelenideElement checkoutTotalText = $(".checkout_total");
    private final SelenideElement deletePromoButton = $(".button_delete_coupon");
    private final SelenideElement totalPrice = $(".checkout_total__price");
    private final SelenideElement actualPromo = $(".checkout_coupon__discount");
    private final SelenideElement wrongPromo = $(".checkout_coupon__warning");

    public void setPromocodeField(String text) {
        promocodeField.setValue(text);
        checkoutTotalText.click();
    }

    public String getPromocode() {
        return promocodeField.getText().trim();
    }

    public void clickDeletePromo() {
        deletePromoButton.click();
    }

    public int setTotalPrice() {
        String s1 = totalPrice.getText().trim();
        String[] s2_array = s1.split("\\D+");
        return Integer.parseInt(String.join("", s2_array));
    }

    public String setActualPromoText() {
        actualPromo.shouldBe(visible);
        return actualPromo.getText().trim();
    }

    public String setWrongPromoText() {
        wrongPromo.shouldBe(visible);
        return wrongPromo.getText().trim();
    }

}

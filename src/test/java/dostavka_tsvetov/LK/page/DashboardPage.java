package dostavka_tsvetov.LK.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $(".lk_header__h1");

    public DashboardPage() {
        heading.shouldBe(visible);
    }
}

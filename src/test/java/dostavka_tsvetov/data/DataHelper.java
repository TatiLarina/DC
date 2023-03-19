package dostavka_tsvetov.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static void openSite() {
        open("https://development.dostavka-tsvetov.com/index.php?route=account/login");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("i@jerrymurr.ru", "123456");
    }

    private static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomEmail(), generateRandomPassword());
    }


}

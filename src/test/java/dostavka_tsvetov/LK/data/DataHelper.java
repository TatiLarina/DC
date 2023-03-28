package dostavka_tsvetov.LK.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class DataHelper {

    private DataHelper() {
    }

    // Методы открытия различных страниц
    public static void openLoginPage() {
        open("https://dostavka-tsvetov.com/index.php?route=account/login");
    }

    public static void openRegistrationPage() {
        open("https://dostavka-tsvetov.com/index.php?route=account/register");
    }

    public static void openForgottenPasswordPage() {
        open("https://dostavka-tsvetov.com/index.php?route=account/forgotten");
    }

    // Пара емайл-пароль
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    // Зарегистрированная пара емайл-пароль
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("i@jerrymurr.ru", "123456");
    }

    // Случайная пара емайл-пароль

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomEmail(), generateRandomPassword());
    }

    // Генерация случайных данных

    private static Faker faker = new Faker(new Locale("en"));
    private static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    private static String generateRandomPhone() {return faker.numerify("##########"); }

    //Пользователь для регистрации

    @Value
    public static class UserInfo {
        String name;
        String phone;
        String email;
        String password;
    }

    // Случайный пользователь
    public static UserInfo generateNewUser() {
        return new UserInfo("Тест", generateRandomPhone(), generateRandomEmail(),
                generateRandomPassword());
    }

    // Существующий пользователь
    public static UserInfo oldUser() {
        return new UserInfo("Тест", generateRandomPhone(), "i@jerrymurr.ru",
                generateRandomPassword());
    }

}

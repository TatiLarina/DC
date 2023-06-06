package dostavka_tsvetov.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class DataHelper {

    private DataHelper() {
    }

    // Выбор сайта для тестов
    private static final String dev = "https://development.dostavka-tsvetov.com/";
    private static final String prod = "https://dostavka-tsvetov.com/";
    private static final String page = dev; // присвоить нужное

    // Методы открытия различных страниц
    public static void openLoginPage() { open(page + "index.php?route=account/login"); }

    public static void openRegistrationPage() { open(page + "index.php?route=account/register"); }

    public static void openForgottenPasswordPage() { open(page + "index.php?route=account/forgotten");}

    public static void openMainPage() { open(page); }

    public static void openOrderPage() {
        open(page + "index.php?route=checkout/simple");
    }

    public static void openProductPage() {
        open(page + "11-roz");
    }


    // Пара емайл-пароль
    @Value
    public static class AuthInfo {
        String login;
        String password;
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

    private static final Faker faker = new Faker(new Locale("en"));

    private static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    private static String generateRandomPhone() {
        return faker.numerify("##########");
    }

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

    // Номер заказа
    @Value
    public static class OrderNum {
        String number;
    }

    public static OrderNum actualNum() {
        if (page.equals(dev)) {
            return new OrderNum("810574");
        } else {
            return new OrderNum("150690");
        }

    }

}

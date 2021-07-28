package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    // Testing the card number

    @Value
    public static class CardNumber {
        private String cardNumber;
    }

    public static CardNumber getApprovedCardNumber() {
        return new CardNumber("4444 4444 4444 4441");
    }

    public static CardNumber getDeclinedCardNumber() {
        return new CardNumber("4444 4444 4444 4442");
    }

    public static CardNumber getInvalidCardNumber() {
        return new CardNumber("4444 4444 4444 4443");
    }

    public static CardNumber getIncorrectCardNumber() {
        return new CardNumber("4444 4444 4444");
    }

    public static CardNumber getCardWithoutNumber() {
        return new CardNumber("");
    }

    // Testing a month

    public static String getCorrectMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNextMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getIncorrectMonth() {
        return "13";
    }

    public static String getEmptyMonth() {
        return "";
    }

    // Testing year

    public static String getCorrectYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPlusYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getMinusYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyYear() {
        return "";
    }

    // Testing the owner

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getChineseOwner() {
        Faker faker = new Faker(new Locale("zh"));
        return faker.name().fullName();
    }

    public static String getRussianOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getEmptyOwner() {
        return "";
    }

    // Testing CVC/CVV

    public static String getCorrectCVC() {
        return "123";
    }

    public static String getIncorrectCVC() {
        return "12";
    }

    public static String getEmptyCVC() {
        return "";
    }
}

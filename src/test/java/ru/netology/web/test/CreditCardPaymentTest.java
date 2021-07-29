package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditCardPaymentTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // POSITIVE SCENARIOS

    @Test
    void should_Payment_By_Debit_Card_With_Status_Approved() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Status_Declined() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Russian_Owner() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getNextMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getRussianOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Chinese_Owner() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getChineseOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    // NEGATIVE SCENARIOS

    @Test
    void should_Payment_By_Debit_Card_With_Invalid_Card_Number() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getInvalidCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.errorNotification();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Incorrect_Card_Number() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getIncorrectCardNumber();
        val month = DataHelper.getLastMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.wrongFormat();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Invalid_Month() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.invalidCardExpirationDate();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Expired_One_Year() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getMinusYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.cardExpired();
    }

    @Test
    void should_Payment_By_Debit_Card_With_The_Wrong_Year() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getPlusYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.invalidCardExpirationDate();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Invalid_Owner() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getInvalidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.wrongFormat();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Incorrect_CVC() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getIncorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.wrongFormat();
    }

    @Test
    void should_Payment_By_Debit_Card_With_Blank_Fields() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getCardWithoutNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getEmptyOwner();
        val cvc = DataHelper.getEmptyCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.wrongFormat();
        paymentPage.emptyField();
    }
}

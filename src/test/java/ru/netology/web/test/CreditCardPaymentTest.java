package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SqlHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardPaymentTest {

    @BeforeEach
    void setUp() {
        Configuration.startMaximized = true;
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.deleteTables();
    }

    // POSITIVE SCENARIOS

    @Test
    void shouldPaymentByCreditCardWithStatusApproved() {
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
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPaymentByCreditCardWithStatusDeclined() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.PaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.errorNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPaymentByCreditCardWithRussianOwner() {
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
    void shouldPaymentByCreditCardWithChineseOwner() {
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
    void shouldPaymentByCreditCardWithInvalidCardNumber() {
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
    void shouldPaymentByCreditCardWithIncorrectCardNumber() {
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
    void shouldPaymentByCreditCardWithInvalidMonth() {
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
    void shouldPaymentByCreditCardWithExpiredOneYear() {
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
    void shouldPaymentByCreditCardWithTheWrongYear() {
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
    void shouldPaymentByCreditCardWithInvalidOwner() {
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
    void shouldPaymentByCreditCardWithIncorrectCVC() {
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
    void shouldPaymentByCreditCardWithBlankFields() {
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

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
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;


public class DebitCardPaymentTest {

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
    }

    // POSITIVE SCENARIOS

    @Test
    void should_Payment_Via_Debit_Card_With_Status_Approved() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.validPaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    @Test
    void should_Payment_Via_Debit_Card_With_Status_Declined() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.validPaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    @Test
    void should_Payment_Via_Debit_Card_With_Russian_Owner() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getNextMonth();
        val year = DataHelper.getPlusYear();
        val owner = DataHelper.getRussianOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.validPaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }

    @Test
    void should_Payment_Via_Debit_Card_With_Chinese_Owner() {
        val dashboardPage = new DashboardPage();
        val paymentPage = new PaymentPage();
        dashboardPage.getDebitCardPayment();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getCorrectMonth();
        val year = DataHelper.getCorrectYear();
        val owner = DataHelper.getChineseOwner();
        val cvc = DataHelper.getCorrectCVC();
        paymentPage.validPaymentFormat(cardNumber, month, year, owner, cvc);
        paymentPage.successNotification();
    }


}

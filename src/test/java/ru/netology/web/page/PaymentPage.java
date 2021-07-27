package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("placeholder='22'");
    private final SelenideElement ownerField = $$(".input__control").find(Condition.exactText("Владелец"));
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement button = $$(".button").find(Condition.exactText("Продолжить"));

    private final SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement invalidCardExpirationDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));

    public void validPaymentFormat(DataHelper.CardNumber info, String month, String year, String owner, String cvc) {
        cardNumber.setValue(info.getCardNumber());
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvcField.setValue(cvc);
        button.click();
    }

    public void successNotification() {
        successNotification.shouldBe(Condition.visible);
    }

    public void errorNotification() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void wrongFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void invalidCardExpirationDate() {
        invalidCardExpirationDate.shouldBe(Condition.visible);
    }

    public void cardExpired() {
        cardExpired.shouldBe(Condition.visible);
    }

    public void emptyField() {
        emptyField.shouldBe(Condition.visible);
    }
}

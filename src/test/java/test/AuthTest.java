package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @Test
    void shouldLoginWithValidData() {
        var user = DataHelper.validUser();

        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();

        $("h2").shouldHave(text("Личный кабинет")).shouldBe(visible);
    }

    @Test
    void shouldShowErrorWithInvalidLogin() {
        var user = DataHelper.invalidLogin();

        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();

        $("[data-test-id=error-notification]").shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }
}

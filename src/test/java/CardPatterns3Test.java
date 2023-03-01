import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


public class CardPatterns3Test {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldValidUser() {
        DataGenerator.RegistrationInfo validUser = DataGenerator.Registration.generateInfo("ru");
        $("span[data-test-id=city] input").setValue(validUser.getCity());
        $("span[data-test-id=date] input").doubleClick();
        $("span[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $("span[data-test-id=name] input").setValue(validUser.getName());
        $("span[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//span[text()='Запланировать']").click();
        $x("//div[contains(text(), 'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification']").shouldHave(exactText("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        $("[data-test-id=success-notification]").shouldBe(visible);


        $("span[data-test-id=date] input").doubleClick();
        $("span[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue(DataGenerator.generateDate(4));
        $x("//span[text()='Запланировать']").click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(exactText("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(4)));
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15));

    }
}


package site.kpokogujl.test;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

public class FromCodeToAllureTest extends TestBase{

    @Owner("allure8")
    @Feature("Registration")
    @Story("Registration")
    @DisplayName("Registration")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void practiceFormTest() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                phone = "9121112233",
                address = faker.address().fullAddress(),
                gender = "Female",
                day = "10",
                month = "February",
                year = "2000",
                subject = "English",
                hobbies = "Sports",
                state = "NCR",
                city = "Delhi";


        step("Открыть страницу регистрации.", () -> {
            open("/automation-practice-form");
        });

        step("Ввести Имя,", () -> {
            $("#firstName").setValue(firstName);
        });

        step("Ввести Фамилию.", () -> {
            $("#lastName").setValue(lastName);
        });

        step("Ввести имейл.", () -> {
            $("#userEmail").setValue(email);
        });

        step("Указать пол.", () -> {
            $("#genterWrapper").$(byText(gender)).click();
        });

        step("Ввести мобильный телефон.", () -> {
            $("#userNumber").setValue(phone);
        });

        step("Указать дату рождения.", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__year-select").selectOption(year);
            String dayLocator = format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day);
            $(dayLocator).click();
        });

        step("Указать предмет.", () -> {
            $("#subjectsInput").setValue(subject).pressEnter();
        });

        step("Указать хоббы.", () -> {
            $("#hobbiesWrapper").$(byText(hobbies)).click();
        });

        step("Ввести адрес.", () -> {
            $("#currentAddress").setValue(address);
        });

        step("Выбрать штат.", () -> {
            $(byText("Select State")).click();
            $(byText(state)).scrollTo().click();
        });

        step("Выбрать город.", () -> {
            $(byText("Select City")).click();
            $(byText(city)).scrollTo().click();
        });

        step("Нажать Submit.", () -> {
            $("#submit").click();
        });

        step("Проверяю результат.", () -> {
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phone));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(day + " " + month + "," + year));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
        });
    }

}

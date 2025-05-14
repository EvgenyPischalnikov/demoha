import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.SPACE;

public class AutomationPracticForm {

    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Evgeny");
        $("#lastName").setValue("Pischalnikov");
        $("#userEmail").setValue("lusiferik96@gmail.com");

        $("#genterWrapper").$(byText("Male")).click();

        $("#userNumber").setValue("9956551874");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2001");
        $(".react-datepicker__month-select").selectOption(10);
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month").click();

        $("#subjectsInput").setValue("Biology").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();

        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("cow.jpg");

        $("#currentAddress").setValue("г. Москва, ул. Севастопольский пр-кт, д. 13, к.2, кв. 50");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();

        $("#submit").click();

        //Checks
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Evgeny Pischalnikov"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("lusiferik96@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9956551874"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("28 November,2001"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Biology, Physics"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("cow.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("г. Москва, ул. Севастопольский пр-кт, д. 13, к.2, кв. 50"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));
    }
}

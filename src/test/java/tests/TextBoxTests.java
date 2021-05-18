package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void successfulSubmitFormTest() {
        open("https://demoqa.com/text-box");

        $("[id=userName]").setValue("some user");
        $("[id=userEmail]").setValue("someemail@mail.ma");
        $("[id=currentAddress]").setValue("some user address 74");
        $("[id=permanentAddress]").setValue("some user no i dont have");
        $("[id=submit]").click();

        $("[id=name]").shouldHave(text("Name:"), text("some user"));
        $("[id=email]").shouldHave(text("Email:"), text("someemail@mail.ma"));
        $("[id=currentAddress]", 1).shouldHave(
                text("Current Address :"), text("some user address 74"));
        $("p[id=permanentAddress]").shouldHave(
                text("Permananet Address :"), text("some user no i dont have"));
    }

    @Test
    void successfulSubmitFormWithVariablesTest() {
        String userName = "some user";

        open("https://demoqa.com/text-box");

        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue("someemail@mail.ma");
        $("[id=currentAddress]").setValue("some user address 74");
        $("[id=permanentAddress]").setValue("some user no i dont have");
        $("[id=submit]").click();

        $("[id=name]").shouldHave(text("Name:"), text(userName));
        $("[id=email]").shouldHave(text("Email:"), text("someemail@mail.ma"));
        $("[id=currentAddress]", 1).shouldHave(
                text("Current Address :"), text("some user address 74"));
        $("p[id=permanentAddress]").shouldHave(
                text("Permananet Address :"), text("some user no i dont have"));
    }

    @Test
    void successfulSubmitFormSearchInOutputTest() {
        String userName = "some user";

        open("https://demoqa.com/text-box");

        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue("someemail@mail.ma");
        $("[id=currentAddress]").setValue("some user address 74");
        $("[id=permanentAddress]").setValue("some user no i dont have");
        $("[id=submit]").click();

        $("[id=output]").shouldHave(text(userName), text("someemail@mail.ma"),
                text("some user address 74"), text("some user no i dont have"));
    }


    @Test
    void successfulSubmitFormWithBadLocatorsTest() {
        String userName = "some user";

        open("https://demoqa.com/text-box");

        $("[id=userName]").setValue(userName);
        $("[placeholder=\"name@example.com\"]").setValue("someemail@mail.ma");
        $("textarea").setValue("some user address 74");
        $(".form-control", 3).setValue("some user no i dont have");
        $(byText("Submit")).click();

        $("[id=output]").shouldHave(text(userName), text("someemail@mail.ma"),
                text("some user address 74"), text("some user no i dont have"));
    }


}

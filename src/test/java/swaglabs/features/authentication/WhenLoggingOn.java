package swaglabs.features.authentication;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import swaglabs.screenplay.login.Login;
import swaglabs.screenplay.login.LoginForm;
import swaglabs.screenplay.navigation.Banner;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

@ExtendWith(SerenityJUnit5Extension.class)
@Tag("authentication")
public class WhenLoggingOn {

    @CastMember
    Actor charles;

    @Test
    // The one where Charles enters valid username and password
    @DisplayName("With valid credentials")
    public void withValidCredentials() {
        charles.attemptsTo(
                Login.withCredentials("standard_user", "secret_sauce"),
                WaitUntil.the(Banner.TITLE, isVisible()),
                Ensure.that(Banner.TITLE).hasText("PRODUCTS")
        );
    }

    @ParameterizedTest(name = "{index}: {2}")
    @CsvSource(value = {
            "               ,secret_sauce   ,Username is required",
            "standard_user  ,                , Password is required",
            "               ,                , Username is required",
            "standard_user  , wrong_password , Username and password do not match any user in this service",
            "wrong_user     , secret_sauce   , Username and password do not match any user in this service",
    })
    public void withInvalidCredentials(String username, String password, String errorMessage) {
        charles.attemptsTo(
                Login.withCredentials(nonNull(username), nonNull(password)),
                Ensure.that(LoginForm.ERROR_MESSAGE).text().contains(errorMessage)
        );
    }

    private String nonNull(String value) {
        return (value == null) ? "" : value.trim();
    }
}

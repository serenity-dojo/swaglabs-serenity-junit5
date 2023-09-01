package swaglabs.screenplay.login;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;

public class Login {
    public static Performable withCredentials(String username, String password) {
        return Task.where("{0} logs in with specified credentials",
                Open.url("https://www.saucedemo.com"),
                Enter.theValue(username).into(LoginForm.USERNAME),
                Enter.theValue(password).into(LoginForm.PASSWORD),
                Click.on(LoginForm.LOGIN_BUTTON)
        );
    }

    public static Performable asAStandardUser() {
        return withCredentials("standard_user", "secret_sauce");
    }

    public static Performable andFail() {
        return Task.where("The actor fails to do something",
                Ensure.that(true).isFalse()
        );
    }

}

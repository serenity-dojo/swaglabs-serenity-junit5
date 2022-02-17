package swaglabs.screenplay.login;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.PageElement;

public class LoginForm {

    public static Target ERROR_MESSAGE = Target.the("an error message").locatedBy("[data-test=error]");

    public static Target USERNAME = InputField.withNameOrId("user-name");
    public static Target PASSWORD = InputField.withNameOrId("password");
    public static Target LOGIN_BUTTON = Button.withText("Login");
}

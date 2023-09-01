package swaglabs.screenplay.login;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;

public class LogOff {
    public static Performable fromTheSite() {
        return Task.where("{0} logs in with specified credentials",
                Click.on("#react-burger-menu-btn"),
                Click.on("#logout_sidebar_link")
        );
    }


}

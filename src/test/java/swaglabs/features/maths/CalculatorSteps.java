package swaglabs.features.maths;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class CalculatorSteps extends UIInteractions {

    private static final By FIRST_VALUE_FIELD = By.cssSelector("[ng-model=first]");
    private static final By SECOND_VALUE_FIELD = By.cssSelector("[ng-model=second]");
    private static final By OPERATOR_DROPDOWN = By.cssSelector("[ng-model=operator]");
    private static final By GO_BUTTON = By.id("gobutton");
    private static final By RESULT_FIELD = By.tagName("h2");
    private static final Pattern A_VALID_NUMBER = Pattern.compile("-?\\d\\.?d*|Infinity");

    @Step
    public void openTheCalculatorApp() {
        openUrl("https://juliemr.github.io/protractor-demo/");
    }

    @Step("Calculate the answer to {0} {1} {2}")
    public String theAnswerTo(String firstValue, String operator, String secondValue) {

        $(FIRST_VALUE_FIELD).sendKeys(firstValue);
        $(OPERATOR_DROPDOWN).selectByVisibleText(operator);
        $(SECOND_VALUE_FIELD).sendKeys(secondValue);
        $(GO_BUTTON).click();
        waitFor(ExpectedConditions.textMatches(RESULT_FIELD,A_VALID_NUMBER));

        return $(RESULT_FIELD).getText();
    }
}

package swaglabs.screenplay.navigation;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Banner {
    public static final By TITLE =  By.cssSelector(".title");
    public static final Target TEXT_LINK = Target.the("text link {0}").locatedBy("//div[@class='text-link']/h2[contains(.,'{0}')]");
    public static final Target TUBE_AND_RAIL_MAP = TEXT_LINK.of("Tube and Rail maps");
}

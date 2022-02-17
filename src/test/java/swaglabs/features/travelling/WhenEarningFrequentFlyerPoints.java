package swaglabs.features.travelling;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static swaglabs.features.travelling.Status.Bronze;
import static swaglabs.features.travelling.Status.Silver;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenEarningFrequentFlyerPoints {

    @Steps
    TravellerEarningStatusPoints tracy;

    @Test
    public void members_should_start_with_Bronze_status() {
        // GIVEN
        tracy.joins_the_frequent_flyer_program();

        // THEN
        tracy.should_have_a_status_of(Bronze);
    }

    @Test
    public void earn_silver_after_10000_kilometers() {
        // GIVEN
        tracy.joins_the_frequent_flyer_program();

        // WHEN
        tracy.flies(10000);

        // THEN
        tracy.should_have_a_status_of(Silver);
    }
}

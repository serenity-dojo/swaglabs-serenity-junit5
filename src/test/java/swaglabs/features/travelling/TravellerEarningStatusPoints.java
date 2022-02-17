package swaglabs.features.travelling;

import net.thucydides.core.annotations.Step;

public class TravellerEarningStatusPoints {

    private String actor;

    @Step("#actor joins the frequent flyer program")
    public void joins_the_frequent_flyer_program() {
    }

    @Step("#actor flies {0} km")
    public void flies(int distance) {

    }

    @Step("#actor should have a status of {0}")
    public void should_have_a_status_of(Status expectedStatus) {
    }

    @Step("#actor transfers {0} points to {1}")
    public void transfers_points(int points, TravellerEarningStatusPoints otherFrequentFlier) {
        // Left as an exercise
    }

    @Override
    public String toString() {
        return actor;
    }

    @Step("#actor should have {0} points")
    public void should_have_points(int expectedPoints) {
        // Left as an exercise
    }
}
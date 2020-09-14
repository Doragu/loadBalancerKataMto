package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    public static final double EPSILON = 0.01d;
    private double expectedPercentageLoad;

    public CurrentLoadPercentageMatcher(double expectedPercentageLoad) {
        this.expectedPercentageLoad = expectedPercentageLoad;
    }

    protected boolean matchesSafely(Server server) {
        return areDoublesEqual(expectedPercentageLoad, server.currentLoadPercentage);
    }

    private boolean areDoublesEqual(double double1, double double2) {
        return double1 == double2 || Math.abs(double1 - double2) < EPSILON;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(expectedPercentageLoad);
    }

    public static CurrentLoadPercentageMatcher hasLoadPercentageOf(double expectedLoad) {
        return new CurrentLoadPercentageMatcher(expectedLoad);
    }
}

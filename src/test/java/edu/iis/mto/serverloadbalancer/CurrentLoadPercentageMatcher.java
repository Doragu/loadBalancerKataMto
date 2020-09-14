package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedPercentageLoad;

    public CurrentLoadPercentageMatcher(double expectedPercentageLoad) {
        this.expectedPercentageLoad = expectedPercentageLoad;
    }

    protected boolean matchesSafely(Server server) {
        return expectedPercentageLoad == server.currentLoadPercentage || Math.abs(expectedPercentageLoad - server.currentLoadPercentage) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(expectedPercentageLoad);
    }
}

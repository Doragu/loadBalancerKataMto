package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadBalancerMatcher extends TypeSafeMatcher<Server> {

    public static final double EPSILON = 0.01d;
    private double expectedValue;

    public ServerLoadBalancerMatcher(double expectedValue) {
        this.expectedValue = expectedValue;
    }

    protected boolean matchesSafely(Server server) {
        return areDoublesEqual(expectedValue, server.currentLoadBalance);
    }

    private boolean areDoublesEqual(double double1, double double2) {
        return double1 == double2 || Math.abs(double1 - double2) <= EPSILON;
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("a server with current load of").appendValue(item.currentLoadBalance);
    }

    public void describeTo(Description description) {
        description.appendText("a server with current load of").appendValue(expectedValue);
    }

    public static ServerLoadBalancerMatcher hasLoadPercentageOf(double expectedValue) {
        return new ServerLoadBalancerMatcher(expectedValue);
    }
}

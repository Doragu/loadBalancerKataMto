package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadBalancerMatcher extends TypeSafeMatcher<Server> {

    private double expectedValue;

    public ServerLoadBalancerMatcher(double expectedValue) {
        this.expectedValue = expectedValue;
    }

    protected boolean matchesSafely(Server server) {
        return true;
    }

    public void describeTo(Description description) {
        description.appendText("a server with current load of").appendValue(expectedValue);
    }

    public static ServerLoadBalancerMatcher hasLoadPercentageOf(double expectedValue) {
        return new ServerLoadBalancerMatcher(expectedValue);
    }
}

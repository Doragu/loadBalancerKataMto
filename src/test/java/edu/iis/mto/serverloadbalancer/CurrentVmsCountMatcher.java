package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedAmount;

    public CurrentVmsCountMatcher(int expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("a server with vms count of").appendValue(item.vmsCount());
    }

    protected boolean matchesSafely(Server server) {
        return server.vmsCount() == expectedAmount;
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedAmount);
    }

    public static CurrentVmsCountMatcher hasVmsCountOf(int expectedAmount) {
        return new CurrentVmsCountMatcher(expectedAmount);
    }
}

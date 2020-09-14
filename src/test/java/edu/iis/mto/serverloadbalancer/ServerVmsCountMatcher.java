package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedValue;

    public ServerVmsCountMatcher(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    protected boolean matchesSafely(Server server) {
        return server.vmCount() == expectedValue;
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("a server with vms count of ").appendValue(item.vmCount());
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedValue);
    }
}

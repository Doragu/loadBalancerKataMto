package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServer_noVms_ServerStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(listOfServersWith(theServer), anEmptyVmsList());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}
}

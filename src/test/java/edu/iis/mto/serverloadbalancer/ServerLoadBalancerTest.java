package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.CurrentVmsCountMatcher.hasVmsCountOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServer_noVm_ServerStaysEmpty() {
	    Server theServer = a(server().withCapacity(1));

	    balance(aListOfServersWith(theServer), anEmptyListOfVms());

	    assertThat(theServer, hasLoadPercentageOf(0.0d));
    }

    @Test
	public void balancingOneServerWithOneSlot_andOneSlotVm_fillsTheServerWithTheVm() {
	    Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));

	    balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

	    assertThat(theServer, hasLoadPercentageOf(100.0d));
	    assertThat("a server should contain the vm", theServer.contains(theVm));
	}

	@Test
	public void balancingOneServerWithTenSlot_andOneSlotVm_fillsTheServerWithTenPercent() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));

		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(10.0d));
		assertThat("a server should contain the vm", theServer.contains(theVm));
	}

	@Test
	public void balancingOneServerWithEnoughRoom_getsFilledWithAllVms() {
		Server theServer = a(server().withCapacity(10));
		Vm vm1 = a(vm().ofSize(4));
		Vm vm2 = a(vm().ofSize(4));

		balance(aListOfServersWith(theServer), aListOfVmsWith(vm1, vm2));

		assertThat(theServer, hasVmsCountOf(2));
		assertThat("a server should contain the first vm", theServer.contains(vm1));
		assertThat("a server should contain the second vm", theServer.contains(vm2));
	}

	@Test
	public void balancingTwoServers_vmAttachedToLessLoaded() {
		Server moreLoadedServer = a(server().withCapacity(10).withCurrentLoadOf(60.0d));
		Server lessLoadedServer = a(server().withCapacity(10).withCurrentLoadOf(50.0d));
		Vm theVm = a(vm().ofSize(3));

		balance(aListOfServersWith(lessLoadedServer, moreLoadedServer), aListOfVmsWith(theVm));

		assertThat("a less loaded server should contain the vm", lessLoadedServer.contains(theVm));
		assertThat("a less loaded server should not contain the vm", !moreLoadedServer.contains(theVm));
	}

	@Test
	public void balanceAServerWithNotEnoughRoom_ShouldNotBeFilled() {
		Server theServer = a(server().withCapacity(10).withCurrentLoadOf(8.0d));
		Vm theVm = a(vm().ofSize(3));

		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat("a server should not contain the vm", !theServer.contains(theVm));
	}

	private Vm[] aListOfVmsWith(Vm... vms) {
		return vms;
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}
}

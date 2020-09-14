package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancerMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmsCountOf;
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
	public void balancingServer_noVms_ServerStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(listOfServersWith(theServer), anEmptyVmsList());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingServerWithOneSlotCapacity_andOneSlotVm_fillsTheServer() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balance(listOfServersWith(theServer), listOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
	}

    @Test
    public void balancingServerWithTenSlotCapacity_andOneSlotVm_fillsTheServerWithTenPercent() {
        Server theServer = a(server().withCapacity(10));
        Vm theVm = a(vm().ofSize(1));
        balance(listOfServersWith(theServer), listOfVmsWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(10.0d));
        assertThat("server should contain the vm", theServer.contains(theVm));
    }

    @Test
    public void balancingServerWithEnoughRoom_getsFilledWithAllVms() {
	    Server theServer = a(server().withCapacity(10));
	    Vm vm1 = a(vm().ofSize(1));
	    Vm vm2 = a(vm().ofSize(5));
	    balance(listOfServersWith(theServer), listOfVmsWith(vm1, vm2));

	    assertThat(theServer, hasVmsCountOf(2));
	    assertThat("server should contain the vm", theServer.contains(vm2));
	    assertThat("server should contain the vm", theServer.contains(vm1));
    }

	@Test
	public void aVm_shouldBeBalanced_onLessLoadedFirst() {
		Server lessLoadedServer = a(server().withCapacity(10).withLoadPercentage(40.0d));
		Server moreLoadedServer = a(server().withCapacity(10).withLoadPercentage(50.0d));
		Vm theVm = a(vm().ofSize(2));
		balance(listOfServersWith(lessLoadedServer, moreLoadedServer), listOfVmsWith(theVm));

		assertThat("less loaded server should contain the vm", lessLoadedServer.contains(theVm));
		assertThat("more laoded server should not contain the vm", !moreLoadedServer.contains(theVm));
	}

	@Test
	public void balanceAServerWithNotEnoughRoom_shouldNotBeFilledWithAVm() {
		Server theServer = a(server().withCapacity(10).withLoadPercentage(90.0d));
		Vm theVm = a(vm().ofSize(2));
		balance(listOfServersWith(theServer), listOfVmsWith(theVm));

		assertThat("server should not contain the vm", !theServer.contains(theVm));
	}

	@Test
	public void balanceServers_andVms(){
		Server server1 = a(server().withCapacity(10).withLoadPercentage(30.0d));
		Server server2 = a(server().withCapacity(10).withLoadPercentage(40.0d));
		Vm vm1 = a(vm().ofSize(2));
		Vm vm2 = a(vm().ofSize(2));
		Vm vm3 = a(vm().ofSize(2));
		balance(listOfServersWith(server1, server2), listOfVmsWith(vm1, vm2, vm3));

		assertThat("first server should contain the vm1", server1.contains(vm1));
		assertThat("second server should contain the vm2", server2.contains(vm2));
		assertThat("first server should contain the vm3", server1.contains(vm3));

		assertThat(server1, hasLoadPercentageOf(70.0d));
		assertThat(server2, hasLoadPercentageOf(60.0d));

		assertThat(server1, hasVmsCountOf(3));
		assertThat(server2, hasVmsCountOf(2));
	}

	private Vm[] listOfVmsWith(Vm... vms) {
        return vms;
    }

    private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyVmsList() {
		return new Vm[0];
	}

	private Server[] listOfServersWith(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder) {
	    return builder.build();
    }
}

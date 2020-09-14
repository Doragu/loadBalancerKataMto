package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        servers[0].currentLoadPercentage = 0.0d;
    }
}

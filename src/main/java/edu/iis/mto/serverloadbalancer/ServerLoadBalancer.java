package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        Server lessLoadedServer = null;
        for (Vm vm : vms) {
            for (Server server : servers) {
                if (lessLoadedServer == null || lessLoadedServer.getCurrentLoadPercentage() > server.getCurrentLoadPercentage()) {
                    lessLoadedServer = server;
                }
            }

            lessLoadedServer.addVm(vm);
        }
    }
}

package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        Server lessLoadedServer = null;
        for (Vm vm : vms) {
            lessLoadedServer = findLessLoadedServer(servers, lessLoadedServer);

            lessLoadedServer.addVm(vm);
        }
    }

    private Server findLessLoadedServer(Server[] servers, Server lessLoadedServer) {
        for (Server server : servers) {
            if (lessLoadedServer == null || lessLoadedServer.getCurrentLoadPercentage() > server.getCurrentLoadPercentage()) {
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}

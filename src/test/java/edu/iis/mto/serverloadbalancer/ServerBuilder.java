package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;

public class ServerBuilder implements Builder<Server>{

    private int capacity;
    private double loadPercentage;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        addInitialLoadPercentage(server);
        return server;
    }

    private void addInitialLoadPercentage(Server server) {
        if (loadPercentage > 0.0) {
            int tempSize = (int)(loadPercentage * capacity / Server.MAXIMUM_LOAD);
            Vm tempVm = vm().ofSize(tempSize).build();
            server.addVm(tempVm);
        }
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withLoadPercentage(double loadPercentage) {
        this.loadPercentage = loadPercentage;
        return this;
    }
}

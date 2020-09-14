package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;

public class ServerBuilder implements Builder<Server>{

    public int capacity;
    private int vmSize;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        if (vmSize > 0) {
            server.addVm(vm().ofSize(vmSize).build());
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withCurrentLoadOf(double loadPercentage) {
        this.vmSize = (int)(loadPercentage * capacity);
        return this;
    }
}

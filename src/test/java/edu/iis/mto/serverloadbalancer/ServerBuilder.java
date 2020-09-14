package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

    private int loadPercentage;

    public ServerBuilder withCapacity(int loadPercentage) {
        this.loadPercentage = loadPercentage;
        return this;
    }

    public Server build() {
        return new Server();
    }
}

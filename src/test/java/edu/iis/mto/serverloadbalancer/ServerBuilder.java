package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{

    private int loadPercentage;

    public ServerBuilder withCapacity(int loadPercentage) {
        this.loadPercentage = loadPercentage;
        return this;
    }

    public Server build() {
        return new Server();
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}

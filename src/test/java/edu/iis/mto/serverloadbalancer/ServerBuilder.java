package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{

    public int capacity;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server(capacity);
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}

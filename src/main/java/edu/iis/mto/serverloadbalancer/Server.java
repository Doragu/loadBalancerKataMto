package edu.iis.mto.serverloadbalancer;

public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm theVm) {
        return true;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage = (double) vm.getVmSize() / capacity * MAXIMUM_LOAD;
    }
}

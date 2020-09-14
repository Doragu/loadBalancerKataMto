package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadBalance;
    public int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public void addVm(Vm vm) {
        vms.add(vm);
        currentLoadBalance += vmLoadPercentage(vm) * MAXIMUM_LOAD;
    }

    public int vmCount() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadBalance + vmLoadPercentage(vm) * MAXIMUM_LOAD <= MAXIMUM_LOAD;
    }

    private double vmLoadPercentage(Vm vm) {
        return (double) vm.size / capacity;
    }
}

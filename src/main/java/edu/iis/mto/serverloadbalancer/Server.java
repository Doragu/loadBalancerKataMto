package edu.iis.mto.serverloadbalancer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    private double currentLoadPercentage;
    private List<Vm> vms = new ArrayList<Vm>();
    private int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    public void addVm(Vm vm) {
        vms.add(vm);
        currentLoadPercentage = (double) vm.getVmSize() / capacity * MAXIMUM_LOAD;
    }

    public int vmsCount() {
        return vms.size();
    }
}

package edu.iis.mto.serverloadbalancer;

public class VmBuilder {

    private int vmSize;

    public VmBuilder ofSize(int vmSize) {
        this.vmSize = vmSize;
        return this;
    }

    public Vm build() {
        return new Vm();
    }
}

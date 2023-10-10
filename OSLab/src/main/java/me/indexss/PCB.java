package me.indexss;

public class PCB {
    private int pid;
    private int requireTime;
    private double priority;
    private int stat;
    private int attribute;
    private int waitTime;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;

    public PCB(int pid, int requireTime, double priority, int stat, int attribute, int size) {
        setSize(size);
        setPID(pid);
        setRequireTime(requireTime);
        setPriority(priority);
        setStat(0);
        setAttribute(attribute);
        setWaitTime(0);
    }

    public String printInfo(){
        String ret = "";
        ret += "====================\n";
        ret += "PID: " + getPID() + "\n";
        ret += "Size: " + getSize() + "\n";
        ret += "Require Time: " + getRequireTime() + "\n";
        ret += "Priority: " + getPriority() + "\n";
        ret += "Statement: " + getStat() + "\n";
        ret += "attribute: " + getAttribute() + "\n";
        ret += "Wait Time: " + getWaitTime() + "\n";
        ret += "====================\n";
        System.out.println(ret);
        return ret;
    }

    public static void main(String[] args) {
        new PCB(1001, 5, 1, ProcType.IN_STORAGE, Attribute.INDEPENDENT,10).printInfo();
    }


    public int getPID() {
        return pid;
    }

    public void setPID(int pid) {
        this.pid = pid;
    }

    public int getRequireTime() {
        return requireTime;
    }

    public void setRequireTime(int requireTime) {
        this.requireTime = requireTime;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

}

interface ProcType {
    int IN_STORAGE = 1;
    int IN_RAM = 2;
    int IN_CPU = 3;
}

interface Attribute {
    int INDEPENDENT = 1;
    int PRI = 2;
}

package me.indexss;

public class CPU {

    public static int cpuNum;
    public static int slot;
    public static int runTime;
    public static RAM ram = null;
    public static Storage storage = new Storage();
    public CPU(){
        setCpuNum(3);
        setSlot(5);
        setRunTime(0);
        ram = new RAM(2, 100);
        storage = new Storage();
    }

    public static void main(String[] args) {
        CPU cpu = new CPU();
        Storage storage1 = new Storage();
        cpu.work();
    }
public static int ccccc = 20;
    public void work(){
        for(PCB pcb : storage.storageList){
            System.out.println("#########################");
            System.out.println("@@@@@@@@"+pcb.getPID());
            boolean flag = false;
            int pre = pcb.getAttribute();
            System.out.println("[][][][][][][][]finish[][][][][][][[]");
            for(int i : Simulation.finishFlag){
                System.out.println(i);
            }
            System.out.println("[][][][][][][][]finish[][][][][][][[]");
            for(int i = 0; i < 6; i++){
                if(pre == Simulation.finishFlag[i]){
                    flag = true;
                    break;
                }
                else {
                    flag = false;
                }
            }
            System.out.println("///////////////////////"+pre + "//////" + flag + "////");
            if(flag) {
                System.out.println("进来了进来了进来了进来了进来了进来了进来了进来了进来了进来了进来了进来了进来了进来了进来了");
                int i = ram.insertRAM(pcb);
                storage.removeStorage(pcb);
                if (i != 3) {
                    storage.insertStorage(pcb);
                }
            }
        }
        ram.sortReadyQueue();
        System.out.println("====================================================================");
        for (int i = 0; i < ram.readyQueue.size(); i++) {
            ram.readyQueue.get(i).printInfo();
        }
        if (ram.readyQueue.size() == 1) {
            ram.readyQueue.get(0).setRequireTime(ram.readyQueue.get(0).getRequireTime() - 1);
            ram.readyQueue.get(0).setStat(2);
            for (int i = 0; i < ram.readyQueue.size(); i++) {
                ram.readyQueue.get(i).setWaitTime(ram.readyQueue.get(i).getWaitTime() + 1);
            }
            for (int i = 1; i < ram.readyQueue.size(); i++) {
                ram.readyQueue.get(i).setStat(1);
            }
        }
        if (ram.readyQueue.size() >= 2) {
            ram.readyQueue.get(0).setRequireTime(ram.readyQueue.get(0).getRequireTime() - 1);
            ram.readyQueue.get(1).setRequireTime(ram.readyQueue.get(1).getRequireTime() - 1);
            ram.readyQueue.get(0).setStat(2);
            ram.readyQueue.get(1).setStat(2);
            for (int i = 0; i < ram.readyQueue.size(); i++) {
                ram.readyQueue.get(i).setWaitTime(ram.readyQueue.get(i).getWaitTime() + 1);
            }
            for (int i = 2; i < ram.readyQueue.size(); i++) {
                ram.readyQueue.get(i).setStat(1);
            }
        }
        for (int i = 0; i < ram.readyQueue.size(); i++) {
            if (ram.readyQueue.get(i).getRequireTime() <= 0) {
                ram.readyQueue.get(i).setStat(0);
                ram.removeRAM(ram.readyQueue.get(i));
            }
        }
        for (int i = 0; i < ram.readyQueue.size(); i++) {
            if (ram.readyQueue.get(i).getRequireTime() <= 0) {
                ram.readyQueue.get(i).setStat(0);
                ram.removeRAM(ram.readyQueue.get(i));
            }
        }
        for (int i = 0; i < ram.readyQueue.size(); i++) {
            ram.refreshPriority();
        }
        ram.printRam();


    }

    public static int getCpuNum() {
        return cpuNum;
    }

    public static void setCpuNum(int cpuNum) {
        CPU.cpuNum = cpuNum;
    }

    public static int getSlot() {
        return slot;
    }

    public static void setSlot(int slot) {
        CPU.slot = slot;
    }

    public static int getRunTime() {
        return runTime;
    }

    public static void setRunTime(int runTime) {
        CPU.runTime = runTime;
    }



}

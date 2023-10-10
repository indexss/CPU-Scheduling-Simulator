package me.indexss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Storage {
//    public static HashMap<Integer, PCB> storageHm = null;
    public static CopyOnWriteArrayList<PCB> storageList = new CopyOnWriteArrayList<PCB>();
    public static CopyOnWriteArrayList<PCB> waitList = new CopyOnWriteArrayList<PCB>();

    public void insertWaitList(PCB pcb){
        waitList.add(pcb);
    }
    public void removeWaitList(PCB pcb) {
        waitList.remove(pcb);
    }


    public void insertStorage(PCB pcb){
        storageList.add(pcb);
    }

    public void removeStorage(PCB pcb) {
        storageList.remove(pcb);
    }
}

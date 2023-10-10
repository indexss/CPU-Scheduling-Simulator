package me.indexss;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class RAM {
    public static int roadNum;
    public static int ramSize;
    public static int procNum;
    public static int[] ramMap;
    public static CopyOnWriteArrayList<PCB> readyQueue;
    public RAM(int roadNum, int ramSize){
       this.roadNum = roadNum;
       this.ramSize = ramSize;
       this.procNum = 0;
       this.ramMap = new int[ramSize];
       this.readyQueue = new CopyOnWriteArrayList<PCB>();
////       this.ramMap = {1,0,0,0,0,1,0,0,1,1};
//        ramMap[0] = 0;
//        ramMap[1] = 0;
//        ramMap[2] = 0;
//        ramMap[3] = 0;
//        ramMap[4] = 0;
//        ramMap[5] = 0;
//        ramMap[6] = 0;
//        ramMap[7] = 0;
//        ramMap[8] = 0;
//        ramMap[9] = 0;
//        printRam();
    }

    public static HashMap<Integer, Integer> searchEmpty(){
        HashMap<Integer, Integer> emptyHm = new HashMap<Integer, Integer>();
        for(int i = 0; i < ramSize; i++){
            if (ramMap[i] == 0){
                int start = i;
                int length = 1;
                int j = i+1;
                while(ramMap[j] == 0){
                    length++;
                    j++;
                    if(j == ramSize){
                        break;
                    }
                }
                emptyHm.put(start, length);
                i += length;
            }
        }
        return emptyHm;
    }

    public int insertRAM(PCB pcb){
        if (procNum == roadNum){
            return 0; //0代表道数满
        }
        HashMap<Integer, Integer> emptyHm = searchEmpty();
        if (emptyHm.isEmpty()){
            return 1; //1代表内存满了
        }
        int smallestStart = 100000;
        for (Map.Entry<Integer, Integer> entry : emptyHm.entrySet()) {
            Integer start = entry.getKey();
            Integer length = entry.getValue();
            if (length >= pcb.getSize() && start < smallestStart){
                smallestStart = start;
            }
        }
        if(smallestStart == -1){
            return 2; //碎片太小放不下
        }
//        System.out.println("smallestStart: "  + smallestStart);
        for(int i = smallestStart; i < smallestStart+pcb.getSize(); i++){
//            System.out.println("pid: " + pcb.getPID());
            ramMap[i] = pcb.getPID();
        }
        procNum++;
        readyQueue.add(pcb);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-Insert-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
//        for(int i = 0; i < 100 ; i++){
//            System.out.println(ramMap[i]);
//        }
        return 3; //放下了
    }

    public void removeRAM(PCB pcb){
        readyQueue.remove(pcb);
        procNum--;
        for(int i = 0; i < ramSize; i++){
            if(ramMap[i] == pcb.getPID()){
                ramMap[i] = 0;
            }
        }
    }

    public void printRam(){
        System.out.println("--==--==haha--==--==");
        for(int i = 0; i < ramSize; i++){
            System.out.println(ramMap[i]);
        }
    }

    public static void sortReadyQueue(){
        Comparator<PCB> pcbComparator = new Comparator<>() {
            @Override
            public int compare(PCB pcb1, PCB pcb2) {
                if(pcb1.getPriority() < pcb2.getPriority()){
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Collections.sort(readyQueue, pcbComparator);
    }

    public static void refreshPriority(){
        for(int i = 0; i< readyQueue.size(); i++){
            int waitTime = readyQueue.get(i).getWaitTime();
            int requireTime = readyQueue.get(i).getRequireTime();
            double priority = (1.0* (waitTime + requireTime)/requireTime);
            readyQueue.get(i).setPriority(priority);
        }
    }

    public static void main(String[] args) {
        PCB pcb1 = new PCB(1001, 5, 3, 0, 0, 20);
        PCB pcb2 = new PCB(1002, 5, 1, 0, 0, 30);
        PCB pcb3 = new PCB(1003, 5, 2, 0, 0, 10);
        PCB pcb4 = new PCB(1004, 5, 4, 0, 0, 15);
        RAM ram = new RAM(5, 100);
        ram.insertRAM(pcb1);
        ram.insertRAM(pcb2);
        ram.insertRAM(pcb3);
        ram.insertRAM(pcb4);
//        for(PCB p : readyQueue){
//            System.out.println(p.getPID());
//        }
//        ram.sortReadyQueue();
//        System.out.println();
//        for(PCB p : readyQueue){
//            System.out.println(p.getPID());
//        }
        for(int i = 0; i < 100 ; i++){
            System.out.println(ramMap[i]);
        }

//        RAM ram = new RAM(5);
//        int i1 = ram.insertRAM(pcb);
//        ram.insertRAM(pcb2);
//        for(int i = 0; i < 100; i++){
//            System.out.println(ramMap[i]);
//        }
//        System.out.println(readyQueue);
//        ram.removeRAM(pcb);
//        PCB pcb3 = new PCB(1003, 10, 10, 1, 1, 4);
//        ram.insertRAM(pcb3);
//        for(int i = 0; i < 100; i++){
//            System.out.println(ramMap[i]);
//        }
//        System.out.println(readyQueue);

    }
}

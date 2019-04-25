package AssignmentOne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MLFQ {
    List<Process> processList;             // New process list
    private int timeQuantum1;              // Time Quantum 1
    private int timeQuantum2;              // Time Quantum 2
    private int count;                     // List size
    int j=0;
    private int ganntP[];                  // ProcessID running simulation
    private int ganntT[];                  // Time running simulation
    private int totalWaitingTime = 0;      // Total waiting time of process
    private int totalTurnAroundTime = 0;   // Total turn around time of process
    private float avgWatingTime = 0;       // Average waiting time of list
    private float avgTurnaroundTime = 0;   // Average turn around time of list
    
    // Create new process list for MLFQ, add all processes, sort via arrival & priority
    MLFQ(List<Process> processList, int timeQuantum1,int timeQuantum2) {
        count = processList.size();
        ganntT=new int[200];
        ganntP=new int[200];
        this.timeQuantum1 = timeQuantum1;
        this.timeQuantum2 = timeQuantum2;
        this.processList=new ArrayList<Process>();
        for(Process p : processList)
        {
            this.processList.add(new Process(p.getProcessId(), p.getArrivalTime(), p.getBurstTime(),p.getPriority()));
        }
        Collections.sort(this.processList, Process.BY_ARRIVAL_TIME);
        Collections.sort(this.processList, Process.BY_PRIORITY);
    }
    
    // Simulate the MLFQ, run quantum 1, else run quantum 2, return each processes data, find averages 
    public void simulate() {
        int currentTime =0;
        int remainingProcess = count;
        while (remainingProcess > processList.size()/2) 
        {
            int clockTime=currentTime;
            for (int i = 0; i < count; i++) 
            {
                Process current = processList.get(i);
                if(currentTime<current.getArrivalTime())
                    break;
                if (current.getStartTime() == -1)
                    current.setStartTime(currentTime);
                ganntP[j]=current.getProcessId();
                ganntT[j]=currentTime;
                j++;
                if (current.getRemainingTime() <= timeQuantum1 && current.getEndTime()==-1)
                {
                    current.setEndTime(currentTime + current.getRemainingTime());
                    currentTime += current.getRemainingTime();
                    current.setRemainingTime(0);
                    remainingProcess--;
                } 
                else if (current.getRemainingTime()>timeQuantum1)
                {
                    currentTime += timeQuantum1;
                    current.setRemainingTime(current.getRemainingTime()-timeQuantum1);
                }

            }
            if(clockTime==currentTime)
            {
                currentTime++;
            }
        }

        while (remainingProcess > processList.size()/2) 
        {
            int clockTime=currentTime;
            for (int i = 0; i < count; i++) 
            {
                Process current = processList.get(i);
                if(currentTime<current.getArrivalTime())
                    break;
                if (current.getStartTime() == -1)
                    current.setStartTime(currentTime);
                ganntP[j]=current.getProcessId();
                ganntT[j]=currentTime;
                j++;
                if (current.getRemainingTime() <= timeQuantum2 && current.getEndTime()==-1)
                {
                    current.setEndTime(currentTime + current.getRemainingTime());
                    currentTime += current.getRemainingTime();
                    current.setRemainingTime(0);
                    remainingProcess--;
                } 
                else if (current.getRemainingTime()>timeQuantum2)
                {
                    currentTime += timeQuantum2;
                    current.setRemainingTime(current.getRemainingTime()-timeQuantum2);
                }
            }
            if(clockTime==currentTime)
            {
                currentTime++;
            }
        }     

        for(int i=0;i<count;i++)
        {
            Process current=processList.get(i);
            if(current.getRemainingTime()>0 )
            {   
                if(currentTime<current.getArrivalTime())
                {   
                    currentTime=current.getArrivalTime();
                    current.setStartTime(currentTime);
                }
                current.setEndTime(currentTime+current.getRemainingTime());
                currentTime+=current.getRemainingTime();
            }
        }

        for (int i = 0; i < count; i++) 
        {
            Process current = processList.get(i);
            current.setWaitingTime(current.getEndTime()-current.getBurstTime()-current.getArrivalTime());
            current.setTurnaroundTime(current.getEndTime() - current.getArrivalTime());
            totalWaitingTime += current.getWaitingTime();
            totalTurnAroundTime += current.getTurnaroundTime();
        }
        avgWatingTime = (float) totalWaitingTime / count;
        avgTurnaroundTime = (float) totalTurnAroundTime / count;
    }

    // Print the result of the MLFQ, all processes in order of simulation,
    // Time simulation of processes
    public void printResult() 
    {
        System.out.println("First Come First Serve MultiLevel Feedback Queue ");
        System.out.println("ID   Arrival  Burst  Priority  Start   End    Wait TurnAround"); 
        for (Process p : processList) 
        {
            System.out.println(p);
        }
        System.out.println();
        System.out.println("Average Waiting Time:  " + avgWatingTime);
        System.out.println("Average TurnAround Time:  " + avgTurnaroundTime);
        System.out.println();
        System.out.println("Simulation: ");
        for(int i=0;i<j;i++)
        {
            System.out.println("Time: " +ganntT[i]+" ProcessID: "+ganntP[i]);
        }
        System.out.println();
    }
}
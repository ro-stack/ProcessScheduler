package AssignmentOne;

import java.util.Comparator;

public class Process {
    private int processId;        // Unique process ID
    private int arrivalTime;      // Arrival time of process
    private int burstTime;        // Burst time of process
    private int priority;         // Priority of process
    private int startTime;        // Time process starts 
    private int endTime;          // Time process finishes
    private int waitingTime;      // Total time process waits
    private int turnaroundTime;   // Time between start and end 
    private int remainingTime;    // Remaining time of process

    // Setting multiple comparators for process information
    public static final Comparator<Process> BY_PROCESSID=new ByProcessId();
    public static final Comparator<Process> BY_ARRIVAL_TIME=new ByArrivalTime();
    public static final Comparator<Process> BY_PRIORITY=new ByPriority();
    public static final Comparator<Process> BY_BURST_TIME=new ByBurstTime();

    // Compare process IDs
    private static class ByProcessId implements Comparator<Process>
    {
        @Override
        public int compare(Process arg0, Process arg1) {
            if (arg0.processId < arg1.processId)
                return -1;
            if(arg0.processId >arg1.processId)
                return 1;
            return 0;
        }
    }

    // Compare process arrival times
    private static class ByArrivalTime implements Comparator<Process>
    {
        @Override
        public int compare(Process arg0, Process arg1) {
            if (arg0.arrivalTime < arg1.arrivalTime)
                return -1;
            if(arg0.arrivalTime >arg1.arrivalTime)
                return 1;
            return 0;
        }
    }

    // Compare process priorities
    private static class ByPriority implements Comparator<Process>
    {
        @Override
        public int compare(Process arg0, Process arg1) {
            if (arg0.priority < arg1.priority)
                return -1;
            if(arg0.priority >arg1.priority)
                return 1;
            return 0;
        }
    }

    // Compare process burst times
    private static class ByBurstTime implements Comparator<Process>
    {
        @Override
        public int compare(Process arg0, Process arg1) {
            if(arg0.arrivalTime==arg1.arrivalTime)
            {   
                if (arg0.burstTime < arg1.burstTime)
                    return -1;
                if(arg0.burstTime >arg1.burstTime)
                    return 1;
            }
            return 0;
        }   
    }

    // Create process with input parameters
    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        super();
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.startTime=-1;
        this.endTime=-1;
        this.waitingTime=-1;
        this.turnaroundTime=-1;
        this.remainingTime=burstTime;

    }

    // Create a process without priority 
    public Process(int processId, int arrivalTime, int burstTime) {
        super();
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority=0;
        this.startTime=-1;
        this.endTime=-1;
        this.waitingTime=-1;
        this.turnaroundTime=-1;
        this.remainingTime=burstTime;
    }

    // Set and Get methods for all the process information 
    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    @Override
    public String toString() {
        return processId+"\t"+arrivalTime+"\t"+burstTime+"\t"+priority+"\t"+startTime+"\t"+
        endTime+"\t"+waitingTime+"\t"+turnaroundTime;
    }
}
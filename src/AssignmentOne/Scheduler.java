package AssignmentOne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Scheduler {
    
    // Create a new processList using CSV file, set the timeQuantums, 
    // create a new MLFQ, simulate the MLFQ, print the MLFQ result
    public static void  main(String string[]) {
        try{
            List<Process> processList= readCSV("csv.csv");     
            int timeQuantum1=1;
            int timeQuantum2=4;
            MLFQ mlfqfcfs=new MLFQ(processList, timeQuantum1,timeQuantum2);
            mlfqfcfs.simulate();
            mlfqfcfs.printResult(); 
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Are you sure you entered the correct path?");
        } catch (IOException e) {
            System.out.println("Problem importing the CSV. Why don't you try again later?");
        }
    }    

    // Read in the CSV file, set the CSV data to the correct format, insert it to a process
    private static ArrayList<Process> readCSV(String url) throws FileNotFoundException, IOException{
        BufferedReader br = null;
        String csvFile = url;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Process> processes;
        Process process = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            processes = new ArrayList<Process>();
            while ((line = br.readLine()) != null) {
                String[] processesArr = line.split(cvsSplitBy);

                process = new Process(
                    Integer.parseInt(processesArr[0]),  // ProcessID
                    Integer.parseInt(processesArr[1]),  // ArrivalTime             
                    Integer.parseInt(processesArr[2]),  // BurstTime
                    Integer.parseInt(processesArr[3])); // Priority

                processes.add(process);
            }
            return processes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
    }
}
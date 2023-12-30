import java.util.LinkedList;
import java.util.Queue;

public class RR {
    private Queue<Process> processQueue;
    private int currentTime = 0;
    private final int totalMemory = 1024;
    private int availableMemory = totalMemory;
    private int timeQuantum = 2;

    public RR() {
        processQueue = new LinkedList<>();
    }

    public void addProcess(Process process) {
        processQueue.add(process);
    }

    public void schedule() {
        while (!processQueue.isEmpty()) {
            Process currentProcess = processQueue.poll();
            if (currentProcess.memory <= availableMemory) {
                runProcess(currentProcess);
                if (currentProcess.processTime > 1) {
                    processQueue.add(currentProcess);
                }
            } else {
                System.out.println("Not enough memory to run process: " + currentProcess);
            }
        }
    }

    private void runProcess(Process process) {
        System.out.println("Running process using RR: " + process);
        availableMemory -= process.memory;

        process.setStatus("COMPLETED");
        availableMemory += process.memory;
        System.out.println("Process completed: " + process);
    }
}
import java.util.Comparator;
import java.util.PriorityQueue;

public class GBG {
    private PriorityQueue<Process> processQueue;
    private int currentTime = 0;
    private final int totalMemory = 1024;
    private int availableMemory = totalMemory;

    public GBG() {
        processQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.arrivalTime));
    }

    public void addProcess(Process process) {
        processQueue.add(process);
    }

    public void schedule() {
        while (!processQueue.isEmpty()) {
            Process currentProcess = processQueue.peek();
            if (currentProcess != null && currentProcess.arrivalTime <= currentTime) {
                processQueue.poll();
                if (currentProcess.memory <= availableMemory) {
                    runProcess(currentProcess);
                } else {
                    System.out.println("Not enough memory to run process: " + currentProcess);
                }
            }
            currentTime++;
            try {
                Thread.sleep(100); // Simulate time passing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void runProcess(Process process) {
        System.out.println("Running process using GBG: " + process);
        availableMemory -= process.memory; // Allocate memory
        // Simulate process running
        try {
            Thread.sleep(process.processTime * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        availableMemory += process.memory; // Release memory
        System.out.println("Process completed: " + process);
    }
}
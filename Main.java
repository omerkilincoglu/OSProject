import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "giris.txt";
        ProcessScheduler scheduler = new ProcessScheduler();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            System.out.println("İşleme Alınacak Süreçler:");
            System.out.println("PID | Varış | Öncelik | CPU | Bellek | Yazıcılar | Tarayıcılar | Modemler | CD'ler | Durum");
            System.out.println("------------------------------------------------------------------------------------------");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    Process process = new Process(
                            Integer.parseInt(parts[0].trim()),
                            Integer.parseInt(parts[1].trim()),
                            Integer.parseInt(parts[2].trim()),
                            Integer.parseInt(parts[3].trim()),
                            Integer.parseInt(parts[4].trim()),
                            Integer.parseInt(parts[5].trim()),
                            Integer.parseInt(parts[6].trim()),
                            Integer.parseInt(parts[7].trim()),
                            0
                    );

                    scheduler.addProcess(process);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scheduler.schedule();
    }
}
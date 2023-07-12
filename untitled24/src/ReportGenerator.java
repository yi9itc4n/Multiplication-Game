import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGenerator {
    public static boolean generateReport(ArrayList<Child> children) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("rapor.txt"));
            for (Child child : children) {
                writer.write(child.toReport());
            }
            writer.close();
            System.out.println("Rapor oluşturuldu ve rapor.txt dosyasına kaydedildi.");
            return true;
        } catch (IOException e) {
            System.out.println("Rapor oluşturulurken bir hata oluştu: " + e.getMessage());
            return false;
        }
        }
    }


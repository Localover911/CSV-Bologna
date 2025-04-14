import java.io.*;
import java.util.*;

public class Main {
    String filePath = "src/Locatelli.csv";
    File csv = new File (filePath);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\locatelli.21131\\Desktop\\loca.CVS\\Locatelli.CSV";
        File csv = new File (filePath);
        if (!csv.exists()){
            return;
        }
        try{ BufferedReader br = new BufferedReader(new FileReader(csv));
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv));
            String next = "";

            if(!(next == null)) {
                br.readLine();
                String [] campi = next.split(";");
                System.out.println("campi = " + campi.length);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void generaHTML() {
        String percorsoCSV = "Locatelli.csv";
        String percorsoHTML = "Locatelli.html";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(percorsoCSV));
                PrintWriter writer = new PrintWriter(percorsoHTML);
        ) {
            String next = reader.readLine();

            if (next == null) {
                System.out.println("Il file CSV è vuoto.");
                return;
            }

            writer.println("<html><head><title>ElaboratoCSV</title></head><body>");
            writer.println("<h2>CSV</h2>");

            String[] intestazioni = next.split(";");
            writer.print("<tr>");
            for (String colonna : intestazioni) {
                writer.print("<th>" + colonna.trim() + "</th>");
            }
            writer.println("</tr>");

            while ((next = reader.readLine()) != null) {
                String[] valori = next.split(";");
                writer.print("<tr>");
                for (String valore : valori) {
                    writer.print("<td>" + valore.trim() + "</td>");
                }
                writer.println("</tr>");
            }

            writer.println("</table></body></html>");
            System.out.println("File HTML generato con successo: " + percorsoHTML);

        } catch (IOException e) {
            System.out.println("Errore durante la lettura o scrittura: " + e.getMessage());
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "locatelli.csv";

        try {
            List<String[]> records = readCsv(filePath);

            int[] maxColumnWidths = calculateMaxColumnWidths(records);
            int maxRecordLength = calculateMaxRecordLength(records, maxColumnWidths);

            formatAndPrintRecords(records, maxColumnWidths);

            System.out.println("\nLunghezza massima di ciascun campo:");
            for (int i = 0; i < maxColumnWidths.length; i++) {
                System.out.println("Colonna " + (i + 1) + ": " + maxColumnWidths[i] + " caratteri");
            }
            System.out.println("\nLunghezza massima del record: " + maxRecordLength + " caratteri");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;


        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            records.add(values);
        }
        br.close();
        return records;
    }


    private static int[] calculateMaxColumnWidths(List<String[]> records) {
        int numColumns = records.get(0).length;
        int[] maxWidths = new int[numColumns];

        for (String[] record : records) {
            for (int i = 0; i < record.length; i++) {
                if (record[i].length() > maxWidths[i]) {
                    maxWidths[i] = record[i].length();
                }
            }
        }
        return maxWidths;
    }


    private static int calculateMaxRecordLength(List<String[]> records, int[] maxColumnWidths) {
        int maxLength = 0;

        for (String[] record : records) {
            int recordLength = 0;
            for (int i = 0; i < record.length; i++) {
                recordLength += maxColumnWidths[i] + 1; // +1 per il separatore della virgola
            }
            recordLength--;
            if (recordLength > maxLength) {
                maxLength = recordLength;
            }
        }

        return maxLength;
    }


    private static void formatAndPrintRecords(List<String[]> records, int[] maxColumnWidths) {
        for (String[] record : records) {
            for (int i = 0; i < record.length; i++) {

                System.out.print(String.format("%-" + maxColumnWidths[i] + "s", record[i]));
                if (i < record.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
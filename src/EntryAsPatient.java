import java.util.List;
import java.util.Scanner;

public class EntryAsPatient {

    public static void Entry() {
        CSVReader csvReader = new CSVReader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your patient ID and your first name.");

        while (true) {
            List<Patient> patientList= csvReader.readPatientList("patient.csv");
            System.out.print("patient ID: ");
            String patientId = scanner.nextLine();

            System.out.print("first name: ");
            String firstName = scanner.nextLine();
            csvReader.readPatientList("patient.csv");

            if (patientList.contains(patientId) && patientList.get(Integer.parseInt(patientId)).equals(firstName)) {
                System.out.println("Entry successful! Welcome, " + firstName + "!");
                break;
            } else {
                System.out.println("Invalid data! Please enter your data again.");
            }
        }
    }
}

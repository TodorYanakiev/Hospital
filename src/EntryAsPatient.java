import java.util.List;
import java.util.Scanner;

public class EntryAsPatient {

    public static void Entry() {
        System.out.println("Please enter your patient ID: ");
        Scanner sc = new Scanner(System.in);
        int patientID = getPatientID(sc);
        System.out.println("Please enter your first name(use Capital letter): ");
        String patientFirstName = sc.next();
        PatientOptionSelector optionSelector = new PatientOptionSelector();
        if (isPatientExisting(patientID, patientFirstName)) {
            optionSelector.selectOption(patientID);
        } else {
            while (!isPatientExisting(patientID,patientFirstName)){
                System.out.println("There is no such a patient! Enter your patient ID again:");
                patientID = getPatientID(sc);
                System.out.println("Please enter your first name again(use Capital letter): ");
                patientFirstName = sc.next();
            }
            optionSelector.selectOption(patientID);
        }
    }

    private static int getPatientID(Scanner sc){
        String textPatientID = sc.next();
        int patientID;
        while (true) {
            if (textPatientID.matches("^\\d+$")){
                patientID = Integer.parseInt(textPatientID);
                break;
            }else {
                System.out.println("The patient ID must be a whole number! Enter the ID again: ");
                textPatientID = sc.next();
            }
        }
        return patientID;
    }

    private static boolean isPatientExisting(int patientID, String patientFirstName) {
        CSVReader csvReader = new CSVReader();
        List<Patient> patientList = csvReader.readPatientList("patients.csv");
        for (Patient patient : patientList) {
            if (patient.getPatientID() == patientID && patient.getFirstName().equals(patientFirstName)) return true;
        }
        return false;
    }
}

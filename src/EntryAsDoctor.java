import java.util.List;
import java.util.Scanner;

public class EntryAsDoctor {
    public static void Entry() {
        System.out.println("Please enter your doctor ID: ");
        Scanner sc = new Scanner(System.in);
        int doctorID = getDoctorID(sc);
        System.out.println("Please enter your first name(use Capital letter): ");
        String doctorFirstName = sc.next();
        DoctorOptionSelector optionSelector =
                new DoctorOptionSelector("appointments.csv","doctors.csv","patients.csv");
        if (isDoctorExisting(doctorID, doctorFirstName)) {
            optionSelector.selectOption(doctorID);
        } else {
            while (!isDoctorExisting(doctorID, doctorFirstName)) {
                System.out.println("There is no such a doctor! Enter your doctor ID again:");
                doctorID = getDoctorID(sc);
                System.out.println("Please enter your first name again(use Capital letter): ");
                doctorFirstName = sc.next();
            }
            optionSelector.selectOption(doctorID);
        }
    }

    protected static int getDoctorID(Scanner sc) {
        String textDoctorID = sc.next();
        int doctorID;
        while (true) {
            if (textDoctorID.matches("^\\d+$")) {
                doctorID = Integer.parseInt(textDoctorID);
                break;
            } else {
                System.out.println("The doctor ID must be a whole number! Enter the ID again: ");
                textDoctorID = sc.next();
            }
        }
        return doctorID;
    }

    private static boolean isDoctorExisting(int doctorID, String doctorFirstName) {
        CSVReader csvReader = new CSVReader();
        List<Doctor> doctorList = csvReader.readDoctorList("doctors.csv");
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorID() == doctorID && doctor.getFirstName().equals(doctorFirstName)) return true;
        }
        return false;
    }
}

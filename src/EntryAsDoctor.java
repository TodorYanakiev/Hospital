import java.util.List;
import java.util.Scanner;

public class EntryAsDoctor {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public EntryAsDoctor(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }

    public void Entry() {
        System.out.println("Please enter your doctor ID: ");
        Scanner sc = new Scanner(System.in);
        int doctorID = getDoctorID(sc);
        System.out.println("Please enter your first name: ");
        String doctorFirstName = sc.next();
        DoctorOptionSelector optionSelector =
                new DoctorOptionSelector(this.appointmentFileName, this.doctorFileName, this.patientFileName);
        if (isDoctorExisting(doctorID, doctorFirstName)) {
            optionSelector.selectOption(doctorID);
        } else {
            while (!isDoctorExisting(doctorID, doctorFirstName)) {
                System.out.println("There is no such a doctor! Enter your doctor ID again:");
                doctorID = getDoctorID(sc);
                System.out.println("Please enter your first name again: ");
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
            if (doctor.getDoctorID() == doctorID && doctor.getFirstName().equalsIgnoreCase(doctorFirstName))
                return true;
        }
        return false;
    }
}

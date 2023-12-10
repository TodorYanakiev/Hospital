import java.util.*;
import java.util.stream.Collectors;

public class AppointmentsSorter {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public AppointmentsSorter(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }

    public List<Appointment> getSortedList(int doctorID) {
        String option = getSortingOption();
        List<Appointment> patientList = new ArrayList<>();
        if (option.equalsIgnoreCase("1")){
            patientList = getSortedListByPatientName(doctorID);
        }
        return patientList;
    }

    public List<Appointment> getSortedListByPatientName(int doctorID) {
        List<Appointment>
    }

    private List<Appointment> getListIncreasingOrDecreasingOrder(List<Appointment> appointmentList, Scanner sc) {
        System.out.println("Select order - increasing or decreasing: ");
        String order = sc.next();
        while (true) {
            if (order.equalsIgnoreCase("increasing")) {
                return appointmentList;
            } else if (order.equalsIgnoreCase("decreasing")) {
                Collections.reverse(appointmentList);
                return appointmentList;
            } else {
                System.out.println("Invalid order! Enter again(increasing or decreasing): ");
                order = sc.next();
            }
        }
    }

    private String getSortingOption() {
        System.out.println("Sort appointments by(enter only the number): ");
        System.out.println("1. by patient name   2. by date   3. patient ID");
        Scanner sc = new Scanner(System.in);
        String option = sc.next();
        while (true) {
            if (option.equals("1")) {
                return option;
            } else if (option.equals("2")) {
                return option;
            } else if (option.equals("3")) {
                return option;
            } else {
                System.out.println("Invalid option! Enter again: ");
                option = sc.next();
            }
        }
    }

    public List<Patient> getPatientListFromAppointments() {
        CSVReader reader = new CSVReader();
        List<Appointment> appointmentList = reader.readAppointmentList(this.appointmentFileName);
        List<Integer> patientIDList = appointmentList.stream().map(Appointment::getPatientID).toList();
        List<Patient> patientList = reader.readPatientList(this.patientFileName);
        List<Patient> filteredPatientList = new ArrayList<>();
        for (int patientID : patientIDList) {
            for (Patient patient : patientList) {
                if (patient.getPatientID() == patientID && !filteredPatientList.contains(patient)) {
                    filteredPatientList.add(patient);
                }
            }
        }
        return filteredPatientList;
    }
}

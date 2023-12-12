import java.util.*;

public class AppointmentsSorter {
    private final String appointmentFileName;
    private final String doctorFileName;
    private final String patientFileName;
    private final int doctorID;

    public AppointmentsSorter(String appointmentFileName, String doctorFileName, String patientFileName, int doctorID) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
        this.doctorID = doctorID;
    }

    public List<Appointment> getSortedList() {
        Scanner sc = new Scanner(System.in);
        String option = getSortingOption(sc);
        List<Appointment> sortedAppointmentList;
        if (option.equals("1")) {
            sortedAppointmentList = makeAppointmentListIncreasingOrDecreasingOrder(getSortedAppointmentListByPatientName(), sc);
        } else if (option.equals("2")) {
            sortedAppointmentList = makeAppointmentListIncreasingOrDecreasingOrder(getSortedAppointmentListByDateTime(), sc);
        } else {
            sortedAppointmentList = makeAppointmentListIncreasingOrDecreasingOrder(getSortedAppointmentListByPatientID(), sc);
        }
        sc.close();
        return sortedAppointmentList;
    }

    private List<Appointment> getSortedAppointmentListByPatientName() {
        return new ArrayList<>();
        //
    }

    private List<Appointment> getSortedAppointmentListByPatientID() {
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Appointment> currentDoctorAppointmentsList = allAppointmentsList.stream()
                .filter(appointment -> appointment.getDoctorID() == this.doctorID).toList();
        List<Appointment> sortedCurrentDoctorAppointmentsList = currentDoctorAppointmentsList.stream()
                .sorted(Comparator.comparing(Appointment::getPatientID)).toList();
        return sortedCurrentDoctorAppointmentsList;
    }

    private List<Appointment> getSortedAppointmentListByDateTime() {
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Appointment> currentDoctorAppointmentsList = allAppointmentsList.stream()
                .filter(appointment -> appointment.getDoctorID() == this.doctorID).toList();
        Comparator<Appointment> compareByDateAndTime = Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTime);
        List<Appointment> sortedCurrentDoctorAppointmentsList = currentDoctorAppointmentsList.stream().sorted(compareByDateAndTime).toList();
        return sortedCurrentDoctorAppointmentsList;
    }

    private List<Appointment> makeAppointmentListIncreasingOrDecreasingOrder(List<Appointment> appointmentList, Scanner sc) {
        System.out.println("Select order - increasing or decreasing: ");
        String order = sc.nextLine();
        while (true) {
            if (order.equalsIgnoreCase("increasing")) {
                return appointmentList;
            } else if (order.equalsIgnoreCase("decreasing")) {
                List<Appointment> reversedAppointmentList = new ArrayList<>(appointmentList);
                Collections.reverse(reversedAppointmentList);
                return reversedAppointmentList;
            } else {
                System.out.println("Invalid order! Enter again(increasing or decreasing): ");
                order = sc.nextLine();
            }
        }
    }

    private String getSortingOption(Scanner sc) {
        System.out.println("Sort appointments by(enter only the number): ");
        System.out.println("1. by patient name   2. by date   3. patient ID");
        String option = sc.nextLine();
        while (true) {
            if (option.equals("1") || option.equals("2") || option.equals("3")) {
                return option;
            } else {
                System.out.println("Invalid option! Enter again: ");
                option = sc.nextLine();
            }
        }
    }

    private List<Patient> getPatientListFromAppointments() {
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

    private String getPatientFullName(int patientId, List<Patient> patientList) {
        for (Patient patient : patientList) {
            if (patient.getPatientID() == patientId) {
                return patient.getFirstName() + "" + patient.getLastName();
            }
        }
        return "";
    }
}

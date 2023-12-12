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
        return sortedAppointmentList;
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
        System.out.println("Select order - ascending or descending: ");
        String order = sc.nextLine();
        while (true) {
            if (order.equalsIgnoreCase("ascending")) {
                return appointmentList;
            } else if (order.equalsIgnoreCase("descending")) {
                List<Appointment> reversedAppointmentList = new ArrayList<>(appointmentList);
                Collections.reverse(reversedAppointmentList);
                return reversedAppointmentList;
            } else {
                System.out.println("Invalid order! Enter again(ascending or descending): ");
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

    private List<Appointment> getSortedAppointmentListByPatientName() {
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Appointment> currentDoctorAppointmentsList = allAppointmentsList.stream()
                .filter(appointment -> appointment.getDoctorID() == this.doctorID).toList();
        Comparator<Appointment> compareByPatientName = Comparator.comparing(appointment -> getPatientName(appointment.getPatientID()));
        List<Appointment> sortedCurrentDoctorAppointmentsList = currentDoctorAppointmentsList.stream().sorted(compareByPatientName).toList();
        return sortedCurrentDoctorAppointmentsList;
    }

    private String getPatientName(int patientID) {
        CSVReader reader = new CSVReader();
        List<Patient> allPatientsList = reader.readPatientList(this.patientFileName);
        Optional<Patient> patient = allPatientsList.stream()
                .filter(p -> p.getPatientID() == patientID)
                .findFirst();

        return patient.map(p -> p.getFirstName() + " " + p.getLastName()).orElse("");
    }
}

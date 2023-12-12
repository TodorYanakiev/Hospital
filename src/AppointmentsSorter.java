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
        String option = getSortingOption();
        List<Appointment> patientList = new ArrayList<>();
        if (option.equalsIgnoreCase("1")){
            return makeAppointmentListIncreasingOrDecreasingOrder(getSortedAppointmentListByPatientName());
        } else if (option.equalsIgnoreCase("2")) {
            return makeAppointmentListIncreasingOrDecreasingOrder(getSortedAppointmentListByDateTime());
        }
        return patientList;
    }

    public List<Appointment> getSortedAppointmentListByPatientName() {
        return new ArrayList<>();
        //
    }

    public List<Appointment> getSortedAppointmentListByDateTime(){
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Appointment> currentDoctorAppointmentsList = allAppointmentsList.stream()
                .filter(appointment -> appointment.getDoctorID() == this.doctorID).toList();
        Comparator<Appointment> compareByDateAndTime = Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTime);
        List<Appointment> filteredCurrentDoctorAppointmentsList = currentDoctorAppointmentsList.stream().sorted(compareByDateAndTime).toList();
        return filteredCurrentDoctorAppointmentsList;
    }

    private List<Appointment> makeAppointmentListIncreasingOrDecreasingOrder(List<Appointment> appointmentList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select order - increasing or decreasing: ");
        String order = sc.next();
        while (true) {
            if (order.equalsIgnoreCase("increasing")) {
                sc.close();
                return appointmentList;
            } else if (order.equalsIgnoreCase("decreasing")) {
                List<Appointment> reversedAppointmentList = new ArrayList<>(appointmentList);
                Collections.reverse(reversedAppointmentList);
                sc.close();
                return reversedAppointmentList;
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

    private String getPatientFullName(int patientId, List<Patient> patientList) {
        for (Patient patient : patientList) {
            if (patient.getPatientID() == patientId) {
                return patient.getFirstName() + "" + patient.getLastName();
            }
        }
        return "";
    }
}

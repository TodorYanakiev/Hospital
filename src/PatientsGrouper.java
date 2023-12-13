import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PatientsGrouper {
    private final String appointmentFileName;
    private final String patientFileName;
    private final String doctorFileName;

    public PatientsGrouper(String appointmentFileName, String patientFileName, String doctorFileName) {
        this.appointmentFileName = appointmentFileName;
        this.patientFileName = patientFileName;
        this.doctorFileName = doctorFileName;
    }

    public Map<String, List<Patient>> getGroupedPatients(){
        Scanner sc = new Scanner(System.in);
        Map<String, List<Patient>> groupedPatientsMap;
        String option = getGroupingOption(sc);
        if (option.equals("1")){
            groupedPatientsMap = groupPatientsByDoctorName();
        } else if (option.equals("2")) {
            groupedPatientsMap = groupPatientsByDoctorSpeciality();
        } else {
            groupedPatientsMap = groupPatientsByAppointmentDate();
        }
        return groupedPatientsMap;
    }

    private Map<String, List<Patient>> groupPatientsByDoctorName() {
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Doctor> allDoctorsList = reader.readDoctorList(this.doctorFileName);
        List<Patient> allPatientsList = reader.readPatientList(this.patientFileName);

        Map<Integer, String> doctorIdToNameMap = allDoctorsList.stream()
                .collect(Collectors.toMap(doctor -> Integer.valueOf(doctor.getDoctorID())
                        , doctor -> doctor.getFirstName() + " " + doctor.getLastName()));

        Map<String, List<Patient>> patientsGroupedByDoctorName = allAppointmentsList.stream()
                .collect(Collectors.groupingBy(appointment -> doctorIdToNameMap.get(Integer.valueOf(appointment.getDoctorID())),
                        Collectors.flatMapping(appointment -> getPatientsById(allPatientsList, appointment.getPatientID())
                                .stream(), Collectors.toList())));
        patientsGroupedByDoctorName.replaceAll((doctor, patients) -> removeDuplicatingPatients(patients));


        return patientsGroupedByDoctorName;
    }

    private Map<String, List<Patient>> groupPatientsByDoctorSpeciality() {
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Doctor> allDoctorsList = reader.readDoctorList(this.doctorFileName);
        List<Patient> allPatientsList = reader.readPatientList(this.patientFileName);

        Map<Integer, String> doctorIdToSpecialityMap = allDoctorsList.stream()
                .collect(Collectors.toMap(doctor -> Integer.valueOf(doctor.getDoctorID())
                        , doctor -> doctor.getSpecialty()));
        Map<String, List<Patient>> patientsGroupedByDoctorSpeciality = allAppointmentsList.stream()
                .collect(Collectors.groupingBy(appointment -> doctorIdToSpecialityMap.get(Integer.valueOf(appointment.getDoctorID())),
                        Collectors.flatMapping(appointment -> getPatientsById(allPatientsList, appointment.getPatientID())
                                .stream(), Collectors.toList())));
        patientsGroupedByDoctorSpeciality.replaceAll((doctor, patients) -> removeDuplicatingPatients(patients));
        return patientsGroupedByDoctorSpeciality;
    }

    public Map<LocalDate, List<Patient>> groupPatientsByAppointmentDate(){
        CSVReader reader = new CSVReader();
        List<Appointment> allAppointmentsList = reader.readAppointmentList(this.appointmentFileName);
        List<Patient> allPatientsList = reader.readPatientList(this.patientFileName);

        Map<LocalDate, List<Patient>> patientsGroupedByAppointmentDate = allAppointmentsList.stream()
                .collect(Collectors.groupingBy(Appointment::getDate,Collectors
                        .flatMapping(appointment -> getPatientsById(allPatientsList,appointment.getPatientID())
                                .stream(),Collectors.toList())));
        patientsGroupedByAppointmentDate.replaceAll((localDate, patientList) -> removeDuplicatingPatients(patientList));
        return patientsGroupedByAppointmentDate;
    }

    private List<Patient> removeDuplicatingPatients(List<Patient> patients) {
        Set<Patient> uniquePatients = new HashSet<>(patients);
        return new ArrayList<>(uniquePatients);
    }

    private List<Patient> getPatientsById(List<Patient> patients, int patientId) {
        return patients.stream()
                .filter(patient -> patient.getPatientID() == patientId)
                .collect(Collectors.toList());
    }

    private String getGroupingOption(Scanner sc) {
        System.out.println("Group patients by(enter only the number): ");
        System.out.println("1. doctor name   2. doctor's speciality   3. date");
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
}

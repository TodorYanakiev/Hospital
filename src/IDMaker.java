import java.util.List;
public class IDMaker {
    public static int makeAppointmentID(){
        CSVReader reader = new CSVReader();
        List<Appointment> appointmentList = reader.readAppointmentList("appointments.csv");
        int highestID = appointmentList.stream().mapToInt(Appointment::getAppointmentID).max().orElse(0) + 1;
        return highestID;
    }

    public static int makePatientID(){
        CSVReader reader = new CSVReader();
        List<Patient> patientList = reader.readPatientList("patients.csv");
        int highestID = patientList.stream().mapToInt(Patient::getPatientID).max().orElse(0) + 1;
        return highestID;
    }

    public static int makeDoctorID(){
        CSVReader reader = new CSVReader();
        List<Doctor> doctorList = reader.readDoctorList("doctors.csv");
        int highestID = doctorList.stream().mapToInt(Doctor::getDoctorID).max().orElse(0) + 1;
        return highestID;
    }
}

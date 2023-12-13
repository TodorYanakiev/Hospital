import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisualiseAllAppointmentsFoPatient {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public VisualiseAllAppointmentsFoPatient(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }

    public void visualizePatientAppointments(int patientId) {
        CSVReader csvReader = new CSVReader();
        List<Appointment> allAppointments = csvReader.readAppointmentList(appointmentFileName);
        List<Appointment> filteredAppointments = allAppointments.stream().filter(appointment -> appointment.getPatientID() == patientId).toList();
        filteredAppointments.forEach(System.out::println);
    }
}
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentsForDoctorVisualization {
    public static List<Appointment> getAppointmentsForDoctor(int doctorID,String appointmentsFileName){
        CSVReader reader = new CSVReader();
        List<Appointment> appointmentList = reader.readAppointmentList(appointmentsFileName);
        List<Appointment> filteredAppointmentsList = appointmentList.stream()
                .filter(appointment -> appointment.getDoctorID() == doctorID).collect(Collectors.toList());
        return  filteredAppointmentsList;
    }
}

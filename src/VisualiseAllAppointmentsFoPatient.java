import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisualiseAllAppointmentsFoPatient {
    private static final String APPOINTMENTS_FILE = "appointments.csv";
    private static final String DOCTORS_FILE = "doctors.csv";

    public static void visualizePatientAppointments(int patientId) {
        try {
            BufferedReader appointmentsReader = new BufferedReader(new FileReader(APPOINTMENTS_FILE));
            BufferedReader doctorsReader = new BufferedReader(new FileReader(DOCTORS_FILE));

            List<String[]> appointments = new ArrayList<>();
            List<String[]> doctors = new ArrayList<>();

            String line;
            while ((line = appointmentsReader.readLine()) != null) {
                String[] appointmentData = line.split(",");
                appointments.add(appointmentData);
            }

            while ((line = doctorsReader.readLine()) != null) {
                String[] doctorData = line.split(",");
                doctors.add(doctorData);
            }

            boolean foundAppointments = false;
            for (String[] appointment : appointments) {
                if (appointment[1].equals(String.valueOf(patientId))) {
                    foundAppointments = true;
                }
            }

            if (!foundAppointments) {
                System.out.println("This patient has no appointments.");
            }
            appointmentsReader.close();
            doctorsReader.close();
        } catch (IOException e) {
            System.err.println("Error: The file for the appointments is not found.");
        }
    }
}

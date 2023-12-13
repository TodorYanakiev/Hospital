import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CancelAppointment {
    private static final String APPOINTMENTS_FILE = "appointments.csv";

    public void cancelAnAppointment(int patientId) {
        Scanner scanner = new Scanner(System.in);

        try (BufferedReader br = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            System.out.print("Enter the appointment ID you want to cancel: ");
            int appointmentIdToCancel = scanner.nextInt();
            scanner.nextLine();

            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] appointmentData = line.split(",");
                int currentPatientId = Integer.parseInt(appointmentData[1]);
                int currentAppointmentId = Integer.parseInt(appointmentData[0]);

                if (currentPatientId == patientId && currentAppointmentId == appointmentIdToCancel) {
                    System.out.println("Appointment canceled successfully!");
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(APPOINTMENTS_FILE)) {
                writer.write(fileContent.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

import java.io.*;
import java.util.Scanner;

public class ChangeDateAndTimeForAppointment {
    public static void updateAppointmentDateTime(int appointmentId) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter new date (dd-mm-yyyy): ");
            String newDate = scanner.next();

            System.out.print("Enter new time (hhmm): ");
            String newTime = scanner.next();

            if (isValidDateTime(newDate, newTime)) {
                if (updateAppointmentInFile(appointmentId, newDate, newTime)) {
                    System.out.println("Appointment updated successfully.");
                    break;
                } else {
                    System.out.println("Error updating appointment. Please try again.");
                }
            } else {
                System.out.println("Invalid date or time format. Please enter valid date and time.");
            }
        }
    }

    private static boolean isValidDateTime(String date, String time) {
        return true;
    }

    private static boolean updateAppointmentInFile(int appointmentId, String newDate, String newTime) {
        try {
            File inputFile = new File("appointments.csv");
            File tempFile = new File("appointments.csv");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int currentAppointmentId = Integer.parseInt(parts[0].trim());

                    if (currentAppointmentId == appointmentId) {
                        parts[3] = newDate;
                        parts[4] = newTime;
                        line = String.join(",", parts);
                    }

                    writer.println(line);
                }
            }

            if (tempFile.renameTo(inputFile)) {
                return true;
            } else {
                System.out.println("Error updating appointment. Please try again.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

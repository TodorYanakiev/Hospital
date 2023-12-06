import java.io.*;
import java.util.Scanner;

public class ScheduleNewAppointment {
    public static void scheduleAppointment(Patient patient) {
        Scanner scan = new Scanner(System.in);
        int doctorID = searchForDoctorId();
        System.out.println("Enter the type of the examination:");
        String typeOfExamination = scan.nextLine();
        System.out.println("Enter date and time you would like to schedule:");
        String date = scan.nextLine();
        String time = scan.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter("appointments.csv", true))) {
            writer.println(IDMaker.makeAppointmentID() + "," + patient + "," + typeOfExamination + "," + date + "," + time + "," + doctorID);
            System.out.println("Appointment scheduled successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to appointments.csv.");
        }
    }
    private static int searchForDoctorId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter doctorId: ");
        int doctorId = scanner.nextInt();

        while (!doctorExists(doctorId)) {
            System.out.println("Invalid doctorId. Please enter a valid doctorId.");
            System.out.print("Enter doctorId: ");
            doctorId = scanner.nextInt();
        }

        return doctorId;
    }

    private static boolean doctorExists(int doctorId) {
        return entityExists("doctors.csv", doctorId);
    }

    private static boolean entityExists(String filename, int entityId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                if (id == entityId) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from " + filename + ".");
        }
        return false;
    }
}
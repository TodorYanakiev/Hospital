import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CSVWriter {
    public static void addAppointment(Appointment appointment, File file) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
            printStream.println();
            printStream.print(appointment.getAppointmentID() + "," + appointment.getPatientID() + "," +
                    appointment.getTypeOfExamination() + "," + appointment.getDate() + "," + appointment.getTime() + "," +
                    appointment.getDoctorID());
            printStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addDoctor(Doctor doctor, File file) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
            printStream.println();
            printStream.print(doctor.getDoctorID() + "," + doctor.getFirstName() + "," +
                    doctor.getLastName() + "," + doctor.getSpecialty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addPatient(Patient patient, File file) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
            printStream.println();
            printStream.print(patient.getPatientID() + "," + patient.getFirstName() + "," +
                    patient.getLastName() + "," + patient.getAge());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

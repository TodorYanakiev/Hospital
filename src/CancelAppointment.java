import java.io.*;
import java.util.List;
import java.util.Scanner;

public class CancelAppointment {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public CancelAppointment(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }
    public void cancelAnAppointment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter appointment ID: ");
        int appointmentID = getAppointmentID(sc);
        while (!isAppointmentExisting(appointmentID)) {
            appointmentID = getAppointmentID(sc);
        }
        CSVReader reader = new CSVReader();
        List<Appointment> appointments = reader.readAppointmentList(appointmentFileName);
        int size = appointments.size();
        for (int i = 0; i < size; i++) {
            if (appointments.get(i).getAppointmentID() == appointmentID) {
                appointments.remove(appointments.get(i));
                break;
            }
        }
        try {
            PrintStream printStream = new PrintStream(new File(appointmentFileName));
            printStream.println("appointment_id, patient_id, type_of_examination, date, time, doctor_id");
            for (Appointment appointment: appointments) {
                printStream.println(appointment.getAppointmentID() + "," + appointment.getPatientID() + "," +
                        appointment.getTypeOfExamination() + "," + appointment.getDate() + "," + appointment.getTime() + "," +
                        appointment.getDoctorID());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Successfully canceled appointment!");
    }
    public int getAppointmentID(Scanner sc) {
        int appointmentId;
        String textAppointmentID = sc.next();
        while (true) {
            if (textAppointmentID.matches("^\\d+$")) {
                appointmentId = Integer.parseInt(textAppointmentID);
                break;
            } else {
                System.out.println("Invalid input, enter appointmentID again: ");
                textAppointmentID = sc.next();
            }
        }
        return appointmentId;
    }
    private boolean isAppointmentExisting(int appointmentID) {
        CSVReader csvReader = new CSVReader();
        List<Appointment> appointments = csvReader.readAppointmentList(appointmentFileName);
        boolean isAppointmentExisting = appointments.stream().anyMatch(appointment -> appointment.getAppointmentID() == appointmentID);
        return isAppointmentExisting;
    }
}

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeDateAndTimeForAppointment {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public ChangeDateAndTimeForAppointment(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }
    public void updateAppointmentDateTime(int appointmentId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter appointment ID");
        int appointmentID = getAppointmentID(sc);
        while(!isAppointmentExisting(appointmentID)) {
            System.out.println("Non existing appointment.");
            System.out.println("Enter appointment ID again: ");
            appointmentID = getAppointmentID(sc);
        }
        System.out.println("Enter new date: ");
        String date = getDate(sc);

        System.out.println("Enter new time: ");
        String time = getTime(sc);

        CSVReader csvReader = new CSVReader();
        List<Appointment> appointments = csvReader.readAppointmentList(appointmentFileName);
        int size = appointments.size();
        for (int i = 0; i < size; i++) {
            if (appointments.get(i).getAppointmentID() == appointmentID) {
                appointments.get(i).setDate(LocalDate.parse(date));
                appointments.get(i).setTime(Integer.parseInt(time));
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

    private String getDate(Scanner sc) {
        String date = sc.next();
        while (true) {
            if (date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                break;
            } else {
                System.out.println("Date is in incorrect format, please rewrite it in this format(yyyy-mm-dd): ");
                date = sc.next();
            }
        }
        return date;
    }

    private String getTime(Scanner sc) {
        String time = sc.next();
        while (true) {
            if (time.matches("^\\d{4}$")) {
                break;
            } else {
                System.out.println("Time is in incorrect format, please rewrite it in this format(hhmm): ");
                time = sc.next();
            }
        }
        return time;
    }
}

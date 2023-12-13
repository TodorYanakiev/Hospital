import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ScheduleNewAppointment {
    private String appointmentsFileName;

    public ScheduleNewAppointment(String appointmentsFileName) {
        this.appointmentsFileName = appointmentsFileName;
    }

    public void scheduleAppointment(Patient patient) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter doctor's ID: ");
        int doctorID = getDoctorID(scan);
        while (!isDoctorExisting(doctorID)) {
            System.out.println("Invalid doctorID. Please enter a valid doctorID.");
            System.out.print("Enter doctorID: ");
            doctorID = getDoctorID(scan);
        }
        System.out.println("Enter the type of the examination: ");
        String typeOfExamination = scan.next();
        System.out.println("Please enter date: ");
        String date = getDate(scan);
        System.out.println("Please enter time: ");
        String time = getTime(scan);

        CSVWriter.addAppointment(new Appointment(IDMaker.makeAppointmentID(),
                patient.getPatientID(), typeOfExamination, LocalDate.parse(date), Integer.parseInt(time), doctorID),
                new File(appointmentsFileName));
        System.out.println("Appointment scheduled successfully.");
    }

    private int getDoctorID(Scanner sc) {
        String textDoctorID = sc.next();
        int doctorID;
        while (true) {
            if (textDoctorID.matches("^\\d+$")) {
                doctorID = Integer.parseInt(textDoctorID);
                break;
            } else {
                System.out.println("The doctor ID must be a whole number! Enter the ID again: ");
                textDoctorID = sc.next();
            }
        }
        return doctorID;
    }

    private boolean isDoctorExisting(int doctorID) {
        CSVReader csvReader = new CSVReader();
        List<Doctor> doctorList = csvReader.readDoctorList("doctors.csv");
        for (Doctor doctor : doctorList) {
            if (doctor.getDoctorID() == doctorID) return true;
        }
        return false;
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
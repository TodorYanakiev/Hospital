import java.util.List;
import java.util.Scanner;

public class DoctorOptionSelector {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public DoctorOptionSelector(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }


    public void selectOption(int doctorID) {
        //TODO gives NoSuchElementException
        Scanner sc = new Scanner(System.in);
        String option;
        do {
            System.out.println("Chose one from the options(write only the number):");
            System.out.println("1. Show all appointments for a doctor.");
            System.out.println("2. Sort  all appointments for a doctor.");
            System.out.println("3. Group patients.");
            System.out.println("4. Exit.");
            option = sc.next();
            while (true) {
                if (option.equals("1")) {
                    int newID = changeDoctorID(doctorID);
                    showAllAppointments(newID);
                    break;
                } else if (option.equals("2")) {
                    int newID = changeDoctorID(doctorID);
                    sortAllAppointments(newID);
                    break;
                } else if (option.equals("3")) {
                    groupPatients(doctorID);
                    break;
                } else if (option.equals("4")) {
                    break;
                } else {
                    System.out.println("Invalid input! Write only the number:");
                    option = sc.next();
                }
            }
        } while (!option.equals("4"));
    }

    public void showAllAppointments(int doctorID) {
        List<Appointment> appointmentList = AppointmentsForDoctorVisualization.getAppointmentsForDoctor(doctorID,this.appointmentFileName);
        System.out.println("All appointments for doctor with ID = " + doctorID + " are: ");
        appointmentList.forEach(System.out::println);
        System.out.println();
    }

    public void sortAllAppointments(int doctorID) {

    }

    public void groupPatients(int doctorID) {

    }

    private int changeDoctorID(int doctorID) {
        System.out.println("Do you want to change the doctor ID(yes/no):");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        while (true) {
            if (answer.equalsIgnoreCase("yes")) {
                return getNewDoctorID(sc, doctorID);
            } else if (answer.equalsIgnoreCase("no")) {
                return doctorID;
            } else {
                System.out.println("Invalid answer! Enter yes or no:");
                answer = sc.next();
            }
        }
    }

    private int getNewDoctorID(Scanner sc, int doctorID) {
        int newDoctorID;
        System.out.println("Enter the new ID:");
        newDoctorID = EntryAsDoctor.getDoctorID(sc);
        CSVReader reader = new CSVReader();
        List<Doctor> doctorList = reader.readDoctorList("doctors.csv");
        boolean isDoctorInTheList = doctorList.stream().anyMatch(doctor -> doctor.getDoctorID() == newDoctorID);
        if (isDoctorInTheList) {
            return newDoctorID;
        } else {
            System.out.println("There is no doctor with such an ID! The ID remains as before.");
        }
        return doctorID;
    }
}
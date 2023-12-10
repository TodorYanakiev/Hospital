import java.util.Scanner;

public class DoctorOptionSelector {
    private int doctorID;
    private String appointmentFileName;

    public DoctorOptionSelector(int doctorID, String appointmentFileName) {
        this.doctorID = doctorID;
        this.appointmentFileName = appointmentFileName;
    }

    public void selectOption() {
        Scanner sc = new Scanner(System.in);
        String option;
        while (true) {
            System.out.println("Chose one from the options(write only the number):");
            System.out.println("1. Show all appointments for a doctor.");
            System.out.println("2. Sort  all appointments for a doctor.");
            System.out.println("3. Group patients.");
            System.out.println("4. Exit.");
            option = sc.next();
            while (true) {
                if (option.equals("1")) {
                    showAllAppointments();
                    break;
                } else if (option.equals("2")) {
                    sortAllAppointments();
                    break;
                } else if (option.equals("3")) {
                    groupPatients();
                    break;
                } else if (option.equals("4")) {
                    break;
                } else {
                    System.out.println("Invalid input! Write only the number:");
                    option = sc.next();
                }
            }
            if (option.equals("4")){
                break;
            }
        }

    }

    public void showAllAppointments() {
    }

    public void sortAllAppointments() {
    }

    public void groupPatients() {
    }
}

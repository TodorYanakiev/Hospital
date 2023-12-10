import java.util.Scanner;

public class DoctorOptionSelector {
    public void selectOption(int doctorID) {
        System.out.println("Chose one from the options(write only the number):");
        System.out.println("1. Show all appointments for a doctor.");
        System.out.println("2. Sort  all appointments for a doctor.");
        System.out.println("3. Group patients.");
        Scanner sc = new Scanner(System.in);
        String option = sc.next();
        while (true) {
            switch (option) {
                case "1":
                    showAllAppointments();
                    break;
                case "2":
                    sortAllAppointments();
                    break;
                case "3":
                    groupPatients();
                    break;
                default:
                    System.out.println("Invalid option! Please enter again:");
                    option = sc.next();
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

import java.util.Scanner;

public class RoleSelector {
    public static String selectRole() {
        System.out.println("Please enter your role (doctor or patient): ");
        Scanner sc = new Scanner(System.in);
        String role = sc.next();
        role = role.toLowerCase();
        while (true) {
            if (role.equals("doctor")) {
                System.out.println("You entered as doctor.");
                return "doctor";
            } else if (role.equals("patient")) {
                System.out.println("You entered as patient.");
                return "patient";
            } else {
                System.out.println("Incorrect input! Please reenter your role: ");
                role = sc.next();
            }
        }
    }
}

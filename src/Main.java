import java.util.Scanner;

public class Main {
    public static String getUserRole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your role (patient or doctor):");
        String role = sc.next();
        role.toLowerCase();
        while (true) {
            if (role.equals("doctor") || role.equals("patient")) {
                break;
            }
            System.out.println("Your role must be \"patient\" or \"doctor\": ");
            role = sc.next();
        }
        return role;
    }

    public static void main(String[] args) {
        if (getUserRole().equals("doctor")) {

        } else {

        }
    }
}
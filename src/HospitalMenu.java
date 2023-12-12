public class HospitalMenu {
    public void startHospital() {
        String userRole = RoleSelector.selectRole();
        if (userRole.equalsIgnoreCase("patient")) {
            System.out.println("You entered as patient.");
            EntryAsPatient.Entry();
        } else if (userRole.equalsIgnoreCase("doctor")) {
            System.out.println("You entered as doctor.");
            EntryAsDoctor.Entry();
        }
    }
}

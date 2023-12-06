public class HospitalMenu {
    public void startHospital(){
        String userRole = RoleSelector.selectRole();
        if (userRole.equals("patient")){
            System.out.println("You entered as patient.");
            EntryAsPatient.Entry();
        } else if (userRole.equals("doctor")) {
            System.out.println("You entered as doctor.");

        }
    }
}

public class HospitalMenu {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;

    public HospitalMenu(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }

    public void startHospital() {
        String userRole = RoleSelector.selectRole();
        if (userRole.equalsIgnoreCase("patient")) {
            System.out.println("You entered as patient.");
            EntryAsPatient entryAsPatient = new EntryAsPatient(this.appointmentFileName, this.doctorFileName, this.patientFileName);
            entryAsPatient.Entry();
        } else if (userRole.equalsIgnoreCase("doctor")) {
            System.out.println("You entered as doctor.");
            EntryAsDoctor entryAsDoctor = new EntryAsDoctor(this.appointmentFileName, this.doctorFileName, this.patientFileName);
            entryAsDoctor.Entry();
        }
    }
}

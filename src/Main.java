public class Main {
    public static void main(String[] args) {
        HospitalMenu hm = new HospitalMenu("appointments.csv", "doctors.csv", "patients.csv");
        hm.startHospital();
    }
}
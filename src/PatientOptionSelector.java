import java.util.Scanner;

public class PatientOptionSelector {
    private String appointmentFileName;
    private String doctorFileName;
    private String patientFileName;
    public PatientOptionSelector(String appointmentFileName, String doctorFileName, String patientFileName) {
        this.appointmentFileName = appointmentFileName;
        this.doctorFileName = doctorFileName;
        this.patientFileName = patientFileName;
    }

    public void selectOption(int patientID) {
            Scanner sc = new Scanner(System.in);
            String option;
            do {
                System.out.println("Chose one from the options(write only the number):");
                System.out.println("1. Make new appointment.");
                System.out.println("2. Visualise all of your appointments.");
                System.out.println("3. Change the date/time for an appointment.");
                System.out.println("4. Cancel an appointment.");
                System.out.println("5. Exit.");
                option = sc.nextLine();
                while (true) {
                    if (option.equals("1")) {
                        Patient patient = null;
                        schedulingNewAppointmentMethod(patient);
                        break;
                    } else if (option.equals("2")) {
                        patientID = Integer.parseInt(sc.nextLine());
                        visualisingAllAppointmentsForPatientMethod(patientID);
                        break;
                    } else if (option.equals("3")) {
                        int appointmentID = Integer.parseInt(sc.nextLine());
                        changingDateAndTimeForPatientMethod(appointmentID);
                        break;
                    } else if (option.equals("4")) {
                        patientID = Integer.parseInt(sc.nextLine());
                        cancelingAppointmentMethod(patientID);
                        break;
                    } else if (option.equals(("5"))) {
                        System.out.println("Exiting hospital software...");
                        break;
                    } else {
                        System.out.println("Invalid input! Write only the number:");
                        option = sc.nextLine();
                    }
                }
            } while (!option.equals("5"));
    }

    public void cancelingAppointmentMethod(int patientID) {
        CancelAppointment cancelAppointment = new CancelAppointment();
        cancelAppointment.cancelAnAppointment(patientID);
    }

    public void visualisingAllAppointmentsForPatientMethod(int patientID) {
        VisualiseAllAppointmentsFoPatient visualiseAllAppointmentsFoPatient = new VisualiseAllAppointmentsFoPatient();
        visualiseAllAppointmentsFoPatient.visualizePatientAppointments(patientID);
    }

    public void changingDateAndTimeForPatientMethod(int appointmentID) {
        ChangeDateAndTimeForAppointment changeDateAndTimeForAppointment = new ChangeDateAndTimeForAppointment();
        changeDateAndTimeForAppointment.updateAppointmentDateTime(appointmentID);
    }

    public void schedulingNewAppointmentMethod(Patient patient) {
        ScheduleNewAppointment scheduleNewAppointment = new ScheduleNewAppointment();
        scheduleNewAppointment.scheduleAppointment(patient);
    }
}

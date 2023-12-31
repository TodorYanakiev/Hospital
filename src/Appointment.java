import java.time.LocalDate;

public class Appointment {
    private int appointmentID;
    private int patientID;
    private String typeOfExamination;
    private LocalDate date;
    private int time;
    private int doctorID;

    public Appointment(int appointmentID, int patientID, String typeOfExamination, LocalDate date, int time, int doctorID) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.typeOfExamination = typeOfExamination;
        this.date = date;
        this.time = time;
        this.doctorID = doctorID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getTypeOfExamination() {
        return typeOfExamination;
    }

    public void setTypeOfExamination(String typeOfExamination) {
        this.typeOfExamination = typeOfExamination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", patientID=" + patientID +
                ", typeOfExamination='" + typeOfExamination + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", doctorID=" + doctorID +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null) {
            return false;
        }
        if (!(obj instanceof Appointment)) {
            return false;
        }
        Appointment secondA = (Appointment) obj;
        return this.appointmentID == secondA.appointmentID && this.patientID == secondA.patientID &&
                this.typeOfExamination.equals(secondA.typeOfExamination) && this.date.equals(secondA.date) && this.time == secondA.time &&
                this.doctorID == secondA.doctorID;
    }
}

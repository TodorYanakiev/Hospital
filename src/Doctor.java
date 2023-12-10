public class Doctor {
    private int doctorID;
    private String firstName;
    private String lastName;
    private String specialty;

    public Doctor(int doctorID, String firstName, String lastName, String specialty) {
        setDoctorID(doctorID);
        setFirstName(firstName);
        setLastName(lastName);
        setSpecialty(specialty);
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null){
            return false;
        }
        if (!(obj instanceof Doctor)) {
            return false;
        }
        if (this.doctorID == ((Doctor) obj).doctorID && this.firstName.equals(((Doctor) obj).firstName) &&
                this.lastName.equals(((Doctor) obj).lastName) && this.specialty.equals(((Doctor) obj).specialty)){
            return true;
        }
        return false;
    }
}

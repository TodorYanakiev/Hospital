public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private int age;

    public Patient(int patientID, String firstName, String lastName, int age) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Patient secondP = (Patient) obj;
        if (this == null || secondP == null) {
            return false;
        }
        if (!(obj instanceof Patient)) {
            return false;
        }
        return this.patientID == secondP.patientID && this.firstName.equals(secondP.firstName) &&
                this.lastName.equals(secondP.lastName) && this.age == secondP.age;
    }
}

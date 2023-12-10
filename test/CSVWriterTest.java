import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class CSVWriterTest {
    @Test
    public void addPatientTestGivenCorrectParameters(){
        //GIVEN
        Patient patient1 = new Patient(IDMaker.makePatientID("patientsTest1.csv"),"Yavor", "Radoslavov",89);
        CSVReader reader = new CSVReader();
        //WHEN
        CSVWriter.addPatient(patient1,new File("patientsTest1.csv"));
        List<Patient> patientList = reader.readPatientList("patientsTest1.csv");
        //THEN
        boolean isPatientInTheList = patientList.stream().anyMatch(patient -> patient.equals(patient1));
        Assertions.assertTrue(isPatientInTheList);
    }

    @Test
    public void addPatientTestGivenNull(){
        //GIVEN
        Patient patient1 = null;
        CSVReader reader = new CSVReader();
        //WHEN
        CSVWriter.addPatient(patient1,new File("patientsTest2.csv"));
        List<Patient> patientList = reader.readPatientList("patientsTest2.csv");
        //THEN
        boolean isPatientInTheList = patientList.stream().anyMatch(patient -> patient.equals(patient1));
        Assertions.assertFalse(isPatientInTheList);
    }

    @Test
    public void addDoctorTestGivenCorrectParameters(){
        //GIVEN
        Doctor doctor1 = new Doctor(IDMaker.makeDoctorID("doctorsTest1.csv"),"Nikolay","Simeonov","cardiology");
        CSVReader reader = new CSVReader();
        //WHEN
        CSVWriter.addDoctor(doctor1,new File("doctorsTest1.csv"));
        List<Doctor> doctorList = reader.readDoctorList("doctorsTest1.csv");
        //THEN
        boolean isDoctorInTheList = doctorList.stream().anyMatch(doctor -> doctor.equals(doctor1));
        Assertions.assertTrue(isDoctorInTheList);
    }

    @Test
    public void addDoctorTestGivenNull(){
        //GIVEN
        Doctor doctor1 = null;
        CSVReader reader = new CSVReader();
        //WHEN
        CSVWriter.addDoctor(doctor1, new File("doctorsTest2.csv"));
        List<Doctor> doctorList = reader.readDoctorList("doctorsTest2.csv");
        //THEN
        boolean isDoctorInTheList = doctorList.stream().anyMatch(doctor -> doctor.equals(doctor1));
        Assertions.assertFalse(isDoctorInTheList);
    }

    @Test
    public void addAppointmentTestGivenCorrectParameters(){
        //GIVEN
        Appointment app1 = new Appointment(IDMaker.makeAppointmentID("appointmentsTest1.csv"),3,
                "procedure",(LocalDate.parse("2024-01-17")),
                930,6);
        CSVReader reader = new CSVReader();
        //WHEN
        CSVWriter.addAppointment(app1, new File("appointmentsTest1.csv"));
        List<Appointment> appointmentList = reader.readAppointmentList("appointmentsTest1.csv");
        //THEN
        boolean isAppointmentInTheList = appointmentList.stream().anyMatch(appointment -> appointment.equals(app1));
        Assertions.assertTrue(isAppointmentInTheList);
    }

    @Test
    public void addAppointmentTestGivenNull(){
        //GIVEN
        Appointment app1 = null;
        CSVReader reader = new CSVReader();
        //WHEN
        CSVWriter.addAppointment(app1, new File("appointmentsTest2.csv"));
        List<Appointment> appointmentList = reader.readAppointmentList("appointmentsTest2.csv");
        //THEN
        boolean isAppointmentInTheList = appointmentList.stream().anyMatch(appointment -> appointment.equals(app1));
        Assertions.assertFalse(isAppointmentInTheList);
    }
}

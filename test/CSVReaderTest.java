import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderTest {
    CSVReader reader = new CSVReader();
    @Test
    public void readDoctorListTest(){
        //GIVEN
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor(1,"Ivaylo","Petrov","urology"));
        doctorList.add(new Doctor(2,"Tsvetomira","Borisova","gynecology"));
        doctorList.add(new Doctor(3,"Krasen","Borislavov","anesthesiology"));
        //WHEN
        List<Doctor> doctorListResult = reader.readDoctorList("doctorsTest1.csv");
        //THEN
        Assertions.assertEquals(doctorList,doctorListResult);
    }

    @Test
    public void readPatientListTest(){
        //GIVEN
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient(1,"Maria","Petrova",25));
        patientList.add(new Patient(2, "Ivan", "Ivanov", 34));
        patientList.add(new Patient(3, "Konstantin", "Marinov", 18));
        //WHEN
        List<Patient> patientListResult = reader.readPatientList("patientsTest1.csv");
        //THEN
        Assertions.assertEquals(patientList,patientListResult);
    }

    @Test
    public void readAppointmentListTest(){
        //GIVEN
        List<Appointment> appointmentsList = new ArrayList<>();
        appointmentsList.add(new Appointment(1,1,"initial",(LocalDate.parse("2023-11-27")),1030,1));
        appointmentsList.add(new Appointment(2,3,"secondary",(LocalDate.parse("2021-09-11")),900,2));
        appointmentsList.add(new Appointment(3,7,"initial",(LocalDate.parse("2021-12-03")),1305,1));
        //WHEN
        List<Appointment> appointmentsListResult = reader.readAppointmentList("appointmentsTest1.csv");
        //THEN
        Assertions.assertEquals(appointmentsList,appointmentsListResult);

    }
}

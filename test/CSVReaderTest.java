import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

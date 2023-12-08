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
}

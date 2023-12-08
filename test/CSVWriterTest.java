import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class CSVWriterTest {

    @Test
    public void addAppointmentTestCorrectParameters(){
        //GIVEN
        Appointment app1 = new Appointment(2,3,"procedure",(LocalDate.parse("2024-01-17")),
                930,6);
        CSVReader reader = new CSVReader();
        List<Appointment> appointmentList = reader.readAppointmentList("appointmentsTest2.csv");
        //WHEN
        CSVWriter.addAppointment(app1, new File("appointmentsTest2.csv"));
        //THEN
        boolean isAppointmentInTheList = appointmentList.stream().anyMatch(appointment -> appointment.equals(app1));
        Assertions.assertTrue(isAppointmentInTheList);
    }

    @Test
    public void addAppointmentTestMethodCalledTwice(){
        //GIVEN
        Appointment app1 = new Appointment(2,3,"procedure",(LocalDate.parse("2024-01-17")),
                930,6);
        CSVReader reader = new CSVReader();
        List<Appointment> appointmentList = reader.readAppointmentList("appointmentsTest2.csv");
        //WHEN
        CSVWriter.addAppointment(app1, new File("appointmentsTest2.csv"));
        CSVWriter.addAppointment(app1, new File("appointmentsTest2.csv"));
        //THEN
        boolean isAppointmentInTheList = appointmentList.stream().anyMatch(appointment -> appointment.equals(app1));
        Assertions.assertTrue(isAppointmentInTheList);
    }

    @Test
    public void addAppointmentTestGivenNull(){
        //GIVEN
        Appointment app1 = null;
        CSVReader reader = new CSVReader();
        List<Appointment> appointmentList = reader.readAppointmentList("appointmentsTest2.csv");
        //WHEN
        CSVWriter.addAppointment(app1, new File("appointmentsTest2.csv"));
        //THEN
        boolean isAppointmentInTheList = appointmentList.stream().anyMatch(appointment -> appointment.equals(app1));
        Assertions.assertFalse(isAppointmentInTheList);
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsForDoctorVisualizationTest {
    @Test
    public void getAppointmentsForDoctorTest(){
        //GIVEN
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment(2,2,"secondary",
                LocalDate.of(2021,9,11),900,2));
        appointmentList.add(new Appointment(5,2,"procedure",
                LocalDate.of(2021,12,3),1305,2));
        appointmentList.add(new Appointment(8,2,"consultation",
                LocalDate.of(2023,11,27),1030,2));
        String appointmentFileName = "appointmentsTest4.csv";
        int doctorID = 2;
        //WHEN
        List<Appointment> appointmentListResult = AppointmentsForDoctorVisualization.getAppointmentsForDoctor(doctorID,appointmentFileName);
        //THEN
        Assertions.assertEquals(appointmentList, appointmentListResult);
    }
}

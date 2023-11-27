import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public List<Doctor> readDoctorList(String fileName){
        File file = new File(fileName);
        List<Doctor> doctorList = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            String[] text;
            while(sc.hasNextLine()){
                text = sc.nextLine().split(",");
                doctorList.add(new Doctor(Integer.parseInt(text[0]),text[1],text[2],text[3]));
            }
            sc.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return doctorList;
    }

    public List<Patient> readPatientList(String fileName){
        File file = new File(fileName);
        List<Patient> patientList = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            String[] text;
            while(sc.hasNextLine()){
                text = sc.nextLine().split(",");
                patientList.add(new Patient(Integer.parseInt(text[0]),text[1],text[2],Integer.parseInt(text[3])));
            }
            sc.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return patientList;
    }

    public List<Appointment> readAppointmentList(String fileName){
        File file = new File(fileName);
        List<Appointment> appointmentList = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            String[] text;
            while(sc.hasNextLine()){
                text = sc.nextLine().split(",");
                appointmentList.add(new Appointment(Integer.parseInt(text[0]),Integer.parseInt(text[1]),text[2],
                        LocalDate.parse(text[3]),Integer.parseInt(text[4]),Integer.parseInt(text[5])));
            }
            sc.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return appointmentList;
    }
}
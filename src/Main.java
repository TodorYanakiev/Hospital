import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalTime d = LocalTime.parse("11:03");
        System.out.println(d);
    }
}
//package Collections.AuditoryExercises;
//
//import java.util.ArrayList;
//import java.util.Deque;
//import java.util.List;
//
//class Driver {
//    private String name;
//    private List<String> laps;
//
//    public Driver(String name, String lap1, String lap2, String lap3) {
//        this.name = name;
//        this.laps = new ArrayList<>();
//        laps.add(lap1);
//        laps.add(lap2);
//        laps.add(lap3);
//    }
//
//    public static Driver createDriverFromInputLine (String line) {
//        String[] parts = line.split("\\s+");
//        String name = parts[0];
//        String lap1 = parts[1];
//        String lap2 = parts[2];
//        String lap3 = parts[3];
//        return new Driver(name, lap1, lap2, lap3);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public static int getLapmillis()
//}
//
//
//public class F1Test {
//
//    public static void main(String[] args) {
//        F1Race f1Race = new F1Race();
//        f1Race.readResults(System.in);
//        f1Race.printSorted(System.out);
//    }
//}

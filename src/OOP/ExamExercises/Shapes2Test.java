////Zadacha 2/51 -> Samo trgni gi 2kite pred sekoja klasa
//package OOP.ExamExercises;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//class IrregularCanvasException extends Exception {
//    public IrregularCanvasException(String message) {
//        super(message);
//    }
//}
//
//class Shape2 {
//    private String id;
//    private int total_shapes;
//    private int total_circles;
//    private int total_squares;
//    private double min_area;
//    private double max_area;
//    private double average_area;
//
//    public Shape2(String id, int total_shapes, int total_circles, int total_squares, double min_area, double max_area, double average_area) {
//        this.id = id;
//        this.total_shapes = total_shapes;
//        this.total_circles = total_circles;
//        this.total_squares = total_squares;
//        this.min_area = min_area;
//        this.max_area = max_area;
//        this.average_area = average_area;
//    }
//
//
//    public String getId() {
//        return id;
//    }
//
//    public int getTotal_shapes() {
//        return total_shapes;
//    }
//
//    public int getTotal_circles() {
//        return total_circles;
//    }
//
//    public int getTotal_squares() {
//        return total_squares;
//    }
//
//    public double getMin_area() {
//        return min_area;
//    }
//
//    public double getMax_area() {
//        return max_area;
//    }
//
//    public double getAverage_area() {
//        return average_area;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s %d %d %d %.2f %.2f %.2f", id, total_shapes, total_circles, total_squares, min_area, max_area, average_area);
//    }
//}
//
//class ShapesApplication2 {
//    private double maxArea;
//
//    public ShapesApplication2(double maxArea) {
//        this.maxArea = maxArea;
//    }
//
//    public int readCanvases2 (InputStream inputStream) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//        String line;
//        while((line = br.readLine()) != null)
//        {
//            int sumCircle = 0;
//            int sumSquare = 0;
//            int circleCounter = 0;
//            int saquareCounter = 0;
//            String []parts = line.split("\\s+");
//            String id = parts[0];
//            for (int i = 1; i < parts.length; i++) {
//                if(parts[i].equals("C"))
//                {
//                    circleCounter++;
//                    sumCircle += Integer.parseInt(parts[i+1]);
//                }
//                else if(parts[i].equals("S"))
//                {
//                    saquareCounter++;
//                    sumSquare += Integer.parseInt(parts[i+1]);
//                }
//            }
//        }
//    }
//
//}
//
//public class Shapes2Test {
//
//    public static void main(String[] args) {
//
//        ShapesApplication2 shapesApplication = new ShapesApplication2(10000);
//
//        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
//        shapesApplication.readCanvases2(System.in);
//
//        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
//        shapesApplication.printCanvases2(System.out);
//
//
//    }
//}

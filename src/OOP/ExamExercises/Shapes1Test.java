//Zadacha 1/51
package OOP.ExamExercises;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Shape {
    private String id;
    private int counter;
    private int perimeter;

    public Shape(String id, int counter, int perimeter) {
        this.id = id;
        this.counter = counter;
        this.perimeter = perimeter;
    }

    public String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public int getPerimeter() {
        return perimeter;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", id, counter, perimeter);
    }
}

class ShapesApplication {

    private List<Shape> shapes;

    public ShapesApplication() {
        shapes = new ArrayList<>();
    }

    public int readCanvases (InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int counter = 0;
        while((line = br.readLine()) != null)
        {
            int sum = 0;
            int current_counter = 0;
            String []parts = line.split("\\s+");
            String id = parts[0];
            for (int i = 1; i < parts.length; i++) {
                counter++;
                current_counter++;
                sum+=Integer.parseInt(parts[i]);
            }
            int perimeter = 4 * sum;
            shapes.add(new Shape(id, current_counter, perimeter));
        }
        return counter;
    }

    public void printLargestCanvasTo (OutputStream outputStream) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        Shape print = shapes.stream()
                .max(Comparator.comparing(Shape::getPerimeter)).get();
        bw.append(print.toString());
        bw.flush();
    }
}

public class Shapes1Test {

    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}

package Collections.LaboratoryExercises;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Student {
    private String id;
    private List<Integer> grades;

    public Student(String id, List<Integer> grades) {
        this.id = id;
        this.grades = grades;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public int getPassedGrades() {
        int counter = 0;
        for (Integer grad : grades) {
            counter++;
        }
        return counter;
    }

    public double getAverageGrade() {
        double sum = 0;
        for (Integer grad : grades) {
            sum += grad;
        }
        return sum / getPassedGrades();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", grades=" + grades +
                '}';
    }
}

class StudentAlreadyExistsException extends Exception {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}


class Faculty {
    private List<Student> students;

    public Faculty() {
        this.students = new ArrayList<>();
    }

    public void addStudent(String id, List<Integer> grades) throws StudentAlreadyExistsException {
        boolean exists = students.stream().anyMatch(s -> s.getId().equals(id));

        if(exists)
        {
            throw new StudentAlreadyExistsException("Student with ID " + id + " already exists");
        }

        students.add(new Student(id, grades));
    }

    public void addGrade(String id, int grade) {
        for (Student stu : students) {
            if (stu.getId().equals(id)) {
                stu.getGrades().add(grade);
            }
        }
    }

    public Set<Student> getStudentsSortedByAverageGrade() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getAverageGrade)
                        .thenComparing(Student::getPassedGrades)
                        .thenComparing(Student::getId).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Student> getStudentsSortedByCoursesPassed() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getPassedGrades)
                        .thenComparing(Student::getAverageGrade)
                        .thenComparing(Student::getId).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}

public class SetsTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Faculty faculty = new Faculty();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "addStudent":
                    String id = tokens[1];
                    List<Integer> grades = new ArrayList<>();
                    for (int i = 2; i < tokens.length; i++) {
                        grades.add(Integer.parseInt(tokens[i]));
                    }
                    try {
                        faculty.addStudent(id, grades);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "addGrade":
                    String studentId = tokens[1];
                    int grade = Integer.parseInt(tokens[2]);
                    faculty.addGrade(studentId, grade);
                    break;

                case "getStudentsSortedByAverageGrade":
                    System.out.println("Sorting students by average grade");
                    Set<Student> sortedByAverage = faculty.getStudentsSortedByAverageGrade();
                    for (Student student : sortedByAverage) {
                        System.out.println(student);
                    }
                    break;

                case "getStudentsSortedByCoursesPassed":
                    System.out.println("Sorting students by courses passed");
                    Set<Student> sortedByCourses = faculty.getStudentsSortedByCoursesPassed();
                    for (Student student : sortedByCourses) {
                        System.out.println(student);
                    }
                    break;

                default:
                    break;
            }
        }

        scanner.close();
    }
}


package Lambda.AuditoryExercises;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.*;

class Student {
    public final String id;
    public String name;
    public int grade;
    public int attendance;

    public Student(String id, String name, int grade, int attendance) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return name + " (" + id + "), grade=" + grade + ", attendance=" + attendance + "%";
    }
}

class Course {
    public String name;
    public Student[] students;
    public int capacity;
    public int size;

    public Course(String name, int capacity) {
        this.name = name;
        students = new Student[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public void addStudent(Student s)
    {
        if(size<students.length)
        {
            students[size++]=s;
        }
    }

    public void enroll(Supplier<Student> supplier)
    {
        addStudent(supplier.get());
    }

    public void forEach(Consumer<Student> action)
    {
        for (int i = 0; i < size; i++) {
            action.accept(students[i]);
        }
    }

    public void conditionalForEach(Predicate<Student> condition, Consumer<Student> mutator)
    {
        for (int i = 0; i < size; i++) {
            if(condition.test(students[i]))
            {
                mutator.accept(students[i]);
            }
        }
    }

    public int count(Predicate<Student> condition)
    {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if(condition.test(students[i]))
            {
                count++;
            }
        }
        return count;
    }

    public String [] map (Function<Student, String> mapper)
    {
        String [] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = mapper.apply(students[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course: ").append(name).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(i+1).append(". ").append(students[i]).append("\n");
        }
        return sb.toString();
    }
}

public class CourseDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Course course = new Course("NP", 100);

        int n=3;

        Supplier<Student> reader = () -> {
            //1 Stefan 6 88
            String index = sc.next();
            String name = sc.next();
            int grade = sc.nextInt();
            int attendance = sc.nextInt();
//            sc.next();
            return new Student(index, name, grade, attendance);
        };

        for (int i=0;i<n;i++){
            course.enroll(reader);
        }

        System.out.println(course);

        Consumer<Student> increaseGrade = student -> student.grade++;

        course.forEach(increaseGrade);

        System.out.println(course);


        Predicate<Student> highAttendance = s -> s.attendance>=80;
        Predicate<Student> veryHighAttendance = s -> s.attendance>=90;
        Predicate<Student> passGrade = s -> s.grade>5;
        Predicate<Student> bareMinimumGrade = s -> s.grade==6 || s.grade==7;
        Predicate<Student> freshman = s -> s.id.startsWith("25");
        Predicate<Student> highGrade = s -> s.grade>=9;

        System.out.println(course.count(highAttendance));
        System.out.println(course.count(veryHighAttendance));
        System.out.println(course.count(passGrade));
        System.out.println(course.count(bareMinimumGrade));

        course.conditionalForEach(
                bareMinimumGrade.and(veryHighAttendance),
                increaseGrade
        );

        System.out.println(course);

        Function<Student, String> function = student -> String.format("Freshman: %s High grade: %s.", freshman.test(student), highGrade.test(student));

        for (String s : course.map(function)) {
            System.out.println(s);
        }


    }
}

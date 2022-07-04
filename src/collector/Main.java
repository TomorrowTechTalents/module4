package collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Alice"));
        students.add(new Student("Bob"));
        students.add(new Student("Charlie"));
        students.add(new Student("David"));
        students.add(new Student("Emanuel"));

        Map<Object, Long> studentsCollected = students.stream().
                                                       collect(Collectors.
                                                               groupingBy(student -> student.getName().length(),
                                                                          Collectors.counting()));

        System.out.println(studentsCollected);
    }
}

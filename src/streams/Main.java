package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final double PASSING_GRADE = 5;
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("escolha uma opção:");
        System.out.println("1 - preencher dados de forma automática");
        System.out.println("2 - preencher dados de forma manual");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 1) {
            createDataAutomatically();
        } else if (option == 2) {
            getDataFromUserInput();
        } else {
            System.err.println("opção inválida");
            return;
        }

        System.out.println("\n==========\n");

        while (true) {
            System.out.println("escolha uma opção:");
            System.out.println("1 - somar todas as notas de cada gênero");
            System.out.println("2 - calcular a média das notas de cada gênero");
            System.out.println("3 - mostrar alunos/as de cada gênero que foram reprovados");
            System.out.println("4 - sair");

            option = scanner.nextInt();
            switch (option) {
                case 1:
                    showSumOfGradesByGenderAlternative();
                    break;
                case 2:
                    getAverageGradeByGender();
                    break;
                case 3:
                    showFailingStudentsByGender();
                    break;
                case 4:
                    System.out.println("até mais");
                    return;
                default:
                    System.out.println("opção inválida");
                    return;
            }

            System.out.println();
        }
    }

    private static void createDataAutomatically() {
        students.add(new Student("Alice", Student.Gender.FEMININO, 4.9));
        students.add(new Student("Bob", Student.Gender.MASCULINO, 5));
        students.add(new Student("Charlie", Student.Gender.MASCULINO, 8));
        students.add(new Student("David", Student.Gender.MASCULINO, 3));
        students.add(new Student("Emanuel", Student.Gender.MASCULINO, 4));
    }

    private static void getDataFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("nome do/a aluno/a (vazio para sair): ");
            String name = scanner.nextLine();

            if (name.isEmpty()) {
                return;
            }

            System.out.print("gênero do/a aluno/a (as opções são: " + Student.getValidGenders() + "): ");
            String genderString = scanner.nextLine().toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderString);

            System.out.print("nota do/a aluno/a: ");
            double grade = scanner.nextDouble();
            scanner.nextLine();

            students.add(new Student(name, gender, grade));
        }
    }

    private static void showSumOfGradesByGenderAlternative() {
        for (Student.Gender gender : Student.Gender.values()) {
            double sum = Main.students.stream().
                                       filter(student -> student.getGender().equals(gender)).
                                       map(Student::getGrade).
                                       reduce(Double::sum).
                                       orElse(.0);

            System.out.println("soma das notas de alunos/as do gênero " + gender + ": " + sum);
        }
    }

    private static void showSumOfGradesByGender(List<Student> students) {
        Arrays.stream(Student.Gender.values()).forEach(gender -> {
            double sum = students.stream().
                                  filter(student -> student.getGender().equals(gender)).
                                  mapToDouble(Student::getGrade).
                                  summaryStatistics().
                                  getSum();

            System.out.println("soma das notas de alunos/as do gênero " + gender + ": " + sum);
        });
    }

    private static void getAverageGradeByGender() {
            Arrays.stream(Student.Gender.values()).forEach(gender -> {
                double average = Main.students.stream().
                                               filter(student -> student.getGender().equals(gender)).
                                               mapToDouble(Student::getGrade).
                                               average().
                                               orElse(0);

                System.out.println("gênero " + gender.toString() + ": " + average);
        });
    }

    private static void showFailingStudentsByGender() {
        Map<Object, List<Student>> failingStudents = Main.students.stream().
                                                                   filter(student -> student.getGrade() < PASSING_GRADE).
                                                                   collect(Collectors.groupingBy(Student::getGender));

        System.out.println(failingStudents);
    }
}

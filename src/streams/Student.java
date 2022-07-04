package streams;

import java.util.Arrays;

public class Student {
    enum Gender {MASCULINO, FEMININO}

    private final String name;
    private final Gender gender;
    private final double grade;

    Student(String pName, Gender pGender, double pGrade) {
        this.name = pName;
        this.gender = pGender;
        this.grade = pGrade;
    }

    private String getName() {
        return this.name;
    }

    Gender getGender() {
        return this.gender;
    }

    double getGrade() {
        return this.grade;
    }

    static String getValidGenders() {
        return Arrays.toString(Gender.values());
    }

    @Override
    public String toString() {
        return "Aluno/a=[" + "nome: " + this.getName() + ", nota: " + this.getGrade() + "]";
    }
}

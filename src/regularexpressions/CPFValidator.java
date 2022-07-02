package regularexpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("digite o CPF a ser validado:");
        String cpf = scanner.nextLine();

        Pattern pattern = Pattern.compile("[0-9]{11}");

        Matcher matcher = pattern.matcher(cpf);

        boolean match = matcher.matches();

        System.out.println(match ? "CPF validado" : "não é possível validar o CPF");
    }
}

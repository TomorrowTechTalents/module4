package regularexpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPJValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("digite o CNPJ a ser validado:");
        String cnpj = scanner.nextLine();

        Pattern pattern = Pattern.compile("[0-9]{14}");

        Matcher matcher = pattern.matcher(cnpj);

        boolean match = matcher.matches();

        System.out.println(match ? "CNPJ validado" : "não é possível validar o CNPJ");
    }
}

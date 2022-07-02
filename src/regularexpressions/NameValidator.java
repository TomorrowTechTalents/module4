package regularexpressions;

import java.util.Scanner;
import java.util.regex.Pattern;

public class NameValidator {
    public static void main(String[] args) {
        System.out.println("digite o nome a ser validado:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        boolean match = Pattern.matches("([a-zA-Z]{2,99}\\s){1,99}[a-zA-Z]{2,99}", name);

        System.out.println(match ? "nome validado" : "não é possível validar o nome");
    }
}

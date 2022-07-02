package regularexpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAdressValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("digite o endereço de email a ser validado:");
        String emailAddress = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\S+@\\S+\\.\\S+");

        Matcher matcher = pattern.matcher(emailAddress);

        boolean match = matcher.matches();

        System.out.println(match ? "endereço de email validado" : "não é possível validar o endereço de email");
    }
}

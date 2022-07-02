package regularexpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneNumberValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("digite o número de telefone a ser validado:");

        String telephoneNumber = scanner.nextLine();

        Pattern pattern = Pattern.compile("(([1-9])|([1-9][0-9]))[0-9]{7}");

        Matcher matcher = pattern.matcher(telephoneNumber);

        boolean match = matcher.matches();

        System.out.println(match ? "número de telefone validado" : "não é possível validar o número de telefone");
    }
}

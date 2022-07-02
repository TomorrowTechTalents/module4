package regularexpressions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateExpressionValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("digite o padrão que será aceito para datas (eg dd/MM/yyyy):");
        String patternString = scanner.nextLine();

        Pattern patternRegex = Pattern.compile(patternString.replaceAll("[dMy]", "[0-9]"));

        System.out.println("digite uma data a ser validada:");
        String date = scanner.nextLine();

        Matcher matcher = patternRegex.matcher(date);

        boolean match = matcher.matches();

        System.out.println(match ? "data validada" : "não é possível validar a data");
    }
}

package org.erijl.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    private static final String DO_STRING = "do()";
    private static final String DONT_STRING = "don't()";

    public static void main(String[] args) {
        String input = getRawInput();


        BigInteger product = evaluteOnlyMulInstructions(input);
        BigInteger productPartTwo = evaluteMulDosAndDontsInstructions(input);

        System.out.printf("Product (Part One): %d, Product (Part Two): %d", product, productPartTwo);
    }

    public static BigInteger evaluteMulDosAndDontsInstructions(String input) {
        List<String> matches = new ArrayList<>();

        Matcher multiplicationMatcher = Pattern.compile("(mul\\(\\d+,\\d+\\))|do\\(\\)|don't\\(\\)").matcher(input);
        while(multiplicationMatcher.find()) {
            matches.add(multiplicationMatcher.group());
        }

        matches.forEach(System.out::println);
        boolean isMultiplicationEnabled = true;
        BigInteger product = BigInteger.ZERO;
        for (String match : matches) {
            if (match.equals(DO_STRING)) isMultiplicationEnabled = true;
            if (match.equals(DONT_STRING)) isMultiplicationEnabled = false;


            if(match.startsWith("mul") && isMultiplicationEnabled) {
                String[] numbers = match.replace("mul(", "").replace(")", "").split(",");
                product = product.add(
                        new BigInteger(numbers[0])
                                .multiply(
                                        new BigInteger(numbers[1]
                                        )
                                )
                );
            }
        }

        return product;
    }

    public static BigInteger evaluteOnlyMulInstructions(String input) {
        List<String> matches = new ArrayList<>();

        Matcher multiplicationMatcher = Pattern.compile("mul\\(\\d+,\\d+\\)").matcher(input);
        while(multiplicationMatcher.find()) {
            matches.add(multiplicationMatcher.group());
        }

        matches.forEach(System.out::println);

        BigInteger product = BigInteger.ZERO;
        for (String match : matches) {
            String[] numbers = match.replace("mul(", "").replace(")", "").split(",");
            product = product.add(
                    new BigInteger(numbers[0])
                            .multiply(
                                    new BigInteger(numbers[1]
                                    )
                            )
            );
        }

        return product;
    }

    private static String getRawInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(Day01.class.getClassLoader().getResource("input-day-03.txt").getPath()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

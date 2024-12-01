package org.erijl.aoc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {

    public static void main(String[] args) {
        String input = getRawInput();
        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        for (String line : input.split("\r\n")) {
            String[] numbers = line.replace("   ", ";").split(";");
            leftSide.add(Integer.parseInt(numbers[0]));
            rightSide.add(Integer.parseInt(numbers[1]));
        }
        rightSide = rightSide.stream().sorted().collect(Collectors.toList());
        leftSide = leftSide.stream().sorted().collect(Collectors.toList());

        assert rightSide.size() == leftSide.size();
        long totalDistance = 0;

        for (int i = 0; i < rightSide.size(); i++) {
            totalDistance += Math.abs(leftSide.get(i) - rightSide.get(i));
        }

        System.out.println(totalDistance);
    }

    private static String getRawInput() {
        try(BufferedReader br = new BufferedReader(new FileReader(Day01.class.getClassLoader().getResource("input-day-01.txt").getPath()))) {
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

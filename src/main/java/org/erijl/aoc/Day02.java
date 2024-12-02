package org.erijl.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) {
        String input = getRawInput();
        List<List<Integer>> reports = new ArrayList<>();
        for (String line : input.split("\r\n")) {
            String[] levels = line.split(" ");
            reports.add(Arrays.stream(levels).map(Integer::parseInt).collect(Collectors.toList()));
        }

        int safeReportCountPartOne = 0;
        for (List<Integer> report : reports) {
            boolean isReportSafe = isReportSafe(report);
            if (isReportSafe) safeReportCountPartOne++;
        }

        int safeReportCount = 0;
        for (List<Integer> report : reports) {
            boolean isReportSafe = isReportSafe(report);
            if (isReportSafe) {
                safeReportCount++;
            } else {
                boolean isAnySafe = false;
                List<Integer> baseReport = new ArrayList<>(report);

                for (int i = 0; i < report.size(); i++) {
                    report.remove(i);
                    boolean isPartialReportSafe = isReportSafe(report);
                    assert (baseReport.size() - 1) == report.size();

                    if (isPartialReportSafe) {
                        isAnySafe = true;
                        break;
                    }
                    report = new ArrayList<>(baseReport);
                }

                if (isAnySafe) safeReportCount++;
            }


        }

        System.out.printf("Safe reports (Part One): %d, Safe reports with problem dampener: %d", safeReportCountPartOne, safeReportCount);
    }

    private static boolean isReportSafe(List<Integer> report) {
        boolean increasing = false;
        boolean decreasing = false;
        boolean isSame = false;
        boolean toMuchDif = false;

        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) < report.get(i - 1)) decreasing = true;

            if (report.get(i) > report.get(i - 1)) increasing = true;

            if (Objects.equals(report.get(i), report.get(i - 1))) isSame = true;

            if (Math.abs((report.get(i) - report.get(i - 1))) > 3) toMuchDif = true;
        }

        return !(increasing && decreasing) && !isSame && !toMuchDif;
    }


    private static String getRawInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(Day01.class.getClassLoader().getResource("input-day-02.txt").getPath()))) {
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

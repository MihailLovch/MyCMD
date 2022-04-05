package ru.kpfu.itis.Lovchitskiy.cmd.commands;

import ru.kpfu.itis.Lovchitskiy.cmd.App;

import java.io.File;
import java.util.Arrays;

public class DirCommand implements Command {
    private final App app;
    private final String[] parameters = {"-n"};
    private final int MAX_AMOUNT_OF_INPUT = 2;

    public DirCommand(App app) {
        this.app = app;
    }

    @Override
    public void execute() {
        if (!verifyInput()) {
            throw new IllegalArgumentException("Unknown parameters");
        } else {
            File[] files = new File(app.getFilePath().toString()).listFiles();
            System.out.printf("%-8s %-15s %-20s %-25s\n", "Flags", "Space", "Data", "Name");
            if (files != null) {
                for (File sub : files) {
                    System.out.printf("%-8s %-15s %-20s %-25s\n", getFlags(sub), getLength(sub), convertToData(sub.lastModified()), sub.toPath().getFileName());
                }
            }
        }
    }

    private boolean verifyInput() {
        String[] check = app.getUserInput();
        if (check.length > MAX_AMOUNT_OF_INPUT) {
            return false;
        }
        if (check.length == MAX_AMOUNT_OF_INPUT) {
            return Arrays.asList(parameters).contains(check[1]);
        }
        return true;
    }

    private String getLength(File file) {
        double len = file.length();
        if (app.getUserInput().length == MAX_AMOUNT_OF_INPUT) {
            int count = 0;
            StringBuilder length = new StringBuilder();
            while (len >= 1024) {
                len /= 1024;
                count++;
            }
            length.append(Math.ceil(len));
            if (count == 0) length.append("B");
            if (count == 1) length.append("Kb");
            if (count == 2) length.append("Mb");
            if (count == 3) length.append("Gb");
            return length.toString();
        } else {
            return Math.ceil(len) + "B";
        }
    }

    private String getFlags(File file) {
        StringBuilder flags = new StringBuilder();
        if (file.canExecute()) flags.append("-x");
        if (file.canRead()) flags.append("-r");
        if (file.canWrite()) flags.append("-w");

        return flags.toString();
    }

    private String convertToData(long millis) {
        millis = millis / 1000;
        long minutes = millis / 60 % 60;
        long hours = millis / (60 * 60) % 24;
        long days = millis / (60 * 60 * 24);
        long month = days / 30 % 12;
        long years = 1970 + days / 365;
        days %= 30;
        return String.format("%02d", days) + "." + String.format("%02d", month) + "." + years + " " + hours + ":" + minutes;
    }
}

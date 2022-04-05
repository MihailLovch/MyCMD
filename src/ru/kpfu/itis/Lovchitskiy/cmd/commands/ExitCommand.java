package ru.kpfu.itis.Lovchitskiy.cmd.commands;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println(":(");
        System.exit(0);
    }
}

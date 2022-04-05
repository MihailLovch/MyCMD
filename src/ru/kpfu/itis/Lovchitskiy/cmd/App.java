package ru.kpfu.itis.Lovchitskiy.cmd;

import ru.kpfu.itis.Lovchitskiy.cmd.commands.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class App extends AbstractApp {
    public static void main(String[] args) {
        App app = new App();
    }

    private Path filePath;
    private Scanner sc;
    private String[] commandNames;
    private Command[] commands;
    private String[] userInput;

    @Override
    void init() {
        filePath = Paths.get("D:\\delete");
        sc = new Scanner(System.in);
        commandNames = new String[]{"cd", "copy", "del", "dir", "exit"};
        commands = new Command[]{
                new ChangeDirCommand(this),
                new CopyCommand(this),
                new DeleteCommand(this),
                new DirCommand(this),
                new ExitCommand()
        };
    }

    @Override
    void start() {
        while (true) {
            userInput = refactorUserInput();
            int commandIndex = 0;
            boolean found = false;
            for (String commandName : commandNames) {
                if (Objects.equals(userInput[0], commandName)) {
                    try {
                        commands[commandIndex].execute();
                    } catch (IOException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    found = true;
                    break;
                }
                commandIndex++;
            }
            if (!found) {
                System.out.println("Command not found");
            }
        }
    }
    // Метод, который позволяет вводить пути с пробелами в ""
    public String[] refactorUserInput() {
        String input = sc.nextLine();
        String[] userIn = input.split(" ");
        ArrayList<String> formatted = new ArrayList<>();
        if (input.matches(".*\".*\".*")) {
            for (int i = 0; i < userIn.length-1; i++) {
                if (!userIn[i].matches(".*\".*")) {
                    formatted.add(userIn[i]);
                }else{
                    String path = userIn[i];
                    i++;
                    while(!userIn[i].matches(".*\".*")){
                        path +=" " + userIn[i];
                        i++;
                    }
                    path +=" " + userIn[i];
                    path = path.substring(1,path.length()-1);
                    formatted.add(path);
                }
            }
            userIn =  formatted.toArray(new String[0]);
        }
        return userIn;
    }

    public String[] getUserInput() {
        return userInput;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

}

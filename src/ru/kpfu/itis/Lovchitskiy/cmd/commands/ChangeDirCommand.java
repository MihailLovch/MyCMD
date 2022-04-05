package ru.kpfu.itis.Lovchitskiy.cmd.commands;

import ru.kpfu.itis.Lovchitskiy.cmd.App;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDirCommand implements Command {
    private final App app;
    private final int AMOUNT_OF_INPUT = 2;
    private final int USER_PATH_INDEX = 1;
    public ChangeDirCommand(App app) {
        this.app = app;
    }

    @Override
    public void execute() throws IOException{
        if (app.getUserInput().length > AMOUNT_OF_INPUT){
            throw new IllegalArgumentException("Unknown parameters");
        }
        if (app.getUserInput().length == 1) {
            System.out.println(app.getFilePath());
        } else {
            String parameter = app.getUserInput()[USER_PATH_INDEX];
            if (!Paths.get(parameter).isAbsolute()) {
                Path newPath = app.getFilePath().resolve(parameter).normalize();
                if (new File(newPath.toString()).exists()) {
                    app.setFilePath(newPath);
                    System.out.println(app.getFilePath().toString());
                    return;
                }
            } else if (new File(parameter).exists()) {
                app.setFilePath(Paths.get(parameter + "\\").normalize());
                System.out.println(app.getFilePath().toString());
                return;
            }
            throw new IOException("The system cannot find the specified path.");
        }
    }
}

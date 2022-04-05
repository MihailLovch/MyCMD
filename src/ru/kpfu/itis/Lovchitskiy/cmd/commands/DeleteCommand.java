package ru.kpfu.itis.Lovchitskiy.cmd.commands;

import ru.kpfu.itis.Lovchitskiy.cmd.App;

import java.io.File;
import java.nio.file.Path;

public class DeleteCommand implements Command {
    private App app;
    private final int AMOUNT_OF_INPUT = 2;
    public DeleteCommand(App app) {
        this.app = app;
    }

    @Override
    public void execute() {
        File file;
        if (app.getUserInput().length > AMOUNT_OF_INPUT){
            throw new IllegalArgumentException("Unknown parameters");
        }
        if (app.getUserInput().length == AMOUNT_OF_INPUT){
            file = new File(app.getFilePath().resolve(app.getUserInput()[1]).normalize().toString());
        }else{
            file = new File(app.getFilePath().toString());
            app.setFilePath(app.getFilePath().resolve("..").normalize());
        }
        deleteDirectory(file);
    }

    private void deleteDirectory(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File d : files) {
                deleteDirectory(d);
            }
        }
        file.delete();
    }
}

package ru.kpfu.itis.Lovchitskiy.cmd.commands;

import ru.kpfu.itis.Lovchitskiy.cmd.App;

import java.io.*;

public class CopyCommand implements Command {
    private final App app;

    public CopyCommand(App app) {
        this.app = app;
    }

    @Override
    public void execute() throws IOException {
        if (app.getUserInput().length == 1 ||app.getUserInput().length > 3){
            throw new IllegalArgumentException("Not Enough arguments");
        }
        if (app.getUserInput().length == 3) {
            copyFile(app.getUserInput()[1], app.getUserInput()[2]);
        } else {
            copyFile(app.getFilePath().toString(), app.getUserInput()[1]);
        }
    }

    private void copyFile(String from, String to) throws IOException {
        File src = new File(from);
        File stock = new File(to + "\\" + src.getName());
        if (!src.isFile() || !new File(to).exists()){
            throw new IOException("Can't find this directory or file");
        }
        if (stock.createNewFile()){
            try(FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(stock)){
                int copy;
                while ((copy = in.read()) != -1){
                    out.write(copy);
                }
            }catch (IOException ex){
                throw new IOException(ex.getMessage());
            }
        }

    }
}

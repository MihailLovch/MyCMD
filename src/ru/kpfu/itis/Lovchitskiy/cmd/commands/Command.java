package ru.kpfu.itis.Lovchitskiy.cmd.commands;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}

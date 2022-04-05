package ru.kpfu.itis.Lovchitskiy.cmd;

public abstract class AbstractApp {
    abstract void init();

    abstract void start();

    public AbstractApp() {
        init();
        start();
    }
}

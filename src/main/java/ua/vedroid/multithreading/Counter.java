package ua.vedroid.multithreading;

public class Counter {
    private int num = 0;

    public int incrementNumber() {
        return num++;
    }

    public int getNum() {
        return num;
    }
}

package ua.vedroid.multithreading;

public class AppStarter {
    public static void main(String[] args) {
        Counter counter = new Counter();
        new ThreadA(counter).start();
        new Thread(new ThreadB(counter)).start();
    }
}

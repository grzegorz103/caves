package main;

public class Main {

    private final static String IN_FILE = "cav.in";
    private final static String OUT_FILE = "cav.out";

    public static void main(String[] args) {
        new Cave(IN_FILE, OUT_FILE).process();
    }
}

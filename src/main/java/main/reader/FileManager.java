package main.reader;

import main.rooms.BaseRoom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private final static String IN_FILE = "cav.in";
    private final static String OUT_FILE = "cav.out";

    public static List<String> importFile(){
        try {
            return Files.readAllLines(Paths.get(IN_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void export(List<BaseRoom> bestPath) {
        String collect = bestPath.stream()
                .map(BaseRoom::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        try {
            Files.write(Paths.get(OUT_FILE), collect.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

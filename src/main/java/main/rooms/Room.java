package main.rooms;

import java.util.HashSet;
import java.util.Set;

public class Room extends BaseRoom {

    public Room(int number, boolean outer) {
        super(number, new HashSet<>(), outer);
    }
}

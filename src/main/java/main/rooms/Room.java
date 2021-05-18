package main.rooms;

import java.util.HashSet;

public class Room extends BaseRoom {

    public Room(int number, boolean outer) {
        super(number, new HashSet<>(), outer);
    }

}

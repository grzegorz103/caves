package main.cave;

import main.reader.FileManager;
import main.rooms.BaseRoom;
import main.rooms.Link;
import main.validators.DefaultInputValidator;

import java.util.*;
import java.util.stream.Collectors;

public class Cave extends AbstractCave {
    private int invocationCount = 0;

    private BaseRoom startRoom;

    public Cave() {
        super(new DefaultInputValidator());
    }

    public void process() {
        super.initialize();

        this.startRoom = super.getRoomByNumber(1);

        traverse(startRoom);

        this.calculateBestPath()
                .ifPresent(FileManager::export);
    }

    private Optional<List<BaseRoom>> calculateBestPath() {
        return super.paths
                .stream()
                .min((path1, path2) -> Math.toIntExact(path1.stream().filter(BaseRoom::isFinalDifficulty).count() - path2.stream().filter(BaseRoom::isFinalDifficulty).count()));
    }


    List<BaseRoom> preparePath(LinkedList<BaseRoom> visitedRooms) {
        return visitedRooms.stream()
                .map(BaseRoom::clone)
                .collect(Collectors.toList());
    }

    private void traverse(BaseRoom baseRoom) {
        ++invocationCount;
        baseRoom.setVisited(true);
        super.visitedRooms.add(baseRoom);

        if (super.visitedRooms.size() == totalRoomCount && this.lastRoomHasStartingRoom()) {
            List<BaseRoom> path = this.preparePath(super.visitedRooms);
            paths.add(path);
        }

        if (invocationCount == 1 || super.visitedRooms.getLast().getNumber() != 1) {
            Set<Link> linkByRoom = super.getLinkByRoom(baseRoom);
            for (Link link : linkByRoom) {
                BaseRoom neighbour = link.getLinkedRoom(baseRoom);

                if (!neighbour.getVisited()) {
                    neighbour.setFinalDifficulty(link.isHard());
                    traverse(neighbour);
                }
            }
            baseRoom.setVisited(false);
            super.visitedRooms.removeLast();
        }
    }

    private boolean lastRoomHasStartingRoom(){
        return super.visitedRooms
                .getLast()
                .getLinks()
                .stream()
                .anyMatch(e -> e.contains(startRoom));
    }

}

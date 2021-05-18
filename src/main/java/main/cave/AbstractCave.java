package main.cave;

import main.reader.FileManager;
import main.rooms.BaseRoom;
import main.rooms.Link;
import main.rooms.Room;
import main.validators.InputValidator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractCave {

    protected int totalRoomCount;
    protected int innerRoomCount;

    private InputValidator inputValidator;

    private Set<BaseRoom> rooms = new HashSet<>();

    private Set<Link> links = new HashSet<>();

    protected LinkedList<BaseRoom> visitedRooms = new LinkedList<>();

    protected List<List<BaseRoom>> paths = new ArrayList<>();

    public AbstractCave(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    protected void initialize() {
        List<String> list = FileManager.importFile();
        this.processFirstLine(
                list.stream()
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException("Incorrect first line in input file")
                        )
        );

        list.stream()
                .skip(1)
                .forEach(this::processLine);

    }

    private void processFirstLine(String line) {
        String[] lineSplit = line.split(" ");
        if (lineSplit.length == 2) {
            int n = Integer.parseInt(lineSplit[0]);
            int k = Integer.parseInt(lineSplit[1]);
            if (this.inputValidator.validateRoomCounts(n, k)) {
                this.totalRoomCount = n;
                this.innerRoomCount = k;
                IntStream.rangeClosed(1, n)
                        .forEach(num -> {
                            if (num <= k) {
                                this.rooms.add(new Room(num, true));
                            } else {
                                this.rooms.add(new Room(num, false));
                            }
                        });
            }

        }
    }

    private void processLine(String line) {
        String[] lineSplit = line.split(" ");
        if (lineSplit.length == 3) {
            int a = Integer.parseInt(lineSplit[0]);
            int b = Integer.parseInt(lineSplit[1]);
            int hardness = Integer.parseInt(lineSplit[2]);

            if (this.inputValidator.validateRoomHardness(hardness)) {
                Optional<BaseRoom> aRoomOpt = Optional.ofNullable(this.getRoomByNumber(a));
                Optional<BaseRoom> bRoomOpt = Optional.ofNullable(this.getRoomByNumber(b));

                if (aRoomOpt.isPresent() && bRoomOpt.isPresent()) {
                    BaseRoom aRoom = aRoomOpt.get();
                    BaseRoom bRoom = bRoomOpt.get();
                    Link link = new Link(aRoom, bRoom, hardness == 1);
                    this.links.add(link);
                    aRoom.addLink(link);
                    bRoom.addLink(link);
                }
            }
        }
    }

    public BaseRoom getRoomByNumber(int number) {
        return this.rooms
                .stream()
                .filter(room -> room.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Set<Link> getLinkByRoom(BaseRoom baseRoom) {
        return links.stream()
                .filter(room -> room.contains(baseRoom))
                .collect(Collectors.toSet());
    }

}

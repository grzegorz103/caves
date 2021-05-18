package main.rooms;

public class Link {

    private BaseRoom first;
    private BaseRoom second;
    private boolean hard;
    private boolean traversed;

    public Link(BaseRoom first, BaseRoom second, boolean hard) {
        this.first = first;
        this.second = second;
        this.hard = hard;
    }

    public BaseRoom getLinkedRoom(BaseRoom baseRoom) {
        return this.first == baseRoom
                ? second
                : first;
    }

    public BaseRoom getFirst() {
        return first;
    }

    public void setFirst(BaseRoom first) {
        this.first = first;
    }

    public BaseRoom getSecond() {
        return second;
    }

    public void setSecond(BaseRoom second) {
        this.second = second;
    }

    public boolean isHard() {
        return hard;
    }

    public void setHard(boolean hard) {
        this.hard = hard;
    }

    public boolean contains(BaseRoom baseRoom) {
        return this.first == baseRoom || this.second == baseRoom;
    }

    public boolean isTraversed() {
        return traversed;
    }

    public void setTraversed(boolean traversed) {
        this.traversed = traversed;
    }
}

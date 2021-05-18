package main.rooms;

import java.util.Set;

public abstract class BaseRoom implements Cloneable {

    private int number;

    private Set<Link> links;

    private boolean outer;
    private boolean visited;

    private boolean finalDifficulty;

    public BaseRoom(int number, Set<Link> links, boolean outer) {
        this.number = number;
        this.links = links;
        this.outer = outer;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public boolean isOuter() {
        return outer;
    }

    public void setOuter(boolean outer) {
        this.outer = outer;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited() {
        return visited;
    }

    public boolean isFinalDifficulty() {
        return finalDifficulty;
    }

    public void setFinalDifficulty(boolean finalDifficulty) {
        this.finalDifficulty = finalDifficulty;
    }

    public BaseRoom clone() {
        try {
            return (BaseRoom) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

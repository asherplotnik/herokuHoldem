package app.core.util;

public class OpenGame {

    public OpenGame(String name, Integer owner) {
        this.name = name;
        this.owner = owner;
    }

    private String name;
    private Integer owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}

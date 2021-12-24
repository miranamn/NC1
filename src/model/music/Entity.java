package model.music;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    private String name;
    private UUID id;

    public Entity(){}

    public Entity(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

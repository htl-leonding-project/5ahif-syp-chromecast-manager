package at.htl.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Room extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int roomNumber;

    private String roomName;


    public Room(int roomNumber, String roomName) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

    public Room() { }

    public Long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }
}

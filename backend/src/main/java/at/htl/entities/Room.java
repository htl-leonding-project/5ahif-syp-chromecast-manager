package at.htl.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "ROOM")
public class Room extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<InstallAt> installs;

    @Column(name = "ROOMNUMBER")
    private int roomNumber;

    @Column(name = "ROOMNAME")
    private String roomName;


    public Room(int roomNumber, String roomName) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

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

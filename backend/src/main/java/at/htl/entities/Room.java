package at.htl.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select r from Room r")
})
public class Room  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "ROOM_INSTALLS")
    private List<InstallAt> installs;

    @Column(name = "ROOMNUMBER")
    private int roomNumber;
    @Column(name = "ROOMNAME")
    private String roomName;


    public Room(int roomNumber, String roomName) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

    public Room() {

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

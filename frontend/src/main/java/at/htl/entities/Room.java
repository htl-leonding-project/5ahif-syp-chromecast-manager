package at.htl.entities;

import javax.persistence.*;

@Entity (name = "ROOM")
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select r from Room r")
})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int roomNumber;

    @Column
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

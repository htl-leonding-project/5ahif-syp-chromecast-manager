package at.htl.entities;

import javax.persistence.*;

@Entity
@Table(name = "HTL_ROOM")
@SequenceGenerator(
        name = "roomSequence",
        sequenceName = "room_id_seq",
        allocationSize = 1, //increment
        initialValue = 1000) //start
public class Room  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomSequence")
    @Column(name = "R_ID")
    private Long id;


    @Column(name = "R_NUMBER")
    private int roomNumber;

    @Column(name = "R_NAME")
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

    public void setId(Long id) {
        this.id = id;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}

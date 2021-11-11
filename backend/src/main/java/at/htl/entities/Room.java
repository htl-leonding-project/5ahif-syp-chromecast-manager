package at.htl.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HTL_ROOM")
@NamedQueries({
        @NamedQuery(name = "Room.findAll", query = "select r from Room r")
})
public class Room  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_ID")
    private Long id;

    @OneToMany
    @JoinColumn(name = "R_INSTALLS")
    private List<InstallAt> installs;

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

    public List<InstallAt> getInstalls() {
        return installs;
    }

    public void setInstalls(List<InstallAt> installs) {
        this.installs = installs;
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
                ", installs=" + installs +
                ", roomNumber=" + roomNumber +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}

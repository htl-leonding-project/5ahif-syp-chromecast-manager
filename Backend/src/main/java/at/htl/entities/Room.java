package at.htl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    private int roomNumber;
    private String roomName;
}

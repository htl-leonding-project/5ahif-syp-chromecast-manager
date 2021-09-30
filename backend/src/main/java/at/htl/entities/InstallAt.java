package at.htl.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class InstallAt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate installDate;
    private LocalDate removeDate;
    private String description;
    private User user;
    private Room room;
    private Device device;


    public InstallAt(LocalDate installDate, LocalDate removeDate, String description, User user, Room room, Device device) {
        this.installDate = installDate;
        this.removeDate = removeDate;
        this.description = description;
        this.user = user;
        this.room = room;
        this.device = device;
    }


    public Long getId() {
        return id;
    }

    public LocalDate getInstallDate() {
        return installDate;
    }

    public LocalDate getRemoveDate() {
        return removeDate;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public Device getDevice() {
        return device;
    }
}

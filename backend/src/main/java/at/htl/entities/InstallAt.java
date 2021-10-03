package at.htl.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name="INSTALL_AT")
public class InstallAt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "INSTALL_DATE")
    private LocalDate installDate;

    @Column(name = "REMOVE_DATE")
    private LocalDate removeDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Device device;


    public InstallAt(LocalDate installDate, LocalDate removeDate, String description, User user, Room room, Device device) {
        this.installDate = installDate;
        this.removeDate = removeDate;
        this.description = description;
        this.user = user;
        this.room = room;
        this.device = device;
    }

    public InstallAt() {

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

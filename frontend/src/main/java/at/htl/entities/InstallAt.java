package at.htl.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity (name = "INSTALL_AT")
@NamedQueries({
        @NamedQuery(name = "InstallAt.findAll", query = "select i from INSTALL_AT i")
})
public class InstallAt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate installDate;

    @Column
    private LocalDate removeDate;

    @Column
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

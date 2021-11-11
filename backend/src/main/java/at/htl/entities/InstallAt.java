package at.htl.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HTL_INSTALLAT")
public class InstallAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_ID")
    private Long id;

    @Column(name ="I_INSTALLDATE")
    private LocalDate installDate;
    @Column(name ="I_REMOVEDATE")
    private LocalDate removeDate;
    @Column(name ="I_DESCRIPTION")
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Room room;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Device device;




    public InstallAt(LocalDate installDate, LocalDate removeDate, String description, User user, Room room, Device device) {
        this.installDate = installDate;
        this.removeDate = removeDate;
        this.description = description;
        this.room = room;
        this.device = device;
        this.user = user;
    }

    public InstallAt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInstallDate() {
        return installDate;
    }

    public void setInstallDate(LocalDate installDate) {
        this.installDate = installDate;
    }

    public LocalDate getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(LocalDate removeDate) {
        this.removeDate = removeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public at.htl.entities.User getUser() {
        return user;
    }

    public void setUser(at.htl.entities.User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "InstallAt{" +
                "id=" + id +
                ", installDate=" + installDate +
                ", removeDate=" + removeDate +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", room=" + room +
                ", device=" + device +
                '}';
    }
}

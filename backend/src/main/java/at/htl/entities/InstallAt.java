package at.htl.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "INSTALLAT")
public class InstallAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="INSTALLDATE")
    private LocalDate installDate;
    @Column(name ="REMOVEDATE")
    private LocalDate removeDate;
    @Column(name ="DESCRIPTION")
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

    public LocalDate getInstallDate() {
        return installDate;
    }

    public LocalDate getRemoveDate() {
        return removeDate;
    }

    public String getDescription() {
        return description;
    }
}

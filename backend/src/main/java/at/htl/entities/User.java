package at.htl.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HTL_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_ID")
    private Long id;

    @Column(name = "U_NAME")
    private String name;

    @Column(name = "U_PASSWORD_HASH")
    private String passwordHash;

    @OneToMany
    @JoinColumn(name = "U_INSTALLS_AT")
    private List<InstallAt> installs;

    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}

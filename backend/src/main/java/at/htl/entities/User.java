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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<InstallAt> getInstalls() {
        return installs;
    }

    public void setInstalls(List<InstallAt> installs) {
        this.installs = installs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", installs=" + installs +
                '}';
    }
}

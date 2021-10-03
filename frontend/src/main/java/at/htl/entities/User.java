package at.htl.entities;

import javax.persistence.*;

@Entity (name = "USER")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from Room u")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

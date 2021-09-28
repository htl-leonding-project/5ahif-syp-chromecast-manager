package at.htl.entities;

import javax.persistence.Id;

public class User {
    @Id
    private long id;
    private String name;
    private String password;
}

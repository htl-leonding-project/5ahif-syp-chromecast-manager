package at.htl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Class {
    @Id
    private long id;
    private String className;
}

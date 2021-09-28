package at.htl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Device {
    @Id
    private int serialNumber;
    private String name;
    private String brand;
    Date installed;
}

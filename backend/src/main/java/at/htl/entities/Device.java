package at.htl.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int serialNumber;
    private String name;
    private String brand;


    public Device(int serialNumber, String name, String brand) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.brand = brand;
    }


    public Long getId() {
        return id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }
}

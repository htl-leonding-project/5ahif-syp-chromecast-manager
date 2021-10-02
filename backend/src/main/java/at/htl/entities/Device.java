package at.htl.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DEVICE")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SERIAL_NUMBER")
    private int serialNumber;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BRAND")
    private String brand;


    public Device(int serialNumber, String name, String brand) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.brand = brand;
    }

    public Device() {

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

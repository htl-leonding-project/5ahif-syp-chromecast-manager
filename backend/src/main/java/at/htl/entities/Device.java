package at.htl.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HTL_DEVICE")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "D_SERIALNUMBER")
    private int serialNumber;

    @Column(name = "D_NAME")
    private String name;


    @OneToMany
    @JoinColumn(name = "D_INSTALLS")
    private List<InstallAt> installs;

    @Column(name = "D_BRAND")
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

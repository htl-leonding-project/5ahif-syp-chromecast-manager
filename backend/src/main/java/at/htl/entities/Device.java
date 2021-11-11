package at.htl.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HTL_DEVICE")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "D_ID")
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

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InstallAt> getInstalls() {
        return installs;
    }

    public void setInstalls(List<InstallAt> installs) {
        this.installs = installs;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", installs=" + installs +
                ", brand='" + brand + '\'' +
                '}';
    }
}

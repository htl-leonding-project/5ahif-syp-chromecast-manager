package at.htl.entities;

import javax.persistence.*;

@Entity (name="DEVICE")
@NamedQueries({
        @NamedQuery(name = "Device.findAll", query = "select d from Device d")
})
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int serialNumber;

    @Column
    private String name;

    @Column
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

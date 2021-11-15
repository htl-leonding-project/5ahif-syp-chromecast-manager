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

    @Column(name = "D_NAME")
    private String name;

    @Column(name = "D_BRAND")
    private String brand;


    public Device(String name, String brand) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
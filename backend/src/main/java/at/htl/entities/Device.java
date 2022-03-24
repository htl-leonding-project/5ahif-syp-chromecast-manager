package at.htl.entities;

import javax.persistence.*;

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

    @Column(name = "D_EAN")
    private String ean;

    @Column(name = "D_CATEGORY")
    private String category;

    public Device(String name, String brand,String ean,String category) {
        this.name = name;
        this.brand = brand;
        this.ean = ean;
        this.category = category;
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

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
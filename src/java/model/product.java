package model;
/*old*/

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class product implements Serializable{

    private int id;
    private String name;
    private String description;
    private String image;
    private float price;

    //id and table generator

    @Id
    @TableGenerator(name = "g", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "g")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}

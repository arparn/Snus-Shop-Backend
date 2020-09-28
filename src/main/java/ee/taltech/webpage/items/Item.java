package ee.taltech.webpage.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private Double price;
    private String description;
    private Double rating;
    private Integer strength;

    public Item(String name) {
        this.name = name;
    }
}

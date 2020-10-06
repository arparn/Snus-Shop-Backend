package ee.taltech.webpage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemCount {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Item item;
    private Integer quantity;

    public ItemCount(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

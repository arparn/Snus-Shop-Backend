package ee.taltech.webpage.a_theory.question6.chocolate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cake {

    private Long id;
    private String size;
    private String sweetness;
    private List<String> ingredients;
    private List<String> toppings;
}

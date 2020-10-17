package ee.taltech.webpage.a_theory.question6.vineyard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wine {
    private Long id;
    private String name;
    private String region;
    private String grape;
    private Integer year;
    private String description;
}

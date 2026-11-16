package vulnlab.lombok;

import lombok.Getter;
import lombok.Setter;

/** DTO Lombok avec un identifiant numérique : getId() renvoie un Long. */
@Getter
@Setter
public class LombokIdDto {
    private Long id;
    private String label;
}

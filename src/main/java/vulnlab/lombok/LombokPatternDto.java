package vulnlab.lombok;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

/** DTO Lombok contraint : column ne contient que [a-zA-Z_] (avec @Valid sur la source). */
@Data
public class LombokPatternDto {
    @Pattern(regexp = "[a-zA-Z_]+")
    private String column;
}

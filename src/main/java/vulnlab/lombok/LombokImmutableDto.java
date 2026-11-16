package vulnlab.lombok;

import lombok.Builder;
import lombok.Value;

/** DTO immuable Lombok (@Value : champs private final, getters, pas de setter) ; à ne pas confondre avec @Value de Spring. */
@Value
@Builder
public class LombokImmutableDto {
    String name;
    int quantity;
}

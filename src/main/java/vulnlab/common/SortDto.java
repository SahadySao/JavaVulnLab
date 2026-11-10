package vulnlab.common;

import jakarta.validation.constraints.Pattern;

/** DTO de tri : le champ `column` est contraint par Bean Validation, `label` ne l'est pas. */
public class SortDto {
    @Pattern(regexp = "[a-zA-Z_]+")
    private String column;

    @Pattern(regexp = "[a-zA-Z' ]+")   // trop large : accepte l'apostrophe
    private String label;

    private String free;

    public String getColumn() { return column; }
    public void setColumn(String column) { this.column = column; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getFree() { return free; }
    public void setFree(String free) { this.free = free; }
}

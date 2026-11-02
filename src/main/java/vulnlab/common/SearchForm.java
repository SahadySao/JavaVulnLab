package vulnlab.common;

/** Objet lié par Spring à partir de la requête (@ModelAttribute ou @RequestBody). */
public class SearchForm {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

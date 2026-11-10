package vulnlab.common;

/** Objet de formulaire Spring MVC (@ModelAttribute). */
public class SearchForm {
    private String keyword;
    private Integer page;

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }
}

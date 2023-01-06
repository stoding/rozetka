package model;

public class SearchField {
    private String suggestionCategoryName;
    private String suggestingCategoryLink;

    public SearchField(String suggestionCategoryName, String suggestingCategoryLink) {
        this.suggestionCategoryName = suggestionCategoryName;
        this.suggestingCategoryLink = suggestingCategoryLink;
    }

    public String getSuggestionCategoryName() {
        return suggestionCategoryName;
    }

    public String getSuggestingCategoryLink() {
        return suggestingCategoryLink;
    }
}

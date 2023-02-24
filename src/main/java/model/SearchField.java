package model;

public class SearchField {
    private final String suggestionCategoryName;
    private final String suggestingCategoryLink;

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

package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Inject;
import model.SearchField;
import pages.CategoryPage;
import pages.Homepage;

import java.util.ArrayList;
import java.util.List;

public class SearchFormSteps extends BaseSteps {

    @Inject
    private Homepage homepage;
    @Inject
    private CategoryPage categoryPage;

    public void enterSearchQuery(String input) {
        homepage.searchFieldClear();
        homepage.enterSearchQuery(input);
        homepage.waitForSearchSuggestionListAppear();
    }

    public ElementsCollection getSuggestionList() {
        return homepage.getSearchSuggestionList();
    }

    public boolean searchSuggestionListContainsString(ElementsCollection elementsList, String expectedString) {

        for (SelenideElement element : elementsList
        ) {
            if (!element.getText().toLowerCase().contains(expectedString.toLowerCase()))
                return false;
        }
        return true;
    }

    public List<SearchField> getSuggestedCategoryNameAndLink() {
        List<SearchField> categoryParameters = new ArrayList<>();
        for (SelenideElement element : homepage.getSearchSuggestionList()
        ) {
            categoryParameters.add(new SearchField(element.getText(), element.getAttribute("href")));
        }
        return categoryParameters;
    }

    public String getCategoryPageBreadCrumbs() {
        return categoryPage.getBreadCrumbs();
    }

    public boolean isCategoryDisplayedCorrectly(List<SearchField> categoriesFromSuggestionList) {
        for (SearchField parameter : categoriesFromSuggestionList) {
            navigateTo(parameter.getSuggestingCategoryLink());
            if (!parameter.getSuggestionCategoryName().equals(getCategoryPageBreadCrumbs()))
                return false;
        }
        return true;
    }
}

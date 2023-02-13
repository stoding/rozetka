package steps;

import model.SearchField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchFormSteps extends BaseSteps {
    public SearchFormSteps(WebDriver driver) {
        super(driver);
    }

    public void enterSearchQuery(String input) {
        homepage.searchFieldClear();
        homepage.enterSearchQuery(input);
    }

    public List<WebElement> getSuggestionList() {
        return homepage.getSearchSuggestionList();
    }

    public boolean searchSuggestionListContainsString(List<WebElement> elementsList, String expectedString) {
        boolean isExpectedStringContainActualString = false;
        for (WebElement element : elementsList
        ) {
            isExpectedStringContainActualString = element.getText().contains(expectedString);
        }
        return isExpectedStringContainActualString;
    }

    public List<SearchField> getSuggestedCategoryNameAndLink() {
        List<SearchField> categoryParameters = new ArrayList<>();
        for (WebElement element : homepage.getSearchSuggestionList()
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

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
        //   waitForSearchResultUpdate();
    }

    public List<WebElement> getSuggestionList() {
        return homepage.getSearchSuggestionList();
    }

    public boolean searchSuggestionListContainsString(List<WebElement> elementsList, String expectedString) {
        for (WebElement element : elementsList
        ) {
            if (element.getText().contains(expectedString))
                return true;
        }
        return false;
    }

    public void waitForSearchResultUpdate() {
        homepage.waitForElementStaleness("//div[@class='search-suggest ng-star-inserted']//a");
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
}

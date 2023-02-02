import model.SearchField;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.SearchFormSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SearchFormTest extends BaseTest {

    private static final String SEARCH_STRING = "Компьютер";

    @DataProvider(name = "keyboardWrongLayoutData")
    public Object[][] keyboardWrongLayoutData() {
        return new Object[][]{
                {"En.y", "Утюг"},
                {"Gsktcjc", "Пылесос"}
        };
    }

    @Test(dataProvider = "keyboardWrongLayoutData")
    public void wrongKeyboardLayoutTest(String searchQuery, String expectedStringDisplayed) {
        SearchFormSteps searchFormSteps = new SearchFormSteps(driver);
        searchFormSteps.navigateToHomePage();
        searchFormSteps.enterSearchQuery(searchQuery);
        assertThat(searchFormSteps.searchSuggestionListContainsString(searchFormSteps.getSuggestionList(),expectedStringDisplayed)).isTrue();
    }
    @Test
    public void categoryPageNavigateFromSearchSuggestionList() {
        SearchFormSteps searchFormSteps = new SearchFormSteps(driver);
        searchFormSteps.enterSearchQuery(SEARCH_STRING);
        List<SearchField> categoriesFromSuggestionList = searchFormSteps.getSuggestedCategoryNameAndLink();
        assertThat(searchFormSteps.isCategoryDisplayedCorrectly(categoriesFromSuggestionList)).isTrue();

    }
}

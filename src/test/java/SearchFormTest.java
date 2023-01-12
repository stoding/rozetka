import model.SearchField;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.SearchFormSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SearchFormTest extends BaseTest {

    public static final String SEARCH_STRING = "Компьютер";

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

        //Щось в мене не вийшло написати більш елегантний код, щоб дочекатись оновлення списку запропонованих товарів після
        //зміни строки пошуку. Буду вдячний за підказку
        try {
            assertThat(searchFormSteps.searchSuggestionListContainsString(searchFormSteps.getSuggestionList(),expectedStringDisplayed)).isTrue();
        } catch (AssertionError e) {
            searchFormSteps.waitForSearchResultUpdate();
            assertThat(searchFormSteps.searchSuggestionListContainsString(searchFormSteps.getSuggestionList(),expectedStringDisplayed)).isTrue();
        }
    }
    @Test
    public void categoryPageNavigateFromSearchSuggestionList() {
        SearchFormSteps searchFormSteps = new SearchFormSteps(driver);
        searchFormSteps.enterSearchQuery(SEARCH_STRING);
        List<SearchField> categoriesFromSuggestionList = searchFormSteps.getSuggestedCategoryNameAndLink();
        for (SearchField parameter : categoriesFromSuggestionList)
                 {
                     searchFormSteps.navigateTo(parameter.getSuggestingCategoryLink());
                     assertThat(parameter.getSuggestionCategoryName()).isEqualTo(searchFormSteps.getCategoryPageBreadCrumbs());
        }
    }
}

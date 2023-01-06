import model.SearchField;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Homepage;
import steps.SearchFormSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SearchFormTest extends BaseTest {

    public static final String SEARCH_STRING = "Компьютер";

    @DataProvider(name = "layoutCheck")
    public Object[][] layoutData() {
        return new Object[][]{
                {"En.y", "Утюг"},
                {"Gsktcjc", "Пылесос"}
        };
    }

    @Test(dataProvider = "layoutCheck")
    public void wrongKeyboardLayoutTest(String searchQuery, String expectedStringDisplayed) {
        SearchFormSteps searchFormSteps = new SearchFormSteps(driver);
        searchFormSteps.navigateToHomePage();
        searchFormSteps.searchFieldClear();
        searchFormSteps.inputSearchQuery(searchQuery);

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
        searchFormSteps.inputSearchQuery(SEARCH_STRING);
        List<WebElement> categorySuggestionElementsList = searchFormSteps.getSuggestionList();
        List<SearchField> categoriesFromSuggestionList = searchFormSteps.getSuggestedCategoryNameAndLink(categorySuggestionElementsList);
        for (SearchField parameter : categoriesFromSuggestionList)
                 {
                     searchFormSteps.navigateTo(parameter.getSuggestingCategoryLink());
                     assertThat(parameter.getSuggestionCategoryName()).isEqualTo(searchFormSteps.getCategoryPageBreadCrumbs());
        }
    }
}

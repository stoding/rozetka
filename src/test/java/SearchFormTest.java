import com.google.inject.Inject;
import model.SearchField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.SearchFormSteps;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class SearchFormTest extends BaseTest {

    @Inject
    SearchFormSteps searchFormSteps;

    private static final String SEARCH_STRING = "Компьютер";

    @BeforeMethod
    public void goToHomePage() {
        open(ROZETKA_HOMEPAGE_URL);
    }

    @DataProvider(name = "keyboardWrongLayoutData")
    public Object[][] keyboardWrongLayoutData() {
        return new Object[][]{
                {"En.y", "Утюг"},
                {"Gsktcjc", "Пылесос"}
        };
    }

    @Test(dataProvider = "keyboardWrongLayoutData")
    public void wrongKeyboardLayoutTest(String searchQuery, String expectedStringDisplayed) {
        searchFormSteps.navigateToHomePage();
        searchFormSteps.enterSearchQuery(searchQuery);
        assertThat(searchFormSteps.searchSuggestionListContainsString(searchFormSteps.getSuggestionList(), expectedStringDisplayed)).isTrue();
    }

    @Test
    public void categoryPageNavigateFromSearchSuggestionList() {
        searchFormSteps.enterSearchQuery(SEARCH_STRING);
        List<SearchField> categoriesFromSuggestionList = searchFormSteps.getSuggestedCategoryNameAndLink();
        assertThat(searchFormSteps.isCategoryDisplayedCorrectly(categoriesFromSuggestionList)).isTrue();

    }
}

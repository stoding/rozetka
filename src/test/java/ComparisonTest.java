import model.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ComparisonSteps;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class ComparisonTest extends BaseTest {

    @Inject
    private ComparisonSteps comparisonSteps;

    private static final int ITEM_NUMBER_IN_CATEGORY_LIST = 1;
    private static final String SEARCH_QUERY_CATEGORY = "iPhone";
    private static final String[] SEARCH_QUERY_FIRST_ITEM = {"318463324", "Мобильный телефон Apple iPhone 13 128GB Pink (MLPH3HU/A)"};
    private static final String[] SEARCH_QUERY_SECOND_ITEM = {"260937036", "Мобильный телефон Apple iPhone 11 128GB Yellow (MHDL3)"};


    @BeforeMethod
    public void goToHomePage() {
        open(ROZETKA_HOMEPAGE_URL);
    }

    @AfterMethod
    public void clearComparisonList() {
        clearBrowserCookies();
    }

    @Test
    public void addOneItemToComparisonList() {
        comparisonSteps.searchForCategory(SEARCH_QUERY_CATEGORY);
        String comparedItemTitle = comparisonSteps.getItemTitleFromCategoryPage(ITEM_NUMBER_IN_CATEGORY_LIST);
        comparisonSteps.addElementToComparisonList(ITEM_NUMBER_IN_CATEGORY_LIST);
        assertThat(comparisonSteps.isComparisonIconUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).contains("1");
        assertThat(comparisonSteps.isItemAddToComparisonListMessageDisplayed()).isTrue();
        assertThat(comparisonSteps.openComparisonWindow()).isTrue();
        assertThat(comparisonSteps.getComparisonCategory()).contains("Мобильные телефоны");
        assertThat(comparisonSteps.getNumberOfItemsToCompare()).isEqualTo("1");
        comparisonSteps.navigateToComparisonPage();
        assertThat(comparisonSteps.getComparisonPageURL()).contains("comparison");
        assertThat(comparisonSteps.isNotEnoughItemsToCompareMessageDisplayed()).isTrue();
        assertThat(comparisonSteps.getItemOnComparisonPage()).contains(comparedItemTitle);
    }

    @Test
    public void comparedItemsParametersCheck() {
        comparisonSteps.searchForItem(SEARCH_QUERY_FIRST_ITEM[0]);
        assertThat(comparisonSteps.getItemPageTitle()).contains(SEARCH_QUERY_FIRST_ITEM[1]);
        List<Item> itemList = new ArrayList<>();
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.isComparisonIconUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("1");
        assertThat(comparisonSteps.isItemAddToComparisonListMessageDisplayed()).isTrue();
        comparisonSteps.searchForItem(SEARCH_QUERY_SECOND_ITEM[0]);
        assertThat(comparisonSteps.getItemPageTitle()).contains(SEARCH_QUERY_SECOND_ITEM[1]);
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.isComparisonIconUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("2");
        assertThat(comparisonSteps.isItemAddToComparisonListMessageDisplayed()).isTrue();
        assertThat(comparisonSteps.openComparisonWindow()).isTrue();
        comparisonSteps.navigateToComparisonPage();
        comparisonSteps.openAllItemSpecsOnComparisonPage();
        List<Item> itemListOnComparisonPage = comparisonSteps.getItemsListFromComparisonPage();
        assertThat(comparisonSteps.isComparedListsEqual(itemList, itemListOnComparisonPage)).isTrue();

    }
}


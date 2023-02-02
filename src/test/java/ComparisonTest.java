import model.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ComparisonSteps;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ComparisonTest extends BaseTest {

    private static final int ITEM_NUMBER_IN_CATEGORY_LIST = 1;
    private static final String SEARCH_QUERY_CATEGORY = "iPhone";
    private static final String[] SEARCH_QUERY_FIRST_ITEM = {"318463324","Мобильный телефон Apple iPhone 13 128GB Pink (MLPH3HU/A)"};
    private static final String[] SEARCH_QUERY_SECOND_ITEM = {"260937036","Мобильный телефон Apple iPhone 11 128GB Yellow (MHDL3)"};


    @BeforeMethod
    public void goToHomePage() {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.navigateToHomePage();
    }

    @AfterMethod
    public void clearComparisonList() {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.clearComparisonList();
    }

    @Test
    public void addOneItemToComparisonList() {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.searchForCategory(SEARCH_QUERY_CATEGORY);
        String comparedItemTitle = comparisonSteps.getItemTitleFromCategoryPage(ITEM_NUMBER_IN_CATEGORY_LIST);
        comparisonSteps.addElementToComparisonList(ITEM_NUMBER_IN_CATEGORY_LIST);
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).contains("1");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        assertThat(comparisonSteps.openComparisonWindow()).isTrue();
        assertThat(comparisonSteps.getComparisonCategory()).contains("Мобильные телефоны");
        assertThat(comparisonSteps.getNumberOfItemsToCompare()).isEqualTo("1");
        comparisonSteps.navigateToComparisonPage();
        assertThat(comparisonSteps.getComparisonPageURL()).contains("comparison");
        assertThat(comparisonSteps.notEnoughItemsToCompareMessageIsDisplayed()).isTrue();
        assertThat(comparisonSteps.getItemOnComparisonPage()).contains(comparedItemTitle);
    }

    @Test
    public void comparedItemsParametersCheck() {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.SearchForItem(SEARCH_QUERY_FIRST_ITEM[0]);
        assertThat(comparisonSteps.getItemPageTitle()).contains(SEARCH_QUERY_FIRST_ITEM[1]);
        List<Item> itemList = new ArrayList<>();
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("1");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        comparisonSteps.SearchForItem(SEARCH_QUERY_SECOND_ITEM[0]);
        assertThat(comparisonSteps.getItemPageTitle()).contains(SEARCH_QUERY_SECOND_ITEM[1]);
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("2");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        assertThat(comparisonSteps.openComparisonWindow()).isTrue();
        comparisonSteps.navigateToComparisonPage();
        comparisonSteps.openAllItemSpecsOnComparisonPage();
        List<Item> itemListOnComparisonPage = comparisonSteps.getItemSpecsFromComparisonPage();
        assertThat(comparisonSteps.isComparedListsEqual(itemList, itemListOnComparisonPage)).isTrue();

    }
}


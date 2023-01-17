import model.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ComparisonSteps;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ComparisonTest extends BaseTest {

    public static final int ITEM_NUMBER_IN_CATEGORY_LIST = 1;
    public static final String SEARCH_QUERY_CATEGORY = "iPhone";
    public static final String SEARCH_QUERY_FISRT_ITEM_ID = "318463324";
    public static final String SEARCH_QUERY_SECOND_ITEM_ID = "260937036";

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
        comparisonSteps.inputSearchCategory(SEARCH_QUERY_CATEGORY);
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
        assertThat(comparisonSteps.itemToCompareDisplayed()).contains(comparedItemTitle);
    }

    @Test
    public void comparedItemsParametersCheck() {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.inputSearchItem(SEARCH_QUERY_FISRT_ITEM_ID);
        assertThat(comparisonSteps.getItemPageTitle()).contains("Мобильный телефон Apple iPhone 13 128GB Pink (MLPH3HU/A)");
        List<Item> itemList = new ArrayList<>();
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("1");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        comparisonSteps.inputSearchItem(SEARCH_QUERY_SECOND_ITEM_ID);
        assertThat(comparisonSteps.getItemPageTitle()).contains("Мобильный телефон Apple iPhone 11 128GB Yellow (MHDL3)");
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("2");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        assertThat(comparisonSteps.openComparisonWindow()).isTrue();
        comparisonSteps.navigateToComparisonPage();
        comparisonSteps.openAllItemSpecsOnComparisonPage();
        List<Item> itemListOnComparisonPate = comparisonSteps.getItemSpecsFromComparisonPage();
        int itemsListSize = itemList.size();
        assertThat(itemsListSize).isEqualTo(itemListOnComparisonPate.size());
        for (int i = 0; i < itemsListSize; i++) {
            assertThat(itemList.get(i).equals(itemListOnComparisonPate.get(itemsListSize - 1 - i))).isTrue();
        }
    }
}

import model.Item;
import org.testng.annotations.Test;
import steps.ComparisonSteps;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ComparisonTest extends BaseTest{

    public static final int ITEM_NUMBER_IN_CATEGORY_LIST = 1;

    @Test
    public void addOneItemToComparisonList() {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.inputSearchItem("iPhone");
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
    public void comparedItemsParametersCheck() throws InterruptedException {
        ComparisonSteps comparisonSteps = new ComparisonSteps(driver);
        comparisonSteps.inputSearchItem("318463324");
        assertThat(comparisonSteps.getItemPageTitle()).contains("Мобильный телефон Apple iPhone 13 128GB Pink (MLPH3HU/A)");
        List<Item> itemList = new ArrayList<>();
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("1");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        comparisonSteps.inputSearchItem("260937036");
        assertThat(comparisonSteps.getItemPageTitle()).contains("Мобильный телефон Apple iPhone 11 128GB Yellow (MHDL3)");
        itemList.add(comparisonSteps.getItemSpecsFromItemPage());
        comparisonSteps.addElementToComparisonList();
        assertThat(comparisonSteps.comparisonIconIsUpdated()).isTrue();
        assertThat(comparisonSteps.getNumberOfItemsOfComparisonIcon()).isEqualTo("2");
        assertThat(comparisonSteps.itemAddToComparisonListMessageIsDisplayed()).isTrue();
        comparisonSteps.openComparisonWindow();
        comparisonSteps.navigateToComparisonPage();
        comparisonSteps.openAllItemSpecsOnComparisonPage();
        List<Item> itemListOnComparisonPate = comparisonSteps.getItemSpecsFromComparisonPage();
        int itemsListSize = itemList.size();
        assertThat(itemsListSize).isEqualTo(itemListOnComparisonPate.size());
        for (int i=0;i<itemsListSize; i++) {
            assertThat(itemList.get(i).equals(itemListOnComparisonPate.get(itemsListSize-1-i))).isTrue();
        }
    }
}

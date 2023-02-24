package steps;

import com.google.inject.Inject;
import model.Item;
import pages.CategoryPage;
import pages.ComparisonPage;
import pages.Homepage;
import pages.ItemPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparisonSteps extends BaseSteps {

    private static final String ITEM_PRICE_ON_COMPARISON_PAGE = "(//div[contains(@class,'product__price' ) and contains(@class,'ng-star-inserted')]/span)[%s]";

    @Inject
    private Homepage homepage;
    @Inject
    private ItemPage itemPage;
    @Inject
    private CategoryPage categoryPage;
    @Inject
    private ComparisonPage comparisonPage;

    public void searchForItem(String queryString) {
        homepage.enterSearchQuery(queryString);
        homepage.clickSearchButton();
        itemPage.waitForItemPageLoad();
    }

    public String getItemTitleFromCategoryPage(int elementNumberInCategoryList) {
        return categoryPage.getItemTitle(elementNumberInCategoryList);
    }

    public void addElementToComparisonList(int elementNumberInCategoryList) {
        categoryPage.addElementToComparison(elementNumberInCategoryList);
    }

    public void addElementToComparisonList() {
        categoryPage.addElementToComparison();
    }

    public boolean isComparisonIconUpdated() {
        return categoryPage.comparisonIconIsUpdated();
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return categoryPage.getNumberOfItemsOfComparisonIcon();
    }

    public boolean isItemAddToComparisonListMessageDisplayed() {
        return categoryPage.itemAddToComparisonListMessageIsDisplayed();
    }

    public boolean openComparisonWindow() {
        categoryPage.comparisonHeaderButtonClick();
        return categoryPage.isComparisonWindowDisplayed();
    }

    public String getComparisonCategory() {
        return categoryPage.getComparisonCategory();
    }

    public String getNumberOfItemsToCompare() {
        return categoryPage.getNumberOfItemsToCompare();
    }

    public void navigateToComparisonPage() {
        categoryPage.navigateToComparisonPage();
    }

    public String getComparisonPageURL() {
        return comparisonPage.getPageURL();
    }

    public boolean isNotEnoughItemsToCompareMessageDisplayed() {
        return comparisonPage.isNotEnoughItemsToCompareMessageDisplayed();
    }

    public String getItemOnComparisonPage() {
        return comparisonPage.getItemTitleOnComparisonPage();
    }

    public String getItemPageTitle() {
        return itemPage.getPageTitle();
    }

    public Item getItemSpecsFromItemPage() {
        itemPage.specificationButtonClick();
        return new Item(
                itemPage.getItemTitleWithOpenedSpecs(),
                itemPage.getItemPriceFromItemPage(),
                itemPage.getItemScreenResolution(),
                itemPage.getItemNumberOfSupportedSimCards()
        );
    }

    public void openAllItemSpecsOnComparisonPage() {
        comparisonPage.openAllItemSpecsOnComparisonPage();
    }

    public List<Item> getItemsListFromComparisonPage() {
        List<Item> comparedItems = new ArrayList<>();
        int getItemsQuantityOnPage = comparisonPage.getComparedItemsQuantity();
        for (int i = 1; i < getItemsQuantityOnPage + 1; i++) {
            comparedItems.add(new Item(
                    comparisonPage.getItemTitle(i),
                    comparisonPage.getItemPrice(String.format(ITEM_PRICE_ON_COMPARISON_PAGE, i)),
                    comparisonPage.getItemScreenResolution(i),
                    comparisonPage.getItemNumberOfSupportedSimCards(i)));
        }
        return comparedItems;
    }

    public void searchForCategory(String searchQueryCategory) {
        homepage.searchFieldClear();
        homepage.enterSearchQuery(searchQueryCategory);
        homepage.clickSearchButton();
        categoryPage.waitForCategoryPageLoad();
    }

    public boolean isComparedListsEqual(List<Item> itemList, List<Item> itemListOnComparisonPage) {
        int itemsListSize = itemList.size();
        Collections.reverse(itemListOnComparisonPage);
        if (itemList.size() == itemListOnComparisonPage.size()) {
            for (int i = 0; i < itemsListSize; i++) {
                if (!itemList.get(i).equals(itemListOnComparisonPage.get(i)))
                    return false;
            }
            return true;
        } else return false;
    }
}
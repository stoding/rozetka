package steps;

import model.Item;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ComparisonSteps extends BaseSteps {
    public ComparisonSteps(WebDriver driver) {
        super(driver);
    }

    public void SearchForItem(String queryString) {
        homepage.enterSearchQuery(queryString);
        homepage.clickSearchButton();
        itemPage.waitForItemPageLoad(queryString);
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

    public boolean comparisonIconIsUpdated() {
        return categoryPage.comparisonIconIsUpdated();
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return categoryPage.getNumberOfItemsOfComparisonIcon();
    }

    public boolean itemAddToComparisonListMessageIsDisplayed() {
        return categoryPage.itemAddToComparisonListMessageIsDisplayed();
    }

    public boolean openComparisonWindow() {
        categoryPage.comparisonIconClick();
        return categoryPage.isDisplayed("//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']");
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

    public boolean notEnoughItemsToCompareMessageIsDisplayed() {
        return comparisonPage.isDisplayed("//*[contains(text(),'Недостаточно товаров')]");
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
                itemPage.getItemPrice("//div[(contains(@class,'product-carriage__price'))]"),
                itemPage.getItemScreenResolution(),
                itemPage.getItemNumberOfSupportedSimCards()
        );
    }

    public void openAllItemSpecsOnComparisonPage() {
        comparisonPage.openAllItemSpecsOnComparisonPage();
    }

    public List<Item> getItemSpecsFromComparisonPage() {
        List<Item> comparedItems = new ArrayList<>();
        Integer getItemsQuantityOnPage = comparisonPage.getComparedItemsQuantity();
        for (int i = 1; i < getItemsQuantityOnPage + 1; i++) {
            comparedItems.add(new Item(comparisonPage.getItemTitle(i),
                    comparisonPage.getItemPrice(String.format("(//div[contains(@class,'product__price')]/span)[%s]",i)),
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

    public void clearComparisonList() {
        // Знайшов простіший спосіб видаляти зміст списку порівняння, та в іншому тесті зі змістом кошику покупця
        comparisonPage.clearCookies();
        }

    public boolean isComparedListsEqual(List<Item> itemList, List<Item> itemListOnComparisonPage) {
        int itemsListSize = itemList.size();
        if (itemList.size()==itemListOnComparisonPage.size()) {
            for (int i = 0; i < itemsListSize; i++) {
                if (!itemList.get(i).equals(itemListOnComparisonPage.get(itemsListSize - 1 - i)))
                    return false;
            }
        }
        else return false;

        return true;
    }
}

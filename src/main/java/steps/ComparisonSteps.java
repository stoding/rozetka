package steps;

import model.Item;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ComparisonSteps extends BaseSteps{
    public ComparisonSteps(WebDriver driver) {
        super(driver);
    }

    public void inputSearchItem(String queryString) {
        homepage.inputSearchQuery(queryString);
        homepage.clickSearchButton();
    }
    public String getItemTitleFromCategoryPage(Integer elementNumberInCategoryList) {
        return categoryPage.getItemTitleFromCategoryPage(elementNumberInCategoryList);
    }

    public void addElementToComparisonList(Integer elementNumberInCategoryList) {
        categoryPage.addElementToComparison(elementNumberInCategoryList);
    }
    public void addElementToComparisonList() {
        categoryPage.addElementToComparison();
    }

    public boolean comparisonIconIsUpdated() {
        return categoryPage.isDisplayed("//button[@class='compare-button compare-button_state_active']");
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return categoryPage.getNumberOfItemsOfComparisonIcon();
    }

    public boolean itemAddToComparisonListMessageIsDisplayed() {
        return categoryPage.isDisplayed("//a[contains(text(),'сравнение')]");
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

    public String itemToCompareDisplayed() {
        return comparisonPage.itemToCompareDisplayed();

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
        for (int i = 1; i < getItemsQuantityOnPage+1; i++) {
            comparedItems.add(new Item(comparisonPage.getItemTitle(i),
                    comparisonPage.getItemPrice("(//div[@class='product__prices']/div)["+i+"]"),
                    comparisonPage.getItemScreenResolution(i),
                    comparisonPage.getItemNumberOfSupportedSimCards(i)));
        }
        return comparedItems;
    }

}

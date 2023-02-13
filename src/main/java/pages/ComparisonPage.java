package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComparisonPage extends BasePage {

    private static final String ITEM_LINK = "//li[@class='products-grid__cell ng-star-inserted']//a";
    private static final String OPEN_ALL_SPECS_BUTTON = "//button[(contains(@class,'comparison-settings__toggle'))]";
    private static final String ITEM = "//a[@class='product__heading']";
    private static final String ITEM_LINK_IN_COMPARISON_PAGE = ITEM;
    private static final String ITEM_SCREEN_RESOLUTION = "//h3[contains(text(),'Разрешение дисплея')]/..//span";
    private static final String ITEM_NUMBER_OF_SUPPORTED_SIM_CARDS = "//h3[contains(text(),'Количество SIM'";
    private static final String NOT_ENOUGH_ITEMS_TO_COMPARE_MESSAGE = "//*[contains(text(),'Недостаточно товаров')]";

    public ComparisonPage(WebDriver driver) {
        super(driver);
    }


    public String getItemTitleOnComparisonPage() {
        return driver.findElement(By.xpath(ITEM_LINK)).getText();
    }

    public void openAllItemSpecsOnComparisonPage() {
        driver.findElement(By.xpath(OPEN_ALL_SPECS_BUTTON)).click();
    }

    public Integer getComparedItemsQuantity() {
        return driver.findElements(By.xpath(ITEM_LINK_IN_COMPARISON_PAGE)).size();
    }

    public String getItemTitle(Integer itemNumberOnPage) {
        return driver.findElement(By.xpath(String.format("(" + ITEM + ")[%s]", itemNumberOnPage))).getText();
    }

    public String getItemScreenResolution(Integer itemNumberOnPage) {
        return driver.findElement(By.xpath(String.format("(" + ITEM_SCREEN_RESOLUTION + ")[%s]", itemNumberOnPage))).getText();
    }

    public Integer getItemNumberOfSupportedSimCards(Integer itemNumberOnPage) {
        return Integer.parseInt(driver.findElement(By.xpath(String.format("(" + ITEM_NUMBER_OF_SUPPORTED_SIM_CARDS + ")]/..//span)[%s]", itemNumberOnPage))).getText());
    }

    public boolean isNotEnoughItemsToCompareMessageDisplayed() {
        return isDisplayed(NOT_ENOUGH_ITEMS_TO_COMPARE_MESSAGE);
    }
}

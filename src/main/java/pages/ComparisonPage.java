package pages;

import static com.codeborne.selenide.Selenide.*;

public class ComparisonPage extends BasePage {

    private static final String ITEM_LINK = "//li[@class='products-grid__cell ng-star-inserted']//a";
    private static final String OPEN_ALL_SPECS_BUTTON = "//button[(contains(@class,'comparison-settings__toggle'))]";
    private static final String ITEM = "//a[@class='product__heading']";
    private static final String ITEM_LINK_IN_COMPARISON_PAGE = ITEM;
    private static final String ITEM_SCREEN_RESOLUTION = "//h3[contains(text(),'Разрешение дисплея')]/..//span";
    private static final String ITEM_NUMBER_OF_SUPPORTED_SIM_CARDS = "//h3[contains(text(),'Количество SIM'";
    private static final String NOT_ENOUGH_ITEMS_TO_COMPARE_MESSAGE = "//*[contains(text(),'Недостаточно товаров')]";

    public String getItemTitleOnComparisonPage() {
        return $x(ITEM_LINK).getText();
    }

    public void openAllItemSpecsOnComparisonPage() {
        $x(OPEN_ALL_SPECS_BUTTON).click();
    }

    public int getComparedItemsQuantity() {
        return $$x(ITEM_LINK_IN_COMPARISON_PAGE).size();
    }

    public String getItemTitle(int itemNumberOnPage) {
        return $x(String.format("(" + ITEM + ")[%s]", itemNumberOnPage)).getText();
    }

    public String getItemScreenResolution(int itemNumberOnPage) {
        return $x(String.format("(" + ITEM_SCREEN_RESOLUTION + ")[%s]", itemNumberOnPage)).getText();
    }

    public int getItemNumberOfSupportedSimCards(int itemNumberOnPage) {
        return Integer.parseInt($x(String.format("(" + ITEM_NUMBER_OF_SUPPORTED_SIM_CARDS + ")]/..//span)[%s]", itemNumberOnPage)).getText());
    }

    public boolean isNotEnoughItemsToCompareMessageDisplayed() {
        return $x(NOT_ENOUGH_ITEMS_TO_COMPARE_MESSAGE).isDisplayed();
    }
}

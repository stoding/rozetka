package pages;

import com.codeborne.selenide.*;
import model.Item;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CategoryPage extends BasePage {

    private static final String BREADCRUMBS = "//li[@class='breadcrumbs__item breadcrumbs__item--text ng-star-inserted']/span";
    private static final String COMPARISON_BUTTON_OVER_ELEMENT_IMAGE = "//button[contains(@class,'compare-button')]";
    private static final String COMPARISON_BUTTON_OVER_IMAGE_OF_CHOSEN_ELEMENT = "(//button[contains(@class,'compare-button')])[%s]";
    private static final String COMPARISON_ICON_OVER_ELEMENT_IMAGE = COMPARISON_BUTTON_OVER_ELEMENT_IMAGE + "//*[local-name()='use']";
    private static final String COMPARISON_HEADER_BUTTON_QUANTITY_OF_ELEMENTS_TO_COMPARE = "//span[@class='badge badge--gray ng-star-inserted']";
    private static final String COMPARISON_HEADER_BUTTON = "//button[@class='header__button ng-star-inserted' and @aria-label='Списки сравнения']";
    private static final String COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY = "//div[contains(@class,'modal__holder')]//a";
    private static final String COMPARISON_WINDOW_QUANTITY_OF_ITEMS_TO_COMPARE = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a/span";
    private static final String COMPARISON_PAGE_H1 = "//h1[@class='comparison__heading']";
    private static final String CATEGORY_PAGE_ITEM_TITLE = "(//a[@class='goods-tile__heading ng-star-inserted'])[%s]";
    private static final String CATEGORY_PAGE_IPHONE_CATEGORY_LINK = "//span[contains(text(),'Apple iPhone')]";
    private static final String ITEM_WITH_DISCOUNT_LINK = "(//div[contains(@class,'price--red')]/../../a[contains(@class,'goods-tile__heading')])[%s]";
    private static final String HOUSEHOLD_ELECTRONICS_CATEGORY_WASHING_MACHINES_LINK = "//li[@class='tile-cats__item ng-star-inserted']/a[contains(@href,'washing_machines')]";
    private static final String SHOPPING_CART_HEADER_ELEMENTS_IN_CART_QUANTITY = "//button[@class='header__button ng-star-inserted header__button--active']//span";
    private static final String SHOPPING_CART_HEADER_BUTTON = "//*[name()='use' and @*='#icon-header-basket']/../..";
    private static final String HOUSEHOLD_CATEGORY_BLANKETS_LINK = "//ul[@class='tile-cats__list ng-star-inserted']//a[contains(@href,'blankets')]";
    private static final String SHOPPING_CART_BUTTON_OVER_SELECTED_ITEM = ".//*[contains(@class,'goods-tile__buy-button')]//*[name()='svg']";
    private static final String SHOPPING_CART_FILLED_ICON = ".//*[name()='use' and @*='#icon-basket-filled']";
    private static final String ITEM_WITH_DISCOUNTED_PRICE = "//div[@class='goods-tile__price price--red ng-star-inserted']/../..";
    private static final String ITEM = "//div[@class='goods-tile__inner']";
    private static final String ITEM_TITLE = ".//a[@class='goods-tile__heading ng-star-inserted']";
    private static final String ITEM_PRICE = ".//span[@class='goods-tile__price-value']";
    private static final String SORT_BY_PRICE_OPTION = "//select/option[@value='1: cheap']";
    private static final String PAGINATION_PAGE = "//ul[@class='pagination__list']//a[contains(text(),'%s')]";
    private static final String SORT_BY_NOVELTY_OPTION = "//select/option[@value='3: novelty']";
    private static final String ITEM_WITH_NOVELTY_TAG = "//span[contains(@class,'promo-label_type_novelty')]";
    private static final String HOME_TEXTILE_CATEGORY_LINK = "//div[@class='tile-cats']//a[contains(@href,'home_textile')]";
    private static final String COMPARISON_ICON_ACTIVE = "//button[@class='compare-button compare-button_state_active']";
    private static final String ITEM_ADDED_TO_COMPARISON_MESSAGE = "//a[contains(text(),'Сравнение')]";
    private static final String ITEM_ADDED_TO_CART_MESSAGE = "//span[contains(text(),'Товар добавлен в корзину')]";
    private static final String ITEM_ADDED_TO_CART_ERROR_MESSAGE = "//span[contains(text(),'Ошибка')]";
    private static final String COMPARISON_WINDOW = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']";
    private static final String SHOPPING_CART_ICON_OVER_ITEM = "//button[contains(@class,'goods-tile__buy-button')]//*[name()='use']";
    private static final String ITEMS_GRID_VIEW_SWITCHER = "//div[contains(@class,'catalog-view__switch')]//*[name()='use']";
    private static final String PRELOADER_BAR = "//div[@class='preloader']";

    public String getBreadCrumbs() {
        return $x(BREADCRUMBS).getText();
    }

    public void addElementToComparison(int elementNumberInList) {
        $x(COMPARISON_BUTTON_OVER_ELEMENT_IMAGE).shouldBe(visible);
        $x(String.format(COMPARISON_BUTTON_OVER_IMAGE_OF_CHOSEN_ELEMENT, elementNumberInList)).click();
    }

    public void addElementToComparison() {
        $x(COMPARISON_BUTTON_OVER_ELEMENT_IMAGE).click();
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return $x(COMPARISON_HEADER_BUTTON_QUANTITY_OF_ELEMENTS_TO_COMPARE).getText();
    }

    public void comparisonHeaderButtonClick() {
        $x(COMPARISON_HEADER_BUTTON).click();
    }

    public String getComparisonCategory() {
        return $x(COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY).getText();
    }

    public String getNumberOfItemsToCompare() {
        return $x(COMPARISON_WINDOW_QUANTITY_OF_ITEMS_TO_COMPARE).getText();
    }

    public void navigateToComparisonPage() {
        $x(COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY).click();
        $x(COMPARISON_PAGE_H1).shouldBe(visible);
    }

    public String getItemTitle(Integer elementNumberInCategoryList) {
        return $x(String.format(CATEGORY_PAGE_ITEM_TITLE, elementNumberInCategoryList)).getAttribute("title");
    }

    public void clickIphoneCategoryLink() {
        $x(CATEGORY_PAGE_IPHONE_CATEGORY_LINK).click();
        $x(SHOPPING_CART_ICON_OVER_ITEM).shouldBe(visible);
    }

    public void openElementPageWithDiscountedPrice(int elementNumberInList) {
        $x(String.format(ITEM_WITH_DISCOUNT_LINK, elementNumberInList)).click();
    }

    public void openWashingMachinesCategory() {
        $x(HOUSEHOLD_ELECTRONICS_CATEGORY_WASHING_MACHINES_LINK).click();
        $x(SHOPPING_CART_ICON_OVER_ITEM).shouldBe(visible);
    }

    public String getElementTitle(SelenideElement element) {
        return element.getAttribute("title");
    }

    public void addElementToShoppingCart(SelenideElement element) {
        element.find(byXpath(SHOPPING_CART_BUTTON_OVER_SELECTED_ITEM)).shouldBe(interactable).click();
        if ($x(ITEM_ADDED_TO_CART_ERROR_MESSAGE).isDisplayed()) {
            element.find(byXpath(SHOPPING_CART_BUTTON_OVER_SELECTED_ITEM)).shouldBe(interactable).click();
        }
        $x(ITEM_ADDED_TO_CART_MESSAGE).shouldBe(visible);
    }

    public boolean isShoppingCartIconUpdated(Item item) {
        return item.getItemWebElement().find(byXpath(SHOPPING_CART_FILLED_ICON)).isDisplayed();
    }

    public int shoppingCartHeaderCounter() {
        return Integer.parseInt($x(SHOPPING_CART_HEADER_ELEMENTS_IN_CART_QUANTITY).getText().trim());
    }

    public void waitForMessageDisappear() {
        $x(ITEM_ADDED_TO_CART_MESSAGE).shouldBe(Condition.disappear);
    }

    public void clickShoppingCartHeaderIcon() {
        $x(SHOPPING_CART_HEADER_BUTTON).click();
    }

    public Item getItemSpecs(int elementNumberInList, boolean discountedItemsOnly) {
        SelenideElement element;
        String itemTitle;
        Float itemPrice;

        if (discountedItemsOnly) {
            element = $x(String.format("(" + ITEM_WITH_DISCOUNTED_PRICE + ")[%s]", elementNumberInList));
        } else {
            element = $x(String.format("(" + ITEM + ")[%s]", elementNumberInList));
        }

        itemTitle = getElementTitle(element.find(byXpath(ITEM_TITLE)));
        itemPrice = getItemPrice(element.find(byXpath(ITEM_PRICE)));
        return new Item(itemTitle, itemPrice, element);
    }

    public void openBlanketPage() {
        $x(HOUSEHOLD_CATEGORY_BLANKETS_LINK).click();
        $x(CATALOG_PAGE_H1).shouldBe(visible);
    }

    public void clickSortingByPriceAsc() {
        sortItemCategoryList(SORT_BY_PRICE_OPTION);
    }

    private void sortItemCategoryList(String sortMethod) {
        $x(ITEMS_GRID_VIEW_SWITCHER).shouldBe(visible);
        $x(sortMethod).click();
        $x(PRELOADER_BAR).shouldBe(hidden);
    }

    public ElementsCollection getItemsListOnPage() {
        return $$x(ITEM);
    }


    public void clickPageInPaginationList(Integer numberOfPageInPagination) {
        String firstElementIDBeforeListUpdate = $x(ITEM).getAttribute("data-goods-id");
        $x(String.format(PAGINATION_PAGE, numberOfPageInPagination)).click();
        $x(ITEM).shouldNotHave(attribute("data-goods-id", firstElementIDBeforeListUpdate));
    }

    public void clickSortingByNewArrivals() {
        sortItemCategoryList(SORT_BY_NOVELTY_OPTION);
    }


    public ElementsCollection getItemsNewArrivalsList() {
        return $$x(ITEM_WITH_NOVELTY_TAG);
    }

    public void waitForCategoryPageLoad() {
        $x(COMPARISON_ICON_OVER_ELEMENT_IMAGE).shouldBe(visible);
    }

    public void openHomeTextilePage() {
        $x(HOME_TEXTILE_CATEGORY_LINK).click();
        $x(CATALOG_PAGE_H1).shouldBe(visible);
    }

    public boolean comparisonIconIsUpdated() {
        return $x(COMPARISON_ICON_ACTIVE).shouldBe(visible).isDisplayed();
    }

    public boolean itemAddToComparisonListMessageIsDisplayed() {
        return $x(ITEM_ADDED_TO_COMPARISON_MESSAGE).isDisplayed();
    }

    public boolean isItemAddedToCartMessageDisplayed() {
        return $x(ITEM_ADDED_TO_CART_MESSAGE).isDisplayed();
    }

    public boolean isComparisonWindowDisplayed() {
        return $x(COMPARISON_WINDOW).isDisplayed();
    }
}

package pages;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CategoryPage extends BasePage {

    private static final String BREADCRUMBS = "//li[@class='breadcrumbs__item breadcrumbs__item--text ng-star-inserted']/span";
    private static final String COMPARISON_BUTTON_OVER_ELEMENT_IMAGE = "//button[@class='compare-button ng-star-inserted']";
    private static final String COMPARISON_SCALES_ICON_OVER_ELEMENT_IMAGE = COMPARISON_BUTTON_OVER_ELEMENT_IMAGE + "//*[local-name()='use']";
    private static final String COMPARISON_HEADER_BUTTON_QUANTITY_OF_ELEMENTS_TO_COMPARE = "//span[@class='badge badge--gray ng-star-inserted']";
    private static final String COMPARISON_HEADER_BUTTON = "//button[@class='header__button ng-star-inserted' and @aria-label='Списки сравнения']";
    private static final String COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a";
    private static final String COMPARISON_WINDOW_QUANTITY_OF_ITEMS_TO_COMPARE = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a/span";
    private static final String COMPARISON_PAGE_H1 = "//h1[@class='comparison__heading']";
    private static final String CATEGORY_PAGE_ITEM_TITLE = "(//a[@class='goods-tile__heading ng-star-inserted'])[%s]";
    private static final String CATEGORY_PAGE_IPHONE_CATEGORY_LINK = "//span[contains(text(),'Apple iPhone')]";
    private static final String ITEM_WITH_DISCOUNT_LINK = "(//div[contains(@class,'price--red')]/../../a[contains(@class,'goods-tile__heading')])[%s]";
    private static final String HOUSEHOLD_ELECTRONICS_CATEGORY_WASHING_MACHINES_LINK = "//li[@class='tile-cats__item ng-star-inserted']/a[contains(@href,'washing_machines')]";
    private static final String SHOPPING_CART_HEADER_ELEMENTS_IN_CART_QUANTITY = "//button[@class='header__button ng-star-inserted header__button--active']//span";
    private static final String SHOPPING_CART_HEADER_BUTTON = "//*[name()='use' and @*='#icon-header-basket']/../..";
    private static final String HOUSEHOLD_CATEGORY_BLANKETS_LINK = "//ul[@class='tile-cats__list ng-star-inserted']//a[contains(@href,'blankets')]";
    private static final String BUTTON_ADD_TO_SHOPPING_CART_ON_ITEM_FRAME = ".//*[contains(@class,'buy-button')]//*[name()='svg']";
    private static final String SHOPPING_CART_FILLED_ICON = "//*[name()='use' and @*='#icon-basket-filled']";
    private static final String ITEM_WITH_DISCOUNTED_PRICE = "//div[@class='goods-tile__price price--red ng-star-inserted']/../..";
    private static final String ITEM = "//div[@class='goods-tile__inner']";
    private static final String ITEM_TITLE = ".//a[@class='goods-tile__heading ng-star-inserted']";
    private static final String ITEM_PRICE = ".//span[@class='goods-tile__price-value']";
    private static final String SORTING_BUTTON_ICON = "//*[name()='use' and @*='#icon-view-less']";
    private static final String SORT_BY_PRICE_OPTION = "//select/option[@value='1: cheap']";
    private static final String PAGINATION_PAGE = "//ul[@class='pagination__list']//a[contains(text(),'%s')]";
    private static final String SORT_BY_NOVELTY_OPTION = "//select/option[@value='3: novelty']";
    private static final String ITEM_WITH_NOVELTY_TAG = "//span[contains(@class,'promo-label_type_novelty')]";
    private static final String H1_CATALOG_HEADER = "//h1[@class='catalog-heading ng-star-inserted']";
    private static final String HOME_TEXTILE_CATEGORY_LINK = "//div[@class='tile-cats']//a[contains(@href,'home_textile')]";
    private static final String COMPARISON_ICON_ACTIVE = "//button[@class='compare-button compare-button_state_active']";
    private static final String ITEM_ADDED_TO_COMPARISON_MESSAGE = "//a[contains(text(),'Сравнение')]";
    private static final String ITEM_ADDED_TO_CART_MESSAGE = "//span[contains(text(),'Товар добавлен в корзину')]";
    private static final String SHOPPING_CART_WINDOW = "//div[@class='modal__holder modal__holder_show_animation modal__holder--large']";
    private static final String SHOPPING_CART_PAGE_H1_HEADER = "//h1[@class='cart-page__heading']";
    private static final String COMPARISON_WINDOW = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']";

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBreadCrumbs() {
        return driver.findElement(By.xpath(BREADCRUMBS)).getText();
    }

    public void addElementToComparison(Integer elementNumberInList) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(COMPARISON_SCALES_ICON_OVER_ELEMENT_IMAGE))));
        driver.findElement(By.xpath(String.format("(" + COMPARISON_BUTTON_OVER_ELEMENT_IMAGE + ")[%s]", elementNumberInList))).click();
    }

    public void addElementToComparison() {
        driver.findElement(By.xpath(COMPARISON_BUTTON_OVER_ELEMENT_IMAGE)).click();
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return driver.findElement(By.xpath(COMPARISON_HEADER_BUTTON_QUANTITY_OF_ELEMENTS_TO_COMPARE)).getText();
    }

    public void comparisonIconClick() {
        driver.findElement(By.xpath(COMPARISON_HEADER_BUTTON)).click();
    }

    public String getComparisonCategory() {
        return driver.findElement(By.xpath(COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY)).getText();
    }

    public String getNumberOfItemsToCompare() {
        return driver.findElement(By.xpath(COMPARISON_WINDOW_QUANTITY_OF_ITEMS_TO_COMPARE)).getText();
    }

    public void navigateToComparisonPage() {
        driver.findElement(By.xpath(COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(COMPARISON_PAGE_H1))));
    }

    public String getItemTitle(Integer elementNumberInCategoryList) {
        return driver.findElement(By.xpath(String.format(CATEGORY_PAGE_ITEM_TITLE, elementNumberInCategoryList))).getAttribute("title");
    }

    public void clickIphoneCategoryLink() {
        driver.findElement(By.xpath(CATEGORY_PAGE_IPHONE_CATEGORY_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public void openElementPageWithDiscountedPrice(Integer elementNumberInList) {

        driver.findElement(By.xpath(String.format(ITEM_WITH_DISCOUNT_LINK, elementNumberInList))).click();
    }

    public void openWashingMachinesCategory() {
        driver.findElement(By.xpath(HOUSEHOLD_ELECTRONICS_CATEGORY_WASHING_MACHINES_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public String getElementTitle(WebElement element) {
        return element.getAttribute("title");
    }

    public void addElementToShoppingCart(WebElement element) {
        element.findElement(By.xpath(BUTTON_ADD_TO_SHOPPING_CART_ON_ITEM_FRAME)).click();
    }

    public boolean isShoppingCartIconUpdated(Item item) {
        return item.getItemWebElement().findElement(By.xpath(SHOPPING_CART_FILLED_ICON)
        ).isDisplayed();
    }

    public Integer shoppingCartHeaderCounter() {
        return Integer.parseInt(driver.findElement(By.xpath(SHOPPING_CART_HEADER_ELEMENTS_IN_CART_QUANTITY)).getText().trim());
    }

    public void waitForMessageDisappear() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(ITEM_ADDED_TO_CART_MESSAGE))));
    }

    public void clickShoppingCartHeaderIcon() {
        driver.findElement(By.xpath(SHOPPING_CART_HEADER_BUTTON)).click();
    }

    public Item getItemSpecs(int elementNumberInList, boolean discountedItemsOnly) {
        WebElement element;
        String itemTitle;
        Float itemPrice;

        if (discountedItemsOnly) {
            element = driver.findElement(By.xpath(String.format("(" + ITEM_WITH_DISCOUNTED_PRICE + ")[%s]", elementNumberInList)));
        } else {
            element = driver.findElement(By.xpath(String.format("(" + ITEM + ")[%s]", elementNumberInList)));
        }
        itemTitle = getElementTitle(element.findElement(By.xpath(ITEM_TITLE)));
        itemPrice = getItemPrice(element.findElement(By.xpath(ITEM_PRICE)));
        return new Item(itemTitle, itemPrice, element);
    }

    public void openBlanketPage() {
        driver.findElement(By.xpath(HOUSEHOLD_CATEGORY_BLANKETS_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public void clickSortingByPriceAsc() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(SORTING_BUTTON_ICON))));
        driver.findElement(By.xpath(SORT_BY_PRICE_OPTION)).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("goods-tile__inner"))));
    }

    public List<WebElement> getItemsListOnPage() {
        return driver.findElements(By.xpath(ITEM));
    }


    public void clickPageInPaginationList(Integer numberOfPageInPagination) {
        driver.findElement(By.xpath(String.format(PAGINATION_PAGE, numberOfPageInPagination))).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("goods-tile__inner"))));
    }

    public void clickSortingByNewArrivals() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(SORTING_BUTTON_ICON))));
        driver.findElement(By.xpath(SORT_BY_NOVELTY_OPTION)).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(ITEM + "/span"))));

    }


    public List<WebElement> getItemsNewArrivalsList() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        List<WebElement> itemsNewArrivalsList = driver.findElements(By.xpath(ITEM_WITH_NOVELTY_TAG));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return itemsNewArrivalsList;

    }

    public void waitForCategoryPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(H1_CATALOG_HEADER))));
    }

    public void openHomeTextilePage() {
        driver.findElement(By.xpath(HOME_TEXTILE_CATEGORY_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public boolean comparisonIconIsUpdated() {
        return isDisplayed(COMPARISON_ICON_ACTIVE);
    }

    public boolean itemAddToComparisonListMessageIsDisplayed() {
        return isDisplayed(ITEM_ADDED_TO_COMPARISON_MESSAGE);
    }

    public boolean isItemAddedToCartMessageDisplayed() {
        return isDisplayed(ITEM_ADDED_TO_CART_MESSAGE);
    }

    public void waitForItemsLoaded() {
        waitForElementStaleness(ITEM_WITH_DISCOUNTED_PRICE);
    }

    public boolean isShoppingCartWindowDisplayed() {
        return isDisplayed(SHOPPING_CART_WINDOW) || isDisplayed(SHOPPING_CART_PAGE_H1_HEADER);
    }

    public boolean isComparisonWindowDisplayed() {
        return isDisplayed(COMPARISON_WINDOW);
    }
}

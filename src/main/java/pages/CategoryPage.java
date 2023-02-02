package pages;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CategoryPage extends BasePage {

    private static final String XPATH_BREADCRUMBS = "//li[@class='breadcrumbs__item breadcrumbs__item--text ng-star-inserted']/span";
    private static final String XPATH_COMPARISON_BUTTON_OVER_ELEMENT_IMAGE = "//button[@class='compare-button ng-star-inserted']";
    private static final String XPATH_COMPARISON_SCALES_ICON_OVER_ELEMENT_IMAGE = XPATH_COMPARISON_BUTTON_OVER_ELEMENT_IMAGE + "//*[local-name()='use']";
    private static final String XPATH_COMPARISON_HEADER_BUTTON_QUANTITY_OF_ELEMENTS_TO_COMPARE = "//span[@class='badge badge--gray ng-star-inserted']";
    private static final String XPATH_COMPARISON_HEADER_BUTTON = "//button[@class='header__button ng-star-inserted' and @aria-label='Списки сравнения']";
    private static final String XPATH_COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a";
    private static final String XPATH_COMPARISON_WINDOW_QUANTITY_OF_ITEMS_TO_COMPARE = "//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a/span";
    private static final String XPATH_COMPARISON_PAGE_H1 = "//h1[@class='comparison__heading']";
    private static final String XPATH_CATEGORY_PAGE_ITEM_TITLE = "(//a[@class='goods-tile__heading ng-star-inserted'])[%s]";
    private static final String XPATH_CATEGORY_PAGE_IPHONE_CATEGORY_LINK = "//span[contains(text(),'Apple iPhone')]";
    private static final String XPATH_ITEM_WITH_DISCOUNT_LINK = "(//div[contains(@class,'price--red')]/../../a[contains(@class,'goods-tile__heading')])[%s]";
    private static final String XPATH_HOUSEHOLD_ELECTRONICS_CATEGORY_WASHING_MACHINES_LINK = "//li[@class='tile-cats__item ng-star-inserted']/a[contains(@href,'washing_machines')]";
    private static final String XPATH_SHOPPING_CART_HEADER_ELEMENTS_IN_CART_QUANTITY = "//button[@class='header__button ng-star-inserted header__button--active']//span";
    private static final String XPATH_ELEMENT_ADDED_TO_CART_MESSAGE = "//span[contains(text(),'Товар добавлен в корзину')]";
    private static final String XPATH_SHOPPING_CART_HEADER_BUTTON = "//*[name()='use' and @*='#icon-header-basket']/../..";
    private static final String XPATH_HOUSEHOLD_CATEGORY_BLANKETS_LINK = "//ul[@class='tile-cats__list ng-star-inserted']//a[contains(@href,'blankets')]";

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBreadCrumbs() {
        return driver.findElement(By.xpath(XPATH_BREADCRUMBS)).getText();
    }

    public void addElementToComparison(Integer elementNumberInList) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(XPATH_COMPARISON_SCALES_ICON_OVER_ELEMENT_IMAGE))));
        driver.findElement(By.xpath(String.format("(" + XPATH_COMPARISON_BUTTON_OVER_ELEMENT_IMAGE + ")[%s]", elementNumberInList))).click();
    }

    public void addElementToComparison() {
        driver.findElement(By.xpath(XPATH_COMPARISON_BUTTON_OVER_ELEMENT_IMAGE)).click();
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return driver.findElement(By.xpath(XPATH_COMPARISON_HEADER_BUTTON_QUANTITY_OF_ELEMENTS_TO_COMPARE)).getText();
    }

    public void comparisonIconClick() {
        driver.findElement(By.xpath(XPATH_COMPARISON_HEADER_BUTTON)).click();
    }

    public String getComparisonCategory() {
        return driver.findElement(By.xpath(XPATH_COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY)).getText();
    }

    public String getNumberOfItemsToCompare() {
        return driver.findElement(By.xpath(XPATH_COMPARISON_WINDOW_QUANTITY_OF_ITEMS_TO_COMPARE)).getText();
    }

    public void navigateToComparisonPage() {
        driver.findElement(By.xpath(XPATH_COMPARISON_WINDOW_COMPARED_ITEMS_CATEGORY)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(XPATH_COMPARISON_PAGE_H1))));
    }

    public String getItemTitle(Integer elementNumberInCategoryList) {
        return driver.findElement(By.xpath(String.format(XPATH_CATEGORY_PAGE_ITEM_TITLE, elementNumberInCategoryList))).getAttribute("title");
    }

    public void clickIphoneCategoryLink() {
        driver.findElement(By.xpath(XPATH_CATEGORY_PAGE_IPHONE_CATEGORY_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public void openElementPageWithDiscountedPrice(Integer elementNumberInList) {

        driver.findElement(By.xpath(String.format(XPATH_ITEM_WITH_DISCOUNT_LINK, elementNumberInList))).click();
    }

    public void openWashingMachinesCategory() {
        driver.findElement(By.xpath(XPATH_HOUSEHOLD_ELECTRONICS_CATEGORY_WASHING_MACHINES_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public String getElementTitle(WebElement element) {
        return element.getAttribute("title");
    }

    public void addElementToShoppingCart(WebElement element, boolean discountedItemsOnly) {
        if (discountedItemsOnly) {
            element.findElement(By.xpath("//*[@class='buy-button goods-tile__buy-button ng-star-inserted']//*[name()='svg']")).click();
        }
    }

    public boolean isShoppingCartIconUpdated(Item item) {
        return item.getItemWebElement().findElement(By.xpath("//*[name()='use' and @*='#icon-basket-filled']")).isDisplayed();
    }

    public Integer shoppingCartHeaderCounter() {
        return Integer.parseInt(driver.findElement(By.xpath(XPATH_SHOPPING_CART_HEADER_ELEMENTS_IN_CART_QUANTITY)).getText().trim());
    }

    public void waitForMessageDisappear() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(XPATH_ELEMENT_ADDED_TO_CART_MESSAGE))));
    }

    public void clickShoppingCartHeaderIcon() {
        driver.findElement(By.xpath(XPATH_SHOPPING_CART_HEADER_BUTTON)).click();
    }

    public Item getItemSpecs(Integer elementNumberInList, boolean discountedItemsOnly) {
        WebElement element;
        String itemTitle;
        Float itemPrice;

        if (discountedItemsOnly) {
            element = driver.findElement(By.xpath(String.format("(//div[@class='goods-tile__price price--red ng-star-inserted']/../..)[%s]", elementNumberInList)));
        } else {
            element = driver.findElement(By.xpath(String.format("(//div[@class='goods-tile__inner'])[%s]", elementNumberInList)));
        }
        itemTitle = getElementTitle(element.findElement(By.xpath(".//a[@class='goods-tile__heading ng-star-inserted']")));
        itemPrice = getItemPrice(element.findElement(By.xpath(".//span[@class='goods-tile__price-value']")));
        return new Item(itemTitle, itemPrice, element);
    }

    public void openBlanketPage() {
        driver.findElement(By.xpath(XPATH_HOUSEHOLD_CATEGORY_BLANKETS_LINK)).click();
        waitForCategoryPageLoaded();
    }

    public void clickSortingByPriceAsc() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[name()='use' and @*='#icon-view-less']"))));
        driver.findElement(By.xpath("//select/option[@value='1: cheap']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("goods-tile__inner"))));
    }

    public List<WebElement> getItemsListOnPage() {
        return driver.findElements(By.xpath("//div[@class='goods-tile ng-star-inserted']"));
    }


    public void clickPageInPaginationList(Integer numberOfPageInPagination) {
        driver.findElement(By.xpath(String.format("//ul[@class='pagination__list']//a[contains(text(),'%s')]", numberOfPageInPagination))).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("goods-tile__inner"))));
    }

    public void clickSortingByNewArrivals() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[name()='use' and @*='#icon-view-less']"))));
        driver.findElement(By.xpath("//select/option[@value='3: novelty']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@class='goods-tile__inner']/span"))));

    }


    public List<WebElement> getItemsNewArrivalsList() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        List<WebElement> itemsNewArrivalsList = driver.findElements(By.xpath("//span[contains(@class,'promo-label_type_novelty')]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return itemsNewArrivalsList;

    }

    public void waitForCategoryPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='catalog-heading ng-star-inserted']"))));
    }

    public void openHomeTextilePage() {
        driver.findElement(By.xpath("//div[@class='tile-cats']//a[contains(@href,'home_textile')]")).click();
        waitForCategoryPageLoaded();
    }

    public boolean comparisonIconIsUpdated() {
        return isDisplayed("//button[@class='compare-button compare-button_state_active']");
    }

    public boolean itemAddToComparisonListMessageIsDisplayed() {
        return isDisplayed("//a[contains(text(),'Сравнение')]");
    }
}

package steps;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ShoppingCartSteps extends BaseSteps{
    public ShoppingCartSteps(WebDriver driver) {
        super(driver);
    }

    public void openElectronicsCategory() {
        homepage.clickElectronicsCategory();
    }


    public void openIphoneCategory() {
        categoryPage.clickIphoneCategoryLink();
        waitForItemsLoaded();
    }

    public Item getItemWithDiscountSpecsAndOpenPage(Integer elementNumberInList) {
        Item item = categoryPage.getItemSpecs(elementNumberInList,true);
        categoryPage.openElementPageWithDiscountedPrice(elementNumberInList);
        return item;
    }

//    public Item getItemSpecs(Integer elementNumberInList, boolean discountedItemsOnly) {
//        WebElement element;
//        String itemTitle;
//        Float itemPrice;
//       // Item item;
//
//        if (discountedItemsOnly) {
//            element = categoryPage.getWebElement("(//div[@class='goods-tile__price price--red ng-star-inserted']/../..)["+elementNumberInList+"]");
//
//            //item = new Item(itemTitle,itemPrice,element);
//        }
//        else {
//            element = categoryPage.getWebElement("(//div[@class='goods-tile__inner'])["+elementNumberInList+"]");
//            //item = new Item(itemTitle,itemPrice,element);
//        }
//        itemTitle = categoryPage.getElementTitle(element);
//        itemPrice = categoryPage.getItemPrice(element);
//        return new Item(itemTitle,itemPrice,element);
//    }

    public String getItemTitleFromItemPage() {
        return itemPage.getItemTitle();
    }

    public void buyButtonClick() {
        itemPage.buyButtonClick();
    }

    public boolean shoppingCartWindowIsDisplayed() {
        return categoryPage.isDisplayed("//div[@class='modal__holder modal__holder_show_animation modal__holder--large']") || categoryPage.isDisplayed("//h1[@class='cart-page__heading']");
    }

    public float getTotalPriceFromShoppingCartWindow() {
        return itemPage.getShoppingCartTotal();
    }

    public Float getTwoYearWarrantyPriceAndAddService() {
        Float twoYearWarrantyPrice = itemPage.getTwoYearWarrantyPrice();
        itemPage.addTwoYearWarrantyRadioButtonClick();
        itemPage.waitForPriceUpdate();
        return twoYearWarrantyPrice;
    }

    public void clickShoppingCartButton() {
        itemPage.clickShoppingCartButton();
    }

    public void openAppliancesCategory() {
        homepage.clickAppliancesCategory();
    }

    public void openWashingMachinesCategory() {
        categoryPage.openWashingMachinesCategory();
    }

    public Item getItemWithDiscountSpecsAndAddToCart(Integer elementNumberInList) {
        Item item = categoryPage.getItemSpecs(elementNumberInList,true);
        categoryPage.addElementToShoppingCart(item.getItemWebElement(), true);
        return item;
    }

    public boolean itemAddedToShoppingCartMessageIsDisplayed() {
        boolean isDisplayed = categoryPage.isDisplayed("//span[contains(text(),'Товар добавлен в корзину')]");
        categoryPage.waitForMessageDisappear();
        return isDisplayed;
    }

    public boolean shoppingCartIconIsUpdated(Item item) {
        return categoryPage.shoppingCartIconIsUpdated(item);

    }

    public Integer shoppingCartHeaderCounterIsUpdated() {
        return categoryPage.shoppingCartHeaderCounter();
    }

    public void waitForItemsLoaded() {
        categoryPage.waitForElementStaleness("//div[@class='goods-tile__price price--red ng-star-inserted']/../..");
    }

    public void clickShoppingCartHeaderIcon() {
        categoryPage.clickShoppingCartHeaderIcon();
    }

    public List<WebElement> getItemsListFromShoppingCartPage() {
        return shoppingCartPage.getItemListFromShoppingCartPage();
    }

    public String getItemTitleFromShoppingCartPage(WebElement itemInShoppingCartPage) {
        return itemInShoppingCartPage.findElement(By.xpath("//ul[@class='cart-list']/li//a[@class='cart-product__title']")).getText();
    }

    public Float getShoppingCartTotal() {
        return getTotalPriceFromShoppingCartWindow();
    }

    public List<Item> getShoppingCartItems() {
        List<WebElement> elementsInShoppingCart = shoppingCartPage.getItemListFromShoppingCartPage();
        return shoppingCartPage.getShoppingCartItems(elementsInShoppingCart);
    }

    public void shoppingCartClear() {
        shoppingCartPage.deleteAllItemsInCart();
    }

    public void waitForShoppingCartPageLoad() {
        shoppingCartPage.waitForShoppingCartPageLoad();
    }
}

package steps;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartSteps extends BaseSteps{
    public ShoppingCartSteps(WebDriver driver) {
        super(driver);
    }

    public void openIphoneCategory() {
        categoryPage.clickIphoneCategoryLink();
        waitForItemsLoaded();
    }

    public Item getItemWithDiscountSpecsAndOpenPage(int elementNumberInList) {
        Item item = categoryPage.getItemSpecs(elementNumberInList,true);
        categoryPage.openElementPageWithDiscountedPrice(elementNumberInList);
        return item;
    }

    public String getItemTitleFromItemPage() {
        return itemPage.getItemTitle();
    }

    public void buyButtonClick() {
        itemPage.buyButtonClick();
        //Іноді не спливає вікно з вмістом кошику, додав перевірку
        if (!shoppingCartWindowIsDisplayed())
            clickShoppingCartButton();
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

    public void openWashingMachinesCategory() {
        categoryPage.openWashingMachinesCategory();
    }

    public Item getItemWithDiscountSpecsAndAddToCart(int elementNumberInList) {
        Item item = categoryPage.getItemSpecs(elementNumberInList,true);
        categoryPage.addElementToShoppingCart(item.getItemWebElement(), true);
        return item;
    }

    public boolean itemAddedToShoppingCartMessageIsDisplayed() {
        boolean isDisplayed = categoryPage.isDisplayed("//span[contains(text(),'Товар добавлен в корзину')]");
        categoryPage.waitForMessageDisappear();
        return isDisplayed;
    }

    public boolean isShoppingCartIconUpdated(Item item) {
        return categoryPage.isShoppingCartIconUpdated(item);

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

    public Float getShoppingCartTotal() {
        return getTotalPriceFromShoppingCartWindow();
    }

    public List<Item> getShoppingCartItems() {
        List<WebElement> elementsInShoppingCart = shoppingCartPage.getItemListFromShoppingCartPage();
        String itemTitle;
        Float itemPrice;
        List<Item> itemsList = new ArrayList<>();
        for (WebElement element : elementsInShoppingCart
        ) {
            itemTitle = element.findElement(By.xpath(".//a[@class='cart-product__title']")).getText();
            itemPrice = shoppingCartPage.getItemPrice(element.findElement(By.xpath(".//p[contains(@class,'cart-product__price')]")));
            itemsList.add(new Item(itemTitle, itemPrice, element));
        }
        return itemsList;
    }

    public void clearShoppingCart() {
//        знайшов простіший метод очистки кошику
        shoppingCartPage.clearCookies();
    }
}

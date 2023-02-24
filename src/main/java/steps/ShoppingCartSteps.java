package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Inject;
import model.Item;
import pages.CategoryPage;
import pages.ItemPage;
import pages.ShoppingCartPage;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartSteps extends BaseSteps {

    @Inject
    private CategoryPage categoryPage;
    @Inject
    private ItemPage itemPage;
    @Inject
    private ShoppingCartPage shoppingCartPage;


    public void openIphoneCategory() {
        categoryPage.clickIphoneCategoryLink();
    }

    public Item getItemWithDiscountSpecsAndOpenPage(int elementNumberInList) {
        Item item = categoryPage.getItemSpecs(elementNumberInList, true);
        categoryPage.openElementPageWithDiscountedPrice(elementNumberInList);
        return item;
    }

    public String getItemTitleFromItemPage() {
        return itemPage.getItemTitle();
    }

    public void buyButtonClick() {
        itemPage.buyButtonClick();
        if (emptyShoppingCartWindowIsDisplayed()) {
            String productCode = itemPage.getProductCode();

            shoppingCartPage.closeShoppingCartWindow();
            itemPage.buyButtonClick();
        }
            //clickShoppingCartButton();
    }

    public boolean shoppingCartWindowIsDisplayed() {
        return shoppingCartPage.isShoppingCartWindowDisplayed();
    }
    public boolean emptyShoppingCartWindowIsDisplayed() {
        return shoppingCartPage.isEmptyShoppingCartWindowDisplayed();
    }

    public float getTotalPriceFromShoppingCartWindow() {
        return itemPage.getShoppingCartTotal();
    }

    public Float getTwoYearWarrantyPriceAndAddService() {
        Float twoYearWarrantyPrice = itemPage.getTwoYearWarrantyPrice();
        itemPage.addTwoYearWarrantyRadioButtonClick();
        return twoYearWarrantyPrice;
    }

    public void clickShoppingCartButton() {
        itemPage.clickShoppingCartButton();
    }

    public void openWashingMachinesCategory() {
        categoryPage.openWashingMachinesCategory();
    }

    public Item getItemWithDiscountSpecsAndAddToCart(int elementNumberInList) {
        Item item = categoryPage.getItemSpecs(elementNumberInList, true);
        categoryPage.addElementToShoppingCart(item.getItemWebElement());
        return item;
    }

    public boolean itemAddedToShoppingCartMessageIsDisplayed() {
        boolean isDisplayed = categoryPage.isItemAddedToCartMessageDisplayed();
        if (isDisplayed)
            categoryPage.waitForMessageDisappear(); //чекаємо на зникнення повідомлення про додавання в кошик
        return isDisplayed;
    }

    public boolean isShoppingCartIconUpdated(Item item) {
        return categoryPage.isShoppingCartIconUpdated(item);

    }

    public int isShoppingCartHeaderCounterUpdated() {
        return categoryPage.shoppingCartHeaderCounter();
    }

    public void clickShoppingCartHeaderIcon() {
        categoryPage.clickShoppingCartHeaderIcon();
    }

    public Float getShoppingCartTotal() {
        return getTotalPriceFromShoppingCartWindow();
    }

    public List<Item> getShoppingCartItems() {
        ElementsCollection elementsInShoppingCart = shoppingCartPage.getItemsWebelementListFromShoppingCartPage();
        String itemTitle;
        Float itemPrice;
        List<Item> itemsList = new ArrayList<>();
        for (SelenideElement element : elementsInShoppingCart
        ) {
            itemTitle = shoppingCartPage.getItemTitle(element);
            itemPrice = shoppingCartPage.getShoppingCartItemPrice(element);
            itemsList.add(new Item(itemTitle, itemPrice, element));
        }
        return itemsList;
    }

    public void clearShoppingCart() {
        shoppingCartPage.clearCookies();
    }
}

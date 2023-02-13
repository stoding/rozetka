import model.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ShoppingCartSteps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ShoppingCartTest extends BaseTest {

    @BeforeMethod
    public void goToHomePage() {
        ShoppingCartSteps shoppingCartSteps = new ShoppingCartSteps(driver);
        shoppingCartSteps.navigateToHomePage();
    }

    @AfterMethod
    public void clearShoppingCart() {
        ShoppingCartSteps shoppingCartSteps = new ShoppingCartSteps(driver);
        shoppingCartSteps.clearShoppingCart();
    }

    @Test
    public void priceUpdateAfterAddingService() {
        ShoppingCartSteps shoppingCartSteps = new ShoppingCartSteps(driver);
        shoppingCartSteps.openCategory("telefony-tv-i-ehlektronika");
        assertThat(shoppingCartSteps.getPageURL()).contains("telefony-tv-i-ehlektronika");
        shoppingCartSteps.openIphoneCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("mobile-phones");
        Item itemFromCategoryPage = shoppingCartSteps.getItemWithDiscountSpecsAndOpenPage(1);
        assertThat(itemFromCategoryPage.getItemTitle()).isEqualTo(shoppingCartSteps.getItemTitleFromItemPage());
        shoppingCartSteps.buyButtonClick();
        assertThat(shoppingCartSteps.shoppingCartWindowIsDisplayed()).isTrue();
        assertThat(itemFromCategoryPage.getItemPrice()).isEqualTo(shoppingCartSteps.getTotalPriceFromShoppingCartWindow());
        float actualTotalPrice = shoppingCartSteps.getTwoYearWarrantyPriceAndAddService() + itemFromCategoryPage.getItemPrice();
        assertThat(actualTotalPrice).isEqualTo(shoppingCartSteps.getTotalPriceFromShoppingCartWindow());
    }

    @Test
    public void itemsWithDiscountAddToCart() {
        ShoppingCartSteps shoppingCartSteps = new ShoppingCartSteps(driver);
        shoppingCartSteps.openCategory("bt.rozetka");
        assertThat(shoppingCartSteps.getPageURL()).contains("bt.rozetka.com.ua");
        shoppingCartSteps.openWashingMachinesCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("washing_machines");
        shoppingCartSteps.waitForItemsLoaded();

        List<Item> itemsList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            itemsList.add(shoppingCartSteps.getItemWithDiscountSpecsAndAddToCart(i));
            assertThat(shoppingCartSteps.itemAddedToShoppingCartMessageIsDisplayed()).isTrue();
            assertThat(shoppingCartSteps.isShoppingCartIconUpdated(itemsList.get(i - 1))).isTrue();
            assertThat(shoppingCartSteps.isShoppingCartHeaderCounterUpdated()).isEqualTo(i);
        }
        shoppingCartSteps.clickShoppingCartHeaderIcon();
        assertThat(shoppingCartSteps.shoppingCartWindowIsDisplayed()).isTrue();
        List<Item> shoppingCartItems = shoppingCartSteps.getShoppingCartItems();
        assertThat(shoppingCartItems.size() == itemsList.size()).isTrue();
        Collections.reverse(itemsList);

        float total = 0;
        for (int i = 0; i < itemsList.size(); i++) {
            // тут навіть пощастило побачити падіння тесту, бо title однієї з пральних машинок на сторінці категорії
            // відрізняється, від title у корзині
            assertThat(shoppingCartItems.get(i).getItemTitle()).isEqualTo(itemsList.get(i).getItemTitle());
            total += itemsList.get(i).getItemPrice();
        }
        assertThat(shoppingCartSteps.getShoppingCartTotal()).isEqualTo(total);
    }
}

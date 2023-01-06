import model.Item;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import steps.ShoppingCartSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ShoppingCartTest extends BaseTest {


    @Test
    public void priceUpdateAfterAddingService() {
        ShoppingCartSteps shoppingCartSteps = new ShoppingCartSteps(driver);
        shoppingCartSteps.openElectronicsCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("telefony-tv-i-ehlektronika");
        shoppingCartSteps.openIphoneCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("mobile-phones");
        Item itemFromCategoryPage = shoppingCartSteps.getItemWithDiscountSpecsAndOpenPage(1);
        assertThat(itemFromCategoryPage.getItemTitle()).isEqualTo(shoppingCartSteps.getItemTitleFromItemPage());
        shoppingCartSteps.buyButtonClick();
        //Іноді не спливає вікно з вмістом кошику, додав перевірку
        if (!shoppingCartSteps.shoppingCartWindowIsDisplayed())
            shoppingCartSteps.clickShoppingCartButton();
        assertThat(shoppingCartSteps.shoppingCartWindowIsDisplayed()).isTrue();
        assertThat(itemFromCategoryPage.getItemPrice()).isEqualTo(shoppingCartSteps.getTotalPriceFromShoppingCartWindow());
        assertThat(shoppingCartSteps.getTwoYearWarrantyPriceAndAddService()+itemFromCategoryPage.getItemPrice()).isEqualTo(shoppingCartSteps.getTotalPriceFromShoppingCartWindow());
    }

    @Test
    public void itemsWithDiscountAddToCart() throws InterruptedException {
        ShoppingCartSteps shoppingCartSteps = new ShoppingCartSteps(driver);
        shoppingCartSteps.openAppliancesCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("bt.rozetka.com.ua");
        shoppingCartSteps.openWashingMachinesCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("washing_machines");
        shoppingCartSteps.waitForItemsLoaded();

//        List<Item> itemsList = new ArrayList<>();
//        for(int i = 1; i<3; i++) {
//            itemsList.add(shoppingCartSteps.getItemWithDiscountSpecsAndAddToCart(i));
//            assertThat(shoppingCartSteps.itemAddedToShoppingCartMessageIsDisplayed()).isTrue();
//            assertThat(shoppingCartSteps.shoppingCartIconIsUpdated(itemsList.get(i-1))).isTrue();
//            assertThat(shoppingCartSteps.shoppingCartHeaderCounterIsUpdated()).isEqualTo(i);
//        }
        Item item1 = shoppingCartSteps.getItemWithDiscountSpecsAndAddToCart(1);
        Thread.sleep(4000);
        Item item2 = shoppingCartSteps.getItemWithDiscountSpecsAndAddToCart(2);
        assertThat(shoppingCartSteps.itemAddedToShoppingCartMessageIsDisplayed()).isTrue();
        //assertThat(shoppingCartSteps.shoppingCartIconIsUpdated(itemsList.get(i-1))).isTrue();
       // assertThat(shoppingCartSteps.shoppingCartHeaderCounterIsUpdated()).isEqualTo(i);

        shoppingCartSteps.clickShoppingCartHeaderIcon();
        assertThat(shoppingCartSteps.shoppingCartWindowIsDisplayed()).isTrue();
        List<WebElement> shoppingCartItems = shoppingCartSteps.getItemsListFromShoppingCartPage();
//        assertThat(shoppingCartItems.size()==itemsList.size()).isTrue();
//        Collections.reverse(itemsList);
//        float total = 0;
//        for (int i=0;i<itemsList.size();i++)
//             {
//                 assertThat(shoppingCartSteps.getItemTitleFromShoppingCartPage(shoppingCartItems.get(i))).isEqualTo(itemsList.get(i).getItemTitle());
//                 total+=itemsList.get(i).getItemPrice();
//        }
//        assertThat(shoppingCartSteps.getShoppingCartTotal()).isEqualTo(total);
        Thread.sleep(5000);
    }
}

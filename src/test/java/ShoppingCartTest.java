import com.google.inject.Inject;
import model.Item;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.ShoppingCartSteps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class ShoppingCartTest extends BaseTest {

    @Inject
    ShoppingCartSteps shoppingCartSteps;

    @BeforeMethod
    public void goToHomePage() {
        open(ROZETKA_HOMEPAGE_URL);
    }

    @AfterMethod
    public void clearShoppingCart() {
        shoppingCartSteps.clearShoppingCart();
    }

    @Test
    public void priceUpdateAfterAddingService() {
        shoppingCartSteps.openCategory("telefony-tv-i-ehlektronika");
        assertThat(shoppingCartSteps.getPageURL()).contains("telefony-tv-i-ehlektronika");
        shoppingCartSteps.openIphoneCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("mobile-phones");
        Item itemFromCategoryPage = shoppingCartSteps.getItemWithDiscountSpecsAndOpenPage(1);
        assertThat(itemFromCategoryPage.getItemTitle()).isEqualTo(shoppingCartSteps.getItemTitleFromItemPage());
        //В мене тут виникла проблема. Іноді якось криво завантажується сторінка і після кліку по кнопці покупки
//        відкривається порожня корзина. Я намагався вирішити різними методами. Якщо просто обробляти подію закриваючи вікно то
//        наступний клік по кнопці покупки знов відкриває порожнє вікно корзини. Дещо допоміг варіант
//        перезавантаження поточної сторінки, але після цього став криво реагувати браузер на додавання послуги гарантії,
//        а саме не додавалась гарантія у підсумкову суму, типу чекбокс клацає, але сторінка обновляється зі старою ціною
//        дивно, якщо є думки - буду рад пораді як вирішити...
        shoppingCartSteps.buyButtonClick();
        assertThat(shoppingCartSteps.shoppingCartWindowIsDisplayed()).isTrue();
        assertThat(itemFromCategoryPage.getItemPrice()).isEqualTo(shoppingCartSteps.getTotalPriceFromShoppingCartWindow());
        float actualTotalPrice = shoppingCartSteps.getTwoYearWarrantyPriceAndAddService() + itemFromCategoryPage.getItemPrice();
        assertThat(actualTotalPrice).isEqualTo(shoppingCartSteps.getTotalPriceFromShoppingCartWindow());
    }

    @Test
    public void itemsWithDiscountAddToCart() {
        shoppingCartSteps.openCategory("bt.rozetka");
        assertThat(shoppingCartSteps.getPageURL()).contains("bt.rozetka.com.ua");
        shoppingCartSteps.openWashingMachinesCategory();
        assertThat(shoppingCartSteps.getPageURL()).contains("washing_machines");
        //shoppingCartSteps.waitForItemsLoaded();

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

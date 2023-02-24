package pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.time.Duration;

public class ShoppingCartPage extends BasePage {

    private static final String ITEM_IN_CART = "//ul[@class='cart-list']/li";
    private static final String ITEM_LINK = ".//a[@class='cart-product__title']";
    private static final String ITEM_PRICE_ON_CART_PAGE = ".//p[contains(@class,'cart-product__price')]";
    private static final String SHOPPING_CART_WINDOW = "//div[contains(@class,'modal__holder')]";


    public ElementsCollection getItemsWebelementListFromShoppingCartPage() {
        return $$x(ITEM_IN_CART);
    }

    public String getItemTitle(SelenideElement element) {
        return element.find(byXpath(ITEM_LINK)).getText();
    }

    public Float getShoppingCartItemPrice(SelenideElement element) {
        return getItemPrice(element.find(byXpath(ITEM_PRICE_ON_CART_PAGE)));
    }

    public void closeShoppingCartWindow() {
        Selenide.clearBrowserCookies();
//        Selenide.refresh();
//        Selenide.sleep(2000);
        //WebDriverRunner.getWebDriver().navigate().refresh();
    }

    public boolean isShoppingCartWindowDisplayed() {
        return $x(SHOPPING_CART_WINDOW).shouldBe(visible).isDisplayed(); //|| $x(SHOPPING_CART_PAGE_H1_HEADER).shouldBe(visible).isDisplayed();
    }

    public boolean isEmptyShoppingCartWindowDisplayed() {
        try {
            $(".cart-dummy__illustration").shouldBe(visible, Duration.ofSeconds(2));
            return true;
        } catch (ElementNotFound e) {
            return false;
        }
    }
}

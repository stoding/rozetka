package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ItemPage extends BasePage {

    private static final String SPECS_BUTTON = "//a[contains(@href,'characteristics')]";
    private static final String ITEM_SCREEN_RESOLUTION = "//span[contains(text(),'Разрешение дисплея')]/ancestor::dt/following-sibling::dd//a";
    private static final String ITEM_SUPPORTED_SIM_CARDS = "//span[contains(text(),'Количество SIM-карт')]/ancestor::dt/following-sibling::dd//a";
    private static final String ITEM_TITLE_IN_FULL_SPECS = "//div/div[@class='product-carriage__goods-title']";
    private static final String ITEM_BUY_BUTTON = "//div[contains(@class,'product-trade')]//button[contains(@class,'buy-button')]/span";
    private static final String SHOPPING_CART_TOTAL = "//div[@class='cart-receipt__sum-price']/span";
    private static final String TWO_YEAR_WARRANTY_CHECKBOX = "//ul[@class='cart-service__variants']//span[contains(text(),'25001-40000')]";
    private static final String TWO_YEAR_WARRANTY_PRICE = "//ul[@class='cart-service__variants']//span[contains(text(),'25001-40000')]//following-sibling::span";
    private static final String PRODUCT_TITLE_H1 = "//h1[@class='product__title']";
    private static final String SHOPPING_CART_BUTTON_AFTER_ITEM_ADDED_TO_CART = "//ul[@class='product-buttons']//button[contains(@class,'buy-button_state_in-cart')]";
    private static final String ITEM_PRICE_ON_ITEM_PAGE = "//p[contains(@class,'product-carriage__price')]";
    private static final String ITEM_PAGE_TABS = ".tabs__list";
    private static final String ITEM_PRODUCT_CODE = ".detail-code";

    public void specificationButtonClick() {
        $(ITEM_PAGE_TABS).shouldBe(visible);
        $x(SPECS_BUTTON).click();
    }

    public String getItemScreenResolution() {
        return $x(ITEM_SCREEN_RESOLUTION).getText();
    }

    public int getItemNumberOfSupportedSimCards() {
        return Integer.parseInt($x(ITEM_SUPPORTED_SIM_CARDS).getText());
    }

    public String getItemTitleWithOpenedSpecs() {
        return $x(ITEM_TITLE_IN_FULL_SPECS).getText();
    }

    public void buyButtonClick() {
        $x(ITEM_BUY_BUTTON).shouldBe(interactable).click();
    }

    public float getShoppingCartTotal() {
        return getItemPrice(SHOPPING_CART_TOTAL);
    }

    public void addTwoYearWarrantyRadioButtonClick() {
        String oldPrice = $x(SHOPPING_CART_TOTAL).getText();
        $x(TWO_YEAR_WARRANTY_CHECKBOX).click();
        $x(SHOPPING_CART_TOTAL).shouldBe(not(Condition.matchText(oldPrice)));
    }

    public Float getTwoYearWarrantyPrice() {
        return getItemPrice(TWO_YEAR_WARRANTY_PRICE);
    }

    public String getItemTitle() {
        return $x(PRODUCT_TITLE_H1).getText();
    }

    public void clickShoppingCartButton() {
        $x(SHOPPING_CART_BUTTON_AFTER_ITEM_ADDED_TO_CART).click();
    }

    public void waitForItemPageLoad() {
        $x(PRODUCT_TITLE_H1).shouldBe(visible);
    }

    public Float getItemPriceFromItemPage() {
        return getItemPrice(ITEM_PRICE_ON_ITEM_PAGE);
    }

    public String getProductCode() {
        return $(ITEM_PRODUCT_CODE).getText();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    private static final String ITEM_IN_CART = "//ul[@class='cart-list']/li";
    private static final String ITEM_LINK = ".//a[@class='cart-product__title']";
    private static final String ITEM_PRICE_ON_CART_PAGE = ".//p[contains(@class,'cart-product__price')]";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getItemsWebelementListFromShoppingCartPage() {
        return driver.findElements(By.xpath(ITEM_IN_CART));
    }

    public String getItemTitle(WebElement element) {
        return element.findElement(By.xpath(ITEM_LINK)).getText();
    }

    public Float getShoppingCartItemPrice(WebElement element) {
        return getItemPrice(element.findElement(By.xpath(ITEM_PRICE_ON_CART_PAGE)));
    }
}

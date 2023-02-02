package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getItemListFromShoppingCartPage() {
        return driver.findElements(By.xpath("//ul[@class='cart-list']/li"));
    }

}

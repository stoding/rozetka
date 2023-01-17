package pages;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getItemListFromShoppingCartPage() {
        return driver.findElements(By.xpath("//ul[@class='cart-list']/li"));
    }

    public WebElement getWebElement(String webElementXpath) {
        return driver.findElement(By.xpath(webElementXpath));
    }

    public List<Item> getShoppingCartItems(List<WebElement> elementsInShoppingCart) {
        String itemTitle;
        Float itemPrice;
        List<Item> itemsList = new ArrayList<>();
        for (WebElement element : elementsInShoppingCart
        ) {
            itemTitle = element.findElement(By.xpath(".//a[@class='cart-product__title']")).getText();
            itemPrice = getItemPrice(element.findElement(By.xpath(".//p[contains(@class,'cart-product__price')]")));
            itemsList.add(new Item(itemTitle, itemPrice, element));
        }
        return itemsList;
    }

    public void deleteAllItemsInCart() {
        List<WebElement> itemsInCart = driver.findElements(By.xpath("//div[@class='cart-product__body']//button"));
        for (WebElement element : itemsInCart) {
            element.click();
            //Дивно, але тут як і в тестах з порівняння - ComparisonTest перестали відпрацьовувати кліки мишки, падає вейтер
            wait.until(ExpectedConditions.visibilityOf(element.findElement(By.xpath("./following-sibling::div//button")))).click();
            wait.until(ExpectedConditions.stalenessOf(element));
        }
    }

    public void waitForShoppingCartPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='cart-page__heading']"))));
    }

    public void confirmButtonClick() {
        driver.findElement(By.xpath("//div[@class='popup-menu__list popup-menu__list--confirm ng-star-inserted']//button")).click();
    }
}

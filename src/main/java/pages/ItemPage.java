package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemPage extends BasePage {

    private static final String SPECS_BUTTON = "//a[contains(@href,'characteristics')]";
    private static final String ITEM_SCREEN_RESOLUTION = "//span[contains(text(),'Разрешение дисплея')]/ancestor::dt/following-sibling::dd//a";
    private static final String ITEM_SUPPORTED_SIM_CARDS = "//span[contains(text(),'Количество SIM-карт')]/ancestor::dt/following-sibling::dd//a";
    private static final String ITEM_TITLE_IN_FULL_SPECS = "//div/div[@class='product-carriage__product-title']";
    private static final String ITEM_BUY_BUTTON = "//div[contains(@class,'product-trade')]//button[contains(@class,'buy-button')]";
    private static final String SHOPPING_CART_TOTAL = "//div[@class='cart-receipt__sum-price']/span";
    private static final String TWO_YEAR_WARRANTY_CHECKBOX = "//ul[@class='cart-service__variants']//span[contains(text(),'25001-40000')]";
    private static final String TWO_YEAR_WARRANTY_PRICE = "//ul[@class='cart-service__variants']//span[contains(text(),'25001-40000')]//following-sibling::span";
    private static final String PRODUCT_TITLE_H1 = "//h1[@class='product__title']";
    private static final String SHOPPING_CART_BUTTON_AFTER_ITEM_ADDED_TO_CART = "//ul[@class='product-buttons']//button[contains(@class,'buy-button_state_in-cart')]";
    private static final String ITEM_PRICE_ON_ITEM_PAGE = "//div[(contains(@class,'product-carriage__price'))]";

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void specificationButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("tabs__list"))));
        try {
            driver.findElement(By.xpath(SPECS_BUTTON)).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath(SPECS_BUTTON)).click();
        }
    }

    public String getItemScreenResolution() {
        return driver.findElement(By.xpath(ITEM_SCREEN_RESOLUTION)).getText();
    }

    public Integer getItemNumberOfSupportedSimCards() {
        return Integer.parseInt(driver.findElement(By.xpath(ITEM_SUPPORTED_SIM_CARDS)).getText());
    }

    public String getItemTitleWithOpenedSpecs() {
        return driver.findElement(By.xpath(ITEM_TITLE_IN_FULL_SPECS)).getText();
    }

    public void buyButtonClick() {
        driver.findElement(By.xpath(ITEM_BUY_BUTTON)).click();
    }

    public float getShoppingCartTotal() {
        return getItemPrice(SHOPPING_CART_TOTAL);
    }

    public void addTwoYearWarrantyRadioButtonClick() {
        driver.findElement(By.xpath(TWO_YEAR_WARRANTY_CHECKBOX)).click();
    }

    public Float getTwoYearWarrantyPrice() {
        return getItemPrice(TWO_YEAR_WARRANTY_PRICE);
    }

    public String getItemTitle() {
        return driver.findElement(By.xpath(PRODUCT_TITLE_H1)).getText();
    }

    public void clickShoppingCartButton() {
        driver.findElement(By.xpath(SHOPPING_CART_BUTTON_AFTER_ITEM_ADDED_TO_CART)).click();
    }

    public void waitForPriceUpdate() {
        WebElement oldPrice = driver.findElement(By.xpath(SHOPPING_CART_TOTAL));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(oldPrice, oldPrice.getText())));
    }

    public void waitForItemPageLoad(String expectedItem) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//p[contains(text(),'" + expectedItem + "')]"))));
    }

    public Float getItemPriceFromItemPage() {
        return getItemPrice(ITEM_PRICE_ON_ITEM_PAGE);
    }
}

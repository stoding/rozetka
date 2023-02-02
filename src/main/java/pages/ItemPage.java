package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemPage extends BasePage {
    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void specificationButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("tabs__list"))));
        try {
            driver.findElement(By.xpath("//a[contains(@href,'characteristics')]")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//a[contains(@href,'characteristics')]")).click();
        }
    }

    public String getItemScreenResolution() {
        return driver.findElement(By.xpath("//span[contains(text(),'Разрешение дисплея')]/ancestor::dt/following-sibling::dd//a")).getText();
    }

    public Integer getItemNumberOfSupportedSimCards() {
        return Integer.parseInt(driver.findElement(By.xpath("//span[contains(text(),'Количество SIM-карт')]/ancestor::dt/following-sibling::dd//a")).getText());
    }

    public String getItemTitleWithOpenedSpecs() {
        return driver.findElement(By.xpath("//div/div[@class='product-carriage__product-title']")).getText();
    }

    public void buyButtonClick() {
        driver.findElement(By.xpath("//div[contains(@class,'product-trade')]//button[contains(@class,'buy-button')]")).click();
    }

    public float getShoppingCartTotal() {
        return getItemPrice("//div[@class='cart-receipt__sum-price']/span");
    }

    public void addTwoYearWarrantyRadioButtonClick() {
        driver.findElement(By.xpath("//ul[@class='cart-service__variants']//span[contains(text(),'25001-40000')]")).click();
    }

    public Float getTwoYearWarrantyPrice() {
        return getItemPrice("//ul[@class='cart-service__variants']//span[contains(text(),'25001-40000')]//following-sibling::span");
    }

    public String getItemTitle() {
        return driver.findElement(By.xpath("//h1[@class='product__title']")).getText();
    }

    public void clickShoppingCartButton() {
        driver.findElement(By.xpath("//button[@class='buy-button button button--with-icon button--green button--medium ng-star-inserted buy-button_state_in-cart']")).click();
    }

    public void waitForPriceUpdate() {
        WebElement oldPrice = driver.findElement(By.xpath("//div[@class='cart-receipt__sum-price']/span"));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(oldPrice, oldPrice.getText())));
    }

    public void waitForItemPageLoad(String expectedItem) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//p[contains(text(),'" + expectedItem + "')]"))));
    }
}

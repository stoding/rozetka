package pages;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage extends BasePage{
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBreadCrumbs() {
        return driver.findElement(By.xpath("//li[@class='breadcrumbs__item breadcrumbs__item--text ng-star-inserted']/span")).getText();
    }

    public void addElementToComparison(Integer elementNumberInList) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//button[@class='compare-button ng-star-inserted']//*[local-name()='use']"))));
        driver.findElement(By.xpath("(//button[@class='compare-button ng-star-inserted'])["+elementNumberInList+"]")).click();
    }
    public void addElementToComparison() {
        driver.findElement(By.xpath("//button[@class='compare-button ng-star-inserted']")).click();
    }

    public String getNumberOfItemsOfComparisonIcon() {
        return driver.findElement(By.xpath("//span[@class='badge badge--gray ng-star-inserted']")).getText();
    }

    public void comparisonIconClick() {
        driver.findElement(By.xpath("//button[@class='header__button ng-star-inserted' and @aria-label='Списки сравнения']")).click();
    }

    public String getComparisonCategory() {
        return driver.findElement(By.xpath("//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a")).getText();
    }

    public String getNumberOfItemsToCompare() {
        return driver.findElement(By.xpath("//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a/span")).getText();
    }

    public void navigateToComparisonPage() {
        driver.findElement(By.xpath("//div[@class='modal__holder modal__holder_show_animation modal__holder_size_small']//a")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='comparison__heading']"))));
    }

    public String getItemTitleFromCategoryPage(Integer elementNumberInCategoryList) {
        return driver.findElement(By.xpath("(//a[@class='goods-tile__heading ng-star-inserted'])["+elementNumberInCategoryList+"]")).getAttribute("title");
    }

    public void clickIphoneCategoryLink() {
        driver.findElement(By.xpath("//span[contains(text(),'Apple iPhone')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='catalog-heading ng-star-inserted']"))));
    }

    public void openElementPageWithDiscountedPrice(Integer elementNumberInList) {
        driver.findElement(By.xpath("(//div[@class='goods-tile__price--old price--gray ng-star-inserted' and text()[string-length() > 4]]/following-sibling::div//span[@class='goods-tile__price-value']/../../../../a)["+elementNumberInList+"]")).click();
    }

    public String getElementWithDiscountTitle(Integer elementNumberInList) {
        return driver.findElement(By.xpath("(//div[@class='goods-tile__price--old price--gray ng-star-inserted' and text()[string-length() > 4]]/following-sibling::div//span[@class='goods-tile__price-value']/../../../..//img)["+elementNumberInList+"]")).getAttribute("title");

    }

    public void openWashingMachinesCategory() {
        driver.findElement(By.xpath("//li[@class='tile-cats__item ng-star-inserted']/a[contains(@href,'washing_machines')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='catalog-heading ng-star-inserted']"))));
    }

    public String getElementTitle(WebElement element) {
//        String temp = element.getAttribute("title");
        //якщо ідентичний елемент випрати явно, то все працює
//        WebElement testelem = driver.findElement(By.xpath("(//div[@class='goods-tile__price price--red ng-star-inserted']/../..)[2]//a[@class='goods-tile__heading ng-star-inserted']"));
        WebElement subelem = element.findElement(By.xpath("//a[@class='goods-tile__heading ng-star-inserted']"));
        String title = subelem.getAttribute("title");
        return title;
    }

    public void addElementToShoppingCart(WebElement element, boolean discountedItemsOnly) {
        if (discountedItemsOnly){

            element.findElement(By.xpath("//*[@class='buy-button goods-tile__buy-button ng-star-inserted']//*[name()='svg']")).click();
           //element.findElement(By.xpath("//*[@class='buy-button goods-tile__buy-button ng-star-inserted']//*[name()='svg']")).click();
           // wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
           //button[@class='buy-button goods-tile__buy-button ng-star-inserted'
            //button[@class='buy-button goods-tile__buy-button ng-star-inserted']
            // driver.findElement(By.xpath("(//div[@class='goods-tile__price price--red ng-star-inserted']//button)["+elementNumberInList+"]")).click();
        }
    }

    public boolean shoppingCartIconIsUpdated(Item item) {
        return item.getItemWebElement().findElement(By.xpath("//*[name()='use' and @*='#icon-basket-filled']")).isDisplayed();
    }

    public Integer shoppingCartHeaderCounter() {
        return Integer.parseInt(driver.findElement(By.xpath("//button[@class='header__button ng-star-inserted header__button--active']//span")).getText().trim());
    }

    public void waitForMessageDisappear() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//span[contains(text(),'Товар добавлен в корзину')]"))));
    }

    public void clickShoppingCartHeaderIcon() {
        driver.findElement(By.xpath("//*[name()='use' and @*='#icon-header-basket']/../..")).click();
    }
    public WebElement getWebElement(String webElementXpath) {
        return driver.findElement(By.xpath(webElementXpath));
    }
    public Item getItemSpecs(Integer elementNumberInList, boolean discountedItemsOnly) {
        WebElement element;
        String itemTitle;
        Float itemPrice;
        // Item item;

        if (discountedItemsOnly) {
            element = driver.findElement(By.xpath("(//div[@class='goods-tile__price price--red ng-star-inserted']/../..)["+elementNumberInList+"]"));

            //item = new Item(itemTitle,itemPrice,element);
        }
        else {
            element = driver.findElement(By.xpath("(//div[@class='goods-tile__inner'])["+elementNumberInList+"]"));
            //item = new Item(itemTitle,itemPrice,element);
        }
        itemTitle = getElementTitle(element);
        itemPrice = getItemPrice(element);
        return new Item(itemTitle,itemPrice,element);
    }
}

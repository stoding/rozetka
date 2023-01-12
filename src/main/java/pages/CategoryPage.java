package pages;

import model.Item;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBreadCrumbs() {
        return driver.findElement(By.xpath("//li[@class='breadcrumbs__item breadcrumbs__item--text ng-star-inserted']/span")).getText();
    }

    public void addElementToComparison(Integer elementNumberInList) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//button[@class='compare-button ng-star-inserted']//*[local-name()='use']"))));
        driver.findElement(By.xpath("(//button[@class='compare-button ng-star-inserted'])[" + elementNumberInList + "]")).click();
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
        return driver.findElement(By.xpath("(//a[@class='goods-tile__heading ng-star-inserted'])[" + elementNumberInCategoryList + "]")).getAttribute("title");
    }

    public void clickIphoneCategoryLink() {
        driver.findElement(By.xpath("//span[contains(text(),'Apple iPhone')]")).click();
        waitForCategoryPageLoaded();
    }

    public void openElementPageWithDiscountedPrice(Integer elementNumberInList) {
        driver.findElement(By.xpath("(//div[@class='goods-tile__price--old price--gray ng-star-inserted' and text()[string-length() > 4]]/following-sibling::div//span[@class='goods-tile__price-value']/../../../../a)[" + elementNumberInList + "]")).click();
    }

    public String getElementWithDiscountTitle(Integer elementNumberInList) {
        return driver.findElement(By.xpath("(//div[@class='goods-tile__price--old price--gray ng-star-inserted' and text()[string-length() > 4]]/following-sibling::div//span[@class='goods-tile__price-value']/../../../..//img)[" + elementNumberInList + "]")).getAttribute("title");

    }

    public void openWashingMachinesCategory() {
        driver.findElement(By.xpath("//li[@class='tile-cats__item ng-star-inserted']/a[contains(@href,'washing_machines')]")).click();
        waitForCategoryPageLoaded();
    }

    public String getElementTitle(WebElement element) {
//        String temp = element.getAttribute("title");
        //якщо ідентичний елемент випрати явно, то все працює
//        WebElement testelem = driver.findElement(By.xpath("(//div[@class='goods-tile__price price--red ng-star-inserted']/../..)[2]//a[@class='goods-tile__heading ng-star-inserted']"));
        WebElement subelem = element.findElement(By.xpath(".//a[@class='goods-tile__heading ng-star-inserted']"));
        String title = subelem.getAttribute("title");
        return title;
    }

    public void addElementToShoppingCart(WebElement element, boolean discountedItemsOnly) {
        if (discountedItemsOnly) {

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

        if (discountedItemsOnly) {
            element = driver.findElement(By.xpath("(//div[@class='goods-tile__price price--red ng-star-inserted']/../..)[" + elementNumberInList + "]"));
            ;
        } else {
            element = driver.findElement(By.xpath("(//div[@class='goods-tile__inner'])[" + elementNumberInList + "]"));
        }
        itemTitle = getElementTitle(element);
        itemPrice = getItemPrice(element.findElement(By.xpath(".//span[@class='goods-tile__price-value']")));
        return new Item(itemTitle, itemPrice, element);
    }

    public void openBlanketPage() {
        driver.findElement(By.xpath("//ul[@class='tile-cats__list ng-star-inserted']//a[contains(@href,'blankets')]")).click();
        waitForCategoryPageLoaded();
    }

    public void clickSortingByPriceAsc() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[name()='use' and @*='#icon-view-less']"))));
        driver.findElement(By.xpath("//select/option[@value='1: cheap']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("goods-tile__inner"))));
    }

    public List<WebElement> getItemsListOnPage() {
        return driver.findElements(By.xpath("//div[@class='goods-tile ng-star-inserted']"));
    }

    public boolean sortByPriceAscIsApplied(List<WebElement> elementsListOnPage) {
        Float currentElementPrice;
        Float previousElementPrice = 0f;
        for (WebElement element : elementsListOnPage) {
            currentElementPrice = getItemPrice(element.findElement(By.xpath(".//span[@class='goods-tile__price-value']")));
            if (currentElementPrice < previousElementPrice)
                return false;
            previousElementPrice = currentElementPrice;

        }
        return true;
    }

    public void clickPageInPaginationList(Integer numberOfPageInPagination) {
        driver.findElement(By.xpath("//ul[@class='pagination__list']//a[contains(text(),'" + numberOfPageInPagination + "')]")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("goods-tile__inner"))));
    }

    public void clickComputerCategory() {
        driver.findElement(By.xpath("//div[@class='menu-wrapper menu-wrapper_state_static ng-star-inserted']//a[contains(@href,'computers')]")).click();
        waitForCategoryPortalPageLoaded();
    }

    public void clickTabletCategory() {
        driver.findElement(By.xpath("//div[@class='ng-star-inserted']//a[@title='Планшеты']")).click();
        waitForCategoryPageLoaded();
    }

    public void clickSortingByNewArrivals() {
        try {
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[name()='use' and @*='#icon-view-less']"))));
            driver.findElement(By.xpath("//select/option[@value='3: novelty']")).click();
        } catch (TimeoutException e) {
            driver.findElement(By.xpath("//select/option[@value='3: novelty']")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public boolean newItemsDisplayedFirst(List<WebElement> itemsListOnPage) {
        boolean itemWithoutNewTagPresent = false;
        int itemsQuantityWithNewArrivalsTag = getItemsNewArrivalsList().size();
        int itemCounter = 0;
        //Мабуть опишу тут логику: є List всіх товарів на сторінці, є кількість товарів з тегом "новинка"
        //Щоб не йти по всьому масиву елементів я додав лічильник - кількість товарів які ми вже зустріли з тегом "новинка"
        //Якщо нам не зустрівся елемент без тега "новинка" та лічильник нарахував ту ж саму кількість товарів, то повертаємо true
        //а якщо нам трапиться товар без тегу новинка, а лічильник ще не буде дорівнювати необхідної кількості - то викене false,
        //тобто ланцюжок товарів-новинок перервався, що є дефектом сортування
        //
        // UPD: Вже написав сценарій, але цей тепер тест падає))), ну з іншого боку теж добре.
        // На сторінці між елементами з новинками є елемент з тегом топ продаж
        for (WebElement item : itemsListOnPage
        ) {
            //перевіряю через findelements щоб не падав тест з ексепшн коли елементу не буде
            if (item.findElements(By.xpath(".//span[contains(@class,'promo-label_type_novelty')]")).size() != 0) {
                itemCounter++;
                if (itemCounter == itemsQuantityWithNewArrivalsTag)
                    return true;
                if (itemWithoutNewTagPresent)
                    return false;
            } else itemWithoutNewTagPresent = true;


        }
        return true;
    }

    public List<WebElement> getItemsNewArrivalsList() {
        //зменшив вейтер для цього метода щоб драйвер не чекав елементу якщо його нема.
        //Питання, чи треба назад змінити час очикування, чи воно міняється лише для цього методу?
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
        return driver.findElements(By.xpath("//span[contains(@class,'promo-label_type_novelty')]"));
    }

    public void waitForCategoryPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='catalog-heading ng-star-inserted']"))));
    }

    public void openHomeTextilePage() {
        driver.findElement(By.xpath("//div[@class='tile-cats']//a[contains(@href,'home_textile')]")).click();
        waitForCategoryPageLoaded();
    }
}

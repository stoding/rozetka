package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public static final String ROZETKA_HOMEPAGE_ADDRESS = "https://rozetka.com.ua/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Не впевнений чи роблять так, але для прискорення деяких тестів зробив два методи isDisplayed,
    //в одному з них змінюю implicitlyWait для драйвера, щоб прискорити виконання тестів де не треба так довго чекати
    public boolean isDisplayed(String webElementXpath, int elementWaitTime) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(elementWaitTime));
        try {
            return driver.findElement(By.xpath(webElementXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisplayed(String webElementXpath) {
        try {
            return driver.findElement(By.xpath(webElementXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
//    public boolean isDisplayed(WebElement element) {
//        try {
//            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }

    public void waitForElementStaleness(String webElementXpath) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(webElementXpath))));
    }

    public void fieldClear(String webElementXpath) {
        driver.findElement(By.xpath(webElementXpath)).clear();
    }

    public void navigateTo(String link) {
        driver.get(link);
    }
//    public void waitForPageLoad() {
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(new Function<WebDriver, Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                System.out.println("Current Window State       : "
//                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
//                return String
//                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
//                        .equals("complete");
//            }
//        });
//    }

    public String getPageTitle() {
        //wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//title"))));
        return driver.getTitle();
    }

    public Float getItemPrice(String itemXpath) {
        String price = driver.findElement(By.xpath(itemXpath)).getText();
        price = price.replaceAll(" ", "");
        price = price.replaceAll("₴", "");
        return Float.parseFloat(price);
    }

    public Float getItemPrice(WebElement element) {
        String price = element.getText();
        price = price.replaceAll(" ", "");
        price = price.replaceAll("₴", "");
        return Float.parseFloat(price);
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public void browserNavigateBack() {
        driver.navigate().back();
    }

    public void deleteItemsFromComparisonList() {
        driver.findElement(By.xpath("//button[contains(@class,'comparison-modal__remove')]")).click();

        //Чомусь у другому тесті не відбувся клік кнопки видалення елементів зі списку порівняння, тому тест падав
        //з Timeout тому що та кнопка не пропадала зі сторінки (коли видаляються елементи - кнопка пропадає)
        // хоча при відладці все було ок, елемент знаходило той що треба, добавив такий обробник,
        // буду вдячний за підказку що не так
        try {
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//button[contains(@class,'comparison-modal__remove')]"))));
        }
        catch (TimeoutException e){
            driver.findElement(By.xpath("//button[contains(@class,'comparison-modal__remove')]")).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//button[contains(@class,'comparison-modal__remove')]"))));
        }
        }

    public void waitForCategoryPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='catalog-heading ng-star-inserted']"))));
    }

    public void waitForCategoryPortalPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='portal__heading ng-star-inserted']"))));
    }

    public void openHomePage() {
        driver.get(ROZETKA_HOMEPAGE_ADDRESS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@class='menu-categories__link']"))));
    }


//    public WebElement getWebElement(String webElementXpath){
//        return driver.findElement(By.xpath(webElementXpath));
//    }
}

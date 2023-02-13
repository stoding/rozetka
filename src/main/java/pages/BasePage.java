package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final String CATALOG_PAGE_H1 = "//h1[@class='catalog-heading ng-star-inserted']";
    private static final String PORTAL_H1 = "//h1[@class='portal__heading ng-star-inserted']";
    private static final String MAIN_PAGE_SIDE_MENU = "//div[contains(@class,'menu-wrapper_state_static')]";
    private static final String HEADER_SEARCH_BUTTON = "//button[contains(@class,'search-form__submit')]";
    private static final String MAIN_PAGE_SIDE_MENU_CATEGORY_LINK = "//ul[contains(@class,'menu-categories_type_main')]//a[contains(@href,'%s')]";
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
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    public boolean isDisplayed(String webElementXpath) {
        try {
            return driver.findElement(By.xpath(webElementXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElementStaleness(String webElementXpath) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(webElementXpath))));
    }

    public void fieldClear(String webElementXpath) {
        driver.findElement(By.xpath(webElementXpath)).clear();
    }

    public void navigateTo(String link) {
        driver.get(link);
    }

    public String getPageTitle() {
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

    public void waitForCategoryPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(CATALOG_PAGE_H1))));
    }

    public void waitForCategoryPortalPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(PORTAL_H1))));
    }

    public void openHomePage() {
        driver.get(ROZETKA_HOMEPAGE_ADDRESS);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MAIN_PAGE_SIDE_MENU))));
    }

    public void clickSearchButton() {
        driver.findElement(By.xpath(HEADER_SEARCH_BUTTON)).click();
    }

    public void openCategory(String categoryLinkContains) {
        driver.findElement(By.xpath(String.format(MAIN_PAGE_SIDE_MENU_CATEGORY_LINK, categoryLinkContains))).click();
        waitForCategoryPortalPageLoaded();
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

}

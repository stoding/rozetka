package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public class BasePage {
    public static WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed(String webElementXpath) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(webElementXpath)))).isDisplayed();
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

    public static void navigateTo(String link) {
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
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//title"))));
        return driver.getTitle();
    }
    public Float getItemPrice(String itemXpath) {
        String price = driver.findElement(By.xpath(itemXpath)).getText();
        price = price.replaceAll(" ","");
        price = price.replaceAll("₴","");
        return Float.parseFloat(price);
    }
    public Float getItemPrice(WebElement element) {
        String price = element.findElement(By.xpath("//span[@class='goods-tile__price-value']")).getText();
        price = price.replaceAll(" ","");
        price = price.replaceAll("₴","");
        return Float.parseFloat(price);
    }
    public static String getPageURL() {
        return driver.getCurrentUrl();
    }
    public static void browserNavigateBack(){
        driver.navigate().back();
    }

//    public WebElement getWebElement(String webElementXpath){
//        return driver.findElement(By.xpath(webElementXpath));
//    }
}

package pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    protected static final String CATALOG_PAGE_H1 = "//h1[@class='catalog-heading ng-star-inserted']";
    private static final String PORTAL_H1 = "//h1[@class='portal__heading ng-star-inserted']";
    private static final String HEADER_SEARCH_BUTTON = "//button[contains(@class,'search-form__submit')]";
    private static final String MAIN_PAGE_SIDE_MENU_CATEGORY_LINK = "//ul[contains(@class,'menu-categories_type_main')]//a[contains(@href,'%s')]";

    public static final String ROZETKA_HOMEPAGE_ADDRESS = "https://rozetka.com.ua/";

    public void navigateTo(String link) {
        open(link);
    }

    public String getPageTitle() {
        return title();
    }

    public Float getItemPrice(String itemXpath) {

        String price = $x(itemXpath).getText();
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
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public void openHomePage() {
        open(ROZETKA_HOMEPAGE_ADDRESS);
    }

    public void clickSearchButton() {
        $x(HEADER_SEARCH_BUTTON).click();
    }

    public void openCategory(String categoryLinkContains) {
        $x(String.format(MAIN_PAGE_SIDE_MENU_CATEGORY_LINK, categoryLinkContains)).click();
        $x(PORTAL_H1).shouldBe(visible);
    }

    public void clearCookies() {
        clearBrowserCookies();
    }

}

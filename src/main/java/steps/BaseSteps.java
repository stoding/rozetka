package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.*;

public class BaseSteps {
    public BasePage basePage;
    public Homepage homepage;
    public CategoryPage categoryPage;
    public ComparisonPage comparisonPage;
    public ItemPage itemPage;
    public ShoppingCartPage shoppingCartPage;

    public BaseSteps(WebDriver driver) {
        this.homepage = new Homepage(driver);
        this.categoryPage = new CategoryPage(driver);
        this.comparisonPage = new ComparisonPage(driver);
        this.itemPage = new ItemPage(driver);
        this.shoppingCartPage = new ShoppingCartPage(driver);
        this.basePage = new BasePage(driver);
    }

    public <T extends BasePage>void navigateTo(String link) {
        T.navigateTo(link);
    }
    public void navigateToHomePage() {
        homepage.openHomePage();
    }

    public <T extends BasePage> String getPageURL() {
        return T.getPageURL();
    }
//    public <T extends BasePage> WebElement getWebElement(String webElementXpath) {
//        return T.getWebElement(webElementXpath);
//    }
//    public WebElement getWebElement(String webElementXpath) {
//        return shoppingCartPage.getWebElement(webElementXpath);
//    }

}

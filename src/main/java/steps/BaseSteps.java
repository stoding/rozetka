package steps;

import org.openqa.selenium.WebDriver;
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

    public void navigateTo(String link) {
        basePage.navigateTo(link);
    }

    public void navigateToHomePage() {
        homepage.openHomePage();
    }

    public String getPageURL() {
        return basePage.getPageURL();
    }

    public void openCategory(String categoryLinkContains){
        basePage.openCategory(categoryLinkContains);
    }
}

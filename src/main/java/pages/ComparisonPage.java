package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ComparisonPage extends BasePage {
    public ComparisonPage(WebDriver driver) {
        super(driver);
    }


    public String itemToCompareDisplayed() {
        return driver.findElement(By.xpath("//li[@class='products-grid__cell ng-star-inserted']//a")).getText();
    }

    public void openAllItemSpecsOnComparisonPage() {
        driver.findElement(By.xpath("//button[@class='button button--medium button--with-icon button--link comparison-settings__button comparison-settings__toggle ng-star-inserted']")).click();
    }

//    public String getItemTitle() {
//        return driver.findElements(By.xpath("//a[@class='product__heading']"));
//
//    }

    public Integer getComparedItemsQuantity() {
        return driver.findElements(By.xpath("//a[@class='product__heading']")).size();
    }

    public String getItemTitle(Integer itemNumberOnPage) {
        return driver.findElement(By.xpath("(//a[@class='product__heading'])[" + itemNumberOnPage + "]")).getText();
    }

    public String getItemScreenResolution(Integer itemNumberOnPage) {
        return driver.findElement(By.xpath("(//h3[contains(text(),'Разрешение дисплея')]/..//span)[" + itemNumberOnPage + "]")).getText();
    }

    public Integer getItemNumberOfSupportedSimCards(Integer itemNumberOnPage) {
        return Integer.parseInt(driver.findElement(By.xpath("(//h3[contains(text(),'Количество SIM')]/..//span)[" + itemNumberOnPage + "]")).getText());
    }

    @Override
    public Float getItemPrice(String itemXpath) {
        WebElement parentElement = driver.findElement(By.xpath(itemXpath));
        String originalText = parentElement.getText();
        List<WebElement> children = parentElement.findElements(By.xpath("*[string-length(text()) > 0]"));
        for (WebElement element : children)
            originalText = originalText.replace(element.getText(), "");

        originalText = originalText.replaceAll(" ", "");
        return Float.parseFloat(originalText);
    }
}

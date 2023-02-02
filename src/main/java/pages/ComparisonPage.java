package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ComparisonPage extends BasePage {
    public ComparisonPage(WebDriver driver) {
        super(driver);
    }


    public String getItemTitleOnComparisonPage() {
        return driver.findElement(By.xpath("//li[@class='products-grid__cell ng-star-inserted']//a")).getText();
    }

    public void openAllItemSpecsOnComparisonPage() {
        driver.findElement(By.xpath("//button[(contains(@class,'comparison-settings__toggle'))]" + "")).click();
    }

    public Integer getComparedItemsQuantity() {
        return driver.findElements(By.xpath("//a[@class='product__heading']")).size();
    }

    public String getItemTitle(Integer itemNumberOnPage) {
        return driver.findElement(By.xpath(String.format("(//a[@class='product__heading'])[%s]",itemNumberOnPage))).getText();
    }

    public String getItemScreenResolution(Integer itemNumberOnPage) {
        return driver.findElement(By.xpath(String.format("(//h3[contains(text(),'Разрешение дисплея')]/..//span)[%s]",itemNumberOnPage))).getText();
    }

    public Integer getItemNumberOfSupportedSimCards(Integer itemNumberOnPage) {
        return Integer.parseInt(driver.findElement(By.xpath(String.format("(//h3[contains(text(),'Количество SIM')]/..//span)[%s]",itemNumberOnPage))).getText());
    }
}

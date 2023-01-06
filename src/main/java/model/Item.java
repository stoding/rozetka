package model;

import org.openqa.selenium.WebElement;

import java.util.Objects;

public class Item {
    private String itemTitle;
    private Float itemPrice;
    private String itemScreenResolution;
    private Integer numberOfSupportedSimCards;
    private WebElement itemWebElement;

    public Item(String itemTitle, Float itemPrice, String itemScreenResolution, Integer numberOfSupportedSimCards) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemScreenResolution = itemScreenResolution;
        this.numberOfSupportedSimCards = numberOfSupportedSimCards;
    }

    public Item(String itemTitle, Float itemPrice, WebElement element) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemWebElement = element;
    }

    public WebElement getItemWebElement() {
        return itemWebElement;
    }

    public void setItemWebElement(WebElement itemWebElement) {
        this.itemWebElement = itemWebElement;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public String getItemScreenResolution() {
        return itemScreenResolution;
    }

    public Integer getNumberOfSupportedSimCards() {
        return numberOfSupportedSimCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemTitle, item.itemTitle) && Objects.equals(itemPrice, item.itemPrice) && Objects.equals(itemScreenResolution, item.itemScreenResolution) && Objects.equals(numberOfSupportedSimCards, item.numberOfSupportedSimCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemTitle, itemPrice, itemScreenResolution, numberOfSupportedSimCards);
    }
}

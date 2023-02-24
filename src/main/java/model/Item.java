package model;

import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

public class Item {
    private final String itemTitle;
    private final Float itemPrice;
    private String itemScreenResolution;
    private Integer numberOfSupportedSimCards;
    private SelenideElement itemWebElement;

    public Item(String itemTitle, Float itemPrice, String itemScreenResolution, Integer numberOfSupportedSimCards) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemScreenResolution = itemScreenResolution;
        this.numberOfSupportedSimCards = numberOfSupportedSimCards;
    }

    public Item(String itemTitle, Float itemPrice, SelenideElement element) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemWebElement = element;
    }

    public SelenideElement getItemWebElement() {
        return itemWebElement;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Float getItemPrice() {
        return itemPrice;
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

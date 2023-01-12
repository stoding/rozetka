package steps;

import org.openqa.selenium.WebDriver;

public class SortingSteps extends BaseSteps{
    public SortingSteps(WebDriver driver) {
        super(driver);
    }

    public void openHouseHoldItemsCategory() {
        homepage.clickHouseHoldItemsCategory();
    }

    public void openBlanketPage() {
        categoryPage.openBlanketPage();
    }

    public void sortByPriceAscClick() {
        categoryPage.clickSortingByPriceAsc();
    }

    public boolean sortByPriceAscIsAppliedOnPage() {
        return categoryPage.sortByPriceAscIsApplied(categoryPage.getItemsListOnPage());
    }

    public void openItemsListPageFromPagination(Integer numberOfPageInPagination) {
        categoryPage.clickPageInPaginationList(numberOfPageInPagination);
    }

    public void sortByNewArrivals() {
        categoryPage.clickSortingByNewArrivals();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean newItemsDisplayedFirst() {
        return categoryPage.newItemsDisplayedFirst(categoryPage.getItemsListOnPage());
    }

    public boolean noNewItemsOnNextPages() {
        boolean allItemsHasNewArrivalTag = true;
        int page = 2;
        while (allItemsHasNewArrivalTag){
            categoryPage.clickPageInPaginationList(page);
            page++;
        if(categoryPage.getItemsListOnPage().size() != categoryPage.getItemsNewArrivalsList().size())
            allItemsHasNewArrivalTag = false;
        }
        categoryPage.clickPageInPaginationList(page);
        return categoryPage.getItemsNewArrivalsList().size() == 0;
    }

    public void openHomeTextilePage() {
        categoryPage.openHomeTextilePage();
    }
}

package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Inject;
import pages.CategoryPage;

import static com.codeborne.selenide.Selectors.byXpath;

public class SortingSteps extends BaseSteps {

    @Inject
    private CategoryPage categoryPage;

    public void openBlanketPage() {
        categoryPage.openBlanketPage();
    }

    public void openHomeTextilePage() {
        categoryPage.openHomeTextilePage();
    }

    public void sortByPriceAsc() {
        categoryPage.clickSortingByPriceAsc();
    }

    public boolean isSortedByPriceAscIsAppliedOnPage() {
        Float currentElementPrice;
        Float previousElementPrice = 0f;
        ElementsCollection elementsListOnPage = categoryPage.getItemsListOnPage();
        for (SelenideElement element : elementsListOnPage) {
            currentElementPrice = categoryPage.getItemPrice(element.find(byXpath(".//span[@class='goods-tile__price-value']")));
            if (currentElementPrice < previousElementPrice)
                return false;
            previousElementPrice = currentElementPrice;
        }
        return true;
    }

    public void openItemsListPageFromPagination(int numberOfPageInPagination) {
        categoryPage.clickPageInPaginationList(numberOfPageInPagination);
    }

    public void sortByNewArrivals() {
        categoryPage.clickSortingByNewArrivals();
    }

    public boolean isNewItemsDisplayedFirst() {
        int itemsListPage = 1;
        while (true) {
            if (categoryPage.getItemsListOnPage().size() == categoryPage.getItemsNewArrivalsList().size()) {
                itemsListPage++;
                categoryPage.clickPageInPaginationList(itemsListPage);
            } else {
                System.out.println("current page: " + itemsListPage); //для відладки тесту
                return isNewItemsDisplayedFirstOnCurrentPage(categoryPage.getItemsListOnPage());
            }
        }
    }

    private boolean isNewItemsDisplayedFirstOnCurrentPage(ElementsCollection itemsListOnPage) {
        boolean itemWithoutNewTagPresent = false;
        int itemsQuantityWithNewArrivalsTag = categoryPage.getItemsNewArrivalsList().size();
        int itemsWithNoveltyTagCounter = 0;
        //Мабуть опишу тут логику: є List всіх товарів на сторінці, є кількість товарів з тегом "новинка"
        //Щоб не йти по всьому масиву елементів я додав лічильник - кількість товарів які ми вже зустріли з тегом "новинка"
        //Якщо нам не зустрівся елемент без тега "новинка" та лічильник нарахував ту ж саму кількість товарів, то повертаємо true
        //а якщо нам трапиться товар без тегу новинка, а лічильник ще не буде дорівнювати необхідної кількості - то викене false,
        //тобто ланцюжок товарів-новинок перервався, що є дефектом сортування
        //
        // UPD: Вже написав сценарій, але цей тепер тест падає))), ну з іншого боку теж добре.
        // На сторінці між елементами з новинками є елемент з тегом топ продаж
        for (SelenideElement item : itemsListOnPage
        ) {

            if (item.find(byXpath(".//span[contains(@class,'promo-label_type_novelty')]")).isDisplayed()) {
                itemsWithNoveltyTagCounter++;
                if (itemsWithNoveltyTagCounter == itemsQuantityWithNewArrivalsTag)
                    return true;
                if (itemWithoutNewTagPresent) {
                    System.out.println("Defect in element number: " + itemsWithNoveltyTagCounter + "\n" + itemsListOnPage.get(itemsWithNoveltyTagCounter-1).text());
                    return false;
                }
            } else itemWithoutNewTagPresent = true;
        }
        return false;
    }


}

package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SortingSteps extends BaseSteps {
    public SortingSteps(WebDriver driver) {
        super(driver);
    }


    public void openBlanketPage() {
        categoryPage.openBlanketPage();
    }

    public void openHomeTextilePage() {
        categoryPage.openHomeTextilePage();
    }

    public void sortByPriceAscClick() {
        categoryPage.clickSortingByPriceAsc();
    }

    public boolean isSortedByPriceAscIsAppliedOnPage() {
        Float currentElementPrice;
        Float previousElementPrice = 0f;
        List<WebElement> elementsListOnPage = categoryPage.getItemsListOnPage();
        for (WebElement element : elementsListOnPage) {
            currentElementPrice = categoryPage.getItemPrice(element.findElement(By.xpath(".//span[@class='goods-tile__price-value']")));
            if (currentElementPrice < previousElementPrice)
                return false;
            previousElementPrice = currentElementPrice;

        }
        return true;
    }

    public void openItemsListPageFromPagination(Integer numberOfPageInPagination) {
        categoryPage.clickPageInPaginationList(numberOfPageInPagination);
    }

    public void sortByNewArrivals() {
        categoryPage.clickSortingByNewArrivals();
    }

    public boolean isNewItemsDisplayedFirst() {
        int itemsListPage = 1;
        // Шукаємо сторінку де не всі елементи є новинками
        while (true) {
            if (categoryPage.getItemsListOnPage().size() == categoryPage.getItemsNewArrivalsList().size()) {
                itemsListPage++;
                categoryPage.clickPageInPaginationList(itemsListPage);
            } else {
                System.out.println("current page: " + itemsListPage); //для відладки тесту
                break;
            }
        }
        return isNewItemsDisplayedFirstOnCurrentPage(categoryPage.getItemsListOnPage());
    }

    private boolean isNewItemsDisplayedFirstOnCurrentPage(List<WebElement> itemsListOnPage) {
        boolean itemWithoutNewTagPresent = false;
        int itemsQuantityWithNewArrivalsTag = categoryPage.getItemsNewArrivalsList().size();
        int itemCounter = 0;
        //Мабуть опишу тут логику: є List всіх товарів на сторінці, є кількість товарів з тегом "новинка"
        //Щоб не йти по всьому масиву елементів я додав лічильник - кількість товарів які ми вже зустріли з тегом "новинка"
        //Якщо нам не зустрівся елемент без тега "новинка" та лічильник нарахував ту ж саму кількість товарів, то повертаємо true
        //а якщо нам трапиться товар без тегу новинка, а лічильник ще не буде дорівнювати необхідної кількості - то викене false,
        //тобто ланцюжок товарів-новинок перервався, що є дефектом сортування
        //
        // UPD: Вже написав сценарій, але цей тепер тест падає))), ну з іншого боку теж добре.
        // На сторінці між елементами з новинками є елемент з тегом топ продаж
        for (WebElement item : itemsListOnPage
        ) {
            //перевіряю через findelements щоб не падав тест з ексепшн коли елементу не буде

            if (item.findElements(By.xpath(".//span[contains(@class,'promo-label_type_novelty')]")).size() != 0) {
                itemCounter++;
                if (itemCounter == itemsQuantityWithNewArrivalsTag)
                    return true;
                if (itemWithoutNewTagPresent) {
                    System.out.println("Defect in element number: " + itemCounter);
                    return false;
                }
            } else itemWithoutNewTagPresent = true;
        }
        return false;
    }


}

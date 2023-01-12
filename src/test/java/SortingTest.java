import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.BaseSteps;
import steps.SortingSteps;

import static org.assertj.core.api.Assertions.*;

public class SortingTest extends BaseTest {

    @BeforeMethod
    public void goToHomePage() {
        SortingSteps sortingSteps = new SortingSteps(driver);
        sortingSteps.navigateToHomePage();
        System.out.println("Before Method");
    }

    @Test
    public void sortingMethodSavedAfterSwitchingPage() {
        SortingSteps sortingSteps = new SortingSteps(driver);
        sortingSteps.openHouseHoldItemsCategory();
        assertThat(sortingSteps.getPageURL()).contains("tovary-dlya-doma");
        sortingSteps.openBlanketPage();
        assertThat(sortingSteps.getPageURL()).contains("blankets");
        sortingSteps.sortByPriceAscClick();
        assertThat(sortingSteps.sortByPriceAscIsAppliedOnPage()).isTrue();
        sortingSteps.openItemsListPageFromPagination(3);
        assertThat(sortingSteps.sortByPriceAscIsAppliedOnPage()).isTrue();
    }

    @Test
    public void noNewItemsOnNextPageIfCurrentPageHasItemWithoutNewTag() {
        SortingSteps sortingSteps = new SortingSteps(driver);
        //Я віришив відійти від степів оригінального тесткейсу, так як не можна було перевірити
        //як відпрацьовує тест, через те що в категорії не було достатньо новинок. знайшов іншу категорію де багато новинок
//        sortingSteps.openComputerCategory();
//        assertThat(sortingSteps.getPageURL()).contains("computers-notebooks");
//        sortingSteps.openTabletCategory();
//        assertThat(sortingSteps.getPageURL()).contains("tablets");
        sortingSteps.openHouseHoldItemsCategory();
        sortingSteps.openHomeTextilePage();
        sortingSteps.sortByNewArrivals();
        //перевірка що першими у списку йдуть новинки
        assertThat(sortingSteps.newItemsDisplayedFirst()).isTrue();
        //перевірка що наступна сторінка після сторінки де не всі елементи є новинками, не містить товарів с тегов новинка
        assertThat(sortingSteps.noNewItemsOnNextPages()).isTrue();
    }

}

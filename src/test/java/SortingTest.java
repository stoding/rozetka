import com.google.inject.Inject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.SortingSteps;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

public class SortingTest extends BaseTest {

    @Inject
    SortingSteps sortingSteps;

    @BeforeMethod
    public void goToHomePage() {
        open(ROZETKA_HOMEPAGE_URL);
    }

    @Test
    public void sortingMethodSavedAfterSwitchingPage() {
        sortingSteps.openCategory("tovary-dlya-doma");
        assertThat(sortingSteps.getPageURL()).contains("tovary-dlya-doma");
        sortingSteps.openBlanketPage();
        assertThat(sortingSteps.getPageURL()).contains("blankets");
        sortingSteps.sortByPriceAsc();
        assertThat(sortingSteps.isSortedByPriceAscIsAppliedOnPage()).isTrue();
        sortingSteps.openItemsListPageFromPagination(3);
        assertThat(sortingSteps.isSortedByPriceAscIsAppliedOnPage()).isTrue();
    }

    @Test
    public void noNewItemsOnNextPageIfCurrentPageHasItemWithoutNewTag() {
        sortingSteps.openCategory("tovary-dlya-doma");
        sortingSteps.openHomeTextilePage();
        sortingSteps.sortByNewArrivals();
        assertThat(sortingSteps.isNewItemsDisplayedFirst()).isTrue();
    }

}

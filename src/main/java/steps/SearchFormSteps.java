package steps;

import model.SearchField;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchFormSteps extends BaseSteps {
    public SearchFormSteps(WebDriver driver) {
        super(driver);
    }

    public void inputSearchQuery(String input) {
        homepage.inputSearchQuery(input);
    }

    public List<WebElement> getSuggestionList(){
        return homepage.getSearchSuggestionList();
    }

    public boolean searchSuggestionListContainsString(List<WebElement> elementsList, String expectedString) {
        for (WebElement element : elementsList
        ) {
            if (element.getText().contains(expectedString))
                return true;
        }
        return false;
    }

    public void searchFieldClear() {
        homepage.fieldClear("//*[@name='search']");
    }

    public void waitForSearchResultUpdate() {
        homepage.waitForElementStaleness("//div[@class='search-suggest ng-star-inserted']//a");
    }

    public List<SearchField> getSuggestedCategoryNameAndLink(List<WebElement> suggestionList) {
        List<SearchField> categoryParameters = new ArrayList<>();
        for (WebElement element: suggestionList
             ) {
            categoryParameters.add(new SearchField(element.getText(), element.getAttribute("href")));

        }
        return categoryParameters;
    }

    public String getCategoryPageBreadCrumbs() {
        return categoryPage.getBreadCrumbs();
    }



    //    public boolean compareCategories (List<CategoryParameters> categoryParameters){
//
//        for (CategoryParameters suggestedCategory: categoryParameters
//             ) {
//            homepage.navigateTo(suggestedCategory.getCategoryLink());
//            categoryPage.getBreadCrumbs();
//            if (categoryPage.getBreadCrumbs().contains(suggestedCategory.getCategoryName()))
//                return true;
//        }
//        return false;
//    }
    //Вирішив зробити вкладений клас, бо здається ці об'єкти більше ніде не будуть використовуватися

}

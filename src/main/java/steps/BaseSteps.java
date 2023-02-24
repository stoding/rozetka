package steps;

import com.google.inject.Inject;
import pages.*;
import org.testng.annotations.Guice;

@Guice
public class BaseSteps {
    @Inject
    private BasePage basePage;
    @Inject
    private Homepage homepage;

    public void navigateTo(String link) {
        basePage.navigateTo(link);
    }

    public void navigateToHomePage() {
        basePage.openHomePage();
    }

    public String getPageURL() {
        return basePage.getPageURL();
    }

    public void openCategory(String categoryLinkContains) {
        basePage.openCategory(categoryLinkContains);
    }
}

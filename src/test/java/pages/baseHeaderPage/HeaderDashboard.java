package pages.baseHeaderPage;

import baseEntities.BasePage;
import core.BrowsersService;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.TestCasesPage;
import wrappers.Button;

@Log4j2
public class HeaderDashboard extends BasePage {

    private final static By LOGO_LABEL = By.id("top-logo");
    private final static By ADMINISTRATION_BUTTON = By.id("navigation-admin");
    private final static By DASHBOARD_TEST_CASE_TITLE = By.id("navigation-suites");
    private final static By RETURN_DASHBOARD_PAGE = By.id("navigation-dashboard");

    public HeaderDashboard(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getLogoLabel().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getLogoLabel() {
        return browsersService.getWaiters().waitForVisibility(LOGO_LABEL);
    }

    private Button getReturnDashboardButton() {
        return new Button(browsersService, RETURN_DASHBOARD_PAGE);
    }

    private Button getAdministrationButton() {
        return new Button(browsersService, ADMINISTRATION_BUTTON);
    }

    private Button getDashboardTestCaseButton() {
        return new Button(browsersService, DASHBOARD_TEST_CASE_TITLE);
    }

    @Step("Click on the Administration button and go to the Overview Page")
    public AdministrationSidebar clickAdministrationButton() {
        log.info("Step: Click on the Administration button and go to the Overview Page");
        getAdministrationButton().click();
        return new AdministrationSidebar(browsersService, false);
    }

    @Step("Click on the Dashboard button and go to the Dashboard Page")
    public DashboardPage clickReturnDashboardPageButton() {
        log.info("Step: Click on the Dashboard button and go to the Dashboard Page");
        getReturnDashboardButton()
                .click();
        return new DashboardPage(browsersService, false);
    }

    @Step("Click on dashboard Test Case button and go to the Test cases page")
    public TestCasesPage clickDashboardTestCaseButton() {
        log.info("Step: Click on dashboard Test Case button and go to the Test cases page");
        getDashboardTestCaseButton()
                .click();
        return new TestCasesPage(browsersService, false);
    }
}

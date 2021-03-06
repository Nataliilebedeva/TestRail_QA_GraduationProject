package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

@Log4j2
public class TestCasesPage extends BasePage {

    private final static String ENDPOINT = "index.php?/suites/view/%d";

    private final static By TEST_CASES_TITLE = By.cssSelector(".page_title");
    private final static By ADD_TEST_CASE_BUTTON = By.id("sidebar-cases-add");
    private final static By OPEN = By.id("sidebar-cases-add");

    public TestCasesPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTestCasesInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getTestCasesInstallationName() {
        return browsersService.getWaiters().waitForVisibility(TEST_CASES_TITLE);
    }

    private WebElement getAddTestCaseButton() {
        return browsersService.getWaiters().waitForVisibility(ADD_TEST_CASE_BUTTON);
    }

    @Step("Click on the Add Test Case button and go to the Add Test Case Page")
    public AddEditTestCasePage clickAddTestCaseButton() {
        log.info("Step: Click on the Add Test Case button and go to the Add Test Case Page");
        ((JavascriptExecutor) browsersService.getDriver()).executeScript("arguments[0].click();", getAddTestCaseButton());
        return new AddEditTestCasePage(browsersService, false);
    }
}

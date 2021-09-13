package pages;

import core.BrowsersService;
import core.ReadProperties;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.baseHeaderPage.HeaderDashboard;
import wrappers.Button;
import wrappers.UIElement;

import java.util.List;

public class DashboardPage extends HeaderDashboard {
    Actions actions;

    private final static String ENDPOINT = "/index.php?/dashboard";

    private final static By DASHBOARD_PAGE_TITLE = By.className("content-header-title");
    private final static By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private final static By PROJECTS_LIST = By.xpath("//table[@class = 'grid']//tr[@class != 'header']//td[3]//a[1]");
    private final static By ICON_FOR_POPUP = By.className("icon-display-small");
    private final static By TITLE_POPUP_MESSAGE = By.className("tooltip-header");

    private final static String OPEN_SOME_PROJECT_BUTTON = "//*[@class='grid']//a[contains(text(), '%s')]";


    public DashboardPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getDashboardPageInstallationName().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getDashboardPageInstallationName() {
        return browsersService.getWaiters().waitForVisibility(DASHBOARD_PAGE_TITLE);
    }

    private List<WebElement> getProjectList() {
        return browsersService.getDriver().findElements(PROJECTS_LIST);
    }

    private Button getAddProjectButton() {
        return new Button(browsersService, ADD_PROJECT_BUTTON);
    }

    private UIElement getSomeProjectHref(String nameProject) {
        return new UIElement(browsersService, By.xpath(String.format(OPEN_SOME_PROJECT_BUTTON, nameProject)));
    }

    private WebElement getIconForPopUp() {
        return browsersService.getWaiters().waitForVisibility(ICON_FOR_POPUP);
    }

    private WebElement getTitlePopUpMessage() {
        return browsersService.getWaiters().waitForVisibility(TITLE_POPUP_MESSAGE);
    }

    public AddProjectPage clickAddProjectButton() {
        getAddProjectButton()
                .click();
        return new AddProjectPage(browsersService, false);
    }

    public SomeProjectPage clickSomeProjectPage(String nameProject) {
        getSomeProjectHref(nameProject)
                .click();
        return new SomeProjectPage(browsersService, false);
    }

    //**************нажимаем на название проекта в таблице
    public SomeProjectPage clickProjectLink(Project project) {

        for (WebElement element : getProjectList()) {
            if (element.getText().equalsIgnoreCase(project.getName())) {
                element.click();
                return new SomeProjectPage(browsersService, false);
            }
        }
        throw new NoSuchElementException("Project not found");
    }

    /***
     * Оставляем это или нет?
     */
//********************метод поиска в таблице проекта
    public boolean presentInTheTable(Project project) {
        if (project == null) {
            return false;
        }

        try {
            for (WebElement element : getProjectList()) {
                if (element.getText().equalsIgnoreCase(project.getName())) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            //logger.info("Project table is empty")
        }
        return false;
    }

    public DashboardPage actionForPopUp() {
        actions = new Actions(browsersService.getDriver());
        actions
                .moveToElement(getIconForPopUp())
                .build()
                .perform();
        return this;
    }

    public String getPopUpMessageTitleText() {
        return getTitlePopUpMessage().getText();
    }
}
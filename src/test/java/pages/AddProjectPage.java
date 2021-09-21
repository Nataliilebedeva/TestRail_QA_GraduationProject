package pages;

import baseEntities.BasePage;
import core.BrowsersService;
import core.ReadProperties;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.CheckBox;
import wrappers.InputField;
import wrappers.RadioButton;

@Log4j2
public class AddProjectPage extends BasePage {

    private final static String ENDPOINT = "index.php?/admin/projects/add/%d";

    private final static By ADD_PROJECT_PAGE_TITLE = By.className("content-header-title");
    private final static By NAME_PROJECT_INPUT = By.id("name");
    private final static By ANNOUNCEMENT_PROJECT_INPUT = By.id("announcement");
    private final static By SHOW_ANNOUNCEMENT_PROJECT_CHECK_BOX = By.id("show_announcement");
    private final static By SUITE_MODE_RADIO_BUTTON = By.name("suite_mode");
    private final static By ADD_PROJECT_BUTTON = By.id("accept");

    public AddProjectPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + ENDPOINT);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getAddProjectPageTitle().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getAddProjectPageTitle() {
        return browsersService.getWaiters().waitForVisibility(ADD_PROJECT_PAGE_TITLE);
    }

    private InputField getNameProjectInput() {
        return new InputField(browsersService, NAME_PROJECT_INPUT);
    }

    private InputField getAnnouncementProjectInput() {
        return new InputField(browsersService, ANNOUNCEMENT_PROJECT_INPUT);
    }

    private CheckBox getShowAnnouncementProjectCheckBox() {
        return new CheckBox(browsersService, SHOW_ANNOUNCEMENT_PROJECT_CHECK_BOX);
    }

    private RadioButton getRadioButtonAddProject() {
        return new RadioButton(browsersService, SUITE_MODE_RADIO_BUTTON);
    }

    private Button getAddProjectButton() {
        return new Button(browsersService, ADD_PROJECT_BUTTON);
    }

    private AddProjectPage inputNameProject(String nameProject) {
        getNameProjectInput()
                .sendKeys(nameProject);
        return this;
    }

    private AddProjectPage inputAnnouncementProjectInput(String announcementProject) {
        getAnnouncementProjectInput()
                .sendKeys(announcementProject);
        return this;
    }

    private AddProjectPage setShowAnnouncementProjectCheckBox(boolean makeSelected) {
        getShowAnnouncementProjectCheckBox()
                .changeState(makeSelected);
        return this;
    }

    private AddProjectPage setRadioButtonAddProject(int indexSuiteMode) {
        getRadioButtonAddProject()
                .selectByIndex(indexSuiteMode);
        return this;
    }

    private void clickAddProjectButton() {
        getAddProjectButton()
                .click();
    }

    @Step("Fill the project attributes with random values {project}, " +
            "click on the button Add Project and go to the Administration AllProjects Page")
    public AdministrationProjectsPage addProject(Project project) {
        log.info("Step: Fill the project attributes with random values {project}, " +
                "click on the button Add Project and go to the Administration AllProjects Page");
        inputNameProject(project.getName())
                .inputAnnouncementProjectInput(project.getAnnouncement())
                .setShowAnnouncementProjectCheckBox(project.isShow_announcement())
                .setRadioButtonAddProject(project.getSuite_mode())
                .clickAddProjectButton();
        return new AdministrationProjectsPage(browsersService, false);
    }
}

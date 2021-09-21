package pages;

import core.BrowsersService;
import core.ReadProperties;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.baseHeaderPage.HeaderDashboard;
import pages.conformationPages.ConfirmationDeleteWindow;
import wrappers.table.Table;

@Log4j2
public class AdministrationProjectsPage extends HeaderDashboard {
    private static Table table;
    private static final String endpoint = "/index.php?/admin/projects/overview";

    private final static By PROJECTS_PAGE_TITLE = By.xpath("//*[@class = 'content-header-title page_title']");
    private final static By TABLE = By.xpath("//table[@class = 'grid']");


    public AdministrationProjectsPage(BrowsersService browsersService, boolean openPageByUrl) {
        super(browsersService, openPageByUrl);

        table = new Table(browsersService, browsersService.getDriver().findElement(TABLE));
    }

    @Override
    protected void openPage() {
        browsersService.getDriver().get(ReadProperties.getInstance().getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getProjectPageTitle().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private WebElement getProjectPageTitle() {
        return browsersService.getDriver().findElement(PROJECTS_PAGE_TITLE);
    }

    @Step("Delete project {project}")
    public AdministrationProjectsPage deleteProject(Project project) {
        log.info("Step: Delete project {project}");
        table.getDeleteCell(project.getName()).click();
        return new ConfirmationDeleteWindow(browsersService)
                .checkBoxDelete()
                .clickButtonOk();
    }

    @Step("Open Conformation Delete Window before delete project")
    public ConfirmationDeleteWindow openConformationDeleteWindow(Project project) {
        log.info("Step: Open Conformation Delete Window before delete project");
        table.getDeleteCell(project.getName()).click();
        return new ConfirmationDeleteWindow(browsersService);
    }

    public boolean projectIsFound(Project project) {
        return table.presentInTheTable(project.getName());
    }
}

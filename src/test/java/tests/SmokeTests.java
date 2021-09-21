package tests;

import baseEntities.BaseUITest;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.ModelsFactory;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEditTestCasePage;
import pages.AdministrationProjectsPage;
import pages.LoginPage;

import java.awt.*;

@Log4j2
public class SmokeTests extends BaseUITest {

    @Description("Positive test for deleting a project")
    @Test
    public void positiveDeleteProjectTest() {
        log.info("Test in progress: SmokeTests.positiveDeleteProjectTest()");
        AdministrationProjectsPage adminPage = new LoginPage(browsersService, true)
                .successfulLogin()
                .clickAdministrationButton()
                .clickProjectsButton()
                .deleteProject(project);

        Assert.assertThrows(java.lang.NullPointerException.class, () -> adminPage.deleteProject(project));
    }
}

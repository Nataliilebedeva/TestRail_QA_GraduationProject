package tests;

import adapters.CasesAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import baseEntities.BaseApiTest;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import models.Cases;
import models.ModelsFactory;
import models.Project;
import models.Section;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class TestRailApiExecutionTest extends BaseApiTest {
    int numberOfCases = 3;

    @Description("API test for creating a Test Case by POST request. " +
            "This test include step creating project and section by POST request")
    @Test
    public void addCasesTest() {
        log.info("Test in progress: TestRailApiExecutionTest.addCasesTest()");
        Project projectModels = ModelsFactory.getProject();

        project = new ProjectsAdapter().add(projectModels);

        Section sectionModels = ModelsFactory.getSection();

        currentSection = new SectionAdapter().add(sectionModels, project.getId());

        for (int count = 1; count <= numberOfCases; count++) {
            Cases casesModels = ModelsFactory.getCases();
            actualCasesList.add(new CasesAdapter().add(casesModels, currentSection.getId()));
        }
    }

    @Description("API test for getting a Test Cases by GET request")
    @Test(dependsOnMethods = "addCasesTest")
    public void getCasesTest() {
        log.info("Test in progress: TestRailApiExecutionTest.getCasesTest()");
        Cases actualCases = actualCasesList.get(0);
        Cases cases = new CasesAdapter().get(actualCases);

        Assert.assertEquals(actualCases.getTitle(), cases.getTitle());
    }

    @Description("Negative API test for getting a Test Cases by incorrect GET request")
    @Test(dependsOnMethods = "getCasesTest")
    public void negativeGetCasesTest() {
        log.info("Test in progress: TestRailApiExecutionTest.negativeGetCasesTest()");
        Cases actualCases = ModelsFactory.getCases();
        new CasesAdapter().getFailed(actualCases);

    }

    @Description("API test for getting Test Cases History by GET request")
    @Test(dependsOnMethods = "negativeGetCasesTest")
    public void getHistoryForCases() {
        log.info("Test in progress: TestRailApiExecutionTest.getHistoryForCases()");
        Cases actualCases = actualCasesList.get(0);
        new CasesAdapter().getHistory(actualCases);
    }

    @Description("Negative API test for getting Test Cases History by incorrect GET request")
    @Test(dependsOnMethods = "getHistoryForCases")
    public void negativeGetHistoryForCasesTest() {
        log.info("Test in progress: TestRailApiExecutionTest.negativeGetHistoryForCasesTest()");
        Cases actualCases = ModelsFactory.getCases();
        new CasesAdapter().getHistoryFailed(actualCases);
    }

    @Description("API test for updating Test Cases by POST request")
    @Test(dependsOnMethods = "negativeGetHistoryForCasesTest")
    public void updateCaseTest() {
        log.info("Test in progress: TestRailApiExecutionTest.updateCaseTest()");
        Cases expected_cases = Cases.builder()
                .title("TITLE №00")
                .refs("Какое-то обновленное поле...")
                .build();

        Cases actual_case = new CasesAdapter().updateCase(actualCasesList.get(0).getId(), expected_cases);
        Assert.assertEquals(expected_cases.getTitle(), actual_case.getTitle());
        log.info("Project name:" + project.getName());
    }
}


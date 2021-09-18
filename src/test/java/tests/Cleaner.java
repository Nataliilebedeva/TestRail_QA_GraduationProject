package tests;

import apiRequest.adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.Project;
import org.testng.annotations.Test;
import java.util.List;

public class Cleaner extends BaseApiTest {
    @Test
    public void cleanerTestRail(){
        List<Project> projectList;

        projectList = new ProjectsAdapter().get();

        for (Project project : projectList){
            new ProjectsAdapter().delete(project.getId());
        }
    }
}

package baseEntities;

import models.Cases;
import models.Project;
import models.Section;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {
    public static Project project;
    public static Cases cases;
    public static Section currentSection;
    public static List<Cases> actualCaseslist = new ArrayList<>();
    public BaseTest(){
        System.out.println("Запускаем BaseTest");
    }
}

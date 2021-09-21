package wrappers.table;

import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.UIElement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private UIElement uiElement;
    private List<TableRow> tableRowList = new ArrayList<>();

    public Table(BrowsersService browsersService, WebElement webElement) {
        uiElement = new UIElement(browsersService, webElement);

        for (WebElement element : uiElement.findElements(By.xpath("//tr[@class != 'header']")))
            tableRowList.add(new TableRow(browsersService, element));

    }

    public UIElement getDeleteCell(String projectName) {
        return getRowByText(projectName).getCellByIndex(3);
    }

    public UIElement getEditCell(String projectName) {
        return getRowByText(projectName).getCellByIndex(2);
    }

    private TableRow getRowByText(String projectName) {
        for (TableRow row : tableRowList) {
            if (row.getCellByIndex(1).findElement(By.xpath("./a[1]")).getText().equalsIgnoreCase(projectName))
                return row;
        }
        throw new NullPointerException("Project not found");
    }

    public boolean presentInTheTable(String projectName) {
        if (projectName == null) {
            return false;
        }

        for (TableRow row : tableRowList) {
            if (row.getCellByIndex(1).findElement(By.xpath("./a[1]")).getText().equalsIgnoreCase(projectName)) {
                System.out.println(row.getCellByIndex(1).findElement(By.xpath("./a[1]")).getText());
                return true;
            }
        }
        return false;
    }
}

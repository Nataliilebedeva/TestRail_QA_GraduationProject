package wrappers.table;

import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.UIElement;

import java.util.ArrayList;
import java.util.List;

public class TableRow {
    private UIElement uiElement;
    private List<Cell> tableCellList = new ArrayList<>();

    public TableRow(BrowsersService browsersService, WebElement webElement) {
        this.uiElement = new UIElement(browsersService, webElement);

        for (WebElement element : this.uiElement.findElements(By.xpath("./td")))
            tableCellList.add(new Cell(browsersService, element));
    }

    protected UIElement getCellByIndex(int index) {
        if (!(index > 0 & index <= 3)) {
            return null;
        }            // добавить логгер error
        return tableCellList.get(index - 1).getUIElement();
    }
}

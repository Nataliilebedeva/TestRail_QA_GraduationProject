package wrappers.table;


import core.BrowsersService;
import org.openqa.selenium.WebElement;
import wrappers.UIElement;

public class Cell {
    private UIElement uiElement;

    public Cell(BrowsersService browsersService, WebElement webElement){
        uiElement = new UIElement(browsersService, webElement);
    }

    public UIElement getUIElement() {
        return uiElement;
    }
}

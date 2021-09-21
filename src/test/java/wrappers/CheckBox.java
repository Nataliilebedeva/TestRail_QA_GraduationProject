package wrappers;

import core.BrowsersService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private final UIElement element;

    public CheckBox(BrowsersService browsersService, By by) {
        this.element = new UIElement(browsersService, by);
    }

    public CheckBox(BrowsersService browsersService, WebElement element) {
        this.element = new UIElement(browsersService, element);
    }

    protected boolean isSelected() {
        return this.element.isSelected();
    }

    public void changeState(boolean makeSelected) {
        if (this.isSelected() != makeSelected) this.element.click();
    }
}

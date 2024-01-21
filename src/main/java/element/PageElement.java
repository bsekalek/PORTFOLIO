package element;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class PageElement {

    private final FluentWait<WebDriver> wait;
    private final By locator;

    public PageElement(By locator) {
        this.locator = locator;
        WebDriver driver = Driver.getInstance();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(Exception.class);
    }

    public void click() {
        wait.withMessage("Element %s wasn't clickable after 10 seconds")
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    public void typeText(String text) {
        getVisibleElement().sendKeys(text);
    }

    public void selectByText(String text) {
        new Select(getVisibleElement()).selectByVisibleText(text);
    }

    public String getText() {
        return getVisibleElement().getText();
    }

    public void selectByIndex(int index) {
        new Select(getVisibleElement()).selectByIndex(index);
    }

    public void selectByValue(String value) {
        new Select(getVisibleElement()).selectByValue(value);
    }

    public boolean isOptionAvailableInDropdown(String expectedOption) {
        return new Select(getVisibleElement())
                .getOptions()
                .stream()
                .anyMatch(option -> option.getText().contains(expectedOption));
    }

    private WebElement getVisibleElement() {
        return wait.withMessage("Element %s wasn't visible after 10 seconds".formatted(locator))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

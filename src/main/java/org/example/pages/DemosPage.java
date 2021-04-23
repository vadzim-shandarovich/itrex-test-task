package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents Demos page with its interactions
 */
public class DemosPage {
    private WebDriver driver;
    private By widgetTitles = By.cssSelector(".widget-title");
    private By spinnerLink = By.linkText("Spinner");

    public DemosPage(WebDriver driver) {
        this.driver = driver;
    }

    public SpinnerPage clickSpinnerLink() {
        driver.findElement(spinnerLink).click();
        return new SpinnerPage(driver);
    }

    public Set<String> getWidgetTitlesNames() {
        return driver.findElements(widgetTitles)
                .parallelStream().map(WebElement::getText).collect(Collectors.toSet());
    }
}

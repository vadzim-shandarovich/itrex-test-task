package org.example.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents Spinner page with its interactions
 */
public class SpinnerPage {
    private WebDriver driver;
    private By selectTextBox = By.id("spinner");
    private By getValueButton = By.id("getvalue");
    private By demoFrame = By.className("demo-frame");

    public SpinnerPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Clicks GetValueButton, accepts showing alert and returns alert value
     */
    public String clickGetValueButton() {
        String popupValue;
        WebElement demoFrameWE = driver.findElement(demoFrame);
        driver.switchTo().frame(demoFrameWE);
        driver.findElement(getValueButton).click();

        Alert alert = driver.switchTo().alert();
        popupValue = alert.getText();
        alert.accept();
        driver.switchTo().defaultContent();
        return popupValue;
    }

    public void enterSelectTextBoxValue(String value) {
        WebElement demoFrameWE = driver.findElement(demoFrame);
        driver.switchTo().frame(demoFrameWE);
        driver.findElement(selectTextBox).sendKeys(value);
        driver.switchTo().defaultContent();
    }
}

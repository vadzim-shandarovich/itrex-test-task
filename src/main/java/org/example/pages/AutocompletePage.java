package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Represents Autocomplete page with its interactions
 */
public class AutocompletePage {
    private WebDriver driver;
    private By demoFrame = By.className("demo-frame");
    private By tagsTextBox = By.id("tags");
    private By autocompleteListStyleBlock = By.cssSelector("#ui-id-1 [style*='block']");

    public AutocompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterTagsTextBoxValue(String value) {
        WebElement demoFrameWE = driver.findElement(demoFrame);
        driver.switchTo().frame(demoFrameWE);
        driver.findElement(tagsTextBox).sendKeys(value);
        driver.switchTo().defaultContent();
    }

    public String getTagsTextBoxValue() {
        String textBoxValue;

        WebElement demoFrameWE = driver.findElement(demoFrame);
        driver.switchTo().frame(demoFrameWE);
        textBoxValue = driver.findElement(tagsTextBox).getAttribute("value");
        driver.switchTo().defaultContent();
        return textBoxValue;
    }

    public void sendKeyToTagsTextBox(Keys keys) {
        WebElement demoFrameWE = driver.findElement(demoFrame);
        driver.switchTo().frame(demoFrameWE);
        driver.findElement(tagsTextBox).sendKeys(keys);
        driver.switchTo().defaultContent();
    }

    /*
     * Choose element in Autocomplete list by autocompleteListIndex
     */
    public void sendArrowDownTagsTextBox(int autocompleteListIndex) {
        WebElement demoFrameWE = driver.findElement(demoFrame);
        driver.switchTo().frame(demoFrameWE);

        WebElement tagsTextBoxWE = driver.findElement(tagsTextBox);
        tagsTextBoxWE.sendKeys(Keys.ARROW_DOWN);

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(300));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(autocompleteListStyleBlock));

        for (int i = 0; i < autocompleteListIndex; i++) {
            tagsTextBoxWE.sendKeys(Keys.ARROW_DOWN);
        }
        driver.switchTo().defaultContent();
    }
}

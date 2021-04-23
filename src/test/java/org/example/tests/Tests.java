package org.example.tests;

import com.aventstack.extentreports.Status;
import org.example.base.BaseTests;
import org.example.pages.SpinnerPage;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Contains task tests
 */
public class Tests extends BaseTests {

    @Test(description = "Verifies that left menu bar contains necessary sections")
    public void verifyLeftMenuBarSections() {
        Set<String> expectedSections = new HashSet<>(
                List.of("Interactions", "Widgets", "Effects", "Utilities"));
        Set<String> actualSections = demosPage.getWidgetTitlesNames();

        test = report.createTest("Left menu bar verification",
                "Verifies that left menu bar contains necessary sections");
        test.log(Status.INFO, "Navigated to Demos URL");
        test.log(Status.INFO, "Expected sections: " + expectedSections.toString());
        test.log(Status.INFO, "Actual sections: " + actualSections.toString());

        assertEquals(actualSections, expectedSections,
                "Actual sections do not correspond to expected sections");
    }

    @Test(description = "Verifies that Spinner page popup contains entered value")
    public void verifyPopupContainsEnteredValue() {
        final String ENTERED_VALUE = "10";
        String actualValue;

        test = report.createTest("Popup value verification",
                "Verifies that Spinner page popup contains entered value");

        SpinnerPage spinnerPage = demosPage.clickSpinnerLink();
        test.log(Status.INFO, "Navigated to Spinner URL");

        spinnerPage.enterSelectTextBoxValue(ENTERED_VALUE);
        test.log(Status.INFO, "Value " + ENTERED_VALUE + " was entered to the text box");

        actualValue = spinnerPage.clickGetValueButton();
        test.log(Status.INFO, "Actual value: " + actualValue);

        assertEquals(actualValue, ENTERED_VALUE,
                "Actual value do not correspond to entered sections");
    }
}

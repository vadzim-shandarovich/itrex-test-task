package org.example.demos;

import com.aventstack.extentreports.Status;
import org.example.base.BaseTests;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Contains task tests on Demos page
 */
public class DemosTests extends BaseTests {

    @Test(description = "Verifies left menu bar contains necessary sections")
    public void verifyLeftMenuBarSections() {
        Set<String> expectedSections = new HashSet<> (
                List.of("Interactions", "Widget", "Effects", "Utilities"));
        Set<String> actualSections = demosPage.getWidgetTitlesNames();

        test = report.createTest("Left menu bar verification",
                "Verifies left menu bar contains necessary sections");
        test.log(Status.INFO, "Navigated to Demos URL");
        test.log(Status.INFO, "Expected sections: " + expectedSections.toString());
        test.log(Status.INFO, "Actual sections: " + actualSections.toString());

        assertEquals(actualSections, expectedSections,
                "Actual sections do not correspond to expected sections");
    }
}

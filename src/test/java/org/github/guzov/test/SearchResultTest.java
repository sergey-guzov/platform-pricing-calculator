package org.github.guzov.test;

import junit.framework.Assert;
import org.github.guzov.page.GoogleCloudHomePage;
import org.testng.annotations.Test;

public class SearchResultTest extends CommonCondition {
    @Test
    public void searchForCalculatorPageTest ()
    {
        new GoogleCloudHomePage(driver)
                .openPage()
                .searchFor("Google Cloud Pricing Calculator")
                .selectResult();
        Assert.assertTrue("No match in URL",driver.getCurrentUrl().contains("calculator"));
    }
}


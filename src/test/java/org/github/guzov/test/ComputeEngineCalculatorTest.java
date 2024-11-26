package org.github.guzov.test;

import org.github.guzov.page.GoogleCloudHomePage;
import org.github.guzov.page.GoogleCloudCostEstimateSummaryPage;
import org.github.guzov.product.ComputeEngine;
import org.github.guzov.service.ComputeEngineCreator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ComputeEngineCalculatorTest extends CommonCondition {
    private GoogleCloudCostEstimateSummaryPage summaryPage;
    private ComputeEngine computeEngine;

    @BeforeClass(alwaysRun = true)
    public void createSummaryPage()
    {
        computeEngine = ComputeEngineCreator.withSettingsFromProperty();
        summaryPage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchFor("Google Cloud Pricing Calculator")
                .selectResult()
                .selectProduct(computeEngine)
                .setProductParameters()
                .openSummaryPage();
    }

    @Test(description = "operation system test")
    public void operationSystemTest()
    {
        Assert.assertTrue(summaryPage.getValueOf("Operating System / Software")
                .equals(computeEngine.getOperationSystem()), "Operation System doesn't match with selected");
    }

    @Test(description = "committed term test")
    public void committedTermTest()
    {
        Assert.assertTrue(summaryPage.getValueOf("Committed use discount options")
                .equals(computeEngine.getCommittedTerm()), "Committed term doesn't match with selected");
    }

    @Test(description = "Local SSD test")
    public void localSSDTest()
    {
        Assert.assertTrue(summaryPage.getValueOf("Local SSD")
                .equals(computeEngine.getLocalSSD()), "Local SSD doesn't match with selected");
    }

    @Test(description = "GPU model test")
    public void gpuModelTest()
    {
        Assert.assertTrue(summaryPage.getValueOf("GPU Model")
                .equals(computeEngine.getGpuModel()), "GPU model doesn't match with selected");
    }

    @Test(description = "Region test")
    public void regionTest()
    {
        Assert.assertTrue(summaryPage.getValueOf("Region")
                .equals(computeEngine.getRegion()), "Region doesn't match with selected");
    }

    @Test(description = "Machine type test")
    public void machineTypeTest()
    {
        Assert.assertTrue(summaryPage.getValueOf("Machine type")
                .contains(computeEngine.getMachineType()), "Machine type match with selected");
    }

}

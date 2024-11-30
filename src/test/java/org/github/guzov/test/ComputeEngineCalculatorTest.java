package org.github.guzov.test;

import org.github.guzov.page.GoogleCloudHomePage;
import org.github.guzov.page.GoogleCloudCostEstimateSummaryPage;
import org.github.guzov.product.ComputeEngine;
import org.github.guzov.product.Characteristics;
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

    @Test(description = "Operation system test")
    public void operationSystemTest()
    {
        Assert.assertTrue(summaryPage.getValueOf(Characteristics.OPERATION_SYSTEM.getTitle())
                .equals(computeEngine.getOperationSystem()), "Operation System doesn't match with selected");
    }

    @Test(description = "Committed term test")
    public void committedTermTest()
    {
        Assert.assertTrue(summaryPage.getValueOf(Characteristics.COMMITTED_TERM.getTitle())
                .equals(computeEngine.getCommittedTerm()), "Committed term doesn't match with selected");
    }

    @Test(description = "Local SSD test")
    public void localSSDTest()
    {
        Assert.assertTrue(summaryPage.getValueOf(Characteristics.LOCAL_SSD.getTitle())
                .equals(computeEngine.getLocalSSD()), "Local SSD doesn't match with selected");
    }

    @Test(description = "GPU model test")
    public void gpuModelTest()
    {
        Assert.assertTrue(summaryPage.getValueOf(Characteristics.GPU_MODEL.getTitle())
                .equals(computeEngine.getGpuModel()), "GPU model doesn't match with selected");
    }

    @Test(description = "Region test")
    public void regionTest()
    {
        Assert.assertTrue(summaryPage.getValueOf(Characteristics.REGION.getTitle())
                .equals(computeEngine.getRegion()), "Region doesn't match with selected");
    }

    @Test(description = "Machine type test")
    public void machineTypeTest()
    {
        Assert.assertTrue(summaryPage.getValueOf(Characteristics.MACHINE_TYPE.getTitle())
                .contains(computeEngine.getMachineType()), "Machine type match with selected");
    }

}

package org.github.guzov.test;

import org.github.guzov.service.ComputeEngineCalculatorCreator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ComputeEngineCalculatorTest {

    private String operationSystem = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    private String machineType = "n1-standard-8";
    private String region = "Netherlands (europe-west4)";
    private String localSSD = "2x375 GB";
    private String committedTerm = "1 year";
    private String gpuModel = "NVIDIA TESLA P100";
    private ComputeEngineCalculatorCreator instance;

    @BeforeClass(alwaysRun = true)
    public void instanceCreation() {

        instance = new ComputeEngineCalculatorCreator(new ChromeDriver());
        instance.openCalculatorPage().setComputeEngineProperties();
    }


    @Test(description = "operation system test")
    public void operationSystemTest() {
        Assert.assertTrue(instance.getOperatingSystem()
                .equals(operationSystem), "Operation System doesn't match with selected");
    }

    @Test(description = "committed term test")
    public void committedTermTest() {
        Assert.assertTrue(instance.getCommittedTerm()
                .equals(committedTerm), "Committed term doesn't match with selected");
    }

    @Test(description = "Local SSD test")
    public void localSSDTest() {
        Assert.assertTrue(instance.getLocalSSD()
                .equals(localSSD), "Local SSD doesn't match with selected");
    }

    @Test(description = "GPU model test")
    public void gpuModelTest() {
        Assert.assertTrue(instance.getGPUModel()
                .equals(gpuModel), "GPU model doesn't match with selected");
    }

    @Test(description = "Region test")
    public void regionTest() {
        Assert.assertTrue(instance.getRegion()
                .equals(region), "Region doesn't match with selected");
    }

    @Test(description = "Machine type test")
    public void machineTypeTest() {
        Assert.assertTrue(instance.getMachineType()
                .contains(machineType), "Machine type match with selected");
    }

    @AfterClass(alwaysRun = true)
    public void instanceTierDown () {

        instance.tierDown();
    }
}

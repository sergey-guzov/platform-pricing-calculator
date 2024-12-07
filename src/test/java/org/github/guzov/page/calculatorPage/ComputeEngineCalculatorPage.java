package org.github.guzov.page.calculatorPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.github.guzov.product.ComputeEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComputeEngineCalculatorPage extends GoogleCloudPricingCalculatorPage {
    private ComputeEngine computeEngine;
    private final Logger LOGGER = LogManager.getLogger();

    @FindBy(xpath = "//div[@class='QiFlid']//input")
    WebElement numberOfInstancesField;

    @FindBy(xpath = "//span[text() = 'Operating System / Software']/../../..")
    WebElement operationSystemDropBox;

    @FindBy(xpath = "//div[text()='Provisioning Model']/../..")
    WebElement provisioningModelArea;

    @FindBy(xpath = "//span[text() = 'Machine type']/../../..")
    WebElement machineTypeDropBox;

    @FindBy(xpath = "//button[@aria-label = 'Add GPUs']")
    WebElement gpuCheckBox;

    @FindBy(xpath = "//span[text() = 'GPU Model']/../../..")
    WebElement gpuTypeDropBox;

    @FindBy(xpath = "//span[text() = 'Number of GPUs']/../../..")
    WebElement gpuNumberDropBox;

    @FindBy(xpath = "//span[text() = 'Local SSD']/../../..")
    WebElement localSsdDropBox;

    @FindBy(xpath = "//span[text() = 'Region']/../../..")
    WebElement serverRegionDropBox;

    @FindBy(xpath = "//div[text()='Committed use discount options']/../..")
    WebElement committedPeriodArea;

    public ComputeEngineCalculatorPage (WebDriver driver, ComputeEngine computeEngine)
    {
        super(driver);
        this.computeEngine = computeEngine;
    }

    public ComputeEngineCalculatorPage setNumberOfInstances()
    {
        numberOfInstancesField.clear();
        numberOfInstancesField.sendKeys("value", computeEngine.getInstancesNumber());
        LOGGER.info("Set number of instances - `{}`", computeEngine.getInstancesNumber());
        return this;
    }

    public ComputeEngineCalculatorPage selectOperatingSystem()
    {
        operationSystemDropBox.click();
        selectOptionFromDropBox(operationSystemDropBox, computeEngine.getOperationSystem());
        LOGGER.info("Select operation system - `{}`", computeEngine.getOperationSystem());
        return this;
    }

    public ComputeEngineCalculatorPage selectProvisionModel()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(calculationError));
        activateRadioButton(provisioningModelArea,computeEngine.getProvisioningModel());
        LOGGER.info("Select provisioning model - `{}`", computeEngine.getProvisioningModel());
        return this;
    }

    public ComputeEngineCalculatorPage selectMachineType()
    {
        machineTypeDropBox.click();
        selectOptionFromDropBox(machineTypeDropBox, computeEngine.getMachineType());
        LOGGER.info("Select machine type - `{}`", computeEngine.getMachineType());
        return this;
    }

    public ComputeEngineCalculatorPage setGPU ()
    {
        scrollPageTo(gpuCheckBox);
        gpuCheckBox.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(gpuTypeDropBox));
        scrollPageTo(gpuTypeDropBox);
        gpuTypeDropBox.click();
        selectOptionFromDropBox(gpuTypeDropBox, computeEngine.getGpuModel());
        LOGGER.info("Select GPY type - `{}`", computeEngine.getGpuModel());

        gpuNumberDropBox.click();
        selectOptionFromDropBox(gpuNumberDropBox, computeEngine.getGpuNumber());
        LOGGER.info("Set GPY number - `{}`", computeEngine.getGpuNumber());

        localSsdDropBox.click();
        selectOptionFromDropBox(localSsdDropBox, computeEngine.getLocalSSD());
        LOGGER.info("Set LocalSSD number - `{}`", computeEngine.getLocalSSD());

        return this;
    }

    public ComputeEngineCalculatorPage selectServerRegion ()
    {
        scrollPageTo(serverRegionDropBox);
        serverRegionDropBox.click();
        selectOptionFromDropBox(serverRegionDropBox, computeEngine.getRegion());
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.
                        visibilityOfElementLocated(By.
                                xpath("//span[text() = 'Region']/../../..//span[text()='" + computeEngine.getRegion() + "']")));
        LOGGER.info("Select region - `{}`", computeEngine.getRegion());
        return this;
    }

    public ComputeEngineCalculatorPage selectUsingPeriod ()
    {
        activateRadioButton(committedPeriodArea,computeEngine.getCommittedTerm());
        LOGGER.info("Select using period - `{}`", computeEngine.getCommittedTerm());
        return this;
    }

    public GoogleCloudPricingCalculatorPage setProductParameters ()
    {
        setNumberOfInstances();
        selectOperatingSystem();
        selectProvisionModel();
        selectMachineType();
        setGPU();
        selectServerRegion();
        selectUsingPeriod();
        return this;
    }

}

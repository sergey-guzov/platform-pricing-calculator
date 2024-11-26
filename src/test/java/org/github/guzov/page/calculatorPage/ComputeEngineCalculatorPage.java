package org.github.guzov.page.calculatorPage;

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

    @FindBy(xpath = "//div[@class='QiFlid']//input")
    WebElement numberOfInstancesField;

    @FindBy(xpath = "//span[text() = 'Operating System / Software']/../../..")
    WebElement operationSystemDropBox;

    @FindBy(xpath = "//label[text()='Regular']/..")
    WebElement provisioningModelRadioButton;

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

    @FindBy(xpath = "//label[text()='1 year']/..")
    WebElement usingPeriodRadioButton;

    public ComputeEngineCalculatorPage (WebDriver driver, ComputeEngine computeEngine)
    {
        super(driver);
        this.computeEngine = computeEngine;
    }

    public ComputeEngineCalculatorPage setNumberOfInstances()
    {
        numberOfInstancesField.clear();
        numberOfInstancesField.sendKeys("value", String.valueOf(computeEngine.getInstancesNumber()));
        return this;
    }

    public ComputeEngineCalculatorPage selectOperatingSystem()
    {
        operationSystemDropBox.click();
        selectOptionFromDropBox(operationSystemDropBox, computeEngine.getOperationSystem());
        return this;
    }

    public ComputeEngineCalculatorPage selectProvisionModel()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(calculationError));
        scrollPageTo(provisioningModelRadioButton);
        provisioningModelRadioButton.click();
        return this;
    }

    public ComputeEngineCalculatorPage selectMachineType()
    {
        machineTypeDropBox.click();
        selectOptionFromDropBox(machineTypeDropBox, computeEngine.getMachineType());
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
        gpuNumberDropBox.click();
        selectOptionFromDropBox(gpuNumberDropBox, String.valueOf(computeEngine.getGpuNumber()));
        localSsdDropBox.click();
        selectOptionFromDropBox(localSsdDropBox, String.valueOf(computeEngine.getLocalSSD()));
        return this;
    }

    public ComputeEngineCalculatorPage selectServerRegion ()
    {
        scrollPageTo(serverRegionDropBox);
        serverRegionDropBox.click();
        selectOptionFromDropBox(serverRegionDropBox, computeEngine.getRegion());
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Region']/../../..//span[text()='Netherlands (europe-west4)']")));
        return this;
    }

    public ComputeEngineCalculatorPage selectUsingPeriod ()
    {
        scrollPageTo(usingPeriodRadioButton);
        usingPeriodRadioButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(usingPeriodRadioButton, "checked", ""));
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

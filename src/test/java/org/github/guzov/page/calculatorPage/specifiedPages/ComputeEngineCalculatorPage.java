package org.github.guzov.page.calculatorPage.specifiedPages;

import org.github.guzov.page.AbstractPage;
import org.github.guzov.page.calculatorPage.GoogleCloudCostEstimateSummaryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComputeEngineCalculatorPage extends AbstractPage {

    public ComputeEngineCalculatorPage (WebDriver driver) {

        super(driver);
    }

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

    @FindBy(xpath = "//*[@aria-label='Open detailed view']")
    WebElement openSummaryPageButton;
    @FindBy(xpath = "//div[@class = 'xJ0wqe']//*[contains(@class, 'Z7Qi9d')]")
    WebElement calculationIndicator;

    @FindBy(xpath = "//div[@class='VfPpkd-gIZMF']")
    WebElement calculationError;

    public ComputeEngineCalculatorPage setNumberOfInstances(int number) {

        numberOfInstancesField.clear();
        numberOfInstancesField.sendKeys("value", String.valueOf(number));

        return this;
    }

    public ComputeEngineCalculatorPage selectOperatingSystem(String operationSystem) {

        operationSystemDropBox.click();
        selectOptionFromDropBox(operationSystemDropBox,operationSystem);
        return this;
    }

    public ComputeEngineCalculatorPage selectProvisionModel(String model) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(calculationError));
        scrollPageTo(provisioningModelRadioButton);
        provisioningModelRadioButton.click();
        return this;
    }

    public ComputeEngineCalculatorPage selectMachineType(String machineType) {

        machineTypeDropBox.click();
        selectOptionFromDropBox(machineTypeDropBox,machineType);
        return this;
    }

    public ComputeEngineCalculatorPage setGPU (String gpuType, int gpuNumber, String ssdNumber) {

        scrollPageTo(gpuCheckBox);
        gpuCheckBox.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(gpuTypeDropBox));
        scrollPageTo(gpuTypeDropBox);
        gpuTypeDropBox.click();
        selectOptionFromDropBox(gpuTypeDropBox,gpuType);
        gpuNumberDropBox.click();
        selectOptionFromDropBox(gpuNumberDropBox, String.valueOf(gpuNumber));
        localSsdDropBox.click();
        selectOptionFromDropBox(localSsdDropBox, String.valueOf(ssdNumber));
        return this;

    }

    public ComputeEngineCalculatorPage selectServerRegion (String region) {

        scrollPageTo(serverRegionDropBox);
        serverRegionDropBox.click();
        selectOptionFromDropBox(serverRegionDropBox,region);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Region']/../../..//span[text()='Netherlands (europe-west4)']")));
        return this;

    }

    public ComputeEngineCalculatorPage selectUsingPeriod (String period) {

        scrollPageTo(usingPeriodRadioButton);
        usingPeriodRadioButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(usingPeriodRadioButton, "checked", ""));
        return this;
    }

    public GoogleCloudCostEstimateSummaryPage openSummaryPage () {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(calculationIndicator));
        openSummaryPageButton.click();
        switchToNewTab();
        return new GoogleCloudCostEstimateSummaryPage(driver);
    }

    private void switchToNewTab () {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> webDriver.getWindowHandles().size() > 1
        );
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    protected void scrollPageTo (WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(element));
    }

    private void selectOptionFromDropBox (WebElement dropBox, String requiredOption) {
        String formattedValue = requiredOption.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        driver.findElement(By.xpath("//span[text()='" + requiredOption + "']/../..")).click();
    }


}

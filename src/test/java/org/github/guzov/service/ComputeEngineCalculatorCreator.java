package org.github.guzov.service;

import org.github.guzov.page.GoogleCloudHomePage;
import org.github.guzov.page.calculatorPage.GoogleCloudCostEstimateSummaryPage;
import org.github.guzov.product.ComputeEngine;
import org.openqa.selenium.WebDriver;

public class ComputeEngineCalculatorCreator extends CalculatorCreator {

    private final String PRODUCT_TYPE = "COMPUTE ENGINE";

    private ComputeEngine computeEngineInstance;

    private GoogleCloudCostEstimateSummaryPage summaryPage;


    public ComputeEngineCalculatorCreator(WebDriver driver) {
        super(driver);
        computeEngineInstance = new ComputeEngine();
    }

    @Override
    public ComputeEngineCalculatorCreator openCalculatorPage () {
        driver.manage().window().maximize();
        calculatorPage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchFor(SEARCH_ITEM)
                .selectResult(SEARCH_ITEM);
        return this;
    }

    public void setComputeEngineProperties (){
        summaryPage = calculatorPage.selectProduct(PRODUCT_TYPE)
                .setNumberOfInstances(4)
                .selectOperatingSystem("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)")
                .selectProvisionModel("Regular")
                .selectMachineType("n1-standard-8")
                .setGPU("NVIDIA TESLA P100", 1,"2x375 GB")
                .selectServerRegion("Netherlands (europe-west4)")
                .selectUsingPeriod("1 year")
                .openSummaryPage();
    }

    public String getOperatingSystem () {

        return summaryPage.getValueOf("Operating System / Software");
    }
    public String getCommittedTerm () {
        return summaryPage.getValueOf("Committed use discount options");
    }

    public String getLocalSSD () {
        return summaryPage.getValueOf("Local SSD");
    }

    public String getMachineType () {
        return summaryPage.getValueOf("Machine type");
    }

    public String getGPUModel() {
        return summaryPage.getValueOf("GPU Model");
    }

    public String getRegion() {
        return summaryPage.getValueOf("Region");
    }

    public void tierDown () {
        driver.quit();
        driver = null;
    }
}

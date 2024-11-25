package org.github.guzov.service;


import org.github.guzov.page.calculatorPage.GoogleCloudPricingCalculatorPage;
import org.openqa.selenium.WebDriver;

public abstract class CalculatorCreator {

    protected WebDriver driver;
    protected final String SEARCH_ITEM = "Google Cloud Pricing Calculator";
    protected GoogleCloudPricingCalculatorPage calculatorPage;

    protected CalculatorCreator (WebDriver driver) {
        this.driver = driver;
    }

    protected abstract CalculatorCreator openCalculatorPage();
}

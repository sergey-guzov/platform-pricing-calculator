package org.github.guzov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class GoogleCloudCostEstimateSummaryPage extends AbstractPage {
    private HashMap<String,String> cloudSolutionCharacteristics = new HashMap<>();

    @FindBys(@FindBy(xpath = "//span[@class='zv7tnb']"))
    List<WebElement> listOfSelectedCharacteristics;

    @FindBys(@FindBy(xpath = "//span[@class='Kfvdz']"))
    List<WebElement> listOfSelectedValues;

    private final By COST_ESTIMATE_SUMMARY_LOCATOR = By.xpath("//div[text()='Cost Estimate Summary']");
    public GoogleCloudCostEstimateSummaryPage (WebDriver driver) {super(driver);}

    private void parseDataFromSummaryPage ()
    {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(COST_ESTIMATE_SUMMARY_LOCATOR));
        if (cloudSolutionCharacteristics.isEmpty())
        {
            for (int i = 0; i < listOfSelectedCharacteristics.size(); i++)
            {
                cloudSolutionCharacteristics.
                        put(listOfSelectedCharacteristics.get(i).getText(), listOfSelectedValues.get(i).getText());
            }
        }

    }

    public String getValueOf (String characteristic)
    {
        parseDataFromSummaryPage();
        return cloudSolutionCharacteristics.get(characteristic);
    }

}

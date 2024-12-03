package org.github.guzov.test;

import org.github.guzov.driver.DriverSingleton;

import org.github.guzov.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonCondition {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {driver = DriverSingleton.getDriver();}

    @AfterClass(alwaysRun = true)
    public void tierDownBrowser () {DriverSingleton.stopDriver();}

}

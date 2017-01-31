package stas.applogic;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import stas.pages.PageManager;

/**
 * Created by stas on 1/23/17.
 */
public class DriverHelper
{
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected PageManager pageManager;

    public DriverHelper(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 10);
        pageManager = new PageManager(driver);
    }
}

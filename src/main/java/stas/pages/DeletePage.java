package stas.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stas on 1/28/17.
 */
public class DeletePage extends Page {

    @FindBy(css = ".ui-button:first-child")
    private WebElement yesButton;

    @FindBy(css = ".ui-button:last-child")
    private WebElement noButton;


    public DeletePage(PageManager pages) {
        super(pages);
    }

    public MainPage clickYesButton() {
        yesButton.click();
        return pages.getMainPage();
    }

    public MainPage clickNoButton() {
        noButton.click();
        return pages.getMainPage();
    }
}

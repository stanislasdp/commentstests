package stas.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by skir on 1/23/2017.
 */
public class CommentPage extends Page {

    @FindBy (id = "Text")
    private WebElement commentText;
    @FindBy (id = "Number")
    private WebElement commentNumber;
    @FindBy (id = "Active")
    private WebElement active;

    @FindBy(css ="div[id='alvailablecategories'] > .categoryitem > input[type ='checkbox']")
    private List<WebElement> availableCategorieschekboxes;

    @FindBy(css ="div[id='alvailablecategories'] > .categoryitem > span")
    private List<WebElement> availableCategoriesText;


    @FindBy(css ="div[id='selectedCategories'] > .categoryitem > input[type ='checkbox']")
    private List<WebElement> selectedCategoriesCheckboxes;

    @FindBy(css ="div[id='selectedCategories'] > .categoryitem > span")
    private List<WebElement> selectedCategoriesText;


    @FindBy(css = "input[name='CurSelect']")
    private WebElement addSelectedCategoriesButton;

    @FindBy(css = "input[name='AllSelect']")
    private WebElement addAllCategoriesButton;

    @FindBy(css = "input[name='CurUnSelectBtn']")
    private WebElement removeSelectedCategoriesButton;

    @FindBy(css = "input[name='AllUnSelectBtn']")
    private WebElement removeAllCategoriesButton;

    @FindBy (css = "#editor-navigation >a")
    private WebElement refreshButton;

    @FindBy(css = ".buttonAsLink[value='Save']")
    private WebElement saveButton;

    @FindBy(css = ".buttonAsLink[value='Save & Return']")
    private WebElement saveAndReturnButton;

    @FindBy(css = "#editor-navigation > a")
    private WebElement returnButton;


    public CommentPage(PageManager pages) {
        super(pages);
    }


    public CommentPage enterCommentText(String text) {
        commentText.clear();
        commentText.sendKeys(text);
        return this;
    }

    public String getCommentText() {
       return commentText.getAttribute("value");
    }

    public CommentPage enterCommentNumber(String number) {
        commentNumber.clear();
        commentNumber.sendKeys(number + "");
        return this;
    }

    public String getCommentNumber() {
        return commentNumber.getAttribute("value");
    }

    public CommentPage checkActiveCheckbox() {
        active.click();
        return this;
    }

    public boolean isCommentChecked() {
        return active.isSelected();
    }

    public int getAvaialbleCategoriesLength() {
        return  availableCategorieschekboxes.size();
    }


    public CommentPage checkAvailableCategory(int categoryNumber) {

        for (WebElement checkbox : availableCategorieschekboxes) {
            int checkBoxNumber = Integer.parseInt(checkbox.getAttribute("value"));
            if (checkBoxNumber == categoryNumber +1) {
                checkbox.click();
            }
        }
        return this;
    }

    public String getAvaiableCategoryText(int categoryNumber) {
        return availableCategoriesText.get(categoryNumber).getText();
    }


    public CommentPage checkSelectedCategory(int categoryNumber) {

        for (WebElement checkbox : selectedCategoriesCheckboxes) {
            int checkBoxNumber = Integer.parseInt(checkbox.getAttribute("value"));
            if (checkBoxNumber == categoryNumber +1) {
                checkbox.click();
            }
        }
        return this;
    }

    public String getSelectedCategoryText(int categoryNumber) {
       return selectedCategoriesText.get(categoryNumber).getText();
    }

    public int getSelelctedCategoriesLength() {
        return  selectedCategoriesCheckboxes.size();
    }




    public CommentPage clickAddSelectedCategories() {
        addSelectedCategoriesButton.click();
        return this;
    }

    public CommentPage clickRemoveSelectedCategories() {
        removeSelectedCategoriesButton.click();
        return this;
    }

    public CommentPage clickAddAllCategories() {
        addAllCategoriesButton.click();
        return this;
    }

    public CommentPage clickRemoveAllCategories() {
        removeAllCategoriesButton.click();
        return this;
    }


    public CommentPage clickRefreshButton() {
        refreshButton.click();
        return this;
    }

    public CommentPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    public MainPage clickSaveAndReturnButton() {
        saveAndReturnButton.click();
        return pages.getMainPage();
    }



    public CommentPage clickReturnButton() {
        returnButton.click();
        return this;
    }

    @Override
    public CommentPage ensurePageLoaded() {
        super.ensurePageLoaded();
        //wait until comment text field will be present
        wait.until(presenceOfElementLocated(By.cssSelector("#Number")));
        return this;
    }

    /*  public CommentPage checkCategoryValidation (String text)  {
        WebElement webElement = driver.findElement(By.xpath("//div[@id='errorfield' "
               + "and contains(.,'"+ text + "')]"));
        return this;
    }*/
}

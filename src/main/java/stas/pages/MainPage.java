package stas.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by skir on 1/23/2017.
 */
public class MainPage extends Page {

    public MainPage (PageManager pages)  {
        super(pages);
    }

    @FindBy(id = "newbutton")
    @CacheLookup
    private WebElement newComment;

    @FindBy(css = "input[value='Duplicate...']")
    @CacheLookup
    private WebElement duplicateComment;

    @FindBy(css = "input[value='Edit..']")
    @CacheLookup
    private WebElement editComment;

    @FindBy(css = "input[value='Delete']")
    @CacheLookup
    private WebElement deleteComment;

    @FindBy(id = "commandSelect")
    @CacheLookup
    private WebElement actionDropDown;

    @FindBy(id= "SelectedCateg")
    @CacheLookup
    private WebElement categoryNamesDropDown;

    @FindBy(id = "SelectedStatus")
    @CacheLookup
    private WebElement statusesDropDown;

    @FindBy(id = "applybutton")
    @CacheLookup
    private WebElement applyButton;

    @FindBy(css = ".webgrid-header >th:nth-child(2) > a")
    @CacheLookup
    private WebElement numberHeader;

    @FindBy(css = ".webgrid-header >th:nth-child(3) > a")
    @CacheLookup
    private WebElement commentTextHeader;

    @FindBy(css = ".webgrid-header >th:nth-child(4) > a")
    @CacheLookup
    private WebElement activeTextHeader;

    @FindBy(css = ".webgrid-header >th:nth-child(5) > a")
    @CacheLookup
    private WebElement categoryHeader;

    @FindBy(id="SelectedCateg")
    @CacheLookup
    private WebElement categoriesDropDown;

    @FindBy(css = ".webgrid-row-style >.numbercolumn, .webgrid-alternating-row >.numbercolumn")
    private List<WebElement> commentsNumbers;

    @FindBy(css = ".webgrid-row-style > .textcolumn, .webgrid-alternating-row > .textcolumn")
    private List<WebElement> commentsText;

    @FindBy(css = ".webgrid-row-style >.inactivecolumn,.webgrid-alternating-row >.inactivecolumn")
    private List<WebElement> commentsStatuses;

    @FindBy(css = ".webgrid-row-style >.categorycolumn,.webgrid-alternating-row >.categorycolumn")
    private List<WebElement> commentCategories;

    @FindBy(css = ".checkedcolumn > input[type='checkbox']")
    private List<WebElement> commentsCheckboxes;

    @FindBy(css = ".webgrid-footer>td>a")
    List<WebElement> tablepages;

    @FindBy (css = "#logindisplay>a")
    private WebElement refReshButton;

    public CommentPage clickNewCommentButt() {
        newComment.click();
        return pages.getCommentsPage();
    }

    public CommentPage clickDuplicateCommentButt() {
        duplicateComment.click();
        return pages.getCommentsPage();
    }

    public CommentPage clickEditCommentButt() {
        editComment.click();
        return pages.getCommentsPage();
    }

    public DeletePage clickDeleteCommentButt() {
        deleteComment.click();
        return pages.getDeletePage();
    }

    public MainPage selectAction(String action) {
        Select actionDropDownSelect = new Select(actionDropDown);
        actionDropDownSelect.selectByVisibleText(action);
        return this;
    }

    public String getAction() {
        Select actionDropDownSelect = new Select(actionDropDown);
        return actionDropDownSelect.getFirstSelectedOption().getAttribute("value");
    }

    public MainPage selectCategoryToFilter(int category) {

        Select catDropDownSelect = new Select(categoriesDropDown);
        if ((category >= 1 && category <= 6) || category == -1) {
            catDropDownSelect.selectByIndex(category);
        } else {
            throw new IllegalArgumentException("not supported category");
        }
        return this;
    }

    public String getCategoryToFilter() {
        Select catDropDownSelect = new Select(categoriesDropDown);
        return catDropDownSelect.getFirstSelectedOption().getAttribute("value");
    }

    public MainPage selectStatusToFilter(String status) {

        Select statusDropDownSelect = new Select(statusesDropDown);
        statusDropDownSelect.selectByValue(status);
        return this;
    }

    public String getStatusToFilter() {
        Select statusDropDownSelect = new Select(statusesDropDown);
        return statusDropDownSelect.getFirstSelectedOption().getAttribute("value");
    }

    public MainPage clickApplyButton() {
        applyButton.click();
        return this;
    }


    public MainPage clickSortTableByNumber() {
        numberHeader.click();
        return this;
    }

    public MainPage clickSortTableByCommentText() {
        commentTextHeader.click();
        return this;
    }

    public MainPage clickSortTableByActiveComment() {
        activeTextHeader.click();
        return this;
    }

    public MainPage clickSortTableByCategory() {
        categoryHeader.click();
        return this;
    }


    public MainPage checkCommentCheckBoxInTable(int rowNumber)
    {
     commentsCheckboxes.get(rowNumber).click();
        return this;
    }

    public boolean isCheckBoxInTableChecked (int rowNumber) {
        return commentsCheckboxes.get(rowNumber).isSelected();
    }

    public MainPage clickPageNumber(int rowNumber) {

        for (WebElement pageElement: tablepages) {
             if (Integer.parseInt(pageElement.getText()) == rowNumber) {
                 pageElement.click();
                 return this;
             }
        }
        throw new NoSuchElementException("No such page number is present on the page");
    }

    public int getCurrentPageNumber() {
        JavascriptExecutor  js =((JavascriptExecutor) driver);
        String result = (String) js.executeScript
                ("return $('tr.webgrid-footer>td').clone().children().remove().end().text().trim();");
        return Integer.parseInt(result);
    }



    public boolean isNextpageExists() {

        WebElement prPage = driver.findElement(By.cssSelector(".webgrid-footer>td>a:last-child"));
        return prPage.getText().equals(">");
    }

    public boolean isPageExists(int pageNum) {

        for (WebElement page: tablepages) {
            if (!page.getText().equals("<") && !page.getText().equals(">")) {
                if (Integer.parseInt(page.getText()) == pageNum) {
                    return true;
                }
            }
        }
        return false;
    }


    public MainPage clickPreviousPage() {
        WebElement prevPage = tablepages.get(0);
        if (prevPage.getText().equals("<")) {
            prevPage.click();
            return this;
        } else {
            throw new NoSuchElementException("previous page number has not been found oth the page");
        }
    }

    public MainPage clickNextPage() {

        WebElement lastPage = tablepages.get(tablepages.size() -1);
        if (lastPage.getText().equals(">")) {
            lastPage.click();
            return this;
        } else {
            throw new NoSuchElementException("next page number has not been found oth the page\"");
        }
    }


    public boolean isPreviousPageExists() {
            WebElement prPage = driver.findElement(By.cssSelector(".webgrid-footer>td>a:first-child"));
            return prPage.getText().equals("<");
    }

    public int getCommentRowNumberInTable(int commentID) {

        for (int i = 0; i < commentsNumbers.size() ; i++) {
            if (Integer.parseInt(commentsNumbers.get(i).getText()) == commentID) {
                return i;
            }
        }
        throw new NoSuchElementException("no such number in the current page");
    }

    public String getCommentNumberfromRowInTable(int rowNum) {
        return commentsNumbers.get(rowNum).getText();
    }

    public String getCommentsTextInTable(int rowNumber) {
        return commentsText.get(rowNumber).getText();
    }

    public String getCommentActiveStatus(int rowNumber) {
        return commentsStatuses.get(rowNumber).getText();
    }

    public String getCommentsCategories(int rowNumber) {
        return commentCategories.get(rowNumber).getText();
    }

    public int getRowsCount() {
        return commentsNumbers.size();
    }

    public MainPage clickRefreshButton() {
        refReshButton.click();
        return this;
    }

    @Override
    public MainPage ensurePageLoaded() {
        super.ensurePageLoaded();
        wait.until(presenceOfElementLocated(By.cssSelector("#title>h1")));
        return this;
    }
}

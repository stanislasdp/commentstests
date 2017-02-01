package stas.applogic;

import stas.model.Category;
import stas.model.Comment;
import stas.pages.MainPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/26/17.
 */
public class MainHelper extends DriverHelper {

    public MainHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
    }

    public void refReshCommentsPage() {
        pageManager.getMainPage().clickRefreshButton();
    }


    public void addNewComment() {
        pageManager.getMainPage().clickNewCommentButt();
    }

    public void editComment() {
        pageManager.getMainPage().clickEditCommentButt();
    }

    public void duplicateComment() {
        pageManager.getMainPage().clickDuplicateCommentButt();
    }

    public void setCategory(int category) {
        pageManager.getMainPage().selectCategoryToFilter(category);
    }

    public String getCategory() {
       return pageManager.getMainPage().getCategoryToFilter();
    }

    public void setStatus(String status) {
        pageManager.getMainPage().selectStatusToFilter(status);
    }

    public String getStatus() {
       return pageManager.getMainPage().getStatusToFilter();
    }

    public void setAction(String action) {
       pageManager.getMainPage().selectAction(action);
    }
    public String getAction() {
       return pageManager.getMainPage().getAction();
    }

    public void setApply() {
        pageManager.getMainPage().clickApplyButton();
    }

    public void setCommentsWithCheckBox(int rowNum) {
        pageManager.getMainPage().checkCommentCheckBoxInTable(rowNum);
    }

    public boolean isCommentCheckedInTable( int tableRowNumber) {
        return  pageManager.getMainPage().isCheckBoxInTableChecked(tableRowNumber);
    }

    public Comment getCommentFromMainPage(int commentRow) {
        MainPage mainPage = pageManager.getMainPage();
        mainPage.ensurePageLoaded();
        String commentId = mainPage.getCommentNumberfromRowInTable(commentRow);
        String commentText = mainPage.getCommentsTextInTable(commentRow);
        String commentStatusString = mainPage.getCommentActiveStatus(commentRow);
        boolean isCommentActive = false;
        if (commentStatusString.equals("V")) {
            isCommentActive = true;
        } else if (!commentStatusString.isEmpty()) {
            throw new IllegalArgumentException("Ilegal active status");
        }
        String[] categories = mainPage.getCommentsCategories(commentRow).split(";");
        List<Category> categorList = new ArrayList<>();
        for (String cat: categories) {
            categorList.add(new Category(cat.trim()));
        }
        Comment gotComment = new Comment();
        gotComment.setCommentId(commentId);
        gotComment.setCommentText(commentText);
        gotComment.setActive(isCommentActive);
        gotComment.setCategories(categorList);
        return gotComment;
    }


    public int getCommentTableSize() {
        return pageManager.getMainPage().getRowsCount();
    }

    public void sortCommentsByNumber() {
        pageManager.getMainPage().clickSortTableByNumber();
    }

    public void setCommentsPageNumber(int pageNumber) {
        pageManager.getMainPage().clickPageNumber(pageNumber);
    }

    public void setNextPage() {
        pageManager.getMainPage().clickNextPage();
    }

    public void setPreviousPage() {
        pageManager.getMainPage().clickPreviousPage();
    }

    public boolean isPreviousPageExists()
    {
        return pageManager.getMainPage().isPreviousPageExists();
    }

    public boolean isNextPageExists() {
        return pageManager.getMainPage().isNextpageExists();
    }

    public boolean isPageExists(int pageNumber) {
        return pageManager.getMainPage().isPageExists(pageNumber);
    }

    public int getCurrentPageNumber() {
      return   pageManager.getMainPage().getCurrentPageNumber();
    }
}

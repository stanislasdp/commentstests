package stas.applogic;
import stas.model.Category;
import stas.model.Comment;
import stas.pages.CommentPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/24/17.
 */
public class CommentHelper extends DriverHelper {

    public CommentHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
    }

    public Comment setCommentTextToComment(Comment comment, String text) {
        CommentPage commentPage = pageManager.getCommentsPage();
        commentPage.enterCommentText(text);
        comment.setCommentText(text);
        return comment;
    }

    public Comment setCommentActiveStatus(Comment comment, boolean isActive) {
        CommentPage commentPage = pageManager.getCommentsPage();
        commentPage.checkActiveCheckbox();
        boolean activeStatus = commentPage.isCommentChecked();
        comment.setActive(activeStatus);
        return comment;
    }

    public String getCommentText(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        comment.setCommentText(commentPage.getCommentText());
        return comment.getCommentText();
    }

    public Comment setCommentNumberToComment(Comment comment, String number) {
        CommentPage commentPage = pageManager.getCommentsPage();
        commentPage.enterCommentNumber(number);
        comment.setCommentId(number);
        return comment;
    }

    public String getCommentNumber(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        comment.setCommentId(commentPage.getCommentNumber());
        return comment.getCommentId();
    }

    public boolean isCommentActive(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        comment.setActive(commentPage.isCommentChecked());
        return comment.isActive();
    }

    public Comment setCategoriesToComment(Comment comment, List<Category> newCategories) {
        CommentPage commentPage = pageManager.getCommentsPage();
        List<Category> commentCategories = comment.getCategories();

        for (Category category: newCategories) {
            if (!commentCategories.contains(category)) {
                commentPage.checkAvailableCategory(category.getIntCategory());
                comment.getCategories().add(new Category(category.getCategory()));
            }
        }
        commentPage.clickAddSelectedCategories();
        return comment;
    }

    public List<Category> getAvailableCategories() {
        CommentPage commentPage = pageManager.getCommentsPage();
        List<Category> avaialableCategories = new ArrayList<>();

        for (int i = 0; i < commentPage.getAvaialbleCategoriesLength() ; i++) {
            Category category = new Category(commentPage.getAvaiableCategoryText(i));
            avaialableCategories.add(category);
        }
        return avaialableCategories;
    }

    public Comment setAllCategoriesToComment(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        List<Category> commentCategories = comment.getCategories();
       int categoriesLength =  commentPage.getAvaialbleCategoriesLength();

        for (int i = 0; i < categoriesLength ; i++) {
            Category category = new Category(commentPage.getAvaiableCategoryText(i));
            if (!commentCategories.contains(category)) {
                commentCategories.add(category);
            }
        }
        commentPage.clickAddAllCategories();
        return comment;
    }

    public Comment removeCategoriesFromComment(Comment comment, List<Category> removeCategories) {
        CommentPage commentPage = pageManager.getCommentsPage();
        List<Category> commentCategories = comment.getCategories();

        for (Category category: removeCategories) {
            if (commentCategories.contains(category)) {
                commentPage.checkSelectedCategory(category.getIntCategory());
                comment.getCategories().remove(category);
            }
        }
        commentPage.clickRemoveSelectedCategories();
        return comment;
    }


    public Comment removeAllCategoriesFromComment(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        List<Category> commentCategories = comment.getCategories();
        commentCategories.clear();
        commentPage.clickRemoveAllCategories();
        return comment;
    }


    public List<Category> getSeelctedCategories(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        List<Category> commentCategories = comment.getCategories();
        commentCategories.clear();

        for (int i = 0; i < commentPage.getSelelctedCategoriesLength() ; i++) {
            Category category = new Category(commentPage.getSelectedCategoryText(i));
            commentCategories.add(category);
        }
        return comment.getCategories();
    }


    public void refreshCommentsPage(Comment comment) {
        CommentPage commentPage = pageManager.getCommentsPage();
        pageManager.getCommentsPage().clickRefreshButton();
        comment.setCommentText(commentPage.getCommentText());
        comment.setActive(commentPage.isCommentChecked());
        comment.setCommentId(commentPage.getCommentNumber());
        comment.setCategories(getSeelctedCategories(comment));
    }

    public void saveComment() {
        pageManager.getCommentsPage().clickSaveButton();
    }

    public void saveCommentAndReturnToMainPage() {
        pageManager.getCommentsPage().clickSaveAndReturnButton();
    }

}

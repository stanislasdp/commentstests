package stas;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stas.applogic.ApplicationManage;
import stas.applogic.ApplicationManager;
import stas.applogic.CommentHelper;
import stas.applogic.MainHelper;
import stas.model.Category;
import stas.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stas on 1/28/17.
 */
public class RefreshCommentTestNew {

    private ApplicationManage app;

    @BeforeClass
    public void init() {
        System.setProperty("webdriver.chrome.driver", "/media/MEDIA/install/linux/instALL/chromedriver");
        app = new ApplicationManager(new ChromeDriver());
        app.getNavigationManage().openMainPage();
    }

    @Test
    public void refreshPageWhenCommentisAdded() {

        String expCommentText = "";
        String expCommentNumver = "";
        List<Category> expectedCategories = new ArrayList<>();
        Comment expComment = new Comment();
        expComment.setCommentText(expCommentText);
        expComment.setCommentId(expCommentNumver);
        expComment.setCategories(expectedCategories);

        MainHelper commentsHelper = app.getCommentsHelper();

        commentsHelper.addNewComment();
        CommentHelper commHelp = app.getCommentHelper();
        Comment actComment = new Comment();
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        commHelp.setCommentTextToComment(actComment, "Some Text");
        commHelp.setCommentNumberToComment(actComment,"555");
        commHelp.setAllCategoriesToComment(actComment);
        commHelp.refreshCommentsPage(actComment);

        commHelp.setCommentTextToComment(actComment, "Some Text 2");
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        Assert.assertEquals(actComment, expComment);
        commHelp.setCommentNumberToComment(actComment ,"777");
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        List<Category> actCategories = new ArrayList<>();
        actCategories.add(new Category("Cat0"));
        actCategories.add(new Category("Cat1"));
        commHelp.setCategoriesToComment(actComment ,actCategories);
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        commHelp.setCommentActiveStatus(actComment ,false);
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);


    }




}

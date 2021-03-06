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
 * Created by stas on 1/29/17.
 */
public class RefreshCommentTestDuplicate {

    private ApplicationManage app;

    @BeforeClass
    public void init() {
        //System.setProperty("webdriver.chrome.driver", "/media/MEDIA/install/linux/instALL/chromedriver");
        System.setProperty("webdriver.chrome.driver", "D:\\KiryanStanislav\\screnshots\\SoftBank\\PCC-22108\\PCC-22108\\PCC-22108\\selenium\\chromedriver.exe");
        app = new ApplicationManager(new ChromeDriver());
        app.getNavigationManage().openMainPage();
    }


    @Test
    public void refreshPageWhenCommentisAdded() {

        MainHelper mainHelp = app.getCommentsHelper();
        CommentHelper commHelp = app.getCommentHelper();
        mainHelp.setCommentsWithCheckBox(1);
        mainHelp.duplicateComment();
        Comment expComment = new Comment();
        expComment.setCommentText(commHelp.getCommentText(expComment));
        expComment.setCommentId(commHelp.getCommentNumber(expComment));
        expComment.setActive(commHelp.isCommentActive(expComment));
        List<Category> expAvailableCategories = commHelp.getAvailableCategories();
        expComment.setCategories(commHelp.getSeelctedCategories(expComment));


        Comment actComment = new Comment();
        commHelp.setCommentTextToComment(actComment,expComment.getCommentText());
        commHelp.setCommentNumberToComment(actComment, expComment.getCommentId());
        commHelp.setCommentActiveStatus(actComment,expComment.isActive());
        commHelp.setCategoriesToComment(actComment, expComment.getCategories());

        List<Category> actAvailableCategories = commHelp.getAvailableCategories();
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);
        Assert.assertEquals(actAvailableCategories, expAvailableCategories);

        commHelp.setCommentTextToComment(actComment, "New Text");
        commHelp.setCommentNumberToComment(actComment,"333");
        commHelp.setCommentActiveStatus(actComment,false);
        commHelp.setAllCategoriesToComment(actComment);
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        commHelp.setCommentTextToComment(actComment,"New Text2");
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        commHelp.setCommentNumberToComment(actComment,"444");
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        List<Category> newCategories = new ArrayList<>();
        newCategories.add(new Category("Cat5"));
        commHelp.setCategoriesToComment(actComment, newCategories);
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);

        commHelp.setCommentActiveStatus(actComment, true);
        commHelp.refreshCommentsPage(actComment);
        Assert.assertEquals(actComment, expComment);
    }
}

package stas.applogic;

import org.openqa.selenium.WebDriver;

/**
 * Created by stas on 1/23/17.
 */
public interface ApplicationManage
{
    NavigationManage getNavigationManage();
    void stopApp();
    WebDriver getDriver();
    String getStartURL();
    MainHelper getCommentsHelper();
    CommentHelper getCommentHelper();
    DeleteHelper getDeleteHelper();
}

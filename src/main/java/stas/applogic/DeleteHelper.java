package stas.applogic;

import stas.pages.DeletePage;
import stas.pages.MainPage;

/**
 * Created by skir on 2/1/2017.
 */
public class DeleteHelper extends DriverHelper {

    public DeleteHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
    }

    public void deleteComment() {
        MainPage mainPage = pageManager.getMainPage();
        DeletePage deletePage = mainPage.clickDeleteCommentButt();
        deletePage.clickYesButton();
    }

    public void notDeleteComment() {
        MainPage mainPage = pageManager.getMainPage();
        DeletePage deletePage = mainPage.clickDeleteCommentButt();
        deletePage.clickNoButton();
    }
}
